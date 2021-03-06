package mywechat.store.auto;

import java.util.Date;

import org.apache.cayenne.CayenneDataObject;

/**
 * Class _VLastSessionMessage was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _VLastSessionMessage extends CayenneDataObject {

    public static final String ID_PROPERTY = "id";
    public static final String MSG_CONTENT_PROPERTY = "msgContent";
    public static final String MSG_TYPE_PROPERTY = "msgType";
    public static final String RECEIVER_ID_PROPERTY = "receiverId";
    public static final String SEND_TIME_PROPERTY = "sendTime";
    public static final String SENDER_ID_PROPERTY = "senderId";

    public static final String ID_PK_COLUMN = "id";

    public void setId(Integer id) {
        writeProperty(ID_PROPERTY, id);
    }
    public Integer getId() {
        return (Integer)readProperty(ID_PROPERTY);
    }

    public void setMsgContent(String msgContent) {
        writeProperty(MSG_CONTENT_PROPERTY, msgContent);
    }
    public String getMsgContent() {
        return (String)readProperty(MSG_CONTENT_PROPERTY);
    }

    public void setMsgType(String msgType) {
        writeProperty(MSG_TYPE_PROPERTY, msgType);
    }
    public String getMsgType() {
        return (String)readProperty(MSG_TYPE_PROPERTY);
    }

    public void setReceiverId(Integer receiverId) {
        writeProperty(RECEIVER_ID_PROPERTY, receiverId);
    }
    public Integer getReceiverId() {
        return (Integer)readProperty(RECEIVER_ID_PROPERTY);
    }

    public void setSendTime(Date sendTime) {
        writeProperty(SEND_TIME_PROPERTY, sendTime);
    }
    public Date getSendTime() {
        return (Date)readProperty(SEND_TIME_PROPERTY);
    }

    public void setSenderId(Integer senderId) {
        writeProperty(SENDER_ID_PROPERTY, senderId);
    }
    public Integer getSenderId() {
        return (Integer)readProperty(SENDER_ID_PROPERTY);
    }

}
