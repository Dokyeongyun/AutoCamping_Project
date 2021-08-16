package ROOT.VO.Member;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Member {
    private String memberId;
    private String nickName;
    private String password;
    @JsonAlias({"email", "MEM_EMAIL"})
    private String email;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMember(Member member) {
        this.memberId = member.getMemberId();
        this.nickName = member.getNickName();
        this.password = null;
        this.email = member.getEmail();
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
