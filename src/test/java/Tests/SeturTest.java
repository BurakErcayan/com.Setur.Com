package Tests;

import Pages.SeturPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import utilities.*;
import utilities.fileIO.CsvReader;
import java.net.MalformedURLException;
import java.util.Random;

public class SeturTest {
    private static final Logger logger = LogManager.getLogger();
    private static final String DESTINATION_CITY = CsvReader.getCityName();
    private static final int PAGINATION_TRIGGER = 20;

    SeturPage sp = new SeturPage();
    Random random = new Random();

    @Test
    public void TC01() throws MalformedURLException {
        /*
        wait türleri nelerdir --- waits videosunu izle ----
3 çeşittir
hard waits, thread.sleep = ne olursa olsun o saniyeye kadar bekler sonra calısır

implicitly waits = bir duruma (kondisyona) bağlı olmadan verilen işlemi yazılan saniye kadar bekler ve verilen işlem yapılabilir olduğu
anda devam eder. eğer verilen saniye içerisidne olmazsa patlar. nosuchelementexeption verir.

explicitywaits  = bir kondisyona bağlı olarak yani duruma bağlı olarak çalışır örneğin;
X webelementi görünür olana kadar bekle - bir element dom'a eklenene kadar bekle, bir attribute bir değeri içerene kadar bekle gibi
durumları kapsar verilen süre içerisinde durum olşursa devam eder.

********* burada önemli olan 15 sn lik beklemenin 4. saniyesinde durum oluşursa devam eder buda CI-CD de yani otomasyonda testlerde gerkesiz
zaman kaybını ve gereksiz test patlamalarını engeller stabiliteyi arttırılır ****
*/




        /*
        date picker: şimdi date pickerda sorun şu: class isimleri özel olarak oluşturulmamış yani hazır komponent kullanılmış.
        ve özel QA takımı için class yada QA-data isimli bir selector eklenmediği için ve ayrıca bunun bir date picker olduğundan dolayı
        özel olarak tarih ile çalışabilecek bir datepicker locator foncksiyonu yazmaya çalıştım. Bunuda ne yazık ki bahsettiğim class durumundan dolayı
        Xpath kullanarak tarih textlerini kullanarak oluşturdum. Gayet verimli çalıştığını görünce bu fonksiyonu kullanma kara verdim.
         */

        /*
        neden bu site ZOR!!!!
        Çünkü bu site hazır komponent oluşturma toolları kullanılarak yada yardımcı developer tooları kullanılarak olusturulmus
        bunu nereden anlıyoruz, DOM'a baktığımızda bütün classlar hepsi otomatik oluşturulmuş rastgele textlerden oluşuyor.
        1- Hiç bir mantıklı class isminin olmamasından
        2- komponentlerin hepsinin aynı standart DOM yapısına iskeletine sahip olmasından örneğin div altında span tagında TEXT
        olması div altıda o TEXT ile alakalı input elementinin olması gibi bir çok benzer komponent gözlemdim.

Bu nedenle bu sitenin class isimlerinin ve DOM daki diğer attributelarin random stringlerden olusuyor olması bana projenin kapanıp açıldığında yada
herhangi bir proje restartında değişebileceğini gösterdi. Buda eğer testlerimizi otomatik üretilen attributelar üzerine dizayn etseydim
amatör bir yaklaşım olup testlerin bozulmasına ve sıksık locator değişiklikleri yapılmak zorunda kalmasına sebep olacaktı.

Peki bu durumda ne yapıalbilirdi???

Bu durumda neyazık ki yapabileceğimiz şeyler kısıtlı, sitenin tek dil üzerinden test edileceğini varsayarak Xpath'in parent
Child ilişkilerini ve text seçme konusundaki gücünü kullanarak çözmeye karar erdim.

ve testlerimi text seçmek üzerne dizayn ettim. Böylece eğer textler değiştirilmezse testlerim uzun süre attr,bute lara bağımlı kalmadan çalışacaktı

         */





        // Step 1: Open the browser and navigate to the URL
        logger.info("Step 1: Opening the browser and navigating to the URL");
        Driver.getDriver().get(ConfigReader.getProperty("seturBaseUrl"));
        ReusableMethods.waitForPageToLoad(5);

        // Step 2: Verify that Setur URL is correct
        logger.info("Step 2: Verifying that the Setur URL is correct");
        String expectedUrlIcerik = "setur.com";
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));

        // Wait for the page to load
        ReusableMethods.waitFor(2);

        // If there is a close button for ads on the homepage, close it
        // herhangi bir webelementin var olup olmadığını kontrol et.
        if (ReusableMethods.checkAnyElementIsExist(sp.HOMEPAGE_ADSIMAGE_BODY_XPATH)) {
            logger.info("Step 2.1: Closing the ad if it exists");
            ReusableMethods.highLightToElement(sp.homepageAdsImageBodyCloseButton).click();
        }

        // If there is a cookies dialog on the homepage, click "Accept All" button
        if (ReusableMethods.checkAnyElementIsExist(sp.HOMEPAGE_COOKIES_DIALOG_PAGE_XPATH)) {
            logger.info("Step 2.2: Clicking 'Accept All' button for the cookies dialog");
            ReusableMethods.highLightToElement(sp.homepageCookiesAllowAllButton).click();
        }

        // Step 3: Verify that the default tab on the homepage is "Otel"
        logger.info("Step 3: Verifying that the default tab on the homepage is 'Otel'");
        Boolean isOtelTabActive = ReusableMethods.waitForElementAttributeToContain
        (sp.homepageOtelTabOtelButton, "otel tabi", "class", "jmbIRo", 5000);
        Assert.assertTrue(isOtelTabActive);

        // Step 4: Enter "Antalya" from the CSV file into the "Where to Go?" input box
        // and click on the top option for Antalya
        logger.info("Step 4: Entering 'Antalya' into the 'Where to Go?' input box and clicking on the top option for Antalya");
        ReusableMethods.highLightToElement(sp.homepageWhereToGoInput).sendKeys(DESTINATION_CITY);
        ReusableMethods.waitFor(1);
        sp.homepageWhereToGoInput.click();
        ReusableMethods.highLightToElement(sp.homepageWhereToGoFirstCityElement).click();

        // Step 5: Select a one-week range for the first week of April in the date field
        logger.info("Step 5: Selecting a one-week range for the first week of April in the date field");
        ReusableMethods.highLightToElement(sp.checkInAndOutDates).click();
        ReusableMethods.waitFor(2);
        ReusableMethods.highLightToElement(sp.selectDate("1", "Nisan", "2024")).click();
        ReusableMethods.highLightToElement(sp.selectDate("7", "Nisan", "2024")).click();

        // Step 6: Increase the number of adults by 1 and verify the change
        logger.info("Step 6: Increasing the number of adults by 1 and verifying the change");
        ReusableMethods.highLightToElement(sp.roomPeopleInputBox).click();
        ReusableMethods.waitFor(1);
        int adultNumberBeforeInc = Integer.parseInt(sp.adultNumber.getText().trim());
        ReusableMethods.highLightToElement(sp.addAdultButton).click();
        int adultNumberAfterInc = Integer.parseInt(sp.adultNumber.getText().trim());
        Assert.assertEquals((adultNumberBeforeInc + 1), adultNumberAfterInc);

        // Step 7: Verify the visibility of the "Search" button and click it
        logger.info("Step 7: Verifying the visibility of the 'Search' button and clicking it");
        Assert.assertTrue(sp.searchButton.isDisplayed());
        ReusableMethods.highLightToElement(sp.searchButton).click();

        // Step 8: Verify that the opened URL contains the word "antalya"
        logger.info("Step 8: Verifying that the opened URL contains the word 'antalya'");
        //explicitly wait for page
        ReusableMethods.waitForPageTitleToContain("antalya", 5000);
        String actualUrlCityName = Driver.getDriver().getCurrentUrl();
        String expectedUrlCityName = "antalya";
        //senkronizasyon
        ReusableMethods.waitFor(1);
        Assert.assertTrue(actualUrlCityName.contains(expectedUrlCityName));

        // Step 9: Click on a random option in the "Show Other Regions" section
        // and record the number in the parentheses
        logger.info("Step 9: Clicking on a random option in the 'Show Other Regions' section and recording the number in the parentheses");
        ReusableMethods.waitForElementToBeClickable(sp.otherAreaElement, "bölge listesi", 5000);
        int randomIndex = random.nextInt(sp.otherAreaList.size());
        WebElement selectedArea = sp.otherAreaList.get(randomIndex);
        String selectedAreasNumber = selectedArea.getText().replaceAll("\\D", "");
        ConfigReader.setProperty("bolgeSayisi", selectedAreasNumber);
        ReusableMethods.highLightToElement(selectedArea).click();

        // Step 10: Scroll the screen until the text "Antalya Hotels and Best Antalya Hotel Prices" at the bottom of the page,
        // and check if the recorded value is equal to the one recorded in step 9.
        logger.info("Step 10: Scrolling the screen until the text 'Antalya Hotels and Best Antalya Hotel Prices' at the bottom of the page and checking if the recorded value is equal to the one recorded in step 9");
        if (Integer.parseInt(selectedAreasNumber) >= PAGINATION_TRIGGER) {
            ReusableMethods.waitForAnyElementToExistRecursively("//div[text()=' aramanızda']", 5000);
            ReusableMethods.scrollToBottomOfPage(Driver.getDriver(),sp.footerTotalOtelNumberText);
            Assert.assertEquals(sp.footerOtelNumberText.getText(), selectedAreasNumber);
            ReusableMethods.highLightToElement(sp.footerOtelNumberInfoText);
        } else {
            System.out.println("Antalya Otelleri ve En Uygun Antalya Otel Fiyatları IS NOT VISABLE : " + selectedAreasNumber);
        }
    }
}
