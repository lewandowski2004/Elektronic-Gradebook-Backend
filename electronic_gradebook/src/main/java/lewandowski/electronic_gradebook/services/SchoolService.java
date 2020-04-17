package lewandowski.electronic_gradebook.services;


import lewandowski.electronic_gradebook.Component.MessageComponent;
import lewandowski.electronic_gradebook.dto.SchoolDto;
import lewandowski.electronic_gradebook.dto._toSave.SchoolDtoToSave;
import lewandowski.electronic_gradebook.model.Address;
import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.model.School;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class SchoolService {

    @Autowired
    public SchoolRepository schoolRepository;
    @Autowired
    public SchoolService schoolService;
    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public MessageComponent messageComponent;
    @Autowired
    public EmployeeService employeeService;


    public void saveSchoolDto(SchoolDtoToSave schoolDto) {
        Address address = Address.builder()
                .street(schoolDto.getStreet())
                .buildingNumber(schoolDto.getBuildingNumber())
                .apartmentNumber(schoolDto.getApartmentNumber())
                .city(schoolDto.getCity())
                .zipCode(schoolDto.getZipCode())
                .voivodeship(schoolDto.getVoivodeship())
                .country(schoolDto.getCountry())
                .build();
        School school = School.builder()
                .name(schoolDto.getName())
                .patron(schoolDto.getPatron())
                .dateOfAddition(new Date())
                .phoneNumber(schoolDto.getPhoneNumber())
                .email(schoolDto.getEmail())
                .address(address)
                .typeSchool(schoolDto.getTypeSchool())
                .kindSchool(schoolDto.getKindSchool())
                .build();

        schoolRepository.save(school);
    }

    public void saveSchoolDtoAndUpdateSchoolInSchoolAdministrator(SchoolDtoToSave schoolDto, UUID id) {
        Address address = Address.builder()
                .street(schoolDto.getStreet())
                .buildingNumber(schoolDto.getBuildingNumber())
                .apartmentNumber(schoolDto.getApartmentNumber())
                .city(schoolDto.getCity())
                .zipCode(schoolDto.getZipCode())
                .voivodeship(schoolDto.getVoivodeship())
                .country(schoolDto.getCountry())
                .build();
        School school = School.builder()
                .name(schoolDto.getName())
                .patron(schoolDto.getPatron())
                .dateOfAddition(new Date())
                .phoneNumber(schoolDto.getPhoneNumber())
                .email(schoolDto.getEmail())
                .address(address)
                .typeSchool(schoolDto.getTypeSchool())
                .kindSchool(schoolDto.getKindSchool())
                .build();

        School schoolToSave = schoolRepository.save(school);
        Employee employee = employeeRepository.findById(id).get();
        employee.setSchool(schoolToSave);
        employeeRepository.save(employee);
    }
    public SchoolDto findById(UUID id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(messageComponent.NOT_FOUND));
        return getSchoolDto(school);
    }


    public School getSchool(SchoolDto schoolDto) {
        if(schoolDto != null) {
            Address address = Address.builder()
                    .street(schoolDto.getStreet())
                    .buildingNumber(schoolDto.getBuildingNumber())
                    .apartmentNumber(schoolDto.getApartmentNumber())
                    .city(schoolDto.getCity())
                    .zipCode(schoolDto.getZipCode())
                    .voivodeship(schoolDto.getVoivodeship())
                    .country(schoolDto.getCountry())
                    .build();
            School school = School.builder()
                    .id(schoolDto.getId())
                    .name(schoolDto.getName())
                    .patron(schoolDto.getPatron())
                    .dateOfAddition(new Date())
                    .phoneNumber(schoolDto.getPhoneNumber())
                    .email(schoolDto.getEmail())
                    .address(address)
                    .typeSchool(schoolDto.getTypeSchool())
                    .kindSchool(schoolDto.getKindSchool())
                    .build();
            return school;
        }else {
            return null;
        }
    }
    public SchoolDto getSchoolDto(School school) {
        if (school != null) {
            SchoolDto schoolDto = SchoolDto.builder()
                    .id(school.getId())
                    .name(school.getName())
                    .patron(school.getPatron())
                    .dateOfAddition(school.getDateOfAddition())
                    .phoneNumber(school.getPhoneNumber())
                    .email(school.getEmail())
                    .street(school.getAddress().getStreet())
                    .buildingNumber(school.getAddress().getBuildingNumber())
                    .apartmentNumber(school.getAddress().getApartmentNumber())
                    .city(school.getAddress().getCity())
                    .zipCode(school.getAddress().getZipCode())
                    .voivodeship(school.getAddress().getVoivodeship())
                    .country(school.getAddress().getCountry())
                    .typeSchool(school.getTypeSchool())
                    .kindSchool(school.getKindSchool())
                    .build();
            return schoolDto;
        }else {
            return null;
        }
    }
}
