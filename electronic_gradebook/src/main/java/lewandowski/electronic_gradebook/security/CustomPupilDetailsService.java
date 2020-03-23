package lewandowski.electronic_gradebook.security;

import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.model.Parent;
import lewandowski.electronic_gradebook.model.Pupil;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.repository.ParentRepository;
import lewandowski.electronic_gradebook.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class CustomPupilDetailsService implements UserDetailsService {

    @Autowired
    PupilRepository pupilRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ParentRepository parentRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {

        Boolean pupilIsPresent = pupilRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).isPresent();
        Boolean employeeIsPresent = employeeRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).isPresent();
        Boolean parentIsPresent = parentRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).isPresent();

        if(pupilIsPresent){
            Pupil getPupil = pupilRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();
            return PupilPrincipal.createPupil(getPupil);
        }
        if(employeeIsPresent){
            Employee getEmployee = employeeRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();
            return PupilPrincipal.createEmployee(getEmployee);
        }
        if(parentIsPresent){
            Parent getParent = parentRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();
            return PupilPrincipal.createParent(getParent);
        }
        return null;
    }

    @Transactional
    public UserDetails loadUserById(UUID id) {

        Boolean pupilIsPresent = pupilRepository.findById(id).isPresent();
        Boolean employeeIsPresent = employeeRepository.findById(id).isPresent();
        Boolean parentIsPresent = parentRepository.findById(id).isPresent();

        if(pupilIsPresent){
            Pupil getPupil = pupilRepository.findById(id).get();
            return PupilPrincipal.createPupil(getPupil);
        }
        if(employeeIsPresent){
            Employee getEmployee = employeeRepository.findById(id).get();
            return PupilPrincipal.createEmployee(getEmployee);
        }
        if(parentIsPresent){
            Parent getParent = parentRepository.findById(id).get();
            return PupilPrincipal.createParent(getParent);
        }
        return null;
    }
}