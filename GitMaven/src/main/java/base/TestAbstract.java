package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;

public abstract class TestAbstract  {

	public Properties prop;
	abstract void openBrowser(String browser);
	abstract void openUrl(String appurl);
	abstract WebElement getElement(String locator);
	abstract void typeText(String locator);
	abstract void clickElement(String locator);
	abstract boolean isElementPresent(String locator);
	abstract String getTextElement(String locator);
	abstract String getTimeStamp();
	abstract void waitToLoad();
	abstract void getScreenShot();
	abstract void getReports();
	abstract void selectValue(int i,String locator);
	abstract void selectValue(String value,String locator);
	TestAbstract(){
		
		prop=new Properties();
		
		try {
			FileInputStream file=new FileInputStream("./src/main/resources/OR.properties");
			try {
				prop.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
