package base;

import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ScreenshotUtil;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setUp() {

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(300)
        );

        context = browser.newContext(
        new Browser.NewContextOptions()
                .setViewportSize(1536, 864)
                .setIgnoreHTTPSErrors(true)
);

        page = context.newPage();

        page.setDefaultTimeout(10000);

for (int i = 0; i < 3; i++) {

    try {

        page.navigate(
            "https://campaign-management-rose.vercel.app/",
            new Page.NavigateOptions().setTimeout(30000)
        );

        page.waitForLoadState();

        break;

    } catch (Exception e) {

        if (i == 2)
            throw e;

    }
}

page.waitForLoadState();

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        try {

            if (result.getStatus() == ITestResult.FAILURE) {

                ScreenshotUtil.capture(page, result.getName());

            }

        } catch (Exception e) {

            System.out.println("Screenshot capture failed");

        }

        try {
    if (page != null)
        page.close();
} catch (Exception ignored) {}

try {
    if (context != null)
        context.close();
} catch (Exception ignored) {}

try {
    if (browser != null)
        browser.close();
} catch (Exception ignored) {}

try {
    if (playwright != null)
        playwright.close();
} catch (Exception ignored) {}

    }

}