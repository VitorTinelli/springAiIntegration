package open.ai.Config;

import open.ai.controller.ResponseDTO;
import open.ai.utils.ApiUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AiApi {

  private final RestClient restClient;

  public AiApi(RestClient.Builder restClientBuilder,
      @Value("${spring.tinelli.ai.url}") String baseUrl,
      @Value("${spring.tinelli.ai.token}") String token) {
    this.restClient = restClientBuilder
        .baseUrl(baseUrl)
        .defaultHeaders(ApiUtils.getJsonContentHeaders(token))
        .build();
  }

  public ResponseEntity<ResponseDTO> returnResponse(String message) {
    return this.restClient.post()
        .uri("/v1/inference")
        .body(ApiUtils.getJsonContentBody(message))
        .retrieve()
        .toEntity(ResponseDTO.class);
  }
}
