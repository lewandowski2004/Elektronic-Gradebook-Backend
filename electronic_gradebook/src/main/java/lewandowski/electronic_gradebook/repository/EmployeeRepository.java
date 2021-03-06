package lewandowski.electronic_gradebook.repository;


import lewandowski.electronic_gradebook.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findById(UUID id);

    Optional<Employee> findByUsernameOrEmail(String username, String email);

    Set<Employee> findByIdIn(Set<UUID> id);

    Set<Employee> findByEmailIn(Set<String> emails);

    Optional<Employee> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
