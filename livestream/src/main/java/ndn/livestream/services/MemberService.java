package ndn.livestream.services;

import ndn.livestream.configs.security.CustomUserDetail;
import ndn.livestream.configs.security.UserDetailServiceImpl;
import ndn.livestream.entities.DeviceTokenEntity;
import ndn.livestream.entities.FollowEntity;
import ndn.livestream.entities.RoleEnum;
import ndn.livestream.entities.MemberEntity;
import ndn.livestream.repositories.DeviceTokenRepository;
import ndn.livestream.repositories.FollowRepository;
import ndn.livestream.repositories.MemberRepository;
import ndn.livestream.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private DeviceTokenRepository deviceTokenRepository;

    public CustomUserDetail login(String username, String password, String deviceToken) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }
        catch (BadCredentialsException e) {
            return null;
        }
        final CustomUserDetail userDetails = (CustomUserDetail) userDetailServiceImpl.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);
        userDetails.setToken(jwt);
        if(deviceToken != null) {
            DeviceTokenEntity deviceTokenEntity = new DeviceTokenEntity();
            deviceTokenEntity.setMemberId(userDetails.getId());
            deviceTokenEntity.setDeviceToken(deviceToken);
            deviceTokenRepository.save(deviceTokenEntity);
        }
        return userDetails;

    }

    public void logout(Long memberId) {
        if(deviceTokenRepository.findById(memberId).isEmpty()) return;
        deviceTokenRepository.deleteById(memberId);
    }

    public void signUp(String username, String password, String nickName) {
        if(isExistUsername(username)) return;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickName(nickName);
        memberEntity.setUsername(username);
        memberEntity.setPassword(passwordEncoder.encode(password));
        memberEntity.setRole(RoleEnum.USER);
        memberEntity.setIsBan(false);
        memberEntity.setAvatar("https://img.nimo.tv/t/201907081562587247030_1599512126145_avatar.png/w360_l0/img.png");
        memberEntity.setCreatedTime(new Date());
        memberEntity.setUpdatedTime(new Date());
        repository.save(memberEntity);
    }

    public boolean isExistUsername(String username) {
        return repository.findByUsername(username).isPresent();
    }

    public MemberEntity getInformation(Long id) {
        MemberEntity memberEntity =  repository.findById(id).orElse(null);
        memberEntity.setFollowingNumber(followRepository.countByFollowerId(id));
        memberEntity.setFollowNumber(followRepository.countByFolloweeId(id));
        return memberEntity;
    }

    public void updatePersonalInfo(Long memberId, String nickName, String phone, String email, String avatar) {
        MemberEntity memberEntity = repository.findById(memberId).orElseThrow();
        memberEntity.setAvatar(avatar);
        memberEntity.setNickName(nickName);
        memberEntity.setPhone(phone);
        memberEntity.setEmail(email);
        repository.save(memberEntity);
    }

    public List<MemberEntity> findFriends(Long memberId) {
        List<Long> friendIds = followRepository.findFriends(memberId);
        if(friendIds.isEmpty()) return Collections.emptyList();
        return repository.findByIdIn(friendIds);
    }

    public List<MemberEntity> getAllMember() {
        return repository.findAll();
    }

    public void deleteMember(Long id) {
        if(repository.findById(id).isEmpty()) return;
        repository.deleteById(id);
    }

    public void changeStatus(Long id) {
        Optional<MemberEntity> optional = repository.findById(id);
        if(optional.isEmpty()) return;
        MemberEntity memberEntity = optional.get();
        boolean isBan = memberEntity.getIsBan();
        memberEntity.setIsBan(!isBan);
        repository.save(memberEntity);
    }

    public List<MemberEntity> getListFollow(Long memberId) {
        List<Long> ids = followRepository.findByFollowerId(memberId).
                stream().map(FollowEntity::getFolloweeId).collect(Collectors.toList());
        return repository.findByIdIn(ids);
    }

//    public MemberEntity getDetailMember(Long memberId){
//        MemberEntity memberEntity = repository.findById(memberId).orElseThrow();
//        memberEntity.setFollowingNumber(followRepository.countByFollowerId(memberId));
//        memberEntity.setFollowNumber(followRepository.countByFolloweeId(memberId));
//        return memberEntity;
//    }

}