package lewandowski.electronic_gradebook.services;


import lewandowski.electronic_gradebook.dto.EmployeeDto;
import lewandowski.electronic_gradebook.dto._toSave.EmployeeDtoToSave;
import lewandowski.electronic_gradebook.model.Address;
import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder encoder;

    public void saveEmployeeDto(EmployeeDtoToSave employeeDto) {
        Address address = Address.builder()
                .addressLine1(employeeDto.getAddressLine1())
                .addressLine2(employeeDto.getAddressLine2())
                .city(employeeDto.getCity())
                .zipCode(employeeDto.getZipCode())
                .build();
        Employee employee = Employee.builder()
                .name(employeeDto.getName())
                .lastName(employeeDto.getLastName())
                .username(employeeDto.getUsername())
                .active(employeeDto.getActive())
                .email(employeeDto.getEmail())
                .address(address)
                .password(encoder.encode(employeeDto.getPassword()))
                .roles(roleService.findByIdIn(employeeDto.getRoles()))
                .build();

        employeeRepository.save(employee);
    }
   /* @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUserById(UUID id){
        User user = userRepository.findById(id);
        userRepository.delete(user);
    }


    public List<UserDto> findAllUsersDto() {
        return findAllUsersDtoList(userRepository.findAll());
    }*/

    public EmployeeDto findById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Error: Employee is not found."));
        return getEmployeeDto(employee);
    }

    public Employee getEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .password(employeeDto.getPassword())
                .active(employeeDto.getActive())
                .roles(roleService.getRoles(employeeDto.getRoleDtos()))
                .build();

    }

    public List<EmployeeDto> findAllEmployeesDtoList(List<Employee> employeeList) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee : employeeList) {
            EmployeeDto pupilDto = getEmployeeDto(employee);
            employeeDtoList.add(pupilDto);
        }
        return employeeDtoList;
    }

    public List<EmployeeDto> findAllEmployeesDto() {
        return findAllEmployeesDtoList(employeeRepository.findAll());
    }

    public EmployeeDto getEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .lastName(employee.getLastName())
                .username(employee.getUsername())
                .email(employee.getEmail())
                .password(employee.getPassword())
                .roleDtos(roleService.getRolesDto(employee.getRoles()))
                .build();
    }
}
