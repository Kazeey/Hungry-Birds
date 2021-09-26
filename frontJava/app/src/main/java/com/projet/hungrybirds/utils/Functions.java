package com.projet.hungrybirds.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.projet.hungrybirds.MainActivity;
import com.projet.hungrybirds.R;
import com.projet.hungrybirds.RegisterActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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

    /**
     * Check if the user is connected or not, and then setup the sharedPreferences if not
     *
     * @param context Context of the current activity
     * @return        Returns true if the user is connected, or false if not
     */
    public boolean verification(Context context)
    {
        Calendar timeNow = Calendar.getInstance();
        SharedPreferences sharedPreferences = context.getSharedPreferences("file_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Long timeDestruction = sharedPreferences.getLong("TimeDestruction", 0);

        if(sharedPreferences.getBoolean("Connected", false))
        {
            if(timeNow.getTimeInMillis() > timeDestruction)
            {
                editor.putBoolean("Connected", false);
                editor.apply();
                return false;
            }
            else
                return true;
        }
        else
            return false;
    }

    /**
     * Redirect from the current activiy to the main one if you're not connected
     *
     * @param context Context of the current activity
     */
    public void redirect(Context context)
    {
        // TODO : Rediriger vers une activité tampon pour dire que la session est terminée
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void redirectAfterTime(Context context, int timeout)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        }, timeout);
    }
}
