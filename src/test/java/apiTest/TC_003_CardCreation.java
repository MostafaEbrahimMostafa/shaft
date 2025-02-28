package apiTest;

import apiRequests.RQ_001_CreateBoardRequest;
import apiRequests.RQ_005_CreateListRequest;
import apiRequests.RQ_009_CreateCardRequest;
import apiRequests.RQ_010_GetCardRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to validate Trello card creation.
 */
public class TC_003_CardCreation {

    private RQ_009_CreateCardRequest createCardRequest;
    private RQ_010_GetCardRequest getCardRequest;
    private Response response;

    @Feature("F003_Trello Card Management")
    @Story("TC_003_Card Creation")
    @Description("Ensure that a new Trello card is successfully created via API and its details are validated.\n" +
            "Test Steps:\n" +
            "1. Sends a POST request to create a new card under a specified list.\n" +
            "2. Retrieves the created card details via a GET request.\n" +
            "3. Validates that the card ID, name, list ID, and board ID match expected values.\n" +
            "4. Confirms successful card creation by verifying HTTP response codes.")
    @Test
    public void validateCardCreation() {
        // Step 1: Initialize request objects for card creation and retrieval
        createCardRequest = new RQ_009_CreateCardRequest();
        getCardRequest = new RQ_010_GetCardRequest();

        // Step 2: Send request to create a new card
        createCardRequest.createCard();

        // Step 3: Retrieve the created card's details
        response = getCardRequest.getCard();

        // Step 4: Validate response is not null
        Assert.assertNotNull(response, "Response is null. Card creation might have failed.");

        // Step 5: Validate HTTP status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code. Card retrieval failed.");

        // Step 6: Extract and validate card details
        String actualCardId = response.jsonPath().getString("id");
        String actualCardName = response.jsonPath().getString("name");
        String actualListId = response.jsonPath().getString("idList");
        String actualBoardId = response.jsonPath().getString("idBoard");

        // Step 7: Assert values against expected data
        Assert.assertEquals(actualCardId, RQ_009_CreateCardRequest.getCardId(), "Card ID mismatch!");
        Assert.assertEquals(actualCardName, RQ_009_CreateCardRequest.getCardName(), "Card name mismatch!");
        Assert.assertEquals(actualListId, RQ_005_CreateListRequest.getListId(), "List ID mismatch!");
        Assert.assertEquals(actualBoardId, RQ_001_CreateBoardRequest.getBoardId(), "Board ID mismatch!");
    }
}
