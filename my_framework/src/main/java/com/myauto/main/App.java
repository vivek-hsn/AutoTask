package com.myauto.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello World!");
		
		try{
			
			System.out.println(System.getProperty("os.name"));
			Process p = null;
			if (System.getProperty("os.name").contains("Windows")) {
				p = Runtime.getRuntime().exec("taskkill /im chrome.exe /f");
				p = Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
				
				p = Runtime.getRuntime().exec("taskkill /im firefox.exe /f");
				p = Runtime.getRuntime().exec("taskkill /im geckodriver.exe /f");
				
				p = Runtime.getRuntime().exec("taskkill /im iexplore.exe /f");
				p = Runtime.getRuntime().exec("taskkill /im iedriver.exe /f");
				
				p = Runtime.getRuntime().exec("taskkill /im msedge.exe /f");
				p = Runtime.getRuntime().exec("taskkill /im msedgedriver.exe /f");
				
				Thread.sleep(3000);
			}
			p.destroy();
		}
		catch(Exception err){
			err.printStackTrace();
		}
		
		WebDriver driver;
		
//		WebDriverManager.edgedriver().setup();
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
//		driver = new EdgeDriver();
		
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();

	    String browserName = cap.getBrowserName();
	    String browserVersion = (String)cap.getCapability("browserVersion");
	    
	    System.out.println("BROWSER NAME IS " +browserName);
	    System.out.println("BROWSER VERSION IS " +browserVersion);
		driver.get("https://github.com/bonigarcia/webdrivermanager");
		
		try{
			String process;
			Thread.sleep(1000);
			Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((process = input.readLine()) != null) {
				System.out.println(process);
			}
			input.close();
		}
		catch(Exception err){
			err.printStackTrace();
		}
		
		
	}
}
