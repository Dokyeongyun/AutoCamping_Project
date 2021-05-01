package ROOT.Validator;


import ROOT.VO.Form.LoginForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return LoginForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    }
}
