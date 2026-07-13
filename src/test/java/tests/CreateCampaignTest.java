package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ExecutionPage;
import utils.RandomDataUtil;

public class CreateCampaignTest extends BaseTest {

    @Test(priority = 1)
    public void createCampaignSuccessfully() {

        ExecutionPage execution = new ExecutionPage(page);

        execution.openExecutionTab();

        String campaign = RandomDataUtil.campaignName();
        String message = RandomDataUtil.message();

        execution.enterCampaignName(campaign);
        execution.enterMessage(message);

        execution.selectChannel("Email");
        execution.selectAudience("trial-users");
        execution.selectSendMode("now");

        execution.estimateAudience();

        Assert.assertEquals(
                execution.getEstimatedAudience(),
                "1840 recipients"
        );

execution.createCampaign();

execution.waitForCampaignCreation();

execution.openExecutionTab();

page.waitForTimeout(2500);

Assert.assertTrue(
        execution.campaignExists(campaign),
        "Campaign was not created"
);

        Assert.assertEquals(
                execution.firstCampaignChannel(),
                "Email"
        );

        Assert.assertEquals(
                execution.firstCampaignStatus(),
                "Draft"
        );
    }
}