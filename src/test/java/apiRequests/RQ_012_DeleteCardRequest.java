package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for deleting a Trello card.
 */
public class RQ_012_DeleteCardRequest {

    private String cardId;
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Deletes an existing Trello card.
     * @return Response containing the deletion confirmation.
     */
    public Response deleteCard() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());

        cardId = RQ_009_CreateCardRequest.getCardId();
        if (cardId == null || cardId.isEmpty()) {
            throw new IllegalStateException("Card ID is not initialized. Please ensure a card is created before deleting.");
        }

        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));

        return api.delete("/1/cards/" + cardId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }
}
