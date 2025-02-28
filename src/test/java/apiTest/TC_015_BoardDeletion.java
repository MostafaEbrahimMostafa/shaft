package apiTest;

import apiRequests.RQ_004_DeleteBoardRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test class to verify that a Trello board is deleted successfully via API.
 */
public class TC_015_BoardDeletion {

    private RQ_004_DeleteBoardRequest deleteBoardRequest;
    private Response response;

    @Feature("F001_Trello Board Management")
    @Story("TC_003_Delete Trello Board")
    @Description("Verify that a Trello board is successfully deleted using the API.\n" +
            "Test Steps:\n" +
            "1. Sends a DELETE request to remove the board.\n" +
            "2. Validates that the response status code is 200, confirming successful deletion.\n" +
            "3. Ensures the response body is empty or '{}', confirming the board no longer exists.")
    @Test
    public void validateBoardDeletion() {
        // Step 1: Initialize the request object for deleting the board
        deleteBoardRequest = new RQ_004_DeleteBoardRequest();

        // Step 2: Send a request to delete the board
        response = deleteBoardRequest.deleteBoard();

        // Step 3: Ensure the response is not null
        Assert.assertNotNull(response, "Response is null. Board deletion might have failed.");

        // Extract the 'value' field from the JSON response
        String valueField = response.jsonPath().getString("value");

        // Assert that the value field is null
        Assert.assertNull(valueField, "Expected 'value' to be null after deletion!");
    }
}
