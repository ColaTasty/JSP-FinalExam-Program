package global;

import com.alibaba.fastjson.*;

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

    public void setJsonValue(String key, Array value) {
        json.put(key, value);
    }

    public String getJsonString() {
        return JSON.toJSONString(this.json);
    }
}
