package com.splitface.tattoo.validator.validatorImpl;
import com.splitface.tattoo.validator.ArtistCheck;
import java.util.regex.Pattern;

public class ArtistCheckImpl implements ArtistCheck {
    @Override
    public boolean checkEmail(String email) {
        String regExpression = ("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}");
        return Pattern.compile(regExpression)
                .matcher(email)
                .matches();
    }

    @Override
    public String checkPassword(String password) {
        if (password.length()<8){
            return "too short password";
        }
        if (!password.matches(".*\\d.*")){
            return "no numbers in pass";
        }
        Pattern pattern = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        if (!pattern.matcher(password).find()){
            return "no special symbol";
        }
        pattern = Pattern.compile("[a-z]");
        if (!pattern.matcher(password).find()){
            return "no lowercase character";
        }
        pattern = Pattern.compile("[A-Z]");
        if (!pattern.matcher(password).find()){
            return "no uppercase letter";
        }
        return "OK";
    }
}
