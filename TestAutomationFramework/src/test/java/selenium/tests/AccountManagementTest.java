package selenium.tests;

import org.junit.jupiter.api.BeforeEach;

public class AccountManagementTest extends BaseTestSetup {

    @BeforeEach
    public void navigateToPage() {
        accountPage.navigateToPage();
    }


}
