CREATE SEQUENCE student_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE address_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE course_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE tutor_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE special_type_seq INCREMENT BY 1 START WITH 1;

CREATE TABLE STUDENTS (
stud_id INT NOT NULL DEFAULT nextval('student_seq'),
name text NOT NULL,
email text NOT NULL,
dob DATE DEFAULT NULL,
addr_id INT DEFAULT NULL,
PRIMARY KEY (stud_id)
);

CREATE TABLE ADDRESS (
addr_id INT NOT NULL DEFAULT nextval('address_seq'),
city text NOT NULL,
street text NOT NULL,
zip_code text NOT NULL,
PRIMARY KEY (addr_id)
);

CREATE TABLE TUTOR (
tutor_id INT NOT NULL DEFAULT nextval('tutor_seq'),
name text NOT NULL,
email text NOT NULL,
PRIMARY KEY (tutor_id)
);

CREATE TABLE COURSE (
course_id INT NOT NULL DEFAULT nextval('course_seq'),
name text NOT NULL,
description text NOT NULL,
start_date DATE NULL,
end_date DATE NULL,
tutor_id INT NULL,
PRIMARY KEY (course_id)
);

CREATE TABLE SPECIAL_TYPE(
id INT NOT NULL DEFAULT nextval('special_type_seq'),
bytes_data bytea NULL,
PRIMARY KEY (id)
);



insert into STUDENTS (name, email, dob) values ('Noah Huang','Noah.Z.H.Huang@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('Allie','Allie@****.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_A','stu_A@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_B','stu_B@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_C','stu_C@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_D','stu_D@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_E','stu_E@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_F','stu_F@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_G','stu_G@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_H','stu_H@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_I','stu_I@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_J','stu_J@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_K','stu_K@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_L','stu_L@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_M','stu_M@**.com','1987-07-07');
insert into STUDENTS (name, email, dob) values ('stu_N','stu_N@**.com','1987-07-07');


insert into ADDRESS(city, street, zip_code) values ('GZ','Tian He Road','510000');
insert into ADDRESS(city, street, zip_code) values ('SH','**** Rold','123456');
insert into STUDENTS (name, email, dob, addr_id) values ('Peter','Peter@**.com','1987-07-07',1);
insert into STUDENTS (name, email, dob, addr_id) values ('Tom','Tom@**.com','1987-07-07',2);


insert into TUTOR(name, email) values ('Noah', 'Noah@**.com');
insert into TUTOR(name, email) values ('Allie', 'Allie@**.com');

insert into COURSE(name, description, tutor_id) values ('Java', 'Thinking In Java',1);
insert into COURSE(name, description, tutor_id) values ('JavaScript', 'Thinking In JavaScript',1);
insert into COURSE(name, description, tutor_id) values ('DB', 'DB Design',2);


