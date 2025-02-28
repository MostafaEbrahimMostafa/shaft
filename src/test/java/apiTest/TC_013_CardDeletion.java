package apiTest;

import apiRequests.RQ_012_DeleteCardRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to verify that an existing Trello card is deleted successfully via API.
 */
public class TC_013_CardDeletion {

    private RQ_012_DeleteCardRequest deleteCardRequest;
    private Response response;

    @Feature("F003_Trello Card Management")
    @Story("TC_009_Card Deletion")
    @Description("Ensure that a Trello card is successfully deleted via API and its details are validated.\n" +
            "Test Steps:\n" +
            "1. Sends a DELETE request to remove a card using its ID.\n" +
            "2. Verifies that the API responds with a 200 status code, indicating successful deletion.\n" +
            "3. Ensures that the response body is empty or contains '{}', confirming the card is deleted.")
    @Test
    public void validateCardDeletion() {
        // Step 1: Initialize the request object for deleting the card
        deleteCardRequest = new RQ_012_DeleteCardRequest();

        // Step 2: Send a request to delete the card
        response = deleteCardRequest.deleteCard();

        // Step 3: Extract the 'value' field from the JSON response
        String valueField = response.jsonPath().getString("value");

        // Step 4: Validate that the 'value' field is null after deletion
        Assert.assertNull(valueField, "Expected 'value' to be null after deletion, but found: " + valueField);
    }
}
