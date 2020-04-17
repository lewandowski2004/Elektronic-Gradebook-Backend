package lewandowski.electronic_gradebook.dto._toSave;

import lewandowski.electronic_gradebook.dto.EmployeeDto;
import lewandowski.electronic_gradebook.dto.ParentDto;
import lewandowski.electronic_gradebook.dto.PupilDto;
import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.model.Parent;
import lewandowski.electronic_gradebook.model.Pupil;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MessageDtoToSave {

    @NotEmpty
    @Size(max = 255)
    private String title;

    @NotEmpty
    private String content;

    private Date dateOfAddition;

    private String senderEmail;

    private Set<UUID> employeeIds;

    private Set<UUID> parentIds;

    private Set<UUID> pupilIds;
}
