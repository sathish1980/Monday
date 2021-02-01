package New.Spicejet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class Browser {
	public static WebDriver driver;

	String chromediverpath="C:\\Users\\sathish.kumar15\\OneDrive - EY\\Desktop\\chromedriver_win32\\chromedriver.exe";
	
	
	public void launch()
	{
		System.setProperty("webdriver.chrome.driver",chromediverpath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("start-maximized");
		//driver=new HtmlUnitDriver();
		driver=new ChromeDriver(options);
		//driver =new ChromeDriver(options);
		//driver.manage().window().maximize();

	}

}
