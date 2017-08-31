package diandi;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.june.bean.PostVO;
import com.june.dao.PostDao;
import com.june.util.FormatUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/config/spring-mybatis.xml" })
public class TestDao {
	
	@Autowired
	private PostDao postDao;
	
	@Test
	public void test() {
		System.out.println("test");
		List<PostVO> lists = postDao.getPosts();
		for(PostVO post:lists) {
			post.setContent(FormatUtil.filterHtml(post.getContent()));
			post.setUuid(UUID.randomUUID().toString());
		}
//	    SolrUtil.createPost(lists);
//		List <Post> lists = SolrUtil.queryPost("content", "静静", null, null, 5);
//		System.out.println(lists);
		System.out.println("end");
	}
}
