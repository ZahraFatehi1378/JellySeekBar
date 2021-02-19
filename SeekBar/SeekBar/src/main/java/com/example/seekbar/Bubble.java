package com.example.seekbar;


public class Bubble {

    private final int x;
    private final int y;
    private int r;
    private int alpha ;
    Long createdTime;


    public Bubble(int x, int y, int r, int alpha, Long createdTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.alpha = alpha;
        this.createdTime = createdTime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public Long getCreatedTime() {
        return createdTime;
    }
}
