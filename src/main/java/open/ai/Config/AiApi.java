package open.ai.Config;

import java.util.Set;
import open.ai.domain.ConversationData;
import open.ai.repository.ConversationDataRepository;
import open.ai.requests.ConversationDataRequestBody;
import open.ai.responses.AiResponse;
import open.ai.service.ConversationDataService;
import open.ai.utils.ApiUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.Builder;

@Service
public class AiApi {

  private final RestClient restClient;
  private final ConversationDataService conversationDataService;
  private final ConversationDataRepository conversationDataRepository;
  private final String persistence;
  private final String model;

  public AiApi(Builder restClientBuilder,
      ConversationDataService conversationDataService,
      ConversationDataRepository conversationDataRepository,
      @Value("${spring.tinelli.ai.url}") String baseUrl,
      @Value("${spring.tinelli.ai.token}") String token,
      @Value("${spring.tinelli.ai.model}") String modelValue,
      @Value("${spring.tinelli.ai.persistence}") String persistenceValue) {
    this.conversationDataService = conversationDataService;
    this.conversationDataRepository = conversationDataRepository;
    this.model = modelValue;
    this.persistence = persistenceValue;
    this.restClient = restClientBuilder
        .baseUrl(baseUrl)
        .defaultHeaders(ApiUtils.getJsonContentHeaders(token))
        .build();
  }

  public AiResponse returnResponse(ConversationDataRequestBody conversation) {
    ConversationData history = null;
    if (conversation.getId() != null) {
      history = conversationDataService.getConversationDataById(conversation.getId());
    }
    AiResponse response = this.restClient.post()
        .uri("/v1/inference")
        .body(ApiUtils.getJsonContentBody(conversation.getMessage(), model, history, persistence))
        .retrieve()
        .toEntity(AiResponse.class)
        .getBody();
    if (persistence.equals("true") && history != null) {
      conversationDataRepository.save(ConversationData.builder()
          .id(history.getId())
          .userMessage(history.getUserMessage() + conversation.getMessage())
          .aiResponse(
              history.getAiResponse() + response.getResult().getAnswer().values().toString())
          .build());
    } else if (persistence.equals("true")) {
      conversationDataService.saveConversationData(conversation.getMessage(),
          response.getResult().getAnswer().values().toString());
    }
    return response;
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
