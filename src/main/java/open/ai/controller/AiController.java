package open.ai.controller;

import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import open.ai.config.ChatClient;
import open.ai.listOutputParser.ListOutputParser;
import open.ai.prompt.Message;
import open.ai.prompt.Prompt;
import open.ai.prompt.SystemMessage;
import open.ai.prompt.UserMessage;
import open.ai.requests.ConversationDataRequestBody;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {

  private final ChatClient chatClient;

  @GetMapping
  public ResponseEntity<List<String>> chat(@RequestBody ConversationDataRequestBody conversation) {
    ListOutputParser outputParser = new ListOutputParser(new DefaultConversionService());
    String message = conversation.getMessage() + outputParser.getFormat();
    return ResponseEntity.ok(outputParser.parse(
        chatClient.call(conversation.getId(), message)));
  }

  @GetMapping("/prompt")
  public ResponseEntity<String> prompt(@RequestBody ConversationDataRequestBody conversation) {
    String systemText = "Seu nome é contador tinelli, sempre começe dizendo seu nome, e após a resposta";
    Message systemMessage = new SystemMessage(systemText);
    Message userMessage = new UserMessage(conversation.getMessage());
    return ResponseEntity.ok(
        chatClient.call(conversation.getId(), new Prompt(List.of(systemMessage, userMessage)))
            .getResult().getAnswer().values().toString().replace("[", "").replace("]", ""));
  }

  @GetMapping("/models")
  public ResponseEntity<Set> getModels() {
    return ResponseEntity.ok(chatClient.models());
  }

  @GetMapping("/all_models")
  public ResponseEntity<Set> getAllModels() {
    return ResponseEntity.ok(chatClient.allModels());
  }

}
