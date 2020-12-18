insert into ers.type values
(1, 'LODGING'),
(2, 'TRAVEL'),
(3, 'FOOD'),
(4, 'OTHER');

insert into ers.status values
(0, 'PENDING'),
(1, 'APPROVED'),
(2, 'DENIED');

insert into ers.roles values
(1, 'EMPLOYEE'),
(2, 'FINANCEMANAGER');

insert into ers.users values
(1, 'tophe', 'pass', 'Chris', 'Yun', 'christopher.yun@revature.net', 1),
(2, 'tophf', 'pass', 'Chris', 'Yun', 'christopher.yun@revature.net', 2),
(3, 'fmPeter', 'flower321', 'Peter', 'Davis', 'fm.peter.davis@revature.net', 2),
(4, 'debbies', '4dcs', 'Debbie', 'Sutton', 'debbie.sutton@revature.net', 1),
(5, 'dglove', 'anthony', 'Darryl', 'Glover', 'darryl.glover@revature.net', 1),
(6, 'hollings', 'rich123', 'Renee', 'Hollingsworth', 'renee.hollingsworth@revature.net', 1);

insert into ers.reimbursement values
(1, 350.20, current_timestamp, null, 'Stayed at the Hilton for one day.', null, 1, null, 1, 1),
(2, 200.50, current_timestamp, null, 'Ate all you can eat sushi.', null, 4, null, 1, 3),
(3, 11.13, current_timestamp, null, 'Lunch special at Taco truck.', null, 1, null, 1, 3),
(4, 85.16, current_timestamp, null, 'Train tickets for month of July.', null, 1, null, 1, 2),
(5, 123, current_timestamp, null, '', null, 1, null, 0, 4),
(6, 15.74, current_timestamp, null, 'Carls Jr.', null, 1, null, 2, 3);