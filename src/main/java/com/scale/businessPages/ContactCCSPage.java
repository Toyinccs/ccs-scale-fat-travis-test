package com.scale.businessPages;

import com.scale.framework.utility.ConfigurationReader;
import com.scale.framework.utility.Log;
import cucumber.api.Scenario;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactCCSPage {
    private WebDriver driver;
    private ConfigurationReader configReaderObj;
    private Logger log = Log.getLogger(ContactCCSPage.class);
    private Scenario scenario;
    private WebDriverWait wait;

    public ContactCCSPage(WebDriver driver, Scenario scenario) {
        this.driver = driver;
        this.scenario = scenario;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(this.driver, 30);
    }

    @FindBy(xpath = "//h1[@class='govuk-heading-xl page-title'][contains(text(),'Contact CCS')]")
    private WebElement cCSContactPageHeader;

    public void contactCCSPage() {
        if (cCSContactPageHeader.isDisplayed()) {
            String homeLogoText = cCSContactPageHeader.getText();
            Assert.assertTrue(homeLogoText.contains("Contact CCS"));
            log.info("User is on Contact CCS page");
        } else {
            log.info("User is not on Contact CCS page");
        }
    }
}
