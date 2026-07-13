package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

public class ExecutionPage extends BasePage {

    public ExecutionPage(Page page) {
        super(page);
    }

    // ===========================
    // Tabs
    // ===========================

    public void openExecutionTab() {
        clickByTestId("tab-execution");
    }

    public void openApiTab() {
        clickByTestId("tab-api");
    }

    public void openAnalyticsTab() {
        clickByTestId("tab-analytics");
    }

    // ===========================
    // Create Campaign
    // ===========================

    public void enterCampaignName(String name) {
        fillByTestId("campaign-name-input", name);
    }

    public void selectChannel(String channel) {

        page.getByTestId("channel-select")
                .selectOption(new SelectOption().setLabel(channel));

    }

    public void selectAudience(String audience) {

        page.getByTestId("audience-select")
                .selectOption(audience);

    }

    public void selectSendMode(String mode) {

        page.getByTestId("send-mode-select")
                .selectOption(mode);

    }

    public void enterSchedule(String dateTime) {

        fillByTestId("schedule-input", dateTime);

    }

    public void enterMessage(String message) {

        fillByTestId("message-input", message);

    }

    public void estimateAudience() {

        clickByTestId("estimate-button");

        page.waitForFunction(
                "() => document.querySelector('[data-testid=\"audience-estimate\"]').textContent !== 'No estimate'"
        );

    }

    public String getEstimatedAudience() {

        return textByTestId("audience-estimate");

    }

    public void createCampaign() {

    clickByTestId("create-campaign-button");

    page.waitForTimeout(1000);

}

    public String getErrorMessage() {

        return textByTestId("form-error");

    }

    public boolean isErrorDisplayed() {

        String error = getErrorMessage();

        return error != null && !error.trim().isEmpty();

    }

    // ===========================
    // Filters
    // ===========================

public void filterStatus(String status) {

    if (status.isEmpty()) {
        page.getByTestId("status-filter")
                .selectOption(new SelectOption().setIndex(0));
    } else {
        page.getByTestId("status-filter")
                .selectOption(status);
    }
}


public void filterChannel(String channel) {

    if (channel.isEmpty()) {
        page.getByTestId("channel-filter")
                .selectOption(new SelectOption().setIndex(0));
    } else {
        page.getByTestId("channel-filter")
                .selectOption(channel);
    }
}

    // ===========================
    // Campaign Actions
    // ===========================

    public void launchFirstCampaign() {

        page.getByTestId("launch-campaign-button")
                .first()
                .click();

    }

    public String firstCampaignName() {

        return page.getByTestId("campaign-name")
                .first()
                .textContent();

    }

    public String firstCampaignStatus() {

        return page.getByTestId("campaign-status")
                .first()
                .textContent();

    }

    public String firstCampaignChannel() {

        return page.getByTestId("campaign-channel")
                .first()
                .textContent();

    }

    public int campaignCount() {

        return page.getByTestId("campaign-card").count();

    }

public boolean isLaunchButtonDisabled(int index) {

    if (index >= page.getByTestId("launch-campaign-button").count())
        return false;

    return page.getByTestId("launch-campaign-button")
            .nth(index)
            .isDisabled();

}

    // ===========================
    // Analytics
    // ===========================

    public String totalCampaigns() {

        return textByTestId("metric-total");

    }

    public String totalDraft() {

        return textByTestId("metric-draft");

    }

    public String totalScheduled() {

        return textByTestId("metric-scheduled");

    }

    public String totalSent() {

        return textByTestId("metric-sent");

    }
// ===========================
// Advanced Helpers
// ===========================

public boolean campaignExists(String campaignName) {

    return page.getByText(campaignName).count() > 0;

}

public void refresh() {

    page.reload();
    page.waitForLoadState();

}

public boolean sentCampaignLaunchDisabled() {

    int cards = page.getByTestId("campaign-card").count();

    for (int i = 0; i < cards; i++) {

        String status = page.getByTestId("campaign-status").nth(i).textContent();

        if (status.equalsIgnoreCase("Sent")) {

            return page.getByTestId("launch-campaign-button")
                    .nth(i)
                    .isDisabled();

        }

    }

    return false;

}

public void waitForEstimate() {

    page.waitForFunction(
            "() => document.querySelector('[data-testid=\"audience-estimate\"]').textContent !== 'No estimate'"
    );

}

public void waitForCampaignCreation() {

    page.waitForTimeout(1000);

    page.reload();

    page.waitForLoadState();

}
    // ===========================
    // Reset
    // ===========================

    public void resetData() {

        clickByTestId("reset-data-button");

        page.waitForLoadState();

    }

}