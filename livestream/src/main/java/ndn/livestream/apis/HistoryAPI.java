package ndn.livestream.apis;

import ndn.livestream.configs.security.CustomUserDetail;
import ndn.livestream.entities.HistoryEntity;
import ndn.livestream.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HistoryAPI {
    @Autowired
    private HistoryService historyService;
    @GetMapping("user/history/get")
    public List<HistoryEntity> getHistory(@AuthenticationPrincipal CustomUserDetail user) {
        return historyService.findByMemberId(user.getId());
    }
    @PostMapping("user/history/save/{videoId}")
    public void saveHistory(@AuthenticationPrincipal CustomUserDetail user, @PathVariable Long videoId) {
        historyService.save(user.getId(), videoId);
    }
}
