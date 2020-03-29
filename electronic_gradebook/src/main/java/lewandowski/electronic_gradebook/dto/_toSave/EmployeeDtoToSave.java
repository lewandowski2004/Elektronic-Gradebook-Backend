package lewandowski.electronic_gradebook.dto._toSave;

import lewandowski.electronic_gradebook.dto.EmployeeDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class EmployeeDtoToSave extends EmployeeDto {

    private Set<Long> roles;
}
