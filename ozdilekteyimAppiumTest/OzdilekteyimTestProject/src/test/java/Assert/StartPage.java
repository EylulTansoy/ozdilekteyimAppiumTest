package Assert;

import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;


public class StartPage extends StepImplementation
{
    @Step("Açılış sayfasını kontrol et")
    public void start()
    {
        String alisveriseBaslaBtn = findElement(By.id("com.ozdilek.ozdilekteyim:id/tv_startShoppingStore")).getText();
        Assertions.assertEquals("ALIŞVERİŞE BAŞLA",alisveriseBaslaBtn,"Hatalı alışveris Butonu");
        logger.info("Uygulama düzgün olarak başladı");
    }
}
