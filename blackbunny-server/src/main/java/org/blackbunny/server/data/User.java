package org.blackbunny.server.data;

/**
 * Created by IntelliJ IDEA.
 * User: leoincedo
 * Date: 5/15/12
 */
public class User
{

    int uid;
    String id;
    String nickname;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
