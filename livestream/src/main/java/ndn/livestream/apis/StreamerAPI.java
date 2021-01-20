package ndn.livestream.apis;

import ndn.livestream.configs.security.CustomUserDetail;
import ndn.livestream.entities.StreamerEntity;
import ndn.livestream.services.StreamerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class StreamerAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamerAPI.class);
    @Autowired
    private StreamerService streamerService;

    @GetMapping("/user/streamer/getInfo")
    private StreamerEntity getLivestreamInfo(@AuthenticationPrincipal CustomUserDetail customUserDetail) {
        Long streamerId = customUserDetail.getId();
        return streamerService.getLivestreamInfo(streamerId);
    }

    @PostMapping("/guest/streamer/onPublish")
    private void onPublish(@RequestParam("name") String streamKey) throws Exception {
        LOGGER.info("Publish livestream with stream key: {}", streamKey);
        streamerService.publishLivestream(streamKey);
    }

    @PostMapping("/guest/streamer/onPublishDone")
    private void onPublishDone(@RequestParam("name") String streamKey) {
        LOGGER.info("On Publish done livestream with stream key:{}", streamKey);
        streamerService.finishLivestream(streamKey);

    }
//
//    @PostMapping("/guest/streamer/onRecordDone")
//    private void onRecordDone(@RequestParam("recorder") String recorder,
//                              @RequestParam("path") String path,
//                              @RequestParam("name") String name) {
//        LOGGER.info("On record done livestream :{}, {}, {}", recorder, path, name);
//
//    }

    @PostMapping("/user/streamer/create")
    private void create(@RequestBody StreamerEntity streamerEntity,
                        @AuthenticationPrincipal CustomUserDetail currentUser) {
        streamerEntity.setId(currentUser.getId());
        streamerService.create(streamerEntity);
    }

    @PostMapping("/user/streamer/generateStreamKey")
    private String generateStreamKey(@AuthenticationPrincipal CustomUserDetail currentUser) {
        return streamerService.updateStreamKey(currentUser.getId());
    }

//    @GetMapping("/user/streamer/getStreamKey")
//    public String getStreamKey(@AuthenticationPrincipal CustomUserDetail streamer) {
//        Long streamerId = streamer.getId();
//        return streamerService.getStreamKey(streamerId);
//    }




}
