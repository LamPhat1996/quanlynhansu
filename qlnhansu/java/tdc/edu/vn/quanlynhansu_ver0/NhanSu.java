package tdc.edu.vn.quanlynhansu_ver0;

public class NhanSu {
    private String name;
    private String degree;
    private String hoppies;
    private boolean check = false;

    public NhanSu() {
    }

    public NhanSu(String name, String degree, String hoppies) {
        this.name = name;
        this.degree = degree;
        this.hoppies = hoppies;
    }

    public String getName() {
        return name;
    }
    public String getDegree() {
        return degree;
    }
    public String getHoppies() {
        return hoppies;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public void setHoppies(String hoppies) {
        this.hoppies = hoppies;
    }

    @Override
    public String toString() {
        return  name + ':' + degree + ':' + hoppies + ':' ;
    }

    public void setCheck(boolean check) {
        this.check= check;
    }

    public boolean isCheck() {
        return check;
    }
}
