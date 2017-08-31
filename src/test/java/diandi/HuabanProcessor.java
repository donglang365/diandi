package diandi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.june.bean.CrawlerPost;
import com.june.extention.SeleniumDownloader;
import com.june.pipeline.PostPipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 花瓣网抽取器。<br>
 * 使用Selenium做页面动态渲染。<br>
 * @author code4crafter@gmail.com <br>
 * Date: 13-7-26 <br>
 * Time: 下午4:08 <br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/config/spring-mybatis.xml","classpath:/config/spring-mvc.xml" })
public class HuabanProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("http://www\\.191\\.cn/news/.*").all());    
        page.putField("title", page.getHtml().xpath("//div[@class='article-title']/html()").toString());
        page.putField("content", page.getHtml().xpath("//div[@class='article-content']/html()").toString());
    }

    @Override
    public Site getSite() {
        if (null == site) {
            site = Site.me().setDomain("191.cn").setSleepTime(0);
        }
        return site;
    }
    
    @Test
    public void test() {
    	System.getProperties().setProperty("selenuim_config", "D:/my_project/diandi/src/main/resources/config/config.ini");
    	OOSpider.create(Site.me().setSleepTime(100)
    			,new PostPipeline(),CrawlerPost.class)
    			.setDownloader(new SeleniumDownloader("C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe"))
                .addUrl("http://www.191.cn/").thread(1).run();
    }

    public static void main(String[] args) {
//    	System.getProperties().setProperty("webdriver.chrome.driver","/Users/yihua/Downloads/chromedriver");
    	System.getProperties().setProperty("selenuim_config", "D:/my_project/diandi/src/main/resources/config/config.ini");
    	OOSpider.create(Site.me().setSleepTime(100)
    			,new PostPipeline(),CrawlerPost.class)
    			.setDownloader(new SeleniumDownloader("C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe"))
                .addUrl("http://www.191.cn/").thread(1).run();
                
    }
    //http://www.191.cn/news
    //http://bbs.tianya.cn/list-470-1.shtml
    //.setDownloader(new SeleniumDownloader("C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe"))
}
