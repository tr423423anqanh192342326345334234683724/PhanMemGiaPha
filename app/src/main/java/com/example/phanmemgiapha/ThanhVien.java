package com.example.phanmemgiapha;

public class ThanhVien {
    private int id;
    private String ten;
    private int tuoi;
    private int theHe;

    public ThanhVien(int id, String ten, int tuoi, int theHe) {
        this.id = id;
        this.ten = ten;
        this.tuoi = tuoi;
        this.theHe = theHe;
    }

    // Getters
    public int getId() { return id; }
    public String getTen() { return ten; }
    public int getTuoi() { return tuoi; }
    public int getTheHe() { return theHe; }
}
