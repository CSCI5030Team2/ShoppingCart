package com.example.shoppingcartserver.email;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates the email address
 * @author aiden
 */

@Service
public class EmailValidator implements Predicate<String> {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean test(String s) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(s);
        return matcher.find();
    }
}
