package lewandowski.electronic_gradebook.controller;

import lewandowski.electronic_gradebook.Component.MessageComponent;
import lewandowski.electronic_gradebook.dto.EmployeeDto;
import lewandowski.electronic_gradebook.dto.ParentDto;
import lewandowski.electronic_gradebook.dto.PupilDto;
import lewandowski.electronic_gradebook.dto._toPresent.EmployeeDtoToPresent;
import lewandowski.electronic_gradebook.dto._toPresent.ParentDtoToPresent;
import lewandowski.electronic_gradebook.dto._toPresent.PupilDtoToPresent;
import lewandowski.electronic_gradebook.dto._toSave.EmployeeDtoToSave;
import lewandowski.electronic_gradebook.dto._toSave.ParentDtoToSave;
import lewandowski.electronic_gradebook.dto._toSave.PupilDtoToSave;
import lewandowski.electronic_gradebook.dto._toSave.SchoolDtoToSave;
import lewandowski.electronic_gradebook.model.School;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/school-administrator")
public class SchoolAdministratorController {


    private final PupilService pupilService;
    private final ParentService parentService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final SchoolService schoolService;
    private final MessageComponent messageComponent;

    public SchoolAdministratorController(PupilService pupilService, ParentService parentService,
                                         EmployeeService employeeService, UserService userService,
                                         SchoolService schoolService, MessageComponent messageComponent) {
        this.pupilService = pupilService;
        this.parentService = parentService;
        this.employeeService = employeeService;
        this.userService = userService;
        this.schoolService = schoolService;
        this.messageComponent = messageComponent;
    }

    @PostMapping(path="/create/school")
    public ResponseEntity<?> createSchool(@Valid @RequestBody SchoolDtoToSave newSchool,
                                          @CurrentUser UserPrincipal currentUser) {

        if (userService.schoolAdministratorContainsSchool(currentUser.getId())) {
            return ResponseEntity.ok(new ApiResponse(false,
                    messageComponent.SCHOOL_ADDED));
        }
       schoolService.saveSchoolDtoAndUpdateSchoolInSchoolAdministrator(newSchool, currentUser.getId());
        /*URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(pupilDto.getUsername()).toUri();*/

        return ResponseEntity.ok(new ApiResponse(true, messageComponent.REGISTRATION_SUCCESS));
    }

    @PostMapping("/register/pupil")
    public ResponseEntity<?> registerPupil(@Valid @RequestBody PupilDtoToSave newPupil,
                                           @CurrentUser UserPrincipal currentUser) {
        if (userService.existsByUsername(newPupil.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.USERNAME_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(newPupil.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.EMAIL_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }

        EmployeeDto employeeDto = employeeService.findById(currentUser.getId());
        newPupil.setSchoolDto(employeeDto.getSchoolDto());
        pupilService.savePupilDto(newPupil);
        PupilDto pupilByEmail = pupilService.findByEmail(newPupil.getEmail());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/get/pupils/{id}")
                .buildAndExpand(pupilByEmail.getId()).toUri();
        return ResponseEntity
                .created(location)
                .body(pupilByEmail);
    }

    @GetMapping("/get/pupils/{id}")
    public ResponseEntity<?> getPupil(@RequestBody PupilDtoToPresent pupilToPresent,
                                       @PathVariable(value = "id") UUID id) {
        PupilDto toPresent = pupilService.findById(id);
        return ResponseEntity.ok(toPresent);
    }

    @PutMapping("/update/pupils/{id}")
    public ResponseEntity<?> updatePupils(@Valid @RequestBody PupilDtoToSave updatePupil,
                                          @PathVariable(value = "id") UUID id) {

        if (userService.existsByUsername(updatePupil.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.USERNAME_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(updatePupil.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.EMAIL_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }

        pupilService.savePupilDto(updatePupil);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register/parents")
    public ResponseEntity<?> registerParent(@Valid @RequestBody ParentDtoToSave newParent,
                                            @CurrentUser UserPrincipal currentUser) {
        if (userService.existsByUsername(newParent.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.USERNAME_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(newParent.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.EMAIL_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }

        EmployeeDto employeeDto = employeeService.findById(currentUser.getId());
        newParent.setSchoolDto(employeeDto.getSchoolDto());
        parentService.saveParentDto(newParent);
        ParentDto parentByEmail = parentService.findByEmail(newParent.getEmail());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/get/parents/{id}")
                .buildAndExpand(parentByEmail.getId()).toUri();
        return ResponseEntity
                .created(location)
                .body(parentByEmail);
    }

    @GetMapping("/get/parents/{id}")
    public ResponseEntity<?> getParent(@RequestBody ParentDtoToPresent parentToPresent,
                                       @PathVariable(value = "id") UUID id) {
        ParentDto toPresent = parentService.findById(id);
        return ResponseEntity.ok(toPresent);
    }

    @PutMapping("/update/parents/{id}")
    public ResponseEntity<?> updateParent(@Valid @RequestBody ParentDtoToSave updateParent,
                                          @PathVariable(value = "id") UUID id) {

        if (userService.existsByUsername(updateParent.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.USERNAME_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(updateParent.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.EMAIL_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }

        parentService.saveParentDto(updateParent);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register/employee")
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody EmployeeDtoToSave newEmployee,
                                              @CurrentUser UserPrincipal currentUser) {
        if (userService.existsByUsername(newEmployee.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.USERNAME_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(newEmployee.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.EMAIL_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }

        EmployeeDto employeeDto = employeeService.findById(currentUser.getId());
        newEmployee.setSchoolDto(employeeDto.getSchoolDto());
        employeeService.saveEmployeeDto(newEmployee);
        EmployeeDto employeeByEmail = employeeService.findByEmail(newEmployee.getEmail());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/get/parents/{id}")
                .buildAndExpand(employeeByEmail.getId()).toUri();
        return ResponseEntity
                .created(location)
                .body(employeeByEmail);
    }

    @GetMapping("/get/employees/{id}")
    public ResponseEntity<?> getEmployee(@RequestBody EmployeeDtoToPresent employeeToPresent,
                                       @PathVariable(value = "id") UUID id) {
        EmployeeDto toPresent = employeeService.findById(id);
        return ResponseEntity.ok(toPresent);
    }

    @PutMapping("/update/employees/{id}")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody ParentDtoToSave updateParent,
                                          @PathVariable(value = "id") UUID id) {

        if (userService.existsByUsername(updateParent.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.USERNAME_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(updateParent.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, messageComponent.EMAIL_TAKEN),
                    HttpStatus.BAD_REQUEST);
        }

        parentService.saveParentDto(updateParent);
        return ResponseEntity.noContent().build();
    }
}