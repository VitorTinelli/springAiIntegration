package open.ai.config;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import open.ai.domain.ConversationData;
import open.ai.prompt.Prompt;
import open.ai.responses.AiResponse;
import open.ai.service.ConversationDataService;
import open.ai.utils.ApiUtils;
import open.ai.utils.StringOutputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.Builder;

@Service
public class AiApi {

  private static final String INFERENCE = "/v1/inference";
  private final RestClient restClient;
  private final ConversationDataService conversationDataService;
  private final String persistence;
  private final String model;

  public AiApi(Builder restClientBuilder,
      ConversationDataService conversationDataService,
      @Value("${spring.tinelli.ai.url}") String baseUrl,
      @Value("${spring.tinelli.ai.token}") String token,
      @Value("${spring.tinelli.ai.model}") String modelValue,
      @Value("${spring.tinelli.ai.persistence}") String persistenceValue) {
    this.conversationDataService = conversationDataService;
    this.model = modelValue;
    this.persistence = persistenceValue;
    this.restClient = restClientBuilder
        .baseUrl(baseUrl)
        .defaultHeaders(ApiUtils.getJsonContentHeaders(token))
        .build();
  }

  public AiResponse returnResponse(UUID id, String message) {
    Optional<ConversationData> history = conversationDataService.getConversationDataById(id);
    return history.map(hist -> {
          AiResponse resp = this.restClient.post()
              .uri(INFERENCE)
              .body(ApiUtils.getJsonContentBody(message, model, hist, persistence))
              .retrieve()
              .toEntity(AiResponse.class)
              .getBody();
          if (persistence.equals("true")) {
            conversationDataService.replaceConversationData(message,
                StringOutputParser.parse(resp), hist);
          }
          return resp;
        })
        .orElseGet(() -> {
              AiResponse resp = this.restClient.post()
                  .uri(INFERENCE)
                  .body(ApiUtils.getJsonContentBody(message, model, persistence))
                  .retrieve()
                  .toEntity(AiResponse.class)
                  .getBody();
              if (persistence.equals("true")) {
                conversationDataService.saveConversationData(message,
                    StringOutputParser.parse(resp));
              }
              return resp;
            }
        );
  }

  public AiResponse returnResponse(UUID id, Prompt message) {
    Optional<ConversationData> history = conversationDataService.getConversationDataById(id);
    return history.map(hist -> {
          AiResponse resp = this.restClient.post()
              .uri(INFERENCE)
              .body(ApiUtils.getJsonContentBody(message.toString(), model, hist, persistence))
              .retrieve()
              .toEntity(AiResponse.class)
              .getBody();
          if (persistence.equals("true")) {
            conversationDataService.replaceConversationData(message.toString(),
                StringOutputParser.parse(resp), hist);
          }
          return resp;
        })
        .orElseGet(() -> {
              AiResponse resp = this.restClient.post()
                  .uri(INFERENCE)
                  .body(ApiUtils.getJsonContentBody(message.toString(), model, persistence))
                  .retrieve()
                  .toEntity(AiResponse.class)
                  .getBody();
              if (persistence.equals("true")) {
                conversationDataService.saveConversationData(message.toString(),
                    StringOutputParser.parse(resp));
              }
              return resp;
            }
        );
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

