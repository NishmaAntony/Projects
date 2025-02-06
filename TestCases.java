package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }
    @Test
    public void testCase01() throws InterruptedException {

        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

        // name feild
        WebElement name= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[1]/div/div/div[2]/div/div[1]/div/div[1]/input")));
        name.sendKeys("Crio Learner");
        Thread.sleep(2000);

        //practing question
        long epochTime = Instant.now().getEpochSecond();
        WebElement pautomation= driver.findElement(By.xpath("//textarea[@aria-label='Your answer']"));
       pautomation.sendKeys("I want to be the best QA Engineer! " + epochTime);
       Thread.sleep(2000);
        
       // experience

        WebElement experience = driver.findElement(By.xpath("//*[@id=\"i19\"]/div[3]/div"));
        experience.click();
        Thread.sleep(2000);

        //learned in Crio.Do for Automation Testing

        WebElement learntool= driver.findElement(By.xpath("//*[@id=\"i34\"]"));
        learntool.click();
        WebElement selenium= driver.findElement(By.xpath("//*[@id=\"i37\"]"));
        selenium.click();
        WebElement testng= driver.findElement(By.xpath("//*[@id=\"i43\"]"));
        testng.click();

    //     // Select title (Mr/Mrs)
    // driver.findElement(By.xpath("//div[@role='listbox']")).click();
    // driver.findElement(By.xpath("//span[text()='Mrs']")).click();
    // Thread.sleep(2000);

        //How should you be addressed

        WebElement title=driver.findElement(By.xpath("//span[text()='Choose']"));
        title.click();
        Thread.sleep(2000);
        WebElement mytitle=driver.findElement(By.xpath("(//span[text()='Mrs'])[2]"));
        mytitle.click();
        Thread.sleep(2000);

         // Provide the current date minus 7 days
        String dateMinus7 = LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        WebElement last7days=driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[6]/div/div/div[2]/div/div/div[2]/div[1]/div/div[1]/input"));
        last7days.sendKeys(dateMinus7);
        System.out.println("past date--" + dateMinus7);
        Thread.sleep(2000);

        // Provide the time 07:30
       
       WebElement timegivenhh= driver.findElement(By.xpath("//input[@aria-label='Hour']"));
             timegivenhh.sendKeys("07");
             Thread.sleep(2000);
       WebElement timegivenmm= driver.findElement(By.xpath("//input[@aria-label='Minute']"));
timegivenmm.sendKeys("30");
       Thread.sleep(2000);
        
        // Submit the form
       WebElement submit= driver.findElement(By.xpath("//span[text()='Submit']"));
              submit.click();
              System.out.println("the google form submitted successfully");
              Thread.sleep(5000);


              WebElement submissiontext= driver.findElement(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']"));
              String text= submissiontext.getText();
              System.out.println("the text in the screen is ---" + text);
              if(submissiontext.isDisplayed())
              {
                System.out.println("the text in the screen is ---" + submissiontext);
              }
              else {
                System.out.println("Form submission failed or message not found.");
            } Thread.sleep(3000);



    }
    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}