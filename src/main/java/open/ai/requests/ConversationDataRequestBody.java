package open.ai.requests;

import java.util.UUID;
import lombok.Data;

@Data
public class ConversationDataRequestBody {

  UUID id;
  String message;
}
