package lewandowski.electronic_gradebook.dto;

import lewandowski.electronic_gradebook.model.enums.Country;
import lewandowski.electronic_gradebook.model.enums.KindSchool;
import lewandowski.electronic_gradebook.model.enums.TypeSchool;
import lewandowski.electronic_gradebook.model.enums.Voivodeship;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class SchoolDto {

    private UUID id;

    private String name;

    private String patron;

    private Date dateOfAddition;

    private String phoneNumber;

    private String email;

    private String street;

    private int buildingNumber;

    private int apartmentNumber;

    private String city;

    private String zipCode;

    private Voivodeship voivodeship;

    private Country country;

    private TypeSchool typeSchool;

    private KindSchool kindSchool;

    private Set<EmployeeDto> employeesDto;

    private Set<PupilDto> pupilsDto;

    private Set<ParentDto> parentsDto;
}
