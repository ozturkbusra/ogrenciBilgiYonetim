package tr.edu.duzce.ogrenci.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.ogrenci.DAO.MainDAO;
import tr.edu.duzce.ogrenci.model.BolumModel;
import tr.edu.duzce.ogrenci.model.DanismanModel;
import tr.edu.duzce.ogrenci.model.LoginModel;
import tr.edu.duzce.ogrenci.model.OgrenciModel;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MainService {

    @Autowired
    private MainDAO MainDAO;

    //Yeni ogrenci ekleme/güncelleme
    @Transactional
    public Boolean saveOrUpdateOgrenci(Integer ogrenciId, Integer ogrenciNo, String ogrenciTC, String ogrenciAd, String ogrenciSoyad, Integer ogrenciSinif, String ogrenciCinsiyet, Integer bolumId, Integer danismanId) {

        OgrenciModel ogrenci = (OgrenciModel) MainDAO.loadObject(OgrenciModel.class, ogrenciNo); //Bu id ye sahip bir ögrenci var mı?
        if (ogrenci == null) {
            ogrenci = new OgrenciModel();
            ogrenci.setOgrenciNo(ogrenciNo); //Yoksa yeni oluştur
        }
        ogrenci.setOgrenciId(ogrenciId);
        ogrenci.setOgrenciTc(ogrenciTC);
        ogrenci.setOgrenciAd(ogrenciAd);
        ogrenci.setOgrenciSoyad(ogrenciSoyad);

        ogrenci.setOgrenciSinif(ogrenciSinif);
        ogrenci.setOgrenciCinsiyet(ogrenciCinsiyet);

        //Ogrencinin hangi bölümde olduğu
        BolumModel bolum = (BolumModel) MainDAO.loadObject(BolumModel.class, bolumId);
        ogrenci.setBolum(bolum);

        //Ogrencinin danismanı
        DanismanModel danisman = null;
        if (danismanId != null) //danisman bilgisi girildi mi?
            danisman = (DanismanModel) MainDAO.loadObject(DanismanModel.class, danismanId);//danısman bilgisini ata
        ogrenci.setDanisman(danisman);//varsa danısmanı ata, yoksa bos gec

        return MainDAO.saveOrUpdateObject(ogrenci); //Olusturulan Ogrenciyi veritabanına kaydet/guncelle
    }

    //Belirtilen TC no ya sahip ogrenciyi sil
    @Transactional
    public Boolean deleteOgrenci(Integer ogrenciNo) {
        OgrenciModel ogrenci = (OgrenciModel) MainDAO.loadObject(OgrenciModel.class, ogrenciNo);
        return MainDAO.removeObject(ogrenci);
    }

    //Tüm ogrencilerin listesi
    public List<OgrenciModel> loadOgrenci() {
        List<OgrenciModel> list = MainDAO.loadOgrenci();
        return list;
    }

    public List<OgrenciModel> loadOgrenci1(Integer bolum) {
        List<OgrenciModel> list = MainDAO.loadOgrenci1(bolum);
        return list;
    }

    //Girilen numaraya sahip ögrenci
    public OgrenciModel getirOgrenci(Integer ogrenciNo){
        OgrenciModel ogrenci=MainDAO.getOgrenciByOgrenciNo(ogrenciNo);
        return ogrenci;
    }

    public List<LoginModel> loadLogin(String ad, String sifre){
        List<LoginModel> login=MainDAO.loadLogin(ad, sifre);
        return login;
    }


}
