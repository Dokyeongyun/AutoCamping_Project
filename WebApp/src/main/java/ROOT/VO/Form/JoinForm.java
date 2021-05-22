package ROOT.VO.Form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class JoinForm {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-z0-9][a-z0-9_\\-]{4,19}$",
            message = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.")
    private String memberId;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{2,10}$",
            message = "닉네임은 2~10자의 한글, 영문, 숫자만 사용 가능합니다.")
    private String nickName;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9`\\-=\\\\\\[\\];',\\./~!@#\\$%\\^&\\*\\(\\)_\\+|\\{\\}:\"<>\\?]{8,16}$",
            message = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    private String passwordCheck;

    private boolean check;

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

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "JoinForm{" +
                "memberId='" + memberId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", passwordCheck='" + passwordCheck + '\'' +
                '}';
    }
}
