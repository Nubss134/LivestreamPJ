package ndn.livestream.configs.security;

import ndn.livestream.entities.MemberEntity;
import ndn.livestream.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private MemberRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberEntity> optionalUserEntity = repository.findByUsernameAndIsBanFalse(username);
        if(optionalUserEntity.isEmpty()) throw new UsernameNotFoundException("Cannot find user by username");
        MemberEntity user = optionalUserEntity.get();
        return new CustomUserDetail(user.getId(), user.getUsername(), user.getPassword(), user.getAvatar(), user.getRole().toString(), user.getNickName());
    }
}
