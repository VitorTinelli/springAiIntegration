package open.ai.config;

import java.util.Set;
import java.util.UUID;
import open.ai.prompt.Prompt;
import open.ai.responses.AiResponse;
import open.ai.utils.StringOutputParser;
import org.springframework.stereotype.Service;

@Service
public class ChatClient {

  private final AiApi aiApi;

  public ChatClient(AiApi aiApi) {
    this.aiApi = aiApi;
  }

  public String call(UUID id, String message) {
    return StringOutputParser.parse(aiApi.returnResponse(id, message));
  }

  public AiResponse call(UUID id, Prompt message) {
    return aiApi.returnResponse(id, message);
  }

  public Set models() {
    return aiApi.getModels();
  }

  public Set allModels() {
    return aiApi.getAllModels();
  }
}
