package diandi;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

@Configuration("classpath:/config/log4j.properties")
public class TestWebMagic implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		System.out.println("lala");
		System.out.println("啦啦啦");
		page.putField("author", page.getUrl().regex("http://gz.qiliketang\\.com/(\\w+)/.*").toString());
		page.putField("name", page.getHtml().xpath("//p[@class='desc line-clamp2']/text()").toString());
		System.out.println("ss");
		// 部分二：定义如何抽取页面信息，并保存下来
		/*page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
		page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
		if (page.getResultItems().get("name") == null) {
			// skip this page
			page.setSkip(true);
		}
		page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

		// 部分三：从页面发现后续的url地址来抓取
		page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());*/
	}

	/*public void process(Page page) {
		// 部分二：定义如何抽取页面信息，并保存下来
		System.out.println("startssss");
		page.putField("author", page.getUrl().regex("http://gz.qiliketang\\.com/(\\w+)/.*").toString());
		page.putField("name", page.getHtml().xpath("//p[@class='desc line-clamp2']/text()").toString());
		if (page.getResultItems().get("name") == null) {
			// skip this page
			page.setSkip(true);
		}
		page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

		// 部分三：从页面发现后续的url地址来抓取
		page.addTargetRequests(page.getHtml().links().regex("(\"http://gz.qiliketang\\.com/[\\w\\-]+/[\\w\\-]+)").all());
		System.out.println("end");
	}*/
	
	@Override
	public Site getSite() {
		return site;
	}
	
	@Test
	public void test() {
		Spider.create(new TestWebMagic())
		// 从"https://github.com/code4craft"开始抓
		.addUrl("https://github.com/code4craft")
		// 开启5个线程抓取
		.thread(5)
		// 启动爬虫
		.run();
	}

	public static void main(String[] args) {
/*//		 System.out.println("start");
//		 Spider.create(new GithubRepoPageProcessor()).addUrl("https://www.zhihu.com/search?type=people&q=农").thread(5).run();
//		 System.out.println("end");
*/		
		Spider.create(new TestWebMagic())
				// 从"https://github.com/code4craft"开始抓
				.addUrl("http://gz.qiliketang.com")
				// 开启5个线程抓取
				.thread(5)
				// 启动爬虫
				.run();
		
	}

}
