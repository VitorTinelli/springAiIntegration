package open.ai.prompt;

import java.io.IOException;
import java.net.URL;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.MimeType;

/**
 * The Media class represents the data and metadata of a media attachment in a message. It consists
 * of a MIME type and the raw data.
 * <p>
 * This class is used as a parameter in the constructor of the UserMessage class.
 *
 * @author Christian Tzolov
 * @since 0.8.1
 */
public class Media {

  private final MimeType mimeType;

  private final Object data;

  /**
   * The Media class represents the data and metadata of a media attachment in a message. It
   * consists of a MIME type and the raw data.
   * <p>
   * This class is used as a parameter in the constructor of the UserMessage class.
   *
   * @deprecated This constructor is deprecated since version 1.0.0 M1 and will be removed in a
   * future release.
   */
  @Deprecated(since = "1.0.0 M1", forRemoval = true)
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

  /**
   * Get the media data object
   *
   * @return a java.net.URL.toString() or a byte[]
   */
  public Object getData() {
    return this.data;
  }

}
