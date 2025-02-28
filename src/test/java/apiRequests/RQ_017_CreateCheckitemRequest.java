package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for creating a check item within a checklist in Trello.
 */
public class RQ_017_CreateCheckitemRequest {
    public static String checkItemId; // Changed naming to match conventions
    public static String checkItemName;
    public  static String cardId;
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;
    private String checklistId;

    /**
     * Creates a new check item in the existing checklist.
     * @return Response containing the created check item's details.
     */
    public Response createCheckItem() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());

        // Retrieve checklist and card IDs
        checklistId = RQ_013_CreateChecklistRequest.getChecklistId();
         cardId = RQ_009_CreateCardRequest.getCardId();

        // Validate checklist ID
        if (checklistId == null || checklistId.isEmpty()) {
            throw new IllegalStateException("Checklist ID is not initialized. Please ensure a checklist is created before adding check items.");
        }

        // Validate card ID
        if (cardId == null || cardId.isEmpty()) {
            throw new IllegalStateException("Card ID is not initialized. Please ensure a card is created before adding check items.");
        }

        // Add query parameters
        queryParams.add(List.of("name", "Valid Logout Redirection"));
        queryParams.add(List.of("idCard", cardId));
        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));

        // Send API request
        Response response = api.post("/1/checklists/" + checklistId + "/checkItems")
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();

        // Store response values
        checkItemId = response.jsonPath().getString("id");
        checkItemName = response.jsonPath().getString("name");

        return response;
    }

    // Retrieve checkitem ID I
    public static String getCheckItemId() {
        return checkItemId;
    }
}
