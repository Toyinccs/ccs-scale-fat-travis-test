package com.scale.stepdefs;

import com.scale.businessPages.E2EPage;
import com.scale.businessPages.HomePage;
import com.scale.context.TestContext;
import com.scale.framework.utility.ConfigurationReader;
import com.scale.framework.utility.Log;
import com.scale.framework.utility.PageObjectManager;
import cucumber.api.Scenario;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E2ESteps {
    private Logger log = Log.getLogger(E2ESteps.class);
    private WebDriver driver;
    private PageObjectManager objectManager;
    private TestContext testContextObj;
    private Scenario scenario;
    private E2EPage e2EPageObj;
    private HomePage homePageObj;

    public E2ESteps(TestContext testContextObj) {
        this.testContextObj = testContextObj;
        driver = testContextObj.getDriver();
        objectManager = testContextObj.getObjectManager();
    }

    @When("User selects response of looking for \"([^\"]*)\"")
    public void user_selects_response_of_looking_for(String lookingFor) throws InterruptedException {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(lookingFor);
    }

    @When("User selects how much budget \"([^\"]*)\" radio button")
    public void user_selects_how_much_budget_radio_button(String budgetRadio) throws InterruptedException {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(budgetRadio);
        // e2EPageObj.selectBudget(budgetRadio);
    }

    @When("User enters budget amount \"([^\"]*)\" in the budget field")
    public void user_enters_budget_amount_in_the_budget_field(String enterBudgets) throws InterruptedException {
        e2EPageObj = objectManager.getE2EPageObj();
        e2EPageObj.enterBudget(enterBudgets);
    }

    @When("User selects how long contract \"([^\"]*)\" radio button")
    public void user_selects_how_long_contract_radio_button(String contractRadio) throws InterruptedException {
        //e2EPageObj = objectManager.getE2EPageObj();
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(contractRadio);
        //e2EPageObj.clickRadioButton(contractRadio);
    }

    @When("And User enters contract length \"([^\"]*)\" in the contract field")
    public void and_User_enters_contract_length_in_the_contract_field(String contractLength) throws InterruptedException {
        e2EPageObj = objectManager.getE2EPageObj();
        e2EPageObj.enterContractLength(contractLength);
    }

    @When("User selects service needs \"([^\"]*)\"")
    public void user_selects_service_needs(String serviceArea) throws InterruptedException {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(serviceArea);
    }

    @When("User selects additional facilities management services \"([^\"]*)\"")
    public void user_selects_additional_facilities_management_services(String addFacilities) throws InterruptedException {
        /*e2EPageObj = objectManager.getE2EPageObj();
        e2EPageObj.clickRadioButton(addFacilities);*/
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(addFacilities);
    }

    @When("User selects which sector are you looking for \"([^\"]*)\" radio button")
    public void user_selects_which_sector_are_you_looking_for_radio_button(String sectorRadio) throws InterruptedException {
        /*e2EPageObj = objectManager.getE2EPageObj();
        e2EPageObj.clickRadioButton(sectorRadio);*/
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(sectorRadio);
    }

    @When("User selects which services required \"([^\"]*)\"")
    public void user_selects_which_services_required(String serviceArea) throws InterruptedException {
        /*e2EPageObj = objectManager.getE2EPageObj();
        e2EPageObj.clickRadioButton(serviceArea);*/
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(serviceArea);

    }

    @When("User selects which services needed \"([^\"]*)\" multi selection")
    public void user_selects_which_services_needed(String serviceArea) throws InterruptedException {
        homePageObj = objectManager.getHomePageObj();
        String[] options = serviceArea.split("_");
        List<String> optionList = new ArrayList<String>(Arrays.asList(options));
        optionList.forEach((n) -> homePageObj.clickCheckBoxButton(n));
    }

    @When("User selects which location \"([^\"]*)\" multi selection")
    public void user_selects_which_location(String serviceArea) throws InterruptedException {
        homePageObj = objectManager.getHomePageObj();
        String[] options = serviceArea.split("_");
        List<String> optionList = new ArrayList<String>(Arrays.asList(options));
        optionList.forEach((n) -> homePageObj.clickCheckBoxButton(n));
    }

    @When("User selects which type of housing is required \"([^\"]*)\"")
    public void user_selects_which_type_of_housing_required(String serviceArea) throws InterruptedException {
        /*e2EPageObj = objectManager.getE2EPageObj();
        e2EPageObj.clickRadioButton(serviceArea);*/
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(serviceArea);
    }

    @When("User selects which type of housing services is required \"([^\"]*)\"")
    public void user_selects_which_type_of_housing_services_required(String serviceArea) throws InterruptedException {
        /*e2EPageObj = objectManager.getE2EPageObj();
        e2EPageObj.clickRadioButton(serviceArea);*/
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(serviceArea);
    }

    @When("User selects location \"([^\"]*)\" radio button where the service is required")
    public void user_selects_location_radio_button_where_the_service_is_required(String serviceLocation) throws InterruptedException {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(serviceLocation);
    }

    @Then("User selects \"([^\"]*)\" answer for the first question")
    public void user_performs_a_gm_journey(String answer) {
        e2EPageObj = objectManager.getE2EPageObj();
        e2EPageObj.lookingFor(answer);
   }

    @Then("User checks question content for question \"([^\"]*)\"")
    public void user_gets_question_content(String questionName) {
        e2EPageObj = objectManager.getE2EPageObj();
        ConfigurationReader configurationReader = new ConfigurationReader("config//QuestionTypesContent.properties");
        String expectedResult = configurationReader.returnProperty(questionName);
        e2EPageObj.checkQuestionContent(expectedResult);
    }

    @Then("User checks error handling content for question \"([^\"]*)\"")
    public void user_gets_error_handling_content(String questionName) {
        e2EPageObj = objectManager.getE2EPageObj();
        ConfigurationReader configurationReader = new ConfigurationReader("config//QuestionTypesContent.properties");
        String expectedResult1 = configurationReader.returnProperty(questionName + "-error");
        String expectedResult2 = configurationReader.returnProperty(questionName + "-error-sum");

        e2EPageObj.checkErrorHandlingContent(expectedResult1, expectedResult2);
    }

    @When("User selects which domain \"([^\"]*)\" are you interested radio button")
    public void user_selects_which_domain_are_you_interested_radio_button(String domainRadio) {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(domainRadio);
    }

    @When("User selects what type of security \"([^\"]*)\" is needed radio button")
    public void user_selects_what_type_of_security_is_needed_radio_button(String secTypeRadio) {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(secTypeRadio);
    }

    @When("User selects which technical security \"([^\"]*)\" is needed radio button")
    public void user_selects_which_technical_security_is_needed_radio_button(String techSecurity) {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(techSecurity);
    }

    @When("User selects which services \"([^\"]*)\" required multi choice checkboxes")
    public void user_selects_which_services_required_multi_choice_checkboxes(String string) {
        homePageObj = objectManager.getHomePageObj();
        String[] options = string.split("_");
        List<String> optionList = new ArrayList<String>(Arrays.asList(options));
        optionList.forEach((n) -> homePageObj.clickCheckBoxButton(n));
    }

    @When("User select which \"([^\"]*)\" your requirements")
    public void user_select_which_your_requirements(String areaSuits) {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(areaSuits);
    }

    @When("User selects which \"([^\"]*)\" your requirements")
    public void user_selects_which_your_requirements(String areaSuits) {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(areaSuits);
    }

    @When("User selects additional services \"([^\"]*)\" required outside of tech services")
    public void user_selects_additional_services_required_outside_of_tech_services(String addFacilities) {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(addFacilities);
    }

    @When("User selects additional FM services \"([^\"]*)\" required outside of tech services")
    public void user_selects_additional_fm_services_required_outside_of_tech_services(String addServiceRadio) {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(addServiceRadio);
    }

    @When("User selects what sector \"([^\"]*)\"are you buying for radio button")
    public void user_selects_what_sector_are_you_buying_for_radio_button(String sectorRadio) {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(sectorRadio);
    }

    @When("User selects what type of Housing \"([^\"]*)\" is needed radio button")
    public void user_selects_what_type_of_Housing_is_needed_radio_button(String housingTypeRadio) {

    }

    @When("User clicks on the back CTA")
    public void user_clicks_back_cta() {
        e2EPageObj = objectManager.getE2EPageObj();
        e2EPageObj.clickOnTheBackCTA();
    }

    @When("User selects what type \"([^\"]*)\" do you need radio button")
    public void user_selects_what_type_do_you_need_radio_button(String service) {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickRadioButton(service);
    }
}
