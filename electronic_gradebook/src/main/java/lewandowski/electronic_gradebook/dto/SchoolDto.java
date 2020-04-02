package lewandowski.electronic_gradebook.dto;

import lewandowski.electronic_gradebook.model.enums.Country;
import lewandowski.electronic_gradebook.model.enums.TypeSchool;
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

    @NotEmpty(message = "Pole nie może być puste")
    @Size(max = 50)
    private String name;

    @Temporal(value=TemporalType.DATE)
    private Date dateOfAddition;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Size(max = 100)
    @Column(name = "street")
    private String street;

    @NotNull(message = "Pole nie może być puste")
    @Column(name = "building_number")
    private int buildingNumber;

    @Column(name = "apartment_number")
    private int apartmentNumber;

    @NotEmpty(message = "Pole nie może być puste")
    @Size(max = 100)
    @Column(name = "city")
    private String city;

    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "Podaj poprawny kod pocztowy!")
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull(message = "Pole nie może być puste")
    private Country country;

    @Enumerated(EnumType.STRING)
    private TypeSchool typeSchool;

    private Set<EmployeeDto> employeesDto;

    private Set<PupilDto> pupilsDto;

    private Set<ParentDto> parentsDto;
}
