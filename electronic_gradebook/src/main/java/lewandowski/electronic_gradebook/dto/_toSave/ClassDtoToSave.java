package lewandowski.electronic_gradebook.dto._toSave;

import lewandowski.electronic_gradebook.dto.SchoolDto;
import lewandowski.electronic_gradebook.model.Pupil;
import lewandowski.electronic_gradebook.model.School;
import lewandowski.electronic_gradebook.model.enums.ClassProfile;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClassDtoToSave {

    private int classNumber;

    private char classSymbol;

    private ClassProfile classProfile;

    private SchoolDto schoolDto;

}
