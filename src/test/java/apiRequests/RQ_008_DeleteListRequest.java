package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for archiving a list in Trello.
 * Trello does NOT allow deleting lists, only closing (archiving) them.
 */
public class RQ_008_DeleteListRequest {

    private String listId; // ID of the list to be archived
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Archives (closes) an existing Trello list.
     * @return Response object containing the archive status.
     */
    public Response delete_list() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());
        listId = RQ_005_CreateListRequest.getListId();

        if (listId == null || listId.isEmpty()) {
            throw new IllegalStateException("List ID is not initialized. Ensure list creation is executed first.");
        }

        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));
        queryParams.add(List.of("value", "true")); // Flag to archive list

        return api.put("/1/lists/" + listId + "/closed")
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }

    /**
     * Retrieves the ID of the archived list.
     * @return The archived list ID.
     */
    public String getListId() {
        return listId;
    }
}
