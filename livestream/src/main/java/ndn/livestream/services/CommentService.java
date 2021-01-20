package ndn.livestream.services;

import ndn.livestream.configs.security.CustomUserDetail;
import ndn.livestream.entities.CommentEntity;
import ndn.livestream.repositories.FirebaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    public void comment(CustomUserDetail currentUser, CommentEntity comment, Long videoId) {
        String name = currentUser.getNickName() != null ? currentUser.getNickName() : currentUser.getUsername();
        comment.setUsername(name);
        comment.setMemberId(currentUser.getId());
        new FirebaseRepository().saveData(comment,"comment/" + videoId);
    }

}
