package rediff;

import org.testng.annotations.Test;


import base.TestBase;

public class LoginTest extends TestBase {

	@Test
	public void doLogin() {
		openBrowser("chrome");
		openUrl("money");
		waitToLoad();
		clickElement("signLink_xpath");
		waitToLoad();
		getElement("user_xpath").sendKeys("rth143");
		clickElement("email_xpath");
		waitToLoad();
		getElement("pass_xpath").sendKeys("rock12");
		clickElement("login_xpath");
		//https://github.com/Rakyesh24/MavenProject.git
	}
}
