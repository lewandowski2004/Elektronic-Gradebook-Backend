package lewandowski.electronic_gradebook.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "school")
public class School {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;

    @OneToMany(mappedBy="school", fetch=FetchType.EAGER)
    private Set<Employee> employees;

    @OneToMany(mappedBy="school", fetch=FetchType.EAGER)
    private Set<Pupil> pupils;

    @OneToMany(mappedBy="school", fetch=FetchType.EAGER)
    private Set<Parent> parents;
}
