package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ExecutionPage;

public class FilterTest extends BaseTest {

    @Test(priority = 1)
    public void filterByStatusDraft() {

        ExecutionPage execution = new ExecutionPage(page);

        execution.openExecutionTab();

        execution.filterStatus("Draft");

        page.waitForTimeout(1000);

        Assert.assertEquals(
                execution.firstCampaignStatus(),
                "Draft"
        );
    }

    @Test(priority = 2)
    public void filterByChannelEmail() {

        ExecutionPage execution = new ExecutionPage(page);

        execution.openExecutionTab();

        execution.filterChannel("Email");

        page.waitForTimeout(1000);

        Assert.assertEquals(
                execution.firstCampaignChannel(),
                "Email"
        );
    }

    @Test(priority = 3)
    public void clearStatusFilter() {

        ExecutionPage execution = new ExecutionPage(page);

        execution.refresh();

        execution.openExecutionTab();

        Assert.assertTrue(
                execution.campaignCount() >= 3
        );
    }

    @Test(priority = 4)
    public void clearChannelFilter() {

        ExecutionPage execution = new ExecutionPage(page);

        execution.refresh();

        execution.openExecutionTab();

        Assert.assertTrue(
                execution.campaignCount() >= 3
        );
    }

}