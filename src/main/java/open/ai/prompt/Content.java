package open.ai.prompt;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Content {


  String getContent();

  default Collection<Media> getMedia() {
    return getMedia("");
  }

  List<Media> getMedia(String... dummy);

  Map<String, Object> getMetadata();

}