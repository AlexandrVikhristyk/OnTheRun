package com.gachigang.ontherun.common;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class ApplicationConstants {
    @UtilityClass
    public class Validation {
        /**
         * A regular expression for validating email addresses.
         */
        public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        public static final Pattern EMAIL_PATTERN = Pattern.compile(Validation.EMAIL_REGEX);
    }
}
