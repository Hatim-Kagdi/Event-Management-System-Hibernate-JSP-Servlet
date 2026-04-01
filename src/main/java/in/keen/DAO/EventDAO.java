package in.keen.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.keen.Entity.Event;
import in.keen.Entity.User;
import in.keen.Util.HibernateUtil;

public class EventDAO {
	public boolean createNewEvent(Event event, int organizerId) {
		Transaction t = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			t = s.beginTransaction();
			User org = s.load(User.class, organizerId);
			event.setOrganizer(org);
			s.persist(event);
			t.commit();
			return true;
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
			return false;
		}
	}

	public List<Event> getAllEvents(int organizerId) {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			String query = "FROM Event e WHERE e.isActive = true AND e.organizer.userId = :id";
			return s.createQuery(query, Event.class).setParameter("id", organizerId).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Event getEventById(int id) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			return s.get(Event.class, id);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean updateEvent(Event event) {
		Transaction t = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			t = s.beginTransaction();
			Event existingEvent = s.get(Event.class, event.getEventId());
			if(existingEvent != null) {
				existingEvent.setEventName(event.getEventName());
				existingEvent.setEventDescription(event.getEventDescription());
				existingEvent.setEventDate(event.getEventDate());
				existingEvent.setEventTime(event.getEventTime());
				existingEvent.setEventVenue(event.getEventVenue());
				existingEvent.setMaxCapacity(event.getMaxCapacity());
				t.commit();
				return true;
			}
			return false;
		}catch(Exception e) {
			if(t != null) t.rollback();
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteEvent(int id) {
		Transaction t = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			t = s.beginTransaction();
			Event existing = s.get(Event.class, id);
			if(existing != null) {
			existing.setActive(false);
			t.commit();
			return true;
			}
			return false;
		}catch(Exception e) {
			if(t != null) t.rollback();
			e.printStackTrace();
			return false;
		}
	}
}
