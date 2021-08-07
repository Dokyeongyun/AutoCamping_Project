package ROOT.VO.Form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginForm {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private String saveIdYN;

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

    public String getSaveIdYN() { return saveIdYN; }

    public void setSaveIdYN(String saveIdYN) { this.saveIdYN = saveIdYN; }

    @Override
    public String toString() {
        return "LoginForm{" +
                "memberId='" + memberId + '\'' +
                ", password='" + password + '\'' +
                ", saveIdYN='" + saveIdYN + '\'' +
                '}';
    }
}
