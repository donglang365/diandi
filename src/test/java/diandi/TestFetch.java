package diandi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.june.bean.CrawlerPost;
import com.june.extention.SeleniumDownloader;
import com.june.pipeline.PostPipeline;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/config/spring-mybatis.xml" })
public class TestFetch {
	
	@Autowired
	private PostPipeline postPipeline;

	@Test
	public void test() {
		System.getProperties().setProperty("selenuim_config",
				"D:/my_project/diandi/src/main/resources/config/config.ini");
		OOSpider.create(Site.me().setSleepTime(100), postPipeline, CrawlerPost.class)
				.setDownloader(
						new SeleniumDownloader("C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe"))
				.addUrl("http://www.191.cn/").thread(1).run();
	}
}
