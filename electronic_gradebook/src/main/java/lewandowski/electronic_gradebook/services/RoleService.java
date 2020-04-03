package lewandowski.electronic_gradebook.services;


import lewandowski.electronic_gradebook.dto.RoleDto;
import lewandowski.electronic_gradebook.model.Role;
import lewandowski.electronic_gradebook.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /*Dto method*/
    public List<Long> getRolesIdDto(List<Role> roles) {
        List<Long> listId = new ArrayList<>();
        for (Role role : roles) {
            listId.add(role.getId());
        }
        return listId;
    }

    public Set<Role> findByIdIn(Set<Long> listId) {
        return roleRepository.findByIdIn(listId);
    }

    public Role getRole(RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.getId())
                .name(roleDto.getName())
                .build();
    }
    public RoleDto getRoleDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public Set<Role> getRoles(Set<RoleDto> roleDtos) {
        Set<Role> roles = new HashSet<>();
        for (RoleDto roleDto : roleDtos) {
            roles.add(getRole(roleDto));
        }
        return roles;
    }

    public Set<RoleDto> getRolesDto(Set<Role> roleList) {
        Set<RoleDto> roles = new HashSet<>();
        for (Role role : roleList) {
            roles.add(getRoleDto(role));
        }
        return roles;
    }
}
