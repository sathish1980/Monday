package New.Spicejet;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Spicejetsearch extends Browser {
	int tablevalue=0;
	
	String Locationidentified="NotDone";
	
	@BeforeSuite
	public void launchbrowser()
	{
		launch();
	}
	
	@Test(priority=1)
	public void search() {
		
		
		driver.get("https://www.spicejet.com/");
		//driver.manage().window().maximize();
		//Using Explicit Wait
		WebDriverWait wait= new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='left1']//following::input)[1]")));
		//finding segments
		driver.findElement(By.xpath("(//div[@class='left1']//following::input)[1]")).click();
		List<WebElement> fromDrop=driver.findElements(By.xpath("(//div[@id='dropdownGroup1']//child::div)[1]//ul"));
		listvalue(fromDrop,"COK","from");
		//dropdownvalue(fromDrop,"selectbyvalue","CJB");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='right1']//following::input)[1]")));
		//driver.findElement(By.xpath("(//div[@class='right1']//following::input)[1]")).click();
		//List<WebElement> toDrop=driver.findElement(By.xpath("(//div[@class='right1']//following::select)[1]"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> toDrop=driver.findElements(By.xpath("(//div[@id='dropdownGroup1']//child::div)[2]//ul"));
		listvalue(toDrop,"MAA","to");
		//dropdownvalue(toDrop,"selectbyvalue","MAA");
		//select the calender
		selectcalender("15-SEPTEMBER-2020","Depart");
		selectcalender("15-November-2020","Return");
		//Passengers dropdown
		driver.findElement(By.xpath("//div[@id='divpaxinfo']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@id='adultDropdown']//following::select[1])[1]")));
	//Adult Dropdown
		WebElement adultdropdown=driver.findElement(By.xpath("(//p[@id='adultDropdown']//following::select[1])[1]"));
		dropdownvalue(adultdropdown,"selectbyvalue","3");
	//Child Dropdown
		WebElement childdropdown=driver.findElement(By.xpath("(//p[@id='adultDropdown']//following::select[1])[1]"));
		dropdownvalue(childdropdown,"selectbyvalue","3");
		
	//Currecny Dropdown
		WebElement currencydropdown=driver.findElement(By.xpath("(//div[@class='currency-dropdown']//following::select)[1]"));
		dropdownvalue(currencydropdown,"selectbyindex","1");
					
	// search button
		WebElement searchbutton=driver.findElement(By.xpath("(//span[@class='btn-find-flight-home']//child::input)[1]"));
		searchbutton.click();
		
	}
	
	
	public void dropdownvalue(WebElement dropdown,String methodname,String selectvalue)
	{
		Select s= new Select(dropdown);
		if(methodname.equalsIgnoreCase("selectbyvalue"))
		{
			s.selectByValue(selectvalue);
		}
		else if(methodname.equalsIgnoreCase("selectbyvisibletext"))
		{
			s.selectByVisibleText(selectvalue);
		}
		else if(methodname.equalsIgnoreCase("selectbyindex"))
		{
			s.selectByIndex(Integer.parseInt(selectvalue));
		}
		
	}

	public void selectcalender(String date,String Type)
	{
		
		//String date="15-SEPTEMBER-2020";
		Boolean Identifieddate=false;
		String[] datesplit=date.split("-");
		String actualdate= datesplit[0];
		String actualmon= datesplit[1];
		String actualyear= datesplit[2];
		//to click the calender icon
		/*if (Type.equalsIgnoreCase("Depart"))
		{
			driver.findElement(By.xpath("(//button[@class='ui-datepicker-trigger'])[1]")).click();
			
		}*/
		if (Type.equalsIgnoreCase("Return"))
		{
			driver.findElement(By.xpath("(//button[@class='ui-datepicker-trigger'])[2]")).click();
		}
		
		//Validating the month
			for(int mo=1;mo<=12;mo++)
			{
				String month=driver.findElement(By.xpath("(//div[@id='ui-datepicker-div']//child::div)[3]//span[1]")).getText();
				
				if(month.equalsIgnoreCase(actualmon))
				{
					tablevalue=1;
					break;
				}
				else
				{
					month= driver.findElement(By.xpath("(//div[@id='ui-datepicker-div']//child::div)[6]//span[1]")).getText();
					if(month.equalsIgnoreCase(actualmon))
					{
						tablevalue=2;
						break;
					}
					else
					{
						driver.findElement(By.xpath("//span[text()='Next']")).click();
						//month= driver.findElement(By.xpath("(//div[@id='ui-datepicker-div']//child::div)[6]//span[1]")).getText();
					}	
					}
				
			}
				
				
				List<WebElement> Tablerow= driver.findElements(By.xpath("(//table[@class='ui-datepicker-calendar'])["+tablevalue+"]//tbody//tr"));
				int tablerowsize =Tablerow.size();
				List<WebElement> Tablecolumn= driver.findElements(By.xpath("(//table[@class='ui-datepicker-calendar'])["+tablevalue+"]//tbody//tr[1]//td"));
				int tablecolumnsize =Tablecolumn.size();
				
				for( int i=1;i<=tablerowsize;i++)
				{
					for(int j=1;j<=tablecolumnsize;j++)
					{
						String classname=driver.findElement(By.xpath("(//table[@class='ui-datepicker-calendar'])["+tablevalue+"]//tbody//tr["+i+"]//td["+j+"]")).getAttribute("class");
						String Actual_classnamevalue="disabled";
						if(classname.contains(Actual_classnamevalue))
						{
						
						}
						else
						{
						String value=driver.findElement(By.xpath("(//table[@class='ui-datepicker-calendar'])["+tablevalue+"]//tbody//tr["+i+"]//td["+j+"]//a")).getText();
					
						if(Integer.parseInt(value)==Integer.parseInt(actualdate))
						{
							driver.findElement(By.xpath("(//table[@class='ui-datepicker-calendar'])["+tablevalue+"]//tbody//tr["+i+"]//td["+j+"]")).click();
							Identifieddate=true;
							break;
						}
						}
						}
					if(Identifieddate==true)
					{
						break;
					}
				}
	}

	public void listvalue(List<WebElement> fromdropdownvalue,String dropdownvalue,String fromto)
	{
		int s=1;
		int fromtovalue=1;
		int dp_size=fromdropdownvalue.size();
		if (fromto.equalsIgnoreCase("from"))
		{
			fromtovalue=1;
		}
		else if (fromto.equalsIgnoreCase("to"))
		{
			fromtovalue=2;
		}
		//four rows
		for(int i=1;i<=dp_size;i++) 
		{
			
			
			List<WebElement> Locationcount=driver.findElements(By.xpath("((//div[@id='dropdownGroup1']//child::div)["+fromtovalue+"]//ul)["+i+"]//li"));
			int Locationcountsize=Locationcount.size();
			
			//finding out the location value
			 for(int j=1;j<=Locationcountsize;j++)
			 {
				 String locationvalue=driver.findElement(By.xpath("((//div[@id='dropdownGroup1']//child::div)["+fromtovalue+"]//ul)["+i+"]//li["+j+"]//a")).getAttribute("value");
				 if(locationvalue.equalsIgnoreCase(dropdownvalue))
				 {
					 driver.findElement(By.xpath("((//div[@id='dropdownGroup1']//child::div)["+fromtovalue+"]//ul)["+i+"]//li["+j+"]//a")).click();
					 Locationidentified="Done";
					 break;
				 }
			 }
			 if (Locationidentified.equals("Done"))
			 {
				 break;
			 }
		}
	}
}
