package apiTest;

import apiRequests.RQ_001_CreateBoardRequest;
import apiRequests.RQ_002_GetBoardRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to validate Trello board creation.
 */
public class TC_001_BoardCreation {

    private RQ_001_CreateBoardRequest createBoardRequest;
    private RQ_002_GetBoardRequest getBoardRequest;
    private Response response;

    @Feature("F001_Trello Board Management")
    @Story("TC_001_Board Creation and Validation")
    @Description("Ensure that a new Trello board is successfully created via API and its details are validated.\n" +
            "Test Steps:\n" +
            "1. Sends a POST request to create a new board.\n" +
            "2. Retrieves the created board details via a GET request.\n" +
            "3. Validates that the board ID and name match expected values.\n" +
            "4. Confirms successful board creation by verifying HTTP response codes.")
    @Test
    public void validateBoardCreation() {
        createBoardRequest = new RQ_001_CreateBoardRequest();
        getBoardRequest = new RQ_002_GetBoardRequest();

        // Step 1: Create the board
        createBoardRequest.createBoard();

        // Step 2: Retrieve board details
        response = getBoardRequest.getBoard();

        // Step 3: Validate response is not null
        Assert.assertNotNull(response, "Response is null. Board creation might have failed.");

        // Step 4: Validate HTTP status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code. Board retrieval failed.");

        // Step 5: Validate board ID and name
        String actualBoardId = response.jsonPath().getString("id");
        String actualBoardName = response.jsonPath().getString("name");

        Assert.assertEquals(actualBoardId, RQ_001_CreateBoardRequest.getBoardId(), "Board ID mismatch.");
        Assert.assertEquals(actualBoardName, RQ_001_CreateBoardRequest.getBoardName(), "Board name mismatch.");
    }
}
