package open.ai.prompt;

import java.util.Map;

public class FunctionMessage extends AbstractMessage {

  public FunctionMessage(String content) {
    super(MessageType.FUNCTION, content);
  }

  public FunctionMessage(String content, Map<String, Object> properties) {
    super(MessageType.FUNCTION, content, properties);
  }

  @Override
  public String toString() {
    return "FunctionMessage{" + "content='" + getContent() + '\'' + ", properties: messageType="
        + messageType + '}';
  }

}