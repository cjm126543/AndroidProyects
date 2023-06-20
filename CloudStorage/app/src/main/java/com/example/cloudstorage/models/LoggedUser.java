package com.example.cloudstorage.models;

import com.parse.ParseObject;
import com.parse.ParseClassName;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
@ParseClassName("LoggedUser")
public class LoggedUser extends ParseObject {

    // Constructor
    public LoggedUser() {
    }

    // Getters & setters
    public void setUserId(String userId) {
        put("userId", userId);
    }

    public String getUserId() {
        return getString("userId");
    }

    public void setDisplayName(String displayName) {
        put("displayName", displayName);
    }

    public String getDisplayName() {
        return getString("displayName");
    }

    public void setFirstSurname(String surname) { put("surnameOne", surname); }

    public String getFirstSurname() { return getString("surnameOne"); }

    public void setSecondSurname(String surname) { put("surnameSecond", surname); }

    public String getSecondSurname() { return getString("surnameSecond"); }

    public void setMail(String mail) {
        put("mail", mail);
    }

    public String getMail() {
        return getString("mail");
    }

    public void setPhone(String phone) { put("phone", phone); }

    public String getPhone() { return getString("phone"); }

    public void setPassword(String password) {
        put("password", BCrypt.hashpw(password, BCrypt.gensalt()));
    }

    public String getPassword() {
        return getString("password");
    }

    // Custom class methods
    /**
     * Checks whether the user inputted passwords matches with the stored one.
     * @param inputtedPass String containing the users password trial.
     * @return true if matches, false in any other case.
     */
    public boolean checkPasswords(String inputtedPass) {
        return BCrypt.checkpw(inputtedPass, getPassword());
    }
}