package apiTest;

import apiRequests.RQ_003_UpdateBoardRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to verify that an existing Trello board can be updated via API.
 */
public class TC_006_BoardUpdating {

    private RQ_003_UpdateBoardRequest updateBoardRequest;
    private Response response;

    @Feature("F001_Trello Board Management")
    @Story("TC_006_Update Board")
    @Description("Verify that an existing Trello board can be successfully updated using the API.\n" +
            "Test Steps:\n" +
            "1. Sends a PUT request to update the board name.\n" +
            "2. Validates HTTP response status to ensure a successful update.\n" +
            "3. Extracts the updated board name from the response.\n" +
            "4. Compares the updated board name with the expected new name.")
    @Test
    public void validateBoardUpdate() {
        // Step 1: Initialize request object
        updateBoardRequest = new RQ_003_UpdateBoardRequest();

        // Step 2: Send the update request
        response = updateBoardRequest.updateBoard();

        // Step 3: Validate response is not null
        Assert.assertNotNull(response, "Response is null. Board update might have failed.");

        // Step 4: Validate HTTP status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code. Board update failed.");

        // Step 5: Extract and validate updated board name
        String actualBoardName = response.jsonPath().getString("name");
        Assert.assertEquals(actualBoardName, updateBoardRequest.getUpdatedBoardName(), "Board name mismatch!");
    }
}
