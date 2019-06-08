package global;

import com.alibaba.fastjson.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.lang.reflect.Array;

/**
 * @author 黎江
 */
public class TransJson {
    private JSONObject json;

    public TransJson() {
        json = new JSONObject();
        json.put("isOK", false);
    }

    public JSONObject getJson() {
        return json;
    }

    public void setResultStatus(boolean status) {
        json.put("isOK", status);
    }

    public void setJsonValue(String key, String value) {
        json.put(key, value);
    }

    public void setJsonValue(String key, JSONArray value) {
        json.put(key, value);
    }

    public JSONArray getJsonArrayValue(String key){
        return json.getJSONArray(key);
    }

    public String getJsonValue(String key){
        return json.getString(key);
    }

    public String getJsonString() {
        return JSON.toJSONString(this.json);
    }

    public String getJsonString(JSONObject json){
        return JSON.toJSONString(json);
    }
}
