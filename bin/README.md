# Selenium Automation Framework
### Login & Forgot Password Modules | Java + TestNG + Maven + Page Factory + Log4j2 + Extent Reports

---

## Project Structure

```
selenium-framework/
│
├── pom.xml                                          # Maven build + all dependencies
├── testng.xml                                       # TestNG suite runner
├── README.md
│
├── src/
│   ├── main/java/com/qa/
│   │   ├── base/
│   │   │   └── BaseClass.java                       # Browser setup / teardown (ThreadLocal)
│   │   ├── constants/
│   │   │   └── Constants.java                       # All constant strings in one place
│   │   ├── pages/
│   │   │   ├── LoginPage.java                       # POM + PageFactory: Login page
│   │   │   └── ForgotPasswordPage.java              # POM + PageFactory: Forgot Password page
│   │   └── utils/
│   │       ├── ConfigReader.java                    # Reads config.properties
│   │       ├── ExcelUtils.java                      # Apache POI: Excel data-driven testing
│   │       ├── ExtentReportManager.java             # Extent Reports: HTML reporting
│   │       ├── ScreenshotUtils.java                 # Auto-screenshot on failure
│   │       ├── TestListener.java                    # ITestListener: hooks for report + screenshot
│   │       └── WaitUtils.java                       # Explicit + Fluent Wait helpers
│   │
│   └── test/
│       ├── java/com/qa/tests/
│       │   ├── LoginTests.java                      # 12 Login test cases (TC: LOGIN_001–012)
│       │   └── ForgotPasswordTests.java             # 12 FP test cases (TC: FP_001–012)
│       └── resources/
│           ├── log4j2.xml                           # Log4j2: console + file logging config
│           ├── config/
│           │   └── config.properties                # Browser, URL, credentials, timeouts
│           └── testdata/
│               ├── LoginData.xlsx                   # (create manually) Data-driven test data
│               ├── ForgotPasswordData.xlsx          # (create manually) FP test data
│               └── TestData_README.txt              # Column structure guide
│
├── logs/                                            # Auto-created: log files
├── screenshots/                                     # Auto-created: failure screenshots
└── reports/                                         # Auto-created: HTML Extent Report
    └── AutomationReport.html
```

---

## Prerequisites

| Tool | Version |
|------|---------|
| Java JDK | 11 or higher |
| Maven | 3.6+ |
| Chrome Browser | Latest |
| IDE | IntelliJ IDEA / Eclipse |

---

## Setup Steps

### 1. Clone / Import Project
```
Import as Maven project in IntelliJ or Eclipse.
Maven will auto-download all dependencies from pom.xml.
```

### 2. Configure Application URL
Edit `src/test/resources/config/config.properties`:
```properties
base.url=https://your-app-url.com/login
valid.username=Admin
valid.password=admin123
browser=chrome
headless=false
```

### 3. Create Excel Test Data Files (Optional for Data-Driven)
Refer to `src/test/resources/testdata/TestData_README.txt` for column structure.
Create `LoginData.xlsx` and `ForgotPasswordData.xlsx` manually.

### 4. Run Tests

**Run all tests:**
```bash
mvn test
```

**Run only Login tests:**
```bash
mvn test -Dtest=LoginTests
```

**Run only Forgot Password tests:**
```bash
mvn test -Dtest=ForgotPasswordTests
```

**Run only smoke group:**
```bash
mvn test -Dgroups=smoke
```

**Run headless:**
```bash
mvn test -Dheadless=true
```

---

## Test Case Coverage

### Login Module (LoginTests.java)

| TC ID | Test Method | Type | Group |
|-------|-------------|------|-------|
| LOGIN_001 | verifyLoginPageLoadsSuccessfully | Functional | smoke |
| LOGIN_002 | verifyLoginPageUrl | UI | smoke |
| LOGIN_003 | verifyUsernameFieldVisibility | UI | smoke |
| LOGIN_004 | verifyPasswordFieldVisibility | UI | smoke |
| LOGIN_005 | verifyLoginButtonVisibility | UI | smoke |
| LOGIN_006 | verifyValidLogin | Positive | regression |
| LOGIN_007 | verifyLoginWithInvalidUsername | Negative | regression |
| LOGIN_008 | verifyLoginWithInvalidPassword | Negative | regression |
| LOGIN_009 | verifyBlankUsernameValidation | Validation | regression |
| LOGIN_010 | verifyBlankPasswordValidation | Validation | regression |
| LOGIN_011 | verifyBothFieldsBlankValidation | Validation | regression |
| LOGIN_012 | verifyForgotPasswordLink | Functional | regression |

### Forgot Password Module (ForgotPasswordTests.java)

| TC ID | Test Method | Type | Group |
|-------|-------------|------|-------|
| FP_001 | verifyForgotPasswordPageLoads | Functional | smoke |
| FP_002 | verifyForgotPasswordPageUrl | UI | smoke |
| FP_003 | verifyResetPasswordHeadingVisibility | UI | smoke |
| FP_004 | verifyInstructionTextVisibility | UI | ui |
| FP_005 | verifyUsernameFieldVisibility | UI | smoke |
| FP_006 | verifyCancelButtonVisibility | UI | smoke |
| FP_007 | verifyResetPasswordButtonVisibility | UI | smoke |
| FP_008 | verifyResetWithValidUsername | Positive | regression |
| FP_009 | verifyResetWithInvalidUsername | Negative | regression |
| FP_010 | verifyBlankUsernameValidation | Validation | regression |
| FP_011 | verifyCancelButtonNavigation | Functional | regression |
| FP_012 | verifyUsernameAcceptsValidCharacters | Validation | regression |

---

## Framework Features

| Feature | Implementation |
|---------|---------------|
| Page Object Model | `LoginPage.java`, `ForgotPasswordPage.java` |
| Page Factory | `@FindBy` + `PageFactory.initElements()` |
| Browser Management | `WebDriverManager` (no manual chromedriver.exe needed) |
| Multi-browser Support | Chrome, Firefox, Edge (via config.properties) |
| Parallel Test Safety | `ThreadLocal<WebDriver>` in BaseClass |
| Explicit Waits | `WaitUtils.java` (WebDriverWait + FluentWait) |
| Logging | `Log4j2` → console + file (`logs/automation.log`) |
| Data-Driven Testing | `ExcelUtils.java` (Apache POI) + `@DataProvider` |
| Extent HTML Reports | `ExtentReportManager.java` → `reports/AutomationReport.html` |
| Auto Screenshot | `ScreenshotUtils.java` → called from `TestListener.onTestFailure()` |
| TestNG Groups | smoke, regression, functional, ui, positive, negative, validation |
| Config Management | `ConfigReader.java` → `config.properties` |

---

## Reports & Logs

After running tests:

- **HTML Report:** `reports/AutomationReport.html` — open in browser
- **Console Log:** Printed live during execution
- **Log File:** `logs/automation.log` — full execution log
- **Screenshots:** `screenshots/` — captured automatically on any test failure

---

## Adapting to a Different Application

1. Update `config.properties` with your app's URL and credentials
2. Update `@FindBy` locators in `LoginPage.java` and `ForgotPasswordPage.java`
3. Update assertion conditions in test methods if error messages differ
4. Create your Excel data files following the `TestData_README.txt` structure
