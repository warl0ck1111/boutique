package com.aneeque.backendservice.util.validator;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Okala Bashir .O.
 * created at 06/02/2015
 */

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
