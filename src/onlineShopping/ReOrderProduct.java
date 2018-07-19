package onlineShopping;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ReOrderProduct {
	WebDriver driver;
  @Test  (priority=0 ,enabled=false)  
  public void reOrderProduct() throws InterruptedException {
	  
	  //2. Click my Account
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//*[@id='header']//span[contains(text(),'Account')]")).click(); 
		 driver.findElement(By.xpath("/*[@id='top']//a[contains(text(),'My Account')]")).click();

		 //3. Login  
		  Util.getGuruAccount(driver );
	         System.out.println("   Click getGuruAccount "); 
	         
	     //4.  Click on REORDER
	          Thread.sleep(1000);
		       String grandTot  = driver.findElement(By.cssSelector("table#my-orders-table span.price")).getText();
	       driver.findElement(By.linkText("Reorder")).click();
		   grandTot  = driver.findElement(By.cssSelector("table#shopping-cart-totals-table td.a-right  	span.price")).getText();
	      Thread.sleep(2000);
	  		System.out.println("  grandTot  NEW..."+grandTot); 

		//       Thread.sleep(1000);

	   
	       driver.findElement(By.cssSelector("td.product-cart-actions input[title=\'Qty\']")).clear();
	   //    Thread.sleep(5000);

	      driver.findElement(By.cssSelector("td.product-cart-actions input[title=\'Qty\']")).sendKeys("10");

	      driver.findElement(By.cssSelector("td.product-cart-actions button[title=\'Update\']")).submit();
	       Thread.sleep(5000);

	      String grandTotUpda  = driver.findElement(By.cssSelector("table#shopping-cart-totals-table span.price")).getText();

	      try {	     
	    	  		AssertJUnit.assertTrue(grandTot!=grandTotUpda);
	    	  		System.out.println("  grandTot..."+grandTot); 
				    System.out.println("   grandTotUpda... "+grandTotUpda); 
				    System.out.println("   grandTotUpdated Succesfully... ");


		         } catch (Exception e) {
				    	e.printStackTrace();	    	
		}
		  Thread.sleep(1000);

	      
	      driver.findElement(By.cssSelector(".cart-totals button[title=\'Proceed to Checkout\'] ")).click();
	      
	      Thread.sleep(1000);
	      driver.findElement(By.cssSelector("#billing-buttons-container .button")).click();
	      driver.findElement(By.cssSelector("#shipping-method-buttons-container .button")).click();
	      driver.findElement(By.id("p_method_checkmo")).click();
		  Thread.sleep(1000);

	      driver.findElement(By.cssSelector("#payment-buttons-container .button")).click();
	      driver.findElement(By.cssSelector("#review-buttons-container button[title=\'Place Order\']")).click();
		  Thread.sleep(1000);

	      String orderPlacedAct  = driver.findElement(By.xpath("/*[@id='top']//div[@class='page-title']/h1")).getText();
	      String orderNo         = driver.findElement(By.xpath("/*[@id='top']//a[contains(@href,'order_id')]")).getText();
		  System.out.println("   orderPlaced... "+grandTotUpda); 
		  System.out.println("   orderNo... "+orderNo); 
		  String orderPlacedExp  = "YOUR ORDER HAS BEEN RECEIVED."; 
		  try {	     
  	  		AssertJUnit.assertEquals(orderPlacedAct,orderPlacedExp);
  	  		System.out.println("  orderPlacedExp..."+orderPlacedExp); 
			System.out.println("   orderPlacedAct... "+orderPlacedAct); 


	         } catch (Exception e) {
			    	e.printStackTrace();	    	
	}


  
  }
  
  @Test 
  void verifyDiscount() throws InterruptedException {
	  // 1.Goto http://live.guru99.com
		 driver.get(Util.BASE_URL);
		 
		// 2. Click on  'MOBILE'  menu    
	      driver.findElement(By.linkText("Mobile")).click();
	      
	      
		  //  3. Add   sony IPhone to Caet
		      driver.findElement(By.xpath("/*[@id='top']//li[@class='item last'][./a[ @title= 'IPhone']/img[@alt='IPhone']]//*[contains(text(),'Add to Cart')]")).click();
		       System.out.println("   Click IPHONe "); 
		
		  // 4. Enter Coupon code GURU50
		       Thread.sleep(1000);
		       String grandTotAct = driver.findElement(By.cssSelector("#shopping-cart-totals-table span.price")).getText();
		       System.out.println("   Click grandTotAct... "+grandTotAct); 

		       driver.findElement(By.id("coupon_code")).clear();

		       driver.findElement(By.id("coupon_code")).sendKeys("GURU50");
		       driver.findElement(By.cssSelector("#discount-coupon-form button[title=\'Apply\']")).click();
		       Thread.sleep(1000);
		       String grandTotDis = driver.findElement(By.cssSelector("#shopping-cart-totals-table span.price")).getText();
		       System.out.println("   Click grandTotDis... "+grandTotDis); 
		       
		       try {	     
	    	  		AssertJUnit.assertNotSame(grandTotAct,grandTotDis);
	    	  //		System.out.println("  grandTot..."+grandTot); 
				//    System.out.println("   grandTotUpda... "+grandTotUpda); 
				//    System.out.println("   grandTotUpdated Succesfully... ");


		         } catch (Exception e) {
				    	e.printStackTrace();	
		         }

  }
  
  @BeforeTest
  void launchBrowser() {
  	  driver = Util.setgecodriver();
 // 	 driver = new FirefoxDriver();
//  	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  	 driver.get(Util.BASE_URL);
  }
  @AfterTest
  void closeBrowser(){
   	   driver.close();
  	}
    
}














