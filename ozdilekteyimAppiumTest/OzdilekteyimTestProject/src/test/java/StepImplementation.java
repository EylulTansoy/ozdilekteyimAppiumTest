import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;
import java.util.List;
import java.util.Random;

public class StepImplementation extends BaseTest
{
    public void waitUntilPresence(By by)
    {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
    public JavascriptExecutor getJSExecutor()
    {
        return (JavascriptExecutor) appiumDriver;
    }
    public void scrollTo(int x, int y)
    {
        String jsStmt = String.format("window.scrollTo(%d, %d);", x, y);
        getJSExecutor().executeScript(jsStmt, true);
    }
    public void waitUntilElementClickable(By by)
    {
        System.out.println(by.toString()+" --> elementin tıklanabilir olması bekleniyor");
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitUntilElementAppear(By by)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public WebElement findElement(By by)
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public void clickElement(By by)
    {
        waitUntilPresence(by);
        WebElement element;
        element = findElement(by);
        if (!element.isDisplayed()) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY());
        }
        try {
            waitUntilElementClickable(by);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        element.click();
    }

    @Step("<time> saniye kadar bekle")
    public void waitForASecond(int time) throws InterruptedException
    {
        Thread.sleep(time*1000);
    }

    @Step("id <id> li elemente tıkla")
    public void clickById(String id)
    {
        appiumDriver.findElement(By.id(id)).click();
    }

    @Step("id <id> li elementi bul ve <text> değerini yaz")
    public void clickById(String id, String text)
    {
        appiumDriver.findElement(By.id(id)).sendKeys(text);
    }

    //Assert Kontrol
    @Step("id <id> li elementi bul ve <text> alanını kontrol et")
    public void textAreaControl(String id,String text)
    {
        Assert.assertTrue("Element text değerini içermiyor",appiumDriver.findElement(By.id(id)).getText().contains(text));
    }

    @Step("Android klavyeyi kapat")
    public void closeKeyboardOnAndroid()
    {
        appiumDriver.hideKeyboard();
    }

    @Step("<key> Sayfada rastgele bir ürüne tıkla")
    public void findProduct(String key)
    {
        List<MobileElement> product = appiumDriver.findElementsByXPath(key);
        int size = product.size();
        Random random = new Random();
        int randomNumber = random.nextInt(size);
        product.get(randomNumber).click();
    }

    @Step("<Key> xpath'li elemente tıkla")
    public void clickElementByXpath(String Key)
    {
        clickElement(By.xpath(Key));
        //appiumDriver.findElement(By.xpath(Key)).click();
        System.out.println(Key + "Elenitine tıklandı");
    }

    //https://discuss.appium.io/t/how-to-perform-scroll-swipe-top-to-bottom/19908
    @Step("Sayfadan aşağı scroll et")
    public void scrollDowm()
    {
        Dimension dimension = appiumDriver.manage().window().getSize();
        int start_x = (int) (dimension.width * 0.5);
        int start_y = (int) (dimension.height * 0.8);

        int end_x = (int) (dimension.width * 0.2);
        int end_y = (int) (dimension.height * 0.2);

        TouchAction touch = new TouchAction(appiumDriver);
        touch.press(PointOption.point(start_x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(end_x, end_y)).release().perform();
    }

    @Step("Sepete ekle")
    public void addToBasket()
    {
        boolean sizeGroupDisplayed = findElement(By.xpath("//android.widget.TextView[@resource-id='com.ozdilek.ozdilekteyim:id/tvInSizeItem']")).isDisplayed();
        boolean standartDisplayed = findElement(By.id("com.ozdilek.ozdilekteyim:id/tvInSizeItem")).isDisplayed();
        boolean gelinceHaberVer = findElement(By.xpath("//android.widget.TextView[@text='Gelince Haber Ver']")).isDisplayed();
        if(sizeGroupDisplayed)
        {
            clickElementByXpath("//androidx.recyclerview.widget.RecyclerView//android.widget.RelativeLayout[@index=0]");
            if (gelinceHaberVer)
                clickElementByXpath("//android.widget.TextView[@text='Gelince Haber Ver']");
            clickById("com.ozdilek.ozdilekteyim:id/relLayAddCartBtn");
        }
        else if(standartDisplayed){
            clickById("com.ozdilek.ozdilekteyim:id/relLayAddCartBtn");
        }
    }

}
