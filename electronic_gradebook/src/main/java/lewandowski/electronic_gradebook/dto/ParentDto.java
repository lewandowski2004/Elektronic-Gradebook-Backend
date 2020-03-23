package lewandowski.electronic_gradebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class ParentDto extends UserDto {

    public ParentDto(String name, String username, String email, String password) {
        super(name, username, email, password);
    }
}
