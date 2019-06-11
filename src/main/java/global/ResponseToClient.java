package global;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
     * @param response HttpServletResponse
     */
    public void responseToClient(HttpServletResponse response) {
        try {
            response.setHeader("content-type", "application/json;charset=utf-8");
            response.getWriter().print(super.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void alertMsg(String msg, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"/>");
            out.println("<script>");
            out.println("\talert(\"" + msg + "\");");
            out.println("\twindow.history.go(-1);");
            out.println("</script>");
            out.println("</head>");
            out.println("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void illegalVisit(HttpServletResponse response) {
        doNotSupportGet(response);
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
            out.println("基乎");
            out.println("</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 style=\"text-align:center;\">");
            out.println("请正确的访问此页面");
            out.println("</h1>");
            out.println("<h1 style=\"text-align:center;\">");
            out.println("<a href=\"/\">点我返回</a>");
            out.println("</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String toUTF8(String str, String resencoding){
        try {
            String callback;
            byte[] b;
            if (resencoding.length() > 0)
                b = str.getBytes(resencoding);
            else
                b = str.getBytes();
            callback = new String(b,"utf-8");
            return callback;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
