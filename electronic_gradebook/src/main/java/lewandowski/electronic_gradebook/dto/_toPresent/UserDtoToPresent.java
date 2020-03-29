package lewandowski.electronic_gradebook.dto._toPresent;

import lewandowski.electronic_gradebook.dto.RoleDto;
import lewandowski.electronic_gradebook.dto.UserDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class UserDtoToPresent extends UserDto {

    private RoleDto roleDto;

    public UserDtoToPresent(String name, String lastName, String username, String email, String street,
                            int buildingNumber, int apartmentNumber, String city, String zipCode,
                            RoleDto roleDto) {
        super(name, lastName, username, email, street, buildingNumber, apartmentNumber, city, zipCode);
        this.roleDto = roleDto;
    }
}
