package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ExecutionPage;

public class ResetTest extends BaseTest {

    @Test(priority = 1)
    public void verifyResetData() {

        ExecutionPage execution = new ExecutionPage(page);

        execution.resetData();

        page.waitForTimeout(2000);

        execution.openAnalyticsTab();

        Assert.assertEquals(
                execution.totalCampaigns(),
                "3"
        );

        Assert.assertEquals(
                execution.totalDraft(),
                "1"
        );

        Assert.assertEquals(
                execution.totalScheduled(),
                "1"
        );

        Assert.assertEquals(
                execution.totalSent(),
                "1"
        );
    }
}