import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JTextArea;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Amazon_Bot extends Thread{
	WebDriver driver;
	String username;
	String password;
	JTextArea area;
	
	public Amazon_Bot(String username,String password,JTextArea area) {
		this.username=username;
		this.password=password;
		this.area=area;
		
		
	}
	public void run() {
		try {
						
			
			System.setProperty("webdriver.chrome.driver", "C:\\Jars\\chromedriver.exe");		
			HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("plugins.always_open_pdf_externally", true);
			chromePrefs.put("download.default_directory", "C:"+File.separator+"xampp"+File.separator+"mysql"+File.separator+"data"+File.separator+"amazon");
			chromePrefs.put("excludeSwitches", "enable-popup-blocking");	
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(options);
				
			
		        	area.append("Starting Amazon login\n");
		  
			driver.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&marketPlaceId=ATVPDKIKX0DER&language=en_US&pageId=amzn_business_inv_website&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fref%3Dab_reg_gateway&openid.assoc_handle=amzn_business_sso_us&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&switch_account=signin&disableLoginPrepopulate=1&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&email="+username);
			driver.findElement(By.id("ap_password")).sendKeys(password);
			onClickId("Amazon Login","signInSubmit");
			Thread.sleep(4000);
			driver.get("https://www.amazon.com/b2b/aba/?ref_=abn_bnav_ya_ap_oh");
			area.append("Waiting for CSV to download\n");
			Thread.sleep(4000);
			driver.findElement(By.linkText("Orders")).click();		
			Thread.sleep(4000);
			driver.findElement(By.id("download-csv-file-button")).click();
			
			
			FindFileByExtension csvfiles = new FindFileByExtension(driver,area);
			csvfiles.start();
			
			
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
				area.append(title+" complete\n");
				break;
			}catch(NoSuchElementException e) {System.out.println("On click ID refresh");timeouts++;driver.navigate().refresh();}
			catch(Exception ee) {}
		}
	}
	
	
	
	public static void main(String args[]) {
		
	}
}
