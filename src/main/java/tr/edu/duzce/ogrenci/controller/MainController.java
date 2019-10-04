package tr.edu.duzce.ogrenci.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tr.edu.duzce.ogrenci.model.LoginModel;
import tr.edu.duzce.ogrenci.model.OgrenciModel;
import tr.edu.duzce.ogrenci.service.MainService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/*") //Istek ust yolu belırtılerek tum ısteklerı karsılıyor
public class MainController {

    //Bağımlılık sağlandı
    @Autowired
    private MainService MainService;

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    //Tüm Ogrencileri geri döndurur
    @GetMapping(value = "/loadOgrenci.ajax") //isteğin yolu belirlenir
    public @ResponseBody //veriyi Response un gövdesine gömer
    String loadOgrenci(){
        logger.info("Tum ogrenciler aliniyor...");
        List<OgrenciModel> list=MainService.loadOgrenci();
        StringBuilder stringBuilder=new StringBuilder();
        for(OgrenciModel ogrenci : list) //liste boyunca doner
            stringBuilder.append("Ogrenci No : "+ogrenci.getOgrenciNo()+" Ad : "+ogrenci.getOgrenciAd()+" Soyad : "+ogrenci.getOgrenciSoyad()+" Sinif: "+ogrenci.getOgrenciSinif()+" TC : "+ogrenci.getOgrenciTc()+"\n");
        logger.debug("Geri donen deger : "+stringBuilder.toString());
        return stringBuilder.toString();
    }

    //Bolume gore arama
    @GetMapping(value = "/bolumeGore.ajax")
    public @ResponseBody
    String loadOgrenci1(@RequestParam Integer bolum){
        logger.info("Bolumdeki ogrenciler aliniyor...");
        List<OgrenciModel> list=MainService.loadOgrenci1(bolum);
        StringBuilder stringBuilder=new StringBuilder();
        for(OgrenciModel ogrenci : list) //liste boyunca doner
            stringBuilder.append("Ogrenci No : "+ogrenci.getOgrenciNo()+" Ad : "+ogrenci.getOgrenciAd()+" Soyad : "+ogrenci.getOgrenciSoyad()+" Sinif: "+ogrenci.getOgrenciSinif()+" TC : "+ogrenci.getOgrenciTc()+"\n");
        logger.debug("Geri donen deger : "+stringBuilder.toString());
        return stringBuilder.toString();
    }

    //Ogrenci ekleme
    @PostMapping(value = "/saveOrUpdateOgrenci.ajax")
    public @ResponseBody
    String saveOrUpdateOgrenci(@RequestParam Integer ogrenciId,@RequestParam Integer ogrenciNo, @RequestParam String ogrenciTC, @RequestParam String ogrenciAd,
                               @RequestParam String ogrenciSoyad, @RequestParam Integer ogrenciSinif,
                               @RequestParam String ogrenciCinsiyet,@RequestParam Integer bolumId, @RequestParam(required = false) Integer danismanId) {
        logger.info("Ogrenci kayıt ve guncelleme yapılıyor...");
        Boolean success = MainService.saveOrUpdateOgrenci(ogrenciId, ogrenciNo, ogrenciTC, ogrenciAd,ogrenciSoyad,ogrenciSinif,
                ogrenciCinsiyet, bolumId, danismanId);
        logger.debug("Gelen parametreler : "+ogrenciNo+","+ogrenciTC+","+ogrenciAd+","+ogrenciSoyad+","+
                ogrenciSinif+","+ogrenciCinsiyet+","+bolumId+","+danismanId+".");
        logger.debug("Geri donen deger : "+success.toString());
        return success.toString();
    }

    //Ogrenci silme
    @PostMapping(value = "/deleteOgrenci.ajax")
    public @ResponseBody
    String deleteOgrenci(@RequestParam Integer ogrenciNo) {
        logger.info("Ogrenci siliniyor...");
        Boolean success = MainService.deleteOgrenci(ogrenciNo);
        logger.debug("Gelen parametre : "+ogrenciNo);
        logger.debug("Geri donen deger : "+success.toString());
        return success.toString();
    }

    //Ogrenci Nosu sorgulama
    @GetMapping(value = "/getirOgrenci.ajax")
    public @ResponseBody
    String getirOgrenci(@RequestParam Integer ogrNo){
        logger.info("Aranan ogrencı bilgileri getiriliyor...");
        OgrenciModel ogrenci=MainService.getirOgrenci(ogrNo);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(ogrenci.getOgrenciAd()+" "+ogrenci.getOgrenciSoyad());
        logger.debug("Gelen parametre : "+ogrNo);
        logger.debug("Geri donen deger : "+stringBuilder.toString());
        return  stringBuilder.toString();
    }

    @GetMapping(value = "/loadLogin.ajax")
    public @ResponseBody
    String loadLogin(@RequestParam String ad, @RequestParam String sifre,  HttpServletRequest request){
        logger.info("Bolumdeki ogrenciler aliniyor...");
        List<LoginModel> list=MainService.loadLogin(ad,sifre);
        StringBuilder stringBuilder=new StringBuilder();
        for(LoginModel loginModel : list) //liste boyunca doner
        {
            stringBuilder.append("true");
            request.getSession().setAttribute("username",ad);
        }
        logger.debug("Geri donen deger : "+stringBuilder.toString());
        return stringBuilder.toString();
    }

    @GetMapping(value = "/logout.ajax")
    public @ResponseBody
    String logout( HttpServletRequest request){

            request.getSession().setAttribute("username",null);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("true");

        return stringBuilder.toString();
    }
}
