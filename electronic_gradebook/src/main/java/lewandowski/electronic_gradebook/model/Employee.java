package lewandowski.electronic_gradebook.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "employees",
        uniqueConstraints=@UniqueConstraint
                (columnNames={
                        "id",
                        "username",
                        "email"}
                 )
        )
public class Employee extends User{

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name="school_id")
    private School school;

}
