insert into User values('doctor1','Mike','Cruz','asdfg','doctor','mike_cruz@gmail.com');
insert into User values('doctor2','Alice','Land','asdfg','doctor','alice_land@gmail.com');
insert into User values('patient1','Joe','1','Blake','patient','joe_blake@gmail.com');
insert into User values('patient2','Melinda','Fitzgerald','asdfg','patient','melinda_fitzgerald@gmail.com');
insert into User values('staff1','Wong','Chang','asdfg','staff','wong_chang@gmail.com');
insert into User values('staff2','Jessica','Alba','asdfg','staff','jessica_alba@gmail.com');
insert into User values('fo1','Bill','Murray','asdfg','financial officer','bill_murray@gmail.com');
insert into User values('fo2','Roger','Smith','asdfg','financial officer','roger_smith@gmail.com');

insert into Doctor values('doctor1','internal medicine');
insert into Doctor values('doctor2','surgeon');

insert into Patient values('patient1','661 George st.','good','12345678','6132567894',123456778);
insert into Patient values('patient2','1289 Water st.','good','23345678','8072567894',987456778);

insert into Prescription values('Interferon beta-1a','Avonex','For multiple sclerosis');
insert into Prescription values('Celecoxib','Celebrex','For rheumatoid arthritis');

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

insert into VisitationRecord values('patient1','doctor1','2014-04-04 12:12:12',NULL,'13:12:12','Checkup','2014-04-04 13:12:12','none','none','The patient is in good health','Interferon beta-1a');
insert into VisitationRecord values('patient2','doctor2','2014-04-04 12:12:12',NULL,'13:12:12','Checkup','2014-04-04 13:12:12','none','none','The patient is in good health','Celecoxib');
insert into VisitationRecord values('patient1','doctor2','2015-04-04 12:12:12',NULL,'13:12:12','Medical attention','2014-04-04 13:12:12','The patient was overacting','none','Gave more medication','Celecoxib');
