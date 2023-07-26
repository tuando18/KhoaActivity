package com.example.khoaactivity.DTO;

public class LopDTO {
    private int id ;
    private String ten_lop;
    private int si_so;
    private int id_khoa;
    private String ten_khoa;

    public LopDTO() {
    }

    //hàm khởi tạo thêm mới
    public LopDTO(String ten_lop, int si_so, int id_khoa) {
        this.ten_lop = ten_lop;
        this.si_so = si_so;
        this.id_khoa = id_khoa;
    }

    public LopDTO(int id, String ten_lop, int si_so, int id_khoa, String ten_khoa) {
        this.id = id;
        this.ten_lop = ten_lop;
        this.si_so = si_so;
        this.id_khoa = id_khoa;
        this.ten_khoa = ten_khoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_lop() {
        return ten_lop;
    }

    public void setTen_lop(String ten_lop) {
        this.ten_lop = ten_lop;
    }

    public int getSi_so() {
        return si_so;
    }

    public void setSi_so(int si_so) {
        this.si_so = si_so;
    }

    public int getId_khoa() {
        return id_khoa;
    }

    public void setId_khoa(int id_khoa) {
        this.id_khoa = id_khoa;
    }

    public String getTen_khoa() {
        return ten_khoa;
    }

    public void setTen_khoa(String ten_khoa) {
        this.ten_khoa = ten_khoa;
    }
}
