package com.example.lr_Funktionstabelle;

import java.util.LinkedList;
import java.util.List;

public class Funktionstabelle {
    private double x0;
    private double xn;
    private double hX;
    private String action;
    private String result;
    private List<Double> xList;
    private List<Double> values;

    public double getX0() {
        return x0;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public double getXn() {
        return xn;
    }

    public void setXn(double xn) {
        this.xn = xn;
    }

    public double gethX() {
        return hX;
    }

    public void sethX(double hX) {
        this.hX = hX;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Funktionstabelle{" +
                "x0=" + x0 +
                ", xn=" + xn +
                ", hX=" + hX +
                ", action='" + action + '\'' +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public List<Double> getxList() {
        return xList;
    }

    public void setxList(List<Double> xList) {
        this.xList = xList;
    }

}
