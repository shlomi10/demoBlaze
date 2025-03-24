# Selenium Page Object Model Framework

![Selenium with Java](https://img.shields.io/badge/Selenium-Java-green)
![TestNG](https://img.shields.io/badge/TestNG-Framework-orange)
![Allure Reports](https://img.shields.io/badge/Allure-Reports-blue)
![Maven](https://img.shields.io/badge/Apache-Maven-red)

A robust and scalable Selenium test automation framework built with Page Object Model design pattern using Java, Maven, and TestNG. This framework demonstrates automated testing for a demo e-commerce website with features like user registration, login, product selection, cart operations, and checkout.

## 📋 Table of Contents

- [✨ Features](#features)
- [🛠️ Technology Stack](#technology-stack)
- [🏗️ Architecture](#architecture)
- [📁 Project Structure](#project-structure)
- [🧩 Key Components](#key-components)
- [🚀 Getting Started](#getting-started)
- [🌐 Cross-Browser Testing](#cross-browser-testing)
- [⚙️ Running Tests](#running-tests)
- [📊 Test Reports](#test-reports)
- [💯 Best Practices Implemented](#best-practices-implemented)
- [👥 Contributing](#contributing)  

## Features

- **Page Object Model**: Clean separation of page elements and actions
- **Cross-Browser Testing**: Support for different browsers via TestNG parameters
- **Data Generation**: Dynamic test data creation using Java Faker
- **Comprehensive Reporting**: Detailed reports with Allure including screenshots on failures
- **Fail-Safe Testing**: Robust exception handling and recovery mechanisms
- **Modular Design**: Reusable components for UI interactions
- **Custom Waits**: Smart waiting mechanisms for better stability

## Technology Stack

| Component               | Technology                       |
|-------------------------|----------------------------------|
| Programming Language    | Java 20                          |
| Testing Framework       | TestNG                           |
| Build Tool              | Maven                            |
| Reporting               | Allure Reports                   |
| Web Automation          | Selenium WebDriver 4.30          |
| Test Data Generation    | Java Faker                       |
| IDE                     | IntelliJ IDEA                    |
| Version Control         | Git                              |
| CI/CD Integration       | Ready for Jenkins/GitHub Actions |

## Architecture

This framework is designed using the Page Object Model pattern with the following layers:

1. **Test Layer**: Contains test classes with TestNG annotations
2. **Page Layer**: Contains page objects representing web pages
3. **Utilities Layer**: Contains reusable functions and helpers
4. **Configuration Layer**: Environment variables and test configurations

## Project Structure

```
selenium-pom-framework/
├── src/
│   ├── main/java/
│   │   ├── pages/            # Page Objects
│   │   └── utilities/        # Helper Utilities
│   └── test/
│       ├── java/tests/       # Test Cases
│       └── resources/
│           ├── .env          # Environment Configuration
│           └── testng.xml    # TestNG Configuration
├── Reports/
│   ├── allure-results/       # Allure Report Data
│   └── screenshots/          # Failure Screenshots
└── pom.xml                   # Maven Dependencies
```

## Key Components

### Pages Package
Contains page objects for each page in the application:
- `HomePage`: Entry point and navigation
- `LoginPage`: Authentication functionality
- `ProductPage`: Product details and actions
- `CartPage`: Shopping cart operations
- `PlaceOrderPage`: Checkout process
- `ConfirmationWindow`: Order confirmation

### Utilities Package
- `BasePageFunctions`: Core UI interaction methods
- `EnvReader`: Environment configuration reader

### Tests Package
- `BaseTest`: Test configuration and setup
- `MainRunnerTest`: Test suite with annotated test cases
- `ScreenshotListener`: Screenshot capture on test failures

## Getting Started

### Prerequisites
- Java JDK 20 or higher
- Maven 3.8 or higher
- Chrome, Firefox, or Edge browser

### Installation

1. Clone the repository:
```bash
git clone https://github.com/shlomi10/demoBlaze.git
cd selenium-pom-framework
```

2. Install dependencies:
```bash
mvn clean install
```

3. Configure environment variables:
   Create a `.env` file in `src/test/resources/` with the following:
```
SITE_URL=https://www.demoblaze.com/
USERNAME=your_USER_name (test)
PASSWORD=your_password (test)
```

## Cross-Browser Testing

The framework supports running tests across multiple browsers using Selenium 4's built-in driver management capabilities.

### Supported Browsers
- Chrome
- Firefox
- Edge

### Running Tests on Different Browsers

To run tests on all configured browsers:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

To run tests on a specific browser, create browser-specific XML files:
```bash
# For Chrome only
mvn clean test -D suiteXmlFile=testng.xml -D browser=chrome
```
```bash
# For Firefox only
mvn clean test -D suiteXmlFile=testng.xml -D browser=firefox
```
```bash
# For edge only
mvn clean test -D suiteXmlFile=testng.xml -D browser=edge
```

## Running Tests

### Using Maven
```bash
mvn clean test
```

### Using TestNG XML
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run Specific Test
```bash
mvn clean test -Dtest=MainRunnerTest#loginTest
```

## Test Reports

### Generate Allure Report
After running tests, generate the report with:
```bash
allure serve reports/allure-results
```

This will open the interactive Allure report in your default browser showing:
- Test execution summary
- Detailed step information
- Screenshots of failures
- Test execution timeline

## Best Practices Implemented

- **Code Reusability**: Common functions extracted to base classes
- **Explicit Waits**: No hardcoded sleeps, only conditional waits
- **Failure Recovery**: Exception handling for robust test execution
- **Parameterization**: Test data and browser options through TestNG
- **Screenshot Capture**: Automatic capture on test failures
- **Clean Reporting**: Detailed Allure reports with test categorization
- **Random Data Generation**: Dynamic test data using Faker library
- **Annotation-Driven Metadata**: Tests decorated with descriptive annotations

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
