package lewandowski.electronic_gradebook.controller;

import lewandowski.electronic_gradebook.dto._toSave.EmployeeDtoToSave;
import lewandowski.electronic_gradebook.payload.ApiResponse;
import lewandowski.electronic_gradebook.services.EmployeeService;
import lewandowski.electronic_gradebook.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    public final EmployeeService employeeService;
    public final UserService userService;

    public AdminController(EmployeeService employeeService, UserService userService) {
        this.employeeService = employeeService;
        this.userService = userService;
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