package lewandowski.electronic_gradebook.dto._toSave;

import lewandowski.electronic_gradebook.dto.EmployeeDto;
import lewandowski.electronic_gradebook.dto.ParentDto;
import lewandowski.electronic_gradebook.dto.PupilDto;
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
public class SchoolDtoToSave {

    @NotEmpty(message = "Pole nie może być puste")
    @Size(max = 50)
    private String name;

    @Size(max = 225)
    private String patron;

    @Temporal(value=TemporalType.DATE)
    private Date dateOfAddition;

    //@NotEmpty(message = "Pole nie może być puste")
    private String phoneNumber;

    //@NotEmpty(message = "Pole nie może być puste")
    private String email;

    @Size(max = 100)
    //@NotEmpty(message = "Pole nie może być puste")
    private String street;

    //@NotNull(message = "Pole nie może być puste")
    private int buildingNumber;

    private int apartmentNumber;

    //@NotEmpty(message = "Pole nie może być puste")
    @Size(max = 100)
    private String city;

    //@NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "Podaj poprawny kod pocztowy!")
    private String zipCode;

    //@NotNull(message = "Pole nie może być puste")
    private Voivodeship voivodeship;

    //@NotNull(message = "Pole nie może być puste")
    private Country country;

    //@NotNull(message = "Pole nie może być puste")
    private TypeSchool typeSchool;

    //@NotNull(message = "Pole nie może być puste")
    private KindSchool kindSchool;
}
