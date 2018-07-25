package mywechat.store.auto;

import org.apache.cayenne.CayenneDataObject;

/**
 * Class _Rely was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Rely extends CayenneDataObject {

    public static final String CIRCLE_ID_PROPERTY = "circleId";
    public static final String CONTENT_PROPERTY = "content";
    public static final String ID_PROPERTY = "id";
    public static final String NICK_PROPERTY = "nick";

    public static final String ID_PK_COLUMN = "Id";

    public void setCircleId(Integer circleId) {
        writeProperty(CIRCLE_ID_PROPERTY, circleId);
    }
    public Integer getCircleId() {
        return (Integer)readProperty(CIRCLE_ID_PROPERTY);
    }

    public void setContent(String content) {
        writeProperty(CONTENT_PROPERTY, content);
    }
    public String getContent() {
        return (String)readProperty(CONTENT_PROPERTY);
    }

    public void setId(int id) {
        writeProperty(ID_PROPERTY, id);
    }
    public int getId() {
        Object value = readProperty(ID_PROPERTY);
        return (value != null) ? (Integer) value : 0;
    }

    public void setNick(String nick) {
        writeProperty(NICK_PROPERTY, nick);
    }
    public String getNick() {
        return (String)readProperty(NICK_PROPERTY);
    }

}
