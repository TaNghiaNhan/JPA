package controllers;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import utils.Constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
@WebServlet(urlPatterns = {"/image","/gif"})
public class DownloadFileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        String fileName = req.getParameter("fname");
        File file = new File(Constant.UPLOAD_DIRECTORY + "/" + fileName);

        // Kiểm tra xem URL có chứa "/gif"
        if (uri.contains("/gif")) {
            resp.setContentType("image/gif"); // Thiết lập loại nội dung cho GIF
        } else if(uri.contains("/image")) {
            resp.setContentType("image/jpeg"); // Thiết lập loại nội dung cho ảnh JPEG
        }
        // Kiểm tra xem file có tồn tại không
        if (file.exists()) {
            IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND); // Trả về lỗi 404 nếu file không tồn tại
        }
    }
}