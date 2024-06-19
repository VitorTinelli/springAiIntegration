package open.ai.controller;

import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import open.ai.requests.ConversationDataRequestBody;
import open.ai.responses.AiResponse;
import open.ai.service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {

  private final AiService aiService;

  @GetMapping
  public ResponseEntity<String> call(@RequestBody ConversationDataRequestBody conversation) {
    return ResponseEntity.ok(aiService.call(conversation.getId(), conversation.getMessage()));
  }

  @GetMapping("/list")
  public ResponseEntity<List<String>> listOutputParser(
      @RequestBody ConversationDataRequestBody conversation) {
    return ResponseEntity.ok(
        aiService.ListOutputParser(conversation.getId(), conversation.getMessage()));
  }

  @GetMapping("/prompt")
  public ResponseEntity<AiResponse> prompt(@RequestBody ConversationDataRequestBody conversation) {
    return ResponseEntity.ok(aiService.promptCall(conversation.getId(), conversation.getMessage()));
  }

  @GetMapping("/models")
  public ResponseEntity<Set> getModels() {
    return ResponseEntity.ok(aiService.models());
  }

  @GetMapping("/all_models")
  public ResponseEntity<Set> getAllModels() {
    return ResponseEntity.ok(aiService.allModels());
  }

}
