# 🚀 Trello SHAFT API Automation Testing Project 

## 📖 Project Overview
The **Trello API Automation Testing Project** is designed to automate and validate key functionalities of the **Trello API** using **SHAFT Engine**. This project ensures that board, list, card, checklist, and check-item operations are functioning as expected, enhancing the efficiency and reliability of Trello’s API.

## 🎯 Project Objective
The primary objectives of this API test automation framework are to:
- ✅ **Automate CRUD operations** for boards, lists, cards, checklists, and check-items.
- ✅ **Ensure API reliability and functional correctness** by validating response status codes and data integrity.
- ✅ **Improve test efficiency** by reducing manual efforts and leveraging automation for repeated API validations.
- ✅ **Enhance reporting and debugging** through **Allure Reports**, providing clear insights into test execution.
- ✅ **Follow best practices** for scalable and maintainable API test automation.

## 🛠️ Technologies and Tools
This project is built using the following technologies and tools:

| Technology/Tool         | Purpose |
|------------------------|---------|
| **Java**              | Core programming language for automation scripts |
| **SHAFT Engine**      | Robust test automation framework for API validation |
| **TestNG**            | Test framework for execution control and reporting |
| **Allure Reports**    | Provides detailed and visualized test execution reports |
| **Maven**             | Dependency and project build management |
| **JSON Assertions**   | Ensures expected vs actual API response validation |

## 📂 Project Structure
The project follows a well-organized structure to ensure scalability and maintainability:
```
TrelloAPI/
│── src/
│   ├── main/
│   ├── test/
│   │   ├── java/
│   │   │   ├── apiRequests/                  # API request classes
│   │   │   │   ├── Board Requests
│   │   │   │   ├── List Requests
│   │   │   │   ├── Card Requests
│   │   │   │   ├── Checklist Requests
│   │   │   │   ├── Check-item Requests
│   │   │   ├── apiTest/                      # API test cases
│   │   │   │   ├── Board Tests
│   │   │   │   ├── List Tests
│   │   │   │   ├── Card Tests
│   │   │   │   ├── Checklist Tests
│   │   │   │   ├── Check-item Tests
│── resources/
│   ├── test_data.json                        # JSON test data for API requests
│   ├── .env                                  # API keys, tokens, and base URLs
│── allure-results/                           # Allure reports directory
│── pom.xml                                   # Maven dependency management file
│── README.md                                 # Project documentation
```

## 🌟 Key Features

- **Board Management**:
    - Create a new board.
    - Retrieve board details.
    - Update board information.
    - Delete a board.

- **List Management**:
    - Create a new list within a board.
    - Retrieve lists from a board.
    - Update list properties.
    - Delete a list.

- **Card Management**:
    - Create a new card within a list.
    - Retrieve card details.
    - Update card properties.
    - Delete a card.

- **Checklist Management**:
    - Add a checklist to a card.
    - Retrieve checklist details.
    - Update checklist properties.
    - Delete a checklist.

- **Check-item Management**:
    - Create a new check-item within a checklist.
    - Retrieve check-item details.
    - Update check-item properties.
    - Delete a check-item.


This project represents a **significant step in API test automation**, integrating **SHAFT Engine and TestNG** to streamline API validation. Through the implementation of **best practices in API automation**, the project aims to:

- ✅ **Automate Trello API functionalities**, covering board, list, card, checklist, and check-item operations.
- ✅ **Enhance API test execution efficiency** with structured test cases, reusable components, and assertions.
- ✅ **Provide insightful test reporting** with Allure Reports, ensuring clear visibility of test results and failures.
- ✅ **Ensure API stability and compliance** by handling different request/response scenarios and error cases.

🔹 I am excited about applying these skills to future projects to drive quality and efficiency! Let’s connect and exchange ideas about enhancing API testing practices.

## 🎥 Project Demo
Check out the full project demonstration video here:  
📺 **[Watch Demo](https://drive.google.com/file/d/1QwJU-hB0KDav5RvH4OxbWBfzRAjfGlbe/view?usp=sharing)**

## 📩 Contact
For any inquiries, collaborations, or contributions, feel free to reach out:

- **👤 Tester Name:** Mustafa Ibrahim Mostafa
- **📧 Email:** mustafa.ibrahim.qa@gmail.com
- **🔗 LinkedIn:** [MyProfile](https://www.linkedin.com/in/mostafa-ibrahim-mostafa/)
- **📞 Phone:** (+02) 01005747258

---
**📌 Note:** This project is actively maintained, and contributions are welcome! Feel free to fork the repository, raise issues, or submit pull requests to enhance the framework. 🚀
