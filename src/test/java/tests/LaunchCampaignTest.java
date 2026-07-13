package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ExecutionPage;

public class LaunchCampaignTest extends BaseTest {

    @Test(priority = 1)
    public void launchDraftCampaign() {

        ExecutionPage execution = new ExecutionPage(page);

        execution.openExecutionTab();

        // Remember which campaign we are launching
        String launchedCampaign = execution.firstCampaignName();

        execution.launchFirstCampaign();

        page.waitForTimeout(1500);

        page.reload();
        page.waitForLoadState();

        execution.openExecutionTab();

        page.waitForTimeout(1500);

        int total = page.getByTestId("campaign-card").count();

        boolean foundQueued = false;

        for (int i = 0; i < total; i++) {

            String name = page.getByTestId("campaign-name").nth(i).textContent();

            if (name.equals(launchedCampaign)) {

                String status = page.getByTestId("campaign-status").nth(i).textContent();

                Assert.assertEquals(status, "Queued");

                foundQueued = true;
                break;
            }
        }

        Assert.assertTrue(foundQueued, "Launched campaign not found after refresh");
    }

    @Test(priority = 2)
    public void sentCampaignLaunchButtonDisabled() {

        ExecutionPage execution = new ExecutionPage(page);

        execution.openExecutionTab();

        Assert.assertTrue(execution.sentCampaignLaunchDisabled());
    }
}