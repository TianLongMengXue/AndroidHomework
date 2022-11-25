package com.e3e4e20.eleventhweek;

/*
 * Filename: com.e3e4e20.eleventhweek
 * Description:
 * Version: 1.0
 * Created: 2019/11/20 8:19 星期三
 * Revision: none
 * Compiler:
 * Author: DreamSnow·Draco
 * Company:
 * */
public class Music {
    // 音乐的地址
    private int path;
    // 音乐的名称
    private String name;

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Music{" +
                "path=" + path +
                ", name='" + name + '\'' +
                '}';
    }
}
