package open.ai.Config;

import open.ai.controller.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ChatClient{

  private final AiApi aiApi;

  public ChatClient(AiApi aiApi) {
    this.aiApi = aiApi;
  }

  public ResponseDTO call(String message){
    return aiApi.returnResponse(message).getBody();
  }

}
