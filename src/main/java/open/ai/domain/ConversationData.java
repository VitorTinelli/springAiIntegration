package open.ai.domain;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConversationData {

  @Id
  private UUID id;

  @Column(length = 1000000000)
  private String userMessage;
  @Column(length = 1000000000)
  private String aiResponse;

}
