package com.memcpy0.ai.domain.chatgpt.service;


import com.alibaba.fastjson.JSON;
import com.memcpy0.ai.domain.chatgpt.IOpenAI;
import com.memcpy0.ai.domain.chatgpt.model.aggregates.AIAnswer;
import com.memcpy0.ai.domain.chatgpt.model.vo.Choices;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author memcpy0
 * @description 实现提问方法
 */
@Service
public class OpenAI implements IOpenAI {
    private Logger logger = LoggerFactory.getLogger(OpenAI.class);

    @Override
    public String doChatGPT(String openAiKey, String requestUrl, String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 代理地址；open.aiproxy.xyz、open2.aiproxy.xyz
        HttpPost post = new HttpPost(requestUrl); // 这里请求的地址可以是https://api.openai.com/v1/chat/completions
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + openAiKey);

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"" +
                question + "\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice : choices) {
                answers.append(choice.getText());
            }
            return answers.toString();
        } else {
            throw new RuntimeException("api.openai.com Err Code is " + response.getStatusLine().getStatusCode());
        }
    }

}
