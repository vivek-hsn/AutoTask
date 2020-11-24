package com.myauto.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.myauto.base.BaseTest;

public class TestScript extends BaseTest {
	
	
	@Test()
	public void mytest(){
		
		driver.get("https://github.com/bonigarcia/webdrivermanager");
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://github.com/bonigarcia/webdrivermanager1");
	}
	
	
	

}
