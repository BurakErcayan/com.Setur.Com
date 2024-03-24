package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import java.util.List;

public class SeturPage {
    /*
    1. Page Factory classı bize selenium webdriver tarafından üretilmiş ve kullanıma sunulmuştur.
    2. Bir test dizayn pattern ı olan page object model veya farklı bir dizayn patern ı olan page factory model gibi
     dizayn paternlarını framework ümüze kolaylıkla eklememize yardımcı olan methodlar bulundurur.

Mesela örnek olarak göstermek gerekirse PageFactory.initElements(Driver.getDriver(), this); bir yardımcı methodtur.
ve bu method bu page object classında bulunan bütün web elementlerin test classlarındn rahatlıkla çağırılabilmesine olanak
sağlar.

eğer PageFactory classını kullanmamış olsaydık gene page object model oluşturabilirdik ki zaten pyhton ile javascript ve
cypress ile page obecjt model oluşturmak için yeni bir constructur oluşturmalı ve o classta bir çok method olşturmalıyız ve
o methodları claslarımızda çağırmalıyız.

Bunun artıları eksileri nedir:
artıları yok normal page object model standardında artıları halen durur fakat eksileri PageFactory classını kullandığımızda
elde olacak olandan daha fazladır.
eksileri okunurluğu azaltır ve kod kalabalığı oluşturur.
örnek construtor page object model:

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        initializeElements();
    }

    // Method to initialize WebElement objects
    private void initializeElements() {
        usernameInput = driver.findElement(By.id("username"));
        passwordInput = driver.findElement(By.id("password"));
        loginButton = driver.findElement(By.id("loginButton"));
    }

örnek PageFactory page object model:

   @FindBy(xpath = "//span[text()='Otel']/ancestor::button")
    public WebElement homepageOtelTabOtelButton;





*/

    /*
    Bu classda nelerimiz var:
    WebElement var
    WebElementler var  liste!!
    String Selectorler var,
    Bylocatorlar vs bir çok farklı seçicilerimiz var.

     */


    public SeturPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Home Page → Advertising Image → Body Locator
    @FindBy(xpath = "//div[@data-product-name='RESPONSIVE_BANNER']")
    public WebElement homepageAdsImageBodyElement;

    // Home Page → Advertising Image → Body → Xpath Locator
    public static final String HOMEPAGE_ADSIMAGE_BODY_XPATH = "//div[@data-product-name='RESPONSIVE_BANNER']";

    // Home Page → Advertising Image → Body → Exit Button
    @FindBy(xpath = "//span[@class='ins-close-button']")
    public WebElement homepageAdsImageBodyCloseButton;

    // Home Page → Cookies →Xpath CONSTANT Locator
    public static final String HOMEPAGE_COOKIES_DIALOG_PAGE_XPATH = "//div[@id='CybotCookiebotDialog']";

    // Home Page → Accept All Button - Xpath Locator
    @FindBy(xpath = "//a[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")
    public WebElement homepageCookiesAllowAllButton;

    // Home Page → Otel sekmesi → Otel Butonu
    @FindBy(xpath = "//span[text()='Otel']/ancestor::button")
    public WebElement homepageOtelTabOtelButton;

    // Home Page → First Dentination City Element
    @FindBy(xpath = "//div[text()='Nereye Gideceksiniz?']/parent::div//input")
    public WebElement homepageWhereToGoInput;

    // Home Page → Selected City
    @FindBy(xpath = "//span[text()='ŞEHİR']/parent::div/following-sibling::div[1]")
    public WebElement homepageWhereToGoFirstCityElement;

    // Home Page → Checkin - Checkout Dates
    @FindBy(xpath = "//div[text()='Giriş - Çıkış Tarihleri']")
    public WebElement checkInAndOutDates;

    // Home Page → Room and Person Number Box
    @FindBy(xpath = "//span[text()='Kaç Kişi Konaklayacaksınız?']")
    public WebElement roomPeopleInputBox;

    // Home Page → Add Adult Button
    @FindBy(xpath = "(//div[@role='tabpanel']//button[@data-testid='increment-button'])[1]")
    public WebElement addAdultButton;

    // Home Page → Adult Number Icon
    @FindBy(xpath = "(//div[@role='tabpanel']//span[@data-testid='count-label'])[1]")
    public WebElement adultNumber;

    // Home Page → Search Button
    @FindBy(xpath = "//span[text()='Ara']")
    public WebElement searchButton;

    // Other areas containing the city "Antalya"
    @FindBy(xpath = "//*[text()='Antalya']/parent::div//descendant::div[@data-testid='checkbox']")
    public List<WebElement> otherAreaList;

    //  One area containing the city "Antalya"
    @FindBy(xpath = "//*[text()='Antalya']/parent::div//descendant::div[@data-testid='checkbox']")
    public WebElement otherAreaElement;

    // Information text "In your search" at the bottom of the page
    @FindBy(xpath = "//div[text()=' aramanızda']")
    public WebElement footerOtelNumberInfoText;

    // Hotel number text at the bottom of the page
    @FindBy(xpath = "//div[text()=' aramanızda']/span[2]")
    public WebElement footerOtelNumberText;

    // Method for selecting a specific date
    public static WebElement selectDate(String dayNumber, String monthName, String year) {
        String locator = String.format(
                "//td[contains(@class, 'CalendarDay') and contains(@aria-label, '%1$s %2$s %3$s')]", dayNumber, monthName, year
        );
        return Driver.getDriver().findElement(By.xpath(locator));
    }

    // Total Otel Number Text
    @FindBy(xpath = "//img[@alt='Setur Linkedin']")
    public WebElement footerTotalOtelNumberText;

}
