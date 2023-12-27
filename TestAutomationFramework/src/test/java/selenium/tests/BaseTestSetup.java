package selenium.tests;

import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.telerikacademy.testframework.pages.Constants.HOME_PAGE;

public class BaseTestSetup {

    UserActions actions;
    LoginPage loginPage;
    AccountPage accountPage;
    RegistrationPage registerPage;
    WomenCategoriesPage womenCategoriesPage;
    MenCategoriesPage menCategoriesPage;
    SportswearCategoriesPage sportswearCategoriesPage;
    CategoriesPage categoriesPage;

    @BeforeEach
    public void setUp() {
        actions = new UserActions();

        loginPage = new LoginPage(actions.getDriver());
        registerPage = new RegistrationPage(actions.getDriver());
        accountPage = new AccountPage(actions.getDriver());
        womenCategoriesPage = new WomenCategoriesPage(actions.getDriver());
        menCategoriesPage = new MenCategoriesPage(actions.getDriver());
        sportswearCategoriesPage = new SportswearCategoriesPage(actions.getDriver());
        categoriesPage = new CategoriesPage(actions.getDriver());


        UserActions.loadBrowser(HOME_PAGE);
    }

    @AfterEach
    public void tearDown() {
        UserActions.quitDriver();
    }
}
