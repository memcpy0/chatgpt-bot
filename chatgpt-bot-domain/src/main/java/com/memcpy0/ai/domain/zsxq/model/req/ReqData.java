package com.memcpy0.ai.domain.zsxq.model.req;

/**
 * @author memcpy0
 * @description POST请求的JSON对象
 */
/*
{req_data:
    {text: "试试回复别人↵",
    image_ids: [],
    mentioned_user_ids: []
    }
}
*/
//public class ReqData {
//    private String text;
//    private String[] image_ids = new String[]{};
//    private String[] mentioned_user_ids = new String[]{};
//
//    public ReqData(String text) {
//        this.text = text;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public String[] getImage_ids() {
//        return image_ids;
//    }
//
//    public void setImage_ids(String[] image_ids) {
//        this.image_ids = image_ids;
//    }
//
//    public String[] getMentioned_user_ids() {
//        return mentioned_user_ids;
//    }
//
//    public void setMentioned_user_ids(String[] mentioned_user_ids) {
//        this.mentioned_user_ids = mentioned_user_ids;
//    }
//}

public class ReqData {
    private String text;
    private String[] image_ids = new String[]{};
    private boolean silenced;

    public ReqData(String text, boolean silenced) {
        this.text = text;
        this.silenced = silenced;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getImage_ids() {
        return image_ids;
    }

    public void setImage_ids(String[] image_ids) {
        this.image_ids = image_ids;
    }

    public boolean isSilenced() {
        return silenced;
    }

    public void setSilenced(boolean silenced) {
        this.silenced = silenced;
    }
}
