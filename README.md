# Rest Service Test Framework

Welcome to the **Rest Service Test Framework**! This repository provides a robust and modular blueprint for testing RESTful APIs using Java and Rest Assured. It incorporates best practices for clean code, modularity, and seamless integration with modern tools like reporting frameworks and CI/CD pipelines.

---

## 📂 Repository Contents

### 1. **Java Rest Assured Test Framework**
- A sample project blueprint for testing REST APIs.
- Implements **best practices**:
  - Clean and modular code structure.
  - Feature-rich reporting integration (Allure, Extent Reports).
  - CI/CD pipeline compatibility for automated testing.

### 2. **Postman Collection**
- A sample Postman collection for the same project.
- Implements **best practices**:
  - Assertions for validating API responses.
  - Use of variables for dynamic testing.
  - CI/CD integration for automated API testing.

---

## 🛠️ Application Under Test (AUT)
The application under test is a RESTful service project. You can find the backend source code here:

[Spring Blog App Backend](https://github.com/shah-shivam-410/Spring-blog-app-backend)

---

## 🚀 Key Features

- **Modular Design**: Reusable methods for HTTP operations (GET, POST, PUT, DELETE) to ensure maintainability and scalability.
- **Dynamic Data Handling**: Randomized data generation for testing edge cases.
- **Schema Validation**: JSON schema validation for response structure.
- **Comprehensive Reporting**: Integrated with Allure and Extent Reports for detailed test execution insights.
- **CI/CD Ready**: Easily integrable with Jenkins, GitHub Actions, or other CI/CD tools.
- **AI-Powered Development**: Leveraged **GitHub Copilot** extensively to accelerate development, ensure clean code, and implement best practices.

---

## 📖 How to Use

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/rest-service-test-framework.git
   ```

2. **Set Up Dependencies**:
   - Ensure you have **Java 11+** installed.
   - Install **Maven** and run the following command to install dependencies:
     ```bash
     mvn clean install
     ```

3. **Run Tests**:
   - Execute all tests:
     ```bash
     mvn test
     ```
   - Generate Allure reports:
     ```bash
     mvn allure:serve
     ```

4. **Import Postman Collection**:
   - Import the provided Postman collection into Postman.
   - Configure environment variables for dynamic testing.

---

## 🤖 How GitHub Copilot Helped

This project was built with significant assistance from **GitHub Copilot**, which:
- **Accelerated Development**: Suggested reusable methods for HTTP operations, reducing boilerplate code.
- **Improved Code Quality**: Provided intelligent suggestions for clean and modular code structure.
- **Enhanced Productivity**: Helped implement best practices for schema validation, reporting, and CI/CD integration.
- **Dynamic Data Handling**: Assisted in generating randomized data for testing edge cases.

By leveraging **GitHub Copilot**, we were able to focus on the core logic and testing strategies while automating repetitive tasks. This resulted in a faster, more efficient development process.

---

## 📊 Reporting

- **Allure Reports**: Provides detailed insights into test execution with screenshots, logs, and metrics.
- **Extent Reports**: Offers visually appealing HTML reports for test results.

---

## 🤝 Contributing

We welcome contributions to improve this framework! Feel free to fork the repository, create a feature branch, and submit a pull request.

---

## 📜 License

This project is licensed under the MIT License. See the [LICENSE](http://_vscodecontentref_/1) file for details.

---

## 📧 Contact

For any queries or suggestions, feel free to reach out:

- **Author**: Shivam Shah
- **Email**: shah.shivam@example.com
- **GitHub**: [shah-shivam-410](https://github.com/shah-shivam-410)


