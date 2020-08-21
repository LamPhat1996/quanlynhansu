package tdc.edu.vn.luyendethi;

public class Person {
    String hoten,gioitinh,noisinh,sothich;

    public Person() {
    }

    public Person(String hoten, String gioitinh, String noisinh, String sothich) {
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.noisinh = noisinh;
        this.sothich = sothich;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNoisinh() {
        return noisinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
    }

    public String getSothich() {
        return sothich;
    }

    public void setSothich(String sothich) {
        this.sothich = sothich;
    }

    @Override
    public String toString() {
        return "Person{" +
                "hoten='" + hoten + '\'' +
                ", gioitinh='" + gioitinh + '\'' +
                ", noisinh='" + noisinh + '\'' +
                ", sothich='" + sothich + '\'' +
                '}';
    }
}
