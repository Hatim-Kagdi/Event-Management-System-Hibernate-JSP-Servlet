🚀 Key Features
1. Role-Based Access Control (RBAC)
Multi-User Architecture: Support for three distinct roles: Admin, Organizer, and Attendee, each with a dedicated dashboard and unique permissions.

Secure Authentication: User passwords are encrypted using BCrypt hashing.

Global Security Filter: Custom AuthenticationFilter implementation to protect private routes and manage session-based authorization across the entire application.

2. Advanced Event Management
Full CRUD Operations: Organizers can create, read, update, and (soft) delete events.

Real-time Availability Tracking: Integrated logic to track maxCapacity vs. current confirmed bookings.

"Sold Out" Logic: Automatic UI updates to disable booking once an event reaches peak capacity, preventing over-booking.

3. Optimized Data Fetching (Hibernate Mastery)
N+1 Problem Resolution: Extensive use of JOIN FETCH and HQL Projections to minimize database roundtrips and improve performance.

High-Performance Reporting: Correlated subqueries used to fetch event details and booking counts in a single efficient query.

Pagination & Search: Server-side pagination and dynamic search filtering for both User and Event management tables.

4. Booking & Attendee Portal
Duplicate Prevention: Sophisticated backend validation to ensure users cannot book the same event twice, while respecting canceled booking history.

Self-Service Portal: Attendees can view their active bookings and cancel spots, which triggers a status update rather than a hard record deletion (Soft Delete).

Booking History: Organizers can view a detailed list of all attendees registered for a specific event.

5. Profile & Media Management
Binary Data Handling: Support for profile picture uploads stored as LONGBLOB in the database.

Image Streaming: A dedicated Servlet to stream binary image data directly to the browser with correct MIME types.

Entity Relationships: Managed One-to-One (Profile), One-to-Many (Organizer-Events), and Many-to-Many (Bookings) relationships using Hibernate.

6. Performance & Reliability
Transaction Management: ACID-compliant transactions ensure data consistency during the booking process.

Caching: Configured for Hibernate First-Level Cache and prepared for Second-Level Cache (Ehcache) for enterprise-level scalability.

Soft Deletes: Records are marked as is_deleted or is_active = false to maintain data integrity and audit trails.

🛠️ Tech Stack
Language: Java (JDK 17+)

Web Framework: Jakarta Servlet API (JSP & Servlets)

ORM: Hibernate 5/6

Database: MySQL

Security: BCrypt Password Hashing

Utilities: Maven, HibernateUtil (Singleton Factory), Ehcache

⚙️ Installation & Setup
Follow these steps to get the project running on your local machine:

1. Prerequisites
Java Development Kit (JDK): Version 17 or higher.

Database: MySQL Server 8.0+.

IDE: Eclipse (Enterprise Edition) or IntelliJ IDEA Ultimate.

Server: Apache Tomcat 9.0 or 10.1.

2. Database Configuration
Create a new schema in MySQL:

SQL
CREATE DATABASE event_management_db;
Open src/main/resources/hibernate.cfg.xml.

Update the following properties with your local credentials:

XML
<property name="connection.url">jdbc:mysql://localhost:3306/event_management_db</property>
<property name="connection.username">YOUR_USERNAME</property>
<property name="connection.password">YOUR_PASSWORD</property>
3. Dependency Management
This project uses Maven. Ensure your pom.xml includes the following core dependencies:

hibernate-core

mysql-connector-j

j-bcrypt (for password security)

javax.servlet-api & javax.servlet.jsp-api

4. Deployment
Right-click the project in your IDE.

Select Run As > Run on Server.

Select Apache Tomcat.

Access the application at: http://localhost:8080/YourProjectName/login.jsp

📊 Database Schema (ERD)
The system uses a relational model designed for high integrity and minimal redundancy.

Database: event_management_db
Table: users (user_id, user_name, user_email, user_password, user_role, is_deleted)
Table: user_profiles (user_id, user_bio, profile_picture)
Table: events (event_id, event_name, event_description, event_date, event_time, event_venue, max_capacity, organizer_id, is_active)
Table: bookings (booking_id, attendee_id, event_id, booking_date, booking_status)

🧪 Testing the Application
Default Admin: Manually insert an Admin into the users table to begin managing the platform.

Soft Delete Test: Delete an event and verify the is_active flag changes to false in the database while the record remains for audit purposes.

Capacity Test: Attempt to book an event until the maxCapacity is reached; verify the "SOLD OUT" button appears.
