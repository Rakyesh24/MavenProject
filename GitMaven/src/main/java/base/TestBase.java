package base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestBase extends TestAbstract {

	public WebDriver driver;
	public JavascriptExecutor js;
	public ExtentHtmlReporter reporter;
	public ExtentTest logger;
	public ExtentReports extent;

	@Override
	public void openBrowser(String browser) {
		while (driver == null)
			try {
				if (browser.equals("chrome")) {
					driver = new ChromeDriver();
				} else if (browser.equals("firefox")) {
					driver = new FirefoxDriver();
				} else if (browser.equals("edge")) {
					driver = new EdgeDriver();
				} else if (browser.equals("ie")) {
					driver = new InternetExplorerDriver();
				}
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} catch (Exception e) {
				// TODO: handle exception
			}

	}

	@Override
	public void openUrl(String appurl) {
		driver.get(prop.getProperty(appurl));

	}

	@Override
	public WebElement getElement(String locator) {
		WebElement e = null;
		try {
			if (locator.endsWith("_xpath")) {
				e = driver.findElement(By.xpath(prop.getProperty(locator)));
			} else if (locator.endsWith("_id")) {
				e = driver.findElement(By.id(prop.getProperty(locator)));
			} else if (locator.endsWith("_name")) {
				e = driver.findElement(By.name(prop.getProperty(locator)));
			} else if (locator.endsWith("_css")) {
				e = driver.findElement(By.cssSelector(prop.getProperty(locator)));
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		return e;

	}

	@Override
	public void typeText(String locator) {

		getElement(locator);

	}

	@Override
	public void clickElement(String locator) {
		getElement(locator).click();

	}

	@Override
	public boolean isElementPresent(String locator) {
		if (getElement(locator).isDisplayed())
			return true;
		else
			return false;
	}

	@Override
	public String getTextElement(String locator) {
		String value = null;
		value = getElement(locator).getText();
		return value;
	}

	@Override
	public String getTimeStamp() {
		Date current = new Date();
		String day = new SimpleDateFormat("dd").format(current);
		String month = new SimpleDateFormat("MMMM").format(current);
		String year = new SimpleDateFormat("yyyy").format(current);
		String hour = new SimpleDateFormat("hh").format(current);
		String second = new SimpleDateFormat("ss").format(current);
		String desiredDate = month + year + "/" + day + "/" + hour + second;

		return desiredDate;
	}

	@Override
	public void waitToLoad() {

		js = (JavascriptExecutor) driver;
		String status = (String) js.executeScript("return document.readyState");
		while (!status.equals("complete")) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (status.equals("complete"))
				break;
		}
	}

	@Override
	public void getScreenShot() {

		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("./Screenshot/" + getTimeStamp() + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void getReports() {

		reporter = new ExtentHtmlReporter("./Reports/" + getTimeStamp() + ".html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		logger = extent.createTest("Start testing");

	}

	@Override
	public void selectValue(int i, String locator) {
		Select sel = new Select(getElement(locator));
		sel.selectByIndex(i);

	}

	@Override
	public void selectValue(String value, String locator) {
		Select sel = new Select(getElement(locator));
		sel.selectByValue(value);

	}

}
