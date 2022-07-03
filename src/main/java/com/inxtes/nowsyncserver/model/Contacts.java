package com.inxtes.nowsyncserver.model;

import org.springframework.stereotype.Component;

@Component
public class Contacts {
    private String phoneNumber;
    private String name;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacts contacts = (Contacts) o;

        if (phoneNumber != null ? !phoneNumber.equals(contacts.phoneNumber) : contacts.phoneNumber != null)
            return false;
        return name != null ? name.equals(contacts.name) : contacts.name == null;
    }

    @Override
    public int hashCode() {
        int result = phoneNumber != null ? phoneNumber.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
