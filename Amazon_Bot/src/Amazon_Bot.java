import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
	String emailToSend;
	SimpleDateFormat dateformatDay;	
	Login log;
	SimpleDateFormat dateFormat;
	ResultSet rs;
	Statement stmt;
	public Amazon_Bot(String username,String password,JTextArea area,String emailToSend,Login log) {
		this.username=username;
		this.password=password;
		this.area=area;
		this.emailToSend=emailToSend;
		this.log=log;
		dateformatDay = new SimpleDateFormat("EEEEE");
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		
		
	}
	public void run() {
		
										
				String currentdate="";	
				String status="";
				setDataBaseConnection();
				
				while(true) 
				{
					try {
								    
									 Thread.sleep(1000);
									 						 
								     System.out.println("Waiting");
								     System.out.println(currentdate);
			    
									 if(dateformatDay.format(new Date()).equalsIgnoreCase("Monday")&&!currentdate.equalsIgnoreCase(dateFormat.format(new Date()))) 
									 {      	setBrowser();								 			
									 			log.setVisible(true);
										 		while(true) {
										 			try {	
										 				 	Thread.sleep(1000);
															area.append("Starting Amazon login\n");											
															driver.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&marketPlaceId=ATVPDKIKX0DER&language=en_US&pageId=amzn_business_inv_website&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fref%3Dab_reg_gateway&openid.assoc_handle=amzn_business_sso_us&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&switch_account=signin&disableLoginPrepopulate=1&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&email=fsdfsfs");																	
															driver.findElement(By.id("ap_password")).sendKeys("****");																						
															onClickId("Amazon Login","signInSubmit");											
															Thread.sleep(4000);
															driver.get("https://www.amazon.com/b2b/aba/?ref_=abn_bnav_ya_ap_oh");
															area.append("Waiting for CSV to download\n");																												
															Thread.sleep(4000);
															driver.findElement(By.linkText("Orders")).click();																	
															status="";										
															Thread.sleep(4000);
															driver.findElement(By.id("download-csv-file-button")).click();
															FindFileByExtension csvfiles = new FindFileByExtension(driver,area,emailToSend,this);
															csvfiles.start();										
															suspend();
															driver.quit();
															log.setVisible(false);
															Thread.sleep(2000);
															currentdate = dateFormat.format(new Date());
															break;
										 			}catch(Exception ee) {}
										 		}
												
										}																 
							}catch(Exception ee) {ee.printStackTrace();}
						
					
				}
					
					
				
				
	}
	
	public void setBrowser() {
		deleteAllCsv();
		System.setProperty("webdriver.chrome.driver", "C:\\Jars\\chromedriver.exe");		
		HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("download.default_directory", "C:"+File.separator+"xampp"+File.separator+"mysql"+File.separator+"data"+File.separator+"amazon");
		chromePrefs.put("excludeSwitches", "enable-popup-blocking");	
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
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
	public void setDataBaseConnection() {
		log.setVisible(true);
		while(true) {
			area.append("Database connecting\n");
			System.out.println("Database connecting");
			try{  
				Thread.sleep(1500);
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/amazon","root","");  	
				 stmt=con.createStatement();
				 area.append("Database connected\n");
				 log.setVisible(false);
				 break;
				
			   }catch(Exception e){}
		}
	}
	public void deleteAllCsv() {
		
		File folder = new File("C:"+File.separator+"xampp"+File.separator+"mysql"+File.separator+"data"+File.separator+"amazon");
        File fList[] = folder.listFiles();

        for (File f : fList) {
            if (f.getName().endsWith(".csv")) {
                f.delete(); 
            }}
	}
	
	public static void main(String args[]) {
		
	}
}
