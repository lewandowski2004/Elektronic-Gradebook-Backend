package lewandowski.electronic_gradebook.repository;

import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.model.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Repository
public interface PupilRepository extends JpaRepository<Pupil, Integer> {
    Optional<Pupil> findByEmail(String email);

    Optional<Pupil> findById(UUID id);

    Optional<Pupil> findByUsernameOrEmail(String username, String email);

    Set<Pupil> findByIdIn(Set<UUID> userIds);

    Set<Pupil> findByEmailIn(Set<String> emails);

    Optional<Pupil> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
