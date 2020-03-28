package lewandowski.electronic_gradebook.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lewandowski.electronic_gradebook.dto.UserDto;
import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.model.Parent;
import lewandowski.electronic_gradebook.model.Pupil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserPrincipal extends UserDto implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(UUID id, String name, String lastName, String username, String email, String addressLine1, String addressLine2,
                         String city, String zipCode, String password, int active,
                         Collection<? extends GrantedAuthority> authorities) {
        super(id, name, lastName, username, email, addressLine1, addressLine2, city, zipCode, password, active);
        this.authorities = authorities;
    }

    public static UserPrincipal createEmployee(Employee employee) {
        List<GrantedAuthority> authorities = employee.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                employee.getId(),
                employee.getName(),
                employee.getLastName(),
                employee.getUsername(),
                employee.getEmail(),
                employee.getAddress().getAddressLine1(),
                employee.getAddress().getAddressLine2(),
                employee.getAddress().getCity(),
                employee.getAddress().getZipCode(),
                employee.getPassword(),
                employee.getActive(),
                authorities
        );
    }
    public static UserPrincipal createPupil(Pupil pupil) {
        List<GrantedAuthority> authorities = Collections.singletonList
                (new SimpleGrantedAuthority(pupil.getRole().getName().name()));

        return new UserPrincipal(
                pupil.getId(),
                pupil.getName(),
                pupil.getLastName(),
                pupil.getUsername(),
                pupil.getEmail(),
                pupil.getAddress().getAddressLine1(),
                pupil.getAddress().getAddressLine2(),
                pupil.getAddress().getCity(),
                pupil.getAddress().getZipCode(),
                pupil.getPassword(),
                pupil.getActive(),
                authorities
        );
    }

    public static UserPrincipal createParent(Parent parent) {
        List<GrantedAuthority> authorities = Collections.singletonList
                (new SimpleGrantedAuthority(parent.getRole().getName().name()));

        return new UserPrincipal(
                parent.getId(),
                parent.getName(),
                parent.getLastName(),
                parent.getUsername(),
                parent.getEmail(),
                parent.getAddress().getAddressLine1(),
                parent.getAddress().getAddressLine2(),
                parent.getAddress().getCity(),
                parent.getAddress().getZipCode(),
                parent.getPassword(),
                parent.getActive(),
                authorities
        );
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }*/
}
