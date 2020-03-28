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
@Table(name = "pupils", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Pupil extends User {

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
