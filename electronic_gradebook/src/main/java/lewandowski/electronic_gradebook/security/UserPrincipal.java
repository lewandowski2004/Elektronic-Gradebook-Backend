package lewandowski.electronic_gradebook.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lewandowski.electronic_gradebook.dto.UserDto;
import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.model.Parent;
import lewandowski.electronic_gradebook.model.Pupil;
import lewandowski.electronic_gradebook.model.enums.Country;
import lewandowski.electronic_gradebook.model.enums.Gender;
import lewandowski.electronic_gradebook.model.enums.Voivodeship;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserPrincipal extends UserDto implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(UUID id, String name, String secondName, String lastName, String pesel,
                         Date dateOfBirth, Date dateOfAddition, String phoneNumber, String username,
                         String email, Gender gender, String street, int buildingNumber,
                         int apartmentNumber, String city, String zipCode, Voivodeship voivodeship, Country country,
                         String password, int active, Collection<? extends GrantedAuthority> authorities) {
        super(id, name, secondName, lastName, pesel, dateOfBirth, dateOfAddition, phoneNumber, username,
                email, gender, street, buildingNumber, apartmentNumber, city, zipCode,voivodeship, country, password,
                active);
        this.authorities = authorities;
    }


    public static UserPrincipal createEmployee(Employee employee) {
        List<GrantedAuthority> authorities = employee.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                employee.getId(),
                employee.getName(),
                employee.getSecondName(),
                employee.getLastName(),
                employee.getPesel(),
                employee.getDateOfBirth(),
                employee.getDateOfAddition(),
                employee.getPhoneNumber(),
                employee.getUsername(),
                employee.getEmail(),
                employee.getGender(),
                employee.getAddress().getStreet(),
                employee.getAddress().getBuildingNumber(),
                employee.getAddress().getApartmentNumber(),
                employee.getAddress().getCity(),
                employee.getAddress().getZipCode(),
                employee.getAddress().getVoivodeship(),
                employee.getAddress().getCountry(),
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
                pupil.getSecondName(),
                pupil.getLastName(),
                pupil.getPesel(),
                pupil.getDateOfBirth(),
                pupil.getDateOfAddition(),
                pupil.getPhoneNumber(),
                pupil.getUsername(),
                pupil.getEmail(),
                pupil.getGender(),
                //pupil.getMotherName(),
                //pupil.getFatherName(),
                pupil.getAddress().getStreet(),
                pupil.getAddress().getBuildingNumber(),
                pupil.getAddress().getApartmentNumber(),
                pupil.getAddress().getCity(),
                pupil.getAddress().getZipCode(),
                pupil.getAddress().getVoivodeship(),
                pupil.getAddress().getCountry(),
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
                parent.getSecondName(),
                parent.getLastName(),
                parent.getPesel(),
                parent.getDateOfBirth(),
                parent.getDateOfAddition(),
                parent.getPhoneNumber(),
                parent.getUsername(),
                parent.getEmail(),
                parent.getGender(),
                parent.getAddress().getStreet(),
                parent.getAddress().getBuildingNumber(),
                parent.getAddress().getApartmentNumber(),
                parent.getAddress().getCity(),
                parent.getAddress().getZipCode(),
                parent.getAddress().getVoivodeship(),
                parent.getAddress().getCountry(),
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
