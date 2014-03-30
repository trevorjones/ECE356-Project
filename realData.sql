insert into User values('doctor1','Troy','Bodie','asdfg','doctor','tbodie@gmail.com');
insert into User values('doctor2','Dave','Bolland','asdfg','doctor','dbolland@gmail.com');
insert into User values('doctor3','Tyler','Bozak','asdfg','doctor','nm1center@gmail.com');
insert into User values('doctor4','David','Clarkson','asdfg','doctor','dclarkson@gmail.com');
insert into User values('doctor5','Nazem','Kadri','asdfg','doctor','nkadri@gmail.com');
insert into User values('doctor6','Phil','Kessel','asdfg','doctor','pkessel@gmail.com');
insert into User values('doctor7','Nikolai','Kulemin','asdfg','doctor','nkule@gmail.com');
insert into User values('doctor8','Joffery','Lupul','asdfg','doctor','jlupes@gmail.com');
insert into User values('doctor9','Jay','McClement','asdfg','doctor','silentjay@gmail.com');
insert into User values('doctor10','Colton','Orr','asdfg','doctor','corr@gmail.com');
insert into User values('doctor11','Mason','Raymond','asdfg','doctor','mayray@gmail.com');
insert into User values('doctor12','James','Van Riemsdyk','asdfg','doctor','jvr@gmail.com');

insert into User values('patient1','Patrick','Bordeleau','asdfg','patient','cantskate@gmail.com');
insert into User values('patient2','Paul','Carey','asdfg','patient','pcarey@gmail.com');
insert into User values('patient3','Marc-Andre','Cliche','asdfg','patient','mac@gmail.com');
insert into User values('patient4','Matt','Duchene','asdfg','patient','duchene9@gmail.com');
insert into User values('patient5','Gabriel','Landeskog','asdfg','patient','landy@gmail.com');
insert into User values('patient6','Nathan','MacKinnon','asdfg','patient','nate29@gmail.com');
insert into User values('patient7','Brad','Malone','asdfg','patient','bmalone@gmail.com');
insert into User values('patient8','Jamie','McGinn','asdfg','patient','jamiemcginn@gmail.com');
insert into User values('patient9','Cody','McLeod','asdfg','patient','mcleod@gmail.com');
insert into User values('patient10','John','Mitchell','asdfg','patient','johnsnipe@gmail.com');
insert into User values('patient11','Ryan','OReilly','asdfg','patient','ror@gmail.com');
insert into User values('patient12','Paul','Stastny','asdfg','patient','pstastny@gmail.com');
insert into User values('patient13','Pierre-Alexander','Parenslow','asdfg','patient','papar@gmail.com');
insert into User values('patient14','Maxime','Talbot','asdfg','patient','flyers4life@gmail.com');
insert into User values('patient15','Alex','Tanguay','asdfg','patient','ltir@gmail.com');

insert into User values('staff1','Cody','Franson','asdfg','staff','cfran@gmail.com');
insert into User values('staff2','Jake','Gardiner','asdfg','staff','gardy@gmail.com');
insert into User values('staff3','Tim','Gleason','asdfg','staff','caner@gmail.com');
insert into User values('staff4','Carl','Gunnarsson','asdfg','staff','gunner@gmail.com');
insert into User values('staff5','Dion','Phaneuf','asdfg','staff','captiondion@gmail.com');
insert into User values('staff6','Paul','Rander','asdfg','staff','pranger@gmail.com');
insert into User values('staff7','Morgan','Rielly','asdfg','staff','mrielly44@gmail.com');
insert into User values('staff8','Tyson','Barrie','asdfg','staff','tbarrie@gmail.com');
insert into User values('staff9','Andre','Benoit','asdfg','staff','abenoit@gmail.com');
insert into User values('staff10','Nate','Guenin','asdfg','staff','therealnate@gmail.com');
insert into User values('staff11','Jan','Hejda','asdfg','staff','jhejda@gmail.com');
insert into User values('staff12','Nick','Holden','asdfg','staff','sniper2@gmail.com');
insert into User values('staff13','Erik','Jognson','asdfg','staff','1overall@gmail.com');
insert into User values('staff14','Cory','Sarich','asdfg','staff','sarich@gmail.com');
insert into User values('staff15','Ryan','Wilson','asdfg','staff','wilson@gmail.com');

insert into User values('fo1','Reto','Berra','asdfg','financial officer','flamesrock@gmail.com');
insert into User values('fo2','Semyon','Varlamov','asdfg','financial officer','brickwall@gmail.com');
insert into User values('fo3','Jonathan','Bernier','asdfg','financial officer','bernie@gmail.com');
insert into User values('fo4','James','Reimer','asdfg','financial officer','optimusreim@gmail.com');

insert into Doctor values('doctor1','Anaesthetics');
insert into Doctor values('doctor2','Pathology');
insert into Doctor values('doctor3','Cardiology');
insert into Doctor values('doctor4','Geriatrics');
insert into Doctor values('doctor5','Orthopaedics');
insert into Doctor values('doctor6','Paediatrics');
insert into Doctor values('doctor7','Pheunomoly');
insert into Doctor values('doctor8','Neurology');
insert into Doctor values('doctor9','Microbiology');
insert into Doctor values('doctor10','Urology');
insert into Doctor values('doctor11','Neuroradiology');
insert into Doctor values('doctor12','General Surgery');

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