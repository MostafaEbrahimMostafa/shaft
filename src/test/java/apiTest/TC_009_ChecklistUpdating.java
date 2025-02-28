package apiTest;

import apiRequests.RQ_015_UpdateChecklistRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to verify that an existing Trello checklist is updated successfully via API.
 */
public class TC_009_ChecklistUpdating {

    private RQ_015_UpdateChecklistRequest updateChecklistRequest;
    private Response response;

    @Feature("F004_Trello Checklist Management")
    @Story("TC_011_Checklist Updating")
    @Description("Ensure that an existing Trello checklist is successfully updated via API and its details are validated.\n" +
            "Test Steps:\n" +
            "1. Sends a PUT request to update the checklist name using predefined parameters.\n" +
            "2. Retrieves the updated checklist details via API.\n" +
            "3. Validates that the checklist name in the response matches the expected updated name.")
    @Test
    public void validateChecklistUpdate() {
        // Step 1: Initialize the request object for updating the checklist
        updateChecklistRequest = new RQ_015_UpdateChecklistRequest();

        // Step 2: Send a request to update the checklist
        response = updateChecklistRequest.updateChecklist();

        // Step 3: Validate response is not null
        Assert.assertNotNull(response, "Response is null. Checklist update might have failed.");

        // Step 4: Validate HTTP status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code. Checklist update failed.");

        // Step 5: Extract the updated checklist name from the response
        String actualUpdatedName = response.jsonPath().getString("name");

        // Step 6: Validate that the updated name matches the expected name
        Assert.assertEquals(actualUpdatedName, updateChecklistRequest.getUpdatedChecklistName(), "Checklist name update failed!");
    }
}
