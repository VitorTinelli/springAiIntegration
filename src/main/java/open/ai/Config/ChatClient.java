package open.ai.Config;

import java.util.Set;
import open.ai.dto.ResponseDTO;
import open.ai.requests.ConversationDataRequestBody;
import org.springframework.stereotype.Service;

@Service
public class ChatClient{

  private final AiApi aiApi;

  public ChatClient(AiApi aiApi) {
    this.aiApi = aiApi;
  }

  public ResponseDTO call(ConversationDataRequestBody conversation) {
    return aiApi.returnResponse(conversation);
  }

  public Set models() {
    return aiApi.getModels();
  }

  public Set allModels() {
    return aiApi.getAllModels();
  }
}
