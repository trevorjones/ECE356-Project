# Modifying any values such as T or p must be done within the code

all:
	mysql -u root -p project < createTables.sql
	mysql -u root -p project < insertData.sql
