package PageObjects;

import PageObjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;


public class UsersPage extends AbsPageObject {

    private String buttonColor;

    //Warning message if you try to delete a user
    private String warningMessage = "Are you sure about deleting the user from Jenkins?";
    //Default username for performing tests
    private String user = "someuser";


    @FindBy(linkText = "Create User")
    private WebElement createUserLink;

    @FindBy(xpath = "//div[@id='main-panel']/form")
    private WebElement createUserForm;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@name = 'password1']")
    private WebElement passwordFirstInput;

    @FindBy(xpath = "//input[@name = 'password2']")
    private WebElement passwordSecondInput;

    @FindBy(xpath = "//input[@name = 'fullname']")
    private WebElement fullNameInput;

    @FindBy(xpath = "//input[@name = 'email']")
    private WebElement emailInput;

    @FindBy(id = "yui-gen1")
    private WebElement createButton;

    @FindBy(id = "people")
    private WebElement usersTable;

    @FindBy(xpath = "//a[@href='user/someuser/delete']")
    private WebElement deleteUserLink;

    @FindBy(name = "delete")
    private WebElement deleteUserForm;

    @FindBy(id = "yui-gen1-button")
    private WebElement deleteConfirmButton;


    public UsersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isCreateUserLinkExist() {
        if (this.createUserLink.isDisplayed()) {
            return true;
        }
        return false;
    }

    public CreateUserPage goCreateUser() {

        createUserLink.click();
        return new CreateUserPage(driver);

    }

    //Check whether user exists in usertable web element
    public boolean isUserExist() {
        try {
            WebElement td = usersTable.findElement(By.linkText(user));
            if ((td != null) && (td.isDisplayed())) {
                return true;
            }
            return false;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //Perform click on delete user link
    public void goDeleteUser() {

        deleteUserLink.click();
    }

    //Method check if the warningMessage appear
    public boolean isWarningTextAppear() {
        goDeleteUser();
        if (deleteUserForm.getText().contains(warningMessage)) {
            return true;
        }
        return false;
    }

    //Check if link to delete user exist
    public boolean isUserDeleteLinkExist() throws NoSuchElementException {

        if ((deleteUserLink != null) && (deleteUserLink.isDisplayed())) {
            return true;
        }
        return false;
    }

    //Perform deleting user from the users table
    public void confirmDeleteUser() {
        buttonColor = deleteConfirmButton.getCssValue("background-color");
        deleteConfirmButton.click();
    }

    //get button for checking it's color
    public String getButton() {
        return buttonColor;
    }

}
