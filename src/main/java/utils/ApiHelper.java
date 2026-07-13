package utils;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class ApiHelper {

    private final APIRequestContext request;

    public ApiHelper(Playwright playwright) {

        request = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL("https://campaign-management-rose.vercel.app")
        );

    }

    // ==========================
    // GET Campaigns
    // ==========================

    public APIResponse getCampaigns() {

        return request.get("/api/campaigns");

    }

    // ==========================
    // Estimate Audience
    // ==========================

    public APIResponse estimateAudience(String audience, String channel) {

        String body = String.format("""
                {
                  "audienceSegment":"%s",
                  "channel":"%s"
                }
                """, audience, channel);

        return request.post("/api/audiences/estimate",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(body));

    }

    // ==========================
    // Create Campaign
    // ==========================

    public APIResponse createCampaign(String jsonBody) {

        return request.post("/api/campaigns",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(jsonBody));

    }

    // ==========================
    // Launch Campaign
    // ==========================

    public APIResponse launchCampaign(String campaignId) {

        return request.post("/api/campaigns/" + campaignId + "/launch",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData("{}"));

    }

    // ==========================
    // Reset
    // ==========================

    public APIResponse resetData() {

        return request.post("/api/test-controls/reset",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData("{}"));

    }

}