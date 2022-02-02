package Assert;

import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

public class ShoppingPage extends StepImplementation
{
    @Step("Alışveriş sayfasını kontrol etme")
    public void checkShopping()
    {
        String textCheck = findElement(By.id("com.ozdilek.ozdilekteyim:id/relLayStore")).getAttribute("resource-id");
        Assertions.assertEquals("com.ozdilek.ozdilekteyim:id/relLayStore",textCheck,"Alışveris sayfası düzgün çalışmıyor");
        logger.info("Alışveriş Sayfası Açıldığı Doğrulandı");
    }
}
