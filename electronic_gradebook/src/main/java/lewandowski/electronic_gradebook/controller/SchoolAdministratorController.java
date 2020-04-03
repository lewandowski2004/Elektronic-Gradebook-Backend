package lewandowski.electronic_gradebook.controller;

import lewandowski.electronic_gradebook.dto._toSave.EmployeeDtoToSave;
import lewandowski.electronic_gradebook.dto._toSave.ParentDtoToSave;
import lewandowski.electronic_gradebook.dto._toSave.PupilDtoToSave;
import lewandowski.electronic_gradebook.dto._toSave.SchoolDtoToSave;
import lewandowski.electronic_gradebook.payload.ApiResponse;
import lewandowski.electronic_gradebook.security.CurrentUser;
import lewandowski.electronic_gradebook.security.UserPrincipal;
import lewandowski.electronic_gradebook.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/school-administrator")
public class SchoolAdministratorController {

    private final PupilService pupilService;
    private final ParentService parentService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final SchoolService schoolService;

    public SchoolAdministratorController(PupilService pupilService, ParentService parentService, EmployeeService employeeService, UserService userService, SchoolService schoolService) {
        this.pupilService = pupilService;
        this.parentService = parentService;
        this.employeeService = employeeService;
        this.userService = userService;
        this.schoolService = schoolService;
    }

    @PostMapping("/create/school")
    public ResponseEntity<?> createSchool(@Valid @RequestBody SchoolDtoToSave schoolDto,
                                          @CurrentUser UserPrincipal currentUser) {

        schoolService.saveSchoolDto(schoolDto);

        /*URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(pupilDto.getUsername()).toUri();*/

        return ResponseEntity.ok(new ApiResponse(true, "Pupil registered successfully"));
    }

    @PostMapping("/register/pupil")
    public ResponseEntity<?> registerPupil(@Valid @RequestBody PupilDtoToSave pupilDto) {
        if (userService.existsByUsername(pupilDto.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(pupilDto.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        pupilService.savePupilDto(pupilDto);

        /*URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(pupilDto.getUsername()).toUri();*/

        return ResponseEntity.ok(new ApiResponse(true, "Pupil registered successfully"));
    }

    @PostMapping("/register/parent")
    public ResponseEntity<?> registerParent(@Valid @RequestBody ParentDtoToSave parentDto) {
        if (userService.existsByUsername(parentDto.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(parentDto.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        parentService.saveParentDto(parentDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(parentDto.getUsername()).toUri();

        return ResponseEntity.ok(new ApiResponse(true, "Parent registered successfully"));
    }

    @PostMapping("/register/employee")
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody EmployeeDtoToSave employeeDto) {
        if (userService.existsByUsername(employeeDto.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(employeeDto.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

       employeeService.saveEmployeeDto(employeeDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(employeeDto.getUsername()).toUri();

        return ResponseEntity.ok(new ApiResponse(true, "Employee registered successfully"));
    }
}