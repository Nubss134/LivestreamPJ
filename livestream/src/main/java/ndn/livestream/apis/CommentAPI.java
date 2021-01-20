package ndn.livestream.apis;

import ndn.livestream.configs.security.CustomUserDetail;
import ndn.livestream.entities.CommentEntity;
import ndn.livestream.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class CommentAPI {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/{videoId}")
    public void comment(@PathVariable("videoId") Long videoId,
                        @RequestBody CommentEntity comment,
                        @AuthenticationPrincipal CustomUserDetail currentUser) {
        commentService.comment(currentUser, comment, videoId);
    }
}
