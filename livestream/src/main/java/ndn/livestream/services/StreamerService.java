package ndn.livestream.services;

import ndn.livestream.entities.CategoryEntity;
import ndn.livestream.entities.MemberEntity;
import ndn.livestream.entities.StreamerEntity;
import ndn.livestream.entities.VideoEntity;
import ndn.livestream.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StreamerService {
    @Autowired
    private StreamerRepository streamerRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DeviceTokenService deviceTokenService;
    @Value("${rtmp-server.client}")
    private String RTMP_SERVER_CLIENT;
    @Transactional
    public void publishLivestream(String streamKey) {
        StreamerEntity streamerEntity = streamerRepository.findByStreamKey(streamKey).orElseThrow();
        streamerEntity.setIsOnline(true);
        streamerRepository.save(streamerEntity);

        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setTitle(streamerEntity.getTitle());
        videoEntity.setCategoryId(streamerEntity.getCategoryId());
        videoEntity.setVideoUrl(RTMP_SERVER_CLIENT+streamKey+".m3u8");
        videoEntity.setStreamerId(streamerEntity.getId());
        videoEntity.setCreatedTime(new Date());
        videoEntity.setUpdatedTime(new Date());
        videoEntity.setIsLive(true);
        videoEntity.setLiveThumbnail(streamerEntity.getLiveThumbnail());
        videoRepository.save(videoEntity);
        //Push notification
        String nickName = memberRepository.findById(streamerEntity.getId()).orElse(new MemberEntity()).getNickName();
        String categoryName = categoryRepository.findById(streamerEntity.getCategoryId()).orElse(new CategoryEntity()).getName();
        String title = "Thông báo mới";
        String content = nickName + " đang livestream " + categoryName;
        List<String> listDeviceToken = deviceTokenService.getDeviceTokens(streamerEntity.getId());
        new PushNotificationService().pushNotification(listDeviceToken, title, content, streamerEntity.getId());


    }

    public void finishLivestream(String streamKey) {
        StreamerEntity streamerEntity = streamerRepository.findByStreamKey(streamKey).orElseThrow();
        streamerEntity.setIsOnline(false);
        streamerEntity.setStreamKey(generateStreamKey());
        streamerRepository.save(streamerEntity);

        VideoEntity videoEntity = videoRepository.findByStreamerIdAndIsLiveTrue(streamerEntity.getId()).orElseThrow();
        videoEntity.setIsLive(false);
        //videoEntity.setVideoUrl(); Change url video
        videoRepository.save(videoEntity);
    }

    public StreamerEntity getLivestreamInfo(Long streamerId) {
        return streamerRepository.findById(streamerId).orElse(new StreamerEntity());
//        String nickName = memberRepository.findById(streamerId).orElse(new MemberEntity()).getNickName();
//        streamerEntity.setNickName(nickName);
//        String categoryName = categoryRepository.findById(streamerEntity.getCategoryId()).orElse(new CategoryEntity()).getName();
//        streamerEntity.setCategoryName(categoryName);
//
//        if(streamerEntity.getIsOnline()) {
//            streamerEntity.setVideoId(videoRepository.findLivestreamVideo(streamerId).getId());
//        }
    }

    public void create(StreamerEntity streamerEntity) {
        streamerRepository.save(streamerEntity);
    }

//    public String getStreamKey(Long streamerId) {
//        StreamerEntity streamerEntity = streamerRepository.findById(streamerId).orElseThrow();
//        return streamerEntity.getStreamKey();
//    }

    public String updateStreamKey(Long streamerId) {
        Optional<StreamerEntity> optional = streamerRepository.findById(streamerId);
        if(optional.isEmpty()) {
            return null;
        }
        StreamerEntity entity = optional.get();
        entity.setStreamKey(generateStreamKey());
        return streamerRepository.save(entity).getStreamKey();
    }

    public String generateStreamKey() {
        while (true) {
            String newStreamKey = UUID.randomUUID().toString();
            if (streamerRepository.findByStreamKey(newStreamKey).isEmpty()) {
                return newStreamKey;
            }
        }
    }


}
