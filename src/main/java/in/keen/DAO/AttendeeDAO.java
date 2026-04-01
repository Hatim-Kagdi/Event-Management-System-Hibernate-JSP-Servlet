package in.keen.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.keen.Entity.Profile;
import in.keen.Entity.User;
import in.keen.Util.HibernateUtil;

public class AttendeeDAO {
	// Get all attendee
	public List<User> getAllAttendee() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String query = "SELECT u FROM User u JOIN FETCH u.profile WHERE u.is_deleted = false AND u.userRole = 'ATTENDEE'";
			List<User> list = session.createQuery(query, User.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Update Attendee
	public boolean updateAttendee(User user, String userBio, byte[] profilePic) {
		Transaction t = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			t = s.beginTransaction();
			User existingUser = s.get(User.class, user.getUserId());

			if (existingUser == null) {
				return false;
			}
			existingUser.setUserName(user.getUserName());
			existingUser.setUserEmail(user.getUserEmail());

			Profile profile = existingUser.getProfile();
			if (profile == null) {
				profile = new Profile();
				profile.setUser(existingUser);
				existingUser.setProfile(profile);
			}

			profile.setUserBio(userBio);

			if (profilePic != null && profilePic.length > 0) {
				profile.setProfilePicture(profilePic);
			}
			System.out.println("Updating image...");

			s.update(existingUser);
			t.commit();
			return true;
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
			return false;
		}
	}

	// Delete Attendee
	public boolean deleteAttendee(int id) {
		Transaction t = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			t = s.beginTransaction();
			User user = s.get(User.class, id);
			if (user != null) {
				user.setIs_deleted(true);
				t.commit();
				return true;
			}
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		}
		return false;
	}
}
