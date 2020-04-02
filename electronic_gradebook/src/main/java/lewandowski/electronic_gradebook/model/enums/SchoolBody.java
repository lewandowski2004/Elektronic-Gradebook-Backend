package lewandowski.electronic_gradebook.model.enums;

import lombok.Getter;

@Getter
public enum SchoolBody {
    SCHOOL_DIRECTOR ("Dyrektor szkoły"),
    SCHOOL_BOARD ("Rada szkoły"),
    PEDAGOGICAL_COUNCIL ("Rada pedagogiczna"),
    COUNSEL_PARENTS ("Rada rodziców"),
    STUDENT_COUNCIL ("Samorząd uczniowski");

    private String polishNameSchoolBody;

    SchoolBody(String polishNameSchoolBody) {
        this.polishNameSchoolBody = polishNameSchoolBody;
    }
}
