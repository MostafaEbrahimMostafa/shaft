package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for retrieving details of a list within a board using Trello API.
 */
public class RQ_006_GetListRequest {

    private String listId; // ID of the list to retrieve
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Fetches details of a specified Trello list.
     * @return Response object containing list details.
     */
    public Response getList() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());
        listId = RQ_005_CreateListRequest.getListId();

        if (listId == null || listId.isEmpty()) {
            throw new IllegalStateException("List ID is not initialized. Ensure list creation is executed first.");
        }

        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));

        return api.get("/1/lists/" + listId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }

    /**
     * Retrieves the stored list ID.
     * @return The list ID as a string.
     */
    public String getListId() {
        return listId;
    }
}
