package open.ai.controller;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import open.ai.Config.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {

  private final ChatClient chatClient;

  @GetMapping
  public ResponseEntity<ResponseDTO> chat(
      @RequestParam(defaultValue = "Hello", value = "message") String message) {
    return ResponseEntity.ok(chatClient.call(message));
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
