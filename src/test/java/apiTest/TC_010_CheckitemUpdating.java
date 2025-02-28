package apiTest;

import apiRequests.RQ_019_UpdateCheckitemRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to verify that an existing Trello checkitem is updated successfully via API.
 */
public class TC_010_CheckitemUpdating {

    private RQ_019_UpdateCheckitemRequest updateCheckitemRequest;
    private Response response;

    @Feature("F005_Trello Checkitem Management")
    @Story("TC_014_Checkitem Updating")
    @Description("Ensure that an existing Trello checkitem is successfully updated via API and its details are validated.\n" +
            "Test Steps:\n" +
            "1. Sends a PUT request to update the checkitem name using predefined parameters.\n" +
            "2. Retrieves the updated checkitem details via API.\n" +
            "3. Validates that the checkitem name in the response matches the expected updated name.")
    @Test
    public void validateCheckitemUpdate() {
        // Step 1: Initialize the request object for updating the checkitem
        updateCheckitemRequest = new RQ_019_UpdateCheckitemRequest();

        // Step 2: Send a request to update the checkitem
        response = updateCheckitemRequest.updateCheckItem();

        // Step 3: Validate that the response is not null
        Assert.assertNotNull(response, "Response is null. Checkitem update might have failed.");

        // Step 4: Validate HTTP status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code. Checkitem update failed.");

        // Step 5: Extract the updated checkitem name from the response
        String actualUpdatedName = response.jsonPath().getString("name");

        // Step 6: Validate that the updated name matches the expected name
        Assert.assertEquals(actualUpdatedName, updateCheckitemRequest.getUpdatedCheckitemName(),
                "Checkitem name update failed!");
    }
}
