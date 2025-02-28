package apiTest;

import apiRequests.*;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to validate Trello checklist creation.
 */
public class TC_004_ChecklistCreation {

    private RQ_013_CreateChecklistRequest createChecklistRequest;
    private RQ_010_GetCardRequest getCardRequest;
    private Response response;

    @Feature("F004_Trello Checklist Management")
    @Story("TC_004_Checklist Creation")
    @Description("Ensure that a new Trello checklist is successfully created via API and its details are validated.\n" +
            "Test Steps:\n" +
            "1. Sends a POST request to create a new checklist for a card.\n" +
            "2. Validates that the checklist ID and name match expected values.\n" +
            "3. Retrieves the card details via a GET request.\n" +
            "4. Validates that the card is associated with the correct list and board.\n" +
            "5. Confirms successful checklist creation by verifying HTTP response codes.")
    @Test
    public void validateChecklistCreation() {
        // Step 1: Initialize the request object for checklist creation
        createChecklistRequest = new RQ_013_CreateChecklistRequest();

        // Step 2: Send request to create a new checklist
        response = createChecklistRequest.createChecklist();

        // Step 3: Validate response is not null
        Assert.assertNotNull(response, "Response is null. Checklist creation might have failed.");

        // Step 4: Validate HTTP status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code. Checklist creation failed.");

        // Step 5: Extract and validate checklist details
        String actualChecklistId = response.jsonPath().getString("id");
        String actualChecklistName = response.jsonPath().getString("name");

        // Step 6: Assert values against expected data
        Assert.assertEquals(actualChecklistId, RQ_013_CreateChecklistRequest.getChecklistId(), "Checklist ID mismatch!");
        Assert.assertEquals(actualChecklistName, RQ_013_CreateChecklistRequest.getChecklistName(), "Checklist Name mismatch!");

        // Step 7: Retrieve the card details to validate its association
        getCardRequest = new RQ_010_GetCardRequest();
        response = getCardRequest.getCard();

        // Step 8: Validate response is not null
        Assert.assertNotNull(response, "Response is null. Card retrieval might have failed.");

        // Step 9: Validate HTTP status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code. Card retrieval failed.");

        // Step 10: Extract and validate the associated List ID & Board ID
        String actualListId = response.jsonPath().getString("idList");
        String actualBoardId = response.jsonPath().getString("idBoard");

        // Step 11: Assert values against expected data
        Assert.assertEquals(actualListId, RQ_005_CreateListRequest.getListId(), "List ID mismatch!");
        Assert.assertEquals(actualBoardId, RQ_001_CreateBoardRequest.getBoardId(), "Board ID mismatch!");
    }
}
