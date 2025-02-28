package apiTest;

import apiRequests.RQ_011_UpdateCardRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to verify that an existing Trello card is updated successfully via API.
 */
public class TC_008_CardUpdating {

    private RQ_011_UpdateCardRequest updateCardRequest;
    private Response response;

    @Feature("F003_Trello Card Management")
    @Story("TC_008_Card Updating")
    @Description("Ensure that an existing Trello card is successfully updated via API and its details are validated.\n" +
            "Test Steps:\n" +
            "1. Sends a PUT request to update the card name using predefined parameters.\n" +
            "2. Retrieves the updated card details via API.\n" +
            "3. Validates that the card name in the response matches the expected updated name.")
    @Test
    public void validateCardUpdate() {
        // Step 1: Initialize request object
        updateCardRequest = new RQ_011_UpdateCardRequest();

        // Step 2: Send the update request
        response = updateCardRequest.updateCard();

        // Step 3: Validate response is not null
        Assert.assertNotNull(response, "Response is null. Card update might have failed.");

        // Step 4: Validate HTTP status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code. Card update failed.");

        // Step 5: Extract and validate updated card name
        String actualUpdatedName = response.jsonPath().getString("name");
        Assert.assertEquals(actualUpdatedName, updateCardRequest.getUpdatedCardName(), "Card name update failed!");
    }
}
