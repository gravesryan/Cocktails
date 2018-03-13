package com.squad.cocktails.model.database;

/**
 * Created by ryanc on 2/27/2018.
 */

public class Favorite {

    private String _id;
    private String _name;
    private String _thumbnail;

    public Favorite() {}

    public Favorite(String id, String name, String thumbnail) {
        this._id = id;
        this._name = name;
        this._thumbnail = thumbnail;
    }

    public String getID() {
        return _id;
    }

    public void setID(String _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getThumbnail() {
        return _thumbnail;
    }

    public void setThumbnail(String _thumbnail) {
        this._thumbnail = _thumbnail;
    }

}
