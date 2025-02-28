package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for updating an existing checklist on a Trello card.
 */
public class RQ_015_UpdateChecklistRequest {

    private String checklistId;
    private static final String UPDATED_CHECKLIST_NAME = "Valid Test Cases";
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Updates an existing checklist's name.
     * @return Response containing updated checklist details.
     */
    public Response updateChecklist() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());
        checklistId = RQ_013_CreateChecklistRequest.getChecklistId();

        if (checklistId == null || checklistId.isEmpty()) {
            throw new IllegalStateException("Checklist ID is not initialized. Please ensure a checklist is created before updating.");
        }

        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));
        queryParams.add(List.of("name", UPDATED_CHECKLIST_NAME));

        return api.put("/1/checklists/" + checklistId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }

    // Reterive updated checklist name.
    public String getUpdatedChecklistName() {
        return UPDATED_CHECKLIST_NAME;
    }
}
