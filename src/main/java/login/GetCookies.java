package login;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class GetCookies {

	public static void main(String[] args) {

		try (Playwright playwright = Playwright.create()) {

			String url = "https://uat.dmart.in/?uuid=68ca701da2ceab245a056bbfd27a291cDf9423055d8e99e742342f17eb58fd88c";
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();

			page.navigate(url);
			page.getByPlaceholder("Search for pincode, area or street name.").click();
			page.getByPlaceholder("Search for pincode, area or street name.").fill("400053");
			page.getByRole(AriaRole.BUTTON,
					new Page.GetByRoleOptions().setName("pincodeimg 400053 Andheri (W), Mumbai")).click();

			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CONFIRM LOCATION")).click();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In / Register")).click();
			page.getByRole(AriaRole.TEXTBOX).click();
			page.getByRole(AriaRole.TEXTBOX).fill("7972227009");
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
			page.pause();

			// Put OTP manually and Login to site.
			System.out.println("Sing-In Completed");

			// Get Cookies from session and save in a j-son file.
			context.storageState(new BrowserContext.StorageStateOptions()
					.setPath(Paths.get("C:\\Users\\saurabh.sonawane\\Desktop\\Playwrite\\RWD-UAT-Sanity\\Login.json")));
			System.out.println("Cookies Fetched.");

			page.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}