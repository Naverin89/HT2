import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.*;



public class TestsJenkins {
    private String WEB_DRIVER = "webdriver.chrome.driver";
    private String DRIVER_LOCATION = "E:/resources/chromedriver.exe";
    private String BASE_URL = "http://localhost:8080";
    private WebDriver driver = null;
    //   private StringBuilder errorMess = new StringBuilder(); // Future errorlist for authorisation and checkings methods
    //   private final String BUTTON_COLOR = "#4b758b";

    @BeforeClass
    public void beforeClass() {
        System.setProperty(WEB_DRIVER, DRIVER_LOCATION);
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @AfterClass
    public void afterClass() {
        //String errorOut = errorMess.toString();
        //  if (!"".equals(errorOut)) {
        //      Assert.fail(errorOut);
        //  }
        driver.quit();
    }

    @Test(priority = 1, description = "Check correct authorisation")
    public void tst_authorise() {
        SignInPage authorise = new SignInPage(driver);

        Assert.assertEquals(authorise.actualTitle, "Sign in [Jenkins]", "WRONG PAGE!!!");

        authorise.getSignIn();
    }

    @Test(dependsOnMethods={"tst_authorise"}, description = "Check for dt (Manage Users) and dd (Create/delete/modify) at Manage Jenkins link page")
    public void tst_manageJenkins() {
        DashboardPage dbPage = new DashboardPage(driver);
        dbPage.goToManageJenk();
        ManageJenkinsPage mngJenkins = new ManageJenkinsPage(driver);

        Assert.assertTrue(mngJenkins.isDtExist(), "There is no DT element with required text on this page (" + mngJenkins.getMANAGE_JENKINS_URL() + ")");
        Assert.assertTrue(mngJenkins.isDdExist(), "There is no DD element with required text on this page (" + mngJenkins.getMANAGE_JENKINS_URL() + ")");

        mngJenkins.goToManageUsers();
    }

    @Test(dependsOnMethods={"tst_manageJenkins"}, description = "Check that (Create User) link is available")
    public void tst_manageUsersLink() {
        UsersPage usPage = new UsersPage(driver);

        Assert.assertTrue(usPage.isCreateUserLinkExist(),"There isn't such link (Create User)!");

        usPage.goCreateUser();
     }

    @Test(dependsOnMethods={"tst_manageUsersLink"}, description = "Check existing and type form fields (Create User)")
    public void tst_createUser(){
        CreateUserPage createNewUser = new CreateUserPage(driver);
        Assert.assertTrue(createNewUser.isCorrectAmountAndTypeOfFields(driver), "There is " + createNewUser.textCounter + " " + createNewUser.passCounter + " password fields. Or they aren't empty");
        createNewUser.initiateNewUserCreation("someuser","somepassword","somepassword", "Some Full Name", "some@addr.dom");
     }

    @Test(dependsOnMethods = {"tst_createUser"}, description = "Check after creating new user with username (someuser) exist td element with text (someuser)")
    public void tst_someuserTextExistance(){
    Assert.fail("IN PROGRESS");
    }











}
