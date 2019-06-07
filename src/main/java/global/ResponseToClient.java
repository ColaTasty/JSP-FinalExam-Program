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

    public static void doNotSupportGet(HttpServletResponse response) {
        try {
            response.setHeader("content-type", "text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<style>");
            out.println("a:link,a:visited,a:active{color:black;text-decoration:none}");
            out.println("a:hover{color:#576b95;text-decoration:none}");
            out.println("</style>");
            out.println("<title>");
            out.println("请正确的访问此页面");
            out.println("</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 style=\"text-align:center;\">");
            out.println("请正确访问该网页");
            out.println("</h1>");
            out.println("<h1 style=\"text-align:center;\">");
            out.println("<a href=\"/\">点我返回登录</a>");
            out.println("</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
