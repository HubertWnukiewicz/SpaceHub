package com.example.demo1;

import entity.MissionEntity;
import entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8090")
public class MissionController {

    private SessionFactory factory;

    public MissionController() {
        Configuration configuration = new Configuration().addAnnotatedClass(MissionEntity.class).configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties());
        this.factory = configuration.buildSessionFactory(builder.build());
    }

    @RequestMapping(value = "/mission/insert", method = RequestMethod.POST)
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

    @RequestMapping(value = "/mission/{id}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/mission/all", method = RequestMethod.POST)
    public ResponseEntity<?> getAll()
    {
        Session session = factory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<MissionEntity> cq = cb.createQuery(MissionEntity.class);
        Root<MissionEntity> rootEntry = cq.from(MissionEntity.class);
        CriteriaQuery<MissionEntity> all = cq.select(rootEntry);
        TypedQuery<MissionEntity> allQuery = session.createQuery(all);
        List<MissionEntity> response = allQuery.getResultList();
        session.close();

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/mission/find_by_name", method = RequestMethod.POST)
    public ResponseEntity<?> findByName(@PathVariable String missionName)
    {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT me.name FROM MissionEntity me WHERE me.name LIKE :mission_name");
        query.setParameter("mission_name", missionName);
        List<MissionEntity> me = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok(me);

    }

    @RequestMapping(value = "/mission/find_by_type", method = RequestMethod.POST)
    public ResponseEntity<?> findByType(@PathVariable String missionType)
    {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT me.name FROM MissionEntity me WHERE me.imageryType = :mission_type");
        query.setParameter("mission_type", missionType);
        List<MissionEntity> me = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok(me);

    }


}
