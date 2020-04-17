package lewandowski.electronic_gradebook.dto;

import lewandowski.electronic_gradebook.model.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class ParentDto extends UserDto {

    private RoleDto roleDto;

    private SchoolDto schoolDto;


}
