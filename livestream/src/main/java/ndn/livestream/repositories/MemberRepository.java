package ndn.livestream.repositories;

import ndn.livestream.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByUsername(String username);
    Optional<MemberEntity> findByUsernameAndIsBanFalse(String username);
    List<MemberEntity> findByNickNameContains(String nickname);
    List<MemberEntity> findByIdIn(List<Long> ids);
}
