package com.memcpy0.ai.domain.zsxq.model.res;


import com.memcpy0.ai.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * @author memcpy0
 * @description 回复评论的JSON结果数据
 */

/* 评论的回复JSON
{
    "succeeded": true,
    "resp_data": {
        "comment": {
            "comment_id": 411821884242448,
            "create_time": "2023-05-05T13:35:32.060+0800",
            "owner": {
                "user_id": 184245514142512,
                "name": "张平",
                "avatar_url": "https://images.zsxq.com/FjCRrttl3b-qAVj8yRm6JE1wzmhO?e=1688140799&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:l7_gp0LpUmHN3f85VDhab_93hEY=",
                "location": "山东"
            },
            "text": "试试回复别人",
            "likes_count": 0,
            "rewards_count": 0,
            "sticky": false
        }
    }
}
 */
public class RespData {

    private List<Topics> topics;

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }

}
