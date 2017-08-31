package diandi;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("http://gz.qiliketang.com")
@HelpUrl("http://gz.qiliketang.com")
public class GithubRepo {

    @ExtractBy(value = "//p[@class='spacing']/text()")
    private String name;

    @ExtractByUrl("http://gz.qiliketang\\.com/.*")
    private String author;

    @ExtractBy("//span[@class='mint-indicator-text']/text()")
    private String readme;

    public static void main(String[] args) {
    	System.out.println("start");
        OOSpider.create(Site.me().setSleepTime(1000)
                , new ConsolePageModelPipeline(), GithubRepo.class)
                .addUrl("http://gz.qiliketang.com").thread(5).run();
        System.out.println("end");
    }
}
