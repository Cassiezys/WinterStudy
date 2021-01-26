import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SpiderSearch {
    public static void main(String[] args) {
        // 1.1.环境变量找到webdriver
        System.setProperty("webdriver.chrome.driver", SpiderSearch.class.getClassLoader().getResource("chromedriver2.exe").getPath());

        // 1.2.创建webDriver
        WebDriver webDriver = new ChromeDriver();

        // 1.3.打开浏览器地址
        webDriver.get("https://baike.baidu.com/item/%E8%B4%BE%E5%AE%9D%E7%8E%89/59563");


        // 2.模拟各种点击：使用XPath：（后抽出为方法）
        /*clickOption()*/
        WebElement chosenELement = webDriver.findElement(By.xpath("//div[@class='lemma-catalog']//h2[contains(text(),'目录')]"));
        WebElement optionElement = chosenELement.findElement(By.xpath("../div[@class='catalog-list column-4']/ol/li[@class='level1']//a[contains(text(),'人际关系')]"));
        optionElement.click();


    }
}
