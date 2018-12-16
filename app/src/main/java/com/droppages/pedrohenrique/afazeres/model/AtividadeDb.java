package com.droppages.pedrohenrique.afazeres.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class AtividadeDb {
    @Id
    public long id;
    private String title;
    private String description;
    private String registerDate;
    private String deliveryDate;
    private int priority;
    private boolean done;

    public AtividadeDb() {}

    public AtividadeDb(String title, String description, String registerDate, String deliveryDate, int priority, boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.registerDate = registerDate;
        this.deliveryDate = deliveryDate;
        this.priority = priority;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
