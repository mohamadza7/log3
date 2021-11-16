package com.example.login;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



class Utilities
{
    private static Utilities instance;

    public Utilities()
    {
    }

    public static Utilities getInstance()
    {
        if (instance == null)
        {
            instance = new Utilities();
        }

        return instance;
    }

    public boolean emailIsTrue(AppCompatActivity activity, String e) {
        String[] splitstring=e.split("@");
        if (splitstring.length!=2){
            Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
            return false;
        }
        String domain= splitstring[1];
        String username= splitstring[0];
        String[]splitusername=username.split(" ");
        if (splitusername.length!=1){
            Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
            return false;

        }
        char first= username.charAt(0);
        if (!(first>='a'& first<='z'|| first=='_'||first>='A'& first<='Z' )){
            Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
            return false;

        }
        if (username.length()>70){
            Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (username.length()<3){
            Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
            return false;
        }
        for (int i=0;i<username.length();i++){
            char p= username.charAt(i);
            if (!(p>='a'& p<='z'||p>='A'& p<='Z'|| p=='_'|| p>='0'&p<='9' )){
                Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        // min 1 dot max 3
        if (!(domain.split(".").length>=2 && domain.split(".").length<=5)) return false;
        // first char letter underscore
        char firstd= domain.charAt(0);
        if (!(firstd>='a'& firstd<='z'|| firstd=='_'||firstd>='A'& firstd<='Z' )) {
            Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
            return false;
        }
        // domain contains letters numbers underscore
        for (int i=0;i<domain.length();i++) {
            char p = domain.charAt(i);
            if (!(p >= 'a' & p <= 'z' || p >= 'A' & p <= 'Z' || p == '_' || p >= '0' & p <= '9')) {
                Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        // last string all letters
        String[] dot= domain.split(".");
        String laststring= dot[dot.length-1];
        for (int i=0;i<laststring.length();i++){
            char p= laststring.charAt(i);
            if (!(p>='a'& p<='z'||p>='A'& p<='Z')){
                Toast.makeText(activity, "username or email is false check again", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    public boolean validatePassword(AppCompatActivity activity,String password){
        String password1= password.trim();

        if (!(password1.length()>=8 && password1.length()<=30)){
            Toast.makeText(activity, "password is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }
        int countcapital=0,countsmall=0,countstrange=0,countnumber=0, i;
        for (i=0; i<password1.length();i++) {
            char p = password1.charAt(i);
            if (p >= 'a' && p <= 'z') countsmall++;
            if (p >= 'A' && p <= 'Z') countcapital++;
            if (!(p >= 'a' && p <= 'z' || p >= 'A' && p <= 'Z' || p >= '0' && p <= '9'))
                countstrange++;
            if (p >= '0' && p <= '9') countnumber++;
        }
        if (countsmall==0|| countcapital==0||countnumber==0||countstrange==0){
            Toast.makeText(activity, "password is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
