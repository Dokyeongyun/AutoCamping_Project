package WebApplication.Service.Member;


import org.springframework.stereotype.Service;

@Service("View_MemberService")
public class View_MemberServiceImpl implements View_MemberService {

    @Override
    public String login(String id, String password) {
        return "member/login";
    }

    @Override
    public String join(String id, String nickName, String password) {
        return "member/join";
    }
}
