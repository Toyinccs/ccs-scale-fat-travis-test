package com.scale.framework.utility;

import com.scale.businessPages.*;
import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private WebDriver driver;
    private Scenario scenario;


    //Page objects
    private HomePage homePageObj;
    private GMLandingPage gmLandingPage;
    private SearchFrameworkPage searchFrameworkPage;
    private E2EPage e2EPage;
    private GMResultPage gmResultPage;
    private ContactCCSPage contactCCSPage;
    private Actions actions;


    public PageObjectManager(WebDriver driver, Scenario scenario) {
        this.driver = driver;
        this.scenario = scenario;
    }

    public HomePage getHomePageObj() {
        return homePageObj == null ? homePageObj = new HomePage(driver, scenario) : homePageObj;
    }

    public GMLandingPage getGmLandingPageObj() {
        return gmLandingPage == null ? gmLandingPage = new GMLandingPage(driver, scenario) : gmLandingPage;
    }

    public SearchFrameworkPage getSearchFrameworkPageObj() {
        return searchFrameworkPage == null ? searchFrameworkPage = new SearchFrameworkPage(driver, scenario) : searchFrameworkPage;
    }

    public E2EPage getE2EPageObj() {
        return e2EPage == null ? e2EPage = new E2EPage(driver, scenario) : e2EPage;
    }

    public GMResultPage getGmSearchResultPageObj() { return gmResultPage == null ? gmResultPage = new GMResultPage(driver, scenario) : gmResultPage; }

    public ContactCCSPage getContactCCSPageObj() { return contactCCSPage == null ? contactCCSPage = new ContactCCSPage(driver, scenario) : contactCCSPage; }

    public Actions getActions() { return actions == null ? actions = new Actions(driver, scenario) : actions; }


}
