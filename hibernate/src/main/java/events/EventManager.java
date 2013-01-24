package events;

import org.hibernate.Session;

import java.util.Date;

import util.HibernateUtil;

public class EventManager {
    public static void main(String[]  args) {
        EventManager mgr = new EventManager();
        if (args[0].equals("store")) {
            mgr.createAndStoreEvent("My Event", new Date());
        }

        HibernateUtil.getSessionFactory().close();
    }

    private void createAndStoreEvent(String title, Date date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event event = new Event();
        event.setTitle(title);
        event.setDate(date);
        session.save(event);

        session.getTransaction().commit();
    }
}
