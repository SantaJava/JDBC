CREATE TABLE goodsinfo(
	code CHAR(5) NOT NULL,
	name vARCHAR2(30) NOT NULL,
	price NUMBER NOT NULL,
	maker VARCHAR2(20),
	PRIMARY KEY (code)
);


INSERT INTO goodsinfo(code, name, price, maker)
VALUES('10001', 'UHD 아몰레드 TV', 350000, 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10002' , 'DVD 플레이어' , 250000 , 'LG');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10003' , '디지털 카메라' , 210000 , '삼성');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10004' , '전자사전' , 180000 , '아이리버');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES ('10005' , '벽걸이 에어컨' , 400000 , '삼성');

INSERT INTO goodsinfo ( code, name, price, maker)
VALUES (seq.NEXTVAL , '로봇 청소기' , 540000 , 'LG');

CREATE SEQUENCE seq
START WITH 10005
INCREMENT BY 1;


SELECT*FROM GOODSINFO;
