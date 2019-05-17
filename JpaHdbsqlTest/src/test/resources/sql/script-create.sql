 CREATE SCHEMA TESTS AUTHORIZATION DBA;
 
 SET SCHEMA TESTS;

--------------------------------------------------------
-- Archivo creado  - jueves-abril-27-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table COUNTRIES
--------------------------------------------------------

  CREATE TABLE COUNTRIES 
   (	COUNTRY_ID CHAR(2), 
	COUNTRY_NAME VARCHAR(40), 
	REGION_ID INTEGER, 
	 CONSTRAINT COUNTRY_C_ID_PK PRIMARY KEY (COUNTRY_ID)
   );

--------------------------------------------------------
--  DDL for Table COUNTRIES_TEST
--------------------------------------------------------

  CREATE TABLE COUNTRIES_TEST 
   (	COUNTRY_ID CHAR(2), 
	COUNTRY_NAME VARCHAR(40), 
	REGION_ID INTEGER
   ) ;
--------------------------------------------------------
--  DDL for Table DEPARTMENTS
--------------------------------------------------------

  CREATE TABLE DEPARTMENTS 
   (	DEPARTMENT_ID INTEGER, 
	DEPARTMENT_NAME VARCHAR(30), 
	MANAGER_ID INTEGER, 
	LOCATION_ID INTEGER
   ) ;

--------------------------------------------------------
--  DDL for Table EMPLOYEES
--------------------------------------------------------

  CREATE TABLE EMPLOYEES 
   (	EMPLOYEE_ID INTEGER, 
	FIRST_NAME VARCHAR(20), 
	LAST_NAME VARCHAR(25), 
	EMAIL VARCHAR(25), 
	PHONE_NUMBER VARCHAR(20), 
	HIRE_DATE DATE, 
	JOB_ID VARCHAR(10), 
	SALARY DECIMAL (8,2), 
	COMMISSION_PCT DECIMAL (2,2), 
	MANAGER_ID INTEGER, 
	DEPARTMENT_ID INTEGER
   ) ;

--------------------------------------------------------
--  DDL for Table JOB_HISTORY
--------------------------------------------------------

  CREATE TABLE JOB_HISTORY 
   (	EMPLOYEE_ID INTEGER, 
	START_DATE DATE, 
	END_DATE DATE, 
	JOB_ID VARCHAR(10), 
	DEPARTMENT_ID INTEGER
   ) ;

--------------------------------------------------------
--  DDL for Table JOBS
--------------------------------------------------------

  CREATE TABLE JOBS 
   (	JOB_ID VARCHAR(10), 
	JOB_TITLE VARCHAR(35), 
	MIN_SALARY INTEGER, 
	MAX_SALARY INTEGER
   ) ;

--------------------------------------------------------
--  DDL for Table LOCATIONS
--------------------------------------------------------

  CREATE TABLE LOCATIONS 
   (	LOCATION_ID INTEGER, 
	STREET_ADDRESS VARCHAR(40), 
	POSTAL_CODE VARCHAR(12), 
	CITY VARCHAR(30), 
	STATE_PROVINCE VARCHAR(25), 
	COUNTRY_ID CHAR(2)
   ) ;

   
--------------------------------------------------------
--  DDL for Table REGIONS
--------------------------------------------------------

  CREATE TABLE REGIONS 
   (	REGION_ID INTEGER, 
	REGION_NAME VARCHAR(25)
   ) ;


--------------------------------------------------------
--  DDL for Index COUNTRY_C_ID_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX COUNTRY_C_ID_PK ON COUNTRIES (COUNTRY_ID) 
  ;
--------------------------------------------------------
--  DDL for Index DEPT_ID_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX DEPT_ID_PK ON DEPARTMENTS (DEPARTMENT_ID) 
  ;
--------------------------------------------------------
--  DDL for Index DEPT_LOCATION_IX
--------------------------------------------------------

  CREATE INDEX DEPT_LOCATION_IX ON DEPARTMENTS (LOCATION_ID) 
  ;
--------------------------------------------------------
--  DDL for Index EMP_EMAIL_UK
--------------------------------------------------------

  CREATE UNIQUE INDEX EMP_EMAIL_UK ON EMPLOYEES (EMAIL) 
  ;
--------------------------------------------------------
--  DDL for Index EMP_EMP_ID_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX EMP_EMP_ID_PK ON EMPLOYEES (EMPLOYEE_ID) 
  ;
--------------------------------------------------------
--  DDL for Index EMP_DEPARTMENT_IX
--------------------------------------------------------

  CREATE INDEX EMP_DEPARTMENT_IX ON EMPLOYEES (DEPARTMENT_ID) 
  ;
--------------------------------------------------------
--  DDL for Index EMP_JOB_IX
--------------------------------------------------------

  CREATE INDEX EMP_JOB_IX ON EMPLOYEES (JOB_ID) 
  ;
--------------------------------------------------------
--  DDL for Index EMP_MANAGER_IX
--------------------------------------------------------

  CREATE INDEX EMP_MANAGER_IX ON EMPLOYEES (MANAGER_ID) 
  ;
--------------------------------------------------------
--  DDL for Index EMP_NAME_IX
--------------------------------------------------------

  CREATE INDEX EMP_NAME_IX ON EMPLOYEES (LAST_NAME, FIRST_NAME) 
  ;
--------------------------------------------------------
--  DDL for Index JHIST_EMP_ID_ST_DATE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX JHIST_EMP_ID_ST_DATE_PK ON JOB_HISTORY (EMPLOYEE_ID, START_DATE) 
  ;
--------------------------------------------------------
--  DDL for Index JHIST_JOB_IX
--------------------------------------------------------

  CREATE INDEX JHIST_JOB_IX ON JOB_HISTORY (JOB_ID) 
  ;
--------------------------------------------------------
--  DDL for Index JHIST_EMPLOYEE_IX
--------------------------------------------------------

  CREATE INDEX JHIST_EMPLOYEE_IX ON JOB_HISTORY (EMPLOYEE_ID) 
  ;
--------------------------------------------------------
--  DDL for Index JHIST_DEPARTMENT_IX
--------------------------------------------------------

  CREATE INDEX JHIST_DEPARTMENT_IX ON JOB_HISTORY (DEPARTMENT_ID) 
  ;
--------------------------------------------------------
--  DDL for Index JOB_ID_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX JOB_ID_PK ON JOBS (JOB_ID) 
  ;
--------------------------------------------------------
--  DDL for Index LOC_ID_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX LOC_ID_PK ON LOCATIONS (LOCATION_ID) 
  ;
--------------------------------------------------------
--  DDL for Index LOC_CITY_IX
--------------------------------------------------------

  CREATE INDEX LOC_CITY_IX ON LOCATIONS (CITY) 
  ;
--------------------------------------------------------
--  DDL for Index LOC_STATE_PROVINCE_IX
--------------------------------------------------------

  CREATE INDEX LOC_STATE_PROVINCE_IX ON LOCATIONS (STATE_PROVINCE) 
  ;
--------------------------------------------------------
--  DDL for Index LOC_COUNTRY_IX
--------------------------------------------------------

  CREATE INDEX LOC_COUNTRY_IX ON LOCATIONS (COUNTRY_ID) 
  ;
--------------------------------------------------------
--  DDL for Index REG_ID_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX REG_ID_PK ON REGIONS (REGION_ID) 
  ;

--  Constraints for Table DEPARTMENTS
--------------------------------------------------------

  ALTER TABLE DEPARTMENTS ADD CONSTRAINT DEPT_ID_PK PRIMARY KEY (DEPARTMENT_ID);
--------------------------------------------------------
--  Constraints for Table EMPLOYEES
--------------------------------------------------------

  ALTER TABLE EMPLOYEES ADD CONSTRAINT EMP_EMP_ID_PK PRIMARY KEY (EMPLOYEE_ID);
  ALTER TABLE EMPLOYEES ADD CONSTRAINT EMP_EMAIL_UK UNIQUE (EMAIL);
  ALTER TABLE EMPLOYEES ADD CONSTRAINT EMP_SALARY_MIN CHECK (salary > 0);
  
--------------------------------------------------------
--  Constraints for Table JOB_HISTORY
--------------------------------------------------------

  ALTER TABLE JOB_HISTORY ADD CONSTRAINT JHIST_EMP_ID_ST_DATE_PK PRIMARY KEY (EMPLOYEE_ID, START_DATE);
  ALTER TABLE JOB_HISTORY ADD CONSTRAINT JHIST_DATE_INTERVAL CHECK (end_date > start_date);
  
--------------------------------------------------------
--  Constraints for Table JOBS
--------------------------------------------------------

  ALTER TABLE JOBS ADD CONSTRAINT JOB_ID_PK PRIMARY KEY (JOB_ID);
--------------------------------------------------------
--  Constraints for Table LOCATIONS
--------------------------------------------------------

  ALTER TABLE LOCATIONS ADD CONSTRAINT LOC_ID_PK PRIMARY KEY (LOCATION_ID);
--------------------------------------------------------
--  Constraints for Table REGIONS
--------------------------------------------------------

  ALTER TABLE REGIONS ADD CONSTRAINT REG_ID_PK PRIMARY KEY (REGION_ID);
--------------------------------------------------------
--  Ref Constraints for Table COUNTRIES
--------------------------------------------------------

  ALTER TABLE COUNTRIES ADD CONSTRAINT COUNTR_REG_FK FOREIGN KEY (REGION_ID)
	  REFERENCES REGIONS (REGION_ID);
--------------------------------------------------------
--  Ref Constraints for Table DEPARTMENTS
--------------------------------------------------------

  ALTER TABLE DEPARTMENTS ADD CONSTRAINT DEPT_LOC_FK FOREIGN KEY (LOCATION_ID)
	  REFERENCES LOCATIONS (LOCATION_ID);
  ALTER TABLE DEPARTMENTS ADD CONSTRAINT DEPT_MGR_FK FOREIGN KEY (MANAGER_ID)
	  REFERENCES EMPLOYEES (EMPLOYEE_ID);
--------------------------------------------------------
--  Ref Constraints for Table EMPLOYEES
--------------------------------------------------------

  ALTER TABLE EMPLOYEES ADD CONSTRAINT EMP_DEPT_FK FOREIGN KEY (DEPARTMENT_ID)
	  REFERENCES DEPARTMENTS (DEPARTMENT_ID);
  ALTER TABLE EMPLOYEES ADD CONSTRAINT EMP_JOB_FK FOREIGN KEY (JOB_ID)
	  REFERENCES JOBS (JOB_ID);
  ALTER TABLE EMPLOYEES ADD CONSTRAINT EMP_MANAGER_FK FOREIGN KEY (MANAGER_ID)
	  REFERENCES EMPLOYEES (EMPLOYEE_ID);
--------------------------------------------------------
--  Ref Constraints for Table JOB_HISTORY
--------------------------------------------------------

  ALTER TABLE JOB_HISTORY ADD CONSTRAINT JHIST_DEPT_FK FOREIGN KEY (DEPARTMENT_ID)
	  REFERENCES DEPARTMENTS (DEPARTMENT_ID);
  ALTER TABLE JOB_HISTORY ADD CONSTRAINT JHIST_EMP_FK FOREIGN KEY (EMPLOYEE_ID)
	  REFERENCES EMPLOYEES (EMPLOYEE_ID);
  ALTER TABLE JOB_HISTORY ADD CONSTRAINT JHIST_JOB_FK FOREIGN KEY (JOB_ID)
	  REFERENCES JOBS (JOB_ID);
--------------------------------------------------------
--  Ref Constraints for Table LOCATIONS
--------------------------------------------------------

  ALTER TABLE LOCATIONS ADD CONSTRAINT LOC_C_ID_FK FOREIGN KEY (COUNTRY_ID)
	  REFERENCES COUNTRIES (COUNTRY_ID);
--------------------------------------------------------
--  DDL for Trigger SECURE_EMPLOYEES
--------------------------------------------------------
