insert into company(company_id,company_name,turnover,ceo,board_of_directors,stock_exchange_name,sector,brief,stock_code)
values(1,'State Bank of India','1234','A','A,B','BSE','Banking','Nil','500112');
insert into company(company_id,company_name,turnover,ceo,board_of_directors,stock_exchange_name,sector,brief,stock_code)
values(2,'NBI','2314','B','A,B','BSE','Banking','Nil','222');
insert into company(company_id,company_name,turnover,ceo,board_of_directors,stock_exchange_name,sector,brief,stock_code)
values(3,'Wipro','2323','A','A,C','BSE','IT','Nil','213');
insert into company(company_id,company_name,turnover,ceo,board_of_directors,stock_exchange_name,sector,brief,stock_code)
values(4,'Infosys','2211','D','D,B','BSE','IT','Nil','323');


insert into stock_price(stock_id,company_code,exchange_name,current_price,date,time)
values(1,'500112','BSE',310,'2020-08-01','0009:30:00');
insert into stock_price(stock_id,company_code,exchange_name,current_price,date,time)
values(2,'500112','BSE',311,'2020-08-02','0009:30:00');
insert into stock_price(stock_id,company_code,exchange_name,current_price,date,time)
values(3,'500112','BSE',312,'2020-08-03','0009:30:00');
insert into stock_price(stock_id,company_code,exchange_name,current_price,date,time)
values(4,'500112','BSE',316,'2020-08-04','0009:30:00');

insert into stock_price(stock_id,company_code,exchange_name,current_price,date,time)
values(5,'222','BSE',300,'2020-08-01','0009:30:00');
insert into stock_price(stock_id,company_code,exchange_name,current_price,date,time)
values(6,'222','BSE',310,'2020-08-02','0009:30:00');
insert into stock_price(stock_id,company_code,exchange_name,current_price,date,time)
values(7,'222','BSE',320,'2020-08-03','0009:30:00');
insert into stock_price(stock_id,company_code,exchange_name,current_price,date,time)
values(8,'222','BSE',321,'2020-08-04','0009:30:00');

insert into ipo(ipo_id,company_name,exchange_name,price_per_share,number_of_shares,open_date_time,remarks)
values(1,'State Bank of India','BSE',305,2024,'2020-08-01T0009:30:00','Nil');
insert into ipo(ipo_id,company_name,exchange_name,price_per_share,number_of_shares,open_date_time,remarks)
values(2,'NBI','BSE',300,1989,'2020-08-01T0009:30:00','Nil');