--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individua
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
-- insert into Registrant(id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212') 
insert into User(name, email, password) values('Sven Karol', 'uebung@cbse.st', 'test')
insert into User(name, email, password) values('Uwe Aßmann', 'master@cbse.st', 'test')
insert into Appointment(id, endDate, notes, personal, startDate, status, title, creator_email) values (4711, getdate()+100000, 'testnotes', false, getdate(), 'Free', 'Bake Cookies', 'uebung@cbse.st')
insert into Appointment_User(Appointment_id, participants_email) values (4711, 'master@cbse.st')