package diandi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.june.bean.PostVO;
/**
 * solr工具类
 * @author wuchaoqun
 *
 */
public class SolrUtil {
    private static String solrUrl = "http://39.108.155.144:8984/solr/test"; //solr 路径
    private static SolrClient client;
    private int num = 10;
    private String zkUrl;
    private String collectionName;
    
    static {
    	HttpSolrClient.Builder builder = new HttpSolrClient.Builder(solrUrl);
    	HttpSolrClient httpSolrClient = builder.build();
        httpSolrClient.setConnectionTimeout(30000);
        httpSolrClient.setDefaultMaxConnectionsPerHost(100);
        httpSolrClient.setMaxTotalConnections(100);
        httpSolrClient.setSoTimeout(30000);
        client = httpSolrClient;
    }

    private SolrClient createNewSolrClient() {
        try {
            System.out.println("server address:" + solrUrl);
            HttpSolrClient.Builder builder = new HttpSolrClient.Builder(solrUrl);
            HttpSolrClient client = builder.build();
            client.setConnectionTimeout(30000);
            client.setDefaultMaxConnectionsPerHost(100);
            client.setMaxTotalConnections(100);
            client.setSoTimeout(30000);
            return client;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private SolrClient createCouldSolrClient() {
    	CloudSolrClient.Builder builder = new CloudSolrClient.Builder();
    	builder.withSolrUrl(zkUrl);
        CloudSolrClient client = builder.build();
        client.setZkClientTimeout(30000);
        client.setZkConnectTimeout(50000);
        client.setDefaultCollection(collectionName);
        return client;
    }

    public void close() {
        try {
            client.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public SolrUtil(String solrUrl, int num) {
        this.solrUrl = solrUrl;
        this.client = createNewSolrClient();
        this.num = num;
    }

    public SolrUtil(String zkUrl, int num, String collection) {
        this.zkUrl = zkUrl;
        this.num = num;
        collectionName = collection;
        this.client = createCouldSolrClient();
    }
    
    
    public static void createPost(List<PostVO> posts) {
    	 System.out.println("======================add post ===================");
    	 Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
    	 SolrInputDocument doc = null;
    	 for(PostVO post:posts) {
    		 doc = new SolrInputDocument();
    		 doc.addField("content",post.getContent());
    		 doc.addField("id",post.getUuid() );
    		 doc.addField("postId_l", post.getPostId());
    		 doc.addField("imgUrl_s", post.getImgUrl());
    		 doc.addField("shortcontent_s", post.getShortContent());
    		 doc.addField("createTime_dt", post.getCreateTime());
    		 doc.addField("title_s", post.getTitle());
    		 doc.addField("viewCount_i", post.getViewCount());
    		 doc.addField("commentCount_i", post.getCommentCount());
    		 doc.addField("supportCount_i", post.getSupportCount());
    		 doc.addField("kind_i", post.getKind());
    		 doc.addField("system_i", post.getSystem());
    		 doc.addField("nickName_s", post.getNickname());
    		 doc.addField("logoFileName_s", post.getLogoFileName());
    		 doc.addField("imgUrls_s", post.getImages());
    		 doc.addField("userId_l", post.getUserId());
    		 docs.add(doc);
    	 }
    	 
    	 try {
             UpdateResponse rsp = client.add(docs);
             System.out
                     .println("Add doc size" + docs.size() + " result:" + rsp.getStatus() + " Qtime:" + rsp.getQTime());

             UpdateResponse rspcommit = client.commit();
             System.out.println("commit doc to index" + " result:" + rsp.getStatus() + " Qtime:" + rsp.getQTime());

         } catch (SolrServerException | IOException e) {
             e.printStackTrace();
         }
    	 
    }
    
    

    public void createDocs() {
        System.out.println("======================add doc ===================");
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        for (int i = 1; i <= num; i++) {
            SolrInputDocument doc1 = new SolrInputDocument();
            doc1.addField("id", UUID.randomUUID().toString());
            doc1.addField("name", "bean");
            doc1.addField("equIP_s", "192.168.2.104");
            doc1.addField("level_s", "4");
            doc1.addField("collectPro_s", "ffffffffffffffffffffjajajajajajdddddddddd");
            doc1.addField("sourceType_s", "miaohqaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            doc1.addField("filePath_s", "/home/xxxx/test");
            doc1.addField("filename_s", "zhonggggmaiaiadadadddddddddddddddddddddddddd");//            doc1.addField("_route_", "shard1");
            docs.add(doc1);
        }
        try {
            UpdateResponse rsp = client.add(docs);
            System.out
                    .println("Add doc size" + docs.size() + " result:" + rsp.getStatus() + " Qtime:" + rsp.getQTime());

            UpdateResponse rspcommit = client.commit();
            System.out.println("commit doc to index" + " result:" + rsp.getStatus() + " Qtime:" + rsp.getQTime());

        } catch (SolrServerException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public static List<PostVO> queryPost(String value,String orderType,String order,int num,String kind,String system,int start) {
    	 if (num == 0) {
    		num = 3; 
    	 }
    	 SolrQuery query = new SolrQuery();
         System.out.println("======================query===================");
         StringBuilder sb = new StringBuilder();
         sb.append("(").append("content").append(":").append(value);
         sb.append(" or ").append("title_s:").append(value);
         sb.append(" or ").append("shortcontent_s:").append(value).append(")");
         sb.append(" and ").append("kind_i:").append(kind);
         sb.append(" and ").append("system:").append(system);
         query.setQuery(sb.toString());
//         query.set("q", key+":"+value );
         query.set("start", start);
         query.set("rows", num);
         query.setHighlight(true);
         query.addHighlightField("content");
         query.addHighlightField("title_s");
         query.setHighlightSimplePre("<font color='green'>");//标记，高亮关键字前缀  
         query.setHighlightSimplePost("</font>");//后缀  
         if(orderType != null && order !=null) {
        	 query.set("sort", orderType+" "+order);
         }         
         try {
             QueryResponse rsp = client.query(query);             
             SolrDocumentList docs = rsp.getResults();
//             Map<String, Map<String, List<String>>> map = rsp.getHighlighting();
             System.out.println("查询内容:" + query);
             System.out.println("文档数量：" + docs.getNumFound());
             System.out.println("查询花费时间:" + rsp.getQTime());
             System.out.println("------query data:------");
             PostVO post = null;
             List<PostVO> posts = new ArrayList<PostVO>();
             for (SolrDocument doc : docs) {
            	 post = new PostVO();
            	 post.setPostId((Long) doc.getFieldValue("postId_l"));
//            	 post.setContent(map.get(doc.getFieldValue("id")).get(key));
            	 post.setContent((String) doc.getFieldValue("content"));
            	 post.setImgUrl((String) doc.getFieldValue("imgUrl_s"));
            	 post.setShortContent((String) doc.getFieldValue("shortcontent_s"));
            	 post.setCreateTime((Date) doc.getFieldValue("createTime_dt"));
            	 post.setTitle((String) doc.getFieldValue("title_s"));
            	 post.setViewCount((int) doc.getFieldValue("viewCount_i"));
            	 post.setCommentCount((int) doc.getFieldValue("commentCount_i"));
            	 post.setSupportCount((int) doc.getFieldValue("supportCount_i"));
            	 post.setKind((int) doc.getFieldValue("kind_i"));
            	 post.setSystem((int) doc.getFieldValue("system_i"));
            	 post.setLogoFileName((String) doc.getFieldValue("logoFileName_s"));
            	 post.setNickname((String) doc.getFieldValue("nickName_s"));
            	 post.setImgList(((String) doc.getFieldValue("imgUrls_s")).split(","));
//            	 post.setAvatar(PhotoUtil.getAvatarForSolr(post.getLogoFileName()));
            	 
            	 posts.add(post);
             }
             System.out.println("-----------------------");
             return posts;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
    }

    public void queryDocs() {
        SolrQuery params = new SolrQuery();
        System.out.println("======================query===================");
        params.set("q", "level_s:*");
        params.set("start", 0);
        params.set("rows", 5);
        params.set("sort", "accesstime_s desc");

        try {
            QueryResponse rsp = client.query(params);
            SolrDocumentList docs = rsp.getResults();
            System.out.println("查询内容:" + params);
            System.out.println("文档数量：" + docs.getNumFound());
            System.out.println("查询花费时间:" + rsp.getQTime());

            System.out.println("------query data:------");
            for (SolrDocument doc : docs) {
                // 多值查询
                @SuppressWarnings("unchecked")
                List<String> collectTime = (List<String>) doc.getFieldValue("collectTime");
                String clientmac_s = (String) doc.getFieldValue("level_s");
                System.out.println("collectTime:" + collectTime + "\t clientmac_s:" + clientmac_s);
            }
            System.out.println("-----------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteById(String id) {
        System.out.println("======================deleteById ===================");
        try {
            UpdateResponse rsp = client.deleteById(id);
            client.commit();
            System.out.println("delete id:" + id + " result:" + rsp.getStatus() + " Qtime:" + rsp.getQTime());
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteByQuery(String queryCon) {
        System.out.println("======================deleteByQuery ===================");
        UpdateResponse rsp;

        try {
            UpdateRequest commit = new UpdateRequest();
            commit.deleteByQuery(queryCon);
            commit.setCommitWithin(5000);
            commit.process(client);
            System.out.println("url:"+commit.getPath()+"\t xml:"+commit.getXML()+" method:"+commit.getMethod());
//            rsp = client.deleteByQuery(queryCon);
//            client.commit();
//            System.out.println("delete query:" + queryCon + " result:" + rsp.getStatus() + " Qtime:" + rsp.getQTime());
        } catch (SolrServerException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void updateField(String id,String fieldName, Object fieldValue) {
        System.out.println("======================updateField ===================");
        HashMap<String, Object> oper = new HashMap<String, Object>();
//        多值更新方法
//        List<String> mulitValues = new ArrayList<String>();
//        mulitValues.add(fieldName);
//        mulitValues.add((String)fieldValue);
        oper.put("set", fieldValue);

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", id);
        doc.addField(fieldName, oper);
        try {
            UpdateResponse rsp = client.add(doc);
            System.out.println("update doc id" + id + " result:" + rsp.getStatus() + " Qtime:" + rsp.getQTime());
            UpdateResponse rspCommit = client.commit();
            System.out.println("commit doc to index" + " result:" + rspCommit.getStatus() + " Qtime:" + rspCommit.getQTime());

        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        String url = "http://39.108.155.144:8984/solr/test";
//        String zkUrl = "127.0.0.1:9983";
//        PropertyConfigurator.configure("./etc/log4j.properties");
        SolrUtil ss = new SolrUtil(url, 2);
//        SimpleSolr sc = new SimpleSolr(zkUrl, 2, "test201607");
        // 添加文档
        ss.createDocs();
//        sc.createDocs();

        // 删除文档
//        sc.deleteById("00cda454-bd3d-4945-814f-afa7110dcd21");
//         ss.deleteByQuery("name:bean");
        
        //更新文档
        ss.updateField("bd67564f-4939-4de1-9a83-3483ebbbbbee", "name", "1233313131313");
        
//        ss.close();
        

        // 查询文档
        ss.queryDocs();
        ss.close();

      }

}