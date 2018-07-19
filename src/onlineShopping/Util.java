package onlineShopping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Util {
	
	//define a driver
	public static WebDriver driver;
 	

	// Setting Base URL
	public static final String BASE_URL  = "http://live.guru99.com ";
	public static final String BASE_URL2 = "http://live.guru99.com/index.php//backendlogin ";
	public static final String BASE_URL3 = "http://live.guru99.com/index.php/review/product/list/id/1/" ;
	public static final String BASE_URL4 = "http://live.guru99.com/index.php/";


	// Expected output
	public static final String EXPECT_TITLE = "THIS IS DEMO SITE";
	
	//Order No
	public static  String orderNo =null;
;

	
    
	// Time to wait when searching for a GUI element 
	public static final int WAIT_TIME = 30;
//	public static String defaultPath = "C:\Users\vt.jessie\JES\SELENIUM";
	public static String defaultPath = "C:\\Users\\vt.jessie\\JES\\SELENIUM\\";
	 static WebDriver setgecodriver( ){

		  System.setProperty("webdriver.gecko.driver","C:\\Users\\vt.jessie\\JES\\SELENIUM\\geckodriver.exe");
		  System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		  System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"C:\\Users\\vt.jessie\\JES\\SELENIUM\\tmp\\logs.txt");

		//	 String strFFBinaryPath = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
		 FirefoxProfile profile = new FirefoxProfile();
			 
			 FirefoxOptions option=new FirefoxOptions();	 
		 			 profile.setPreference("browser.download.manager.showWhenStarting",false);
					 profile.setPreference("browser.download.manager.showAlertOnComplete", false);
					 profile.setPreference("browser.download.folderList",2);
					 profile.setPreference("browser.helperApps.alwaysAsk.force", false);
					 profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			 		 profile.setPreference("browser.altClickSave", true);

					 
					 profile.setPreference("browser.download.manager.useWindow", false);
					 profile.setPreference("browser.download.manager.closeWhenDone", false);
					 profile.setPreference( "pdfjs.disabled", true );
	/*
					 profile.setPreference("browser.helperApps.neverAsk.openFile","text/csv,application/x-msexcel,application/excel;"
					 +"application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml;"
					 +"application/vnd.openxmlformats-officedocument.wordprocessingml.document;" + 
					 "application/pdf,application/onenote,application/octet-stream;"
					 +"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");*/
				
					 /*	 profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv,application/x-msexcel,application/excel;"
					 +"application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword;"
					 +"application/vnd.openxmlformats-officedocument.wordprocessingml.document;" + 
					 "application/xml,application/pdf,application/onenote,application/octet-stream,text/css,application/octet-stream;"
					 +"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");		 	
			
					
				*/	
					 
					 profile.setPreference("browser.helperApps.neverAsk.openFile",
							 "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"
									 + "application/pdf;" 
									 + "application/vnd.openxmlformats-officedocument.wordprocessingml.document;" 
									 + "text/plain,application/zip;" 
									 + "text/csv");
					 profile.setPreference("browser.helperApps.neverAsk.saveToDisk", 
							 "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"
							 + "application/pdf;" 
							 + "application/vnd.openxmlformats-officedocument.wordprocessingml.document;" 
							 + "text/plain,application/zip;" 
							 + "text/csv"); 
					 profile.setPreference("print.print_to_file", true);
					 profile.setPreference("print.always_print_silent", true);
					 
					 
					//Proxy Setting  /*   
				    //    FirefoxProfile profile = new FirefoxProfile();
				        profile.setAssumeUntrustedCertificateIssuer(false);
		 //		       profile.setEnableNativeEvents(false);
				        /*	          profile.setPreference("network.proxy.type", 1);
				         		        profile.setPreference("network.proxy.http", "localHost");
				        profile.setPreference("newtwork.proxy.http_port",3128); 
				        
	 */
			//	profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf,application/octet-stream,text/css");
				 	 profile.setPreference("print_printer", "Microsoft Print to PDF");
					//	 profile.setPreference("print_printer", "Adobe PDF");
					 profile.setPreference("browser.download.useDownloadDir",true);
				 		 
					 profile.setPreference("browser.download.dir",defaultPath );
				//	 option.setBinary(strFFBinaryPath);
					 option.setProfile(profile);	 
			 ///		 option.setAcceptUntrustedCertificates(true);

					 // Initialize Firefox driver
				 	 driver = new FirefoxDriver(option);

				  	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				 return(driver);
		 

	}
	 
	 static void getGuruAccount( WebDriver driver ) {
	//if account already exist
		 System.out.println("Hi Account...");
	 if ((driver.findElement(By.cssSelector("#email"))).isDisplayed()){
		 driver.findElement(By.cssSelector("#email")).sendKeys("vt.jessie@yahoo.com"); 
		 driver.findElement(By.cssSelector("#pass")).click(); 
		 driver.findElement(By.cssSelector("#pass")).sendKeys("Infant123"); 
		 driver.findElement(By.cssSelector("#pass")).click(); 
		 driver.findElement(By.xpath("//*[@id='send2']//*[contains(text(),'Login')]")).submit(); 

		 
	 }
	 else if ((driver.findElement(By.cssSelector("#login-form> span.Create An Account "))).isDisplayed()) {
		 driver.findElement(By.xpath("//*[@id='login-form']//*[@title='Create an Account']")).click();
		 driver.findElement(By.cssSelector("#firstname")).sendKeys("Jessie "); 
		 driver.findElement(By.cssSelector("#middlename")).sendKeys("Rani "); 
		 driver.findElement(By.cssSelector("#lastname")).sendKeys("VT "); 
		 driver.findElement(By.cssSelector("#email_address")).sendKeys("vt.jessie@yahoo.com"); 
		 driver.findElement(By.cssSelector("#firstname")).sendKeys("Jessie "); 
		 driver.findElement(By.cssSelector("#password")).sendKeys("Infant123"); 
		 driver.findElement(By.cssSelector("#confirmation")).sendKeys("Infant123"); 
		 
		 driver.findElement(By.xpath("//*[@id='form-validate']//*[contains(text(),'Register')]")).submit(); 


	 }

	 }
	 
	 static boolean isPDF(File file) throws FileNotFoundException{
	//      file = new File("Demo.pdf");
		    Scanner input = new Scanner(new FileReader(file));
		    while (input.hasNextLine()) {
		        final String checkline = input.nextLine();
		        if(checkline.contains("%PDF-")) { 
		            // a match!
		            return true;
		        }  
		    }
		    return false;
		}
	 
	 static String dateprint() throws ParseException {
		  Date  today = new Date();
		  String   result ="";
	      // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
//	      Date date = formatter.parse(date_s);
	      result = formatter.format(today);


	      // *** same for the format String below
	      SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
	      System.out.println(result );
	      return(result);
	  }
	 
	 static void  fileCreated()	 {
		Path parentFolder = Paths.get(defaultPath);
		 
		Optional<File> mostRecentFile =
		    Arrays
		        .stream(parentFolder.toFile().listFiles())
		        .max(
		            (f1, f2) -> Long.compare(f1.lastModified(),
		                f2.lastModified()));
		 
	//	Optional<File> mostRecentFolder =defaultPath;
		if (mostRecentFile.isPresent()) {
		    File mostRecent = mostRecentFile.get();
		    System.out.println("most recent is " + mostRecent.getPath());
		} else {
		    System.out.println("folder is empty!");
		}
	 }
	 
	 
	 static void scrollDown() {
		 
		 //driver = new ChromeDriver();

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	     // This  will scroll down the page by  1000 pixel vertical		
	        js.executeScript("window.scrollBy(0,1000)");
		 
	 }
	 
	 
}
