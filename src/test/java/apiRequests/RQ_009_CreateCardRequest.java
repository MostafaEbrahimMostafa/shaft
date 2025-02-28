package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for creating a card within a Trello list.
 */
public class RQ_009_CreateCardRequest {

    private static String cardId; // Stores created card ID
    private static String cardName; // Stores created card name
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Creates a new card inside an existing Trello list.
     */
    public void createCard() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());

        String listId = RQ_005_CreateListRequest.getListId();
        if (listId == null || listId.isEmpty()) {
            throw new IllegalStateException("List ID is not initialized. Please ensure list creation is executed first.");
        }

        queryParams.add(List.of("name", "Login")); // Card name
        queryParams.add(List.of("idList", listId)); // Assign to list
        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));

        api.post("/1/cards")
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();

        cardId = api.getResponseJSONValue("id");
        cardName = api.getResponseJSONValue("name");
    }

    /**
     * Retrieves the created card ID.
     * @return The created card ID.
     */
    public static String getCardId() {
        return cardId;
    }

    /**
     * Retrieves the created card name.
     * @return The created card name.
     */
    public static String getCardName() {
        return cardName;
    }
}
