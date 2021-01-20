package ndn.livestream.apis;

import ndn.livestream.entities.ChatEntity;
import ndn.livestream.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ChatAPI {
    @Autowired
    private ChatService chatService;

    @PostMapping("/user/chat")
    public void chat(@RequestBody ChatEntity chatEntity) {
        chatService.chat(chatEntity);
    }
}
