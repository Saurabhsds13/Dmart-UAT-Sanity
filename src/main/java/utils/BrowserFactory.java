package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

	private static Playwright playwright;
	private static Browser browser;

	public static Browser getBrowser() {
		if (browser == null) {
			playwright = Playwright.create();
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(300));
		}
		return browser;
	}

	public static void closeBrowser() {
		if (browser != null) {
			browser.close();
			browser = null;
		}
		if (playwright != null) {
			playwright.close();
			playwright = null;
		}
	}

}