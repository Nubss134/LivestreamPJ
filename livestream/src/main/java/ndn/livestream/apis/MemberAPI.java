package ndn.livestream.apis;

import ndn.livestream.configs.security.CustomUserDetail;
import ndn.livestream.entities.MemberEntity;
import ndn.livestream.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MemberAPI {

    @Autowired
    private MemberService memberService;

    @PostMapping("/guest/login")
    public CustomUserDetail login(@RequestParam String username,
                                  @RequestParam String password,
                                  @RequestParam(required = false) String deviceToken) {
        return memberService.login(username,password, deviceToken);

    }

    @PostMapping("/user/logout")
    public void logout(@AuthenticationPrincipal CustomUserDetail user) {
        memberService.logout(user.getId());
    }

    @PostMapping("/guest/signUp")
    public void signUp(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam String nickName) {
        memberService.signUp(username,password, nickName);
    }

    @GetMapping("/guest/isExistUsername/{username}")
    public boolean isExistUsername(@PathVariable("username") String username) {
        return memberService.isExistUsername(username);
    }

    @GetMapping("/guest/member/getPersonalInfo/{id}")
    public MemberEntity getPersonalInfo(@PathVariable Long id) {
        return memberService.getInformation(id);
    }

    @PostMapping("/user/member/updatePersonalInfo")
    public void updatePersonalInfo(@AuthenticationPrincipal CustomUserDetail customUserDetail,
                                   @RequestParam String nickName,
                                   @RequestParam String phone,
                                   @RequestParam String email,
                                   @RequestParam String avatar)  {

        memberService.updatePersonalInfo(customUserDetail.getId(), nickName, phone, email, avatar);
    }

    @GetMapping("/user/member/getFriends/{memberId}")
    public List<MemberEntity> getFriends(@PathVariable Long memberId) {
        return memberService.findFriends(memberId);
    }

    @GetMapping("/admin/member/getAll")
    public List<MemberEntity> getAllMember() {
        return memberService.getAllMember();
    }

    @PostMapping("/admin/member/delete/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
    @PostMapping("/admin/member/changeStatus/{id}")
    public void changeStatus(@PathVariable Long id) {
        memberService.changeStatus(id);
    }

    @GetMapping("/user/member/getListFollow")
    public List<MemberEntity> getListFollow(@AuthenticationPrincipal CustomUserDetail user) {
        return memberService.getListFollow(user.getId()) ;
    }
//
//    @GetMapping("/guest/member/getDetailMember/{id}")
//    public MemberEntity getDetailMember(@PathVariable Long id) {
//        return memberService.getDetailMember(id);
//    }





}
