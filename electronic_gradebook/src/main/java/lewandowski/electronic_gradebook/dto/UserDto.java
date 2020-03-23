package lewandowski.electronic_gradebook.dto;

import lewandowski.electronic_gradebook.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class UserDto {

    private UUID id;

    private String name;

    private String lastName;

    private String username;

    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message = "Podaj poprawny email!")
    private String email;

    @NotEmpty(message = "Pole nie może być puste")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\*])(?!.*\\s).{8,12}$",
            message = "Hasło powinno zawierać dużą i małą literę, cyfrę oraz jeden ze znaków !, @, #, $.")
    private String password;

    /*@NotEmpty(message = "Pole nie może być puste")*/
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\*])(?!.*\\s).{8,12}$",
            message = "Hasło powinno zawierać dużą i małą literę, cyfrę oraz jeden ze znaków !, @, #, $.")
    private String confirmPassword;

    private Date dateOfAddition;

    private int active;

    private Set<Long> roles;

    private Set<RoleDto> roleDtos;

    private Role role;

    private int nrRoli;

    private String newPassword;

    private String oldPassword;

    public UserDto(){}

    public UserDto(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
