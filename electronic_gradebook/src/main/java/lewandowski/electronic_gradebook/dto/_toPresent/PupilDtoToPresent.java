package lewandowski.electronic_gradebook.dto._toPresent;

import lewandowski.electronic_gradebook.dto.RoleDto;
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
public class PupilDtoToPresent extends UserDto {

    private RoleDto roleDto;

    public PupilDtoToPresent(UUID id, String name, String lastName, String username, String email, String addressLine1,
                             String addressLine2, String city, String zipCode, String password, int active,
                             RoleDto roleDto) {
        super(id, name, lastName, username, email, addressLine1, addressLine2, city, zipCode, password, active);
        this.roleDto = roleDto;
    }
}
