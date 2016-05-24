package ru.bullfinchdev.criminalintentnew;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID id;
    private String title;
    private Date date;
    private boolean isSolved;

    public Crime(){
        id = UUID.randomUUID();
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getIsSolved() {
        return isSolved;
    }

    public void setIsSolved(boolean solved) {
        isSolved = solved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {

        return id;
    }
}
