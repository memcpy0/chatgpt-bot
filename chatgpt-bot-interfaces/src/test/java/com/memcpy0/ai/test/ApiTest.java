package com.memcpy0.ai.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 单元测试
 */
@SpringBootTest
public class ApiTest {

    @Test
    public void base64(){
        String cronExpression = new String(Base64.getDecoder().decode("MC81MCAqICogKiAqID8="), StandardCharsets.UTF_8);
        System.out.println(cronExpression);
    }

    @Test
//    public void query_unanswered_questions() throws IOException {
    public void query_all_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/48411118851818/topics?scope=unanswered_questions&count=20");

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");
        get.addHeader("cookie", "zsxq_access_token=3B470ACF-281F-A209-5EBA-BE77F5338DAE_E339FF0708AE69CC; zsxqsessionid=f962b544ae56bae22ac313aba08d0b93; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184245514142512%22%2C%22first_id%22%3A%221879ee8bcdc2a6-08832018b8da91-26031851-1572864-1879ee8bcdd3f9%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3OWVlOGJjZGMyYTYtMDg4MzIwMThiOGRhOTEtMjYwMzE4NTEtMTU3Mjg2NC0xODc5ZWU4YmNkZDNmOSIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE4NDI0NTUxNDE0MjUxMiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22184245514142512%22%7D%2C%22%24device_id%22%3A%221879ee8bcdc2a6-08832018b8da91-26031851-1572864-1879ee8bcdd3f9%22%7D");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 是否正确请求
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // https://api.zsxq.com/v2/topics/814218114541822/comments 不同的评论仅仅中间的ID不同
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/214212548841421/comments");
        post.addHeader("cookie", "zsxq_access_token=3B470ACF-281F-A209-5EBA-BE77F5338DAE_E339FF0708AE69CC; zsxqsessionid=f962b544ae56bae22ac313aba08d0b93; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184245514142512%22%2C%22first_id%22%3A%221879ee8bcdc2a6-08832018b8da91-26031851-1572864-1879ee8bcdd3f9%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3OWVlOGJjZGMyYTYtMDg4MzIwMThiOGRhOTEtMjYwMzE4NTEtMTU3Mjg2NC0xODc5ZWU4YmNkZDNmOSIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE4NDI0NTUxNDE0MjUxMiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22184245514142512%22%7D%2C%22%24device_id%22%3A%221879ee8bcdc2a6-08832018b8da91-26031851-1572864-1879ee8bcdd3f9%22%7D");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"来自程序的评论！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
//
//    @Test
//    public void test_chatGPT() throws IOException {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
//        post.addHeader("Content-Type", "application/json");
//        post.addHeader("Authorization", "Bearer 自行申请 https://beta.openai.com/overview");
//
//        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";
//
//        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
//        post.setEntity(stringEntity);
//
//        CloseableHttpResponse response = httpClient.execute(post);
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            String res = EntityUtils.toString(response.getEntity());
//            System.out.println(res);
//        } else {
//            System.out.println(response.getStatusLine().getStatusCode());
//        }
//
//    }

}
