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
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    public   RoleService roleService;
    @Autowired
    public   SchoolService schoolService;
    @Autowired
    public   EmployeeRepository employeeRepository;
    @Autowired
    public   PasswordEncoder encoder;


    public void saveEmployeeDto(EmployeeDtoToSave employeeDto) {
        Address address = Address.builder()
                .street(employeeDto.getStreet())
                .buildingNumber(employeeDto.getBuildingNumber())
                .apartmentNumber(employeeDto.getApartmentNumber())
                .city(employeeDto.getCity())
                .zipCode(employeeDto.getZipCode())
                .voivodeship(employeeDto.getVoivodeship())
                .country(employeeDto.getCountry())
                .build();
        Employee employee = Employee.builder()
                .name(employeeDto.getName())
                .secondName(employeeDto.getSecondName())
                .lastName(employeeDto.getLastName())
                .pesel(employeeDto.getPesel())
                .dateOfBirth(employeeDto.getDateOfBirth())
                .dateOfAddition(new Date())
                .phoneNumber(employeeDto.getPhoneNumber())
                .username(employeeDto.getUsername())
                .active(employeeDto.getActive())
                .email(employeeDto.getEmail())
                .gender(employeeDto.getGender())
                .address(address)
                .school(schoolService.getSchool(employeeDto.getSchoolDto()))
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

    public EmployeeDto findByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Error: Employee is not found."));
        return getEmployeeDto(employee);
    }

    public Employee getEmployee(EmployeeDto employeeDto) {
        Address address = Address.builder()
                .street(employeeDto.getStreet())
                .buildingNumber(employeeDto.getBuildingNumber())
                .apartmentNumber(employeeDto.getApartmentNumber())
                .city(employeeDto.getCity())
                .zipCode(employeeDto.getZipCode())
                .voivodeship(employeeDto.getVoivodeship())
                .country(employeeDto.getCountry())
                .build();
        Employee employee = Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .secondName(employeeDto.getSecondName())
                .lastName(employeeDto.getLastName())
                .pesel(employeeDto.getPesel())
                .dateOfBirth(employeeDto.getDateOfBirth())
                .dateOfAddition(new Date())
                .phoneNumber(employeeDto.getPhoneNumber())
                .username(employeeDto.getUsername())
                .active(employeeDto.getActive())
                .email(employeeDto.getEmail())
                .gender(employeeDto.getGender())
                .address(address)
                .school(schoolService.getSchool(employeeDto.getSchoolDto()))
                .password(encoder.encode(employeeDto.getPassword()))
                .roles(roleService.getRoles(employeeDto.getRolesDto()))
                .build();
        return employee;
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

        Address address = Address.builder()
                .street(employee.getAddress().getStreet())
                .buildingNumber(employee.getAddress().getBuildingNumber())
                .apartmentNumber(employee.getAddress().getApartmentNumber())
                .city(employee.getAddress().getCity())
                .zipCode(employee.getAddress().getZipCode())
                .voivodeship(employee.getAddress().getVoivodeship())
                .country(employee.getAddress().getCountry())
                .build();
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .secondName(employee.getSecondName())
                .lastName(employee.getLastName())
                .pesel(employee.getPesel())
                .dateOfBirth(employee.getDateOfBirth())
                .dateOfAddition(employee.getDateOfAddition())
                .phoneNumber(employee.getPhoneNumber())
                .username(employee.getUsername())
                .active(employee.getActive())
                .email(employee.getEmail())
                .gender(employee.getGender())
                .schoolDto(schoolService.getSchoolDto(employee.getSchool()))
                .password(encoder.encode(employee.getPassword()))
                .rolesDto(roleService.getRolesDto(employee.getRoles()))
                .build();
        return employeeDto;
    }
}
