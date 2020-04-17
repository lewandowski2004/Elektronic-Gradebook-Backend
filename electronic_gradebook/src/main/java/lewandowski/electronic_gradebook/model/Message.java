package lewandowski.electronic_gradebook.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "date_of_addition")
    @Temporal(TemporalType.DATE)
    private Date dateOfAddition;

    @Column(name = "sender_id")
    private String senderEmail;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "messages_employees",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employees;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "messages_pupils",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "pupil_id"))
    private Set<Pupil> pupils;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "messages_parents",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private Set<Parent> parents;
}
