package lewandowski.electronic_gradebook.dto;

import lewandowski.electronic_gradebook.model.enums.RoleName;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Setter
@Getter
public class RoleDto {

    private Long id;

    private RoleName name;
}