package com.example.demo1;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import entity.MissionEntity;
import entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ProductController {

    private SessionFactory factory;

    public ProductController() {
        Configuration configuration = new Configuration().addAnnotatedClass(MissionEntity.class).addAnnotatedClass(ProductEntity.class).configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties());
        this.factory = configuration.buildSessionFactory(builder.build());
    }

    @RequestMapping(value = "/product/insert", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct() {
        Session session = factory.openSession();
        session.beginTransaction();
        ProductEntity pe = new ProductEntity();
        pe.setMissionName("mission_2");
        pe.setAcquisitionDate(new Timestamp(System.currentTimeMillis()));
        pe.setProductFootprint("1.00|2.0|2.5|3.5");
        pe.setPrice(144.00);
        pe.setUrl("url/asdasda/asdasd");
        session.save(pe);
        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok(pe);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMission(@PathVariable Integer id) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT pe.id, pe.price FROM ProductEntity pe WHERE pe.id = :product_id");
        query.setParameter("product_id", id);
        List<ProductEntity> me = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok(me);
    }

    @RequestMapping(value = "/product/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll()
    {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> cq = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> rootEntry = cq.from(ProductEntity.class);
        CriteriaQuery<ProductEntity> all = cq.select(rootEntry);
        TypedQuery<ProductEntity> allQuery = session.createQuery(all);
        List<ProductEntity> response = allQuery.getResultList();
        session.close();

        return ResponseEntity.ok(response);
    }
    @RequestMapping(value = "/product/find_by_name", method = RequestMethod.POST)
    public ResponseEntity<?> findByName(@RequestParam String missionName)
    {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT pe.id, pe.acquisitionDate, pe.productFootprint, pe.price FROM ProductEntity pe WHERE pe.missionName LIKE :mission_name");
        query.setParameter("mission_name", missionName);
        List<ProductEntity> me = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok(me);

    }

    @RequestMapping(value = "/product/find_by_type", method = RequestMethod.POST)
    public ResponseEntity<?> findByType(@RequestParam String missionType)
    {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT me.name, pe.id, pe.price FROM ProductEntity pe LEFT JOIN MissionEntity me ON me.name = pe.missionName WHERE me.imageryType = :mission_type");
        query.setParameter("mission_type", missionType);
        List<ProductEntity> me = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok(me);

    }

    @RequestMapping(value = "/product/find_by_date", method = RequestMethod.POST)
    public ResponseEntity<?> findByDate(@RequestParam String date, @RequestParam String secondDate, @RequestParam String searchType)
    {
        Session session = factory.openSession();
        session.beginTransaction();
        List<ProductEntity> me;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date formattedDate = null, formattedSecondDate = null;
        try {
            formattedDate = formatter.parse(date);
        } catch (ParseException e) {
            throw new  IllegalArgumentException("Date has inncorect format!");
        }

        if (searchType.equals("lower")) {
            Query query = session.createQuery("SELECT pe.id,pe.acquisitionDate,pe.missionName,pe.price,pe.productFootprint,pe.url FROM ProductEntity pe WHERE pe.acquisitionDate < :date");
            query.setParameter("date", formattedDate);
            me = query.getResultList();
            //lower than
        } else if(searchType.equals("grater")) {
            //greater than
            Query query = session.createQuery("SELECT pe.id,pe.acquisitionDate,pe.missionName,pe.price,pe.productFootprint,pe.url FROM ProductEntity pe WHERE pe.acquisitionDate > :date");
            query.setParameter("date", formattedDate);
            me = query.getResultList();
        } else {
            //between
            Query query = session.createQuery("SELECT pe.id,pe.acquisitionDate,pe.missionName,pe.price,pe.productFootprint,pe.url FROM ProductEntity pe WHERE pe.acquisitionDate > :date AND pe.acquisitionDate < :secondDate");
            query.setParameter("date", formattedDate);
            try {
                formattedSecondDate = formatter.parse(secondDate);
            } catch (ParseException e) {
                throw new  IllegalArgumentException("Date has inncorect format!");
            }

            query.setParameter("secondDate", formattedSecondDate);
            me = query.getResultList();
        }

        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok(me);

    }
}

