package vovanthang.vvt.sqlsaveimage;

public class DoVat {
    private int Id;
    private String ten ;
    private String tuoi;
    private byte [] hinh;

    public DoVat(int id, String ten, String tuoi, byte[] hinh) {
        Id = id;
        this.ten = ten;
        this.tuoi = tuoi;
        this.hinh = hinh;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTuoi() {
        return tuoi;
    }

    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
