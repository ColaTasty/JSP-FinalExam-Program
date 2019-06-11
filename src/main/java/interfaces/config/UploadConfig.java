package interfaces.config;

import java.io.File;

import static java.lang.System.getProperty;

/**
 * @author 黎江
 */
public interface UploadConfig {
//            设定内存临界最大值 5MB，超出部分将保存在系统临时分配区
    int SIZE_THRESHOLD = 1024*1024*5;
//            系统分配临时文件保存区
    File OBJ_FILE =  new File(getProperty("java.io.tmpdir"));
//            设置上传文件最大值 6MB
    long FILE_SIZE_MAX = 1024*1024*6;
//            设置上传区的最大值 6MB+800KB，包括文本和文件
    long SIZE_MAX = (1024*1024*6) + (1024*800);
//            设置数据流的编码格式 UTF-8
    String HEADER_ENCODING = "UTF-8";
//            定义上传路径
    String SAVE_FILE_PATH = File.separator + "src" + File.separator +"images" +File.separator + "square";
    String WEBVIEW_FILE_PATH = "/src/images/square";
}
