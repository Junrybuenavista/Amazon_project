import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Amazon_Bot {
	WebDriver driver;
	
	public Amazon_Bot() {
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Jars\\chromedriver.exe");		
			HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("plugins.always_open_pdf_externally", true);
			chromePrefs.put("download.default_directory", "C:"+File.separator+"Amazon_downloads");
			chromePrefs.put("excludeSwitches", "enable-popup-blocking");	
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(options);
			
			driver.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&marketPlaceId=ATVPDKIKX0DER&language=en_US&pageId=amzn_business_inv_website&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fref%3Dab_reg_gateway&openid.assoc_handle=amzn_business_sso_us&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&switch_account=signin&disableLoginPrepopulate=1&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&email=junrybuenavista%40htgrp.net");
			driver.findElement(By.id("ap_password")).sendKeys("Amazon123");
			onClickId("Amazon Login","signInSubmit");
			
		}catch(Exception ee) {ee.printStackTrace();}
		
	}
	public void onClickId(String title,String input) throws Exception
	{
		int timeouts = 0;
		
		while(true) {
			try {
				 if(timeouts == 0) 
					System.out.println("Starting "+title);
				 else
					 System.out.println("Timeout "+timeouts);
				 
				 driver.findElement(By.id(input)).click();
				
				System.out.println(title+" complete:");
				break;
			}catch(NoSuchElementException e) {System.out.println("On click ID refresh");timeouts++;driver.navigate().refresh();}
			catch(Exception ee) {}
		}
	}
	public static void main(String args[]) {
		new Amazon_Bot();
	}
}