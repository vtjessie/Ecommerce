package onlineShopping;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class OrderSavedAsPDF {
	public static WebDriver driver;

  @Test
  //(dependsOnMethods = { "purchaeProd" })
  public void orderAsPDF() throws InterruptedException, AWTException, FileNotFoundException {
	  //2. Click my Account
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//*[@id='header']//span[contains(text(),'Account')]")).click(); 
		 driver.findElement(By.xpath("/*[@id='top']//a[contains(text(),'My Account')]")).click();
 
		 //3. Login  
		  Util.getGuruAccount(driver );
	         System.out.println("   Click wish List "); 

		  
	   //4. click My Wish List
		   Thread.sleep(3000);
	         WebElement ele = driver.findElement(By.xpath("/*[@id='top']//*[contains(text(),'My Orders')]"));
	         ele.click();
	         
	    //5.  Click on view order 
	         //RECEND ORDER NO
			 Thread.sleep(1000);

	         String  orderStat = driver.findElement(By.cssSelector("#my-orders-table tr td.status")).getText();
	         System.out.println("   orderStat -RECENT ORDER ...."+orderStat);
			   Thread.sleep(1000);

	         String  orderNo = driver.findElement(By.cssSelector("#my-orders-table tr td.number")).getText();
	         System.out.println("   orderNo -RECENT ORDER ...."+orderNo);
	         driver.findElement(By.linkText("View Order")).click();
	         
	    //6. Verify status PENDING
	         
	         try {	     
	         String orderStatusExp ="PENDING";
			   Thread.sleep(1000);
			   Thread.sleep(1000);


	         String orderStatusAct=driver.findElement(By.xpath("/*[@id='top']//*[@class='my-account']/div/h1")).getText();
		      AssertJUnit.assertTrue(orderStatusAct.contains(Util.orderNo));
		      System.out.println("   orderNo, Util.orderNo "+orderNo+" ...."+ Util.orderNo); 
		      System.out.println("   orderStatusAct..., Util.orderStatusExp "+orderStatusAct+" ...."+orderStatusExp); 

		      AssertJUnit.assertTrue(orderStatusAct.contains(orderStatusExp));
		      System.out.println("   orderStatusAct..., Util.orderStatusExp "+orderStatusAct+" ...."+orderStatusExp); 


	         } catch (Exception e) {
			    	e.printStackTrace();	    	
			}
	         
	       //7 Click Print Order
	         
	         driver.findElement(By.linkText("Print Order")).click();
	         
	         //8.Verify PDF  created
	         	//Login  
			//   Thread.sleep(3000);

			//  	Util.getGuruAccount(driver );
		         System.out.println("  LOGIN SUCCESS "); 
		     
		         String fileName = Util.defaultPath+"order2.pdf";
			     File filePDF	=  new File(fileName);

		      //   File filePDF	=  new File("D:\\JES\\SELENIUM\\order2.pdf");
		         StringSelection file = new StringSelection(fileName);
		         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);
			 
		         /*  java.awt.Robot robot = new java.awt.Robot();*/

		         Robot rb = new Robot();
		         rb.setAutoDelay(2000); // Similar to thread.sleep
		         rb.keyPress(KeyEvent.VK_CONTROL);
		         rb.keyPress(KeyEvent.VK_V);
		         rb.keyRelease(KeyEvent.VK_CONTROL);
		         rb.keyRelease(KeyEvent.VK_V);
		         rb.setAutoDelay(2000);
		         rb.keyPress(KeyEvent.VK_ENTER);
		         rb.setAutoDelay(2000);
		         rb.keyPress(KeyEvent.VK_TAB); 
		         rb.setAutoDelay(2000);
		         rb.keyRelease(KeyEvent.VK_TAB);	
		         rb.setAutoDelay(2000);

		         rb.keyPress(KeyEvent.VK_ENTER);
		         rb.setAutoDelay(2000);

   		         rb.keyRelease(KeyEvent.VK_ENTER);
   		         
	         String MainWindow=driver.getWindowHandle();		
	         		
	         // To handle all new opened window.				
	         Set<String> s1=driver.getWindowHandles();		
	         Iterator<String> i1=s1.iterator();		
	         		
	         while(i1.hasNext())			
	         {		
	             String ChildWindow=i1.next();		
	             		
	             if(!MainWindow.equalsIgnoreCase(ChildWindow))			
	             {    		
	                  
	                     // Switching to Child window
	                     driver.switchTo().window(ChildWindow);	  
	                    
	                   
	                                  
	 			// Closing the Child Window.
	                         driver.close();		
	             }		
	         }		
	         // Switching to Parent window i.e Main Window.
	             driver.switchTo().window(MainWindow);	
	           if   (Util.isPDF( filePDF) )
			         System.out.println("  PDF FILE IS CREATED ..... SUCCESS "); 

	         		
  }
  
  
  @BeforeTest
  void launchBrowser() {
	 driver = Util.setgecodriver();
 	 driver.get(Util.BASE_URL);
  }

  @AfterTest
  void closeBrowser(){
   	//   driver.close();
   	 //  driver.quit();
  	}
    
}
