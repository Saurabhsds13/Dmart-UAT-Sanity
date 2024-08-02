package utils;

import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

public class TestBase {
	protected Browser browser;
	protected Page page;

	@BeforeTest
	public void setUp() {
		browser = BrowserFactory.getBrowser();

		page = browser.newPage();
		page.evaluate("() => window.moveTo(0,0); window.resizeTo(screen.width, screen.height);");
		page.evaluate("() => document.body.style.zoom = '0.65'");

		page.navigate("https://uat.dmart.in/?uuid=68ca701da2ceab245a056bbfd27a291cDf9423055d8e99e742342f17eb58fd88c");
		page.waitForLoadState();

		page.focus("body");
		page.keyboard().down("Alt");
		page.keyboard().press("Space");
		page.keyboard().up("Alt");
		page.waitForTimeout(200);
		page.keyboard().press("x");

		System.out.println("Opening " + page.title());
	}

	@AfterTest
	public void tearDown() {
		if (page != null) {
			page.close();
		}
		BrowserFactory.closeBrowser();
	}

//	@AfterMethod
	public void afterMethod(ITestResult result) {
		takeScreenshot(result.getMethod().getMethodName());
	}

	public void takeScreenshot(String methodName) {
		String filename = System.getProperty("user.dir") + "\\Screenshot\\";
		try {
			System.out.println("Taking screenshot for method: " + methodName);
			page.waitForTimeout(5000);
			page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filename + methodName + ".png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}