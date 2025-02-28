package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for creating a list within a board using Trello API.
 */
public class RQ_005_CreateListRequest {

    private static String listId; // To store the list ID
    private static String listName; // To store the list name
    private static String boardId; // To store the board ID
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Creates a new list in the specified Trello board.
     */
    public void createList() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());
        boardId = RQ_001_CreateBoardRequest.getBoardId();

        if (boardId == null || boardId.isEmpty()) {
            throw new IllegalStateException("Board ID is not initialized. Please ensure board creation is executed first.");
        }

        queryParams.add(List.of("name", "Pending Testing"));
        queryParams.add(List.of("idBoard", boardId));
        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));

        api.post("/1/lists")
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();

        listId = api.getResponseJSONValue("id");
        listName = api.getResponseJSONValue("name");
    }

    /**
     * Retrieves the created list ID.
     * @return The list ID as a string.
     */
    public static String getListId() {
        return listId;
    }

    /**
     * Retrieves the created list name.
     * @return The list name as a string.
     */
    public static String getListName() {
        return listName;
    }
}
