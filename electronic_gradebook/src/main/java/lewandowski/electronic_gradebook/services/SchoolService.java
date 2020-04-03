package lewandowski.electronic_gradebook.services;


import lewandowski.electronic_gradebook.dto._toSave.SchoolDtoToSave;
import lewandowski.electronic_gradebook.model.Address;
import lewandowski.electronic_gradebook.model.School;
import lewandowski.electronic_gradebook.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

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
}
