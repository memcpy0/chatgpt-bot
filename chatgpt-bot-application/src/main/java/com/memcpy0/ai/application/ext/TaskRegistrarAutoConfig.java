package com.memcpy0.ai.application.ext;

import com.memcpy0.ai.application.job.ChatbotTask;
import com.memcpy0.ai.common.PropertyUtil;
import com.memcpy0.ai.domain.chatgpt.IOpenAI;
import com.memcpy0.ai.domain.zsxq.IZsxqApi;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author memcpy0
 * @description 任务注册服务，支持多组任务配置，比如在多个知识星球中，抑或日后扩展到个人博客的评论回复上
 */
@Configuration
@EnableScheduling
public class TaskRegistrarAutoConfig implements EnvironmentAware, SchedulingConfigurer {
    private Logger logger = LoggerFactory.getLogger(TaskRegistrarAutoConfig.class);

    /**
     * 任务配置组
     */
    private Map<String, Map<String, Object>> taskGroupMap = new HashMap<>();

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAI openAI;

    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "chatgpt-bot.";
        String launchListStr = environment.getProperty(prefix + "launchList");
        if (StringUtils.isEmpty(launchListStr)) return;
        for (String groupKey : launchListStr.split(",")) {
            Map<String, Object> taskGroupProps = PropertyUtil.handle(environment, prefix + groupKey, Map.class);
            taskGroupMap.put(groupKey, taskGroupProps);
        }
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Set<String> taskGroups = taskGroupMap.keySet();
        for (String groupKey : taskGroups) {
            Map<String, Object> taskGroup = taskGroupMap.get(groupKey);
            String groupName = taskGroup.get("groupName").toString();
            String groupId = taskGroup.get("groupId").toString();
            String cookie = taskGroup.get("cookie").toString();
            String openAiKey = taskGroup.get("openAiKey").toString();
            String requestUrl = taskGroup.get("requestUrl").toString();
            String cronExpression = taskGroup.get("cronExpression").toString();
            // cronExpression是执行的频次配置，为了避免泄密，有时可以对配置进行加密
//            String cronExpression = new String(Base64.getDecoder().decode(cronExpressionBase64), StandardCharsets.UTF_8);
            boolean silenced = Boolean.parseBoolean(taskGroup.get("silenced").toString());
            logger.info("创建任务 groupName：{} groupId：{} cronExpression：{}", groupName, groupId, cronExpression);
            // 添加任务
            taskRegistrar.addCronTask(new ChatbotTask(groupName, groupId, cookie, openAiKey, requestUrl, zsxqApi, openAI, silenced), cronExpression);
        }
    }

}
