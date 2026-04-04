package in.keen.DAO;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.keen.Entity.Booking;
import in.keen.Entity.Event;
import in.keen.Entity.User;
import in.keen.Util.HibernateUtil;

public class BookingDAO {
	
	public String createBooking(int attendeeId, int eventId) {
		Transaction t = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			t = s.beginTransaction();
			Event event = s.get(Event.class, eventId);
			String countQuery = "SELECT COUNT(b) FROM Booking b WHERE b.event.eventId = :eid AND b.status = 'CONFIRMED'";
			Long currentBookings = (Long) s.createQuery(countQuery).setParameter("eid", eventId).uniqueResult();
			
			if(currentBookings >= event.getMaxCapacity()) {
				return "FULL";
			}
			
			Booking b = new Booking();
			b.setAttendee(s.load(User.class, attendeeId));
			b.setEvent(event);
			b.setBookingDate(LocalDateTime.now());
			b.setStatus("CONFIRMED");
			
			s.persist(b);
			t.commit();
			return "SUCCESS";
		}catch(Exception e) {
			if(t != null) t.rollback();
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Booking> getBookingByAttendeeId(int attendeeId){
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String query = "SELECT b FROM Booking b JOIN FETCH b.event e JOIN FETCH e.organizer WHERE b.attendee.userId = :uid AND b.status = 'CONFIRMED'";
			return s.createQuery(query, Booking.class).setParameter("uid", attendeeId).list();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean cancelBooking(int bookingId) {
		Transaction t = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			t = s.beginTransaction();
			Booking b = s.get(Booking.class, bookingId);
			b.setStatus("CANCELED");
			t.commit();
			return true;
		}catch(Exception e) {
			if(t != null) t.rollback();
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Booking> getBookingForEvent(int eventId){
		List<Booking> list = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String query = "SELECT b FROM Booking b JOIN FETCH b.attendee WHERE b.event.eventId = :eid";
			list = s.createQuery(query, Booking.class).setParameter("eid", eventId).list();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean isAlreadyBooked(int eventId, int attendeeId) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String query = "SELECT COUNT(b) FROM Booking b WHERE b.attendee.userId = :aId AND b.event.eventId = :eId AND b.status = 'CONFIRMED'";
			Long count = s.createQuery(query, Long.class).setParameter("aId", attendeeId).setParameter("eId", eventId).uniqueResult();
			return count > 0 && count != null ? true : false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
