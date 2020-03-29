package lewandowski.electronic_gradebook.dto._toSave;

import lewandowski.electronic_gradebook.dto.ParentDto;
import lewandowski.electronic_gradebook.model.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class ParentDtoToSave extends ParentDto {

    private Role role;
}
