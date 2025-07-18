Team Member Contribution:
WONG JIA XI	B032310495
CHAI JIAN JIE B032310296
YAP ZHAN HONG B032310386
OOI XIEN XIEN B032310183
LOO WEN CHUAN B032310334
NG CHUN KEAT B032310265
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
Programming language:Java
Framework :Springboot
Other libraries：
Firebase Admin SDK
ZXing (Zebra Crossing)
Webcam Capture API
Json library
JFreeChart Library

List the programming language, framework (e.g., Node.js with Express, Python with Django), and other key libraries used.

API Documentation: This is the most critical part. It should include:

(1) A list of all API endpoints (e.g., /api/books, /api/users/login).

(2) The HTTP method for each endpoint (GET, POST, PUT, DELETE).

(3) Required request parameters, headers, and body formats (with JSON examples).

(4) Example success and error responses (with status codes and JSON examples).

(5) Security: Detail the security measures implemented. Explain the choice of mechanism (e.g., JWT, OAuth 2.0, API Keys) and describe how it protects the endpoints.

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




Schema Justification: Briefly explain why the database was designed this way, highlighting key tables and relationships.

Business Logic and Data Validation

Use Case Diagrams/Flowcharts: Illustrate the main user flows, such as "selecting a book," "borrowing a book," and "returning a book." This visually demonstrates the business logic.

Data Validation: 
Frontend:
Empty Field Checks:


Required input fields such as Activity Title, Venue, Date & Time, Student Name, and Email are checked before submission.


If any mandatory field is left blank, a warning JOptionPane message is shown and the data is not submitted.


Format Validation:


Email addresses are validated with regular expressions to ensure they follow the correct format (e.g., example@student.utem.edu.my).


Numeric fields like Maximum Participants are checked to allow only digits.


Date/Time input via JSpinner ensures the user selects valid date values.


Dropdown Selections:


For combo boxes (e.g., activity categories or event filters), validation ensures that users do not proceed with the default or invalid selection.


QR Code Attendance:


In the student app, before marking attendance via QR scan, the app validates that the student has already registered for that activity.




Backend:
Email Uniqueness:


When a student registers for an activity, the backend checks Firestore to ensure the same email has not already registered for the same event.


Activity Capacity Check:


Before allowing a new registration, the backend checks whether the number of current participants has reached the maximum set in the activity's max_participants field.


Data Consistency & Integrity:


Cloud Functions validate that activity documents exist before writing registration or attendance data to avoid orphan records.


Attendance submissions are validated against valid QR codes (matched with backend records) to prevent spoofing or duplicate attendance.


Authentication Verification (optional):


If Firebase Authentication is integrated, backend functions also verify user tokens before accepting sensitive write operations.

Use Case diagram
The Use Case Diagram provides a high-level overview of the interactions between different types of users (actors) and the core functionalities (use cases) of the EventGo application. It helps stakeholders understand who can perform what actions within the system.

 Actors Involved:
Admin: Responsible for managing events, reviewing registrations, generating QR codes, and analyzing attendance.
Student: Can view events, register for them, and check in using QR codes.
Staff: Handles on-site QR code scanning during check-in.




