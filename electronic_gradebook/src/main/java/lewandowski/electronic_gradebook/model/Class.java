package lewandowski.electronic_gradebook.model;

import lewandowski.electronic_gradebook.model.enums.ClassProfile;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "class_number")
    private int classNumber;

    @Column(name = "class_symbol")
    private char classSymbol;

    @Enumerated(EnumType.STRING)
    @Column(name = "class_profile")
    private ClassProfile classProfile;

    @ManyToOne
    @JoinColumn(name="school_id")
    private School school;

    @OneToMany(mappedBy="schoolClass", fetch=FetchType.EAGER)
    private Set<Pupil> pupils;
}
