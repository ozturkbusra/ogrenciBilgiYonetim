package tr.edu.duzce.ogrenci.exception;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.ogrenci.DAO.MainDAO;
import tr.edu.duzce.ogrenci.config.AppConfig;
import tr.edu.duzce.ogrenci.config.WebAppInitializer;
import tr.edu.duzce.ogrenci.config.WebConfiguration;
import org.junit.runner.RunWith;
import tr.edu.duzce.ogrenci.model.BolumModel;
import tr.edu.duzce.ogrenci.model.LoginModel;
import tr.edu.duzce.ogrenci.model.OgrenciModel;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class, AppConfig.class, WebConfiguration.class})
@Transactional
@WebAppConfiguration
public class TestDAO {
    @Autowired
    private MainDAO MainDAO;

    @Test
    public void testloadObject(){
        OgrenciModel ogrenciModel=(OgrenciModel) MainDAO.loadObject(OgrenciModel.class,121002035);
        Assert.assertTrue(ogrenciModel!=null);
    }

   @Test
    public void testRemoveObject(){
        OgrenciModel ogrenciModel=(OgrenciModel) MainDAO.loadObject(OgrenciModel.class,121002035);
        boolean success=MainDAO.removeObject(ogrenciModel);
        Assert.assertTrue(success);
    }

   @Test
   public void testsaveOrUpdateObject(){
       OgrenciModel ogrenciModel=new OgrenciModel();
       BolumModel bolumModel=(BolumModel) MainDAO.loadObject(BolumModel.class,1);
       ogrenciModel.setOgrenciId(8);
       ogrenciModel.setOgrenciNo(121002035);
       ogrenciModel.setOgrenciTc("39257628197");
       ogrenciModel.setOgrenciAd("Serhat");
       ogrenciModel.setOgrenciSoyad(null);
       ogrenciModel.setOgrenciSinif(2);
       ogrenciModel.setOgrenciCinsiyet("E");
       ogrenciModel.setBolum(bolumModel);

       boolean success= MainDAO.saveOrUpdateObject(ogrenciModel);
       Assert.assertTrue(success);
   }

    @Test
    public void testloadOgrenci() {
        List<OgrenciModel> ogrenciList = MainDAO.loadOgrenci();
        Assert.assertTrue(ogrenciList.size() > 0);
    }

    @Test
    public void testgetOgrenciByOgrenciNo(){
        OgrenciModel ogrenci=MainDAO.getOgrenciByOgrenciNo(121002035);
        Assert.assertTrue(ogrenci.getOgrenciAd().equals("SERHAT"));
    }

    @Test
    public void testloadOgrenci1() {
        List<OgrenciModel> ogrenciList = MainDAO.loadOgrenci1(1);
        Assert.assertTrue(ogrenciList.size() > 0);
    }

    @Test
    public  void testloadLogin(){
        List<LoginModel> loginList=MainDAO.loadLogin("büşra","1");
        Assert.assertTrue(loginList.size()>0);
    }

}
