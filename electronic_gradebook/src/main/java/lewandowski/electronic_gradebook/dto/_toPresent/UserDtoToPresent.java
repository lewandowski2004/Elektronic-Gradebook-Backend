package lewandowski.electronic_gradebook.dto._toPresent;

import lewandowski.electronic_gradebook.dto.RoleDto;
import lewandowski.electronic_gradebook.dto.UserDto;
import lewandowski.electronic_gradebook.model.enums.Country;
import lewandowski.electronic_gradebook.model.enums.Gender;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class UserDtoToPresent extends UserDto {


    public UserDtoToPresent(String name, String lastName, String username, String email, Gender gender, String street,
                            int buildingNumber, int apartmentNumber, String city, String zipCode, Country country) {
        super(name, lastName, username, email, gender, street, buildingNumber, apartmentNumber, city, zipCode, country);
    }
}
