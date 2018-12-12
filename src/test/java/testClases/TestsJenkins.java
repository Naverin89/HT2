package testClases;

import PageObjects.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

//All test for checking Jenkins
public class TestsJenkins {
    private String WEB_DRIVER = "webdriver.chrome.driver";
    // Local link for webdriver
    private String DRIVER_LOCATION = "E:/resources/chromedriver.exe";
    private String BASE_URL = "http://localhost:8080";
    private WebDriver driver = null;
    //Collection all of the buttons colors.
    private ArrayList<String> buttonColorCollector = new ArrayList<String>();
    private final String BUTTON_COLOR_HEX = "#4b758b";


    @BeforeClass
    public void beforeClass() {
        System.setProperty(WEB_DRIVER, DRIVER_LOCATION);
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @AfterClass
    public void afterClass() {
      driver.quit();
    }

    @Test(priority = 1, description = "Check correct authorisation")
    public void tst_authorise() {
        SignInPage authorise = new SignInPage(driver);
        buttonColorCollector.add(authorise.getButton());

        Assert.assertEquals(authorise.actualTitle, "Sign in [Jenkins]", "WRONG PAGE!!!");

        authorise.getSignIn();
    }

    @Test(dependsOnMethods = {"tst_authorise"}, description = "Check for dt (Manage Users) and dd (Create/delete/modify) at Manage Jenkins link page")
    public void tst_manageJenkins() {
        DashboardPage dbPage = new DashboardPage(driver);
        dbPage.goToManageJenk();
        ManageJenkinsPage mngJenkins = new ManageJenkinsPage(driver);

        Assert.assertTrue(mngJenkins.isDtExist(), "There is no DT element with required text on this page (" + mngJenkins.getMANAGE_JENKINS_URL() + ")");
        Assert.assertTrue(mngJenkins.isDdExist(), "There is no DD element with required text on this page (" + mngJenkins.getMANAGE_JENKINS_URL() + ")");

        mngJenkins.goToManageUsers();
    }

    @Test(dependsOnMethods = {"tst_manageJenkins"}, description = "Check that (Create User) link is available")
    public void tst_manageUsersLink() {
        UsersPage usPage = new UsersPage(driver);
        Assert.assertTrue(usPage.isCreateUserLinkExist(), "There isn't such link (Create User)!");
        usPage.goCreateUser();
    }

    @Test(dependsOnMethods = {"tst_manageUsersLink"}, description = "Check existing and type form fields (Create User)")
    public void tst_createUserInputs() {
        CreateUserPage createNewUser = new CreateUserPage(driver);
        buttonColorCollector.add(createNewUser.getButton());
        Assert.assertTrue(createNewUser.isCorrectAmountAndTypeOfFields(driver), "There is " + createNewUser.textCounter + " " + createNewUser.passCounter + " text and password fields. Or they aren't empty");
        createNewUser.initiateNewUserCreation();
    }

    @Test(dependsOnMethods = {"tst_createUserInputs"}, description = "Check after creating new user with username (someuser) exist td element with text (someuser)")
    public void tst_someuserTextExistance() {
        UsersPage usPage = new UsersPage(driver);
        if (driver.getTitle().equalsIgnoreCase("Create User [Jenkins]"))
        Assert.fail("User is already exists!");

        Assert.assertTrue(usPage.isUserExist(), "User haven't been found it (Users) table");
    }

    @Test(dependsOnMethods = {"tst_someuserTextExistance"}, description = "Check appearing text after click on a link with (href = user/someuser/delete)")
    public void tst_appearingTextAfterClickingLink() {
        UsersPage usPage = new UsersPage(driver);
        buttonColorCollector.add(usPage.getButton());
        Assert.assertTrue(usPage.isWarningTextAppear(), "Warning text doesn't appear after clicking link (user/someuser/delete)");
        usPage.confirmDeleteUser();
    }

    @Test(expectedExceptions = NoSuchElementException.class, dependsOnMethods = "tst_appearingTextAfterClickingLink", description = "After clisk (Yes) button element (td text = user/someuser/delete) disapear")
    public void tst_absenceOfDeletedUser() {
        UsersPage usersPage = new UsersPage(driver);
        Assert.assertFalse(usersPage.isUserExist(), "User hadn't been deleted");
        Assert.assertFalse(usersPage.isUserDeleteLinkExist(), "User delete link is exist");
    }

    @Test(description = "Whether all of the buttons have appropriate color")
    public void tst_colorAllButtons() {
        for(String color : buttonColorCollector){
          Assert.assertTrue(Color.fromString(color).asHex().equalsIgnoreCase(BUTTON_COLOR_HEX));
        }
    }
}
