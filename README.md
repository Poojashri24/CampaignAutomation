# Campaign Management Automation

## Overview

This project is a test automation framework developed for a Campaign Management web application.

The application enables users to create, validate, schedule, launch, and monitor marketing campaigns. The automation framework is built using Java, Playwright, TestNG, and Maven, following the Page Object Model (POM) design pattern for better maintainability and code reusability.

The framework covers both UI and API testing to validate key business workflows, user interactions, and backend responses.

---

## Tech Stack

- Java 17
- Playwright
- TestNG
- Maven
- Jackson
- Apache Commons

---

## Project Structure

```
CampaignAutomation
│
├── src
│   ├── main
│   │   └── java
│   │       ├── pages
│   │       └── utils
│   │
│   └── test
│       └── java
│           ├── base
│           └── tests
│
├── pom.xml
├── README.md
└── .gitignore
```

---

## Test Scenarios Covered

### UI Tests

- Create a new campaign successfully
- Validate campaign creation with missing mandatory fields
- Verify audience estimate for selected audience
- Launch a draft campaign
- Verify Sent campaigns cannot be launched
- Filter campaigns by Status
- Filter campaigns by Channel
- Clear applied filters
- Reset application data
- Verify dashboard metrics after reset
- Verify past date scheduling

### API Tests

- Validate the Audience Estimate API by verifying the estimated audience count and supported channels.
- Validate the Create Campaign API by verifying successful campaign creation, response status, and returned campaign details.

---

## How to Run

Clone the repository

```bash
git clone <repository-url>
```

Move into the project directory

```bash
cd CampaignAutomation
```

Install dependencies

```bash
mvn clean install
```

Run UI Tests

```bash
mvn test "-Dtest=CreateCampaignTest,ValidationTest,FilterTest,LaunchCampaignTest,ResetTest"
```

Run API Test

```bash
mvn test "-Dtest=ApiTest"
```

---

## Assumptions

- The application should be reset before executing the test suite.
- Seeded campaign data is restored using the Reset option.
- Audience estimates are fixed according to the provided business data.
- Campaign launch changes status from Draft to Queued before delivery.

---

## Defects Found

### 1: Past Scheduled Date Accepted

Expected:
Scheduled campaigns should accept only a future date and time.

Actual:
The application accepts past dates without displaying a validation error.

Severity:
Medium

### 2. Campaign Launch Status

**Expected**

After clicking Launch, the campaign status should change from Draft to Queued.

**Actual**

Sometimes the status remains Draft for a few seconds before updating.

**Severity**

Medium

---

### 3. Filter Reset Behaviour

**Expected**

Clearing a filter should immediately display all campaigns.

**Actual**

The application occasionally requires a short delay before displaying the full campaign list.

**Severity**

Low

---

## Observations

- The Create Campaign API allows multiple campaigns with the same name by assigning a unique campaign ID to each request.
- Campaign data is persisted until the application is reset using the provided reset functionality.

---

## Future Improvements

If more time was available, I would add:

- Cross-browser execution
- Parallel test execution
- API response schema validation
- Reporting using Allure or Extent Reports
- Data-driven testing using JSON or Excel

---

## Future Test Strategy

If the application is extended with Team Signup and Multi-Tenant Onboarding, I would prioritize automation in the following order.

### UI

- User registration
- Invitation acceptance
- Login flow
- Role-based access
- Tenant switching

### API

- Signup API
- Invite API
- Role assignment API
- Authentication API
- Tenant management APIs

### Lower-Level Tests

- Input validation
- Authorization
- Permission checks
- Business rule validations
- Service layer unit tests

This approach keeps UI tests focused on business workflows while validating most logic through faster API and service-level tests.

---

## Notes

The automation framework follows the Page Object Model (POM) to improve readability, maintainability and code reusability.

Random test data is generated during execution to avoid duplicate campaign names, and screenshots are captured automatically whenever a test fails.
