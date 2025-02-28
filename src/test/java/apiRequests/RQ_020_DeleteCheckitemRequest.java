package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for deleting a check item from Trello.
 */
public class RQ_020_DeleteCheckitemRequest {

    private String checkItemId, checklistId;
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Deletes a check item from a checklist.
     * @return Response confirming the deletion.
     */
    public Response deleteCheckItem() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());

        // Retrieve CheckItem and Checklist IDs
        checkItemId = RQ_017_CreateCheckitemRequest.getCheckItemId();
        checklistId = RQ_013_CreateChecklistRequest.getChecklistId();

        // Validate CheckItem ID
        if (checkItemId == null || checkItemId.isEmpty()) {
            throw new IllegalStateException("CheckItem ID is not initialized. Please ensure a CheckItem is created first.");
        }

        // Validate Checklist ID
        if (checklistId == null || checklistId.isEmpty()) {
            throw new IllegalStateException("Checklist ID is not initialized. Please ensure a Checklist is created first.");
        }

        // Add query parameters
        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));

        // Send API request
        return api.delete("/1/checklists/" + checklistId + "/checkItems/" + checkItemId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }
}
