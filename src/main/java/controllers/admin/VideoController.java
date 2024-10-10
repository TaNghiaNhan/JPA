package controllers.admin;

import entity.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.IVideoService;
import service.impl.CategoryService;
import service.impl.VideoService;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/videos"})
public class VideoController extends HttpServlet {

        IVideoService videoService = new VideoService();
        String categoryid ="";
        CategoryService cateS = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if(url.contains("videos")) {
            categoryid = req.getParameter("id");
            // Lấy danh sách video theo categoryID
            List<Video> videoList;
            videoList = videoService.findListById(categoryid);
            req.setAttribute("videoList", videoList);
            req.getRequestDispatcher("/views/admin/video_list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
