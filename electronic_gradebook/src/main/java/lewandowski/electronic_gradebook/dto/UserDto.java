package lewandowski.electronic_gradebook.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.validation.constraints.*;
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

    @NotEmpty(message = "Pole nie może być puste")
    @Size(max = 50)
    private String lastName;

    @NotEmpty(message = "Pole nie może być puste")
    @Size(max = 50)
    private String username;

    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message = "Podaj poprawny email!")
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

    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\*])(?!.*\\s).{8,12}$",
            message = "Hasło powinno zawierać dużą i małą literę, cyfrę oraz jeden ze znaków !, @, #, $.")
    private String password;

    //private Date dateOfAddition;

    private int active;


    public UserDto(UUID id, String name, String lastName, String username, String email,String street, int buildingNumber,
                   int apartmentNumber, String city, String zipCode, String password, int active) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.password = password;
        this.active = active;
    }

    public UserDto(String name, String lastName, String username, String email, String street, int buildingNumber,
                   int apartmentNumber, String city, String zipCode) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.zipCode = zipCode;
    }
}
