package open.ai.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import open.ai.config.ChatClient;
import open.ai.listOutputParser.ListOutputParser;
import open.ai.prompt.Message;
import open.ai.prompt.Prompt;
import open.ai.prompt.SystemMessage;
import open.ai.prompt.UserMessage;
import open.ai.responses.AiResponse;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

@Service
public class AiService {

  private final ChatClient chatClient;

  public AiService(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  public String call(UUID id, String message) {
    return chatClient.call(id, message);
  }

  public List<String> ListOutputParser(UUID id, String message) {
    ListOutputParser outputParser = new ListOutputParser(new DefaultConversionService());
    return outputParser.parse(chatClient.call(id, message + outputParser.getFormat()));
  }

  public AiResponse promptCall(UUID id, String message) {
    String systemText = "Seu nome é contador tinelli, sempre começe dizendo seu nome, e após a resposta";
    Message systemMessage = new SystemMessage(systemText);
    Message userMessage = new UserMessage(message);
    return chatClient.call(id, new Prompt(List.of(systemMessage, userMessage)));
  }

  public Set models() {
    return chatClient.models();
  }

  public Set allModels() {
    return chatClient.allModels();
  }
}
