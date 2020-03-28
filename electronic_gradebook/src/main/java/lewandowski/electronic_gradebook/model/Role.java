package lewandowski.electronic_gradebook.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    @OneToOne(mappedBy = "role",
            fetch = FetchType.LAZY)
    private Pupil pupil;

    @OneToOne(mappedBy = "role",
            fetch = FetchType.LAZY)
    private Parent parent;

}
