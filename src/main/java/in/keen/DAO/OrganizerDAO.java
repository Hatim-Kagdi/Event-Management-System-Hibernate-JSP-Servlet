package in.keen.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.keen.Entity.Profile;
import in.keen.Entity.User;
import in.keen.Util.HibernateUtil;

public class OrganizerDAO {
	// Get all Organizer
	public List<User> getAllOrganizer() {
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			String query = "SELECT u FROM User u JOIN FETCH u.profile WHERE u.userRole = 'ORGANIZER' AND u.is_deleted = false";
			List<User> list = s.createQuery(query, User.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Update Organizer
	public boolean updateOrganizer(User user, String bio, byte[] image) {
		Transaction t = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			t = s.beginTransaction();
			User u = s.get(User.class, user.getUserId());
			u.setUserName(user.getUserName());
			u.setUserEmail(user.getUserEmail());

			Profile p = u.getProfile();
			if (p == null) {
				p = new Profile();
				p.setUser(u);
				u.setProfile(p);
			}
			p.setUserBio(bio);
			if (image != null && image.length > 0) {
				p.setProfilePicture(image);
			}
			s.update(u);
			t.commit();
			return true;
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
			return false;
		}
	}

	// Delete Organizer
	public boolean deleteOrganizer(int id) {
		Transaction t = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			t = s.beginTransaction();
			User user = s.get(User.class, id);
			if (user != null) {
				user.setIs_deleted(true);
			}
			s.update(user);
			t.commit();
			return true;
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
			return false;
		}
	}
	
	
	//Search
	public List<User> getSearchedOrganizer(String searchQuery, int page, int pageSize) {
		List<User> list = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			int offset = (page - 1)*pageSize;
			String query = "SELECT u FROM User u JOIN FETCH u.profile WHERE (str(u.userId) LIKE :search OR u.userName LIKE :search) AND u.is_deleted = false AND u.userRole = 'ORGANIZER'";
			list = s.createQuery(query, User.class)
					.setFirstResult(offset)
					.setMaxResults(pageSize)
					.setParameter("search", "%" + searchQuery + "%")
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Long getSearchedOrganizerCount(String searchQuery) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String query = "SELECT COUNT(u) FROM User u WHERE (str(u.userId) LIKE :search OR u.userName LIKE :search) AND u.is_deleted = false AND u.userRole = 'ORGANIZER'";
			return s.createQuery(query, Long.class).setParameter("search", "%" + searchQuery + "%").uniqueResult();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Pagination
	public List<User> getOrganizerPagination(int page, int pageSize) {
		List<User> list = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			int offset = (page - 1) * pageSize;
			String query = "SELECT u FROM User u JOIN FETCH u.profile WHERE u.is_deleted = false AND u.userRole = 'ORGANIZER'";
			list = s.createQuery(query, User.class).setFirstResult(offset).setMaxResults(pageSize).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Long getAllOrganizerCount() {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String query = "SELECT COUNT(u) FROM User u WHERE u.is_deleted = false AND u.userRole = 'ORGANIZER'";
			return s.createQuery(query, Long.class).uniqueResult();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
