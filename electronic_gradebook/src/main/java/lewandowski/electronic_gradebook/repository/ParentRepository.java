package lewandowski.electronic_gradebook.repository;

import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {
    Optional<Parent> findByEmail(String email);

    Optional<Parent> findById(UUID id);

    Optional<Parent> findByUsernameOrEmail(String username, String email);

    Set<Parent> findByIdIn(Set<UUID> userIds);

    Set<Parent> findByEmailIn(Set<String> emails);

    Optional<Parent> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
