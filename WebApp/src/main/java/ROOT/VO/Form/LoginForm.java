package ROOT.VO.Form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginForm {

    @Pattern(regexp = "^[a-z0-9][a-z0-9_\\-]{4,19}$",
            message = "아이디를 다시 확인해주세요.")
    private String memberId;

    @NotBlank(message = "비밀번호를 다시 확인해주세요.")
    private String password;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "memberId='" + memberId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
