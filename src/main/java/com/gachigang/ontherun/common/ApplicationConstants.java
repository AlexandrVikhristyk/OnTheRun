package com.gachigang.ontherun.common;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

/**
 * Contains various constants used in the messenger application.
 */
@UtilityClass
public class ApplicationConstants {

    /**
     * Inner utility class for validation-related constants.
     */
    @UtilityClass
    public class Validation {
        /**
         * A regular expression for validating email addresses.
         */
        public static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?``{|}~^.-]+@[a-zA-Z0-9.-]+$";
        public static final Pattern EMAIL_PATTERN = Pattern.compile(Validation.EMAIL_REGEX);

        /**
         * A regular expression for validating password.
         */
        public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z]).{8,20}$";
        public static final Pattern PASSWORD_PATTERN = Pattern.compile(Validation.PASSWORD_REGEX);
    }

    /**
     * Inner utility class for constants related to security part.
     */
    @UtilityClass
    public class Security {
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String AUTH = "auth";
    }
}
