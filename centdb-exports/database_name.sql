-- Database: database_name

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (`id` int ,
`name` varchar(255) );

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
INSERT INTO `courses` VALUES (1,'Advance Software Development Concepts'),(2,'Communicating Computer Science Ideas'),(3,'Data Management, warehousing and analytics');
UNLOCK TABLES;


--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (`id` int PRIMARY KEY ,
`name` varchar(255) ,
`email` varchar(255) );

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
INSERT INTO `student` VALUES (1,'Maan','maan@gmail.com'),(2,'Meha','meha@gmail.com'),(3,'Sumit','sumit@gmail.com'),(4,'Monil','monil@gmail.com'),(5,'Jay','jay@gmail.com');
UNLOCK TABLES;


--
-- Table structure for table `student_registration`
--

DROP TABLE IF EXISTS `student_registration`;
CREATE TABLE `student_registration` (`registration_id` int PRIMARY KEY ,
`student_id` int FOREIGN KEY ,
`course_id` int FOREIGN KEY );

--
-- Dumping data for table `student_registration`
--

LOCK TABLES `student_registration` WRITE;
INSERT INTO `student_registration` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,1),(5,2,2),(6,2,3);
UNLOCK TABLES;

-- Dump completed on 2021-12-06 16:57:43