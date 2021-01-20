package ndn.livestream.services;

import ndn.livestream.entities.MemberVoteEntity;
import ndn.livestream.entities.VoteEntity;
import ndn.livestream.repositories.MemberVoteRepository;
import ndn.livestream.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private MemberVoteRepository memberVoteRepository;

    public VoteEntity findById(Long id) {
        return  voteRepository.findById(id).orElse(null);
    }

    public VoteEntity openVote(VoteEntity voteEntity) {
        return voteRepository.save(voteEntity);
    }

    @Transactional
    public void closeVote(Long id) {
        memberVoteRepository.deleteByVoteId(id);
        voteRepository.deleteById(id);
    }

    public void vote(Long voteId, Long choice, Long memberId) {
        if(isVote(memberId, voteId)) return;
        MemberVoteEntity memberVoteEntity = new MemberVoteEntity();
        memberVoteEntity.setVoteId(voteId);
        memberVoteEntity.setMemberId(memberId);
        memberVoteEntity.setChoice(choice);
        memberVoteRepository.save(memberVoteEntity);
    }

    public boolean isVote(Long memberId, Long voteId) {
        return memberVoteRepository.existsByMemberIdAndVoteId(memberId, voteId);
    }

    public Map<String,Long> getVoteResult(Long voteId) {
        Map<String, Long> res = new HashMap<>();
        long choice1 = memberVoteRepository.countByVoteIdAndChoice(voteId,1L);
        long choice2 = memberVoteRepository.countByVoteIdAndChoice(voteId,2L);
        long choice3 = memberVoteRepository.countByVoteIdAndChoice(voteId,3L);
        long sum = choice1 + choice2 +choice3;
        if(sum == 0) {
            sum = 1l;
        }
        res.put("choice1", choice1*100L/sum);
        res.put("choice2", choice2*100L/sum);
        res.put("choice3", choice3*100L/sum);
        return res;
    }



}
