<div align="center">

![image](https://github.com/x-normalize/Demo-Spree-Project/assets/94692820/5f57b5de-dd6f-4124-ab46-f7c49978971b)

# Spree E-Commerce Project 

</div>

## I. Project Description
This project is an end-to-end testing of the Spree E-commerce platform, a headless open-source solution designed for global brands. It offers a comprehensive suite of features to provide a seamless online shopping experience. The platform includes robust user management with secure password mechanisms, efficient product management, and a user-friendly shopping cart. It also provides a streamlined checkout process with support for multiple payment gateways. With support for multiple languages and currencies, the platform caters to a global audience.

## II. Used Technologies

The project utilizes a stack of various technologies and tools, including:

- **Testing Framework**: Java with Selenium WebDriver
- **API Testing**: Postman, RestAssured, and Newman (Command-line collection runner for Postman)
- **Issue Tracking**: Jira, Xray
- **Build and Dependency Management**: Apache Maven
- **Scripts for Execution of Automated Tests**: MVN Test (Command-line collection runner for Rest Assured and Selenium), Batch and Bash files, Maven Surefire Report.

## III. Prerequisites Tools:
- Jira
- Postman v10.18.9
- IntelliJ IDEA 2023.3
- Selenium WebDriver

## IV. Instructions:
1. Clone repository
2. Open Framework as an IntelliJ IDEA Project
3. Build
4. Navigate to TestAutomationFramework/src/test/java
5. Run tests from the rest-assured folder
6. Run tests from the selenium folder
   
### Running Tests via Scripts:
You can also run the REST Assured and Selenium tests through the included scripts:

1. For Windows users, use the run-selenuim-and-rest-tests.bat file.
2. For Unix-based systems, use the run-selenium-and-rest-tests-bash.sh file.

## V. Resources
-  <a href= "https://github.com/x-normalize/Spree-Project">Spree project repo<a/>
-  <a href= "https://github.com/x-normalize/Spree-Project/tree/main/Documentation"> Test Plan<a/>
-  <a href= "https://github.com/x-normalize/Spree-Project/tree/main/Documentation"> Exploratory testing<a/>
-  <a href= "https://github.com/x-normalize/Spree-Project/tree/main/Documentation"> Test Cases<a/>
-  <a href= "https://spreecommerce-team10.atlassian.net/jira/software/c/projects/SDP/issues"> Logged bugs and issues in Jira<a/>
-  <a href= "https://github.com/x-normalize/Spree-Project/tree/main/Reports"> Test Plans Metrics Report<a/>
-  <a href= "https://github.com/x-normalize/Spree-Project/tree/main/Reports"> Newman report for Postman<a/> 
-  <a href= "https://github.com/x-normalize/Spree-Project/tree/main/Reports"> Selenium And RestAssured Reports<a/>
-  <a href= "https://github.com/x-normalize/Spree-Project/tree/main/Documentation"> Final Test Report<a/>

## VI. Author
- Yordan Nikolov

## VII. Features

### User Management

- **Registration, Login, and Account Management**: The platform provides a fully functional storefront that fits well with most use cases and offers a mobile-friendly experience.
- **Password Recovery/Reset Mechanisms**: Standard password recovery and reset mechanisms are included to ensure user security.

### Product Management

- **Adding, Editing, and Deleting Products**: The platform allows for easy management of products.
- **Product Categorization and Filtering**: Product discovery features with customizable product filters are included for easy product listing browsing.

### Shopping Cart

- **Adding/Removing Items from the Shopping Cart**: Users can easily manage their shopping cart.
- **Total Amount Calculation**: The total amount of items in the shopping cart is automatically calculated.

### Checkout Process

- **Various Steps in the Checkout Process**: The platform provides a streamlined checkout process.
- **Payment Method Verification**: All major cards are accepted through one of the 144 supported payment gateways.

### Localization

- **Language Switching Functionality**: Multiple languages are supported.
- **Currency Display and Conversion**: Multiple currencies are supported.

### Search Functionality

- **Search Different Products**: Combined with Elasticsearch, a powerful search engine, the platform provides amazing user experiences at high speeds, even with millions of SKUs.
