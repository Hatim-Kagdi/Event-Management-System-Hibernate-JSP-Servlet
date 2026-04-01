package in.keen.Entity;

import javax.persistence.*;

@Entity
@Table(name = "user_profiles")
public class Profile {

	@Id
	private int profileId;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@MapsId
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "user_bio")
	private String userBio;

	@Lob
	@Column(name = "profile_picture", columnDefinition = "LONGBLOB")
	private byte[] profilePicture;

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserBio() {
		return userBio;
	}

	public void setUserBio(String userBio) {
		this.userBio = userBio;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Profile(int profileId, User user, String userBio, byte[] profilePicture) {
		super();
		this.profileId = profileId;
		this.user = user;
		this.userBio = userBio;
		this.profilePicture = profilePicture;
	}
	
	public Profile() {}
}
