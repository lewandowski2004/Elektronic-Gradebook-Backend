package lewandowski.electronic_gradebook.dto._toPresent;

import lewandowski.electronic_gradebook.dto.RoleDto;
import lewandowski.electronic_gradebook.dto.UserDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class EmployeeDtoToPresent extends UserDto {

    private Set<RoleDto> rolesDto;
}
