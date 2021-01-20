package ndn.livestream.services;

import ndn.livestream.entities.ChatEntity;
import ndn.livestream.repositories.FirebaseRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public void chat(ChatEntity chatEntity) {
        Long senderId = chatEntity.getSenderId();
        Long receiverId = chatEntity.getReceiverId();

        String path = senderId < receiverId ? "chat/" + senderId + "-" + receiverId : "chat/" + receiverId + "-" + senderId;
        new FirebaseRepository().saveData(chatEntity, path);
    }


}
