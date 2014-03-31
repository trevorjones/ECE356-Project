# Modifying any values such as T or p must be done within the code

all:
	mysql -u root -p project < createTables.sql
	mysql -u root -p project < realData.sql
  mysql -u root -p project < SET GLOBAL time_zone = EST;

