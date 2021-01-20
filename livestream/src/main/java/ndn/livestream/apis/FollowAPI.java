package ndn.livestream.apis;

import ndn.livestream.configs.security.CustomUserDetail;
import ndn.livestream.services.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class FollowAPI {
    @Autowired
    private FollowService followService;

    @GetMapping("/user/follow/isFollow/{streamerId}")
    public boolean isFollow(@PathVariable Long streamerId,
                            @AuthenticationPrincipal CustomUserDetail user) {
        return followService.isFollow(user.getId(), streamerId);
    }

    @PostMapping("/user/follow/follow/{streamerId}")
    public void follow(@PathVariable Long streamerId,
                       @AuthenticationPrincipal CustomUserDetail user) {
         followService.follow(user.getId(), streamerId);
    }

    @PostMapping("/user/follow/unFollow/{streamerId}")
    public void unFollow(@PathVariable Long streamerId,
                         @AuthenticationPrincipal CustomUserDetail user) {
         followService.unFollow(user.getId(), streamerId);
    }



}
