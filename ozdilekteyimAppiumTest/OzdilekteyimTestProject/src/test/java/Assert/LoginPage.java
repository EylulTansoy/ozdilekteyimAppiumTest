package Assert;

import com.thoughtworks.gauge.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class LoginPage extends StepImplementation
{
    @Step("Login page kontrol etme")
    public void checkLogin()
    {
        boolean loginButtonVisible = findElement(By.id("com.ozdilek.ozdilekteyim:id/btnLogin")).isDisplayed();
        Assertions.assertTrue(loginButtonVisible,"login sayfası dogrulanmadı");
        logger.info("login sayfası doğrulandı");
    }
}
