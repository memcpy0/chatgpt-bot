package com.memcpy0.ai;

import com.alibaba.fastjson.JSON;
import com.memcpy0.ai.domain.chatgpt.IOpenAI;
import com.memcpy0.ai.domain.zsxq.IZsxqApi;
import com.memcpy0.ai.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.memcpy0.ai.domain.zsxq.model.vo.Topics;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class ChatgptBotApplicationTests {
	private Logger logger = LoggerFactory.getLogger(ChatgptBotApplicationTests.class);
	@Test
	void contextLoads() {
	}
	@Value("${chatgpt-bot.group01.groupId}")
	private String groupId;

	@Value("${chatgpt-bot.group01.cookie}")
	private String cookie;

	@Resource
	private IZsxqApi zsxqApi;

	@Test
	public void test_zsxqApi() throws IOException {
		UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
		logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
		List<Topics> topicsList = unAnsweredQuestionsAggregates.getResp_data().getTopics();
		for (Topics topic : topicsList) {
			String topicId = topic.getTopic_id();
			String text = topic.getQuestion().getText();
			logger.info("topicId: {} text: {}", topicId, text);
			// 回答问题，现在还是重复回答
			// zsxqApi.answer(groupId, cookie, topicId, text, false);
		}
	}

	@Value("${chatgpt-bot.group01.openAiKey}")
	private String openAiKey;
	@Value("${chatgpt-bot.group01.requestUrl}")
	private String requestUrl;

	@Autowired
	private IOpenAI openAI;
	@Test
	public void test_openAi() throws IOException {
		String response = openAI.doChatGPT(openAiKey, requestUrl, "帮我写一个java插入排序");
		logger.info("测试结果：{}", response);
	}
}
