package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for retrieving a checklist from a Trello card.
 */
public class RQ_014_GetChecklistRequest {

    private String checklistId;
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Retrieves an existing checklist.
     * @return Response containing checklist details.
     */
    public Response getChecklist() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());

        checklistId = RQ_013_CreateChecklistRequest.getChecklistId();
        if (checklistId == null || checklistId.isEmpty()) {
            throw new IllegalStateException("Checklist ID is not initialized. Please ensure a checklist is created before fetching.");
        }

        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));

        return api.get("/1/checklists/" + checklistId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }
}
