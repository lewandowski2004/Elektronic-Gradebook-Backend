package lewandowski.electronic_gradebook.controller;

import lewandowski.electronic_gradebook.dto.MessageDto;
import lewandowski.electronic_gradebook.dto.UserDto;
import lewandowski.electronic_gradebook.dto._toPresent.UserDtoToPresent;
import lewandowski.electronic_gradebook.dto._toSave.MessageDtoToSave;
import lewandowski.electronic_gradebook.dto._toSave.SchoolDtoToSave;
import lewandowski.electronic_gradebook.model.Message;
import lewandowski.electronic_gradebook.payload.ApiResponse;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.security.CurrentUser;
import lewandowski.electronic_gradebook.security.UserPrincipal;
import lewandowski.electronic_gradebook.services.MessageService;
import lewandowski.electronic_gradebook.services.ParentService;
import lewandowski.electronic_gradebook.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final EmployeeRepository employeeRepository;
    private final ParentService parentService;
    private final UserService userService;
    private final MessageService messageService;

    public UserController(EmployeeRepository employeeRepository, ParentService parentService,
                          UserService userService, MessageService messageService) {
        this.employeeRepository = employeeRepository;
        this.parentService = parentService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping(path="/add/message")
    public ResponseEntity<?> addMessage(@Valid @RequestBody MessageDtoToSave newMessage,
                                        @CurrentUser UserPrincipal currentUser) {

        newMessage.setSenderEmail(currentUser.getEmail());
        messageService.saveMessageDto(newMessage);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/messages/sent")
                .buildAndExpand().toUri();

        return ResponseEntity
                .created(location)
                .body(newMessage);
    }


    @GetMapping(path="/messages/sent")
    public ResponseEntity<?> getMyMessagesSent(@CurrentUser UserPrincipal currentUser) {

        List<MessageDto> messages = messageService.getMessagesDtoBySenderEmail(currentUser.getEmail());

        return ResponseEntity.ok(messages);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserDto userDto = userService.findUserById(currentUser.getId());

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
                userDto.getCountry());

        return ResponseEntity.ok(userDtoToPresent);
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
