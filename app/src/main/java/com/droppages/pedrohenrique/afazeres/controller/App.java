package com.droppages.pedrohenrique.afazeres.controller;

import android.app.Application;

import com.droppages.pedrohenrique.afazeres.model.MyObjectBox;

import io.objectbox.BoxStore;

public class App extends Application {

    BoxStore boxStore;

    @Override
    public void onCreate() {
        inicialize();
        super.onCreate();
    }

    public void inicialize(){
        boxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public BoxStore getBoxStore(){
        return this.boxStore;
    }
}
