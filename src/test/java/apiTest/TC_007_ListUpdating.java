package apiTest;

import apiRequests.RQ_007_UpdateListRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to verify that an existing Trello list is updated successfully via API.
 */
public class TC_007_ListUpdating {

    private RQ_007_UpdateListRequest updateListRequest;
    private Response response;

    @Feature("F002_Trello List Management")
    @Story("TC_005_List Updating")
    @Description("Ensure that an existing Trello list is successfully updated via API and its details are validated.\n" +
            "Test Steps:\n" +
            "1. Sends a PUT request to update the list name using predefined parameters.\n" +
            "2. Retrieves the updated list details via API.\n" +
            "3. Validates that the list name in the response matches the expected updated name.")
    @Test
    public void validateListUpdate() {
        // Step 1: Initialize request object
        updateListRequest = new RQ_007_UpdateListRequest();

        // Step 2: Send the update request
        response = updateListRequest.updateList();

        // Step 3: Validate response is not null
        Assert.assertNotNull(response, "Response is null. List update might have failed.");

        // Step 4: Validate HTTP status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code. List update failed.");

        // Step 5: Extract and validate updated list name
        String actualUpdatedName = response.jsonPath().getString("name");
        Assert.assertEquals(actualUpdatedName, updateListRequest.getUpdatedListName(), "List name update failed!");
    }
}
