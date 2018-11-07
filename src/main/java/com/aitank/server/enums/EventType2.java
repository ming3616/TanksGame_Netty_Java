package com.aitank.server.enums;

/**
 * Created by mingPC on 2018/11/1.
 */
public enum EventType2 {

    MsgLogin("MsgLogin", 1),
    MsgInitPlay("MsgInitPlay", 2),
    MsgPlayInfoData("MsgPlayInfoData", 3),
    MsgOnLogout("MsgOnLogout", 4);


    private String name ;
    private int index ;

    private EventType2( String name , int index ){
        this.name = name ;
        this.index = index ;
    }

    public static String getName(int index) {
        for (EventType2 c : EventType2.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    public static String getName(String index) {
        for (EventType2 c : EventType2.values()) {
            if (c.getName().equals(index) ) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
