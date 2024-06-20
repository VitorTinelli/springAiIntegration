package open.ai.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import open.ai.service.ConversationDataService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("data")
@RequiredArgsConstructor
public class ConversationDataController {

  private final ConversationDataService conversationDataService;

  @DeleteMapping
  public void deleteConversationData(UUID id) {
    conversationDataService.deleteConversationData(id);
  }

}
