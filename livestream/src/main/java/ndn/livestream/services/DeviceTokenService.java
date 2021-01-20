package ndn.livestream.services;

import ndn.livestream.entities.DeviceTokenEntity;
import ndn.livestream.entities.FollowEntity;
import ndn.livestream.repositories.DeviceTokenRepository;
import ndn.livestream.repositories.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceTokenService {

    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private DeviceTokenRepository deviceTokenRepository;
    public List<String> getDeviceTokens(Long streamerId) {
        List<Long> followerIds = followRepository.findByFolloweeId(streamerId).
                stream().map(FollowEntity::getFollowerId).collect(Collectors.toList());
        if(followerIds.isEmpty()) return Collections.emptyList();
        return deviceTokenRepository.findByMemberIdIn(followerIds).
                stream().map(DeviceTokenEntity::getDeviceToken).collect(Collectors.toList());
    }




}
