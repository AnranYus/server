package com.inxtes.nowsyncserver.model;

import org.springframework.stereotype.Component;

@Component
public class Message {
    private String senderNumber;
    private String getterNumber;
    private String content;
    private String date;
    private String UUID;
    private int type;
    private String localId;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (type != message.type) return false;
        if (senderNumber != null ? !senderNumber.equals(message.senderNumber) : message.senderNumber != null)
            return false;
        if (getterNumber != null ? !getterNumber.equals(message.getterNumber) : message.getterNumber != null)
            return false;
        if (content != null ? !content.equals(message.content) : message.content != null)
            return false;
        if (date != null ? !date.equals(message.date) : message.date != null) return false;
        if (UUID != null ? !UUID.equals(message.UUID) : message.UUID != null) return false;
        return localId != null ? localId.equals(message.localId) : message.localId == null;
    }

    @Override
    public int hashCode() {
        int result = senderNumber != null ? senderNumber.hashCode() : 0;
        result = 31 * result + (getterNumber != null ? getterNumber.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (UUID != null ? UUID.hashCode() : 0);
        result = 31 * result + type;
        result = 31 * result + (localId != null ? localId.hashCode() : 0);
        return result;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public String getGetterNumber() {
        return getterNumber;
    }

    public void setGetterNumber(String getterNumber) {
        this.getterNumber = getterNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }
}
