package utilities;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ReusableMethods {


    public static WebElement highLightToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        return element;
    }


    /**
     * checkAnyElementIsExist
     * <pre>
     *     XPATH standardında locator'u verilen herhangi bir elementin varlığını,
     *     görünürlüğünü kontrol eder
     * </pre>
     *
     * @param locator XPath locator for the element
     * @return true if the element is present, false otherwise
     * @author Burak Erçayan
     */
    public static boolean checkAnyElementIsExist(String locator) {
        List<WebElement> elementList = Driver.getDriver().findElements(By.xpath(locator));
        return elementList.size() > 0;
    }
    /*
    parmetre olarak string locatiri verilen bir webelementin ihtimal dahilinde web sayfasında var olup olmadığını kontrol eder
    sorun su: eğer direk webelement verseydik we web sayfasında olmasaydı webelement bulunamadı hatası alırdık. selenium önce
    webelementi bulur sonra assertionları yapar. bulamadığı bir web elementin var olup olmadığını kontrol etmez. sorun bu
    liste kullandığımızda element yoksa bile hata vermez sadece listeye eklemek webelement listesinin karakteristiği böyle çalışır
    */

    /**
     * waitForElementAttributeToContain
     * <pre>
     * WebElement'in belirtilen süre içinde belirtilen özniteliği (attribute) ve değeri içermesini bekler.
     * </pre>
     *
     * @param element             Beklenecek WebElement
     * @param elementName         WebElement'in adı (loglarda görünmesi için)
     * @param attribute           Beklenen öznitelik (attribute)
     * @param value               Beklenen değer
     * @param timeoutMilliseconds Maksimum bekleme süresi (milisaniye cinsinden)
     * @return true if the condition is met within the timeout, false otherwise
     * @autor Burak Erçayan
     */
    public static boolean waitForElementAttributeToContain(WebElement element, String elementName, String attribute, String value, int timeoutMilliseconds) {
        Duration timeout = Duration.ofMillis(timeoutMilliseconds);
        try {
            WebDriverWait customWait = new WebDriverWait(Driver.getDriver(), timeout);
            customWait.until(ExpectedConditions.attributeContains(element, attribute, value));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * waitFor
     * <pre>
     * Saniye bazında hard wait yapar.
     * </pre>
     *
     * @param sec kac saniye beklenmesini istiyorsunuz
     * @author Burak Erçayan
     */
   public static void waitFor(int sec) {
       try {
           Thread.sleep(sec * 1000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }


    /**
     * waitForPageToLoad
     * <pre>
     * Verilen Web sayfasının tam anlamıyla yüklediğini kontrol eder.
     * </pre>
     *
     * @param timeout maksimum kac saniye icerisinde sayfanin yuklenmesini
     *                istediginizi yaziniz (saniye)
     * @author Burak Erçayan
     */
    public static void waitForPageToLoad(long timeout) {
        ExpectedCondition<Boolean> expectation = driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
        }
    }

    /**
     * waitForPageTitleToContain
     * <pre>
     * Sayfa başlığının belirtilen metni içermesini bekler.
     * </pre>
     *
     * @param titlePart           Beklenen metin
     * @param timeoutMilliseconds Maksimum bekleme süresi (milisaniye cinsinden)
     * @return true eğer başlık belirtilen süre içinde belirtilen metni içerirse, aksi takdirde false
     * @autor Burak Erçayan
     */
    public static boolean waitForPageTitleToContain(String titlePart, int timeoutMilliseconds) {
        Duration timeout = Duration.ofMillis(timeoutMilliseconds);
        try {
            WebDriverWait customWait = new WebDriverWait(Driver.getDriver(), timeout);
            customWait.until(ExpectedConditions.titleContains(titlePart));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static WebElement selectDate(String dayNumber, String monthName, String year) {
        String locator = String.format("//td[contains(@class, 'CalendarDay') and contains(@aria-label, '%1$s %2$s %3$s')]", dayNumber, monthName, year);
        return Driver.getDriver().findElement(By.xpath(locator));
    }

     /**
     * Belirtilen WebElement'i görünür alana getirmek için sayfayı kaydırır.
     *
     * @param element Görünür alana getirilecek WebElement.
     * @autor Burak Erçayan
     */
  // public static void scrollToElementInView(WebElement element) {
  //     try {
  //         executeJavaScript("arguments[0].scrollIntoView(true);", new Object[]{element}, false);
  //     } catch (Exception e) {
  //         throw new RuntimeException(e);
  //     }
  // }

    /**
     * executeJavaScript
     * <pre>
     * JavaScript kodunu çalıştırır.
     * </pre>
     *
     * @param script Çalıştırılacak JavaScript kodu
     * @param args   Kod içinde kullanılacak argümanlar
     * @param log    Kodun çalıştırılma durumunu loglarda görmek için
     * @throws RuntimeException Kodun çalıştırılmasında hata oluştuğunda
     * @autor Burak Erçayan
     */
   // public static void executeJavaScript(String script, Object[] args, boolean log) {
   //     try {
   //         JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
   //         if (args.length == 0) {
   //             js.executeScript(script);
   //         } else {
   //             js.executeScript(script, args);
   //         }
   //         if (log) {
   //         }
   //     } catch (Exception e) {
   //         throw new RuntimeException(e);
   //     }
   // }

    /**
     * waitForElementToBeClickable
     * <pre>
     * WebElement'in belirtilen süre içinde tıklanabilir olmasını bekler.
     * </pre>
     *
     * @param element             Beklenecek WebElement
     * @param elementName         WebElement'in adı (loglarda görünmesi için)
     * @param timeoutMilliseconds Maksimum bekleme süresi (milisaniye cinsinden)
     * @throws TimeoutException Belirtilen süre içinde tıklanabilir olmadığında
     * @autor Burak Erçayan
     */
    public static void waitForElementToBeClickable(WebElement element, String elementName, int timeoutMilliseconds) {
        Duration timeout = Duration.ofMillis(timeoutMilliseconds);
        try {
            WebDriverWait customWait = new WebDriverWait(Driver.getDriver(), timeout);
            customWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            throw e;
        }
    }

    /**
     * Belirtilen süre içinde belirli bir lokatöre sahip herhangi bir öğenin mevcut olup olmadığını rekürsif olarak bekler.
     *
     * @param locator          Beklenen öğenin XPath lokatörü
     * @param timeoutInSeconds Maksimum bekleme süresi (saniye cinsinden)
     * @return Belirtilen süre içinde öğe bulunursa true, aksi takdirde false
     * @autor Burak Erçayan
     */
    public static boolean waitForAnyElementToExistRecursively(String locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(1));
            List<WebElement> elementList = Driver.getDriver().findElements(By.xpath(locator));

            if (elementList.size() > 0) {
                return true;
            }

            // If the list is still empty, recursively call the method
            if (timeoutInSeconds > 0) {
                Thread.sleep(1000); // Wait for 1 second
                return waitForAnyElementToExistRecursively(locator, timeoutInSeconds - 1);
            } else {
                return false; // Timeout reached
            }
        } catch (Exception e) {
            // Handle exception or log if needed
            return false;
        }
    }

    /**
     * Belirtilen WebElement'i sayfanın en altına getirmek için sayfayı kaydırır.
     *
     * @param driver  Tarayıcıyı kontrol eden WebDriver örneği.
     * @param element En altına getirilecek WebElement.
     * @autor Burak Erçayan
     */
    public static void scrollToBottomOfPage(WebDriver driver, WebElement element) {
        waitFor(2);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String script = "arguments[0].scrollIntoView(false);";
        jsExecutor.executeScript(script, element);
    }

}



