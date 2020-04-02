package lewandowski.electronic_gradebook.services;

import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.repository.ParentRepository;
import lewandowski.electronic_gradebook.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PupilRepository pupilRepository;

    @Autowired
    ParentRepository parentRepository;

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
}
