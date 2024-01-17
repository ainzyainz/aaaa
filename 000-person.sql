create table if not exists person
(
	id int auto_increment
		primary key,
	age int not null,
	salary decimal null,
	passport varchar(10) null,
	address varchar(200) null,
	dateOfBirthday date null,
	dateTimeCreate timestamp default CURRENT_TIMESTAMP null,
	timeToLunch time null,
	` letter` text null
);
INSERT INTO people.person (id, age, salary, passport, address, dateOfBirthday, dateTimeCreate, timeToLunch, ` letter`) VALUES (1, 22, 2323, 'MP1287312', 'California', '1996-01-26', '2024-01-17 12:18:00', '14:00:00', 'easy');
INSERT INTO people.person (id, age, salary, passport, address, dateOfBirthday, dateTimeCreate, timeToLunch, ` letter`) VALUES (2, 1, 234234, 'MP1217222', 'New York', '2012-01-13', '2024-01-17 12:18:00', '13:00:00', 'myletter');
INSERT INTO people.person (id, age, salary, passport, address, dateOfBirthday, dateTimeCreate, timeToLunch, ` letter`) VALUES (3, 2, 25435, 'MP2173123', 'Tokyo', '2005-12-03', '2024-01-17 12:18:00', '13:00:00', 'new letter');

SELECT * FROM person
WHERE age > 21 ORDER BY dateTimeCreate

