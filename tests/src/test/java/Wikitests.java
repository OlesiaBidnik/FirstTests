import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wikitests {
    ChromeDriver driver;

    @Before
    public void openWiki(){

        System.setProperty("webdriver.chrome.driver", "/users/OlesiaBidnik/downloads/chromedriver");
        driver = new ChromeDriver();

        driver.get("https://ru.wikipedia.org/");
    }

    @Test

    public void search(){

        driver.get("https://ru.wikipedia.org/");
        driver.findElement(By.xpath("//input[@type = 'search']")).sendKeys("Автоматизированное тестирование");
        driver.findElement(By.xpath("//input[@type = 'search']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//li[@class = 'toclevel-1 tocsection-6']/a")).click();
        driver.findElement(By.xpath("//a[text() = 'JUnit']")).click();
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("JUnit — Википедия"));
    }

    @Test

    public void userLolgin(){

        driver.get("https://ru.wikipedia.org/");
        driver.findElement(By.xpath("//a[text() = 'Войти']")).click();
        driver.findElement(By.xpath("//input[@id = 'wpName1']")).sendKeys("TestingFor");
        driver.findElement(By.xpath("//input[@id = 'wpPassword1']")).sendKeys("1234qwer");
        driver.findElement(By.xpath("//input[@id = 'wpRemember']")).click();
        driver.findElement(By.xpath("//button[text() = 'Войти']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'p-personal']")));

        driver.findElement(By.xpath("//a[text() = 'Выйти']")).click();

        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Завершение сеанса — Википедия"));
    }

    @After
    public void close(){
        driver.quit();
    }

}
