package in.keen.DAO;

import org.hibernate.Session;

import in.keen.Entity.User;
import in.keen.Util.HibernateUtil;

public class ProfileDAO {
	
	public byte[] getProfilePictureById(int id) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			String query = "SELECT u FROM User u JOIN FETCH u.profile WHERE u.userId= :id AND u.is_deleted = false";
			User user = s.createQuery(query ,User.class).setParameter("id", id).uniqueResult();
			byte[] image = user.getProfile().getProfilePicture();
			return image;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
