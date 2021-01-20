package ndn.livestream.services;

import ndn.livestream.entities.FollowEntity;
import ndn.livestream.repositories.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FollowService {

    @Autowired
    private FollowRepository repository;

    public Boolean isFollow(Long followerId, Long followeeId) {
        return repository.existsByFollowerIdAndFolloweeId(followerId,followeeId);
    }

    public void follow(Long followerId, Long followeeId) {
        FollowEntity followEntity = new FollowEntity();
        followEntity.setFollowerId(followerId);
        followEntity.setFolloweeId(followeeId);
        followEntity.setCreatedTime(new Date());
        followEntity.setUpdatedTime(new Date());
        repository.save(followEntity);
    }

    public void unFollow(Long followerId, Long followeeId) {
        FollowEntity followEntity = repository.findByFollowerIdAndFolloweeId(followerId, followeeId);
        if(followEntity != null) {
            repository.delete(followEntity);
        }
    }

    public long getFollowerNumber(Long memberId) {
        return repository.countByFolloweeId(memberId);
    }

//    public List<Long> findFriends(Long memberId) {
//        return repository.findFriends(memberId);
//    }


}
