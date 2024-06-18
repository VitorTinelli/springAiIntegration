package open.ai.prompt;

import java.util.Map;

public class AssistantMessage extends AbstractMessage {

  public AssistantMessage(String content) {
    super(MessageType.ASSISTANT, content);
  }

  public AssistantMessage(String content, Map<String, Object> properties) {
    super(MessageType.ASSISTANT, content, properties);
  }

  @Override
  public String toString() {
    return "AssistantMessage{" + "content='" + getContent() + '\'' + ", properties=" + metadata
        + ", messageType="
        + messageType + '}';
  }

}

