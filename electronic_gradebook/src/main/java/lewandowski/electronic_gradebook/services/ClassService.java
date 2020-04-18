package lewandowski.electronic_gradebook.services;


import lewandowski.electronic_gradebook.Component.MessageComponent;
import lewandowski.electronic_gradebook.dto.ClassDto;
import lewandowski.electronic_gradebook.dto.SchoolDto;
import lewandowski.electronic_gradebook.dto._toSave.ClassDtoToSave;
import lewandowski.electronic_gradebook.dto._toSave.SchoolDtoToSave;
import lewandowski.electronic_gradebook.model.Address;
import lewandowski.electronic_gradebook.model.Class;
import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.model.School;
import lewandowski.electronic_gradebook.repository.ClassRepository;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.repository.ParentRepository;
import lewandowski.electronic_gradebook.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class ClassService {

    @Autowired
    public ClassRepository classRepository;
    @Autowired
    public ParentRepository parentRepository;
    @Autowired
    public MessageComponent messageComponent;
    @Autowired
    public ParentService parentService;


    public void saveClassDto(ClassDtoToSave classDtoToSave) {
        Class schoolClass = Class.builder()
                .classNumber(classDtoToSave.getClassNumber())
                .classSymbol(classDtoToSave.getClassSymbol())
                .classProfile(classDtoToSave.getClassProfile())
                .build();

        classRepository.save(schoolClass);
    }

    public ClassDto findById(Integer id) {
        Class schoolClass = classRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(messageComponent.NOT_FOUND));
        return getClassDto(schoolClass);
    }


    public Class getClass(ClassDto classDto) {
        if(classDto != null) {
            Class schoolClass = Class.builder()
                    .classNumber(classDto.getClassNumber())
                    .classSymbol(classDto.getClassSymbol())
                    .classProfile(classDto.getClassProfile())
                    .build();
            return schoolClass;
        }else {
            return null;
        }
    }
    public ClassDto getClassDto(Class schoolClass) {
        if (schoolClass != null) {
            ClassDto classDto = ClassDto.builder()
                    .classNumber(schoolClass.getClassNumber())
                    .classSymbol(schoolClass.getClassSymbol())
                    .classProfile(schoolClass.getClassProfile())
                    .build();
            return classDto;
        }else {
            return null;
        }
    }
}
