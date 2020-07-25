create table user (
id int,
username varchar(32) not null,
password varchar(128) not null,
administrator boolean DEFAULT false,
primary key (id)
);

CREATE TABLE `tbl_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `d_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dept_emp` (`d_Id`),
  CONSTRAINT `fk_dept_emp` FOREIGN KEY (`d_Id`) REFERENCES `tbl_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;