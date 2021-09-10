package com.projet.hungrybirds.utils;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

public class Functions {

    /**
     * Check the format of mail address in parameter
     *
     * @param mail The String got from the mailInput
     * @return     Boolean (True or False) in function of the mail's format
     */
    public boolean checkMail(String mail)
    {
        return (!TextUtils.isEmpty(mail) && Patterns.EMAIL_ADDRESS.matcher(mail).matches());
    }

    /**
     * Return the String from an input
     *
     * @param target An EditText from the View
     * @return       The String from the EditText
     */
    public String getTextFromInput(EditText target)
    {
        return target.getText().toString();
    }
}
