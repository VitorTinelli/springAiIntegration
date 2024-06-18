package open.ai.prompt;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.Resource;

public class UserMessage extends AbstractMessage {

  public UserMessage(String message) {
    super(MessageType.USER, message);
  }

  public UserMessage(Resource resource) {
    super(MessageType.USER, resource);
  }

  public UserMessage(String textContent, List<Media> mediaList) {
    super(MessageType.USER, textContent, mediaList);
  }

  public UserMessage(String textContent, Media... media) {
    this(textContent, Arrays.asList(media));
  }

  public UserMessage(String textContent, Collection<Media> mediaList,
      Map<String, Object> metadata) {
    super(MessageType.USER, textContent, mediaList, metadata);
  }

  @Override
  public String toString() {
    return "UserMessage{" + "content='" + getContent() + '\'' + ", properties=" + metadata
        + ", messageType="
        + messageType + '}';
  }

}