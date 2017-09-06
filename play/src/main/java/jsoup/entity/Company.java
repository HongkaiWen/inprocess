package jsoup.entity;

import java.util.List;

/**
 * Created by player on 2017/8/31.
 */
public class Company {


    public Company(String name, String city, String address, String desc, String xingzhi, String guimo) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.desc = desc;
        this.xingzhi = xingzhi;
        this.guimo = guimo;
    }

    private String name;

    private String city;

    private String address;

    private String desc;

    private String xingzhi;

    private String guimo;

    private String detailUrl;

    private List<Job> jobs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getXingzhi() {
        return xingzhi;
    }

    public void setXingzhi(String xingzhi) {
        this.xingzhi = xingzhi;
    }

    public String getGuimo() {
        return guimo;
    }

    public void setGuimo(String guimo) {
        this.guimo = guimo;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
