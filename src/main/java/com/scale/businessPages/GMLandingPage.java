package com.scale.businessPages;

import com.scale.framework.utility.ConfigurationReader;
import com.scale.framework.utility.Log;
import com.scale.framework.utility.PageObjectManager;
import cucumber.api.Scenario;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Set;

public class GMLandingPage {
    private WebDriver driver;
    private ConfigurationReader configReaderObj;
    private Logger log = Log.getLogger(GMLandingPage.class);
    private Scenario scenario;
    private WebDriverWait wait;
    private PageObjectManager pageObjectManager;


    @FindBy(xpath = "//input[@id='framework_q']")
    private WebElement enterFrameworkDetails;

    @FindBy(xpath = "//a[@class='govuk-breadcrumbs__link'][contains(text(),'Search frameworks')]")
    private WebElement searchFrameworksCta;

    @FindBy(xpath = "//h1[@class='govuk-fieldset__heading'][contains(text(),'Are you looking for a product or a service?')]")
    private WebElement firstLinenJourneyQuestionHeader;

    public GMLandingPage(WebDriver driver, Scenario scenario) {
        this.driver = driver;
        this.scenario = scenario;
        PageFactory.initElements(driver, this);
        pageObjectManager = new PageObjectManager(driver, scenario);
        this.wait = new WebDriverWait(this.driver, 30);

    }

    public void gmPage() {
        WebElement gmPage = driver.findElement(By.xpath("//li[contains(text(),'Guided match')]"));
        if (gmPage.isDisplayed()) {
            gmPage.click();
            pageObjectManager.getActions().waitForSeconds(2);
            String gmPageTest = gmPage.getText();
            Assert.assertTrue(gmPageTest.contains("Guided match"));
            log.info("User is on Guided match landing page");
            scenario.write("User is on Guided match landing page");
        } else {
            log.info("User is not on Guided match landing page");
            scenario.write("User is not on Guided match landing page");
        }
    }

    public void clickSearchFrameworksCta() {
        pageObjectManager.getActions().clickElement(searchFrameworksCta);
    }

    public void isGmJourneyOpenedInTheSameTab(){
        Set<String> allWindowHandles = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<String>(allWindowHandles);
        Assert.assertEquals("The number of the tabs is not equal with 1", 1, tabs.size());
        pageObjectManager.getActions().waitForElementToBeVisible(firstLinenJourneyQuestionHeader);
    }
}
