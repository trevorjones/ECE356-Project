insert into User values('doctor1','doctor','1','asdfg','doctor','doctor1@gmail.com');
insert into User values('doctor2','doctor','2','asdfg','doctor','doctor2@gmail.com');
insert into User values('patient1','patient','1','asdfg','patient','patient1@gmail.com');
insert into User values('patient2','patient','2','asdfg','patient','patient2@gmail.com');
insert into User values('staff1','staff','1','asdfg','staff','staff1@gmail.com');
insert into User values('staff2','staff','2','asdfg','staff','staff2@gmail.com');
insert into User values('fo1','fo','1','asdfg','financial officer','fo1@gmail.com');
insert into User values('fo2','fo','2','asdfg','financial officer','fo2@gmail.com');

insert into Doctor values('doctor1','med1');
insert into Doctor values('doctor2','med2');

insert into Patient values('patient1','661 bla st.','good','12345678','6132567894',123456778);
insert into Patient values('patient2','662 bla st.','good','12345678','6132567894',123456778);

insert into Prescription values('Prescription1','p1','bla bla bla blablablabla');
insert into Prescription values('Prescription2','p2','bla bla bla blablablsabla');

insert into Doctor_Staff values('doctor1','staff1',0);
insert into Doctor_Staff values('doctor1','staff2',1);
insert into Doctor_Staff values('doctor2','staff1',1);
insert into Doctor_Staff values('doctor2','staff2',0);

insert into Doctor_Patient values('patient1','doctor1',1);
insert into Doctor_Patient values('patient2','doctor2',1);
insert into Doctor_Patient values('patient2','doctor1',0);
insert into Doctor_Patient values('patient1','doctor2',0);

insert into Appointment values('patient1','doctor1','2014-04-04 12:12:12','2014-04-04 13:12:12','scheduled','none');
insert into Appointment values('patient2','doctor2','2014-04-04 12:12:12','2014-04-04 13:12:12','scheduled','none');

insert into VisitationRecord values('patient1','doctor1','2014-04-04 12:12:12',NULL,'13:12:12','bla','2014-04-04 13:12:12','none','none','yuppy','Prescription1');
insert into VisitationRecord values('patient2','doctor2','2014-04-04 12:12:12',NULL,'13:12:12','bla','2014-04-04 13:12:12','none','none','yuppy','Prescription2');
insert into VisitationRecord values('patient1','doctor2','2015-04-04 12:12:12',NULL,'13:12:12','bla','2014-04-04 13:12:12','none','none','yuppy','Prescription2');
