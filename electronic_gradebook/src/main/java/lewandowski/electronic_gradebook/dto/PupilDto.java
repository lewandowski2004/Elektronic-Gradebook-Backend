package lewandowski.electronic_gradebook.dto;

import lewandowski.electronic_gradebook.model.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class PupilDto extends UserDto {

    @NotEmpty(message = "Pole nie może być puste")
    @Size(max = 50)
    private String fatherName;

    @NotEmpty(message = "Pole nie może być puste")
    @Size(max = 50)
    private String motherName;

    private RoleDto roleDto;
}
