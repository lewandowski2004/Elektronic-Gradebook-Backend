package lewandowski.electronic_gradebook.controller;

import lewandowski.electronic_gradebook.Component.MessageComponent;
import lewandowski.electronic_gradebook.dto.EmployeeDto;
import lewandowski.electronic_gradebook.dto.ParentDto;
import lewandowski.electronic_gradebook.dto._toSave.EmployeeDtoToSave;
import lewandowski.electronic_gradebook.payload.ApiResponse;
import lewandowski.electronic_gradebook.services.EmployeeService;
import lewandowski.electronic_gradebook.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    public final EmployeeService employeeService;
    public final UserService userService;
    public final MessageComponent messageComponent;

    public AdminController(EmployeeService employeeService, UserService userService,
                           MessageComponent messageComponent) {
        this.employeeService = employeeService;
        this.userService = userService;
        this.messageComponent = messageComponent;
    }

    @PostMapping("/register/school-administrator")
    public ResponseEntity<?> registerPupil(@Valid @RequestBody EmployeeDtoToSave newSchoolAdministrator) {
        if (userService.existsByUsername(newSchoolAdministrator.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.USERNAME_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(newSchoolAdministrator.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.EMAIL_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }

        employeeService.saveEmployeeDto(newSchoolAdministrator);
        EmployeeDto employeeByEmail = employeeService.findByEmail(newSchoolAdministrator.getEmail());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/get/parents/{id}")
                .buildAndExpand(newSchoolAdministrator.getId()).toUri();
        return ResponseEntity
                .created(location)
                .body(employeeByEmail);
    }
}