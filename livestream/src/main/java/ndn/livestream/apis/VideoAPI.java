package ndn.livestream.apis;

import ndn.livestream.entities.VideoEntity;
import ndn.livestream.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class VideoAPI {
    @Autowired
    private VideoService videoService;

    @GetMapping("guest/video/getVideoInfo/{id}")
    public VideoEntity getVideoInfo(@PathVariable("id") Long id) {
        if(id == null) {
            return new VideoEntity();
        }
        return videoService.getVideoInfo(id);
    }

    @GetMapping("guest/video/getAllLivestream")
    public List<VideoEntity> findAllLivestream() {
        return videoService.findAllLivestream();
    }

    @GetMapping("guest/video/getLivestream/{categoryId}")
    public List<VideoEntity> findLivestreamByCategory(@PathVariable("categoryId") Long categoryId) {
        return videoService.findLivestreamByCategory(categoryId);
    }

    @GetMapping("guest/video/getReplyVideo/{streamerId}")
    public List<VideoEntity> getReplyVideo(@PathVariable("streamerId") Long streamerId) {
        return videoService.findReplyVideo(streamerId);
    }

    @GetMapping("guest/video/getLivestreamId/{streamerId}")
    public Long getLivestreamId(@PathVariable("streamerId") Long streamerId) {
        return videoService.getLivestreamId(streamerId);
    }

    @GetMapping("guest/video/getBanner")
    public List<VideoEntity> getBanner() {
        return videoService.getBanner();
    }


    @GetMapping("guest/video/getTopLiveByCategory")
    public List<Map<String, Object>> getTopLiveByCategory() {
        return videoService.getTopLiveByCategory();
    }
}
