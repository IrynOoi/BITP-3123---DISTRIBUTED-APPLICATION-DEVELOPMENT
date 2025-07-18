
## Team Member Contribution

**WONG JIA XI** B032310495  
**CHAI JIAN JIE** B032310296  
**YAP ZHAN HONG** B032310386  
**OOI XIEN XIEN** B032310183  
**LOO WEN CHUAN** B032310334  
**NG CHUN KEAT** B032310265



Note: The technical documentation should be provided on Github as a Markdown readme file located at the root of your repository. The contents may include the following:
Introduction
The EventGo application is a desktop-based event management and attendance tracking system designed to support the end-to-end process of organizing, managing, and monitoring events. Developed using Java Swing within the Eclipse IDE, the application provides a graphical user interface (GUI) for both administrators and users to interact with the system. It integrates with Firebase Authentication for secure login access and utilizes MySQL or Firebase Firestore for backend data storage, ensuring a reliable and scalable architecture.
The system is structured to manage six key business processes: event creation, user registration, registration review, QR code generation, on-site check-in, and attendance reporting. Admin users can log in via Firebase and access an interface to perform full CRUD operations on events. This allows them to create, edit, view, and delete event information directly through the application, ensuring flexible event management capabilities.
Users can register for events using a built-in form, which captures and stores their registration with an initial status of "Pending Approval". This registration data is stored in the backend and awaits review by a designated reviewer or administrator. The review process enables authorized personnel to assess registrations and approve or reject them based on predefined criteria. The user-friendly interface streamlines this process and updates the registration status accordingly.
For registrations that are approved, the system automatically generates a QR Code using the ZXing library. This QR code encodes the unique combination of userId and eventId, serving as a digital ticket for event check-in. The code is displayed on the confirmation screen or made available for download, enabling seamless check-in at the event venue.
During on-site check-in, staff members can scan QR codes using a webcam or scanning tool integrated into the system. The scanned code is matched against the backend records to validate the registration, and the system then records the check-in time. This process ensures accurate attendance tracking and prevents unauthorized access to the event.
Finally, the EventGo application supports real-time attendance monitoring and reporting. Administrators can generate statistics such as total registrants, actual attendance count, and event attendance rates. Reports can be exported in CSV or JSON formats, offering flexibility for analysis or archival purposes. Overall, EventGo provides a robust and efficient solution for event administration, user participation, and data-driven decision-making.
Project Overview
EventGo is a desktop application built with Java Swing that simplifies event management and attendance tracking. It allows admins to create events, review registrations, and generate QR codes for approved participants. Users can register through the system, and staff can scan QR codes on-site to record attendance.
The system uses Firebase for authentication and MySQL or Firestore for data storage. It solves common issues like manual registration, slow check-in, and poor attendance tracking by automating these processes. EventGo is ideal for schools, tuition centers, and small organizations looking for an efficient event management solution.

Project Objectives
To automate and simplify the end-to-end event management workflow, including event creation, user registration, and approval, thereby reducing manual administrative tasks and increasing operational efficiency.


To implement a secure and accurate QR code-based check-in system, ensuring reliable attendance tracking and reducing the possibility of fraud or manual error during participant verification.


To provide real-time attendance data and exportable reports in formats such as CSV or JSON, enabling event organizers to analyze participation trends and make informed decisions for future planning.


Commercial Value / Third-Party Integration: 
The EventGo application demonstrates strong potential for real-world commercial deployment, especially in educational institutions, training centers, corporate seminars, and community event organizations. Its modular design and desktop interface make it suitable for in-house deployment in environments where centralized control and offline accessibility are important. By automating processes such as registration approval, QR-based check-in, and attendance tracking, the system reduces administrative burden and human error, while improving participant experience and data accuracy.
To ensure security, scalability, and ease of integration, EventGo leverages trusted third-party services, most notably Firebase Authentication and Firestore Database, both from Google Firebase.
Firebase Authentication is integrated to handle secure student and staff login. It provides a reliable and scalable method for authenticating users using email-password credentials, ensuring that only authorized individuals can access sensitive features like event registration or attendance monitoring. This is particularly important for educational or official settings, where privacy and account control are critical.


Firestore Database serves as a backup storage for check-in data. In addition to any primary storage mechanism (such as MySQL), Firestore offers real-time synchronization, cloud-based access, and high availability. This backup layer ensures that attendance data remains intact even if local databases fail, and it can be used to sync real-time check-in statuses across devices or locations.


The use of these external APIs not only accelerates development by handling complex backend tasks (authentication, syncing, cloud storage) but also provides industry-standard reliability and security. This makes EventGo highly adaptable for institutions looking for a low-maintenance, high-performance solution for event management.


System Architecture

High-Level Diagram: A visual representation (e.g., a block diagram) showing how all components interact: the two frontend apps, the backend server, the database, and any external services. This provides a clear overview of the distributed system.


Backend Application

Technology Stack: 

 Frontend Application
Category
Technology / Library
Programming Language
Java (17)
UI Framework
Java Swing
QR Code Decoding
ZXing (core, javase – v3.4.1)
Webcam Integration
Webcam Capture API (v0.3.12)
HTTP Communication
HttpURLConnection + Apache HttpClient
JSON Parsing
Jackson + org.json
Styling
FlatLaf
Testing Framework
JUnit 5 (junit-jupiter)
IDE
Eclipse IDE
Authentication
Firebase Authentication (ID token-based)


Backend Application
Category
Technology / Library
Programming Language
Java (17)
Framework
Spring Boot 3.3.13
ORM & Persistence
Spring Data JPA
Database
MySQL (via mysql-connector-j)
Build Tool
Maven
Web Framework
Spring Boot Web (REST Controllers)
Authentication
Firebase Admin SDK (ID token validation)
QR Code Handling
ZXing (core, javase – v3.5.3)
Testing Framework
Spring Boot Test + Spring Security Test
Application Packaging
Spring Boot Maven Plugin
IDE
Eclipse IDE
Version Control
GitHub



API Documentation: 

1.Security
All endpoints requiring user authentication use Firebase ID Token:
Header: Authorization: Bearer <Firebase_ID_Token>
The token is validated using Firebase Admin SDK to verify identity.
2. User API
2.1 POST /api/user/register
Function: Register a new user.
Request Body:
{
  "userId": "U001",
  "name": "John Doe",
  "email": "john@example.com",
  "role": "student"
}
Success Response:
Status: 200
Body: "User registered successfully"
Error Response:
Status: 500
Body: "Failed to register account: <error_message>"
2.2 POST /api/user/email
Function: Retrieve user info by email.
Request Body:
{
  "email": "john@example.com"
}
Success Response:
{
  "userId": "U001",
  "name": "John Doe",
  "email": "john@example.com",
  "role": "student",
  "createdAt": "2024-01-01T12:00:00"
}
Error Response:
Status: 404
Body: (no content)
3. Registration API
3.1 POST /api/registration/{eventId}
Function: Register a user to an event.
Path Variable:
eventId (integer)
Headers:
Authorization: Bearer <Firebase_ID_Token>
Success Response:
Status: 200
Body: "Registered user <userId> to event <eventId>"
Error Responses:
404: User not found
409: Already registered
500: Internal error
3.2 POST /api/registration/update-status
Function: Update a user's registration status.
Request Body:
{
  "registrationId": "12",
  "status": "approved"
}
Success Response:
Status: 200
Body: "Status updated"
3.3 GET /api/registration/registered
Function: Retrieve all events the user has registered for.
Headers:
Authorization: Bearer <Firebase_ID_Token>
Success Response:
[
  {
    "eventId": 1,
    "eventTitle": "Hackathon",
    "eventDate": "2025-07-25"
  }
]

3.4 GET /api/registration/byEvent?eventId=1
Function: Retrieve list of registrations for a specific event.
Query Parameter: eventId (integer)
Success Response:
[
  {
    "userId": "U001",
    "name": "John Doe",
    "status": "approved"
  }
]

4. Event API
4.1 GET /api/events
Function: Get all published events.
Success Response:
[
  {
    "eventId": 1,
    "title": "Tech Fest",
    "description": "Annual festival",
    "date": "2025-08-01"
  }
]

4.2 GET /api/events/QRCode?eventId=1
Function: Get QR code PNG image for the event.
Success Response:
Status: 200
Content-Type: image/png
Error Response:
404 or 500 with error message in text/plain
4.3 GET /api/events/attendanceStats?event_id=1
Function: Get attendance stats for a specific event.
Success Response:
{
  "totalRegistered": 50,
  "totalCheckedIn": 35
}

4.4 POST /api/events/publish
Function: Publish a new event.
Request Body:
{
  "title": "AI Conference",
  "description": "A conference on AI",
  "date": "2025-09-10"
}
Success Response:
Status: 200
Body: "Activity published successfully"
Error Response:
Status: 500
Body: "Failed to publish activity"
4.5 GET /api/events/available
Function: List events open for registration.

5. Check-in API
5.1 POST /api/checkin?qrToken=abc123
Function: Check in a user to an event using QR token.
Headers:
Authorization: Bearer <Firebase_ID_Token>
Success Response:
Status: 200
Body: "✅ Check-in successful"
Error Responses:
404: Not found
403: Rejected/Pending registration
500: Server error
5.2 GET /api/checkin/signIn?event_id=1
Function: Get a list of users who have checked in for the event.
Success Response:
[
  {
    "student_id": "U001",
    "name": "John Doe",
    "sign_in_time": "2025-07-18 10:30:00"
  }
]


Frontend Applications

Admin Frontend Application:

Purpose: This application is designed for university administrators. It allows them to publish new activities, generate QR codes for sign-in, manage participant registrations, monitor attendance, and view statistics. The goal is to simplify event management within the university environment.

Technology Stack: 
Language: Java
UI Framework: Java Swing
Charting Library: JFreeChart (for visualizing attendance statistics)
Others: Java Standard Library, ImageIcon (for QR display)

API Integration: The Java Swing application communicates with the Firebase backend via HTTP requests using HttpURLConnection. Each action (e.g., publishing activity, fetching QR code, retrieving attendance data) triggers an API call to a Firebase Cloud Function endpoint, which interacts with Firestore. The app parses JSON responses and updates the UI accordingly.


Student Frontend Application:

Purpose: This frontend targets students and participants. It allows users to browse events, register, check registration status, and scan QR codes to sign in at events. The goal is to make the registration and attendance process smooth and digital.

Technology Stack: 
Language: Java
UI Framework: Java Swing
Others: Java Standard Library, Webcam/QR scanning libraries (e.g., ZXing for Java)

API Integration: Similar to the admin app, the student frontend communicates with the Firebase backend using HTTP requests (via HttpURLConnection). It allows students to fetch a list of available activities, register for an activity and submit QR scan results for attendance tracking. The app handles JSON responses and provides relevant status messages or updates to the UI accordingly.




Database Design

Entity-Relationship Diagram (ERD): A diagram showing the database tables, their columns (with data types), and the relationships between them (one-to-one, one-to-many).

