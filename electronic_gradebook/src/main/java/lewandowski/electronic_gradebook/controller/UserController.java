package lewandowski.electronic_gradebook.controller;

import lewandowski.electronic_gradebook.dto.ParentDto;
import lewandowski.electronic_gradebook.dto._toPresent.UserDtoToPresent;
import lewandowski.electronic_gradebook.exception.ResourceNotFoundException;
import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.payload.UserProfile;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.security.CurrentUser;
import lewandowski.electronic_gradebook.security.UserPrincipal;
import lewandowski.electronic_gradebook.services.ParentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final EmployeeRepository employeeRepository;
    private final ParentService parentService;

    public UserController(EmployeeRepository employeeRepository, ParentService parentService) {
        this.employeeRepository = employeeRepository;
        this.parentService = parentService;
    }


    @GetMapping("/me")
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

}
