package com.memcpy0.ai.domain.chatgpt;

import java.io.IOException;

/**
 * @author memcpy0
 * @description ChatGPT open ai 接口：https://beta.openai.com/account/api-keys
 */
public interface IOpenAI {
    /**
     * 向ChatGPT提问
     * @param openAiKey
     * @param question
     * @param requestUrl
     * @return
     * @throws IOException
     */
    String doChatGPT(String openAiKey, String requestUrl, String question) throws IOException;
}
