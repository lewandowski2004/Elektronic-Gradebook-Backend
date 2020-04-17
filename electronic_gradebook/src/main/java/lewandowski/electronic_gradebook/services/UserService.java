package lewandowski.electronic_gradebook.services;

import lewandowski.electronic_gradebook.dto.EmployeeDto;
import lewandowski.electronic_gradebook.dto.UserDto;
import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.model.Parent;
import lewandowski.electronic_gradebook.model.Pupil;
import lewandowski.electronic_gradebook.payload.ApiResponse;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.repository.ParentRepository;
import lewandowski.electronic_gradebook.repository.PupilRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final EmployeeRepository employeeRepository;
    private final PupilRepository pupilRepository;
    private final ParentRepository parentRepository;
    private final EmployeeService employeeService;
    private final PupilService pupilService;
    private final ParentService parentService;

    public UserService(EmployeeRepository employeeRepository, PupilRepository pupilRepository,
                       ParentRepository parentRepository, EmployeeService employeeService,
                       PupilService pupilService, ParentService parentService) {
        this.employeeRepository = employeeRepository;
        this.pupilRepository = pupilRepository;
        this.parentRepository = parentRepository;
        this.employeeService = employeeService;
        this.pupilService = pupilService;
        this.parentService = parentService;
    }

    public Boolean existsByUsername(String UsernameOrEmail){
        if (employeeRepository.existsByUsername(UsernameOrEmail) ||
                pupilRepository.existsByUsername(UsernameOrEmail) ||
                    parentRepository.existsByUsername(UsernameOrEmail)){
            return true;
        }else {
            return false;
        }
    }

    public Boolean existsByEmail(String UsernameOrEmail){
        if (employeeRepository.existsByEmail(UsernameOrEmail) ||
                pupilRepository.existsByEmail(UsernameOrEmail) ||
                    parentRepository.existsByEmail(UsernameOrEmail)){
            return true;
        }else {
            return false;
        }
    }

    public Boolean schoolAdministratorContainsSchool(UUID id){
        Employee employee = employeeRepository.findById(id).get();
        if(employee.getSchool() == null){
            return false;
        }else {
            return true;
        }
    }

    public UserDto findUserById(UUID id) {
        if(employeeRepository.findById(id).isPresent()){
            Employee employee = employeeRepository.findById(id).get();
            UserDto userDto = employeeService.getEmployeeDto(employee);
            return userDto;
        }
        if(parentRepository.findById(id).isPresent()){
            Parent parent = parentRepository.findById(id).get();
            UserDto userDto = parentService.getParentDto(parent);
            return userDto;
        }
        if(pupilRepository.findById(id).isPresent()){
            Pupil pupil = pupilRepository.findById(id).get();
            UserDto userDto = pupilService.getPupilDto(pupil);
            return userDto;
        }

        return null;
    }
}
