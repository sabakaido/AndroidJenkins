package com.example.anikaido.jenkins.domain;

/**
 * Created by anikaido on 2017/02/26.
 */
public class JobStatusDomain {
    private String _class;
    private String name;
    private String color;
    private String url;

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
