package lewandowski.electronic_gradebook.repository;


import lewandowski.electronic_gradebook.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderEmail(String senderEmail);

}
