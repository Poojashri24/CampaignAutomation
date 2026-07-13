package tests;

import base.BaseTest;
import com.microsoft.playwright.APIResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiHelper;

public class ApiTest extends BaseTest {

    @Test
    public void verifyAudienceEstimateAPI() {

        ApiHelper api = new ApiHelper(playwright);

        APIResponse response =
                api.estimateAudience("trial-users", "Email");

        Assert.assertEquals(response.status(), 200);

        String body = response.text();

        System.out.println(body);

        Assert.assertTrue(body.contains("\"estimatedAudience\":1840"));
        Assert.assertTrue(body.contains("\"trial-users\""));
        Assert.assertTrue(body.contains("\"Email\""));

    }

}