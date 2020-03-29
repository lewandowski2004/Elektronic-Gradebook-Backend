package lewandowski.electronic_gradebook.dto._toPresent;

import lewandowski.electronic_gradebook.dto.UserDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class UserDtoToPresent extends UserDto {

    public UserDtoToPresent(String name, String lastName, String username, String email, String addressLine1,
                            String addressLine2, String city, String zipCode) {
        super(name, lastName, username, email, addressLine1, addressLine2, city, zipCode);
    }
}
