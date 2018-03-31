package com.tasbaque.intenttest;

import android.text.Editable;

public class Credentials {
    private String firstName, secondName;

    @Override
    public String toString() {
        return "Credentials{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credentials that = (Credentials) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null)
            return false;
        return secondName != null ? secondName.equals(that.secondName) : that.secondName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        return result;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Credentials(String firstName, String secondName) {

        this.firstName = firstName;
        this.secondName = secondName;
    }
}
