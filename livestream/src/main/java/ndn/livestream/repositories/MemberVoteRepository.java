package ndn.livestream.repositories;

import ndn.livestream.entities.MemberVoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberVoteRepository extends JpaRepository<MemberVoteEntity, Long> {

    boolean existsByMemberIdAndVoteId(Long memberId, Long voteId);

    void deleteByVoteId(Long voteId);

    long countByVoteIdAndChoice(Long voteId, Long choice);

}
