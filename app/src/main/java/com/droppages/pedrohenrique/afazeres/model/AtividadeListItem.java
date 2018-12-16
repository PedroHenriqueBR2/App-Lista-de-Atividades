package com.droppages.pedrohenrique.afazeres.model;

public class AtividadeListItem {

    private String id;
    private String prioridade;
    private String title;
    private String description;
    private String dateCadaster;

    public AtividadeListItem(String id, String priority, String title, String description, String dateCadaster) {
        this.id = id;
        this.prioridade = priority;
        this.title = title;
        this.description = description;
        this.dateCadaster = dateCadaster;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPriority() {
        return prioridade;
    }

    public void setPriority(String prioridade) {
        this.prioridade = prioridade;
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

    public String getDateCadaster() {
        return dateCadaster;
    }

    public void setDateCadaster(String dateCadaster) {
        this.dateCadaster = dateCadaster;
    }
}
