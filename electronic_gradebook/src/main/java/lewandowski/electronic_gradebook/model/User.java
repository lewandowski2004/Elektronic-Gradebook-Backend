package lewandowski.electronic_gradebook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Embedded
    private Address address;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "active")
    private int active;

    @Transient
    private int nrRoli;

    @Transient
    private String newPassword;

    @Transient
    private String oldPassword;


    public User(){

    }

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
