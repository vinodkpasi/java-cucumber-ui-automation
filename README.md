# Selenium with Cucumber Automation Framework

A Java-based web UI automation framework built with **Selenium WebDriver** and **Cucumber BDD**. The framework is designed around readable Gherkin scenarios, reusable Java step definitions, Page Object Model practices, centralized browser/configuration handling, and Maven-based execution.

## Overview

```text
Feature File (.feature)
        |
        v
Cucumber Scenario
        |
        v
Step Definitions
        |
        v
Page Objects / UI Actions
        |
        v
Selenium WebDriver
        |
        v
Web Application
```

Cucumber feature files describe business behavior in Gherkin, while Java step definitions translate those steps into executable Selenium actions.

## Technology Stack

| Technology | Purpose | Version |
|---|---|---|
| Java | Programming language | See `pom.xml` |
| Selenium WebDriver | Browser automation | 3.141.59 |
| Cucumber | BDD / Gherkin | 1.2.5 |
| TestNG | Test execution | 7.7.1 |
| WebDriverManager | Browser driver management | 4.3.1 |
| Maven | Build and dependency management | Project configured |

## Key Features

- BDD automation using Cucumber/Gherkin
- Selenium WebDriver browser automation
- Page Object Model support
- Reusable step definitions
- Cucumber hooks for setup and teardown
- Maven-based execution
- TestNG integration
- Configurable browser execution
- WebDriver management
- Human-readable feature files
- Cucumber reporting
- Foundation for CI/CD integration

## Project Structure

The repository structure is:

```text
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pages/
│   │   │   │   ├── EmailPage.java
│   │   │   │   ├── LoginPage.java
│   │   │   ├── utlity/
│   │   │   │   ├── Constants.java
│   │   │   │   ├── Utility.java
│   ├── test/
│   │   ├── java/
│   │   │   ├── step_definations/
│   │   │   │   ├── Hooks.java
│   │   │   │   ├── Run_Tests.java
│   │   │   │   ├── StepDefination.java
│   │   ├── jmeter/
│   │   │   ├── Test Plan.jmx
│   │   ├── resources/
│   │   │   ├── driver/
│   │   │   │   ├── geckodriver.exe
│   │   │   ├── feature/
│   │   │   │   ├── incubyte.feature
├── testng.xml
```

The exact package and file names in the repository are the source of truth. Common responsibilities are:

| Component | Responsibility |
|---|---|
| Feature files | Business-readable Gherkin scenarios |
| Step definitions | Maps Gherkin steps to Java code |
| Page objects | Locators and UI actions |
| Hooks | Browser setup and cleanup |
| Runner | Cucumber execution configuration |
| Utilities | Shared framework helpers |
| Resources | Configuration and test data |
| `pom.xml` | Maven dependencies and build configuration |

## Prerequisites

Install:

1. JDK compatible with the version configured in `pom.xml`
2. Apache Maven
3. Google Chrome, Mozilla Firefox, or Microsoft Edge
4. IntelliJ IDEA or Eclipse (optional)

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

## Clone the Repository

```bash
git clone <repository-url>
cd SeleniumwithCucumber-main
```

## Install Dependencies

```bash
mvn clean install
```

For normal test execution:

```bash
mvn clean test
```

Maven downloads dependencies declared in `pom.xml`.

## Running Cucumber Tests

### Run the complete suite

```bash
mvn clean test
```

### Run from an IDE

Locate the Cucumber runner class in the project and execute it using the runner type configured by the project.

A typical Cucumber runner contains configuration similar to:

```java
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepdefinitions",
    plugin = {
        "pretty",
        "html:target/cucumber-report.html"
    }
)
```

Use the actual `features`, `glue`, and runner settings present in this repository.

## Cucumber / BDD Workflow

A typical scenario follows this pattern:

```gherkin
Feature: Login

  Scenario: Successful login
    Given the user opens the application
    When the user enters valid credentials
    And the user clicks the login button
    Then the user should be logged in successfully
```

The implementation should keep the layers separated:

```text
Gherkin
   |
   v
Step Definition
   |
   v
Page Object
   |
   v
Selenium WebDriver
```

This keeps business behavior readable and UI implementation maintainable.

## Page Object Model

Page objects encapsulate:

- Locators
- Page-specific actions
- Reusable UI operations
- Page-level validations

Example:

```java
public class LoginPage {

    private WebDriver driver;

    private By username = By.id("username");
    private By password = By.id("password");
    private By loginButton = By.id("login");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pwd) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(loginButton).click();
    }
}
```

Step definitions can then remain concise:

```java
@When("the user logs in with valid credentials")
public void loginWithValidCredentials() {
    loginPage.login(username, password);
}
```

## Hooks

Cucumber hooks are used for scenario lifecycle management.

### `@Before`

Typical responsibilities:

- Initialize WebDriver
- Configure the browser
- Open the application
- Initialize page objects

### `@After`

Typical responsibilities:

- Capture a screenshot if the scenario fails
- Close the browser
- Release WebDriver resources

Example:

```java
@Before
public void setUp() {
    driver = createDriver();
}

@After
public void tearDown(Scenario scenario) {
    if (scenario.isFailed()) {
        // Capture screenshot
    }

    driver.quit();
}
```

## Configuration

Environment-specific values should be externalized wherever possible.

Example:

```properties
browser=chrome
baseUrl=https://example.com
username=<test-user>
password=<test-password>
```

### Security

**Never commit real credentials to Git.**

Use:

- Environment variables
- GitHub Actions Secrets
- Jenkins Credentials
- Azure DevOps secret variables
- A dedicated secrets manager

Example:

```bash
mvn clean test -Dbrowser=chrome
```

Use the property names defined by the project's actual configuration classes.

## Browser Execution

The framework can be extended/configured to support:

```text
chrome
firefox
edge
```

Example:

```bash
mvn clean test -Dbrowser=chrome
```

If the project uses a different configuration parameter, use that parameter instead.

## WebDriver Management

When WebDriverManager is configured, browser driver setup can be automated.

Typical Chrome setup:

```java
WebDriverManager
    .chromedriver()
    .setup();

WebDriver driver = new ChromeDriver();
```

Firefox:

```java
WebDriverManager
    .firefoxdriver()
    .setup();
```

Edge:

```java
WebDriverManager
    .edgedriver()
    .setup();
```

## Test Data

Test data should be separated from automation implementation.

Possible approaches:

- Properties
- JSON
- Excel
- CSV
- Environment variables
- Java test-data builders
- API-generated data
- Database-generated data

Sensitive values should come from secure CI/CD variables.

## Reporting

Cucumber supports several reporting formats:

```text
HTML
JSON
JUnit XML
```

Example:

```java
plugin = {
    "pretty",
    "html:target/cucumber-report.html"
}
```

After execution, inspect the generated files under:

```text
target/
```

The actual report path depends on the project's runner configuration.

## Failure Screenshots

For failed scenarios, screenshots can be attached directly to the Cucumber scenario.

Example:

```java
if (scenario.isFailed()) {
    byte[] screenshot =
        ((TakesScreenshot) driver)
            .getScreenshotAs(OutputType.BYTES);

    scenario.attach(
        screenshot,
        "image/png",
        "Failure Screenshot"
    );
}
```

This provides valuable visual evidence during local and CI execution.

## Maven Commands

### Clean

```bash
mvn clean
```

### Compile

```bash
mvn compile
```

### Run tests

```bash
mvn test
```

### Clean and test

```bash
mvn clean test
```

### Package without tests

```bash
mvn clean package -DskipTests
```

## CI/CD Integration

This framework can be integrated with:

- GitHub Actions
- Jenkins
- GitLab CI/CD
- Azure DevOps

Recommended pipeline:

```text
Checkout
   |
   v
Set up JDK
   |
   v
Install Maven dependencies
   |
   v
Execute Cucumber tests
   |
   v
Generate reports
   |
   v
Publish reports/screenshots
```

### Example GitHub Actions Workflow

```yaml
name: Selenium Cucumber Tests

on:
  push:
  pull_request:

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout
        uses: actions/checkout@v4

      - name: Set up Java
        uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '17'
          cache: maven

      - name: Run Cucumber tests
        run: mvn clean test

      - name: Upload test reports
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: cucumber-reports
          path: target/
```

Adjust the Java version and report path to match the repository.

## Best Practices

### Feature Files

Keep scenarios:

- Business-readable
- Short
- Focused on behavior
- Independent
- Free from low-level Selenium implementation details

Avoid:

```gherkin
When I find element with id username
And I call sendKeys
And I find element with id password
```

Prefer:

```gherkin
When the user enters valid credentials
```

### Step Definitions

Keep step definitions thin.

Avoid large amounts of Selenium code in step classes.

Prefer:

```text
Step Definition
      |
      v
Page Object
      |
      v
Selenium
```

### Page Objects

Page objects should contain UI implementation details while scenarios should describe business intent.

## Synchronization

Dynamic web applications should use explicit waits where appropriate.

Example:

```java
WebDriverWait wait =
    new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(
    ExpectedConditions.visibilityOfElementLocated(locator)
);
```

Avoid relying on unnecessary hard-coded sleeps such as:

```java
Thread.sleep(5000);
```

## Cucumber Tags

Tags can separate smoke and regression suites.

Example:

```gherkin
@smoke
Scenario: Successful login
```

```gherkin
@regression
Scenario: Invalid password
```

A common strategy is:

```text
@smoke       -> Fast build validation
@regression  -> Full regression
```

## Enterprise Execution Model

```text
                    +----------------+
                    |  CI Pipeline   |
                    +-------+--------+
                            |
                 +----------+----------+
                 |                     |
                 v                     v
            Smoke Suite          Regression Suite
                 |                     |
                 +----------+----------+
                            |
                            v
                    Cucumber Runner
                            |
                            v
                    Step Definitions
                            |
                            v
                      Page Objects
                            |
                            v
                   Selenium WebDriver
                            |
                            v
                  Browser / Grid / Cloud
```

## Recommended Improvements

For enterprise-scale automation, consider adding:

1. Environment-specific configuration
2. Secure credential injection
3. Thread-safe WebDriver management
4. Parallel Cucumber execution
5. Explicit wait utilities
6. Driver factory
7. Test data factories
8. API utilities
9. Database validation
10. Allure or enhanced reporting
11. CI/CD pipeline configuration
12. Docker execution
13. Selenium Grid
14. BrowserStack/cloud browser execution
15. Failure screenshot/video capture
16. Controlled retry strategy
17. Flaky-test tracking
18. Standardized logging

## Git Ignore Recommendations

Generated/local files generally should not be committed:

```text
target/
.idea/
*.iml
.classpath
.project
.settings/
test-output/
screenshots/
*.log
```

Use a `.gitignore` appropriate for the IDE and CI environment.

## Troubleshooting

### `mvn` is not recognized

Install Maven and add its `bin` directory to the system `PATH`.

### Java version mismatch

Run:

```bash
java -version
mvn -version
```

Then compare the result with the Java version configured in `pom.xml`.

### WebDriver errors

Check:

- Browser is installed
- Browser version is supported
- WebDriverManager dependency is available
- Machine has network access if driver download is required

### Element not found

Check:

- Locator
- Page load state
- iframe context
- Explicit waits
- Application state
- Dynamic DOM changes

### Undefined Cucumber step

Check:

- Gherkin step text
- Step definition annotation
- Runner `glue`
- Feature path
- Package structure

### No tests are executed

Check:

1. Cucumber runner exists.
2. `@CucumberOptions` points to the correct feature directory.
3. `glue` points to the correct step-definition package.
4. Runner type matches Maven/TestNG/JUnit configuration.
5. Feature files use the `.feature` extension.

## Adding a New Feature

1. Create a `.feature` file.
2. Add business-focused scenarios.
3. Add or reuse step definitions.
4. Create/update page objects.
5. Add reusable waits/utilities when needed.
6. Execute locally.
7. Run the complete suite.
8. Review reports and screenshots.
9. Commit the required source/configuration changes.

## Quick Start

```bash
git clone <repository-url>
cd SeleniumwithCucumber-main
mvn clean test
```

Then inspect:

```text
target/
```

for generated test output and reports.

## Future Enhancements

A stronger enterprise version can add:

- Selenium 4 best practices
- ThreadLocal WebDriver
- Parallel execution
- Environment selection (`dev`, `qa`, `stage`, `prod`)
- Secure secret injection
- Headless execution
- Docker
- Selenium Grid
- BrowserStack
- Allure reporting
- API + UI workflows
- Database validation
- CI/CD
- Test-data factories
- Flaky-test analytics

## Author

**Selenium + Cucumber Web UI Automation Framework**

Built using Java, Selenium WebDriver, Cucumber, TestNG, Maven, Page Object Model, and reusable automation components.
