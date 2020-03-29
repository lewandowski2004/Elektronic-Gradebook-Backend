package lewandowski.electronic_gradebook.dto._toPresent;

import lewandowski.electronic_gradebook.dto.UserDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class ParentDtoToPresent extends UserDto {

    private List<String> roles;

    public ParentDtoToPresent(UUID id, String name, String lastName, String username, String email, String addressLine1,
                              String addressLine2, String city, String zipCode, String password, int active,
                              List<String> roles) {
        super(id, name, lastName, username, email, addressLine1, addressLine2, city, zipCode, password, active);
        this.roles = roles;
    }
}
