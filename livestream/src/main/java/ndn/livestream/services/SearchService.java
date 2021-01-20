package ndn.livestream.services;

import ndn.livestream.entities.CategoryEntity;
import ndn.livestream.entities.MemberEntity;
import ndn.livestream.entities.VideoEntity;
import ndn.livestream.repositories.CategoryRepository;
import ndn.livestream.repositories.MemberRepository;
import ndn.livestream.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private VideoService videoService;
    @Autowired
    private FollowService followService;

    public Map<String, List<?>> findByKeyWord(String keyWord) {
        Map<String, List<?>> res = new HashMap<>();
        List<CategoryEntity> listCategory = categoryRepository.findByNameContains(keyWord);
        List<MemberEntity> listMember = memberRepository.findByNickNameContains(keyWord);
        List<VideoEntity> listVideo = videoService.findByKeyWord(keyWord);
        listMember.forEach(member -> {
            member.setFollowNumber(followService.getFollowerNumber(member.getId()));
        });
        res.put("categories", listCategory);
        res.put("members", listMember);
        res.put("videos", listVideo);
        return res;
    }


}
