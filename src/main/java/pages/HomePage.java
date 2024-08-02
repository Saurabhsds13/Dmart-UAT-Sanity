package pages;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {

	private Page page;
	private String firstName = generateRandomAlphabeticString(6);
	private String lastName = generateRandomAlphabeticString(8);
	private String name = UUID.randomUUID().toString().substring(0, 8);
	private String apartment = "Apt" + (100 + (int) (Math.random() * 900));

	private List<String> pincodes = Arrays.asList("400076", "400080", "400001", "400053", "400002", "400003", "400004",
			"400005", "400006", "400007", "400008", "400009", "400010");

	Random random = new Random();
	String selectedPincode = pincodes.get(random.nextInt(pincodes.size()));
	Locator accountLocator = page.locator("//button//span[text()='My Account']");

	public HomePage(Page page) {
		this.page = page;
	}

	public void login(String mobileNo, String otp) {
		page.getByPlaceholder("Search for pincode, area or").click();
		page.getByPlaceholder("Search for pincode, area or").fill("400076");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("pincodeimg 400076 Powai,")).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CONFIRM LOCATION")).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In / Register")).click();
		page.getByRole(AriaRole.TEXTBOX).click();
		page.getByRole(AriaRole.TEXTBOX).fill(mobileNo);
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();
		page.getByRole(AriaRole.TEXTBOX).click();
		page.getByRole(AriaRole.TEXTBOX).fill(otp);
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("VERIFY OTP")).click();
		System.out.println("Login successful");
	}

	public void pincodeCheck() {

		for (int i = 0; i < pincodes.size(); i++) {

			page.getByTestId("ExpandMoreIcon").click();
			page.getByPlaceholder("Search for pincode, area or").click();
			page.getByPlaceholder("Search for pincode, area or").fill(selectedPincode);
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("pincodeimg 400076 Powai,")).click();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CONFIRM LOCATION")).click();
		}

	}

	public void searchItem() {

		String[] articles = { "Amul Butter", "oil", "premia sugar", "120000521", "Scotch-Brite Scrub",
				"Nutraj California Almonds", "140003763", "Parachute Coconut Oil", "120000272", "Surf Excel" };

		for (int i = 0; i < articles.length; i++) {
			page.locator("#scrInput").click();
			page.locator("#scrInput").fill(articles[i]);
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
		}

	}

	public void navigation() {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("All Categories")).click();
		page.locator("li").filter(new Locator.FilterOptions().setHasText("Pulses")).click();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Pulses")).click();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Kitchen Appliances")).click();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Papads & Pickles")).click();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Floor Cleaners")).click();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logo")).click();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Daily Savings")).first().click();

	}

	public void profileDetails() {
		page.locator("//button//span[text()='My Account']").click();
		page.waitForLoadState();
		page.locator("div[class='header_dropdown__Frp95 undefined'] div:nth-child(2)").click();
		page.getByPlaceholder("Enter First Name").clear();
		page.getByPlaceholder("Enter First Name").click();
		page.getByPlaceholder("Enter First Name").fill(firstName);
		page.getByPlaceholder("Enter Last Name").click();
		page.getByPlaceholder("Enter Last Name").fill(lastName);
		page.getByPlaceholder("Enter Email").click();
		page.getByPlaceholder("Enter Email").fill(firstName + "@gmail.com");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save Changes")).click();
		page.locator(".common_container__xPeOJ > div").click();

	}

	public void addNewAddress() {

		String fullName = firstName + " " + lastName;

		System.out.println("Generated Full Name: " + fullName);

		accountLocator.click();
		page.locator("div[class='header_dropdown__Frp95 undefined'] div:nth-child(3)").click();
		page.getByTestId("AddOutlinedIcon").locator("path").click();
		page.locator("input[name=\"name\"]").click();
		page.locator("input[name=\"name\"]").fill(fullName);
		page.locator("input[name=\"pincode\"]").click();
		page.locator("input[name=\"pincode\"]").fill(selectedPincode);
		page.locator("textarea[name=\"addressDetails\"]").click();
		page.locator("textarea[name=\"addressDetails\"]").fill(name + " " + "Apartment");
		page.locator("textarea[name=\"apartmentDetails\"]").click();
		page.locator("textarea[name=\"apartmentDetails\"]").fill(apartment);
		page.locator("button[type='submit']").click();
		page.locator("//button[normalize-space()='Save']").click();
	}

	public void editAddress() {

		// Locate the specific address container with the generated first name
		Locator addressLocator = page
				.locator("//div[contains(@class, 'address-list_name__JQGw0') and text()='" + firstName + "']\r\n");

		// Ensure the address with the generated first name exists
		if (addressLocator.count() > 0) {

			// Click the "Edit" button
			page.getByText("Edit").first().click();

			// Additional steps after clicking edit, e.g., edit the address details
		} else {
			System.out.println("No address found with the name: " + firstName);
		}

		page.locator("input[name=\"name\"]").click();
		page.locator("input[name=\"name\"]").clear();
		page.locator("input[name=\"name\"]").fill("Saurabh kanjur");
		page.locator("input[name=\"pincode\"]").click();
		page.locator("input[name=\"pincode\"]").clear();
		page.locator("input[name=\"pincode\"]").fill("400076");
		page.locator("textarea[name='apartmentDetails']").click();
		page.locator("textarea[name='apartmentDetails']").clear();
		page.locator("textarea[name='apartmentDetails']").fill("305");
		page.locator("input[name=\"phone\"]").click();
		page.locator("input[name=\"phone\"]").clear();
		page.locator("input[name=\"phone\"]").fill("7972227009");
		page.locator("textarea[name=\"addressDetails\"]").click();
		page.locator("textarea[name=\"addressDetails\"]").clear();
		page.locator("textarea[name=\"addressDetails\"]").fill("Prashant Apartment Tirandaz ");
		page.locator("button[type='submit']").click();
		page.locator("//button[normalize-space()='Save']").click();
	}

	public void deleteAddress() {
		page.getByText("Delete").nth(3).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("YES")).click();
	}

	public void setAsPrimary() {
		page.getByText("Set Primary").first().click();
		page.getByText("Primary", new Page.GetByTextOptions().setExact(true)).click();
	}

	private static String generateRandomAlphabeticString(int length) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			// Generate a random character from 'A' to 'Z' or 'a' to 'z'
			char randomChar = (char) (random.nextBoolean() ? 'A' + random.nextInt(26) : 'a' + random.nextInt(26));
			sb.append(randomChar);
		}
		return sb.toString();
	}

	public void myOrders() {
		accountLocator.click();
		page.getByRole(AriaRole.TOOLTIP).getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("My Orders"))
				.click();
		System.out.println("My Orders Page Displayed.");
		page.pause();
	}
}