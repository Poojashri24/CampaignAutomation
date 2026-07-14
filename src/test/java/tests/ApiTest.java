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

@Test(priority = 6)
public void pastScheduledDateApi() {

    ApiHelper api = new ApiHelper(playwright);

    String body = """
    {
      "name":"Past Date",
      "channel":"Email",
      "audienceSegment":"trial-users",
      "sendMode":"scheduled",
      "scheduledAt":"2024-01-01T10:00",
      "message":"Testing API"
    }
    """;

    APIResponse response = api.createCampaign(body);

    System.out.println(response.status());
    System.out.println(response.text());

}
}