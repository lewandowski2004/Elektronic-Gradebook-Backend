package lewandowski.electronic_gradebook.services;


import lewandowski.electronic_gradebook.dto.ParentDto;
import lewandowski.electronic_gradebook.dto._toSave.ParentDtoToSave;
import lewandowski.electronic_gradebook.model.Address;
import lewandowski.electronic_gradebook.model.Parent;
import lewandowski.electronic_gradebook.model.Role;
import lewandowski.electronic_gradebook.model.enums.RoleName;
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

    private final RoleService roleService;
    private final SchoolService schoolService;
    private final RoleRepository roleRepository;
    private final ParentRepository parentRepository;
    private final PasswordEncoder encoder;

    public ParentService(RoleService roleService, SchoolService schoolService,
                         RoleRepository roleRepository, ParentRepository parentRepository,
                         PasswordEncoder encoder) {
        this.roleService = roleService;
        this.schoolService = schoolService;
        this.roleRepository = roleRepository;
        this.parentRepository = parentRepository;
        this.encoder = encoder;
    }

    public void saveParentDto(ParentDtoToSave parentDto) {
        Set<Role> roles = new HashSet<>();
        Role pupilRole = roleRepository.findByName(RoleName.ROLE_PARENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(pupilRole);
        Address address = Address.builder()
                .street(parentDto.getStreet())
                .buildingNumber(parentDto.getBuildingNumber())
                .apartmentNumber(parentDto.getApartmentNumber())
                .city(parentDto.getCity())
                .zipCode(parentDto.getZipCode())
                .voivodeship(parentDto.getVoivodeship())
                .country(parentDto.getCountry())
                .build();
        Parent parent = Parent.builder()
                .name(parentDto.getName())
                .secondName(parentDto.getSecondName())
                .lastName(parentDto.getLastName())
                .pesel(parentDto.getPesel())
                .dateOfBirth(parentDto.getDateOfBirth())
                .dateOfAddition(new Date())
                .phoneNumber(parentDto.getPhoneNumber())
                .username(parentDto.getUsername())
                .active(parentDto.getActive())
                .email(parentDto.getEmail())
                .gender(parentDto.getGender())
                .address(address)
                .school(schoolService.getSchool(parentDto.getSchoolDto()))
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
        Address address = Address.builder()
                .street(parentDto.getStreet())
                .buildingNumber(parentDto.getBuildingNumber())
                .apartmentNumber(parentDto.getApartmentNumber())
                .city(parentDto.getCity())
                .zipCode(parentDto.getZipCode())
                .build();
        return Parent.builder()
                .id(parentDto.getId())
                .name(parentDto.getName())
                .lastName(parentDto.getLastName())
                .username(parentDto.getUsername())
                .email(parentDto.getEmail())
                .address(address)
                .password(parentDto.getPassword())
                .active(parentDto.getActive())
                .role(roleService.getRole(parentDto.getRoleDto()))
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
                .street(parent.getAddress().getStreet())
                .buildingNumber(parent.getAddress().getBuildingNumber())
                .apartmentNumber(parent.getAddress().getApartmentNumber())
                .city(parent.getAddress().getCity())
                .zipCode(parent.getAddress().getZipCode())
                .password(parent.getPassword())
                .roleDto(roleService.getRoleDto(parent.getRole()))
                .build();
    }
}
