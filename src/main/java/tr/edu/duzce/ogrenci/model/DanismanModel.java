package tr.edu.duzce.ogrenci.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "danisman")
public class DanismanModel implements Serializable {

    @Id
    @Column(name = "danisman_id", nullable = false)
    private Integer danismanId;

    @Column(name = "danisman_ad", nullable = false)
    private String ad;

    @Column(name = "danisman_soyad", nullable = false)   //danis_id,danis_ad,danis_soyad
    private String soyad;

    @ManyToOne
    @JoinColumn(name = "bolum_id", referencedColumnName = "bolum_id")
    private BolumModel bolum;

    @OneToMany(mappedBy = "danisman", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OgrenciModel> ogrenciList;

    public Integer getDanismanId() {
        return danismanId;
    }

    public void setDanismanId(Integer danismanId) {
        this.danismanId = danismanId;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

  /*  public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }*/

    public BolumModel getBolum() {
        return bolum;
    }

    public void setBolum(BolumModel bolum) {
        this.bolum = bolum;
    }

    public List<OgrenciModel> getOgrenciList() {
        return ogrenciList;
    }

    public void setOgrenciList(List<OgrenciModel> ogrenciList) {
        this.ogrenciList = ogrenciList;
    }
}
