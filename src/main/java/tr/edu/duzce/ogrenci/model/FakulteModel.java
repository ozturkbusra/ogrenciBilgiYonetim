package tr.edu.duzce.ogrenci.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name = "fakulte")
public class FakulteModel implements Serializable {


    @Id
    @Column(name = "fakulte_id", nullable = false)
    private Integer fakulteId;

    @Column(name = "fakulte_ad", nullable = false)
    private String fakulteAd;

    @OneToMany(mappedBy = "fakulte", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BolumModel> bolumList;

    public Integer getFakulteId() {
        return fakulteId;
    }

    public void setFakulteId(Integer fakulteId) {
        this.fakulteId = fakulteId;
    }

    public String getFakulteAd() {
        return fakulteAd;
    }

    public void setFakulteAd(String fakulteAdi) {
        this.fakulteAd = fakulteAdi;
    }

    public List<BolumModel> getBolumList() {
        return bolumList;
    }

    public void setBolumList(List<BolumModel> bolumList) {
        this.bolumList = bolumList;
    }
}
