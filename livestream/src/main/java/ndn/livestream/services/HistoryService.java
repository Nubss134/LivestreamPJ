package ndn.livestream.services;

import ndn.livestream.entities.HistoryEntity;
import ndn.livestream.entities.MemberEntity;
import ndn.livestream.entities.VideoEntity;
import ndn.livestream.repositories.HistoryRepository;
import ndn.livestream.repositories.MemberRepository;
import ndn.livestream.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private MemberRepository memberRepository;

    public List<HistoryEntity> findByMemberId(Long id) {
        List<HistoryEntity> list = historyRepository.findByMemberId(id);
        list.forEach(history -> {
            VideoEntity videoEntity = videoRepository.findById(history.getVideoId()).get();
            MemberEntity memberEntity = memberRepository.findById(videoEntity.getStreamerId()).get();
            history.setTitle(videoEntity.getTitle());
            history.setAvatar(memberEntity.getAvatar());
            history.setStreamerName(memberEntity.getNickName());
        });
        return list;
    }

    public void save(Long memberId, Long videoId) {
        Optional<HistoryEntity> optional = historyRepository.findByMemberIdAndVideoId(memberId, videoId);
        if(optional.isPresent()) {
            HistoryEntity entity = optional.get();
            entity.setUpdatedTime(new Date());
            historyRepository.save(entity);
            return;
        }
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setMemberId(memberId);
        historyEntity.setVideoId(videoId);
        historyEntity.setCreatedTime(new Date());
        historyEntity.setUpdatedTime(new Date());
        historyRepository.save(historyEntity);
    }

}
