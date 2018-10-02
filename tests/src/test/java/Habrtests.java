import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Habrtests {

    ChromeDriver driver;

    @Before
    public void openHabr(){

        System.setProperty("webdriver.chrome.driver", "/users/OlesiaBidnik/downloads/chromedriver");
        driver = new ChromeDriver();

        driver.get("https://habr.com/");

    }

    @Test

    public void searchTitle(){

        driver.findElement(By.xpath("//button[@type = 'button']")).click();
        driver.findElement(By.xpath("//input[@id = 'search-form-field']")).sendKeys("Автоматизация");
        driver.findElement(By.xpath("//input[@id = 'search-form-field']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//a[text() = 'по рейтингу']")).click();
        driver.findElement(By.xpath("//a[text() = 'Моя бабушка — программист']")).click();

        Assert.assertEquals("Моя бабушка — программист / Хабр", driver.getTitle());

    }

    @Test
    public void userLogin(){

        driver.findElement(By.xpath("//a[@id = 'login']")).click();
        driver.findElement(By.xpath("//input[@type = 'email']")).sendKeys("testing.for@bk.ru");
        driver.findElement(By.xpath("//input[@type = 'password']")).sendKeys("1234qwer");
        driver.findElement(By.xpath("//button[@type = 'submit']")).submit();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class = 'btn btn_medium btn_navbar_user-dropdown']")));

    }

    @Test

    public void addBookmark(){

        driver.findElement(By.xpath("//a[@id = 'login']")).click();
        driver.findElement(By.xpath("//input[@type = 'email']")).sendKeys("testing.for@bk.ru");
        driver.findElement(By.xpath("//input[@type = 'password']")).sendKeys("1234qwer");
        driver.findElement(By.xpath("//button[@type = 'submit']")).submit();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@class='search-form']")));

        driver.findElement(By.xpath("//button[@type = 'button']")).click();
        driver.findElement(By.xpath("//input[@id = 'search-form-field']")).sendKeys("Тестирование");
        driver.findElement(By.xpath("//input[@id = 'search-form-field']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//*[@id=\"post_182544\"]/article/h2/a")).click();
        driver.findElement(By.xpath("//button[@title = 'Добавить в закладки']")).click();

        driver.findElement(By.xpath("//button[@class = 'btn btn_medium btn_navbar_user-dropdown']")).click();
        driver.findElement(By.xpath("//a[text() = 'Закладки']")).click();
        driver.findElement(By.xpath("//button[@title = 'Убрать из закладок']")).click();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'empty-placeholder']")));

    }

    @After

    public void close(){

        driver.quit();
    }

}
