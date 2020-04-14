package lewandowski.electronic_gradebook.Component;

import org.springframework.stereotype.Component;

@Component
public class MessageComponent {

    public final String EMAIL_TAKEN = "Email Address already in use!";
    public final String USERNAME_TAKEN = "Username is already taken!";
    public final String REGISTRATION_SUCCESS = "Registered successfully";
    public final String SCHOOL_ADDED = "School has been added, you can only edit it";
}
