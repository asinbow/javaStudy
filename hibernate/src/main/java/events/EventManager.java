package events;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import util.HibernateUtil;

public class EventManager {
    public static void main(String[]  args) {
        EventManager mgr = new EventManager();
        
        Person person = mgr.createPerson(27, "asinbow", "wang");
        Event event = mgr.createEvent("team build", new Date());
        mgr.addPersonToEvent(person.getId(), event.getId());

        HibernateUtil.getSessionFactory().close();
    }

    private Person createPerson(int age, String firstName, String lastName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person person = new Person();
        person.setAge(age);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        session.save(person);

        session.getTransaction().commit();
        return person;
    }

    private Event createEvent(String title, Date date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event event = new Event();
        event.setTitle(title);
        event.setDate(date);
        session.save(event);

        session.getTransaction().commit();
        return event;
    }
    
	private List<Event> listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Event> result = (List<Event>)session.createQuery("from Event").list();
        session.getTransaction().commit();
        return result;
    }
	
	private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Person person = (Person)session.load(Person.class, personId);
        Event event = (Event)session.load(Event.class, eventId);
        person.getEvents().add(event);
        
        session.getTransaction().commit();
	}
}
