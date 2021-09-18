package com.example.demo1;

import entity.MissionEntity;
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

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class HomeController  {

    private SessionFactory factory;

    public HomeController() {
        Configuration configuration = new Configuration().addAnnotatedClass(MissionEntity.class).configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties());
        this.factory = configuration.buildSessionFactory(builder.build());
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<?> addMission() {
        Session session = factory.openSession();
        session.beginTransaction();
        MissionEntity me = new MissionEntity();
        me.setImageryType("Panchromatic");
        me.setName("mission_2");
        me.setStartDate(new Timestamp(System.currentTimeMillis()));
        me.setFinishDate(new Timestamp(System.currentTimeMillis()));
        session.save(me);
        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok(me);
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> getMission(@PathVariable Integer id) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT me.name FROM MissionEntity me WHERE me.id = :mission_id");
        query.setParameter("mission_id", id);
        List<MissionEntity> me = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok(me);
    }

    @RequestMapping(value = "/test/all", method = RequestMethod.POST)
    public List<?> getAll()
    {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<MissionEntity> cq = cb.createQuery(MissionEntity.class);
        Root<MissionEntity> rootEntry = cq.from(MissionEntity.class);
        CriteriaQuery<MissionEntity> all = cq.select(rootEntry);
        TypedQuery<MissionEntity> allQuery = session.createQuery(all);
        List<MissionEntity> response = allQuery.getResultList();
        session.close();

        return response;
    }
}
