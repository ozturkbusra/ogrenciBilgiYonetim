package tr.edu.duzce.ogrenci.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ogrenci")
public class OgrenciModel implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ogrenci_no", nullable = false)
    private Integer ogrenciNo;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ogrenci_id", nullable = false)
    private Integer ogrenciId;

    @Column(name = "ogrenci_tc", nullable = false, unique = true)
    private String ogrenciTc;

    @Column(name = "ogrenci_ad", nullable = false)
    private String ogrenciAd;

    @Column(name = "ogrenci_soyad", nullable = false)
    private String ogrenciSoyad;

    @Column(name = "ogrenci_sinif", nullable = false)
    private Integer ogrenciSinif;

    @Column(name = "ogrenci_cins", nullable = false)
    private String ogrenciCinsiyet;

    @ManyToOne
    @JoinColumn(name = "bolum_id", referencedColumnName = "bolum_id", nullable = false)
    private BolumModel bolum;

    @ManyToOne
    @JoinColumn(name = "danisman_id", referencedColumnName = "danisman_id", nullable = true)
    private DanismanModel danisman;

    public Integer getOgrenciId() {
        return ogrenciId;
    }

    public void setOgrenciId(Integer ogrenciId) {
        this.ogrenciId = ogrenciId;
    }

    public Integer getOgrenciNo() {
        return ogrenciNo;
    }

    public void setOgrenciNo(Integer ogrenciNo) {
        this.ogrenciNo = ogrenciNo;
    }

    public String getOgrenciTc() {
        return ogrenciTc;
    }

    public void setOgrenciTc(String ogrenciTc) {
        this.ogrenciTc = ogrenciTc;
    }

    public String getOgrenciAd() {
        return ogrenciAd;
    }

    public void setOgrenciAd(String ogrenciAd) {
        this.ogrenciAd = ogrenciAd;
    }

    public String getOgrenciSoyad() {
        return ogrenciSoyad;
    }

    public void setOgrenciSoyad(String ogrenciSoyad) {
        this.ogrenciSoyad = ogrenciSoyad;
    }

    public Integer getOgrenciSinif() {
        return ogrenciSinif;
    }

    public void setOgrenciSinif(Integer ogrenciSinif) {
        this.ogrenciSinif = ogrenciSinif;
    }

    public String getOgrenciCinsiyet() {
        return ogrenciCinsiyet;
    }

    public void setOgrenciCinsiyet(String ogrenciCinsiyet) {
        this.ogrenciCinsiyet = ogrenciCinsiyet;
    }

    public BolumModel getBolum() {
        return bolum;
    }

    public void setBolum(BolumModel bolum) {
        this.bolum = bolum;
    }

    public DanismanModel getDanisman() {
        return danisman;
    }



    public void setDanisman(DanismanModel danisman) {
        this.danisman = danisman;
    }
}
