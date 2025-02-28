package apiTest;

import apiRequests.RQ_001_CreateBoardRequest;
import apiRequests.RQ_005_CreateListRequest;
import apiRequests.RQ_006_GetListRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class to validate Trello list creation.
 */
public class TC_002_ListCreation {

    private RQ_005_CreateListRequest createListRequest;
    private RQ_006_GetListRequest getListRequest;
    private Response response;

    @Feature("F002_Trello List Management")
    @Story("TC_002_List Creation")
    @Description("Ensure that a new Trello list is successfully created via API and its details are validated.\n" +
            "Test Steps:\n" +
            "1. Sends a POST request to create a new list under a specified board.\n" +
            "2. Retrieves the created list details via a GET request.\n" +
            "3. Validates that the list ID, name, and associated board ID match expected values.\n" +
            "4. Confirms successful list creation by verifying HTTP response codes.")
    @Test
    public void validateListCreation() {
        // Step 1: Initialize request objects for list creation and retrieval
        createListRequest = new RQ_005_CreateListRequest();
        getListRequest = new RQ_006_GetListRequest();

        // Step 2: Send request to create a new list
        createListRequest.createList();

        // Step 3: Retrieve the created list's details
        response = getListRequest.getList();

        // Step 4: Validate response is not null
        Assert.assertNotNull(response, "Response is null. List creation might have failed.");

        // Step 5: Validate HTTP status code
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code. List retrieval failed.");

        // Step 6: Extract and validate list details
        String actualListId = response.jsonPath().getString("id");
        String actualListName = response.jsonPath().getString("name");
        String actualBoardId = response.jsonPath().getString("idBoard");

        // Step 7: Assert values against expected data
        Assert.assertEquals(actualListId, RQ_005_CreateListRequest.getListId(), "List ID mismatch!");
        Assert.assertEquals(actualListName, RQ_005_CreateListRequest.getListName(), "List name mismatch!");
        Assert.assertEquals(actualBoardId, RQ_001_CreateBoardRequest.getBoardId(), "Board ID mismatch!");
    }
}
