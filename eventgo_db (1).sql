-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 18, 2025 at 02:16 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eventgo_db`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `attendance_stats`
-- (See below for the actual view)
--
CREATE TABLE `attendance_stats` (
`event_id` int(11)
,`title` varchar(255)
,`total_registrations` bigint(21)
,`attended` bigint(21)
,`attendance_rate` decimal(26,2)
);

-- --------------------------------------------------------

--
-- Table structure for table `checkin`
--

CREATE TABLE `checkin` (
  `checkin_id` int(11) NOT NULL,
  `registration_id` int(11) NOT NULL,
  `checkin_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `checkin`
--

INSERT INTO `checkin` (`checkin_id`, `registration_id`, `checkin_time`) VALUES
(3, 6, '2025-07-18 19:41:43');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `event_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `start_datetime` datetime NOT NULL,
  `end_datetime` datetime NOT NULL,
  `capacity` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `qr_token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`event_id`, `title`, `description`, `location`, `start_datetime`, `end_datetime`, `capacity`, `created_at`, `user_id`, `qr_token`) VALUES
(3, 'Testing', 'avdsfdvsdvsdv', 'FTMK', '2025-07-19 00:00:00', '2025-07-20 00:00:00', 100, '2025-07-17 21:52:17', '1', '6ac8f104-d472-49f9-81c9-d1ae0460aba4'),
(4, 'Tech Talk 2025', 'Discussion on emerging tech trends', 'Auditorium A', '2025-08-01 09:00:00', '2025-08-01 12:00:00', 150, '2025-07-18 16:43:37', '1', 'qr-001-tech'),
(5, 'Career Fair', 'Meet companies and apply for jobs', 'Main Hall', '2025-08-10 10:00:00', '2025-08-10 16:00:00', 300, '2025-07-18 16:43:37', '1', 'qr-002-career'),
(6, 'AI Workshop', 'Hands-on AI and ML training session', 'Lab 3', '2025-08-15 14:00:00', '2025-08-15 17:00:00', 50, '2025-07-18 16:43:37', '1', 'qr-003-ai'),
(7, 'Entrepreneurship Bootcamp', 'Learn how to start a business', 'Room B2', '2025-08-20 09:00:00', '2025-08-20 17:00:00', 80, '2025-07-18 16:43:37', '1', 'qr-004-bootcamp'),
(8, 'Sustainability Seminar', 'Talks on environmental sustainability', 'Conference Room', '2025-08-25 13:00:00', '2025-08-25 15:00:00', 100, '2025-07-18 16:43:37', '1', 'qr-005-green'),
(10, 'project zuo bu wan', 'dad project zuo bu wan, jin tian yao jiao liao. you mei you da shen   ke yi bao da tui', 'Online', '2025-07-19 00:00:00', '2025-07-19 23:00:00', 2, '2025-07-18 18:11:42', '1', 'f92d9e36-7236-430f-acdf-002c9000afd5'),
(11, 'dddd', 'ddddd', 'ddd', '2025-07-18 19:30:00', '2025-07-19 00:00:00', 111, '2025-07-18 19:07:02', '1', '47817659-0141-4bde-901c-ae31bb0cfaef'),
(12, 'Hello', 'Greeting to everyone', 'FTMK', '2025-07-18 19:30:00', '2025-07-19 00:00:00', 11, '2025-07-18 19:25:50', '1', '220c3a60-5bd1-4904-b541-a854854cc2ff');

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `registration_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `status` varchar(20) NOT NULL,
  `registration_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`registration_id`, `event_id`, `user_id`, `status`, `registration_time`) VALUES
(5, 10, 'B032310386', 'pending', '2025-07-18 18:16:59'),
(6, 3, 'B032310386', 'Approved', '2025-07-18 18:45:26'),
(7, 4, 'B032310386', 'pending', '2025-07-18 19:09:59'),
(8, 7, 'B032310386', 'pending', '2025-07-18 19:44:02');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `name`, `role`, `created_at`) VALUES
('1', 'admin.gmail.com', 'Admin User', 'admin', '2025-07-17 21:51:56'),
('B032310335', 'B032310335@studen.utem.edu.my', 'chaijianjie', 'student', '2025-07-18 18:15:19'),
('B032310386', 'zhongyap154@gmail.com', 'YAP', 'student', '2025-07-17 23:14:47'),
('B032310387', 'wjx@gmail.com', 'wjx', 'student', '2025-07-18 19:31:28');

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_available_events`
-- (See below for the actual view)
--
CREATE TABLE `view_available_events` (
`event_id` int(11)
,`title` varchar(255)
,`description` varchar(255)
,`start_date_time` datetime
,`end_date_time` datetime
,`location` varchar(255)
,`approved_registrations` bigint(21)
,`capacity` int(11)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_registered_events`
-- (See below for the actual view)
--
CREATE TABLE `view_registered_events` (
`registration_id` int(11)
,`user_id` varchar(255)
,`event_title` varchar(255)
,`registration_time` datetime
,`registration_status` varchar(20)
,`checkin_time` datetime
);

-- --------------------------------------------------------

--
-- Structure for view `attendance_stats`
--
DROP TABLE IF EXISTS `attendance_stats`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `attendance_stats`  AS SELECT `e`.`event_id` AS `event_id`, `e`.`title` AS `title`, count(distinct `r`.`registration_id`) AS `total_registrations`, count(distinct `c`.`checkin_id`) AS `attended`, coalesce(round(count(distinct `c`.`checkin_id`) / nullif(count(distinct `r`.`registration_id`),0) * 100,2),0) AS `attendance_rate` FROM ((`event` `e` left join `registration` `r` on(`e`.`event_id` = `r`.`event_id` and `r`.`status` = 'approved')) left join `checkin` `c` on(`r`.`registration_id` = `c`.`registration_id`)) GROUP BY `e`.`event_id`, `e`.`title` ;

-- --------------------------------------------------------

--
-- Structure for view `view_available_events`
--
DROP TABLE IF EXISTS `view_available_events`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_available_events`  AS SELECT `e`.`event_id` AS `event_id`, `e`.`title` AS `title`, `e`.`description` AS `description`, `e`.`start_datetime` AS `start_date_time`, `e`.`end_datetime` AS `end_date_time`, `e`.`location` AS `location`, count(`r`.`registration_id`) AS `approved_registrations`, `e`.`capacity` AS `capacity` FROM (`event` `e` left join `registration` `r` on(`r`.`event_id` = `e`.`event_id` and `r`.`status` = 'approved')) WHERE `e`.`start_datetime` > current_timestamp() GROUP BY `e`.`event_id`, `e`.`title`, `e`.`description`, `e`.`start_datetime`, `e`.`end_datetime`, `e`.`location`, `e`.`capacity` HAVING count(`r`.`registration_id`) < `e`.`capacity` ;

-- --------------------------------------------------------

--
-- Structure for view `view_registered_events`
--
DROP TABLE IF EXISTS `view_registered_events`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_registered_events`  AS SELECT `r`.`registration_id` AS `registration_id`, `r`.`user_id` AS `user_id`, `e`.`title` AS `event_title`, `r`.`registration_time` AS `registration_time`, `r`.`status` AS `registration_status`, `c`.`checkin_time` AS `checkin_time` FROM ((`registration` `r` join `event` `e` on(`r`.`event_id` = `e`.`event_id`)) left join `checkin` `c` on(`c`.`registration_id` = `r`.`registration_id`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `checkin`
--
ALTER TABLE `checkin`
  ADD PRIMARY KEY (`checkin_id`),
  ADD KEY `registration_id` (`registration_id`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`registration_id`),
  ADD KEY `event_id` (`event_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `checkin`
--
ALTER TABLE `checkin`
  MODIFY `checkin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `registration_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `checkin`
--
ALTER TABLE `checkin`
  ADD CONSTRAINT `checkin_ibfk_1` FOREIGN KEY (`registration_id`) REFERENCES `registration` (`registration_id`);

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `registration`
--
ALTER TABLE `registration`
  ADD CONSTRAINT `registration_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`),
  ADD CONSTRAINT `registration_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
