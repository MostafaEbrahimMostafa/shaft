package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for retrieving a check item from a checklist in Trello.
 */
public class RQ_018_GetCheckitemRequest {

    private String checkItemId, checklistId;
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Retrieves a specific check item from a checklist.
     * @return Response containing the check item details.
     */
    public Response getCheckItem() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());

        // Retrieve checklist and check item IDs
        checkItemId = RQ_017_CreateCheckitemRequest.getCheckItemId();
        checklistId = RQ_013_CreateChecklistRequest.getChecklistId();

        // Validate checklist ID
        if (checklistId == null || checklistId.isEmpty()) {
            throw new IllegalStateException("Checklist ID is not initialized. Please ensure a checklist is created first.");
        }

        // Validate check item ID
        if (checkItemId == null || checkItemId.isEmpty()) {
            throw new IllegalStateException("Check Item ID is not initialized. Please ensure a check item is created first.");
        }

        // Add query parameters
        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));

        // Send API request
        return api.get("/1/checklists/" + checklistId + "/checkItems/" + checkItemId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }
}
