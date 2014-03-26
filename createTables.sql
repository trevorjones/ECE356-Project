DROP TABLE IF EXISTS VisitationRecord;
DROP TABLE IF EXISTS Appointment;
DROP TABLE IF EXISTS Doctor_Patient;
DROP TABLE IF EXISTS Doctor_Staff;
DROP TABLE IF EXISTS Prescription;
DROP TABLE IF EXISTS Patient;
DROP TABLE IF EXISTS Doctor;
DROP TABLE IF EXISTS User; 

CREATE TABLE User (
user_id CHAR(20) NOT NULL,
first_name CHAR(20) NOT NULL,
last_name CHAR(20) NOT NULL,
password CHAR(20) NOT NULL,
type ENUM ('patient', 'doctor', 'staff', 'financial officer') NOT NULL,
email CHAR(30) NOT NULL,
PRIMARY KEY (user_id)
);

CREATE TABLE Doctor (
user_id CHAR(20) NOT NULL,
specialization CHAR(30) NOT NULL,
PRIMARY KEY (user_id),
FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Patient (
user_id CHAR(20) NOT NULL,
address CHAR(30) NOT NULL,
current_health TEXT NOT NULL,
ohip CHAR(12) NOT NULL,
phone CHAR(10) NOT NULL,
sin INT(9) NOT NULL,
PRIMARY KEY (user_id),
FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Prescription (
name CHAR(20) NOT NULL,
alias CHAR(20) NOT NULL,
description TEXT NOT NULL,
PRIMARY KEY (name),
UNIQUE (alias)
);

CREATE TABLE Doctor_Staff (
doctor_user_id CHAR(20) NOT NULL,
staff_user_id CHAR(20) NOT NULL,
permission INT(1) DEFAULT 0,
PRIMARY KEY (doctor_user_id, staff_user_id),
FOREIGN KEY (doctor_user_id) REFERENCES Doctor(user_id),
FOREIGN KEY (staff_user_id) REFERENCES User(user_id)
);

CREATE TABLE Doctor_Patient (
patient_user_id CHAR(20) NOT NULL,
doctor_user_id CHAR(20) NOT NULL,
permission INT(1) DEFAULT 0,
PRIMARY KEY (patient_user_id , doctor_user_id),
FOREIGN KEY (patient_user_id) REFERENCES Patient(user_id),
FOREIGN KEY (doctor_user_id) REFERENCES Doctor(user_id)
);

CREATE TABLE Appointment (
patient_user_id CHAR(20) NOT NULL,
doctor_user_id CHAR(20) NOT NULL,
start_date DATETIME NOT NULL,
end_date DATETIME NOT NULL,
status ENUM ('scheduled', 'cancelled', 'done') NOT NULL,
proc CHAR(30) NOT NULL,
PRIMARY KEY (patient_user_id , doctor_user_id, start_date),
FOREIGN KEY (patient_user_id) REFERENCES Doctor_Patient(patient_user_id),
FOREIGN KEY (doctor_user_id) REFERENCES Doctor_Patient(doctor_user_id)
);

CREATE TABLE VisitationRecord (
patient_user_id CHAR(20) NOT NULL,
doctor_user_id CHAR(20) NOT NULL,
visit_date DATETIME NOT NULL,
updated_at TIMESTAMP NOT NULL,
length_of_visit TIME NOT NULL,
proc CHAR(30),
scheduling_of_treatment DATETIME,
freeform_comments TEXT,
surgery_performed CHAR(30),
diagnosis TEXT NOT NULL,
prescription_name CHAR(20) NOT NULL,
PRIMARY KEY (patient_user_id , doctor_user_id, visit_date, updated_at),
FOREIGN KEY (patient_user_id) REFERENCES Doctor_Patient(patient_user_id),
FOREIGN KEY (doctor_user_id) REFERENCES Doctor_Patient(doctor_user_id),
FOREIGN KEY (prescription_name) REFERENCES Prescription(name)
); 
