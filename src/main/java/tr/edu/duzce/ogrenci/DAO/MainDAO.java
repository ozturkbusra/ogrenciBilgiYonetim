package tr.edu.duzce.ogrenci.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.edu.duzce.ogrenci.model.LoginModel;
import tr.edu.duzce.ogrenci.model.OgrenciModel;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public class MainDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Object loadObject(Class sinif, Serializable id) {

        return getCurrentSession().get(sinif, id);
    }

    public boolean saveOrUpdateObject(Object object) {
        try{
            getCurrentSession().save(object);
            return true;
        }catch(Exception e)
            {return false;}
    }

    public boolean removeObject(Object object) {
        try {
            getCurrentSession().remove(object);
            return true;
        }catch(Exception e)
            {return false; }
    }

    //Ogrenci numarası ile sorgu
    public OgrenciModel getOgrenciByOgrenciNo(Integer  ogrenciNo) {
        OgrenciModel ogrenci =null;
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<OgrenciModel> criteriaQuery = criteriaBuilder.createQuery(OgrenciModel.class);
        Root<OgrenciModel> root = criteriaQuery.from(OgrenciModel.class);

        Predicate predicate = criteriaBuilder.equal(root.get("ogrenciNo"), ogrenciNo);
        criteriaQuery.select(root).where(predicate);

        Query<OgrenciModel> query = getCurrentSession().createQuery(criteriaQuery);
        try {
            ogrenci = query.getSingleResult(); //getSingleResult ile sadece bir tane öğrenci gelecek. Hiç gelmemesi veya birden çok gelmesi durumunda Exception
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ogrenci;
    }



    // Coklu select sorgusu ile Ogrenci listesi dondurme
    public List<OgrenciModel> loadOgrenci() {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<OgrenciModel> criteriaQuery = criteriaBuilder.createQuery(OgrenciModel.class);
        Root<OgrenciModel> root = criteriaQuery.from(OgrenciModel.class);
        criteriaQuery.select(root);
        Query<OgrenciModel> query = getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList(); //Birden cok Ogrenci alıyor getResultList ile
    }

    public List<OgrenciModel> loadOgrenci1(Integer bolumId) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<OgrenciModel> criteriaQuery = criteriaBuilder.createQuery(OgrenciModel.class);
        Root<OgrenciModel> root = criteriaQuery.from(OgrenciModel.class);
        Predicate predicate = criteriaBuilder.equal(root.get("bolum"), bolumId);

        criteriaQuery.select(root).where(predicate);
        Query<OgrenciModel> query = getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList(); //Birden cok Ogrenci alıyor getResultList ile
    }

    public List<LoginModel> loadLogin(String ad, String sifre) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<LoginModel> criteriaQuery = criteriaBuilder.createQuery(LoginModel.class);
        Root<LoginModel> root = criteriaQuery.from(LoginModel.class);
        Predicate predicate = criteriaBuilder.equal(root.get("kullaniciAdi"), ad);
        Predicate predicate2 = criteriaBuilder.equal(root.get("sifre"), sifre);

        criteriaQuery.select(root).where(criteriaBuilder.and(predicate,predicate2));
        Query<LoginModel> query = getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList(); //Birden cok Ogrenci alıyor getResultList ile
    }
}
