SET SQL_MODE = `NO_AUTO_VALUE_ON_ZERO`;
START TRANSACTION;
SET time_zone = `+00:00`;

CREATE DATABASE IF NOT EXISTS `flexfit` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `flexfit`;

CREATE TABLE IF NOT EXISTS `clinic` (
	`vat_number` INTEGER NOT NULL,
	`name`	VARCHAR(64) NOT NULL,
	`address`	VARCHAR(64) NOT NULL,
	`phone_number`	VARCHAR(50) NOT NULL,
	`email`	VARCHAR(64) NOT NULL,
	PRIMARY KEY(`vat_number`)
);
CREATE TABLE IF NOT EXISTS `doctor` (
	`vat_reg_num`	INTEGER NOT NULL,
	`name`	VARCHAR(64) NOT NULL,
	`surname`	VARCHAR(64) NOT NULL,
	`email`	VARCHAR(64) NOT NULL,
	`clinic_vat_number`	INTEGER NOT NULL,
	FOREIGN KEY(`clinic_vat_number`) REFERENCES `clinic`(`vat_number`),
	PRIMARY KEY(`vat_reg_num`)
);
CREATE TABLE IF NOT EXISTS `service` (
	`code`	VARCHAR(64),
	`name`	VARCHAR(64) NOT NULL,
	`description`	VARCHAR(128) NOT NULL,
	`price`	FLOAT(2) NOT NULL,
	`clinic_vat_number`	INTEGER NOT NULL,
	FOREIGN KEY(`clinic_vat_number`) REFERENCES `clinic`(`vat_number`),
	PRIMARY KEY(`code`)
);
CREATE TABLE IF NOT EXISTS `user` (
	`username`	VARCHAR(32) NOT NULL,
	`name`	VARCHAR(16) NOT NULL,
	`email`	VARCHAR(32) NOT NULL,
	`password`	VARCHAR(16) NOT NULL,
	PRIMARY KEY(`username`)
);
CREATE TABLE IF NOT EXISTS `patient` (
	`ssn`	varchar(50) NOT NULL,
	`email`	varchar(50) NOT NULL,
	`name`	varchar(50) NOT NULL,
	`phone_number`	varchar(50) NOT NULL,
	`vat_reg_num` INTEGER NOT NULL,
	FOREIGN KEY(`vat_reg_num`) REFERENCES `doctor`(`vat_reg_num`),
	PRIMARY KEY(`ssn`)
);
CREATE TABLE IF NOT EXISTS `history` (
	`id`	INTEGER AUTO_INCREMENT,
	`patient_ssn`	varchar(50) NOT NULL,
	`tos`	varchar(50) NOT NULL,
	`time`	varchar(50) NOT NULL,
	`date`	DATE NOT NULL,
	`description` LONGTEXT,
	FOREIGN KEY(`patient_ssn`) REFERENCES `patient`(`ssn`),
	PRIMARY KEY(`id`)
);
CREATE TABLE IF NOT EXISTS `appointment` (
	`id`	INTEGER AUTO_INCREMENT,
	`time`	varchar(50) NOT NULL,
	`date`	DATE NOT NULL,
	`tos`	varchar(50) NOT NULL,
	`clinic_vat_number`	INTEGER NOT NULL,
	`patient_ssn` varchar(50) NOT NULL,
	`accepted`	varchar(50) NOT NULL,
	FOREIGN KEY(`clinic_vat_number`) REFERENCES `clinic`(`vat_number`),
	FOREIGN KEY(`patient_ssn`) REFERENCES `patient`(`ssn`),
	PRIMARY KEY(`id`)
);
CREATE TABLE IF NOT EXISTS `economic_movements` (
	`id`	INTEGER AUTO_INCREMENT,
	`patient_ssn`	varchar(50) NOT NULL,
	`date`	DATE NOT NULL,
	`tos`	varchar(50) NOT NULL,
	`cost`	Float NOT NULL,
	FOREIGN KEY(`patient_ssn`) REFERENCES `patient`(`ssn`),
	PRIMARY KEY(`id`)
);

INSERT INTO `clinic` (`vat_number`, `name`, `address`, `phone_number`, `email`) VALUES (10000, 'ABC Physio Clinic', '123 Main Street', '555-1234', 'abcphysio@clinic.com'),
(10001, 'XYZ Physio Center', '456 Oak Avenue', '555-5678', 'xyzphysio@clinic.com'),
(10002, 'City Physio Group', '789 Elm Street', '555-9012', 'cityphysio@clinic.com'),
(10003, 'Elite Physiotherapy', '555 Maple Drive', '555-3456', 'elitephysio@clinic.com'),
(10004, 'Healthy Living Physio', '888 Pine Road', '555-7890', 'healthylivingphysio@clinic.com'),
(10005, 'Revive Physiotherapy', '777 Oak Street', '555-2345', 'revivephysio@clinic.com'),
(10006, 'Complete Physio Solutions', '111 Maple Drive', '555-6789', 'completephysiosolutions@clinic.com'),
(10007, 'Peak Physiotherapy', '222 Pine Road', '555-0123', 'peakphysio@clinic.com'),
(10008, 'Advanced Physio Care', '333 Elm Street', '555-4567', 'advancedphysiocare@clinic.com'),
(10009, 'Progressive Physio Center', '444 Main Street', '555-8901', 'progressivephysio@clinic.com');

INSERT INTO `doctor` (`vat_reg_num`, `name`, `surname`, `email`, `clinic_vat_number`) VALUES (1000, 'John', 'Doe', 'johndoe@doctor.com', 10000),
(1001, 'Jane', 'Smith', 'janesmith@doctor.com', 10001),
(1002, 'Michael', 'Johnson', 'michaeljohnson@doctor.com', 10002),
(1003, 'Emily', 'Davis', 'emilydavis@doctor.com', 10003),
(1004, 'David', 'Brown', 'davidbrown@doctor.com', 10004),
(1005, 'Olivia', 'Garcia', 'oliviagarcia@doctor.com', 10005),
(1006, 'Robert', 'Lee', 'robertlee@doctor.com', 10006),
(1007, 'Sophia', 'Taylor', 'sophiataylor@doctor.com', 10007),
(1008, 'William', 'Anderson', 'williamanderson@doctor.com', 10008),
(1009, 'Isabella', 'Thomas', 'isabellathomas@doctor.com', 10009);

INSERT INTO `service` (`code`, `name`, `description`, `price`, `clinic_vat_number`) VALUES ('S1', 'Massage Therapy', 'Relaxation and Pain Relief', 60.00, 10000),
('S2', 'Physical Therapy', 'Muscle and Joint Rehab', 80.00, 10001),
('S3', 'Acupuncture', 'Pain Management', 70.00, 10002),
('S4', 'Chiropractic Adjustment', 'Spinal Alignment', 90.00, 10003),
('S5', 'Sports Medicine', 'Injury Treatment and Prevention', 100.00, 10004),
('S6', 'Occupational Therapy', 'Functional Rehab', 75.00, 10005),
('S7', 'Orthopedic Surgery', 'Joint Replacement and Repair', 200.00, 10006),
('S8', 'Neurological Rehabilitation', 'Brain and Nerve Injury Rehab', 120.00, 10007),
('S9', 'Cardiopulmonary Rehabilitation', 'Heart and Lung Disease Rehab', 110.00, 10008),
('S10', 'Pediatric Therapy', 'Child Development and Rehab', 85.00, 10009),
('S11', 'Mental Health Counseling', 'Counseling and Therapy Services', 65.00, 10000),
('S12', 'Rehabilitation Counseling', 'Mental and Physical Health Recovery', 70.00, 10001),
('S13', 'Speech Therapy', 'Communication and Language Disorders', 80.00, 10002),
('S14', 'Dental Services', 'Oral Health Care and Treatment', 100.00, 10003),
('S15', 'Dermatology', 'Skin Care and Treatment', 120.00, 10004),
('S16', 'Ophthalmology', 'Eye Care and Treatment', 90.00, 10005),
('S17', 'Gynecology', 'Women Health and Wellness', 110.00, 10006),
('S18', 'Urology', 'Urinary Tract and Reproductive Health', 95.00, 10007),
('S19', 'Endocrinology', 'Hormonal Disorders and Treatment', 130.00, 10008),
('S20', 'Radiology', 'Diagnostic Imaging and Treatment', 150.00, 10009);

INSERT INTO `user` (`username`,`name`,`email`,`password`) VALUES ('user1','John Doe','johndoe@doctor.com','pass123'),
 ('user2','Jane Doe','janedoe@patient.com','pass456'),
 ('user3','Andrea','andreas@psf.com','pass789'),
 ('user4','Helen','eleni@psf.com','pass101112');

INSERT INTO `patient` (`ssn`, `email`, `name`, `phone_number`, `vat_reg_num`) VALUES ('111111111', 'johndoeJR@patient.com', 'John Doe Jr.', '555-4321', 1000),
('222222222', 'janedoe@patient.com', 'Jane Doe', '555-8765', 1000),
('333333333', 'robertsmith@patient.com', 'Robert Smith', '555-1357', 1000),
('444444444', 'alicejohnson@patient.com', 'Alice Johnson', '555-8642', 1001),
('555555555', 'michaelbrown@patient.com', 'Michael Brown', '555-9753', 1001),
('666666666', 'sarahjones@patient.com', 'Sarah Jones', '555-7530', 1001),
('777777777', 'davidlee@patient.com', 'David Lee', '555-9134', 1002),
('888888888', 'emilywang@patient.com', 'Emily Wang', '555-2468', 1002),
('999999999', 'andrewkim@patient.com', 'Andrew Kim', '555-3698', 1002),
('000000001', 'amandawong@patient.com', 'Amanda Wong', '555-5678', 1003),
('111111112', 'johndoe@patient.com', 'John Doe', '555-1234', 1003),
('222222223', 'janedoe23@patient.com', 'Jane Doe', '555-4321', 1003),
('333333334', 'bobsmith@patient.com', 'Bob Smith', '555-9753', 1004),
('444444445', 'alicesmith@patient.com', 'Alice Smith', '555-8642', 1004),
('555555556', 'michaelbrown3@patient.com', 'Michael Brown', '555-1357', 1004),
('666666667', 'sarahjones3@patient.com', 'Sarah Jones', '555-7530', 1005),
('777777778', 'davidlee3@patient.com', 'David Lee', '555-2468', 1005),
('888888889', 'emilywang3@patient.com', 'Emily Wang', '555-3698', 1005),
('999999990', 'andrewkim3@patient.com', 'Andrew Kim', '555-5678', 1006),
('000000002', 'amandawong3@patient.com', 'Amanda Wong', '555-1234', 1006),
('111111113', 'johndoe4@patient.com', 'John Doe', '555-4321', 1006),
('222222224', 'janedoe4@patient.com', 'Jane Doe', '555-9753', 1007),
('333333335', 'robertsmithJR@patient.com', 'Robert Smith Jr.', '555-2468', 1007),
('444444446', 'alicesmith4@patient.com', 'Alice Smith', '555-8642', 1007),
('901234568', 'samlane@patient.com', 'Sam Lane', '555-2468', 1008),
('901234569', 'karensmith@patient.com', 'Karen Smith', '555-3698', 1008),
('901234570', 'jasonpark@patient.com', 'Jason Park', '555-1357', 1008),
('012345689', 'matthewtaylor@patient.com', 'Matthew Taylor', '555-8642', 1009),
('012345690', 'staceykim@patient.com', 'Stacey Kim', '555-9753', 1009),
('012345691', 'harrychen@patient.com', 'Harry Chen', '555-7530', 1009);

INSERT INTO `history` (`patient_ssn`,`tos`,`time`,`date`, `description`) VALUES 
('111111111', 'Checkup', '10:00 AM', '2022-01-01', 'In the session held on January 1, 2022, at 10:00 AM, I, as the physiotherapist, conducted an evaluation to assess the progress of our therapy with my patient.

During this session, our primary focus was to track and measure the advancements made since the beginning of the therapy. We began by discussing the specific goals and objectives we had set together to ensure we were on the right track.

To accurately evaluate the progress, I utilized various assessment techniques and tools. These included observing the patient\'s range of motion, assessing their strength and flexibility, and conducting functional tests related to their specific condition.

We engaged in a collaborative dialogue, allowing the patient to express their experiences, any improvements or challenges encountered, and how the therapy has been impacting their daily life. This provided valuable insights into the effectiveness of the treatment plan and allowed me to make any necessary adjustments to optimize their progress.

Throughout the session, I provided encouragement, guidance, and support, emphasizing the importance of consistent adherence to the therapy program. I also addressed any concerns or questions the patient had, ensuring they felt heard and understood.

Based on the evaluation results, I discussed the patient\'s achievements and areas that required further attention. Together, we identified specific areas for continued focus and developed a plan to advance their treatment.

Additionally, I provided the patient with recommendations for exercises, self-care techniques, and lifestyle modifications that would complement their ongoing therapy and facilitate their recovery. These recommendations were tailored to their individual needs and aimed at empowering them to take an active role in their healing process.

As we concluded the session, I expressed my appreciation for the patient\'s commitment and effort in their therapy journey. I reassured them that I would continue to monitor their progress closely and adjust the treatment plan as needed to ensure their continued improvement.

The session served as a valuable opportunity to assess the patient\'s progress, address any concerns, and refine the therapy approach. Through ongoing collaboration and dedication, we aim to achieve optimal results and enhance the patient\'s overall well-being.'),
('111111111', 'Follow-up', '17:00 PM', '2022-01-15', 'In the session held on January 15, 2022, at 17:00 PM, I, as the physiotherapist, conducted an evaluation to assess the progress of our therapy with my patient.

During this session, our primary focus was to track and measure the advancements made since the beginning of the therapy. We began by discussing the specific goals and objectives we had set together to ensure we were on the right track.

To accurately evaluate the progress, I utilized various assessment techniques and tools. These included observing the patient\'s range of motion, assessing their strength and flexibility, and conducting functional tests related to their specific condition.

We engaged in a collaborative dialogue, allowing the patient to express their experiences, any improvements or challenges encountered, and how the therapy has been impacting their daily life. This provided valuable insights into the effectiveness of the treatment plan and allowed me to make any necessary adjustments to optimize their progress.

Throughout the session, I provided encouragement, guidance, and support, emphasizing the importance of consistent adherence to the therapy program. I also addressed any concerns or questions the patient had, ensuring they felt heard and understood.

Based on the evaluation results, I discussed the patient\'s achievements and areas that required further attention. Together, we identified specific areas for continued focus and developed a plan to advance their treatment.

Additionally, I provided the patient with recommendations for exercises, self-care techniques, and lifestyle modifications that would complement their ongoing therapy and facilitate their recovery. These recommendations were tailored to their individual needs and aimed at empowering them to take an active role in their healing process.

As we concluded the session, I expressed my appreciation for the patient\'s commitment and effort in their therapy journey. I reassured them that I would continue to monitor their progress closely and adjust the treatment plan as needed to ensure their continued improvement.

The session served as a valuable opportunity to assess the patient\'s progress, address any concerns, and refine the therapy approach. Through ongoing collaboration and dedication, we aim to achieve optimal results and enhance the patient\'s overall well-being.'),
('111111111', 'Treatment', '13:00 PM', '2022-02-01', 'In the session held on February 1, 2022, at 13:00 PM, I, as the physiotherapist, conducted an evaluation to assess the progress of our therapy with my patient.

During this session, our primary focus was to track and measure the advancements made since the beginning of the therapy. We began by discussing the specific goals and objectives we had set together to ensure we were on the right track.

To accurately evaluate the progress, I utilized various assessment techniques and tools. These included observing the patient\'s range of motion, assessing their strength and flexibility, and conducting functional tests related to their specific condition.

We engaged in a collaborative dialogue, allowing the patient to express their experiences, any improvements or challenges encountered, and how the therapy has been impacting their daily life. This provided valuable insights into the effectiveness of the treatment plan and allowed me to make any necessary adjustments to optimize their progress.

Throughout the session, I provided encouragement, guidance, and support, emphasizing the importance of consistent adherence to the therapy program. I also addressed any concerns or questions the patient had, ensuring they felt heard and understood.

Based on the evaluation results, I discussed the patient\'s achievements and areas that required further attention. Together, we identified specific areas for continued focus and developed a plan to advance their treatment.

Additionally, I provided the patient with recommendations for exercises, self-care techniques, and lifestyle modifications that would complement their ongoing therapy and facilitate their recovery. These recommendations were tailored to their individual needs and aimed at empowering them to take an active role in their healing process.

As we concluded the session, I expressed my appreciation for the patient\'s commitment and effort in their therapy journey. I reassured them that I would continue to monitor their progress closely and adjust the treatment plan as needed to ensure their continued improvement.

The session served as a valuable opportunity to assess the patient\'s progress, address any concerns, and refine the therapy approach. Through ongoing collaboration and dedication, we aim to achieve optimal results and enhance the patient\'s overall well-being.'),
('222222222', 'Checkup', '11:00 AM', '2022-01-01', 'In the session held on January 1, 2022, at 11:00 AM, I, as the physiotherapist, conducted an evaluation to assess the progress of our therapy with my patient.

During this session, our primary focus was to track and measure the advancements made since the beginning of the therapy. We began by discussing the specific goals and objectives we had set together to ensure we were on the right track.

To accurately evaluate the progress, I utilized various assessment techniques and tools. These included observing the patient\'s range of motion, assessing their strength and flexibility, and conducting functional tests related to their specific condition.

We engaged in a collaborative dialogue, allowing the patient to express their experiences, any improvements or challenges encountered, and how the therapy has been impacting their daily life. This provided valuable insights into the effectiveness of the treatment plan and allowed me to make any necessary adjustments to optimize their progress.

Throughout the session, I provided encouragement, guidance, and support, emphasizing the importance of consistent adherence to the therapy program. I also addressed any concerns or questions the patient had, ensuring they felt heard and understood.

Based on the evaluation results, I discussed the patient\'s achievements and areas that required further attention. Together, we identified specific areas for continued focus and developed a plan to advance their treatment.

Additionally, I provided the patient with recommendations for exercises, self-care techniques, and lifestyle modifications that would complement their ongoing therapy and facilitate their recovery. These recommendations were tailored to their individual needs and aimed at empowering them to take an active role in their healing process.

As we concluded the session, I expressed my appreciation for the patient\'s commitment and effort in their therapy journey. I reassured them that I would continue to monitor their progress closely and adjust the treatment plan as needed to ensure their continued improvement.

The session served as a valuable opportunity to assess the patient\'s progress, address any concerns, and refine the therapy approach. Through ongoing collaboration and dedication, we aim to achieve optimal results and enhance the patient\'s overall well-being.'),
('222222222', 'Follow-up', '16:00 PM', '2022-01-15', 'In the session held on January 15, 2022, at 16:00 PM, I, as the physiotherapist, conducted an evaluation to assess the progress of our therapy with my patient.

During this session, our primary focus was to track and measure the advancements made since the beginning of the therapy. We began by discussing the specific goals and objectives we had set together to ensure we were on the right track.

To accurately evaluate the progress, I utilized various assessment techniques and tools. These included observing the patient\'s range of motion, assessing their strength and flexibility, and conducting functional tests related to their specific condition.

We engaged in a collaborative dialogue, allowing the patient to express their experiences, any improvements or challenges encountered, and how the therapy has been impacting their daily life. This provided valuable insights into the effectiveness of the treatment plan and allowed me to make any necessary adjustments to optimize their progress.

Throughout the session, I provided encouragement, guidance, and support, emphasizing the importance of consistent adherence to the therapy program. I also addressed any concerns or questions the patient had, ensuring they felt heard and understood.

Based on the evaluation results, I discussed the patient\'s achievements and areas that required further attention. Together, we identified specific areas for continued focus and developed a plan to advance their treatment.

Additionally, I provided the patient with recommendations for exercises, self-care techniques, and lifestyle modifications that would complement their ongoing therapy and facilitate their recovery. These recommendations were tailored to their individual needs and aimed at empowering them to take an active role in their healing process.

As we concluded the session, I expressed my appreciation for the patient\'s commitment and effort in their therapy journey. I reassured them that I would continue to monitor their progress closely and adjust the treatment plan as needed to ensure their continued improvement.

The session served as a valuable opportunity to assess the patient\'s progress, address any concerns, and refine the therapy approach. Through ongoing collaboration and dedication, we aim to achieve optimal results and enhance the patient\'s overall well-being.'),
('222222222', 'Treatment', '14:00 PM', '2022-02-01', 'In the session held on Februayry 1, 2022, at 14:00 PM, I, as the physiotherapist, conducted an evaluation to assess the progress of our therapy with my patient.

During this session, our primary focus was to track and measure the advancements made since the beginning of the therapy. We began by discussing the specific goals and objectives we had set together to ensure we were on the right track.

To accurately evaluate the progress, I utilized various assessment techniques and tools. These included observing the patient\'s range of motion, assessing their strength and flexibility, and conducting functional tests related to their specific condition.

We engaged in a collaborative dialogue, allowing the patient to express their experiences, any improvements or challenges encountered, and how the therapy has been impacting their daily life. This provided valuable insights into the effectiveness of the treatment plan and allowed me to make any necessary adjustments to optimize their progress.

Throughout the session, I provided encouragement, guidance, and support, emphasizing the importance of consistent adherence to the therapy program. I also addressed any concerns or questions the patient had, ensuring they felt heard and understood.

Based on the evaluation results, I discussed the patient\'s achievements and areas that required further attention. Together, we identified specific areas for continued focus and developed a plan to advance their treatment.

Additionally, I provided the patient with recommendations for exercises, self-care techniques, and lifestyle modifications that would complement their ongoing therapy and facilitate their recovery. These recommendations were tailored to their individual needs and aimed at empowering them to take an active role in their healing process.

As we concluded the session, I expressed my appreciation for the patient\'s commitment and effort in their therapy journey. I reassured them that I would continue to monitor their progress closely and adjust the treatment plan as needed to ensure their continued improvement.

The session served as a valuable opportunity to assess the patient\'s progress, address any concerns, and refine the therapy approach. Through ongoing collaboration and dedication, we aim to achieve optimal results and enhance the patient\'s overall well-being.'),
('333333333', 'Checkup', '12:00 PM', '2022-01-01', 'In the session held on January 1, 2022, at 12:00 PM, I, as the physiotherapist, conducted an evaluation to assess the progress of our therapy with my patient.

During this session, our primary focus was to track and measure the advancements made since the beginning of the therapy. We began by discussing the specific goals and objectives we had set together to ensure we were on the right track.

To accurately evaluate the progress, I utilized various assessment techniques and tools. These included observing the patient\'s range of motion, assessing their strength and flexibility, and conducting functional tests related to their specific condition.

We engaged in a collaborative dialogue, allowing the patient to express their experiences, any improvements or challenges encountered, and how the therapy has been impacting their daily life. This provided valuable insights into the effectiveness of the treatment plan and allowed me to make any necessary adjustments to optimize their progress.

Throughout the session, I provided encouragement, guidance, and support, emphasizing the importance of consistent adherence to the therapy program. I also addressed any concerns or questions the patient had, ensuring they felt heard and understood.

Based on the evaluation results, I discussed the patient\'s achievements and areas that required further attention. Together, we identified specific areas for continued focus and developed a plan to advance their treatment.

Additionally, I provided the patient with recommendations for exercises, self-care techniques, and lifestyle modifications that would complement their ongoing therapy and facilitate their recovery. These recommendations were tailored to their individual needs and aimed at empowering them to take an active role in their healing process.

As we concluded the session, I expressed my appreciation for the patient\'s commitment and effort in their therapy journey. I reassured them that I would continue to monitor their progress closely and adjust the treatment plan as needed to ensure their continued improvement.

The session served as a valuable opportunity to assess the patient\'s progress, address any concerns, and refine the therapy approach. Through ongoing collaboration and dedication, we aim to achieve optimal results and enhance the patient\'s overall well-being.'),
('333333333', 'Follow-up', '15:00 PM', '2022-01-15', 'In the session held on January 15, 2022, at 15:00 PM, I, as the physiotherapist, conducted an evaluation to assess the progress of our therapy with my patient.

During this session, our primary focus was to track and measure the advancements made since the beginning of the therapy. We began by discussing the specific goals and objectives we had set together to ensure we were on the right track.

To accurately evaluate the progress, I utilized various assessment techniques and tools. These included observing the patient\'s range of motion, assessing their strength and flexibility, and conducting functional tests related to their specific condition.

We engaged in a collaborative dialogue, allowing the patient to express their experiences, any improvements or challenges encountered, and how the therapy has been impacting their daily life. This provided valuable insights into the effectiveness of the treatment plan and allowed me to make any necessary adjustments to optimize their progress.

Throughout the session, I provided encouragement, guidance, and support, emphasizing the importance of consistent adherence to the therapy program. I also addressed any concerns or questions the patient had, ensuring they felt heard and understood.

Based on the evaluation results, I discussed the patient\'s achievements and areas that required further attention. Together, we identified specific areas for continued focus and developed a plan to advance their treatment.

Additionally, I provided the patient with recommendations for exercises, self-care techniques, and lifestyle modifications that would complement their ongoing therapy and facilitate their recovery. These recommendations were tailored to their individual needs and aimed at empowering them to take an active role in their healing process.

As we concluded the session, I expressed my appreciation for the patient\'s commitment and effort in their therapy journey. I reassured them that I would continue to monitor their progress closely and adjust the treatment plan as needed to ensure their continued improvement.

The session served as a valuable opportunity to assess the patient\'s progress, address any concerns, and refine the therapy approach. Through ongoing collaboration and dedication, we aim to achieve optimal results and enhance the patient\'s overall well-being.'),
('333333333', 'Treatment', '15:00 PM', '2022-02-01', 'In the session held on February 1, 2022, at 15:00 PM, I, as the physiotherapist, conducted an evaluation to assess the progress of our therapy with my patient.

During this session, our primary focus was to track and measure the advancements made since the beginning of the therapy. We began by discussing the specific goals and objectives we had set together to ensure we were on the right track.

To accurately evaluate the progress, I utilized various assessment techniques and tools. These included observing the patient\'s range of motion, assessing their strength and flexibility, and conducting functional tests related to their specific condition.

We engaged in a collaborative dialogue, allowing the patient to express their experiences, any improvements or challenges encountered, and how the therapy has been impacting their daily life. This provided valuable insights into the effectiveness of the treatment plan and allowed me to make any necessary adjustments to optimize their progress.

Throughout the session, I provided encouragement, guidance, and support, emphasizing the importance of consistent adherence to the therapy program. I also addressed any concerns or questions the patient had, ensuring they felt heard and understood.

Based on the evaluation results, I discussed the patient\'s achievements and areas that required further attention. Together, we identified specific areas for continued focus and developed a plan to advance their treatment.

Additionally, I provided the patient with recommendations for exercises, self-care techniques, and lifestyle modifications that would complement their ongoing therapy and facilitate their recovery. These recommendations were tailored to their individual needs and aimed at empowering them to take an active role in their healing process.

As we concluded the session, I expressed my appreciation for the patient\'s commitment and effort in their therapy journey. I reassured them that I would continue to monitor their progress closely and adjust the treatment plan as needed to ensure their continued improvement.

The session served as a valuable opportunity to assess the patient\'s progress, address any concerns, and refine the therapy approach. Through ongoing collaboration and dedication, we aim to achieve optimal results and enhance the patient\'s overall well-being.'),
('444444444', 'Checkup', '13:00 PM', '2022-01-01', 'In the session held on January 1, 2022, at 13:00 PM, I, as the physiotherapist, conducted an evaluation to assess the progress of our therapy with my patient.

During this session, our primary focus was to track and measure the advancements made since the beginning of the therapy. We began by discussing the specific goals and objectives we had set together to ensure we were on the right track.

To accurately evaluate the progress, I utilized various assessment techniques and tools. These included observing the patient\'s range of motion, assessing their strength and flexibility, and conducting functional tests related to their specific condition.

We engaged in a collaborative dialogue, allowing the patient to express their experiences, any improvements or challenges encountered, and how the therapy has been impacting their daily life. This provided valuable insights into the effectiveness of the treatment plan and allowed me to make any necessary adjustments to optimize their progress.

Throughout the session, I provided encouragement, guidance, and support, emphasizing the importance of consistent adherence to the therapy program. I also addressed any concerns or questions the patient had, ensuring they felt heard and understood.

Based on the evaluation results, I discussed the patient\'s achievements and areas that required further attention. Together, we identified specific areas for continued focus and developed a plan to advance their treatment.

Additionally, I provided the patient with recommendations for exercises, self-care techniques, and lifestyle modifications that would complement their ongoing therapy and facilitate their recovery. These recommendations were tailored to their individual needs and aimed at empowering them to take an active role in their healing process.

As we concluded the session, I expressed my appreciation for the patient\'s commitment and effort in their therapy journey. I reassured them that I would continue to monitor their progress closely and adjust the treatment plan as needed to ensure their continued improvement.

The session served as a valuable opportunity to assess the patient\'s progress, address any concerns, and refine the therapy approach. Through ongoing collaboration and dedication, we aim to achieve optimal results and enhance the patient\'s overall well-being.'),
('444444444', 'Follow-up', '14:00 PM', '2022-01-15', 'In the session held on January 15, 2022, at 14:00 PM, I, as the physiotherapist, conducted an evaluation to assess the progress of our therapy with my patient.

During this session, our primary focus was to track and measure the advancements made since the beginning of the therapy. We began by discussing the specific goals and objectives we had set together to ensure we were on the right track.

To accurately evaluate the progress, I utilized various assessment techniques and tools. These included observing the patient\'s range of motion, assessing their strength and flexibility, and conducting functional tests related to their specific condition.

We engaged in a collaborative dialogue, allowing the patient to express their experiences, any improvements or challenges encountered, and how the therapy has been impacting their daily life. This provided valuable insights into the effectiveness of the treatment plan and allowed me to make any necessary adjustments to optimize their progress.

Throughout the session, I provided encouragement, guidance, and support, emphasizing the importance of consistent adherence to the therapy program. I also addressed any concerns or questions the patient had, ensuring they felt heard and understood.

Based on the evaluation results, I discussed the patient\'s achievements and areas that required further attention. Together, we identified specific areas for continued focus and developed a plan to advance their treatment.

Additionally, I provided the patient with recommendations for exercises, self-care techniques, and lifestyle modifications that would complement their ongoing therapy and facilitate their recovery. These recommendations were tailored to their individual needs and aimed at empowering them to take an active role in their healing process.

As we concluded the session, I expressed my appreciation for the patient\'s commitment and effort in their therapy journey. I reassured them that I would continue to monitor their progress closely and adjust the treatment plan as needed to ensure their continued improvement.

The session served as a valuable opportunity to assess the patient\'s progress, address any concerns, and refine the therapy approach. Through ongoing collaboration and dedication, we aim to achieve optimal results and enhance the patient\'s overall well-being.');

INSERT INTO `appointment` (`time`,`date`,`tos`,`clinic_vat_number`, `patient_ssn`,`accepted`) VALUES ('10:00 AM','2023-05-21','Routine checkup.',10000, "111111111",'True'),
 ('11:00 AM','2023-05-21','Treatment session.',10000, "111111111",'False'),
 ('12:00 PM','2023-05-21','Routine checkup.',10000, "111111111",'True'),
 ('13:00 PM','2023-05-21','Treatment session.',10000, "111111111",'False'),
 ('15:00 PM','2023-05-21','Routine checkup.',10000, "111111111",'True'),
 ('17:00 PM','2023-05-21','Treatment session.',10000, "111111111",'False'),
 ('10:00 AM','2023-05-22','Routine checkup.',10000, "111111111",'True'),
 ('11:00 AM','2023-05-22','Treatment session.',10000, "111111111",'False'),
 ('12:00 PM','2023-05-22','Routine checkup.',10000, "111111111",'True'),
 ('13:00 PM','2023-05-22','Treatment session.',10000, "111111111",'False'),
 ('14:00 PM','2023-05-22','Routine checkup.',10000, "111111111",'True'),
 ('15:00 PM','2023-05-23','Treatment session.',10000, "111111111",'False'),
 ('16:00 PM','2023-05-23','Routine checkup.',10000, "111111111",'True'),
 ('17:00 PM','2023-05-23','Treatment session.',10000, "111111111",'True'),
 ('10:00 AM','2023-05-23','Treatment session.',10000, "111111111",'False'),
 ('11:00 AM','2023-05-23','Routine checkup.',10000, "111111111",'True'),
 ('12:00 PM','2023-05-23','Treatment session.',10000, "111111111",'True'),
 ('13:00 PM','2023-05-23','Routine checkup.',10000, "111111111",'True'),
 ('14:00 PM','2023-05-23','Treatment session.',10000, "111111111",'False'),
 ('15:00 PM','2023-05-23','Routine checkup.',10000, "111111111",'True'),
 ('16:00 PM','2023-05-23','Treatment session.',10000, "111111111",'True'),
 ('17:00 PM','2023-05-23','Treatment session.',10000, "111111111",'True'),
 ('10:00 AM','2023-05-24','Treatment session.',10000, "111111111",'False'),
 ('11:00 AM','2023-05-24','Routine checkup.',10000, "111111111",'True'),
 ('12:00 PM','2023-05-24','Treatment session.',10000, "111111111",'True'),
 ('13:00 PM','2023-05-24','Routine checkup.',10000, "111111111",'True'),
 ('14:00 PM','2023-05-24','Treatment session.',10000, "111111111",'False'),
 ('15:00 PM','2023-05-24','Routine checkup.',10000, "111111111",'True'),
 ('16:00 PM','2023-05-24','Treatment session.',10000, "111111111",'False'),
 ('17:00 PM','2023-05-24','Treatment session.',10000, "111111111",'True'),
 ('10:00 AM','2023-05-25','Treatment session.',10000, "111111111",'True'),
 ('11:00 AM','2023-05-25','Routine checkup.',10000, "111111111",'True'),
 ('12:00 PM','2023-05-25','Treatment session.',10000, "111111111",'True'),
 ('13:00 PM','2023-05-25','Routine checkup.',10000, "111111111",'True'),
 ('14:00 PM','2023-05-25','Treatment session.',10000, "111111111",'False'),
 ('15:00 PM','2023-05-25','Routine checkup.',10000, "111111111",'True'),
 ('16:00 PM','2023-05-25','Treatment session.',10000, "111111111",'True'),
 ('17:00 PM','2023-05-25','Treatment session.',10000, "111111111",'True'),
 ('10:00 AM','2023-05-26','Treatment session.',10000, "111111111",'True'),
 ('11:00 AM','2023-05-26','Routine checkup.',10000, "111111111",'True'),
 ('12:00 PM','2023-05-26','Treatment session.',10000, "111111111",'False'),
 ('13:00 PM','2023-05-26','Routine checkup.',10000, "111111111",'True'),
 ('14:00 PM','2023-05-26','Treatment session.',10000, "111111111",'False'),
 ('15:00 PM','2023-05-26','Routine checkup.',10000, "111111111",'True'),
 ('16:00 PM','2023-05-26','Treatment session.',10000, "111111111",'True'),
 ('17:00 PM','2023-05-26','Treatment session.',10000, "111111111",'False');

INSERT INTO `economic_movements` (`patient_ssn`,`date`,`tos`,`cost`) VALUES ("111111111",'2022-01-05','Routine checkup.',50.0),
 ("111111111",'2022-02-10','Treatment session.',75.0),
 ("111111111",'2022-03-15','Routine checkup.',60.0),
 ("222222222",'2022-01-05','Routine checkup.',50.0),
 ("222222222",'2022-02-10','Treatment session.',75.0),
 ("222222222",'2022-03-15','Routine checkup.',60.0),
 ("333333333",'2022-01-05','Routine checkup.',50.0),
 ("333333333",'2022-02-10','Treatment session.',75.0),
 ("333333333",'2022-03-15','Routine checkup.',60.0),
 ("444444444",'2022-01-05','Routine checkup.',50.0),
 ("444444444",'2022-02-10','Treatment session.',75.0),
 ("444444444",'2022-03-15','Routine checkup.',60.0),
 ("555555555",'2022-01-05','Routine checkup.',50.0),
 ("555555555",'2022-02-10','Treatment session.',75.0),
 ("555555555",'2022-03-15','Routine checkup.',60.0),
 ("666666666",'2022-01-05','Routine checkup.',50.0),
 ("666666666",'2022-02-10','Treatment session.',75.0),
 ("666666666",'2022-03-15','Routine checkup.',60.0),
 ("777777777",'2022-01-05','Routine checkup.',50.0),
 ("777777777",'2022-02-10','Treatment session.',75.0),
 ("777777777",'2022-03-15','Routine checkup.',60.0);
COMMIT;
