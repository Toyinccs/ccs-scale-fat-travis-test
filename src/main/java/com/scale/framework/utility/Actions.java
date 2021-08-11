package com.scale.framework.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import cucumber.api.Scenario;
import org.apache.log4j.Logger;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;

import static org.junit.Assert.assertEquals;


public class Actions {
	 private Logger log = Log.getLogger(Actions.class);
	    protected Scenario scenario;
	    private WebDriver driver;
	    protected WebDriverWait wait;
	    private ConfigurationReader configReader;
	    private PageObjectManager pageObjectManager;

	public Actions(WebDriver driver, Scenario scenario) {
		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(this.driver, 30);
		pageObjectManager = new PageObjectManager(driver, scenario);
	}


	public void clickElement(WebElement element) {
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        element.click();
	    }

	    public void enterText(WebElement element, String text) {
	        waitForSeconds(1);
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        element.sendKeys(text);
	        element.sendKeys(Keys.TAB);
	    }

	    public void enterText(String fieldName, String value) {
	        String XPATH = ".//*[contains(text(),'" + fieldName + "')]//..//input";
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
	        clearTextBox(element);
	        element.sendKeys(value);
	        waitForSeconds(1);
	        element.sendKeys(Keys.TAB);
	        log.info("Entered " + value + " into " + fieldName);

	    }

	    public void enterText(String fieldName, int value) {
	        waitForLoad();
	        String XPATH = ".//*[contains(text(),'" + fieldName + "')]//..//input";
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
	        List<WebElement> elementList = driver.findElements(By.xpath(XPATH));
	        (elementList.get(elementList.size() - 1)).sendKeys(String.valueOf(value));
	        log.info("Entered " + value + " into " + fieldName);
	    }

	    public String getAttributeValue(WebElement element) {
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        return element.getAttribute("value");
	    }

	    public String getText(String fieldName) {
	        String XPATH = ".//*[contains(text(),'" + fieldName + "')]//..//input";
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
	        return element.getText();
	    }

	    public void selectItemFromDropDown(WebElement element, String itemName) {
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        Select dropDown = new Select(element);
	        dropDown.selectByVisibleText(itemName);
	    }

	    public void waitForElementToBeClickable(WebElement element) {
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	    }

	    public void waitForElementToBeVisible(WebElement element) {
	        wait.until(ExpectedConditions.visibilityOf(element));
	    }

	    public boolean existsElement(String xPath) {
	        waitForLoad();
	        WebElement element = driver.findElement(By.xpath(xPath));
	        System.out.println("Header: " + element.getText());

	        return true;
	    }

	    public boolean isElementEnabled(String webElementclass) {
	        waitForLoad();
	        WebElement element = driver.findElement(By.className(webElementclass));
	        boolean isElementEnabled = element.isEnabled();
	        return isElementEnabled;
	    }

	    public boolean isElementDisabled(String webElementclass) {
	        waitForLoad();
	        WebElement element = driver.findElement(By.className(webElementclass));
	        boolean isElementEnabled = !element.isEnabled();
	        return isElementEnabled;
	    }

	    public void waitForLoad() {
	        ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(pageLoadCondition);
	    }

	    public void clickElement(String elementName) {
	        String XPATH = ".//*[contains(text(),'" + elementName + "')]";
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
	        element.click();
	        log.info("Clicked on " + elementName + " element");
	        scenario.write("User Clicked on " + elementName + " option");

	    }

	    public void clickButton(String buttonName) {
	        waitForSeconds(2);
	       String XPATH = "//*[contains(text(),'" + buttonName +"')]";
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
			JavascriptExecutor executor = ((JavascriptExecutor) driver);
			executor.executeScript("arguments[0].click();", element);
//	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	     //   waitForSeconds(2);
			//element.click();
	        log.info("Clicked on " + buttonName + " button");
	        scenario.write(" User Clicked on " + buttonName + " button");
	    }

	public void clickRadioButton(String radioButtonName) {
		waitForSeconds(2);
		String XPATH = "//*[contains(text(),'"+ radioButtonName +"')]//preceding-sibling::input";
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		element.click();
		log.info("Buyer clicked on " + radioButtonName + " radio button");
		scenario.write(" Buyer clicked on " + radioButtonName + " radio button");
	}

	public void clickCheckbox(String checkboxName) {
		waitForSeconds(2);
		String XPATH = "//*[contains(text(),'"+ checkboxName +"')]//preceding-sibling::input";
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		element.click();
		log.info("Buyer clicked on " + checkboxName + " checkbox");
		scenario.write(" Buyer clicked on " + checkboxName + " checkbox");
	}




	    public void clearTextBox(WebElement element) {

	        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);

	        int textLength = element.getAttribute("value").length();
	        while (textLength > 0) {
	            actions.moveToElement(element)
	                    .click()
	                    .sendKeys(Keys.BACK_SPACE)
	                    .build()
	                    .perform();
	            textLength--;
	        }
	    }

	    public void waitForSeconds(int seconds) {
	        try {
	            Thread.sleep(seconds * 1000);
	        } catch (Exception e) {

	        }
	    }


//	    public void validateText(String expected, String elementName) {
//	        if (elementName.equalsIgnoreCase("Bank charges")) {
//	            String xpathBankCharges = "//*[contains(text(),'" + elementName + "')]/parent::div/child::div/div";
//	            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBankCharges)));
//	            String actual = element.getText();
//	            Assert.assertEquals(actual, expected);
//	        } else {
//	            String XPATH = "//*[contains(text(),'" + elementName + "')]/following::span[1]";
//	            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
//	            String actual = element.getText();
//	            Assert.assertEquals(actual, expected);
//	        }
	//
//	    }

	    public void clickElementWithDiv(String elementName) {
	        String XPATH = ".//div[contains(text(),'" + elementName + "')]";
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
	        element.click();
	    }


	    public void retryTokenAndPin(String fieldName, String value, String buttonName) {

	        waitForSeconds(3);
	        String XPATH = ".//*[contains(text(),'" + fieldName + "')]//..//input";
	        WebElement element = driver.findElement(By.xpath(XPATH));


	        element.click();
	        element.clear();
	        element.sendKeys(value);
	        //element.sendKeys(Keys.TAB);
	        clickButton(buttonName);


	    }

	    public boolean isButtonEnabled(String fieldName) {
	        waitForLoad();
	        String XPATH = ".//*[contains(text(),'" + fieldName + "')]";
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
	        WebElement element = driver.findElement(By.xpath(XPATH));
	        boolean isButtonEnabled = element.isEnabled();
	        return isButtonEnabled;
	    }

	    public boolean isButtonDisabled(String fieldName) {
	        waitForLoad();
	        String XPATH = ".//*[contains(text(),'" + fieldName + "')]";
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
	        WebElement element = driver.findElement(By.xpath(XPATH));
	        boolean isElementDisabled = !element.isEnabled();
	        return isElementDisabled;
	    }

	    public boolean isElementPresent(String fieldName) {
	        waitForLoad();
	        String XPATH = ".//*[contains(text(),'" + fieldName + "')]";
	        try {
	            driver.findElement(By.xpath(XPATH));
	            return true;
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }

	    public boolean isElementPresentByXpath(String xpath) {
	        waitForLoad();
	        try {
	            driver.findElement(By.xpath(xpath));
	            return true;
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }

	    public void clickActionsLinkOfCustomer(String customerCIN, String linkName, WebDriver webDriver) {
	        waitForSeconds(5);
	        WebElement linkElement = (new WebDriverWait(webDriver, 50))
	                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + customerCIN + "')]/following::div/a[contains(@title,'" + linkName + "')]")));
	        linkElement.click();
	    }

	    public void clickActionsLinkOfUser(String CPID, String linkIcon, WebDriver webDriver) {
	        WebElement linkElement = (new WebDriverWait(webDriver, 50))
	                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + CPID + "')]/following::div/a[contains(@title,'" + linkIcon + "')]")));
	        linkElement.click();
	    }

	    public void clickTab(String tabID) {
	        String XPATH = "//a[contains(@id,'" + tabID + "')]";
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
	        element.click();
	    }

		public void headerText(String page) {
		String pageText = driver.findElement(By.tagName("h1")).getText();
		assertEquals(pageText, page);
		log.info("Landed on" + page + "page");
		}

		public void takeSnapShot(Scenario scenario) {
			if (scenario.isFailed()) {
				//Code to take full page screenshot
				ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
				scenario.write("URL - " + driver.getCurrentUrl());
				PageSnapshot snapshot = Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS, true);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");

				try {
					ImageIO.write(snapshot.getImage(), "png", imageStream);
					imageStream.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				byte[] source = imageStream.toByteArray();
				scenario.embed(source, "image/png");
			}
		}

		public void launchPage(String url) {
			driver.navigate().to(url);
			log.info("Webpage launched" + url);
		}

		public void quitDriver() {
			driver.quit();
		}
}