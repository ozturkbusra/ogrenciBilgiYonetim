package tr.edu.duzce.ogrenci.exception;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.ogrenci.config.AppConfig;
import tr.edu.duzce.ogrenci.config.WebAppInitializer;
import tr.edu.duzce.ogrenci.config.WebConfiguration;
import tr.edu.duzce.ogrenci.controller.MainController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class, AppConfig.class, WebConfiguration.class})
@Transactional
@WebAppConfiguration
public class TestController {
    @Autowired
    private MainController MainController;

    MockHttpServletRequest request=new MockHttpServletRequest();

    @Test
    public void testloadLogin(){
        MockHttpServletRequest request=new MockHttpServletRequest();
        String list=MainController.loadLogin("büşra","1",request);
        Assert.assertTrue(list!=null);
    }

    @Test
    public void testlogout(){
        MockHttpServletRequest request=new MockHttpServletRequest();
       try {
           MainController.logout(request);
           Assert.assertTrue(true);
       }catch (Exception e){
           Assert.assertTrue(false);
       }
    }

    @Test
    public void testloadOgrenci(){
        String list=MainController.loadOgrenci();
        Assert.assertTrue(list!=null);
    }

    @Test
    public void testloadOgrenci1(){
        String list=MainController.loadOgrenci1(1);
        Assert.assertTrue(list!=null);
    }

    @Test
    public void testdeleteOgrenci(){
        String success=MainController.deleteOgrenci(121002035);
        Assert.assertTrue(success=="true");
    }

    @Test
    public void testsaveOrUpdateOgrenci(){
        String success=MainController.saveOrUpdateOgrenci(6,121002035,"39257628197","SERHAT","AKIN",2,"E",1,null);
        Assert.assertTrue(success=="true");
    }

    @Test
    public void testgetirOgrenci(){
        String list=MainController.getirOgrenci(121002035);
        Assert.assertTrue(list!=null);
    }

}
