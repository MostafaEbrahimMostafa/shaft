package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for deleting a board using Trello API.
 */
public class RQ_004_DeleteBoardRequest {

    private String boardId;
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Deletes the board in Trello.
     * @return Response object confirming the board deletion.
     */
    public Response deleteBoard() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());
        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));
        boardId = RQ_001_CreateBoardRequest.getBoardId();

        if (boardId == null || boardId.isEmpty()) {
            throw new IllegalStateException("Board ID is not initialized. Please ensure board creation is executed first.");
        }

        return api.delete("/1/boards/" + boardId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }
}
