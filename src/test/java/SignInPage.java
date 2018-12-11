
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SignInPage extends AbsPageObject {

    private String usernameHolder = "Naverin89";
    private String passwordHolder = "123qweasdzxc";
    public String actualTitle;

    @FindBy(name = "j_username")
    private WebElement username;

    @FindBy(name = "j_password")
    private WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement signIn;


    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        actualTitle = driver.getTitle();
    }

    public SignInPage setUsername(String login) {
        username.sendKeys(login);
        return this;
    }

    public SignInPage setPassword(String pass) {
        password.sendKeys(pass);
        return this;
    }

    public SignInPage getSignIn() {
        setUsername(usernameHolder);
        setPassword(passwordHolder);
        signIn.click();
        return this;
    }

}
