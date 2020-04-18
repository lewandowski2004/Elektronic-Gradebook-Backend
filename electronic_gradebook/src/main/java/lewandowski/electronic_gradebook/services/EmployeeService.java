package lewandowski.electronic_gradebook.services;


import lewandowski.electronic_gradebook.Component.MessageComponent;
import lewandowski.electronic_gradebook.dto.EmployeeDto;
import lewandowski.electronic_gradebook.dto._toSave.EmployeeDtoToSave;
import lewandowski.electronic_gradebook.model.Address;
import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.model.Role;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    public RoleService roleService;
    @Autowired
    public SchoolService schoolService;
    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public MessageComponent messageComponent;
    @Autowired
    public PasswordEncoder encoder;


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

    public EmployeeDto findById(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(messageComponent.NOT_FOUND));
        return getEmployeeDto(employee);
    }

    public Set<Employee> findByIdIn(Set<UUID> listId) {
        return employeeRepository.findByIdIn(listId);
    }

    public EmployeeDto findByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(messageComponent.NOT_FOUND));
        return getEmployeeDto(employee);
    }

    public List<Employee> findAllEmployees(List<EmployeeDto> employeesDtoList) {
        List<Employee> employeeList = new ArrayList<>();
        for (EmployeeDto employeeDto : employeesDtoList) {
            Employee employee = getEmployee(employeeDto);
            employeeList.add(employee);
        }
        return employeeList;
    }

    public List<EmployeeDto> findAllEmployeesDto(List<Employee> employeeList) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee : employeeList) {
            EmployeeDto employeeDto = getEmployeeDto(employee);
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }

    public List<EmployeeDto> getEmployeesDto() {
        return findAllEmployeesDto(employeeRepository.findAll());
    }
}

