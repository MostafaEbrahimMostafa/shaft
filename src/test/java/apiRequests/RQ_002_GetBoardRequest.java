package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * API request class for retrieving a board's details using Trello API.
 */
public class RQ_002_GetBoardRequest {

    private String boardId;
    private final List<List<Object>> queryParams = new ArrayList<>();
    private SHAFT.API api;

    /**
     * Retrieves board details from Trello using the stored board ID.
     * @return Response object containing board details.
     */
    public Response getBoard() {
        api = new SHAFT.API(RQ_001_CreateBoardRequest.getBaseUrl());
        queryParams.add(List.of("key", RQ_001_CreateBoardRequest.getApiKey()));
        queryParams.add(List.of("token", RQ_001_CreateBoardRequest.getToken()));
        boardId = RQ_001_CreateBoardRequest.getBoardId();

        if (boardId == null || boardId.isEmpty()) {
            throw new IllegalStateException("Board ID is not initialized. Please ensure board creation is executed first.");
        }

        return api.get("/1/boards/" + boardId)
                .setParameters(queryParams, RestActions.ParametersType.QUERY)
                .setContentType(ContentType.JSON)
                .perform();
    }
}
