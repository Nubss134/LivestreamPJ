package ndn.livestream.services;

import liquibase.pro.packaged.V;
import ndn.livestream.entities.CategoryEntity;
import ndn.livestream.entities.MemberEntity;
import ndn.livestream.entities.VideoEntity;
import ndn.livestream.repositories.CategoryRepository;
import ndn.livestream.repositories.MemberRepository;
import ndn.livestream.repositories.VideoRepository;
import ndn.livestream.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private VoteRepository voteRepository;


    public VideoEntity getVideoInfo(Long id) {
        VideoEntity videoEntity = videoRepository.findById(id).orElseThrow();
        Long viewNumber = videoEntity.getViewNumber();
        Long viewNumberUpdate = viewNumber == null ? 1 : viewNumber + 1;
        videoEntity.setViewNumber(viewNumberUpdate);
       return detailVideo(videoEntity);
    }
    public VideoEntity findById(Long id) {
        return videoRepository.findById(id).orElse(new VideoEntity());
    }

    public VideoEntity detailVideo(VideoEntity videoEntity) {
        Long memberId = videoEntity.getStreamerId();
        MemberEntity memberEntity = memberRepository.findById(memberId).orElse(new MemberEntity());
        videoEntity.setNickName(memberEntity.getNickName());
        videoEntity.setAvatarUrl(memberEntity.getAvatar());
        Long categoryId = videoEntity.getCategoryId();
        videoEntity.setCategoryName(categoryRepository.findById(categoryId).orElse(new CategoryEntity()).getName());
        boolean isOpenVote = voteRepository.existsById(memberId);
        videoEntity.setIsOpenVote(isOpenVote);
        return videoEntity;
    }

    public List<VideoEntity> detailVideos(List<VideoEntity> list) {
        list.forEach(this::detailVideo);
        return list;
    }

//    public List<VideoEntity> detailVideos(List<Long> ids) {
//        if(ids.isEmpty()) return Collections.emptyList();
//        List<VideoEntity> list = videoRepository.findByIdIn(ids);
//        list.forEach(this::detailVideo);
//        return list;
//    }

    public List<VideoEntity> findByKeyWord(String keyword) {
        List<VideoEntity> list = videoRepository.findByTitleContainsAndIsLiveTrue(keyword);
       return detailVideos(list);
    }

    public List<VideoEntity> findLivestreamByCategory(Long categoryId) {
        List<VideoEntity> list = videoRepository.findByCategoryIdAndIsLiveTrue(categoryId);
        return detailVideos(list);
    }
    public List<VideoEntity> findAllLivestream() {
        return videoRepository.findByIsLiveTrue();
    }
    public List<VideoEntity> findReplyVideo(Long streamerId) {
        List<VideoEntity> list = videoRepository.findByStreamerIdAndIsLiveFalse(streamerId);
        return detailVideos(list);
    }

//    public Map<String, String> create(Long streamerId, String title, Long categoryId) {
//        String streamKey = streamerService.updateStreamKey(streamerId);
//        if(streamKey == null) return null;
//        VideoEntity videoEntity = new VideoEntity();
//        videoEntity.setTitle(title);
//        videoEntity.setCategoryId(categoryId);
//        videoEntity.setCreatedTime(new Date());
//        videoEntity.setUpdatedTime(new Date());
//        videoEntity.setIsLive(false);
//        videoEntity.setStreamerId(streamerId);
//        videoEntity.setVideoUrl("http://3.133.94.242:8080/hls/"+streamKey+".m3u8");
//        Map<String, String> res = new HashMap<>();
//        res.put("streamKey", streamKey);
//        res.put("videoId",videoRepository.save(videoEntity).getId().toString());
//        return res;
//    }

    public Long getLivestreamId(Long streamerId) {
        return videoRepository.findByStreamerIdAndIsLiveTrue(streamerId).orElse(new VideoEntity()).getId();
    }

    public List<VideoEntity> getBanner() {
        return videoRepository.findTop3ByIsLiveTrue();
    }

    public List<Map<String, Object>> getTopLiveByCategory() {
        List<CategoryEntity> topCategory = categoryRepository.findTopCategory();
        List<Map<String, Object>> res = new ArrayList<>();
        topCategory.forEach(category -> {
            Map<String, Object> map = new HashMap<>();
            List<VideoEntity> list = videoRepository.findTop5ByCategoryIdAndIsLiveTrue(category.getId());
            if(list.isEmpty()) return;
            list.forEach(this::detailVideo);
            map.put("name", category.getName());
            map.put("videos", list);
            res.add(map);
        });
       return res;
    }



}
