package global;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 黎江
 */
public class ResponseToClient extends TransJson {
    public ResponseToClient() {
        super();
    }

    /**
     * @param out PrintWriter
     */
    public void responseToClient(PrintWriter out) {
        out.print(super.getJsonString());
    }

    /**
     * @param isOK boolean
     * @param msg  String
     * @param out  PrintWriter
     */
    public void responseToClient(boolean isOK, String msg, PrintWriter out) {
        this.setResultStatus(isOK);
        this.setMsg(msg);
        out.print(super.getJsonString());
    }

    /**
     * @param isOK     boolean
     * @param msg      String
     * @param response HttpServletResponse
     */
    public void responseToClient(boolean isOK, String msg, HttpServletResponse response) {
        try {
            response.setHeader("content-type", "application/json;charset=utf-8");
            this.setResultStatus(isOK);
            this.setMsg(msg);
            response.getWriter().print(super.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param msg String
     */
    public void setMsg(String msg) {
        this.setJsonValue("msg", msg);
    }
}
