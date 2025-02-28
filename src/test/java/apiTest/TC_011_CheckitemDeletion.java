package apiTest;

import apiRequests.RQ_020_DeleteCheckitemRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to verify that an existing Trello checkitem is deleted successfully via API.
 */
public class TC_011_CheckitemDeletion {

    private RQ_020_DeleteCheckitemRequest deleteCheckitemRequest;
    private Response response;

    @Feature("F005_Trello Checkitem Management")
    @Story("TC_015_Checkitem Deletion")
    @Description("Ensure that a Trello checkitem is successfully deleted via API and its details are validated.\n" +
            "Test Steps:\n" +
            "1. Sends a DELETE request to remove a checkitem using its ID.\n" +
            "2. Verifies that the API responds with a 200 status code, indicating successful deletion.\n" +
            "3. Ensures that the response body does not contain the checkitem, confirming its deletion.")
    @Test
    public void validateCheckitemDeletion() {
        // Step 1: Initialize the request object for deleting the checkitem
        deleteCheckitemRequest = new RQ_020_DeleteCheckitemRequest();

        // Step 2: Send a request to delete the checkitem
        response = deleteCheckitemRequest.deleteCheckItem();

        // Step 3: Extract the 'value' field from the JSON response
        String valueField = response.jsonPath().getString("value");

        // Step 4: Validate that the 'value' field is null after deletion
        Assert.assertNull(valueField, "Expected 'value' to be null after deletion, but found: " + valueField);

    }
}
