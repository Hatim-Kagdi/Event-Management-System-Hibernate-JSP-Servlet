package in.keen.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private int eventId;
	
	@Column(name = "event_name", nullable = false)
	private String eventName;
	
	@Column(name = "event_description", columnDefinition="TEXT")
	private String eventDescription;
	
	@Column(name = "event_date", nullable = false)
	private LocalDate eventDate;
	
	@Column(name = "event_time", nullable = false)
	private LocalTime eventTime;
	
	@Column(name = "event_venue")
	private String eventVenue;
	
	private int maxCapacity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizer_id", nullable = false)
	private User organizer;
	
	@Column(name = "is_active")
	private boolean isActive = true;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public LocalTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventVenue() {
		return eventVenue;
	}

	public void setEventVenue(String eventVenue) {
		this.eventVenue = eventVenue;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public User getOrganizer() {
		return organizer;
	}

	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Event(int eventId, String eventName, String eventDescription, LocalDate eventDate, LocalTime eventTime,
			String eventVenue, int maxCapacity, User organizer, boolean isActive) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventVenue = eventVenue;
		this.maxCapacity = maxCapacity;
		this.organizer = organizer;
		this.isActive = isActive;
	}

	public Event() {
		// TODO Auto-generated constructor stub
	}
	
}
