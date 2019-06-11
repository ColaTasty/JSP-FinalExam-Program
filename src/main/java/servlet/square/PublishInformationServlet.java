package servlet.square;

import bean.UserBean;
import global.ResponseToClient;
import global.config.Uploader;
import global.config.DBConnecter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import table.PostTableItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 黎江、杨子豪、温剑
 */
public class PublishInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        因为上传文件的content-type是multipart/form-data，
        这个上传格式和application/x-www-form-urlencoded不同，
        是将数据以数据流的形式上传的，所以在对文本和文件进行处理的时候，
        应该使用对数据流操作的方式
         */
        long time = System.currentTimeMillis() / 1000;
//        定义可上传的文件格式
        List<String> fileTypes = new ArrayList<String>();
        fileTypes.add("jpg");
        fileTypes.add("png");
        fileTypes.add("jpeg");
        ResponseToClient responseToClient = new ResponseToClient();
//        定义上传对象，具体请看 global.config.Uploader 以及 interfaces.config.UploadConfig
        ServletFileUpload uploader = Uploader.uploader.getServletFileUpload();
//        定义上传保存目录
        String uploadPath = getServletContext().getRealPath(Uploader.uploader.getSaveFilePath());
//        定义浏览文件目录
        String viewPath = Uploader.uploader.getWebviewFilePath();
//        如果不存在目录，创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();
        try {
            boolean picture_loaded = false;
            String fileStorePath = null;
            String fileViewPath = null;
//        用于保存key->value
            Map<String, String> kvSquare = new HashMap<String, String>();
            List<FileItem> fileItems = uploader.parseRequest(request);
            if (fileItems != null && fileItems.size() > 0) {
//                迭代处理请求项
                for (FileItem request_item :
                        fileItems) {
//                    如果是文本项 String
                    if (request_item.isFormField()) {
                        String name = request_item.getFieldName();
                        String value = request_item.getString("UTF-8");
                        kvSquare.put(name, value);
                    }
//                    如果图片未上传，且是文件项 File
                    else if (!picture_loaded && !request_item.isFormField()) {
                        String fileName = new File(request_item.getName()).getName();
                        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//                        如果上传的文件符合图片文件格式要求 true
                        if (fileTypes.contains(suffix)) {
                            fileStorePath = uploadPath + File.separator + time + fileName;
                            fileViewPath = viewPath + "/" + time + fileName;
                            File storeFile = new File(fileStorePath);
                            request_item.write(storeFile);
//                            如果文件保存成功
                            if (storeFile.exists()) {
                                picture_loaded = true;
                            } else {
                                responseToClient.responseToClient(false, "图片上传失败！", response);
                                return;
                            }
                        }
//                        如果上传的文件不符合图片文件格式要求 false
                        else {
                            responseToClient.responseToClient(false, "你上传的图片格式不正确！", response);
                            return;
                        }
                    }
                }
                String str_content = kvSquare.get("content");
                if (str_content == null) {
                    responseToClient.responseToClient(false, "请填写文字！", response);
                    return;
                }
//                开始写入数据库
                UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");
                int user_id = userBean.getUser_id();
                PostTableItem postTableItem = new PostTableItem(DBConnecter.connecter);
                String utf8_content = ResponseToClient.toUTF8(str_content,"GB2312");
                System.out.println(utf8_content);
                System.out.println(str_content);
//                if (postTableItem.isRelease(user_id, utf8_content, fileViewPath, time)) {
                if (postTableItem.isRelease(user_id, str_content, fileViewPath, time)) {
                    responseToClient.responseToClient(true, "发表成功", response);
                } else {
                    if (fileStorePath != null) {
                        File deleteFile = new File(fileStorePath);
                        boolean deleted = deleteFile.delete();
                        responseToClient.setJsonValue("deleted", deleted);
                    }
                    responseToClient.responseToClient(false, "上传失败！", response);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseToClient.illegalVisit(response);
    }
}
