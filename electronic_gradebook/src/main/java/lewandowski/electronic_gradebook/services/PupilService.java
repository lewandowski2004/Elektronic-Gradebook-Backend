package lewandowski.electronic_gradebook.services;


import lewandowski.electronic_gradebook.Component.MessageComponent;
import lewandowski.electronic_gradebook.dto.PupilDto;
import lewandowski.electronic_gradebook.dto._toSave.PupilDtoToSave;
import lewandowski.electronic_gradebook.model.Address;
import lewandowski.electronic_gradebook.model.Parent;
import lewandowski.electronic_gradebook.model.Pupil;
import lewandowski.electronic_gradebook.model.Role;
import lewandowski.electronic_gradebook.model.enums.RoleName;
import lewandowski.electronic_gradebook.repository.PupilRepository;
import lewandowski.electronic_gradebook.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class PupilService {

    private final RoleService roleService;
    private final SchoolService schoolService;
    private final RoleRepository roleRepository;
    private final PupilRepository pupilRepository;
    private final MessageComponent messageComponent;
    private final PasswordEncoder encoder;

    public PupilService(RoleService roleService, SchoolService schoolService, RoleRepository roleRepository,
                        PupilRepository pupilRepository, MessageComponent messageComponent,
                        PasswordEncoder encoder) {
        this.roleService = roleService;
        this.schoolService = schoolService;
        this.roleRepository = roleRepository;
        this.pupilRepository = pupilRepository;
        this.messageComponent = messageComponent;
        this.encoder = encoder;
    }

    public void savePupilDto(PupilDtoToSave pupilDto) {
        Set<Role> roles = new HashSet<>();
        Role pupilRole = roleRepository.findByName(RoleName.ROLE_PUPIL)
                .orElseThrow(() -> new RuntimeException(messageComponent.NOT_FOUND));
        roles.add(pupilRole);
        Address address = Address.builder()
                .street(pupilDto.getStreet())
                .buildingNumber(pupilDto.getBuildingNumber())
                .apartmentNumber(pupilDto.getApartmentNumber())
                .city(pupilDto.getCity())
                .zipCode(pupilDto.getZipCode())
                .voivodeship(pupilDto.getVoivodeship())
                .country(pupilDto.getCountry())
                .build();
        Pupil pupil = Pupil.builder()
                .name(pupilDto.getName())
                .secondName(pupilDto.getSecondName())
                .lastName(pupilDto.getLastName())
                .pesel(pupilDto.getPesel())
                .dateOfBirth(pupilDto.getDateOfBirth())
                .dateOfAddition(new Date())
                .phoneNumber(pupilDto.getPhoneNumber())
                .username(pupilDto.getUsername())
                .active(pupilDto.getActive())
                .email(pupilDto.getEmail())
                .gender(pupilDto.getGender())
                .motherName(pupilDto.getMotherName())
                .fatherName(pupilDto.getFatherName())
                .address(address)
                .school(schoolService.getSchool(pupilDto.getSchoolDto()))
                .password(encoder.encode(pupilDto.getPassword()))
                .role(pupilRole)
                .build();

        pupilRepository.save(pupil);
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

    public PupilDto findById(UUID id) {
        Pupil pupil = pupilRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(messageComponent.NOT_FOUND));
        return getPupilDto(pupil);
    }

    public Set<Pupil> findByIdIn(Set<UUID> listId) {
        return pupilRepository.findByIdIn(listId);
    }

    public PupilDto findByEmail(String email) {
        Pupil pupil = pupilRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(messageComponent.NOT_FOUND));
        return getPupilDto(pupil);
    }

    public List<PupilDto> getPupilsDto() {
        return findAllPupilsDtoList(pupilRepository.findAll());
    }

    public List<PupilDto> findAllPupilsDtoList(List<Pupil> pupilList) {
        List<PupilDto> pupilDtoList = new ArrayList<>();
        for (Pupil pupil : pupilList) {
            PupilDto pupilDto = getPupilDto(pupil);
            pupilDtoList.add(pupilDto);
        }
        return pupilDtoList;
    }

}
