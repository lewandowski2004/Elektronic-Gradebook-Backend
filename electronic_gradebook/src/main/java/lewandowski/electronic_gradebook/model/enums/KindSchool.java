package lewandowski.electronic_gradebook.model.enums;

import lombok.Getter;

@Getter
public enum KindSchool {

    PUBLIC_SCHOOL ("Szkołą publiczna"),
    NOT_PUBLIC_SCHOOL ("Szkoła niepubliczna");

    private String kind;

    KindSchool(String kind) {
        this.kind = kind;
    }
}
