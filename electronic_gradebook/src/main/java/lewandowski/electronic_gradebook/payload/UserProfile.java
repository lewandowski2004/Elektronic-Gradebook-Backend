package lewandowski.electronic_gradebook.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserProfile {
    private UUID id;
    private String username;
    private String name;


    public UserProfile(UUID id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }
}
