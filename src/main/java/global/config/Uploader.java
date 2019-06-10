/*
 * CREATE TIME 2019/6/10 22:11
 * AUTHOR 黎江
 * CREATE BY IntelliJ IDEA 2019.1.2
 */

package global.config;

import interfaces.config.UploadConfig;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Uploader implements UploadConfig {
    private DiskFileItemFactory _diskFileItemFactory = new DiskFileItemFactory();
    private ServletFileUpload _servletFileUpload;
    public static Uploader uploader = new Uploader();

    private Uploader(){
        this._diskFileItemFactory.setSizeThreshold(SIZE_THRESHOLD);
        this._diskFileItemFactory.setRepository(OBJ_FILE);
        //        定义处理上传文件的类
        this._servletFileUpload = new ServletFileUpload(this._diskFileItemFactory);
        this._servletFileUpload.setFileSizeMax(FILE_SIZE_MAX);
        this._servletFileUpload.setSizeMax(SIZE_MAX);
        this._servletFileUpload.setHeaderEncoding(HEADER_ENCODING);
    }

    public DiskFileItemFactory getDiskFileItemFactory(){
        return this._diskFileItemFactory;
    }

    public ServletFileUpload getServletFileUpload() {
        return _servletFileUpload;
    }

    public String getSaveFilePath(){
        return SAVE_FILE_PATH;
    }
    public String getWebviewFilePath(){
        return WEBVIEW_FILE_PATH;
    }
}
