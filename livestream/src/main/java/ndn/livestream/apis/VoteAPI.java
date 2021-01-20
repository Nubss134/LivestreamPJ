package ndn.livestream.apis;

import ndn.livestream.configs.security.CustomUserDetail;
import ndn.livestream.entities.VoteEntity;
import ndn.livestream.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class VoteAPI {
    @Autowired
    private VoteService voteService;


    @GetMapping("/guest/vote/{id}")
    public VoteEntity findById(@PathVariable Long id) {
        return voteService.findById(id);
    }

    @GetMapping("/user/vote/isVote/{voteId}")
    public boolean isVote(@PathVariable Long voteId,
                          @AuthenticationPrincipal CustomUserDetail user) {
        return voteService.isVote(user.getId(), voteId);
    }

    @GetMapping("/guest/vote/getResult/{voteId}")
    public Map<String, Long> getResult(@PathVariable Long voteId) {
        return voteService.getVoteResult(voteId);
    }

    @PostMapping("/user/vote/openVote")
    public VoteEntity openVote(@AuthenticationPrincipal CustomUserDetail user,
                               @RequestBody VoteEntity voteEntity) {
        voteEntity.setId(user.getId());
        return voteService.openVote(voteEntity);
    }
    @PostMapping("/user/vote/closeVote")
    public void closeVote(@AuthenticationPrincipal CustomUserDetail user) {
        voteService.closeVote(user.getId());
    }

    @PostMapping("/user/vote/vote")
    public void vote(@RequestParam Long choice,
                          @RequestParam Long voteId,
                          @AuthenticationPrincipal CustomUserDetail user) {
        voteService.vote(voteId, choice, user.getId());
    }







}
