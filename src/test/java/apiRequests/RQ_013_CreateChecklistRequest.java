package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for creating a checklist on a Trello card.
 */
public class RQ_013_CreateChecklistRequest {

    private static String checklistId;
    private static String checklistName;
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Creates a checklist for a specific Trello card.
     * @return Response containing the checklist details.
     */
    public Response createChecklist() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());

        String cardId = RQ_009_CreateCardRequest.getCardId();
        if (cardId == null || cardId.isEmpty()) {
            throw new IllegalStateException("Card ID is not initialized. Please ensure a card is created before adding a checklist.");
        }

        queryParams.add(List.of("name", "Valid Login Scenario"));
        queryParams.add(List.of("idCard", cardId));
        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));

        Response response = api.post("/1/checklists")
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();

        checklistId = response.jsonPath().getString("id");
        checklistName = response.jsonPath().getString("name");

        return response;
    }

    /**
     * Retrieves the checklist ID.
     * @return The checklist ID.
     */
    public static String getChecklistId() {
        return checklistId;
    }

    /**
     * Retrieves the checklist name.
     * @return The checklist name.
     */
    public static String getChecklistName() {
        return checklistName;
    }
}
