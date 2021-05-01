package ROOT.VO.Form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class JoinForm {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Email(message = "아이디가 이메일 형식에 맞지 않습니다.")
    private String memberId;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Length(min = 3, max = 10, message = "닉네임은 3자리 이상 10자리 이하로 입력해주세요.")
    private String nickName;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 1개 이상씩 영문/숫자/특수문자가 포함된 8자 ~ 20자로 입력해야합니다.")
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
