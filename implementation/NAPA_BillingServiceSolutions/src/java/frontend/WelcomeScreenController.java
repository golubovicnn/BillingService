package frontend;

import java.io.IOException;

/**
 * Created by golubovic on 15.06.17.
 */
public class WelcomeScreenController {
    public void clickedSignUpButton() throws Exception {
        MainController mainC=new MainController();
        mainC.showRegisterScreen();
    }

    public void clickedLogInButton() throws IOException {
        MainController mainC = new MainController();
        mainC.showLogInScreen();
    }
}
