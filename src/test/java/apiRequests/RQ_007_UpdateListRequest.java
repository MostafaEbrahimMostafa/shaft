package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for updating a list within a Trello board.
 */
public class RQ_007_UpdateListRequest {

    private String listId; // ID of the list to be updated
    private final String updatedListName = "IN Progress"; // New name for the list
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Updates the name of an existing Trello list.
     * @return Response object containing the update status.
     */
    public Response updateList() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());
        listId = RQ_005_CreateListRequest.getListId();

        if (listId == null || listId.isEmpty()) {
            throw new IllegalStateException("List ID is not initialized. Ensure list creation is executed first.");
        }

        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));
        queryParams.add(List.of("name", updatedListName));

        return api.put("/1/lists/" + listId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }

    /**
     * Retrieves the ID of the updated list.
     * @return The list ID as a string.
     */
    public String getListId() {
        return listId;
    }

    public String getUpdatedListName() {
        return updatedListName;
    }
}
