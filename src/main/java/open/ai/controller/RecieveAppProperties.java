package open.ai.controller;

import open.ai.Config.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class RecieveAppProperties {

  private final ChatClient chatClient;

  public RecieveAppProperties(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

  @GetMapping
  public ResponseDTO chat() {
    return chatClient.call("Hello");
  }

}
