-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: gemmy_english
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,'name 1','L001','2019-12-08','2019-04-13',NULL,'donec semper sapien a libero nam dui proin leo odio','1088451',4),(2,'name 2','L002','2020-01-08','2019-11-11',NULL,'nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis in','1713920',1),(3,'name 3','L003','2019-07-23','2019-08-05',NULL,'id nisl venenatis lacinia aenean sit amet justo morbi ut odio cras','2662197',2),(4,'name 4','L004','2019-07-17','2019-06-29',NULL,'lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti','495180',3),(5,'name 5','L005','2019-04-23','2019-05-11',NULL,'nulla pede ullamcorper augue a suscipit nulla elit ac nulla sed vel enim sit amet','2671428',7),(6,'name 6','L006','2020-02-17','2019-04-22',NULL,'elementum in hac habitasse platea dictumst morbi vestibulum velit id pretium','2012758',5),(7,'name 7','L007','2019-11-19','2019-11-24',NULL,'diam id ornare imperdiet sapien urna pretium nisl ut volutpat sapien arcu','2472623',8),(8,'name 8','L008','2019-09-12','2019-10-24',NULL,'sit amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla','1244173',2),(9,'name 9','L009','2019-12-21','2020-01-30',NULL,'curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin','1410839',3),(10,'name 10','L0010','2019-06-11','2019-05-04',NULL,'aliquam lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio','2032638',4),(11,'name 11','L0011','2020-02-23','2019-11-14',NULL,'aliquam convallis nunc proin at turpis a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate','1375467',5),(12,'name 12','L0012','2019-09-22','2020-02-25',NULL,'lorem quisque ut erat curabitur gravida nisi at nibh in hac habitasse platea dictumst aliquam','495721',6),(13,'name 13','L0013','2019-05-05','2019-12-03',NULL,'aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer tincidunt ante vel ipsum praesent','1985704',7),(14,'name 4','L0014','2020-01-05','2020-02-15',NULL,'vestibulum aliquet ultrices erat tortor sollicitudin mi sit amet lobortis sapien','708348',8),(15,'name 15','L0015','2019-05-01','2019-07-21',NULL,'nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at velit','2463650',9),(16,'name 16','L0016','2019-06-14','2020-03-02',NULL,'donec semper sapien a libero nam dui proin leo odio porttitor id consequat in consequat ut nulla sed accumsan','404158',10),(17,'name 17','L0017','2019-05-15','2019-08-31',NULL,'tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat','2023797',11),(18,'name 18','L0018','2019-11-14','2019-08-05',NULL,'augue a suscipit nulla elit ac nulla sed vel enim sit amet nunc viverra dapibus nulla suscipit','2237043',12),(19,'name 19','L0019','2019-09-29','2019-08-06',NULL,'congue diam id ornare imperdiet sapien urna pretium nisl ut volutpat sapien arcu sed augue aliquam erat volutpat in congue','1230755',13),(20,'name 20','L0020','2019-08-16','2020-02-10',NULL,'lacus morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis','73728',14);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `classes_has_staff_info`
--

LOCK TABLES `classes_has_staff_info` WRITE;
/*!40000 ALTER TABLE `classes_has_staff_info` DISABLE KEYS */;
INSERT INTO `classes_has_staff_info` VALUES (1,1),(3,2),(2,3),(4,5);
/*!40000 ALTER TABLE `classes_has_staff_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Topicshots','at turpis a pede posuere nonummy integer non velit donec'),(2,'Rhybox','vehicula condimentum curabitur in libero ut massa volutpat convallis morbi odio odio elementum eu interdum eu tincidunt in'),(3,'Voomm','purus sit amet nulla quisque arcu libero rutrum ac lobortis vel dapibus'),(4,'Jabberbean','nonummy integer non velit donec diam neque vestibulum eget vulputate ut'),(5,'Brainsphere','donec dapibus duis at velit eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit id pretium iaculis'),(6,'Avamba','nulla sed vel enim sit amet nunc viverra dapibus nulla suscipit ligula in'),(7,'Wordify','ut at dolor quis odio consequat varius integer ac leo pellentesque ultrices mattis odio donec vitae'),(8,'Buzzshare','est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin'),(9,'Centimia','ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae nulla'),(10,'Twimbo','lacinia erat vestibulum sed magna at nunc commodo placerat praesent blandit nam nulla integer'),(11,'Flipbug','interdum mauris ullamcorper purus sit amet nulla quisque arcu libero rutrum ac lobortis vel dapibus'),(12,'Youspan','pellentesque viverra pede ac diam cras pellentesque volutpat dui maecenas tristique est'),(13,'JumpXS','velit donec diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum'),(14,'Feedfire','massa volutpat convallis morbi odio odio elementum eu interdum eu tincidunt in leo maecenas pulvinar lobortis est phasellus sit'),(15,'Plambee','etiam pretium iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla'),(16,'Demivee','convallis duis consequat dui nec nisi volutpat eleifend donec ut dolor morbi vel lectus in quam fringilla rhoncus mauris enim'),(17,'Feedmix','augue vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec'),(18,'Flipstorm','lectus aliquam sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non sodales sed tincidunt eu'),(19,'Gigaclub','tristique est et tempus semper est quam pharetra magna ac consequat metus sapien'),(20,'Voomm','aliquet ultrices erat tortor sollicitudin mi sit amet lobortis sapien sapien non mi integer');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (1,'Exam 1','begin',NULL),(2,'Exam 2','middle',NULL),(3,'Exam 12','end',NULL),(4,'Exam 13','middle',NULL),(5,'Exam 4','middle',NULL),(6,'Exam 15','middle',NULL),(7,'Exam 16','middle',NULL),(8,'Exam 17','middle',NULL),(9,'Exam 18','begin',NULL),(10,'Exam 8','middle',NULL);
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `exam_result`
--

LOCK TABLES `exam_result` WRITE;
/*!40000 ALTER TABLE `exam_result` DISABLE KEYS */;
INSERT INTO `exam_result` VALUES (1,5,5,5,5,5,'5.0',1,1,'2019-09-19 00:00:00'),(2,6.5,6.5,6.5,6.5,6.5,'6.5',2,4,'2019-09-19 00:00:00');
/*!40000 ALTER TABLE `exam_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fee_payment`
--

LOCK TABLES `fee_payment` WRITE;
/*!40000 ALTER TABLE `fee_payment` DISABLE KEYS */;
INSERT INTO `fee_payment` VALUES (1,'2019-09-19 00:00:00','cash','fee of july','7',1,'0',1,2),(2,'2019-09-19 00:00:00','cash','fee of feb','2',1,'0',2,3);
/*!40000 ALTER TABLE `fee_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `result_mapping`
--

LOCK TABLES `result_mapping` WRITE;
/*!40000 ALTER TABLE `result_mapping` DISABLE KEYS */;
INSERT INTO `result_mapping` VALUES (1,'5.0',5,5,5,5,5),(2,'6.0',6,6,6,6,6);
/*!40000 ALTER TABLE `result_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'Teacher'),(3,'Assistant'),(4,'Office'),(5,'Sale'),(6,'User');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `staff_info`
--

LOCK TABLES `staff_info` WRITE;
/*!40000 ALTER TABLE `staff_info` DISABLE KEYS */;
INSERT INTO `staff_info` VALUES (1,'Linc Sorley','2019-09-05','lsorley0@unc.edu','https://www.facebook.com/jiuyen262','636-504-4234',15,'PT','collect fee','5000000'),(2,'Onofredo Katzmann','2019-11-23','okatzmann1@photobucket.com','https://www.facebook.com/jnguyen262','256-428-8091',16,'PT','own class','5000000'),(3,'Dru Ruhben','2019-04-23','druhben2@mit.edu','https://www.facebook.com/jinen262','441-256-4146',17,'FT','do whatever','5000000'),(4,'Clarey Scotti','2019-04-21','cscotti3@storify.com','https://www.facebook.com/jin261','616-703-9388',18,'FT','speaking assistant','6000000'),(5,'Sydney Paddison','2019-05-11','spaddison4@usda.gov',NULL,'293-184-0891',19,'FT','do whatever','7000000'),(6,'Orazio McCready','2019-10-17','omccready5@examiner.com',NULL,'771-534-2823',20,'FT','do anything','5000000');
/*!40000 ALTER TABLE `staff_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `student_classes`
--

LOCK TABLES `student_classes` WRITE;
/*!40000 ALTER TABLE `student_classes` DISABLE KEYS */;
INSERT INTO `student_classes` VALUES (1,2),(2,3),(3,4),(5,6),(4,7);
/*!40000 ALTER TABLE `student_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `student_info`
--

LOCK TABLES `student_info` WRITE;
/*!40000 ALTER TABLE `student_info` DISABLE KEYS */;
INSERT INTO `student_info` VALUES (1,'Adel Ardron','2019-09-19','aardron0@yelp.com','https://www.facebook.com/jinngasdasd','626-587-6560','aardron0@ycombinator.com','745-827-6559',1,NULL),(2,'Shepherd Caustic','2020-01-05','scaustic1@microsoft.com','https://www.facebook.com/jinngs6551','828-622-5804','scaustic1@wired.com','658-161-1743',2,NULL),(3,'Robena Ramey','2019-03-28','rramey2@smh.com.au','https://www.facebook.com/jinng53518518','987-836-1842','rramey2@wiley.com','801-284-7250',3,NULL),(4,'Clementine Anderbrugge','2019-05-07','canderbrugge3@phpbb.com','https://www.facebook.com/jinsds471','662-354-6689','canderbrugge3@comcast.net','521-935-1294',4,NULL),(5,'Immanuel Yanshin','2019-09-06','iyanshin4@desdev.cn','https://www.facebook.com/jinnasdasdw','894-450-8320','iyanshin4@buzzfeed.com','816-980-5555',5,NULL),(6,'Gerald Farran','2019-12-20','gfarran5@icio.us','https://www.facebook.com/jinna34252sdasdw','677-230-5540','gfarran5@mozilla.org','398-529-2938',6,NULL),(7,'Anderea Shillitto','2019-09-16','ashillitto6@ebay.co.uk','https://www.facebook.com/jinna34sdasdw','619-585-3563','ashillitto6@comsenz.com','745-207-3753',7,NULL),(8,'Charlton Striker','2019-05-30','cstriker7@gov.uk','https://www.facebook.com/jinnas34addasdw','356-804-9004','cstriker7@infoseek.co.jp','430-153-9176',8,NULL),(9,'Allan Tatem','2019-10-12','atatem8@sogou.com','https://www.facebook.com/jinnaadsdasdw','415-305-5395','atatem8@theguardian.com','369-261-7224',9,NULL),(10,'Casey Loudian','2019-06-28','cloudian9@oracle.com','https://www.facebook.com/jinnasd34asdasdw','409-892-1663','cloudian9@unicef.org','236-534-7090',10,NULL),(11,'Stephannie Ulster','2019-07-15','sulstera@unblog.fr','https://www.facebook.com/jinn43asdasdw','310-436-9517','sulstera@java.com','961-171-6326',11,NULL),(12,'Tallia Stenet','2020-02-23','tstenetb@marriott.com','https://www.facebook.com/jinnaadsdasdw','344-658-9962','tstenetb@xinhuanet.com','756-714-7323',12,NULL),(13,'Matelda McDowall','2019-09-27','mmcdowallc@ezinearticles.com','https://www.facebook.com/jinnr3rasdasdw','575-474-4115','mmcdowallc@toplist.cz','657-882-1290',13,NULL),(14,'Jaimie Kintish','2019-04-22','jkintishd@webnode.com','https://www.facebook.com/jinnasdasdasdw','517-807-7180','jkintishd@booking.com','883-537-8238',14,NULL);
/*!40000 ALTER TABLE `student_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,'jinnguyen111','eOQliyXNb',1,'2020-01-05 09:31:30','2019-09-17 10:14:02'),(2,'anhuyen111','oVZwAeT6Zag9',0,'2019-05-18 03:44:51','2019-07-02 20:01:43'),(3,'anhnguy11','AlyTNzFnq2wh',1,'2019-10-30 00:30:19','2019-04-02 10:47:35'),(4,'anyen111','dgKPdd',1,'2019-06-25 23:15:49','2020-02-18 12:40:15'),(5,'anhnen111','ElKZ0o8l',0,'2019-10-31 21:47:47','2019-03-29 13:14:13'),(6,'hnguyen111','eM9JAqucbX',1,'2020-01-24 02:37:04','2019-11-22 14:36:51'),(7,'anhnun111','oAXXgKQi3W',0,'2019-06-07 23:08:48','2019-04-08 06:59:46'),(8,'anh111','B6RObSTuPj6F',1,'2020-02-23 06:42:10','2020-01-12 17:35:43'),(9,'anyen111','FG2WB8jTU99E',1,'2019-08-30 02:33:07','2020-01-12 17:12:16'),(10,'anhnu11','xPni1qH9hXh',0,'2019-08-27 13:38:54','2019-12-22 20:29:23'),(11,'anhnguye','IvH7vCbKx',0,'2019-10-07 20:38:34','2019-05-08 20:03:17'),(12,'anhngyen111','DuBYCAaVrjnL',0,'2019-06-25 14:46:53','2019-05-22 04:07:01'),(13,'ahnguy11','Qh7VZSI',0,'2020-02-21 04:46:11','2019-07-20 10:33:29'),(14,'anhnguy11','QDgXE1xQ',0,'2019-06-27 22:25:39','2019-05-31 17:05:19'),(15,'anhn111','eGed1rXiFs',0,'2019-11-13 02:27:32','2019-09-21 11:03:45'),(16,'anhgu111','cy7Ijk9xPtL',1,'2019-06-28 18:37:37','2019-12-10 10:39:02'),(17,'anhng111','S4zyn2ChZu',0,'2020-02-22 03:49:29','2019-03-28 20:36:57'),(18,'anguyen11','ELWlSlm',1,'2019-09-23 03:23:46','2020-02-20 08:24:06'),(19,'anguyen1','8gnVGY',0,'2019-06-16 09:47:47','2020-02-18 19:09:44'),(20,'aguye11','mGRrctxW',1,'2019-06-17 19:41:28','2019-04-16 04:44:26');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_account_has_role`
--

LOCK TABLES `user_account_has_role` WRITE;
/*!40000 ALTER TABLE `user_account_has_role` DISABLE KEYS */;
INSERT INTO `user_account_has_role` VALUES (15,1),(20,1),(16,2),(20,2),(17,3),(18,4),(19,5),(1,6),(2,6),(3,6),(4,6),(5,6),(6,6),(7,6),(8,6),(9,6),(10,6),(11,6),(12,6),(13,6),(14,6);
/*!40000 ALTER TABLE `user_account_has_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-19 23:31:07
