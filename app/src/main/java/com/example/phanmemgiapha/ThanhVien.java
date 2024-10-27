package com.example.phanmemgiapha;

public class ThanhVien {
    private int id;
    private String ten;
    private int tuoi;
    private int theHe;
    private String moiQuanHe;
    private String ben;

    public ThanhVien(int id, String ten, int tuoi, int theHe, String mqh, String ben) {
        this.id = id;
        this.ten = ten;
        this.tuoi = tuoi;
        this.theHe = theHe;
        this.moiQuanHe = mqh;
        this.ben = ben;
    }

    // Getters
    public int getId() { return id; }
    public String getTen() { return ten; }
    public int getTuoi() { return tuoi; }
    public int getTheHe() { return theHe; }
    public String getMqiQuanHe() { return moiQuanHe; }
    public String getBen() { return ben; }
}
