package com.projet.hungrybirds.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class Functions {

    public String phraseMessage = "";

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

    /**
     * Set a message in the passed Textview
     *
     * @param textView The targeted TextView
     * @param message  The message
     * @param nbEssais If there is a count of try it will appears in the message
     */
    public void setMessage (TextView textView, String message, int nbEssais)
    {
        this.phraseMessage = message;

        if(nbEssais > 0)
        {
            this.phraseMessage += nbEssais;
        }

        textView.setVisibility(View.VISIBLE);

        if(message == "")
        {
            textView.setVisibility(View.GONE);
        }

        textView.setText(this.phraseMessage);
    }

    public void verification()
    {
        
    }
}
