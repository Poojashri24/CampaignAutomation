package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ExecutionPage;

public class DefectTest extends BaseTest {

    @Test
    public void analyticsSentCountNotUpdated() {

        ExecutionPage execution = new ExecutionPage(page);

        execution.openExecutionTab();

        // Steps to reproduce the defect

        execution.openAnalyticsTab();

        Assert.assertEquals(
                execution.totalSent(),
                "2"
        );
    }
}