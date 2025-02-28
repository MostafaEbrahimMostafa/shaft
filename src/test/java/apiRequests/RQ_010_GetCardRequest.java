package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for retrieving a card from a Trello list.
 */
public class RQ_010_GetCardRequest {

    private String cardId;
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Retrieves details of a specific card from Trello.
     * @return Response containing the card details.
     */
    public Response getCard() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());

        cardId = RQ_009_CreateCardRequest.getCardId();
        if (cardId == null || cardId.isEmpty()) {
            throw new IllegalStateException("Card ID is not initialized. Please ensure a card is created first.");
        }

        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));

        return api.get("/1/cards/" + cardId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }
}
