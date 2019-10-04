package tr.edu.duzce.ogrenci.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name = "bolum")
public class BolumModel implements Serializable {

    @Id
    @Column(name = "bolum_id", nullable = false)
    private Integer bolumId;

    @Column(name = "bolum_ad", nullable = false)
    private String bolumAd;

    @ManyToOne
    @JoinColumn(name = "fakulte_id", referencedColumnName = "fakulte_id")
    private FakulteModel fakulte;

    @OneToMany(mappedBy = "bolum", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OgrenciModel> ogrenciList;

    @OneToMany(mappedBy = "bolum", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DanismanModel> danismanList;

    public Integer getBolumId() {
        return bolumId;
    }

    public void setBolumId(Integer bolumId) {
        this.bolumId = bolumId;
    }

    public String getBolumAd() {
        return bolumAd;
    }

    public void setBolumAd(String bolumAd) {
        this.bolumAd = bolumAd;
    }

    public FakulteModel getFakulte() {
        return fakulte;
    }

    public void setFakulte(FakulteModel fakulte) {
        this.fakulte = fakulte;
    }

    public List<OgrenciModel> getOgrenciModelList() {
        return ogrenciList;
    }

    public void setOgrenciModelList(List<OgrenciModel> ogrenciList) {
        this.ogrenciList = ogrenciList;
    }

    public List<DanismanModel> getDanismanList() {
        return danismanList;
    }

    public void setDanismanList(List<DanismanModel> danismanList) {
        this.danismanList = danismanList;
    }
}
