package controllers.admin;

import entity.Category;
import entity.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import org.apache.taglibs.standard.lang.jstl.Constants;
import service.IVideoService;
import service.impl.CategoryService;
import service.impl.VideoService;
import utils.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig(fileSizeThreshold= 1024 * 1024, maxFileSize= 1024 * 1024*5, maxRequestSize = 1024 * 1024*5*5)
@WebServlet(urlPatterns = {"/admin/videos",
        "/admin/video/add", "/admin/video/insert",
        "/admin/video/edit", "/admin/video/update",
        "/admin/video/delete"})

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
        } else if(url.contains("add")) {
            Category cate = cateS.findById(Integer.parseInt(categoryid));
            req.setAttribute("category", cate);
            req.getRequestDispatcher("/views/admin/video_add.jsp").forward(req, resp);
        } else if(url.contains("edit")) {
            String videoid = req.getParameter("id");
            req.setAttribute("catid",categoryid);
            Category cate = cateS.findById(Integer.parseInt(categoryid));
            req.setAttribute("category", cate);
            Video vid = videoService.findById(videoid);
            req.setAttribute("vId",vid);
            req.getRequestDispatcher("/views/admin/video_update.jsp").forward(req, resp);
        } else if (url.contains("delete")){
            String id = req.getParameter("id");
            Video video = videoService.findById(id);
            try {
                videoService.delete(video);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/videos?id=" + categoryid);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (url.contains("insert")) {
            String videoId = req.getParameter("vId");
            String status = req.getParameter("status");
            int cateid = Integer.parseInt(req.getParameter("cateID"));
            int active = Integer.parseInt(status);
            String des = req.getParameter("description");
            String title = req.getParameter("title");
            int views = Integer.parseInt(req.getParameter("views"));

            String fname = "";

            Video video = new Video();
            video.setVideoId(videoId); // Thiết lập ID video
            video.setActive(active); // Thiết lập trạng thái video
            video.setDescription(des); // Thiết lập mô tả video
            video.setTitle(title); // Thiết lập tiêu đề video
            video.setViews(views); // Thiết lập số lượt xem video

            // Thiết lập đối tượng Category cho Video
            Category category = cateS.findById(cateid);
            video.setCategory(category);

            String uploadPath = Constant.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            try{
                Part part = req.getPart("poster");
                if (part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    //Doi ten file
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    //upload file
                    part.write(uploadPath + "/" + fname);
                    //ghi ten file vao data
                    video.setPoster(fname);
                }
                else { video.setPoster("apple-apple-iphone.gif"); }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            videoService.insert(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos?id=" + categoryid);
        } else if (url.contains("update")) {
            String id = req.getParameter("cateID");
            Category cate = cateS.findById(Integer.parseInt(id));
            String videoID = req.getParameter("videoid");
            int active = Integer.parseInt(req.getParameter("active"));
            int views = Integer.parseInt(req.getParameter("views"));

            Video vi = new Video();
            vi.setCategory(cate);
            vi.setVideoId(videoID);
            vi.setDescription(req.getParameter("description"));
            vi.setTitle(req.getParameter("title"));
            vi.setViews(views);
            vi.setActive(active);

            String fname = "";
            String uploadPath = Constant.UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            try{
                Part part = req.getPart("images");
                if (part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    //Doi ten file
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    //upload file
                    part.write(uploadPath + "/" + fname);
                    //ghi ten file vao data
                    vi.setPoster(fname);
                }
                else {vi.setPoster("apple-apple-iphone.gif"); }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            videoService.update(vi);
            resp.sendRedirect(req.getContextPath() + "/admin/videos?id=" + categoryid);
        }
    }
}
