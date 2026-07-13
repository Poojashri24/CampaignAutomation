package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.ScreenshotType;

import java.nio.file.Paths;

public class ScreenshotUtil {

    public static void capture(Page page,String name){

        page.screenshot(

                new Page.ScreenshotOptions()

                        .setPath(Paths.get("screenshots/"+name+".png"))

                        .setType(ScreenshotType.PNG)

                        .setFullPage(true)

        );

    }

}