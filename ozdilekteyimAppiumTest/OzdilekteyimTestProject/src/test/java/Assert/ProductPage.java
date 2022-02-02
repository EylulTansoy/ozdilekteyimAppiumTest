package Assert;

import com.thoughtworks.gauge.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;


public class ProductPage extends StepImplementation
{
    @Step("Product sayfasını kontrol etme")
    public void checkProduct()
    {
        boolean checkProductDetail = findElements(By.id("com.ozdilek.ozdilekteyim:id/imgCart")).isDisplayed();
        Assertions.assertTrue(checkProductDetail,"Product sayfası hatalı");
        logger.info("Product sayfasında olduğu doğrulandı");
    }
}
