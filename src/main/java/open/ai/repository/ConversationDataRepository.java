package open.ai.repository;

import java.util.UUID;
import open.ai.domain.ConversationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationDataRepository extends JpaRepository<ConversationData, UUID> {

}
