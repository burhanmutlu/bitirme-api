package com.burhanmutlu.ws.shared;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalMessages {

    public static String get(String messageKey) {
        Locale locale = LocaleContextHolder.getLocale();
        return ResourceBundle.getBundle("messages", locale).getString(messageKey);
    }
}
