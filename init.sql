/**
 * 创建市场维修记录(从CRM_REPAIR_CONTENT表同步)
*服务中心,工单编号,物料名称,物料编号,机型类别,产品系列,产品型号,主机条码,故障类别代码,
故障原因代码,故障类别名称,故障原因,结算说明,实际完成时间,故障描述现象,
故障现场现象,详细信息,服务网点,服务工程师,家庭电话,手机,开票日期,创建时间,购买途径
 */
-- Create table
create table T_MARKET_REPAIR_RECORD
(
  id                 NUMBER(12) not null,
  service_center     VARCHAR2(200),
  order_number       VARCHAR2(200),
  part_code          VARCHAR2(300),
  part_name          VARCHAR2(200),
  part_number        VARCHAR2(300),
  invoice_date       DATE,
  finish_date        DATE,
  service_site       VARCHAR2(200),
  service_engineer   VARCHAR2(200),
  settlement_desc    VARCHAR2(200),
  home_phone         VARCHAR2(200),
  cellphone          VARCHAR2(200),
  record_time        DATE,
  buy_type           VARCHAR2(200),
  fault_type_code    VARCHAR2(200),
  fault_type_name    VARCHAR2(200),
  fault_reason_code  VARCHAR2(200),
  fault_reason_name  VARCHAR2(200),
  fault_desc         VARCHAR2(300),
  fault_current_desc VARCHAR2(300),
  info_desc          CLOB,
  entering_time      DATE,
  create_time        DATE default SYSDATE not null,
  product_type       VARCHAR2(200),
  product_family     VARCHAR2(200),
  part_type          VARCHAR2(200),
  is_over            VARCHAR2(200) default '否',
  is_settlement      VARCHAR2(1),
  intall_date        DATE,
  downline_date      VARCHAR2(128),
  raw_service_center VARCHAR2(200),
  description        VARCHAR2(50),
  customer_id        VARCHAR2(50),
  part_id            VARCHAR2(50)
);
-- Add comments to the table 
comment on table T_MARKET_REPAIR_RECORD is '市场维修记录';
-- Create/Recreate indexes 
create index INDEX_MARKCODE on T_MARKET_REPAIR_RECORD (PART_CODE);
  
create index TMRR_CUSTOMER_ID on T_MARKET_REPAIR_RECORD (CUSTOMER_ID);

create index TMRR_PART_ID on T_MARKET_REPAIR_RECORD (PART_ID);

-- Create/Recreate primary, unique and foreign key constraints 
alter table T_MARKET_REPAIR_RECORD add primary key (ID);

/**
*  创建市场维修记录(旧记录表，从mes旧数据里导出)
*/
create table t_Market_Repair_Record_oldmes 
as select * from T_DOWNLINE_SHIP_RECORD t where t.is_oldmes='1' 


/**
产品下线发货记录表
SELECT U.UNIT_KEY,PL.UDA_1,PL.UDA_2,PL.P_LINE_NAME,PL.DESCRIPTION,U.SERIAL_NUMBER,u.part_number,
p.uda_0 机型类别,'型号',p.description as partname,u.finished_time downline_time,nvl(u.promised_time,u.Last_Modified_Time) ship_time, so.destination_s,so.destination_id_s
FROM UNIT U,WORK_ORDER_ITEMS WOI,PRODUCTION_LINE PL,AT_STORAGEORDER SO,PART P
WHERE U.ORDER_ITEM_KEY = WOI.ORDER_ITEM_KEY 
AND WOI.PLANNED_LINE = PL.P_LINE_NAME
AND U.UDA_6 = SO.STORAGE_NUMBER_S(+)
AND U.PART_NUMBER = P.PART_NUMBER
-- and TO_CHAR(u.Last_Modified_Time, 'yyyy-mm')='2014-08'
and u.Last_Modified_Time BETWEEN to_date('2014-07-01 13:23:44','yyyy-mm-dd hh24:mi:ss') AND to_date('2014-12-31 13:23:44','yyyy-mm-dd hh24:mi:ss')
AND ROWNUM<100000  

工厂，车间，产线编号，产线，主机条码，产品编号，机型类别，类型，产品名称，下线日期，发货日期，仓库，仓库id
**/
create table T_DOWNLINE_SHIP_RECORD (
id NUMBER(12) NOT NULL,
factory  VARCHAR(128) NULL,
area VARCHAR(128) NULL,
productline_number  VARCHAR(128) NULL,
productline_name  VARCHAR(256) NULL,
part_code  VARCHAR(128) NULL,
part_number  VARCHAR(128) NULL,
product_type  VARCHAR(256) NULL,
part_type  VARCHAR(256) NULL,
product_family VARCHAR2(256) NULL,
part_name  VARCHAR(256) NULL,
downline_date DATE NULL,
ship_date DATE NULL,
location VARCHAR(128) NULL,/**仓库*/
location_code VARCHAR(128) NULL,
create_time DATE DEFAULT SYSDATE NOT NULL,
is_oldmes VARCHAR2(10) default 0,
DESCRIPTION VARCHAR2(50) NULL,
PRIMARY KEY (id)
);

/**
 * 区域分公司仓库表
 * 分公司仓库‘区域
 * 字段：仓库代码，仓库名称，服务中心
 */
create table T_LOCATION_REGION(
id NUMBER(12) NOT NULL,
location_code  VARCHAR(256) NULL,
location  VARCHAR(256) NULL,
region  VARCHAR(256) NULL,
province VARCHAR(128) NULL,
merge_region VARCHAR(128) NULL, 
create_user  VARCHAR(128) NULL,
create_time DATE DEFAULT SYSDATE NOT NULL,
last_update_user  VARCHAR(128) NULL,
last_update_time DATE NULL,
PRIMARY KEY (id)
);

/**
 * 下线汇总表
 * 机型类别，月份，型号，区域，产线，下线数
 *   select tdsr.PRODUCT_TYPE, to_char(tdsr.DOWNLINE_DATE,'yyyy-mm') as STATISTICS_MONTH, tdsr.PART_TYPE, 
             tlr.REGION, tdsr.PRODUCTLINE_NUMBER,count(*) as DOWNLINE_COUNT
      from T_DOWNLINE_SHIP_RECORD tdsr, T_LOCATION_REGION tlr
      where 1=1 
      and tdsr.LOCATION_CODE = tlr.LOCATION_CODE(+)
      group by tdsr.PRODUCT_TYPE, to_char(tdsr.DOWNLINE_DATE,'yyyy-mm'), tdsr.PART_TYPE, 
             tlr.REGION, tdsr.PRODUCTLINE_NUMBER
**/
create table T_DOWNLINE_TOTAL_RECORD (
id NUMBER(12) NOT NULL,
product_type  VARCHAR(256) NULL,
statistics_month VARCHAR(128) NULL,
PRODUCT_FAMILY VARCHAR(256) NULL,
part_type  VARCHAR(256) NULL,
region  VARCHAR(256) NULL,
productline_number  VARCHAR(128) NULL,
downline_count  NUMBER(12) DEFAULT 0,
create_time DATE DEFAULT SYSDATE NOT NULL,
PRIMARY KEY (id)
);

/**
 * 发货汇总表
 * 机型类别，月份，型号，区域，产线，发货数
 *   select tdsr.PRODUCT_TYPE, to_char(tdsr.SHIP_DATE,'yyyy-mm') as STATISTICS_MONTH, tdsr.PART_TYPE, 
             tlr.REGION, tdsr.PRODUCTLINE_NUMBER,count(*) as SHIP_COUNT
      from T_DOWNLINE_SHIP_RECORD tdsr, T_LOCATION_REGION tlr
      where 1=1
      and tdsr.LOCATION_CODE = tlr.LOCATION_CODE(+)
      group by tdsr.PRODUCT_TYPE, to_char(tdsr.SHIP_DATE,'yyyy-mm'), tdsr.PART_TYPE, 
             tlr.REGION, tdsr.PRODUCTLINE_NUMBER
**/
create table T_SHIP_TOTAL_RECORD (
id NUMBER(12) NOT NULL,
product_type  VARCHAR(256) NULL,
statistics_month VARCHAR(128) NULL,
PRODUCT_FAMILY VARCHAR(256) NULL,
part_type  VARCHAR(256) NULL,
region  VARCHAR(256) NULL,
productline_number  VARCHAR(128) NULL,
ship_count  NUMBER(12) DEFAULT 0,
create_time DATE DEFAULT SYSDATE NOT NULL,
PRIMARY KEY (id)
);

/**
 * 
 * 维修汇总表
 * 机型类别，生产月份，维修月份，型号，区域，产线，故障类别，故障原因，维修数
 * select tdsr.PRODUCT_TYPE, to_char(tdsr.DOWNLINE_DATE,'yyyy-mm') as DOWNLINE_MONTH, 
       to_char(tmrr.finish_date,'yyyy-mm') as REPAIRED_MONTH, tdsr.PART_TYPE, 
       tlr.REGION, tdsr.PRODUCTLINE_NUMBER, tmrr.fault_type_code, tmrr.fault_reason_code, 
       count(*) as DOWNLINE_COUNT
from T_DOWNLINE_SHIP_RECORD tdsr, T_LOCATION_REGION tlr, t_Market_Repair_Record tmrr
where 1=1 
and tdsr.part_code = tmrr.part_code
and tdsr.LOCATION_CODE = tlr.LOCATION_CODE(+)
group by tdsr.PRODUCT_TYPE, to_char(tdsr.DOWNLINE_DATE,'yyyy-mm'), tdsr.PART_TYPE, 
         tlr.REGION, tdsr.PRODUCTLINE_NUMBER, tmrr.fault_type_code, tmrr.fault_reason_code, 
         to_char(tmrr.finish_date,'yyyy-mm')
**/
create table T_MARKET_REPAIR_TOTAL_RECORD (
id NUMBER(12) NOT NULL,
product_type  VARCHAR(256) NULL,
downline_month VARCHAR(128) NULL,
install_month VARCHAR(128) NULL,
repaired_month  VARCHAR(128) NULL,
PRODUCT_FAMILY VARCHAR(256) NULL,
part_type  VARCHAR(256) NULL,
region  VARCHAR(256) NULL,
productline_number  VARCHAR(128) NULL,
fault_type_code  VARCHAR(128) NULL,
fault_reason_code  VARCHAR(128) NULL,
repaired_count  NUMBER(12) DEFAULT 0,
create_time DATE DEFAULT SYSDATE NOT NULL,
is_over   VARCHAR2(200)  NULL,
PRIMARY KEY (id)
);

/**
 * 安装汇总表
 */
-- Create table
create table T_INSTALL
(
  serial_number     VARCHAR2(150),
  service_order     VARCHAR2(150),
  product_type      VARCHAR2(50),
  part_number       VARCHAR2(50),
  part_family       VARCHAR2(50),
  part_type         VARCHAR2(50),
  part_name         VARCHAR2(100),
  region            VARCHAR2(50),
  install_month     VARCHAR2(50),
  fault_type_code   VARCHAR2(50),
  fault_reason_code VARCHAR2(50),
  is_over           VARCHAR2(50),
  valid             VARCHAR2(50),
  repair_month      VARCHAR2(50),
  customer_id       VARCHAR2(50),
  part_id           VARCHAR2(50),
  create_time       DATE default SYSDATE
);

-- Add comments to the columns 
comment on column T_INSTALL.serial_number
  is '主机条码';
comment on column T_INSTALL.product_type
  is '机型类别';
comment on column T_INSTALL.part_number
  is '物料编码';
comment on column T_INSTALL.part_family
  is '产品系列';
comment on column T_INSTALL.part_type
  is '产品型号';
comment on column T_INSTALL.region
  is '服务中心';
comment on column T_INSTALL.install_month
  is '安装时间';
comment on column T_INSTALL.fault_type_code
  is '故障类别代码（故障大类）';
comment on column T_INSTALL.fault_reason_code
  is '故障原因代码（故障小类）';
comment on column T_INSTALL.is_over
  is '百台内';
comment on column T_INSTALL.valid
  is '故障原因是否有效';
comment on column T_INSTALL.repair_month
  is '维修时间';
comment on column T_INSTALL.customer_id
  is '客户ID';
comment on column T_INSTALL.part_id
  is '产品ID';
comment on column T_INSTALL.create_time
  is '创建时间';
comment on column T_INSTALL.part_name
  is '物料名称';
-- Create/Recreate indexes 
create index TI_CUSTOMER_ID on T_INSTALL (CUSTOMER_ID);

create index TI_INSTALL_MONTH on T_INSTALL (INSTALL_MONTH);

create index TI_PART_ID on T_INSTALL (PART_ID);

create index TI_PART_NAME on T_INSTALL (PART_NAME);

create index TI_SERIAL_NUMBER on T_INSTALL (SERIAL_NUMBER);

-- Create table
create table T_INSTALL_REPAIR
(
  product_type      VARCHAR2(50),
  part_type         VARCHAR2(50),
  region            VARCHAR2(50),
  fault_type_code   VARCHAR2(50),
  fault_reason_code VARCHAR2(50),
  is_over           VARCHAR2(50),
  valid             VARCHAR2(50),
  install_month     VARCHAR2(50),
  repair_month      VARCHAR2(50),
  repair_count      VARCHAR2(50),
  create_time       DATE default SYSDATE,
  product_family    VARCHAR2(50)
);

--仪表盘数据
create table T_REPAIR_RATE_DASHBOARD
(
  id                    NUMBER,
  product_type          VARCHAR2(50),
  month                 VARCHAR2(50),
  hundred_repair_rate   FLOAT,
  reference_repair_rate FLOAT,
  target_repair_rate    FLOAT,
  create_time           DATE default sysdate,
  create_user           VARCHAR2(50)
);

alter table T_REPAIR_RATE_DASHBOARD add constraint TRRD_PT_M unique (PRODUCT_TYPE, MONTH);

/**
安装维修汇总表（弃用）
create table t_install_total_record (
product_type  VARCHAR(256) NULL,
install_month VARCHAR(128) NULL,
install_count  NUMBER(12) DEFAULT 0,
repair_month VARCHAR(128) NULL,
repair_count  NUMBER(12) DEFAULT 0,
create_time DATE DEFAULT SYSDATE NOT NULL,
part_type  VARCHAR(256) NULL,
region  VARCHAR(256) NULL,
fault_type_code  VARCHAR(128) NULL,
fault_reason_code  VARCHAR(128) NULL,
is_over   VARCHAR2(200)  NULL,
valid  VARCHAR2(10),
col1 VARCHAR(128) NULL,
col2 VARCHAR(128) NULL,
col3 VARCHAR(128) NULL,
col4 VARCHAR(128) NULL,
PART_FAMILY	VARCHAR2(128) NULL,
PRIMARY KEY (product_type, install_month, repair_month) 
);
**/

/**
电商数据表
**/
create table T_SHIP_ELEC_RECORD
(
  ID                  NUMBER(12) not null,
  PRODUCT_NUMBER      VARCHAR2(50),
  PRODUCT_TYPE        VARCHAR2(100),
  PART_NUMBER         NUMBER(20),
  SEND_LOCATION       VARCHAR2(50),
  REACH_LOCATION_CODE VARCHAR2(50),
  COUNT_DATE          VARCHAR2(50)
);

-- Add comments to the columns 
comment on column T_SHIP_ELEC_RECORD.ID
  is 'ID';
comment on column T_SHIP_ELEC_RECORD.PRODUCT_NUMBER
  is '产品编码  ';
comment on column T_SHIP_ELEC_RECORD.PRODUCT_TYPE
  is '产品名称  ';
comment on column T_SHIP_ELEC_RECORD.PART_NUMBER
  is '数量';
comment on column T_SHIP_ELEC_RECORD.SEND_LOCATION
  is '发出仓库';
comment on column T_SHIP_ELEC_RECORD.REACH_LOCATION_CODE
  is '发往仓库编码';
comment on column T_SHIP_ELEC_RECORD.COUNT_DATE
  is '统计时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SHIP_ELEC_RECORD add primary key (ID);

/**
系统配置表
**/
create table T_MANAGE
(
  id             NUMBER(12) not null,
  code			 VARCHAR2(128),
  code_name      VARCHAR2(128),
  code_value	 VARCHAR2(128),
  create_time 	 DATE,
  create_user    VARCHAR2(128),
  update_time    DATE,
  update_user    VARCHAR2(128),
  PRIMARY KEY (id)
);
/**
故障类别表
**/
create table T_FAULT_TYPE
(
  id                 NUMBER(12) not null,
  code               VARCHAR2(128),
  name               VARCHAR2(128),
  product_type       VARCHAR2(200),
  create_time        DATE,
  create_user        VARCHAR2(128),
  last_update_time   DATE,
  last_update_user   VARCHAR2(128),
  PRIMARY KEY (id)
);
/**
故障原因表
**/
create table T_FAULT_REASON
(
  id                 NUMBER(12) not null,
  code               VARCHAR2(128),
  name               VARCHAR2(128),
  valid              VARCHAR2(128),
  create_time        DATE,
  create_user        VARCHAR2(128),
  last_update_time   DATE,
  last_update_user   VARCHAR2(128),
  meshfaultname		 VARCHAR2(256),
  meshfaultcode		 VARCHAR2(128),
  product_type VARCHAR2(200),
  PRIMARY KEY (id)
);
/**
机型类别百台维修率表
**/
create table T_REPAIR_RATE
(
  id					NUMBER(12) not null,
  machine_type       	VARCHAR2(128),
  year_mon				VARCHAR2(64),
  hundred_repair_rate	FLOAT,
  user_name				VARCHAR2(128),
  entry_time			DATE,
  PRIMARY KEY (id)
);

create sequence SEQ_t_location_region increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_t_market_repair_record increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_t_fault_reason increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_t_fault_type increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_t_relation increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_t_repair_rate increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_t_manage increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;

/**
 * 汇总信息日志
 * ID,汇总名称， 统计时间，接口调用开始，调用结束，结果，操作类型（自动or手动），统计类型（汇总or导入），备注，创建时间
 */
create table T_SUM_OPERATION_LOG (
id NUMBER(12) NOT NULL,
name  VARCHAR(128) NULL,
statistics_time VARCHAR(256) NULL,
start_time  DATE NULL,
end_time  DATE NULL,
rusult  VARCHAR(256) NULL,
operation_type  VARCHAR(128) NULL,
sum_type  VARCHAR(128) NULL,
remak  VARCHAR(500) NULL,
create_time DATE DEFAULT SYSDATE NOT NULL,
PRIMARY KEY (id)
);
/**
机型类别月份无效记录表
**/
create table T_TIME_MATRX(
id                 NUMBER(12) NOT NULL,
machine_type       VARCHAR2(128),
production_month   VARCHAR2(128),
lnvalid_flags 	   VARCHAR2(128),
create_time		   DATE,
create_user        VARCHAR2(128),
statis_type      VARCHAR2(50),
PRIMARY KEY (id)
);
alter table T_TIME_MATRX  add constraint PRODTYPE_MONTH_PRIMARYKEY unique (PRODUCTION_MONTH, MACHINE_TYPE);
/**
机型类别表
**/
create table T_MACHINE_TYPE(
id                 NUMBER(12) NOT NULL,
machine_type       VARCHAR2(128),
create_time		   DATE,
create_user        VARCHAR2(128),
update_time        DATE,
update_user        VARCHAR2(128),
PRIMARY KEY (id)
);
/**
CRM维修记录表(由CRM写入)
*/
create table CRM_REPAIR_CONTENT
(
  service_center           VARCHAR2(200),
  repair_order_number      VARCHAR2(200),
  part_description         VARCHAR2(200),
  settlement               VARCHAR2(200),
  complete_time            DATE,
  entering_time            DATE,
  defect_description       VARCHAR2(300),
  defect_local_description VARCHAR2(300),
  detail_description       CLOB,
  service_point            VARCHAR2(200),
  service_person           VARCHAR2(200),
  telephone                VARCHAR2(200),
  mobilephone              VARCHAR2(200),
  serial_number            VARCHAR2(200),
  invoice_time             DATE,
  creation_time            DATE,
  buy_the_way              VARCHAR2(200),
  defect_type              VARCHAR2(200),
  defect_code              VARCHAR2(200),
  defect_comment           VARCHAR2(200),
  defect_reason            VARCHAR2(200),
  part_number              VARCHAR2(200),
  product_type             VARCHAR2(200),
  product_family           VARCHAR2(200),
  part_type                VARCHAR2(200),
  PART_MODE				   VARCHAR2(300),
  TRX_TIME				   DATE,
  is_settlement      VARCHAR2(1) NULL,
  customer_id              VARCHAR2(50),
  part_id                  VARCHAR2(50),
  create_time              DATE default sysdate not null
);

create table CRM_ACCESSORIES_LIST
(
  job_number  VARCHAR2(100),
  parts_no    VARCHAR2(50),
  parts_name  VARCHAR2(100),
  update_time DATE,
  parts_id    VARCHAR2(100)
);

create table T_SUPPLIER_PART
(
  id                  NUMBER,
  supplier_number     VARCHAR2(50),
  supplier_name       VARCHAR2(100),
  supplier_number_n   VARCHAR2(50),
  part_number         VARCHAR2(50),
  part_name           VARCHAR2(128),
  supplier_code       VARCHAR2(50),
  create_time         DATE default SYSDATE,
  factory             VARCHAR2(50),
  supplier_short_name VARCHAR2(50)
);

create table T_SUPPLIER_REF
(
  id                  NUMBER,
  supplier_number     VARCHAR2(50),
  supplier_number_n   VARCHAR2(50),
  supplier_name       VARCHAR2(100),
  supplier_name_n     VARCHAR2(100),
  supplier_short_name VARCHAR2(100),
  create_time         DATE default SYSDATE
);

create table T_MARKET_REPAIR_PART_RECORD
(
  id                  NUMBER not null,
  order_number        VARCHAR2(100),
  serial_number       VARCHAR2(150),
  part_number         VARCHAR2(50),
  part_serial         VARCHAR2(50),
  consumption_type    VARCHAR2(50),
  supplier_code       VARCHAR2(50),
  part_maturity       VARCHAR2(50),
  part_name           VARCHAR2(100),
  product_type        VARCHAR2(50),
  part_family         VARCHAR2(50),
  part_type           VARCHAR2(50),
  region              VARCHAR2(50),
  region_number       VARCHAR2(50),
  fault_type_code     VARCHAR2(50),
  fault_type_name     VARCHAR2(100),
  fault_reason_code   VARCHAR2(50),
  fault_reason_name   VARCHAR2(100),
  supplier_number     VARCHAR2(50),
  supplier_name       VARCHAR2(100),
  supplier_short_name VARCHAR2(100),
  install_date        VARCHAR2(100),
  downline_date       VARCHAR2(100),
  repair_date         DATE,
  create_date         DATE default SYSDATE,
  is_over             VARCHAR2(20),
  raw_region          VARCHAR2(50),
  product_qms_type    VARCHAR2(50),
  part_level          VARCHAR2(50)
);

create index TMRPR_ON on T_MARKET_REPAIR_PART_RECORD (ORDER_NUMBER);

create index TMRPR_PN on T_MARKET_REPAIR_PART_RECORD (PART_NUMBER);

create index TMRPR_SN on T_MARKET_REPAIR_PART_RECORD (SERIAL_NUMBER);

alter table T_MARKET_REPAIR_PART_RECORD add primary key (ID);

create table T_SHIP_PART_RECORD
(
  id                  NUMBER not null,
  serial_number       VARCHAR2(128),
  part_number         VARCHAR2(50),
  part_serial         VARCHAR2(80),
  supplier_code       VARCHAR2(20),
  part_maturity       VARCHAR2(50),
  part_name           VARCHAR2(100),
  product_part_number VARCHAR2(50),
  product_type        VARCHAR2(50),
  product_family      VARCHAR2(50),
  part_type           VARCHAR2(50),
  region              VARCHAR2(50),
  region_number       VARCHAR2(50),
  supplier_name       VARCHAR2(100),
  supplier_short_name VARCHAR2(100),
  supplier_number     VARCHAR2(50),
  ship_date           VARCHAR2(50),
  downline_time       DATE,
  create_date         DATE default SYSDATE,
  part_level          VARCHAR2(50),
  product_qms_type    VARCHAR2(50)
);

create index TSPR_PART_NUMBER on T_SHIP_PART_RECORD (PART_NUMBER);

create index TSPR_SN on T_SHIP_PART_RECORD (SERIAL_NUMBER);

alter table T_SHIP_PART_RECORD add primary key (ID);

create sequence SEQ_t_machine_type increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
/**
 * 创建唯一约束条件
 */
alter table T_DOWNLINE_TOTAL_RECORD add constraint downline_total_unique unique(product_type,statistics_month,part_type,region,productline_number);
alter table T_SHIP_TOTAL_RECORD add constraint ship_total_unique unique(product_type,statistics_month,part_type,region,productline_number);
alter table T_MARKET_REPAIR_TOTAL_RECORD add constraint market_repair_total_unique unique(product_type,downline_month,repaired_month,part_type,region,productline_number,fault_type_code,fault_reason_code,is_over);
/**
 * 创建sequence
 */
create sequence SEQ_T_TIME_MATRX increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_DOWNLINE_TOTAL_RECORD increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_SHIP_TOTAL_RECORD increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_MARKET_REPAIR_TOTAL increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_SUM_OPERATION_LOG increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_DOWNLINE_SHIP_RECORD increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_REPAIR_RATE_DASHBOARD increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_SUPPLIER_PART increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_SUPPLIER_REF increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_MARKET_REPAIRPARTRECORD by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_SHIP_PART_RECORD by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;

/**
 * 触发器  下线汇总
 */
create trigger TRI_DOWNLINE_TOTAL
before insert on T_DOWNLINE_TOTAL_RECORD
for each row
begin
select SEQ_T_DOWNLINE_TOTAL_RECORD.nextval into :new.id from dual;
end;

/** 
 * 触发器 发货汇总
 */
create trigger TRI_SHIP_TOTAL
before insert on T_SHIP_TOTAL_RECORD
for each row
begin
select SEQ_T_SHIP_TOTAL_RECORD.nextval into :new.id from dual;
end;

/** 
 * 触发器 物料市场维修汇总
 */
CREATE OR REPLACE TRIGGER TRI_MARKET_REPAIR_PART_RECORD
before insert on T_MARKET_REPAIR_PART_RECORD
for each row
begin
select SEQ_T_MARKET_REPAIRPARTRECORD.nextval into :new.id from dual;
end;

/** 
 * 触发器 物料市场发货汇总
 */
create or replace trigger TRI_SHIP_PART_RECORD
before insert on T_SHIP_PART_RECORD
for each row
begin
select SEQ_T_SHIP_PART_RECORD.nextval into :new.id from dual;
end;

/**
 * 触发器
 */
create trigger TRI_MARKET_REPAIR_TOTAL
before insert on T_MARKET_REPAIR_TOTAL_RECORD
for each row
begin
select SEQ_T_MARKET_REPAIR_TOTAL.nextval into :new.id from dual;
end;

/**
 * 触发器 维修表
 */
create trigger TRI_MARKET_REPAIR_RECORD
before insert on T_MARKET_REPAIR_RECORD
for each row
begin
select SEQ_T_MARKET_REPAIR_RECORD.nextval into :new.id from dual;
end;

/**
 * 触发器 MES基础表
 */
create trigger TRI_DOWNLINE_SHIP_RECORD
before insert on T_DOWNLINE_SHIP_RECORD
for each row
begin
select SEQ_T_DOWNLINE_SHIP_RECORD.nextval into :new.id from dual;
end;

/**
 * 供应商物料对应关系触发器
 */
create or replace trigger TRI_SUPPLIER_PART
before insert on T_SUPPLIER_PART
for each row
begin
select SEQ_T_SUPPLIER_PART.nextval into :new.id from dual;
end;

/**
 * 新旧供应商对应关系触发器
 */
create or replace trigger TRI_SUPPLIER_REF
before insert on T_SUPPLIER_REF
for each row
begin
select SEQ_T_SUPPLIER_REF.nextval into :new.id from dual;
end;

/**
* 对产品下线发货记录进行刷新
*/
update T_DOWNLINE_SHIP_RECORD r set (r.part_type,r.product_type) = 
(
	select up.product_type_s ,up.mold_type_s   
    from part p ,uda_part up 
    where p.part_key = up.object_key 
    and p.part_number = r.part_number
)
   
/**
* 对于crm导入的缺失机型类别，产品型号进行刷新（根据mes）
*/
update T_MARKET_REPAIR_RECORD t set (t.part_number,t.part_name,t.part_type,t.product_type) = 
(
	select u.part_number as part_number,p.description as part_name,up.product_type_s as part_type,up.mold_type_s as product_type
	from unit u,part p,uda_part up
	where up.object_key=p.part_key
	and u.part_number=p.part_number
	and rownum = 1     
) where t.part_number is null

/**
* 产品安装信息表（CRM_UNIT_INSTALL）
*/
-- Create table
create table CRM_UNIT_INSTALL
(
  serial_number VARCHAR2(250),
  intall_date   DATE,
  service_order VARCHAR2(100),
  product_type  VARCHAR2(200),
  description   VARCHAR2(100),
  customer_id   VARCHAR2(50),
  part_id       VARCHAR2(50),
  region        VARCHAR2(100),
  create_time   DATE default sysdate not null
);
-- Create/Recreate indexes 
create index CUI_CUSTOMER_ID on CRM_UNIT_INSTALL (CUSTOMER_ID);

create index CUI_DESCRIPTION on CRM_UNIT_INSTALL (DESCRIPTION);

create index CUI_PART_ID on CRM_UNIT_INSTALL (PART_ID);

create index INDEX_INS_SERIAL on CRM_UNIT_INSTALL (SERIAL_NUMBER);

------------------------用户权限表构建---------------------------
CREATE SEQUENCE qms_sequence  
    INCREMENT BY 1  
    START WITH 1    
    NOMAXVALUE     
    NOCYCLE        
    CACHE 10; 
----用户组表----    
create table sys_group
(
  group_key           number(18) not null,
  group_code        varchar2(256) not null,
  group_name        varchar2(256) ,
  creation_time      date default sysdate ,
  last_modified_time date default sysdate
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16k
    next 8k
    minextents 1
    maxextents unlimited
  );
------- key trigger
create or replace trigger trigger_group 
before insert on sys_group for each row when (new.group_key is null)
begin
select qms_sequence.nextval into:new.group_key from dual;
end;  

-- create/recreate primary, unique and foreign key constraints 
alter table sys_group
  add constraint dpk_group primary key (group_key)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64k
    next 1m
    minextents 1
    maxextents unlimited
  );
alter table sys_group
  add constraint der_group unique (group_code)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64k
    next 1m
    minextents 1
    maxextents unlimited
  );
-- create/recreate indexes 
create index dixi_group_ct on sys_group (creation_time)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse;


-- sys_group_privilege ---
create table sys_group_privilege
(
  group_privilege_key number(18) not null,
  group_key                number(18) not null,
  privilege_key             number(18) not null,
  creation_time      date default sysdate ,
  last_modified_time date default sysdate
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16k
    next 8k
    minextents 1
    maxextents unlimited
  );
  ------- key trigger
  CREATE OR REPLACE TRIGGER TRIGGER_SYS_GROUP_PRIVILEGE
BEFORE INSERT ON SYS_GROUP_PRIVILEGE FOR EACH ROW WHEN (new.GROUP_PRIVILEGE_KEY is null)
begin
select qms_sequence.nextval into:new.GROUP_PRIVILEGE_KEY  from dual;
end;

alter table sys_group_privilege
  add constraint dpk_group_privilege primary key (group_privilege_key)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64k
    next 1m
    minextents 1
    maxextents unlimited
  );
  
    alter table sys_group_privilege
  add constraint der_group_privilege unique (group_key,privilege_key)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64k
    next 1m
    minextents 1
    maxextents unlimited
  );
-----------------------------------    
  create table sys_user_group
(

  user_group_key      number(18) not null,
  group_key           number(18) not null,
  user_key           number(18) not null,
  creation_time      date default sysdate ,
  last_modified_time date default sysdate
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16k
    next 8k
    minextents 1
    maxextents unlimited
  );
  
    create or replace trigger trigger_sys_user_group
before insert on sys_user_group for each row when (new.user_group_key is null)
begin
select qms_sequence.nextval into:new.user_group_key from dual;
end;

alter table sys_user_group
  add constraint dpk_user_group primary key (user_group_key)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64k
    next 1m
    minextents 1
    maxextents unlimited
  );
  
    alter table sys_user_group
  add constraint der_user_group unique (user_key,group_key)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64k
    next 1m
    minextents 1
    maxextents unlimited
  );  
  ---------------------
   create table sys_privilege
(
  privilege_key           number(18) not null,
  menu_name        varchar2(256) ,
  menu_code          varchar2(512) not null,
  operation_name        varchar2(256) ,
  operation_code        varchar2(256) ,
  creation_time      date default sysdate ,
  last_modified_time date default sysdate
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16k
    next 8k
    minextents 1
    maxextents unlimited
  );
  
  --key trigger
CREATE OR REPLACE TRIGGER TRIGGER_SYS_PRIVILEGE
BEFORE INSERT ON SYS_PRIVILEGE FOR EACH ROW WHEN (new.PRIVILEGE_KEY is null)
begin
select qms_sequence.nextval into:new.PRIVILEGE_KEY from dual;
end;

-- create/recreate primary, unique and foreign key constraints 
alter table sys_privilege
  add constraint dpk_privilege primary key (privilege_key)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64k
    next 1m
    minextents 1
    maxextents unlimited
  );
  
  alter table sys_privilege
  add constraint der_privilege unique (operation_code)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64k
    next 1m
    minextents 1
    maxextents unlimited
  );
  
  -- create/recreate indexes 
  create index dixi_privilege_ct on sys_privilege (creation_time)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse;
  alter table sys_privilege add (uda_0 varchar2(256));
  alter table sys_privilege add (uda_1 varchar2(256));
  alter table sys_privilege add (uda_2 varchar2(256)); 

/**
百台维修率录入表
*/
create table t_repair_rate_input(
id number(16) not null,
type_category varchar(256) null,        --机型类别
repair_month  varchar(256) null,        --维修月份
repair_rate   number(16,8) null,        --维修率
repair_total_rate   number(16,8) null,  --累计维修率
repaired_count  NUMBER(12) DEFAULT 0,   -- 维修数
ship_count  NUMBER(12) DEFAULT 0,		-- 发货数
create_user   varchar(256) null,        
create_time   date        default sysdate,
primary key(id)
)
alter table t_repair_rate_input  add constraint cate_month_PRIMARYKEY unique (type_category, repair_month);

CREATE OR REPLACE TRIGGER TRIGGER_repair_rate_input
BEFORE INSERT ON t_repair_rate_input FOR EACH ROW WHEN (new.id is null)
begin
select qms_sequence.nextval into:new.id from dual;
end;

/**
累计百台维修率录入表
**/
create table t_repair_totalrate_input(
id number(16) not null,
type_category varchar(256) null,        --机型类别
repair_rate   number(16,8) null,        --累计维修率
insert_month varchar(256)  null,        --月份
create_user   varchar(256) null,        
create_time   date        default sysdate,
primary key(id)
)
CREATE OR REPLACE TRIGGER TRIGGER_repair_totalrate_input
BEFORE INSERT ON t_repair_totalrate_input FOR EACH ROW WHEN (new.id is null)
begin
select qms_sequence.nextval into:new.id from dual;
end;
/**
记录最新统计月份
*/
create table t_later_sumtime(
id number(16) not null,
sum_month varchar(256)  not null,    --统计月份
create_user   varchar(256) null,        
create_time   date        default sysdate,
primary key(id)
)

CREATE OR REPLACE TRIGGER TRIGGER_t_later_sumtime
BEFORE INSERT ON t_later_sumtime FOR EACH ROW WHEN (new.id is null)
begin
select qms_sequence.nextval into:new.id from dual;
end;

/**
创建市场物料维修汇总表（市场不良趋势图使用）
*/
create table T_MARKET_REPAIR_PART_SUM
(
  repair_date         VARCHAR2(20),
  product_type        VARCHAR2(20),
  is_over             VARCHAR2(20),
  region              VARCHAR2(50),
  fault_type_code     VARCHAR2(50),
  fault_reason_code   VARCHAR2(50),
  part_number         VARCHAR2(50),
  mes_part_number     VARCHAR2(50),
  supplier_number     VARCHAR2(50),
  supplier_name       VARCHAR2(100),
  supplier_short_name VARCHAR2(100),
  qty                 NUMBER
);
--Add comments to the table 
comment on table T_MARKET_REPAIR_PART_SUM
  is '市场物料维修汇总表';
-- Add comments to the columns 
comment on column T_MARKET_REPAIR_PART_SUM.repair_date
  is '维修时间';
comment on column T_MARKET_REPAIR_PART_SUM.product_type
  is '机型类别';
comment on column T_MARKET_REPAIR_PART_SUM.is_over
  is '是否百台';
comment on column T_MARKET_REPAIR_PART_SUM.region
  is '服务中心';
comment on column T_MARKET_REPAIR_PART_SUM.fault_type_code
  is '故障大类代码';
comment on column T_MARKET_REPAIR_PART_SUM.fault_reason_code
  is '故障小类代码';
comment on column T_MARKET_REPAIR_PART_SUM.part_number
  is 'CRM物料号';
comment on column T_MARKET_REPAIR_PART_SUM.mes_part_number
  is 'MES物料号';
comment on column T_MARKET_REPAIR_PART_SUM.supplier_number
  is '供应商代号';
comment on column T_MARKET_REPAIR_PART_SUM.supplier_name
  is '供应商名称';
comment on column T_MARKET_REPAIR_PART_SUM.supplier_short_name
  is '供应商简称';
comment on column T_MARKET_REPAIR_PART_SUM.qty
  is '数量';

/**
创建关键件入库汇总表（市场不良趋势图使用）
*/
create table T_DOWNLINE_PART_SUM
(
  product_type        VARCHAR2(50),
  product_family      VARCHAR2(50),
  product_qms_type    VARCHAR2(50),
  part_number         VARCHAR2(50),
  supplier_number     VARCHAR2(50),
  supplier_name       VARCHAR2(100),
  supplier_short_name VARCHAR2(100),
  downline_time       VARCHAR2(20),
  qty                 NUMBER
);
-- Add comments to the table 
comment on table T_DOWNLINE_PART_SUM
  is '关键件入库汇总';
-- Add comments to the columns 
comment on column T_DOWNLINE_PART_SUM.product_type
  is '机型类别';
comment on column T_DOWNLINE_PART_SUM.product_family
  is '产品系列';
comment on column T_DOWNLINE_PART_SUM.product_qms_type
  is '产品型号';
comment on column T_DOWNLINE_PART_SUM.part_number
  is '物料号';
comment on column T_DOWNLINE_PART_SUM.supplier_number
  is '供应商代号';
comment on column T_DOWNLINE_PART_SUM.supplier_name
  is '供应商名称';
comment on column T_DOWNLINE_PART_SUM.supplier_short_name
  is '供应商简称';
comment on column T_DOWNLINE_PART_SUM.downline_time
  is '入库时间';
comment on column T_DOWNLINE_PART_SUM.qty
  is '数量';