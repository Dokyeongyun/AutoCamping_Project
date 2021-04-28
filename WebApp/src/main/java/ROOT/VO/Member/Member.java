package ROOT.VO.Member;

public class Member {
    private String id;
    private String nickName;
    private String pw;

    public Member() { }

    public String getId() { return id; }
    public String getNickName() { return nickName; }
    public String getPw() { return pw; }

    public void setId(String id) { this.id = id; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    public void setPw(String pw) { this.pw = pw; }
}
