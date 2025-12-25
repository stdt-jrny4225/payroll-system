# Employee Payroll Management System (Java)

A desktop-based **Employee Payroll Management System** built using **Core Java, Swing, JDBC, and MariaDB**.  
This project demonstrates real-world application of **OOP concepts**, **database integration**, and a **dashboard-style GUI**.

---

## 🚀 Features

- 📋 Employee Management (Add, View, Update, Delete)
- 💰 Payroll Generation
- 🧾 Payslip Management
- 📄 Payslip PDF Export (iText)
- 🖥️ Dashboard UI with Sidebar Navigation
- 🗄️ MariaDB Database Integration
- 🔄 Real-time UI refresh after database operations
- ⚠️ Confirmation dialogs for critical actions

---

## 🧠 Concepts Used

- Object-Oriented Programming (OOP)
  - Encapsulation
  - Inheritance
  - Polymorphism
  - Abstraction
- Java Swing (GUI)
- JDBC (Database Connectivity)
- DAO Design Pattern
- Service Layer Architecture
- CardLayout (Dashboard UI)
- PDF Generation using iText

---

## 🛠️ Technologies

- **Language:** Java  
- **GUI:** Java Swing  
- **Database:** MariaDB  
- **Connectivity:** JDBC  
- **PDF Library:** iText  
- **IDE:** VS Code  
- **OS:** Windows  

---

## 🗂️ Project Structure

payroll/

├── PayrollGUI.java

├── EmployeeDAO.java

├── PayrollDAO.java

├── PayslipDAO.java

├── PayrollService.java

├── DBConnection.java

├── PayslipPDFExporter.java

├── AddEmployeeForm.java

├── ViewEmployeesFrame.java

├── PayslipFrame.java

└── UpdateDeleteEmployeeFrame.java


---

## ▶️ How to Run

```bash
javac -cp ".;payroll/lib/*" payroll/*.java
java  -cp ".;payroll/lib/*" payroll.PayrollGUI
```

# 📌 Future Enhancements

Login & Role-based Access (Admin / HR)

Monthly Payroll History

Search & Filter Employees

Charts & Analytics

JavaFX UI Upgrade

# 👤 Author

Sanjan Kumar

💼 Java Developer (Learning Phase)

🔗 GitHub: https://github.com/stdt-jrny4225/payroll-system
