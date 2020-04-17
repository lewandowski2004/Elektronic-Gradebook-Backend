package lewandowski.electronic_gradebook.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

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


    /*@ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;*/

/*    @OneToMany(mappedBy="pupil", fetch=FetchType.EAGER)
    private Set<SubjectCard> subjectCards;*/

}
