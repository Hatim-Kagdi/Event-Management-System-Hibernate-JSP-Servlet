package in.keen.DAO;

import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

import org.hibernate.*;

import in.keen.Entity.Profile;
import in.keen.Entity.User;
import in.keen.Util.HibernateUtil;

public class UserDAO {

	public boolean registerUser(User user, byte[] profilePicture, String bio) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			String hashedPW = BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt());
			user.setUserPassword(hashedPW);

			Profile profile = new Profile();
			profile.setUserBio(bio);
			profile.setProfilePicture(profilePicture);
			profile.setUser(user);

			user.setProfile(profile);

			session.save(user);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	public User loginUser(String email, String password) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM User WHERE userEmail = :email AND is_deleted = false";
			User user = session.createQuery(hql, User.class).setParameter("email", email).uniqueResult();
			if (user != null && BCrypt.checkpw(password, user.getUserPassword())) {
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserProfileDetails(int id) {
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String query = "SELECT u FROM User u JOIN FETCH u.profile WHERE u.userId= :id AND u.is_deleted = false";
			user = session.createQuery(query, User.class).setParameter("id", id).uniqueResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

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

	public boolean updateAttendee(User user, String userBio, byte[] profilePic) {
		Transaction t = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			t = s.beginTransaction();
			User existingUser = s.get(User.class, user.getUserId());
			
			if(existingUser == null) {
				return false;
			}
			existingUser.setUserName(user.getUserName());
			existingUser.setUserEmail(user.getUserEmail());

			
			Profile profile = existingUser.getProfile();
			if(profile == null) {
			profile = new Profile();
			profile.setUser(existingUser);
			existingUser.setProfile(profile);
			}
			
			profile.setUserBio(userBio);
			
			if(profilePic != null && profilePic.length > 0) {
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
}
