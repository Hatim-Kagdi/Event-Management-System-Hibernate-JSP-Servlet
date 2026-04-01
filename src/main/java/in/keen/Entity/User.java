package in.keen.Entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_name" ,nullable = false)
	private String userName;

	@Column(name = "user_email",unique = true, nullable = false)
	private String userEmail;

	@Column(name = "user_password",nullable = false)
	private String userPassword;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_role")
	private Role userRole;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Profile profile;

	@Column
	private boolean is_deleted = false;
	
	@OneToMany(mappedBy = "organizer" , cascade = CascadeType.ALL)
	private List<Event> organizedEvents;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public User(int userId, String userName, String userEmail, String userPassword, Role userRole, Profile profile,
			boolean is_deleted) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.profile = profile;
		this.is_deleted = is_deleted;
	}
	
	public User() {}

	public enum Role {
		ADMIN, ORGANIZER, ATTENDEE
	}
}
