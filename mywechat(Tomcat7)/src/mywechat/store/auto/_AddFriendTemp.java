package mywechat.store.auto;

import org.apache.cayenne.CayenneDataObject;

/**
 * Class _AddFriendTemp was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _AddFriendTemp extends CayenneDataObject {

    public static final String ID_PROPERTY = "id";
    public static final String POSITION_PROPERTY = "position";
    public static final String TIME_PROPERTY = "time";
    public static final String USER_ID_PROPERTY = "userId";

    public static final String ID_PK_COLUMN = "Id";

    public void setId(int id) {
        writeProperty(ID_PROPERTY, id);
    }
    public int getId() {
        Object value = readProperty(ID_PROPERTY);
        return (value != null) ? (Integer) value : 0;
    }

    public void setPosition(String position) {
        writeProperty(POSITION_PROPERTY, position);
    }
    public String getPosition() {
        return (String)readProperty(POSITION_PROPERTY);
    }

    public void setTime(String time) {
        writeProperty(TIME_PROPERTY, time);
    }
    public String getTime() {
        return (String)readProperty(TIME_PROPERTY);
    }

    public void setUserId(Integer userId) {
        writeProperty(USER_ID_PROPERTY, userId);
    }
    public Integer getUserId() {
        return (Integer)readProperty(USER_ID_PROPERTY);
    }

}