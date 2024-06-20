package open.ai.prompt;

import java.io.IOException;
import java.net.URL;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.MimeType;

public class Media {

  private final MimeType mimeType;

  private final Object data;

  public Media(MimeType mimeType, Object data) {
    Assert.notNull(mimeType, "MimeType must not be null");
    this.mimeType = mimeType;
    this.data = data;
  }

  public Media(MimeType mimeType, URL url) {
    Assert.notNull(mimeType, "MimeType must not be null");
    this.mimeType = mimeType;
    this.data = url.toString();
  }

  public Media(MimeType mimeType, Resource resource) {
    Assert.notNull(mimeType, "MimeType must not be null");
    this.mimeType = mimeType;
    try {
      this.data = resource.getContentAsByteArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public MimeType getMimeType() {
    return this.mimeType;
  }

  public Object getData() {
    return this.data;
  }

}
