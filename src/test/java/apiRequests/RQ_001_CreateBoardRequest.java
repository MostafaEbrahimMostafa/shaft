package apiRequests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * API request class for creating a new board using Trello API.
 */
public class RQ_001_CreateBoardRequest {

    By board = By.cssSelector("li.boards-page-board-section-list-item > a");

    // Static variables to store API credentials and board details
    private static String apiKey;
    private static String token;
    private static String baseUrl;
    private static String boardId;
    private static String boardName;

    // API object for making requests
    private SHAFT.API api;

    /**
     * Constructor to initialize API credentials from environment variables.
     */
    public RQ_001_CreateBoardRequest() {
        Dotenv dotenv = Dotenv.load(); // Load environment variables
        apiKey = dotenv.get("API_KEY");
        token = dotenv.get("TOKEN");
        baseUrl = dotenv.get("BASE_URL");
        api = new SHAFT.API(baseUrl); // Initialize API object
    }

    /**
     * Creates a new board in Trello with predefined settings.
     * Handles API key, token, and board configurations.
     */
    public void createBoard() {
        List<List<Object>> queryParams = new ArrayList<>();

        // Setting query parameters for the request
        queryParams.add(List.of("name", "API Testing"));
        queryParams.add(List.of("key", apiKey));
        queryParams.add(List.of("token", token));
        queryParams.add(List.of("prefs_background", "orange"));

        try {
            // Sending POST request to create a new board
            api.post("/1/boards/")
                    .setParameters(queryParams, RestActions.ParametersType.QUERY)
                    .setContentType(ContentType.JSON)
                    .perform();

            // Extracting response values
            boardId = api.getResponseJSONValue("id");
            boardName = api.getResponseJSONValue("name");

            // Logging success message
            System.out.println("Board Created Successfully: " + boardName + " (ID: " + boardId + ")");
        } catch (Exception e) {
            System.err.println("Error creating board: " + e.getMessage());
        }
    }

    /**
     * Retrieves the ID of the created board.
     * @return Board ID as a string.
     */
    public static String getBoardId() {
        return boardId;
    }

    /**
     * Retrieves the name of the created board.
     * @return Board name as a string.
     */
    public static String getBoardName() {
        return boardName;
    }

    /**
     * Retrieves the API Key of the created board.
     * @return Key as a string.
     */
    public static String getApiKey() {
        return apiKey;
    }

    /**
     * Retrieves the Base Url of the created board.
     * @return Url as a string.
     */
    public static String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Retrieves the token of the created board.
     * @return token as a string.
     */
    public static String getToken() {
        return token;
    }
}
