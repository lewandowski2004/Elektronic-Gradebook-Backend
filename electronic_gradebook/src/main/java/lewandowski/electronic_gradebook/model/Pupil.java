package lewandowski.electronic_gradebook.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "pupils",
        uniqueConstraints = @UniqueConstraint
                (columnNames = {
                        "id",
                        "username",
                        "email"}
                 )
)
public class Pupil extends User {

    private String fatherName;

    private String motherName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name="school_id")
    private School school;

    @ManyToOne
    @JoinColumn(name="class_id")
    private Class schoolClass;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pupils_parents",
            joinColumns = @JoinColumn(name = "pupil_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private Set<Parent> parents;*/

/*    @OneToMany(mappedBy="pupil", fetch=FetchType.EAGER)
    private Set<SubjectCard> subjectCards;*/

}
