package lewandowski.electronic_gradebook.repository;

import lewandowski.electronic_gradebook.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface SchoolRepository extends JpaRepository<School, UUID> {

    Optional<School> findById(UUID id);
}
