package lewandowski.electronic_gradebook.dto;

import lewandowski.electronic_gradebook.model.enums.Country;
import lewandowski.electronic_gradebook.model.enums.Gender;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class UserDto {

    private UUID id;

    @NotEmpty(message = "Pole nie może być puste")
    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String secondName;

    @NotEmpty(message = "Pole nie może być puste")
    @Size(max = 50)
    private String lastName;

    @NotEmpty(message = "Pole nie może być puste")
    @PESEL(message = "Nieprawidłowy numer PESEL !")
    private String pesel;

    @NotNull(message = "Pole nie może być puste")
    @Temporal(value= TemporalType.DATE)
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date dateOfBirth;

    @Temporal(value=TemporalType.DATE)
    private Date dateOfAddition;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotEmpty(message = "Pole nie może być puste")
    @Size(max = 50)
    private String username;

    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message = "Podaj poprawny email!")
    private String email;

    @NotNull(message = "Pole nie może być puste")
    private Gender gender;

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

    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\*])(?!.*\\s).{8,12}$",
            message = "Hasło powinno zawierać dużą i małą literę, cyfrę oraz jeden ze znaków !, @, #, $.")
    private String password;

    private int active;


    public UserDto(UUID id, String name, String secondName, String lastName, String pesel,
                   Date dateOfBirth, Date dateOfAddition, String phoneNumber, String username,
                   String email, Gender gender, String street, int buildingNumber, int apartmentNumber,
                   String city, String zipCode, Country country, String password, int active) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
        this.dateOfAddition = dateOfAddition;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.password = password;
        this.active = active;
    }

    public UserDto(String name, String lastName, String username, String email, Gender gender,
                   String street, int buildingNumber, int apartmentNumber, String city,
                   String zipCode, Country country) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }


}
