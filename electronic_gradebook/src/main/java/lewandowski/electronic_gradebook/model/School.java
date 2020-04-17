package lewandowski.electronic_gradebook.model;

import lewandowski.electronic_gradebook.model.enums.KindSchool;
import lewandowski.electronic_gradebook.model.enums.TypeSchool;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

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

    @Column(name = "name")
    private String name;

    @Column(name = "patron")
    private String patron;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Embedded
    private Address address;

    @Temporal(value=TemporalType.DATE)
    private Date dateOfAddition;

    @Enumerated(EnumType.STRING)
    private TypeSchool typeSchool;

    @Enumerated(EnumType.STRING)
    private KindSchool kindSchool;

    @OneToMany(mappedBy="school", fetch=FetchType.EAGER)
    private Set<Employee> employees;

    @OneToMany(mappedBy="school", fetch=FetchType.EAGER)
    private Set<Pupil> pupils;

    @OneToMany(mappedBy="school", fetch=FetchType.EAGER)
    private Set<Parent> parents;
}
