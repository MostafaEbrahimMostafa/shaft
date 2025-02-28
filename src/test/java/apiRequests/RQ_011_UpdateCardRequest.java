package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for updating a Trello card's name.
 */
public class RQ_011_UpdateCardRequest {

    private String cardId;
    private String updatedCardName;
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Updates the name of an existing Trello card.
     * @return Response containing the updated card details.
     */
    public Response updateCard() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());
        updatedCardName = "Logout";
        cardId = RQ_009_CreateCardRequest.getCardId();
        if (cardId == null || cardId.isEmpty()) {
            throw new IllegalStateException("Card ID is not initialized. Please ensure a card is created first.");
        }

        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));
        queryParams.add(List.of("name", updatedCardName));

        return api.put("/1/cards/" + cardId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }

    // Retireve updated card name.
    public String getUpdatedCardName() {
        return updatedCardName;
    }
}
