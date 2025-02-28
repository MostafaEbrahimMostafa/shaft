package apiTest;

import apiRequests.RQ_013_CreateChecklistRequest;
import apiRequests.RQ_017_CreateCheckitemRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to validate Trello check item creation within a checklist.
 */
public class TC_005_CheckitemCreation {

    private RQ_017_CreateCheckitemRequest createCheckitemRequest;
    private Response response;

    @Feature("F005_Trello Checkitem Management")
    @Story("TC_005_CheckItem Creation")
    @Description("Ensure that a new Trello check item is successfully created within a checklist via API and its details are validated.\n" +
            "Test Steps:\n" +
            "1. Sends a POST request to create a new check item within an existing checklist.\n" +
            "2. Validates HTTP status code to ensure a successful response.\n" +
            "3. Verifies that the check item's associated checklist ID matches the expected checklist ID.\n" +
            "4. Confirms correct data association for validation of the check item creation process.")
    @Test
    public void validateCheckItemCreation() {
        // Step 1: Initialize the request object for check item creation
        createCheckitemRequest = new RQ_017_CreateCheckitemRequest();

        // Step 2: Send a request to create a new check item
        response = createCheckitemRequest.createCheckItem();

        // Step 3: Validate response is not null
        Assert.assertNotNull(response, "Response is null. Check item creation might have failed.");

        // Step 4: Validate HTTP status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code. Check item creation failed.");

        // Step 5: Extract and validate the associated checklist ID
        String actualChecklistId = response.jsonPath().getString("idChecklist");
        Assert.assertEquals(actualChecklistId, RQ_013_CreateChecklistRequest.getChecklistId(), "Checklist ID mismatch!");
    }
}
