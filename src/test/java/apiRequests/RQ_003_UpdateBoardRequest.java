package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for updating a board's details using Trello API.
 */
public class RQ_003_UpdateBoardRequest {

    private String boardId;
    private String updatedBoardName;
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Updates the board name in Trello.
     * @return Response object containing the updated board details.
     */
    public Response updateBoard() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());
        updatedBoardName = "DataBase Testing";
        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));
        queryParams.add(List.of("name", updatedBoardName));
        boardId = RQ_001_CreateBoardRequest.getBoardId();

        if (boardId == null || boardId.isEmpty()) {
            throw new IllegalStateException("Board ID is not initialized. Please ensure board creation is executed first.");
        }

        return api.put("/1/boards/" + boardId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }
    // Retreive updated board name.
    public String getUpdatedBoardName() {
        return updatedBoardName;
    }
}
