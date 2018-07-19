package onlineShopping;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.apache.commons.io.FileUtils;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class MobTitVerAndSort {
	
	public static WebDriver driver;
	public int scc = 0;
  @Test
  //(priority=0 )
  //,enabled=false)

  public void mobileSort( ) throws IOException, InterruptedException {
	  
 	   System.out.println("launching firefox browser"); 
//	      System.setProperty("webdriver.firefox.marionette", driverPath);
	    
 	  // 1.Goto http://live.guru99.com
		 driver.get(Util.BASE_URL);
		 
	      String expectedTitle = Util.EXPECT_TITLE;
	      String actualTitle = driver.getTitle();
          actualTitle = driver.findElement(By.xpath("/*[@id='top']//div[@class='page-title']/h2")).getText();

	      AssertJUnit.assertTrue(actualTitle.contains(expectedTitle));
	 //     Assert.assertEquals(actualTitle, expectedTitle);
	      
	// 3. Click on  'MOBILE'  menu    
	      driver.findElement(By.linkText("Mobile")).click();
	      
	// 4.Verify the title of the page.
	      String expectedPageTitle =   "MOBILE";
	      String actualPageTitle =   driver.findElement(By.cssSelector("div.page h1")).getText();
	 	  AssertJUnit.assertEquals(actualPageTitle, expectedPageTitle);
	      System.out.println("Mobile Page selected..."+ actualPageTitle); 
	      
	 // 5.Sort by name.
			//Select drpSortBy = new Select(driver.findElement(By.name("SORT BY")));
			Select drpSortBy = new Select(driver.findElement(By.cssSelector("select[Title=\'Sort By\']"))); 
			drpSortBy.selectByVisibleText("Name");
			
	 //6. Verify sorted by name.
		    scc = (scc+1);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String png = ("D:\\JES\\SELENIUM\\PROJECTS\\Ecommerce\\OUTPUT\\Mobile Products are sorted" + scc + ".png");
	        Thread.sleep(1000);
			FileUtils.copyFile(scrFile, new File(png));
		    System.out.println("END Of MOBILE SORTING"); 

  }
  
  @Test(priority=0 ,enabled=false)
  //(priority=1  )
  //,enabled=false)
  void mobCostVer() {
	  
	  // 1.Goto http://live.guru99.com
		 driver.get(Util.BASE_URL);
		 
	  // 2. Click on  'MOBILE'  menu    
	      driver.findElement(By.linkText("Mobile")).click();
	      
	  //  3. Cost of sony Xperia

          String actualCost= driver.findElement(By.xpath("/*[@id='top']//li[@class='item last'][./a[@class='product-image' and @title= 'Xperia']]//span[@class='price']")).getText();  
       //   String actualCost= driver.findElement(By.xpath("/*[@id='top']//li[1]/div/div[1]/span/span ")).getText();
          System.out.println("cost of XPETIA "+ actualCost); 
          
        // 4. clicl Xperia Mobile
         driver.findElement(By.xpath("/*[@id='top']//li[@class='item last'][./a[@class='product-image' and @title= 'Xperia']/img[@alt='Xperia']]")).click(); 

         //5. Find cost of mobile displayed
         String displayedCost=driver.findElement(By.xpath("/*[@id='top']/body[contains(@class,'product-sony-xperia ')]//span[@class='price']")).getText();
         System.out.println("cost of XPETIA displayed "+ displayedCost); 
    	 AssertJUnit.assertEquals(actualCost, displayedCost);
         System.out.println("END OF COST VERIFICATION 2" ); 


  
  }
  
  @Test(priority=2 ,enabled=false ) 
  void mobAddToCart() {
	  // 1.Goto http://live.guru99.com
		 driver.get(Util.BASE_URL);
		 
	  // 2. Click on  'MOBILE'  menu    
	      driver.findElement(By.linkText("Mobile")).click();
	      
	  //  3. Add   sony Xperia to Caet
	      driver.findElement(By.xpath("/*[@id='top']//li[@class='item last'][./a[ @title= 'Xperia']/img[@alt='Xperia']]//*[contains(text(),'Add to Cart')]")).click();
	      
	 // 4. Enter 10000 in QTY                    
	      driver.findElement(By.xpath("/*[@id='top']//tr[@class='first last odd'][./td[@class='product-cart-image']/a [@title= 'Sony Xperia']/img[@alt='Sony Xperia']]//*[@class='input-text qty']")).sendKeys("1000");

	 //  5. Update the Qty.
	      driver.findElement(By.xpath("/*[@id='top']//*[@id='shopping-cart-table']//button[@title='Update']")).submit();
	 
	  //6. Error Msg 
	      String actErrorMsg  = driver.findElement(By.xpath("/*[@id='top']//p[@class='item-msg error']")).getText();
	      String expErrorMsg  ="* The maximum quantity allowed for purchase is 500.";
	      AssertJUnit.assertEquals(actErrorMsg, expErrorMsg);
	      
	   //7 Empty Cart
	      driver.findElement(By.id("empty_cart_button")).click();
	      
	   //8 check if it is empty
	      actErrorMsg = driver.findElement(By.xpath("*[@id='top']//*[@class='page-title']/h1")).getText();
	      expErrorMsg ="SHOPPING CART IS EMPTY";
	      AssertJUnit.assertEquals(actErrorMsg, expErrorMsg);
	      System.out.println("END OF ADD TO CART 3" ); 


  }
  
  
  
  @Test
  //(priority=-3 )
  //,enabled=false)
  void mobCompare() throws IOException, InterruptedException {
	//  Thread.sleep(5000) ;
	  // 1.Goto http://live.guru99.com
		 driver.get(Util.BASE_URL);
		 
	  // 2. Click on  'MOBILE'  menu    
	      driver.findElement(By.linkText("Mobile")).click();
	      
	  // 3. SONY Xperia is added to compare
	      driver.findElement(By.xpath("/*[@id='top']//li[@class='item last'][./a[@class='product-image' and @title= 'Xperia']/img[@alt='Xperia']]//a[@class='link-compare'  and contains(text(),'Add to Compare')]")).click();
          System.out.println("Selected XPETIA for Comparison... "); 

	  // 4. Iphone is added to compare	
	      driver.findElement(By.xpath("/*[@id='top']//li[@class='item last'][./a[@class='product-image' and @title= 'IPhone']/img[@alt='IPhone']]//a[@class='link-compare'  and contains(text(),'Add to Compare')]")).click();
          System.out.println("Selected IPhone   for Comparison... "); 
          
       //5.compare

          driver.findElement(By.xpath("/*[@id='top']//button[@class='button'  and @title='Compare']")).click();
        //  Thread.sleep(5000);
          

		//6. Verify Comparison  of selected Mobile  XPeria and  IPhone.
		    scc = (scc+1);
	        Thread.sleep(1000);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String png = ("D:\\JES\\SELENIUM\\PROJECTS\\Ecommerce\\OUTPUT\\Mobile Products are compared" + scc + ".png");
			FileUtils.copyFile(scrFile, new File(png));
          
      //  7.Close POP UP window   
          
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
                      /*
                      driver.findElement(By.name("emailid"))
                      .sendKeys("gaurav.3n@gmail.com");                			
                      
                      driver.findElement(By.name("btnLogin")).click();			
                      */
                                   
  			// Closing the Child Window.
                          driver.close();		
              }		
          }		
          // Switching to Parent window i.e Main Window.
              driver.switchTo().window(MainWindow);	
         

  }	  
  
  @Test
  //(priority= 4  )
  //(,enabled=false)
  void creatAccShareWishList() throws InterruptedException {
	  // 1.Goto http://live.guru99.com
		 driver.get(Util.BASE_URL);
		 
	  //2. Click my Account

		 driver.findElement(By.xpath("//*[@id='header']//span[contains(text(),'Account')]")).click(); 
		 driver.findElement(By.xpath("/*[@id='top']//a[contains(text(),'My Account')]")).click();
        
		  Util.getGuruAccount(driver );
		    
		 //6 go to tv menu
		 driver.findElement(By.linkText("TV")).click();
		 //Add to Wish List
		 driver.findElement(By.xpath("/*[@id='top']//li[@class='item last'][./a[contains(@href,'lg-lcd.html') and @title='LG LCD']]//*[contains(text(),'Add to Wishlist')]")).click(); 
		//To check login
		 Util.getGuruAccount(driver);
         System.out.println("   Share wish List "); 

		 //7.Share wishlist
         Thread.sleep(1000);
         
         if   ((driver.findElements(By.xpath("/*[@id='top']//form[@id='wishlist-view-form']/div/p")).size()) !=0 )
		 {
    		 driver.findElement(By.linkText("TV")).click();
    		 //Add to Wish List
    		 driver.findElement(By.xpath("/*[@id='top']//li[@class='item last'][./a[contains(@href,'lg-lcd.html') and @title='LG LCD']]//*[contains(text(),'Add to Wishlist')]")).click(); 
    		//To check login
	         System.out.println("  before Calling ACC "); 
	         Thread.sleep(3000);
	        
	         if ((driver.findElements(By.xpath("/*[@id='top']//*[contains(text(),'My Wishlist')]")).size())==0) {
	        	 
	        
	        	 Util.getGuruAccount(driver);
	         }
		 }
         	    	 
      //  	 ele =driver.findElement(By.xpath("/*[@id='top']//*[@id='wishlist-view-form']//*[contains(text(),'Add to Cart')]")) ;

      //    	 executor.executeScript("arguments[0].click();", ele);  
        
		 driver.findElement(By.xpath(".//*[@id='wishlist-view-form']//*[contains(text(),'Share Wishlist')]")).click();
		 driver.findElement(By.xpath("//*[@id='email_address']")).sendKeys("vt.jessie@gmail.com");
		 driver.findElement(By.xpath("//*[@id='message']")).sendKeys("Hi Your wish List is sent");
		 driver.findElement(By.xpath("//*[@id='form-validate']//*[contains(text(),'Share Wishlist')]")).click();
		 String msgSharedAct= driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();
		 String msgSharedExp= "Your Wishlist has been shared.";
    	 AssertJUnit.assertEquals(msgSharedAct, msgSharedExp);
         System.out.println("END OF SHARING 4" ); 


  
  }                                    

@BeforeTest
void launchBrowser() {
	 Util.setgecodriver();
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.get(Util.BASE_URL);
}
@AfterTest
void closeBrowser(){
     driver.close();
	}
  
}
