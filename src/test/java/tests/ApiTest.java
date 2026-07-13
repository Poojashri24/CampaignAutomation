package tests;

import base.BaseTest;
import com.microsoft.playwright.APIResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiHelper;
import utils.RandomDataUtil;

public class ApiTest extends BaseTest {

    @Test(priority = 1)
    public void verifyAudienceEstimateApi() {

        ApiHelper api = new ApiHelper(playwright);

        APIResponse response = api.estimateAudience("trial-users", "Email");

        Assert.assertEquals(response.status(), 200);

        String body = response.text();

        System.out.println(body);

        Assert.assertTrue(body.contains("\"estimatedAudience\":1840"));
        Assert.assertTrue(body.contains("\"Email\""));
        Assert.assertTrue(body.contains("\"SMS\""));
        Assert.assertTrue(body.contains("\"Push\""));

    }

    @Test(priority = 2)
    public void verifyCreateCampaignApi() {

        ApiHelper api = new ApiHelper(playwright);

        String campaign = RandomDataUtil.campaignName();

        String body = """
                {
                  "name":"%s",
                  "channel":"Email",
                  "audienceSegment":"trial-users",
                  "sendMode":"now",
                  "scheduledAt":"",
                  "message":"This is an API automation campaign."
                }
                """.formatted(campaign);

        APIResponse response = api.createCampaign(body);

        Assert.assertEquals(response.status(), 201);

        String responseBody = response.text();

        System.out.println(responseBody);

        Assert.assertTrue(responseBody.contains(campaign));
        Assert.assertTrue(responseBody.contains("\"channel\":\"Email\""));
        Assert.assertTrue(responseBody.contains("\"audienceSegment\":\"trial-users\""));
        Assert.assertTrue(responseBody.contains("\"status\":\"Draft\""));
        Assert.assertTrue(responseBody.contains("\"estimatedAudience\":1840"));

    }

}