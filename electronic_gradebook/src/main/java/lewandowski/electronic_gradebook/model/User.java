package lewandowski.electronic_gradebook.model;

import lewandowski.electronic_gradebook.model.enums.Gender;
import lewandowski.electronic_gradebook.model.enums.SchoolBody;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "date_of_addition")
    @Temporal(TemporalType.DATE)
    private Date dateOfAddition;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Embedded
    private Address address;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "school_body")
    private SchoolBody schoolBody;

    @Transient
    private String confirmPassword;

    @Column(name = "active")
    private int active;

    @Transient
    private String newPassword;

    @Transient
    private String oldPassword;
}
