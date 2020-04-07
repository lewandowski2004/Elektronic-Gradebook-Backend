package lewandowski.electronic_gradebook.services;

import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.repository.ParentRepository;
import lewandowski.electronic_gradebook.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final EmployeeRepository employeeRepository;
    private final PupilRepository pupilRepository;
    private final ParentRepository parentRepository;

    public UserService(EmployeeRepository employeeRepository, PupilRepository pupilRepository, ParentRepository parentRepository) {
        this.employeeRepository = employeeRepository;
        this.pupilRepository = pupilRepository;
        this.parentRepository = parentRepository;
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
}
