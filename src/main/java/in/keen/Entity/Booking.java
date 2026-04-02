package in.keen.Entity;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name="bookings")
public class Booking {
	@Id
	@Column(name = "booking_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attendee_id", nullable = false)
	private User attendee;
	
	@ManyToOne
	@JoinColumn(name = "event_id", nullable = false)
	private Event event;
	
	@Column(name = "booking_date")
	private LocalDateTime bookingDate;
	
	@Column(name = "booking_status")
	private String status;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public User getAttendee() {
		return attendee;
	}

	public void setAttendee(User attendee) {
		this.attendee = attendee;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Booking(int bookingId, User attendee, Event event, LocalDateTime bookingDate, String status) {
		super();
		this.bookingId = bookingId;
		this.attendee = attendee;
		this.event = event;
		this.bookingDate = bookingDate;
		this.status = status;
	}

	public Booking() {}
	
	
}
