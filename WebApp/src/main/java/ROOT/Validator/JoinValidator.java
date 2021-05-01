package ROOT.Validator;


import ROOT.VO.Form.JoinForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class JoinValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return JoinForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        JoinForm joinForm = (JoinForm) o;

        if (!joinForm.getPassword().equals(joinForm.getPasswordCheck())) {
            errors.rejectValue("password", "NotEqualPassword", "비밀번호가 일치하지 않습니다.");
            errors.rejectValue("passwordCheck", "NotEqualPassword", "비밀번호가 일치하지 않습니다.");
        }
    }
}
