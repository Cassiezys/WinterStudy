import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SpiderSearch {
    public static void main(String[] args) {
        // 1.1.环境变量找到webdriver
        System.setProperty("webdriver.chrome.driver", SpiderSearch.class.getClassLoader().getResource("chromedriver2.exe").getPath());

        // 1.2.创建webDriver
        WebDriver webDriver = new ChromeDriver();

        // 1.3.打开浏览器地址
        webDriver.get("https://baike.baidu.com/item/%E8%B4%BE%E5%AE%9D%E7%8E%89/59563");


        /* 2.筛选条件：模拟各种点击：使用XPath：（后抽出为方法ctrl+alt+m）
        clickOptions(webDriver, choseTitle, optionTitle)*/
        clickOptions(webDriver, "目录", "人际关系");

        /* 3.爬取需要爬取的内容：解析页面元素
        * 2.只需要获取一个元素所以使用findElement
        * 3.获取很多标签使用findElements*/
        WebElement chosenElement = webDriver.findElement(By.xpath("//div[@class='para-title level-3']//h3[contains(text(),'主要亲属')]"));
        WebElement mainCharacterElement = webDriver.findElement(By.className("lemmaWgt-lemmaTitle-title")).findElement(By.xpath("h1"));
        String mainName = mainCharacterElement.getText();
        List<WebElement> relativesElements = chosenElement.findElements(By.xpath("..//following-sibling::table[1]//tbody//tr"));
        for (WebElement relativesElement : relativesElements) {
            WebElement titleElement = relativesElement.findElement(By.xpath("./th")).findElement(By.className("para"));
            String relativeTitle = titleElement.getText();
            List<WebElement> nameElements = relativesElement.findElements(By.xpath("./td//a"));
            if (nameElements.size()==1) {
                System.out.println(mainName + " - " + nameElements.get(0).getText() + " - " + relativeTitle);
                continue;
            }
            for (WebElement nameElement : nameElements) {
                String relativeName = nameElement.getText();
                System.out.println(mainName + " - " + relativeName + " - " + relativeTitle);
            }
        }
    }

    // 模拟点击；使用xpath 基本语法
    private static void clickOptions(WebDriver webDriver, String choseTitle, String optionTitle) {
        /*只需要获取一个标签所以用findElement*/
        WebElement chosenElement = webDriver.findElement(By.xpath("//div[@class='lemma-catalog']//h2[contains(text(),'" + choseTitle + "')]"));
        WebElement optionElement = chosenElement.findElement(By.xpath("../div[@class='catalog-list column-4']/ol/li[@class='level1']//a[contains(text(),'" + optionTitle + "')]"));
        optionElement.click();
    }
}
