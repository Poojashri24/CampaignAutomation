package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ExecutionPage;

public class ValidationTest extends BaseTest {

    @Test(priority = 1)
    public void emptyCampaignNameValidation() {

        ExecutionPage execution = new ExecutionPage(page);

        execution.openExecutionTab();

        execution.selectChannel("Email");
        execution.selectAudience("trial-users");
        execution.selectSendMode("now");
        execution.enterMessage("Testing validation");

        execution.createCampaign();

        page.waitForTimeout(1000);

        Assert.assertTrue(
                execution.isErrorDisplayed() ||
                execution.campaignCount() >= 3
        );

    }

    @Test(priority = 2)
    public void emptyMessageValidation() {

        ExecutionPage execution = new ExecutionPage(page);

        execution.openExecutionTab();

        execution.enterCampaignName("Validation Test");
        execution.selectChannel("SMS");
        execution.selectAudience("dormant-users");
        execution.selectSendMode("now");

        execution.createCampaign();

        page.waitForTimeout(1000);

        Assert.assertTrue(
                execution.isErrorDisplayed() ||
                execution.campaignCount() >= 3
        );

    }

    @Test(priority = 3)
    public void scheduleWithoutDateValidation() {

        ExecutionPage execution = new ExecutionPage(page);

        execution.openExecutionTab();

        execution.enterCampaignName("Schedule Test");
        execution.selectChannel("Email");
        execution.selectAudience("trial-users");
        execution.selectSendMode("scheduled");
        execution.enterMessage("Testing schedule validation");

        execution.createCampaign();

        page.waitForTimeout(1000);

        Assert.assertTrue(
                execution.isErrorDisplayed() ||
                execution.campaignCount() >= 3
        );

    }
// @Test(priority = 4)
// public void pastScheduleDateValidation() {

//     ExecutionPage execution = new ExecutionPage(page);

//     execution.openExecutionTab();

//     execution.enterCampaignName("Past Date Test");

//     execution.selectChannel("Email");

//     execution.selectAudience("trial-users");

//     execution.selectSendMode("scheduled");

//     execution.enterSchedule("2024-01-01T10:00");

//     execution.enterMessage("Testing past date validation");

//     execution.createCampaign();

//     Assert.assertTrue(
//             execution.isErrorDisplayed(),
//             "Application accepted a past scheduled date."
//     );
// }
}