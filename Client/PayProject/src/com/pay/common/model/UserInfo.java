
package com.pay.common.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

/**
 * UserInfo，是应用服务器请求SDK服务器得到的SDK用户信息数据。
 * 返回给客户端的用户信息
 */
public class UserInfo {

    private String id; // 用户ID，缺省返回。

    private String name; // 用户名，缺省返回。

    private String avatar; // 3用户头像url，缺省返回。

    private String sex; // 用户性别，仅在fields中包含时候才返回，返回值为：男，女或者未知。

    private String area; // 户地区，仅在fields中包含时候才返回。

    private String nick; // 用户昵称，无值时候返回空。

    /**
     * 解析从应用服务器返回的用户信息
     * @param jsonString
     * @return
     */
    public static UserInfo parseJson(String jsonString) {
        UserInfo userInfo = null;
        if (!TextUtils.isEmpty(jsonString)) {
            try {
                JSONObject jsonObj = new JSONObject(jsonString);
                String status = jsonObj.getString("status");
                JSONObject dataJsonObj = jsonObj.getJSONObject("data");
                if (status != null && status.equals("ok")) {
                    // 必返回项
                    String id = dataJsonObj.getString("id");
                    String name = dataJsonObj.getString("name");
                    String avatar = dataJsonObj.getString("avatar");

                    userInfo = new UserInfo();
                    userInfo.setId(id);
                    userInfo.setName(name);
                    userInfo.setAvatar(avatar);

                    // 非必返回项
                    if (dataJsonObj.has("sex")) {
                        String sex = dataJsonObj.getString("sex");
                        userInfo.setSex(sex);
                    }

                    if (dataJsonObj.has("area")) {
                        String area = dataJsonObj.getString("area");

                        userInfo.setArea(area);
                    }

                    if (dataJsonObj.has("nick")) {
                        String nick = dataJsonObj.getString("nick");
                        userInfo.setNick(nick);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return userInfo;
    }

    public boolean isValid() {
        return !TextUtils.isEmpty(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
    
    public String toJsonString() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("status", "ok");
            
            JSONObject dataObj = new JSONObject();
            dataObj.put("id", id);
            dataObj.put("name", name);
            dataObj.put("avatar", avatar);
            dataObj.put("sex", sex);
            dataObj.put("area", area);
            dataObj.put("nick", nick);
            
            obj.put("data", dataObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj.toString();
    }

}
