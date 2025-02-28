package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for updating a check item in Trello.
 */
public class RQ_019_UpdateCheckitemRequest {

    private String checkItemId, cardId;
    private String updatedCheckItemName;
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Updates the name of a specific check item.
     * @return Response containing the updated check item details.
     */
    public Response updateCheckItem() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());
        updatedCheckItemName = "Validate logout working correctly";

        // Retrieve CheckItem and Card IDs
        checkItemId = RQ_017_CreateCheckitemRequest.getCheckItemId();
        cardId = RQ_009_CreateCardRequest.getCardId();

        // Validate CheckItem ID
        if (checkItemId == null || checkItemId.isEmpty()) {
            throw new IllegalStateException("CheckItem ID is not initialized. Please ensure a CheckItem is created first.");
        }

        // Validate Card ID
        if (cardId == null || cardId.isEmpty()) {
            throw new IllegalStateException("Card ID is not initialized. Please ensure a Card is created first.");
        }

        // Add query parameters
        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));
        queryParams.add(List.of("name", updatedCheckItemName));

        // Send API request
        return api.put("/1/cards/" + cardId + "/checkItem/" + checkItemId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }

    public String getUpdatedCheckitemName() {
        return updatedCheckItemName;
    }
}
