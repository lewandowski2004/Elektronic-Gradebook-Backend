package lewandowski.electronic_gradebook.controller;

import lewandowski.electronic_gradebook.dto.EmployeeDto;
import lewandowski.electronic_gradebook.dto.EmployeeDtoToSave;
import lewandowski.electronic_gradebook.payload.ApiResponse;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.repository.ParentRepository;
import lewandowski.electronic_gradebook.repository.PupilRepository;
import lewandowski.electronic_gradebook.repository.RoleRepository;
import lewandowski.electronic_gradebook.security.JwtTokenProvider;
import lewandowski.electronic_gradebook.services.EmployeeService;
import lewandowski.electronic_gradebook.services.ParentService;
import lewandowski.electronic_gradebook.services.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PupilService pupilService;

    @Autowired
    ParentService parentService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PupilRepository pupilRepository;

    @Autowired
    ParentRepository parentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/register/school-administrator")
    public ResponseEntity<?> registerPupil(@Valid @RequestBody EmployeeDtoToSave employeeDto) {
        if (employeeRepository.existsByUsername(employeeDto.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        employeeService.saveEmployeeDto(employeeDto);

        /*URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(employeeDto.getUsername()).toUri();*/

        return ResponseEntity.ok(new ApiResponse(true, "Pupil registered successfully"));
    }
}