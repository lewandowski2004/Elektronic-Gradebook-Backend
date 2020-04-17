package lewandowski.electronic_gradebook.services;


import lewandowski.electronic_gradebook.Component.MessageComponent;
import lewandowski.electronic_gradebook.dto.EmployeeDto;
import lewandowski.electronic_gradebook.dto.MessageDto;
import lewandowski.electronic_gradebook.dto._toSave.EmployeeDtoToSave;
import lewandowski.electronic_gradebook.dto._toSave.MessageDtoToSave;
import lewandowski.electronic_gradebook.model.Address;
import lewandowski.electronic_gradebook.model.Employee;
import lewandowski.electronic_gradebook.model.Message;
import lewandowski.electronic_gradebook.repository.EmployeeRepository;
import lewandowski.electronic_gradebook.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MessageService {

    @Autowired
    public MessageRepository messageRepository;
    @Autowired
    public EmployeeService employeeService;
    @Autowired
    public ParentService parentService;
    @Autowired
    public PupilService pupilService;
    @Autowired
    public MessageComponent messageComponent;
    @Autowired
    public PasswordEncoder encoder;


    public void saveMessageDto(MessageDtoToSave messageDtoToSave) {
        Message message = Message.builder()
                .title(messageDtoToSave.getTitle())
                .content(messageDtoToSave.getContent())
                .dateOfAddition(new Date())
                .senderEmail(messageDtoToSave.getSenderEmail())
                .employees(employeeService.findByIdIn(messageDtoToSave.getEmployeeIds()))
                .parents(parentService.findByIdIn(messageDtoToSave.getParentIds()))
                .pupils(pupilService.findByIdIn(messageDtoToSave.getPupilIds()))
                .build();

        messageRepository.save(message);
    }

    public Message getMessage(MessageDto messageDto) {

        Message message = Message.builder()
                .title(messageDto.getTitle())
                .content(messageDto.getContent())
                .dateOfAddition(messageDto.getDateOfAddition())
                .senderEmail(messageDto.getSenderEmail())
                .build();
        return message;
    }

    public MessageDto getMessageDto(Message message) {
        MessageDto messageDto = MessageDto.builder()
                .title(message.getTitle())
                .content(message.getContent())
                .dateOfAddition(message.getDateOfAddition())
                .senderEmail(message.getSenderEmail())
                .build();
        return messageDto;
    }

    public MessageDto findById(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(messageComponent.NOT_FOUND));
        return getMessageDto(message);
    }

    public List<MessageDto> findAllMessagesDtoList(List<Message> messageList) {
        List<MessageDto> messageDtoList = new ArrayList<>();
        for (Message message : messageList) {
            MessageDto messageDto = getMessageDto(message);
            messageDtoList.add(messageDto);
        }
        return messageDtoList;
    }

    public List<MessageDto> getMessagesDto() {
        return findAllMessagesDtoList(messageRepository.findAll());
    }

    public List<MessageDto> getMessagesDtoBySenderEmail(String senderEmail) {
        List<Message> allBySenderId = messageRepository.findBySenderEmail(senderEmail);
        return findAllMessagesDtoList(allBySenderId);
    }
}
