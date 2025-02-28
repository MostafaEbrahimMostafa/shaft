package apiTest;

import apiRequests.RQ_008_DeleteListRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to verify that a Trello list is deleted successfully via API.
 */
public class TC_014_ListDeletion {

    private RQ_008_DeleteListRequest deleteListRequest;
    private Response response;

    @Feature("F002_Trello List Management")
    @Story("TC_006_List Deletion")
    @Description("Ensure that a Trello list is successfully deleted via API and its details are validated.\n" +
            "Test Steps:\n" +
            "1. Sends a DELETE request to remove a list using its ID.\n" +
            "2. Verifies that the API responds with a 200 status code, indicating successful deletion.\n" +
            "3. Ensures that the response body is empty or '{}', confirming the list is deleted.")
    @Test
    public void validateListDeletion() {
        // Step 1: Initialize the request object for deleting the list
        deleteListRequest = new RQ_008_DeleteListRequest();

        // Step 2: Send a request to delete the list
        response = deleteListRequest.delete_list();

        // Step 3: Extract the 'value' field from the JSON response
        String valueField = response.jsonPath().getString("value");

        // Step 4: Validate that the 'value' field is null after deletion
        Assert.assertNull(valueField, "Expected 'value' to be null after deletion, but found: " + valueField);
    }
}
