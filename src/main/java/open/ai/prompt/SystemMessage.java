package open.ai.prompt;

import org.springframework.core.io.Resource;

public class SystemMessage extends AbstractMessage {

  public SystemMessage(String content) {
    super(MessageType.SYSTEM, content);
  }

  public SystemMessage(Resource resource) {
    super(MessageType.SYSTEM, resource);
  }

  @Override
  public String toString() {
    return "SystemMessage{" + "content='" + getContent() + '\'' + ", properties: messageType="
        + messageType + '}';
  }

}
