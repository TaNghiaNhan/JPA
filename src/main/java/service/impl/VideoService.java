package service.impl;

import dao.IVideoDao;
import dao.impl.VideoDao;
import entity.Video;
import service.IVideoService;
import java.util.List;

public class VideoService implements IVideoService {

    IVideoDao videoDao = new VideoDao();

    @Override
    public void insert(Video video) {
        Video vid = videoDao.findById(video.getVideoId());
        if(vid==null){
            videoDao.insert(video);
        }
        else{
            System.out.println("Đã tồn tại tài khoản");
        }
    }

    @Override
    public void delete(Video video) throws Exception {
        Video vid = videoDao.findById(video.getVideoId());
        if(vid!=null){
            videoDao.delete(video);
        }
        else{
            System.out.println("Không tồn tại tài khoản");
        }
    }

    @Override
    public void ppdate(Video video) {
        Video vid = videoDao.findById(video.getVideoId());
        if(vid!=null){
            videoDao.update(video);
        }
        else{
            System.out.println("Không tồn tại tài khoản");
        }
    }

    @Override
    public Video findById(String id) {
        return videoDao.findById(id);
    }

    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    public List<Video> findListById(String id) {
        return videoDao.findListById(id);
    }

}
