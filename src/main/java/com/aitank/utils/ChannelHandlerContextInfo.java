package com.aitank.utils;

import io.netty.channel.ChannelHandlerContext;

public class ChannelHandlerContextInfo {
    private float force;

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public float[] getPosition() {
        return position;
    }

    public void setPosition(float[] position) {
        this.position = position;
    }

    public float[] getRotation() {
        return rotation;
    }

    public void setRotation(float[] rotation) {
        this.rotation = rotation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getForce() {
        return force;
    }

    public void setForce(float force) {
        this.force = force;
    }

    public ChannelHandlerContext getChx() {
        return chx;
    }

    public void setChx(ChannelHandlerContext chx) {
        this.chx = chx;
    }

    private String name;
    private ChannelHandlerContext chx;
    private float[] position = new float[3];
    private float[] rotation = new float[4];
    private boolean shoot;
    public int serverHealth;


    public ChannelHandlerContextInfo() {
        this.position = new float[]{0f, 0f, 0f};
        this.rotation = new float[]{0f, 0f, 0f, 1f};
        this.shoot = false;
        this.serverHealth = 0;
    }

    public ChannelHandlerContextInfo(String name, ChannelHandlerContext chx) {
        this.name = name;
        this.chx = chx;
        this.position = new float[]{0f, 0f, 0f};
        this.rotation = new float[]{0f, 0f, 0f, 1f};
        this.shoot = false;
        this.serverHealth = 0;
    }

    public ChannelHandlerContextInfo(String name, ChannelHandlerContext chx, float[] position, float[] rotation) {
        this.name = name;
        this.chx = chx;
        this.position = position;
        this.rotation = rotation;
        this.shoot = false;
        this.serverHealth = 0;
    }

    public ChannelHandlerContextInfo(String name, ChannelHandlerContext chx,
                                     float[] position, float[] rotation,
                                     boolean shoot,
                                     int health
    ) {
        this.name = name;
        this.chx = chx;
        this.position = position;
        this.rotation = rotation;
        this.shoot = shoot;
        this.serverHealth = health;
    }

}
