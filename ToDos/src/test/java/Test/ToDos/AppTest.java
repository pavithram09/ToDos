package Test.ToDos;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;



public class AppTest {
	
	WebDriver driver = null; 
	
    @BeforeClass
    public void OpenBrowser() {
    	System.setProperty("webdriver.chrome.driver","/Users/Documents/Selenium/chromedriver");
    	ChromeOptions ops = new ChromeOptions();
    	ops.addArguments("--remote-allow-origins=*");
    	driver =  new ChromeDriver(ops);
    	driver.get("https://todomvc.com/examples/react/dist/");
    	driver.manage().window().maximize();
    	
    	
    }
    
  @Test(priority =1)
  public void AddToDos() throws InterruptedException {
 WebElement todo = driver.findElement(By.xpath("//input[@id='todo-input']"));
 String[] myStrings = {"design","prepare","develop","test","implement"};
 int cnt = myStrings.length;
 for(int i =0; i<cnt; i++)
 {
	 String addstr = myStrings[i];
	 todo.sendKeys(addstr);
	  todo.sendKeys(Keys.RETURN);;
	  Thread.sleep(1000); 
 }
 
 }
  @Test(priority = 2)
  public void MarkComplete() throws InterruptedException {
WebElement todo = driver.findElement(By.xpath("//input[@id='todo-input']"));
    todo.click();
    List<WebElement> l1 = driver.findElements(By.xpath("//label[@data-testid='todo-item-label']"));
    int datacnt = l1.size();
    System.out.println("Size: "+ datacnt);
    
    for(WebElement cmp : l1)
   {
    	Thread.sleep(2000);
    	String str = cmp.getText();
    //	System.out.println(str);
    	
    	if((str.equals("prepare"))){
    		
    		Thread.sleep(2000);
    		System.out.println("Item to complete:" + str);
    		cmp.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
    		Thread.sleep(1000);
    		
    		
    	}
    	
   }
    	WebElement item = driver.findElement(By.xpath("//span[@class='todo-count']"));
         System.out.println("The items left count:"+ item.getText());
     }
  
  @Test(priority=3)
	public void DisplayStatus() throws InterruptedException {
	  WebElement todo = driver.findElement(By.xpath("//input[@id='todo-input']"));
	    todo.click();
	     driver.findElement(By.xpath("//a[normalize-space()='Active']")).click();
	    Thread.sleep(2000);
	    
	    List<WebElement> l2 = driver.findElements(By.xpath("//label[@data-testid='todo-item-label']"));
	    
	    for(WebElement act : l2)
	    {
	     	Thread.sleep(2000);
	     	String str1 = act.getText();
	     	System.out.println("Active items:"+ str1);
	    }
	    
	    
	     	driver.findElement(By.xpath("//a[normalize-space()='Completed']")).click();
	     	Thread.sleep(2000);
	     	 List<WebElement> l3 = driver.findElements(By.xpath("//label[@data-testid='todo-item-label']"));
	     	  for(WebElement comp : l3)
	  	    {
	  	     	Thread.sleep(2000);
	  	     	String str2 = comp.getText();
	  	     	System.out.println("Completed items:"+ str2);
	  	    }
	     	  
	     	  driver.findElement(By.xpath("//a[normalize-space()='All']")).click();
	     		Thread.sleep(2000);
		     	 List<WebElement> l4 = driver.findElements(By.xpath("//label[@data-testid='todo-item-label']"));
		     	  for(WebElement all : l4)
		  	    {
		  	     	Thread.sleep(2000);
		  	     	String str3 = all.getText();
		  	     	System.out.println("All items:"+ str3);
		  	    } 
	  	      }
  
  @Test(priority=4)
  public void EditToDo() throws InterruptedException {
	//  WebElement todo = driver.findElement(By.xpath("//input[@id='todo-input']"));
	   // todo.click();
	    List<WebElement> l5 = driver.findElements(By.xpath("//label[@data-testid='todo-item-label']"));
	    
	    
	  
	  for(WebElement e1 : l5)
	   {
	    	Thread.sleep(1000);
	    	String edit = e1.getText();
	    
	    	if((edit.equals("develop"))){
	    		
	    	Actions actn = new Actions(driver);
	    	  actn.doubleClick(e1).perform();
	    	  actn.doubleClick();
	    	  Thread.sleep(1000);
	    	  actn.sendKeys("Code").sendKeys(Keys.RETURN).build().perform();
	    	   Thread.sleep(1000);
	    
	    	}
	    	}
	 
	  }
  @Test(priority=5)
  public void ClearCompleted() throws InterruptedException {
	  
	  driver.findElement(By.xpath("//button[contains(text(),'Clear completed')]")).click();
	  Thread.sleep(1000);
 List<WebElement> l6 = driver.findElements(By.xpath("//label[@data-testid='todo-item-label']"));
	    
	    for(WebElement cc :l6)
	   {
		  
		    String str4 = cc.getText();
		    System.out.println("ToDos after ClearCompleted:"+ str4);
		    Thread.sleep(1000);
	   }
	   }
  @Test(priority=6)
  public void MarkAllToDos() throws InterruptedException {
	  driver.findElement(By.xpath("//input[@class='toggle-all']")).click();
	  Thread.sleep(1000);
	  
	 	WebElement item = driver.findElement(By.xpath("//span[@class='todo-count']"));
        System.out.println("The items left count after all complted:"+ item.getText());
	  
  }
  
  @Test(priority=7)
  public void ClearAllToDos() throws InterruptedException {
	  
	  List<WebElement> l7 = driver.findElements(By.xpath("//label[@data-testid='todo-item-label']"));
	  
	  for(WebElement c1 : l7) {
		  c1.click();
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("(//button[@class='destroy'][1])")).click();
	  }
	  
  } 
  
  @AfterClass
  public void Close() {
	  driver.close();
	  
  }
}
