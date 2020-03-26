package lewandowski.electronic_gradebook.dto;

import lewandowski.electronic_gradebook.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
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

    @NotEmpty(message = "Pole nie może być puste")
    @Size(max = 100)
    @Column(name = "address_line_1")
    private String addressLine1;

    @Size(max = 100)
    @Column(name = "address_line_2")
    private String addressLine2;

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

    private Date dateOfAddition;

    private int active;

    private Set<Long> roles;

    private Set<RoleDto> roleDtos;

    private Role role;

    private int nrRoli;

    public UserDto(){}

    public UserDto(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
