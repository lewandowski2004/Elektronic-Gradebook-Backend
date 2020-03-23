package lewandowski.electronic_gradebook.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Employee extends User{

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name="school_id")
    private School school;

    public Employee() {

    }

    public Employee(String name, String username, String email, String password) {
        super(name, username, email, password);
    }

    /*public Employee(UUID id, String name, String lastName, String username, String email, String password,
                   String confirmPassword, int active, int nrRoli, String newPassword, String oldPassword) {
        super(id, name, lastName, username, email, password, confirmPassword, active, nrRoli, newPassword, oldPassword);
    }

    public Employee(UUID id, String name, String lastName, String username, String email, String password, String confirmPassword,
                   int active, int nrRoli, String newPassword, String oldPassword, Set<Role> roles) {
        super(id, name, lastName, username, email, password, confirmPassword, active, nrRoli, newPassword, oldPassword);
        this.roles = roles;
    }*/
}
