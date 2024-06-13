package open.ai.Config;

import java.util.Set;
import open.ai.controller.ResponseDTO;
import open.ai.utils.ApiUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AiApi {

  private final RestClient restClient;
  private String model;

  public AiApi(RestClient.Builder restClientBuilder,
      @Value("${spring.tinelli.ai.url}") String baseUrl,
      @Value("${spring.tinelli.ai.token}") String token,
      @Value("${spring.tinelli.ai.model}") String modelValue) {
    this.model = modelValue;
    this.restClient = restClientBuilder
        .baseUrl(baseUrl)
        .defaultHeaders(ApiUtils.getJsonContentHeaders(token))
        .build();
  }

  public ResponseDTO returnResponse(String message) {
    return this.restClient.post()
        .uri("/v1/inference")
        .body(ApiUtils.getJsonContentBody(message, model))
        .retrieve()
        .toEntity(ResponseDTO.class)
        .getBody();
  }

  public Set getModels() {
    return this.restClient.get()
        .uri("/v2/workflow")
        .retrieve()
        .toEntity(Set.class)
        .getBody();
  }

  public Set getAllModels() {
    return this.restClient.get()
        .uri("/v2/workflow?show_all=true")
        .retrieve()
        .toEntity(Set.class)
        .getBody();
  }
}
