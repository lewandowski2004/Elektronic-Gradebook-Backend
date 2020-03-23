package lewandowski.electronic_gradebook.repository;


import lewandowski.electronic_gradebook.model.Role;
import lewandowski.electronic_gradebook.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);

    List<Role> findAll();
    Set<Role> findByIdIn(Set<Long> id);
}
