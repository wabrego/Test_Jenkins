package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.assertEquals;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SystemTest {
	protected WebDriver driver,driver2;
	
	@BeforeClass
	public static void setupClass() {
		
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\ernes\\Desktop\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebApp.start();
	}
	
	@AfterClass
	public static void teardownClass() {
		WebApp.stop();
	}
	
	@Before
	public void setup() {
		driver = new ChromeDriver();
		driver2 = new ChromeDriver();
	}
	
	@After
	public void teardown() {
		if(driver != null) {
			driver.quit();
		}
		if(driver2 != null) {
			driver2.quit();
		}
	}
	@Test
	public void GanaPlayer1() throws InterruptedException {
		
		driver.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");
		
		WebDriverWait wait = new WebDriverWait(driver, 10); // seconds
		
		driver.findElement(By.id("nickname")).sendKeys("Ernesto");
		driver.findElement(By.id("startBtn")).click();
		driver2.findElement(By.id("nickname")).sendKeys("Guille");
		driver2.findElement(By.id("startBtn")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("cell-0")));
		driver.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-5")).click();
		driver.findElement(By.id("cell-1")).click();
		driver2.findElement(By.id("cell-7")).click();
		driver.findElement(By.id("cell-2")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		String msg = driver.switchTo().alert().getText();
		
		assertEquals(msg,"Ernesto wins! Guille looses.");
	}
	
	@Test
	public void GanaPlayer2() throws InterruptedException {
		
		driver.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");
		
		WebDriverWait wait = new WebDriverWait(driver, 10); // seconds
		
		driver.findElement(By.id("nickname")).sendKeys("Ernesto");
		driver.findElement(By.id("startBtn")).click();
		driver2.findElement(By.id("nickname")).sendKeys("Guille");
		driver2.findElement(By.id("startBtn")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("cell-0")));
		driver.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-3")).click();
		driver.findElement(By.id("cell-1")).click();
		driver2.findElement(By.id("cell-4")).click();
		driver.findElement(By.id("cell-7")).click();
		driver2.findElement(By.id("cell-5")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		String msg = driver.switchTo().alert().getText();
		
		assertEquals(msg,"Guille wins! Ernesto looses.");
	}
	
	@Test
	public void Empate() throws InterruptedException {
		
		driver.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");
		
		WebDriverWait wait = new WebDriverWait(driver, 10); // seconds
		
		driver.findElement(By.id("nickname")).sendKeys("Ernesto");
		driver.findElement(By.id("startBtn")).click();
		driver2.findElement(By.id("nickname")).sendKeys("Guille");
		driver2.findElement(By.id("startBtn")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("cell-0")));
		driver.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-1")).click();
		driver.findElement(By.id("cell-4")).click();
		driver2.findElement(By.id("cell-2")).click();
		driver.findElement(By.id("cell-5")).click();
		driver2.findElement(By.id("cell-3")).click();
		driver.findElement(By.id("cell-6")).click();
		driver2.findElement(By.id("cell-8")).click();
		driver.findElement(By.id("cell-7")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		String msg = driver.switchTo().alert().getText();
		
		assertEquals(msg,"Draw!");
	}
}
