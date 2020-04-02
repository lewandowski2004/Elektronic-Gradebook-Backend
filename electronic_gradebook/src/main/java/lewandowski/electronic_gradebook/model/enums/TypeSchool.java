package lewandowski.electronic_gradebook.model.enums;

import lombok.Getter;

@Getter
public enum TypeSchool {
    PRIMARY_SCHOOL ("Szkoła podstawowa"),
    SECONDARY_SCHOOL ("Liceum ogólnokształcące"),
    TECHNICAL ("Technikum"),
    FIRST_DEGREE_TRADE_SCHOOL ("Szkoła branżowa I stopnia"),
    SECOND_DEGREE_TRADE_SCHOOL ("Szkoła branżowa II stopnia"),
    POST_SECONDARY_SCHOOL ("Szkoła policealna"),
    SPECIAL_SCHOOL ("Szkoła specjalna"),
    ART_SCHOOL ("Liceum plastyczne"),
    ART_SCHOOL_1ST_DEGREE_MUSIC_SCHOOL ("Szkoła muzyczna I stopnia"),
    ART_SCHOOL_2ST_DEGREE_MUSIC_SCHOOL ("Szkoła muzyczna II stopnia"),
    ART_SCHOOL_GENERAL_SCHOOL_OF_FINE_ARTS ("Ogólnokształcąca szkoła sztuk pięknych"),
    ART_SCHOOL_GENERAL_BALLET_SCHOOL ("Ogólnokształcąca szkoła baletowa"),
    ART_SCHOOL_DANCE_SCHOOL ("Szkoła sztuki tańca"),
    ART_SCHOOL_CIRCUS_ART_SCHOOL ("Szkoła sztuki cyrkowej");

    private String PolishSchoolName;

    TypeSchool(String polishSchoolName) {
        PolishSchoolName = polishSchoolName;
    }
}
