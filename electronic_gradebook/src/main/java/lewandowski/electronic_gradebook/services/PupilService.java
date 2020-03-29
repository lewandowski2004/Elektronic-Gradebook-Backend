package lewandowski.electronic_gradebook.services;


import lewandowski.electronic_gradebook.dto.PupilDto;
import lewandowski.electronic_gradebook.dto._toSave.PupilDtoToSave;
import lewandowski.electronic_gradebook.model.Address;
import lewandowski.electronic_gradebook.model.Pupil;
import lewandowski.electronic_gradebook.model.Role;
import lewandowski.electronic_gradebook.model.RoleName;
import lewandowski.electronic_gradebook.repository.PupilRepository;
import lewandowski.electronic_gradebook.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class PupilService {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PupilRepository pupilRepository;

    @Autowired
    PasswordEncoder encoder;

    public void savePupilDto(PupilDtoToSave pupilDto) {
        Set<Role> roles = new HashSet<>();
        Role pupilRole = roleRepository.findByName(RoleName.ROLE_PUPIL)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(pupilRole);
        Address address = Address.builder()
                .addressLine1(pupilDto.getAddressLine1())
                .addressLine2(pupilDto.getAddressLine2())
                .city(pupilDto.getCity())
                .zipCode(pupilDto.getZipCode())
                .build();
        Pupil pupil = Pupil.builder()
                .name(pupilDto.getName())
                .lastName(pupilDto.getLastName())
                .username(pupilDto.getUsername())
                .active(pupilDto.getActive())
                .email(pupilDto.getEmail())
                .address(address)
                .password(encoder.encode(pupilDto.getPassword()))
                .role(pupilRole)
                .build();

        pupilRepository.save(pupil);
    }

    public PupilDto findById(UUID id) {
        Pupil pupil = pupilRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Error: Pupil is not found."));
        return getPupilDto(pupil);
    }

    public List<PupilDto> findAllPupilsDto() {
        return findAllPupilsDtoList(pupilRepository.findAll());
    }

    public Pupil getPupil(PupilDto pupilDto) {
        return Pupil.builder()
                .id(pupilDto.getId())
                .name(pupilDto.getName())
                .lastName(pupilDto.getLastName())
                .username(pupilDto.getUsername())
                .email(pupilDto.getEmail())
                .password(pupilDto.getPassword())
                .role(roleService.getRole(pupilDto.getRoleDto()))
                .build();
    }

    public List<PupilDto> findAllPupilsDtoList(List<Pupil> pupilList) {
        List<PupilDto> pupilDtoList = new ArrayList<>();
        for (Pupil pupil : pupilList) {
            PupilDto pupilDto = getPupilDto(pupil);
            pupilDtoList.add(pupilDto);
        }
        return pupilDtoList;
    }

    public PupilDto getPupilDto(Pupil pupil) {
        return PupilDto.builder()
                .id(pupil.getId())
                .name(pupil.getName())
                .lastName(pupil.getLastName())
                .username(pupil.getUsername())
                .email(pupil.getEmail())
                .password(pupil.getPassword())
                .roleDto(roleService.getRoleDto(pupil.getRole()))
                .build();
    }
}
