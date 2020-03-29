package lewandowski.electronic_gradebook.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class EmployeeDto extends UserDto {

    private Set<RoleDto> rolesDto;

}
