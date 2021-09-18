package com.example.demo1;

import entity.MissionEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;

@Controller
public class HomeController  {

    private Session session;

    public HomeController() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties());
        SessionFactory factory = configuration.buildSessionFactory(builder.build());
        this.session = factory.getCurrentSession();
//        MissionEntity me = new MissionEntity();
//        me.setImageryType("Panchromatic");
//        me.setName("mission_2");
//        me.setStartDate(new Timestamp(System.currentTimeMillis()));
//        me.setFinishDate(new Timestamp(System.currentTimeMillis()));
//        this.session.persist(me);
//        this.session.flush();
    }

    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/test")
    public String showTest() {
        return "test";
    }

//    @RequestMapping(value = "/missions", method = RequestMethod.GET)
//    public String getEmployee(Model model) {
//        this.startSession();
//        this.startTransaction();
////        EmployeeEntity employee = this.getSession().load(EmployeeEntity.class, 1);
////        Query query = this.getSession().createQuery("SELECT E.companyId, E.userId, E.startDate, E.position FROM EmployeeEntity E WHERE E.id = :user_id");
////        query.setParameter("user_id", id);
////        List selectedEmployee = query.getResultList();
//        this.commitTransaction();
//        this.closeSession();
//
//        return ResponseEntity.ok(selectedEmployee);

//        this.startSession();
//        CriteriaBuilder cb = this.getSession().getCriteriaBuilder();
//        CriteriaQuery<MissionEntity> cq = cb.createQuery(MissionEntity.class);
//        Root<MissionEntity> rootEntry = cq.from(MissionEntity.class);
//        CriteriaQuery<MissionEntity> all = cq.select(rootEntry);
//        TypedQuery<MissionEntity> allQuery = this.getSession().createQuery(all);
//        Collection<MissionEntity> response = allQuery.getResultList();
//        this.closeSession();
//        model.addAttribute("missions", response);
//        return "missions";
//    }

//    @PostMapping(value = "/missions2")
//    public List<?> getMissions(Model model) {
//        this.startSession();
//        this.startTransaction();
////        EmployeeEntity employee = this.getSession().load(EmployeeEntity.class, 1);
////        Query query = this.getSession().createQuery("SELECT E.companyId, E.userId, E.startDate, E.position FROM EmployeeEntity E WHERE E.id = :user_id");
////        query.setParameter("user_id", id);
////        List selectedEmployee = query.getResultList();
//        this.commitTransaction();
//        this.closeSession();
//
//        return ResponseEntity.ok(selectedEmployee);

//        this.startSession();
//        CriteriaBuilder cb = this.getSession().getCriteriaBuilder();
//        CriteriaQuery<MissionEntity> cq = cb.createQuery(MissionEntity.class);
//        Root<MissionEntity> rootEntry = cq.from(MissionEntity.class);
//        CriteriaQuery<MissionEntity> all = cq.select(rootEntry);
//        TypedQuery<MissionEntity> allQuery = this.getSession().createQuery(all);
//        List<MissionEntity> response = allQuery.getResultList();
//        this.closeSession();

//        return response;
//    }
}
