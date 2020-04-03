package lewandowski.electronic_gradebook.controller;

import lewandowski.electronic_gradebook.dto._toSave.EmployeeDtoToSave;
import lewandowski.electronic_gradebook.payload.ApiResponse;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.repository.ParentRepository;
import lewandowski.electronic_gradebook.repository.PupilRepository;
import lewandowski.electronic_gradebook.repository.RoleRepository;
import lewandowski.electronic_gradebook.security.JwtTokenProvider;
import lewandowski.electronic_gradebook.services.EmployeeService;
import lewandowski.electronic_gradebook.services.ParentService;
import lewandowski.electronic_gradebook.services.PupilService;
import lewandowski.electronic_gradebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AuthenticationManager authenticationManager;
    private final PupilService pupilService;
    private final ParentService parentService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AdminController(AuthenticationManager authenticationManager, PupilService pupilService, ParentService parentService, EmployeeService employeeService, UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.pupilService = pupilService;
        this.parentService = parentService;
        this.employeeService = employeeService;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register/school-administrator")
    public ResponseEntity<?> registerPupil(@Valid @RequestBody EmployeeDtoToSave employeeDto) {
        if (userService.existsByUsername(employeeDto.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(employeeDto.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        employeeService.saveEmployeeDto(employeeDto);

        /*URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(employeeDto.getUsername()).toUri();*/

        return ResponseEntity.ok(new ApiResponse(true, "School Administrator registered successfully"));
    }
}