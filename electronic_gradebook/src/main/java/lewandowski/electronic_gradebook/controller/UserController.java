package lewandowski.electronic_gradebook.controller;

import lewandowski.electronic_gradebook.dto.ParentDto;
import lewandowski.electronic_gradebook.dto.PupilDto;
import lewandowski.electronic_gradebook.dto.UserDto;
import lewandowski.electronic_gradebook.dto._toPresent.ParentDtoToPresent;
import lewandowski.electronic_gradebook.dto._toPresent.UserDtoToPresent;
import lewandowski.electronic_gradebook.exception.ResourceNotFoundException;
import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.payload.UserProfile;
import lewandowski.electronic_gradebook.payload.UserSummary;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.security.CurrentUser;
import lewandowski.electronic_gradebook.security.UserPrincipal;
import lewandowski.electronic_gradebook.services.ParentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ParentService parentService;


    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('PUPIL') or hasRole('PARENT')")
    public ParentDto getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        ParentDto userDto = parentService.findById(currentUser.getId());

        UserDtoToPresent userDtoToPresent = new UserDtoToPresent(
                userDto.getName(),
                userDto.getLastName(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getGender(),
                userDto.getStreet(),
                userDto.getBuildingNumber(),
                userDto.getApartmentNumber(),
                userDto.getCity(),
                userDto.getZipCode(),
                userDto.getCountry(),
                userDto.getRoleDto());

        return userDto;
    }

    @GetMapping("/pupil")
    @PreAuthorize("hasRole('PUPIL')")
    public String userAccess() {
        return "Pupil Content.";
    }

    @GetMapping("/teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public String moderatorAccess() {
        return "teacher Board.";
    }

    @GetMapping("/parent")
    @PreAuthorize("hasRole('PARENT')")
    public String adminAccess() {
        return "Parent Board.";
    }

    @GetMapping("/school-administrator")
    @PreAuthorize("hasRole('SCHOOL_ADMINISTRATOR')")
    public String schoolAdministratorAccess() {
        return "School-Administrator Board.";
    }


    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        Employee user = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName());

        return userProfile;
    }

}
