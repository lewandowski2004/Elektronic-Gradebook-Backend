package lewandowski.electronic_gradebook.services;


import lewandowski.electronic_gradebook.dto.ParentDto;
import lewandowski.electronic_gradebook.dto.ParentDtoToSave;
import lewandowski.electronic_gradebook.model.Address;
import lewandowski.electronic_gradebook.model.Parent;
import lewandowski.electronic_gradebook.model.Role;
import lewandowski.electronic_gradebook.model.RoleName;
import lewandowski.electronic_gradebook.repository.ParentRepository;
import lewandowski.electronic_gradebook.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ParentService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    PasswordEncoder encoder;

    public void saveParentDto(ParentDtoToSave parentDto) {
        Set<Role> roles = new HashSet<>();
        Role pupilRole = roleRepository.findByName(RoleName.ROLE_PARENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(pupilRole);
        Address address = Address.builder()
                .addressLine1(parentDto.getAddressLine1())
                .addressLine2(parentDto.getAddressLine2())
                .city(parentDto.getCity())
                .zipCode(parentDto.getZipCode())
                .build();
        Parent parent = Parent.builder()
                .name(parentDto.getName())
                .lastName(parentDto.getLastName())
                .username(parentDto.getUsername())
                .active(parentDto.getActive())
                .email(parentDto.getEmail())
                .address(address)
                .password(encoder.encode(parentDto.getPassword()))
                .role(pupilRole)
                .build();

        parentRepository.save(parent);
    }

    public ParentDto findById(UUID id) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Error: Parent is not found."));
        return getParentDto(parent);
    }

    public List<ParentDto> findAllEmployeesDto() {
        return findAllParentsDtoList(parentRepository.findAll());
    }

    public Parent getParent(ParentDto parentDto) {
        return Parent.builder()
                .id(parentDto.getId())
                .name(parentDto.getName())
                .lastName(parentDto.getLastName())
                .email(parentDto.getEmail())
                .password(parentDto.getPassword())
                .active(parentDto.getActive())
                .role(parentDto.getRole())
                .build();

    }


    public List<ParentDto> findAllParentsDtoList(List<Parent> parentList) {
        List<ParentDto> parentDtoList = new ArrayList<>();
        for (Parent parent : parentList) {
            ParentDto parentDto = getParentDto(parent);
            parentDtoList.add(parentDto);
        }
        return parentDtoList;
    }

    public ParentDto getParentDto(Parent parent) {
        return ParentDto.builder()
                .id(parent.getId())
                .name(parent.getName())
                .lastName(parent.getLastName())
                .username(parent.getUsername())
                .email(parent.getEmail())
                .password(parent.getPassword())
                .role(parent.getRole())
                .build();
    }
}
