## Expense Reimbursement System (ERS)

# Description

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

# Features

* Employee
  * Login
  * View reimbursement tickets
  * Request reimbursement ticket
* Finance Manager
  * Login
  * View pending requests
  * Approve or deny requests
  * Filter requests by status

# Technologies Used

* Backend
  * Java - version 1.8.0_271
  * PostgreSQL - version 42.2.5
  * Maven - version 2.22.1
  * JUnit - version 5.4.2
  * Log4j - version 1.2.17
  * Tomcat - version 9.0
  * Javax - version 4.0.1
  * Jackson - version 2.12.0
* Frontend
  * Bootstrap - version 4.1.1
  * HTML
  * CSS
  * JavaScript
* DevOps / Cloud
  * Docker
  * Jenkins
  * AWS RDS
  * AWS EC2
  * AWS S3
* Version Control
  * Git

# Getting Started

1. Copy the repository https url by pressing the green clone button, or copy the url and add .git to the end.

2. Clone the repository by opening Git Bash at the desired location and running the clone command:

  `git clone (name-of-url).git`

3. Open IDE (Spring Tool Suite) that can run a Java Maven project and import as an existing Maven project.

4. Configure PostgreSQL settings in ERSDbUtilProps.java.

5. Run the application on a Tomcat 9 server.

6. Open ERS.html on your browser.

# Usage

You can login and will be redirected to either Employee or Finance 
Manager dashboard depending on your user role. If logged in as an 
Employee, you can either view your past tickets or create a new 
reimbursement ticket request. As a Finance Manager, you can view
all ticket requests, filter them by status, and approve or deny 
any pending requests.
