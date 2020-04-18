package lewandowski.electronic_gradebook.dto;

import lewandowski.electronic_gradebook.model.Pupil;
import lewandowski.electronic_gradebook.model.School;
import lewandowski.electronic_gradebook.model.enums.ClassProfile;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClassDto {

    private int id;

    private int classNumber;

    private char classSymbol;

    private ClassProfile classProfile;

    private SchoolDto schoolDto;

    private Set<PupilDto> pupilsDto;
}
