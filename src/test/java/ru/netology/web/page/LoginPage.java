package ru.netology.web.page;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.netology.web.data.DataHelper;


import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    @FindBy(css = "[data-test-id=login] input")
    private static SelenideElement loginFielg;
    @FindBy(css = "[data-test-id=password] input")
    private static SelenideElement passwordField;
    @FindBy(css = "[data-test-id=action-login]")
    private static SelenideElement loginButton;

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginFielg.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return page(VerificationPage.class);
    }
}