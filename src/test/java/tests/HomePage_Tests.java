package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;
import utils.TestBase;

public class HomePage_Tests extends TestBase {

	HomePage homepage;

	@Test(priority = 0, enabled = true, description = "Login Test for UAT")
	@Parameters({ "mobileNo", "otp" })
	private void loginTest(String mobileNo, String otp) {
		homepage = new HomePage(page);
		homepage.login(mobileNo, otp);
	}

	@Test(priority = 1, enabled = true)
	private void pincodeTest() {
		homepage = new HomePage(page);
		homepage.pincodeCheck();
	}

	@Test(priority = 2, enabled = true)
	private void searchTest() {
		homepage = new HomePage(page);
		homepage.searchItem();
	}

	@Test(priority = 3, enabled = false)
	private void navigationTest() {
		homepage = new HomePage(page);
		homepage.navigation();
	}

	@Test(priority = 4, enabled = true)
	private void profileDetailsTest() {
		homepage = new HomePage(page);
		homepage.profileDetails();
	}

	@Test(priority = 5, enabled = true)
	private void myAddressTest() {
		homepage = new HomePage(page);
		homepage.addNewAddress();
		homepage.editAddress();
		homepage.deleteAddress();
		homepage.setAsPrimary();
	}

	@Test(priority = 6, enabled = true)
	private void myOrdersTest() {
		homepage = new HomePage(page);
		homepage.myOrders();
	}
}