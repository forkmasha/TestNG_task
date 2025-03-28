# Test Automation Framework for Hardcore Task

This project involves building a test automation framework based on the Hardcore task from the WebDriver course.

## Framework Features

1. **WebDriverManager**: Manage drivers for different browsers.
2. **PageObject / PageFactory**: Abstract pages for better maintainability.
3. **Business Model**: Business objects for dedicated entities.
4. **Property Files**: Test data for different environments (at least two).
5. **XML Suites**: Separate suites for Smoke and Regression tests.
6. **Failure Screenshots**: Capture screenshots on test failure. Logs should include information about the saved screenshot.
7. **Parameter Flexibility**: Support for parameters like browser, test suite, and environment to enable CI integration.

## Logging

- Use a logging library (e.g., Log4j or similar) to log every step.
- Configure logs in an informative format.
- Demonstrate usage of different log levels (e.g., `DEBUG`, `INFO`, `ERROR`).
- Logs should:
    - Be written to the console by default.
    - Be saved to a file, with a new file created daily.

## Test Results

- Test results should be visualized on job graphics.
- Screenshots should be archived as artifacts.

## Bonus Task

- Implement part of the test scenario using a Selenium wrapper/framework (e.g., Selenide, Serenity, JDI, HtmlElements, etc.).
- Highlight elements during test execution. Any action performed on an element should highlight it.

## Acceptance Criteria

The framework must meet all the points outlined in the home task to be considered complete.

## Run tests with command
 mvn test "-Dsurefire.suiteXmlFiles=(smoke | regression).xml" -Dbrowser=(EDGE | CHROME | FIREFOX) -Denv=(dev | prod)

-Denv=<value>: Passes the env parameter to your tests.
-Dtest=<class>: Runs a specific test class.
-Dtest=<class>#<method>: Runs a specific test method.
-Dsurefire.suiteXmlFiles=<file>: Runs tests using a TestNG XML file.