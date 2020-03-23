package lewandowski.electronic_gradebook.controller;

import lewandowski.electronic_gradebook.dto.EmployeeDto;
import lewandowski.electronic_gradebook.dto.ParentDto;
import lewandowski.electronic_gradebook.dto.PupilDto;
import lewandowski.electronic_gradebook.payload.ApiResponse;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.repository.ParentRepository;
import lewandowski.electronic_gradebook.repository.PupilRepository;
import lewandowski.electronic_gradebook.services.EmployeeService;
import lewandowski.electronic_gradebook.services.ParentService;
import lewandowski.electronic_gradebook.services.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/school-administrator")
public class SchoolAdministratorController {

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


    @PostMapping("/register/pupil")
    public ResponseEntity<?> registerPupil(@Valid @RequestBody PupilDto pupilDto) {
        if (pupilRepository.existsByUsername(pupilDto.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (pupilRepository.existsByEmail(pupilDto.getEmail())) {
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
    public ResponseEntity<?> registerParent(@Valid @RequestBody ParentDto parentDto) {
        if (parentRepository.existsByUsername(parentDto.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (parentRepository.existsByEmail(parentDto.getEmail())) {
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
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        if (employeeRepository.existsByUsername(employeeDto.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
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