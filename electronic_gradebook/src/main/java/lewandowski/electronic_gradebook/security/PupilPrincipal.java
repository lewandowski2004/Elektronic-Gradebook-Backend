package lewandowski.electronic_gradebook.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lewandowski.electronicgradebook.model.Employee;
import com.lewandowski.electronicgradebook.model.Parent;
import com.lewandowski.electronicgradebook.model.Pupil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class PupilPrincipal implements UserDetails {
    private UUID id;

    private String name;

    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public PupilPrincipal(UUID id, String name, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static PupilPrincipal createEmployee(Employee employee) {
        List<GrantedAuthority> authorities = employee.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new PupilPrincipal(
                employee.getId(),
                employee.getName(),
                employee.getUsername(),
                employee.getEmail(),
                employee.getPassword(),
                authorities
        );
    }
    public static PupilPrincipal createPupil(Pupil pupil) {
        List<GrantedAuthority> authorities = Collections.singletonList
                (new SimpleGrantedAuthority(pupil.getRole().getName().name()));


        return new PupilPrincipal(
                pupil.getId(),
                pupil.getName(),
                pupil.getUsername(),
                pupil.getEmail(),
                pupil.getPassword(),
                authorities
        );
    }
    public static PupilPrincipal createParent(Parent parent) {
        List<GrantedAuthority> authorities = Collections.singletonList
                (new SimpleGrantedAuthority(parent.getRole().getName().name()));

        return new PupilPrincipal(
                parent.getId(),
                parent.getName(),
                parent.getUsername(),
                parent.getEmail(),
                parent.getPassword(),
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PupilPrincipal that = (PupilPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
