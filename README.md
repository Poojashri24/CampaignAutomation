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
- Verify launch functionality for draft campaigns
- Filter campaigns by Status
- Filter campaigns by Channel
- Clear applied filters
- Reset application data
- Verify dashboard metrics after reset
- Verify past date scheduling

### API Tests

- Validate Audience Estimate API response.
- Validate successful Campaign Creation API.
- Validate invalid send mode returns a validation error.
- Validate invalid audience segment returns a validation error.

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

## Defect Reproduction

The project includes a separate defect reproduction test to demonstrate a confirmed application issue.

Run the following command:

```bash
mvn test "-Dtest=DefectTest"
```

**Expected Result**

This test is expected to **fail** because it reproduces a confirmed defect in the application.

**Defect Verified**

- Analytics dashboard does not update the **Sent** campaign count after campaign status changes.
- Expected Sent count: **2**
- Actual Sent count: **1**

The main regression suite and API tests are independent of this defect reproduction test and should pass successfully.

---

## Assumptions

- The application should be reset before executing the test suite.
- Seeded campaign data is restored using the Reset option.
- Audience estimates are fixed according to the provided business data.
- Campaign launch follows the business rules defined in the application.

---

## Defects Found

### 1. Past Scheduled Date Accepted (UI)

**Expected**
Scheduled campaigns should only accept a future date and time.

**Actual**
The UI allows creation of a scheduled campaign with a past date without displaying a validation error.

**Severity**
Medium

---

### 2. Past Scheduled Date Accepted (API)

**Expected**
The Create Campaign API should reject requests containing a past scheduled date with a validation error.

**Actual**
The API accepts the request and creates the campaign successfully (HTTP 201 Created), even when the scheduled date is in the past.

**Severity**
High

---

### 3. Analytics Dashboard Count Not Updated

**Expected**

The Analytics dashboard should display the current number of campaigns in each status.

**Actual**

After launching campaigns, the Execution page shows the updated campaign status, but the Analytics dashboard continues to display the old Sent count.

**Severity**

Medium

---

### 4.Launch Button Enabled for Sent Campaign

**Expected**

Launch button should be disabled or hidden for a Sent campaign.

**Actual**

Launch button remains enabled and clickable, but clicking it performs no action.

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

Random test data is generated during execution to avoid duplicate campaign names, and Screenshots are captured automatically whenever a regression test fails.
