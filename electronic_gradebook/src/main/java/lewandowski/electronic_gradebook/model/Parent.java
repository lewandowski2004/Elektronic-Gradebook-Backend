package lewandowski.electronic_gradebook.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "parents",
        uniqueConstraints = @UniqueConstraint
                (columnNames = {
                        "id",
                        "username",
                        "email"}
                )
)
public class Parent extends User {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;


    /*@OneToMany(mappedBy = "parent", fetch= FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Pupil> pupils;*/
}
