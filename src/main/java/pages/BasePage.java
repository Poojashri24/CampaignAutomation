package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    protected void clickByTestId(String testId) {
        page.getByTestId(testId).click();
    }

    protected void fillByTestId(String testId, String value) {
        page.getByTestId(testId).fill(value);
    }

    protected String textByTestId(String testId) {
        return page.getByTestId(testId).textContent().trim();
    }

    protected Locator locatorByTestId(String testId) {
        return page.getByTestId(testId);
    }

    protected void waitForOneSecond() {
        page.waitForTimeout(1000);
    }

    protected void refreshPage() {
        page.reload();
        page.waitForLoadState();
    }

}