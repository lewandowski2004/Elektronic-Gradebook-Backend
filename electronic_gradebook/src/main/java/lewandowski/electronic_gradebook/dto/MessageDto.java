package lewandowski.electronic_gradebook.dto;

import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.model.Parent;
import lewandowski.electronic_gradebook.model.Pupil;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MessageDto {

    private Long id;

    private String title;

    private String content;

    private Date dateOfAddition;

    private String senderEmail;

    private Set<EmployeeDto> employeesDto;

    private Set<PupilDto> pupilsDto;

    private Set<ParentDto> parentsDto;
}
