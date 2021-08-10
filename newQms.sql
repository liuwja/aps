CREATE SEQUENCE bph_sequence  
    INCREMENT BY 1  --  
    START WITH 1    --
    NOMAXVALUE      --  
    NOCYCLE         --  
    CACHE 10; 

/**
班组类别表
*/
create table t_group_category(
gc_key   number(12)  not null,
factory varchar2(256),
area    varchar2(256),
category varchar2(256),
primary key(gc_key)
)

CREATE OR REPLACE TRIGGER TRIGGER_group_category
BEFORE INSERT ON t_group_category FOR EACH ROW WHEN (new.gc_key is null)
begin
select bph_sequence.nextval into:new.gc_key  from dual;
end;

    
/**
 *考核项目设定表
 */
create table T_ITEM(
item_key NUMBER(12) NOT NULL,
gc_key NUMBER(12) NULL,               --班组类别key
item_code VARCHAR(256) NULL,          --项目代码
item_name VARCHAR(256) NULL,          --项目名称
create_user  VARCHAR(128) NULL,
create_time DATE DEFAULT SYSDATE  NULL,
last_update_user  VARCHAR(128) NULL,
last_update_time DATE NULL,
PRIMARY KEY (item_key) 
 )
 
 --KEY TRIGGER
CREATE OR REPLACE TRIGGER TRIGGER_ITEM 
BEFORE INSERT ON T_ITEM FOR EACH ROW WHEN (new.item_key is null)
begin
select bph_sequence.nextval into:new.item_key from dual;
end;  
--Create/Recreate indexes 
create index DIXI_ITEM_CT on T_ITEM (create_time)
 tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 16k
    next 8k
    minextents 1
    maxextents unlimited
  );


  /**
 *考核指标设定表
 */
create table T_INDEX(
index_key NUMBER(12) NOT NULL,    
item_key number(12) null,             --项目key
index_name VARCHAR(256) NULL,         --指标名称
index_code VARCHAR(128) NULL,         --指标代码
index_description VARCHAR(512) NULL,  --指标描述
mainKey varchar(128),                 --是否关键指标
create_user  VARCHAR(128) NULL,
create_time DATE DEFAULT SYSDATE  NULL,
last_update_user  VARCHAR(128) NULL,
last_update_time DATE NULL,
PRIMARY KEY (index_key) 
 )
 
--KEY TRIGGER
CREATE OR REPLACE TRIGGER TRIGGER_INDEX 
BEFORE INSERT ON T_INDEX FOR EACH ROW WHEN (new.index_key is null)
begin
select bph_sequence.nextval into:new.index_key from dual;
end;  

create index DIXI_INDEX_CT on T_INDEX (create_time)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 16k
    next 8k
    minextents 1
    maxextents unlimited
  );


/**
 *月度考核基准目标设定表
 */

create table T_MONTHLY_ASSESSMENT(
ma_key NUMBER(12) NOT NULL,
index_key NUMBER(12) null,              --考核指标key
group_key number(12) null,              --班组key
monthly VARCHAR(128) NULL,              --月份
item_scale number(12,4) null,           --项目比例
index_scale NUMBER(12,4) NULL,          --指标比例  
index_mainkey varchar(128) NULL,        --是否主键
base_value NUMBER(16,8) NULL,           --基准值
target_value NUMBER(16,8) NULL,         --目标值
create_user  VARCHAR(128) NULL,
create_time DATE DEFAULT SYSDATE  NULL,
last_update_user  VARCHAR(128) NULL,
last_update_time DATE NULL,
PRIMARY KEY (ma_key) 
)

--KEY TRIGGER
CREATE OR REPLACE TRIGGER TRIGGER_MONTHLY_ASSESSMENT 
BEFORE INSERT ON T_MONTHLY_ASSESSMENT FOR EACH ROW WHEN (new.ma_key is null)
begin
select bph_sequence.nextval into:new.ma_key from dual;
end;  

create index DIXI_MONTHLY_ASSESSMENT_CT on T_MONTHLY_ASSESSMENT (create_time)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 16k
    next 8k
    minextents 1
    maxextents unlimited
  );
  


  
/**
班组表  
**/
create table t_group(
group_key number(18) not null,
category_key number(18) null,
old_key     number(18) null,
factory     varchar(256),
area        varchar(256),
group_category   varchar(256),
group_name       varchar(256),
group_code       varchar(256),
group_description  varchar(256),
production_line    varchar(256),
primary key(group_key)
)

CREATE OR REPLACE TRIGGER TRIGGER_group 
BEFORE INSERT ON t_group FOR EACH ROW WHEN (new.group_key is null)
begin
select bph_sequence.nextval into:new.group_key from dual;
end;  
/**
	组装在线更换表（中间表）
**/
create table t_replace_part(
  R_id  number(12)  not null,	--id
  ModelType varchar2(256),	--机型类别
  replaceTime  DATE,	--更换时间
  account_number varchar2(256),		--供应商编号
  account_name varchar2(256),	--供应商名称
  account_Abbreviation varchar2(256),	--供应商简称
  part_number varchar2(256),	--物料编号
  part_name varchar2(256),	--物料名称
  part_class varchar2(256),		--物料分类
  part_level varchar2(256),		--物料级别
  Maturity varchar2(256),	--产品成熟度
  bindingtime DATE,		--绑定时间
  defect_name varchar2(256),	--不良现象
  line_s varchar2(256),	--产品线
  product_name VARCHAR(256) NULL,		--零部件名称
  product_number VARCHAR(256) NULL,	--零部件编号
  ware_house VARCHAR(256) NULL,      --仓库
  account_key VARCHAR(256) NULL,  	--供应商key
  part_key VARCHAR(256) NULL,		--物料key
  consumption_type VARCHAR(256) NULL,	--关键件标示
  account_number_n VARCHAR(256) NULL,	--新供应商编号
  new_part_number VARCHAR(256) NULL,	--新物料编号
  serial_number VARCHAR(256) NULL,      --主机条码
  material_old varchar2(256) null,      --旧物料条码
  material_new varchar2(256) null,      --新物料条码
  PRIMARY KEY (R_id) 
)

CREATE OR REPLACE TRIGGER TRIGGER_replace_part
BEFORE INSERT ON t_replace_part FOR EACH ROW WHEN (new.R_id is null)
begin
select bph_sequence.nextval into:new.R_id  from dual;
end;
 
/*
关键件汇总
*/
/**
create table t_keypartsum(
       R_id  number(12)  not null,  --id
       creation_time_u DATE,	--创建时间
       supplier_number_n varchar2(256),	--供应商编号
       part_number varchar2(256),	--物料编号
	   supplier_code varchar2(256),	--供应商代号
       PRIMARY KEY (R_id) 
)
*/
create table t_keypartsum(
       R_id  number(12)  not null,  --id
       creation_time_u varchar2(256),	--创建时间
       supplier_number_n varchar2(256),	--供应商编号
       part_number varchar2(256),	--物料编号
	   supplier_code varchar2(256),	--供应商代号
       PRIMARY KEY (R_id) 
)
CREATE OR REPLACE TRIGGER TRIGGER_t_keypartsum
BEFORE INSERT ON t_keypartsum FOR EACH ROW WHEN (new.R_id is null)
begin
select bph_sequence.nextval into:new.R_id  from dual;
end;

  create index DIXI_keypartsum_ct on t_keypartsum (creation_time_u)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 16k
    next 8k
    minextents 1
    maxextents unlimited
  );
 create index DIXI_keypartsum_snn on t_keypartsum (supplier_number_n)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 16k
    next 8k
    minextents 1
    maxextents unlimited
  );
  create index DIXI_keypartsum_pn on t_keypartsum (part_number)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 16k
    next 8k
    minextents 1
    maxextents unlimited
  );
  create index DIXI_keypartsum_sc on t_keypartsum (supplier_code)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 16k
    next 8k
    minextents 1
    maxextents unlimited
  );
/**
mes退次汇总表
*/

create table t_return_ware(
       R_id  number(12)  not null,  --id
       line_s varchar2(256),  --产线
       account_key varchar2(256),  --供应商key
       supplier_number varchar2(256),  --供应商编号
       description_sup varchar2(256),  --供应商名称
       is_new_s varchar2(256),  --成品成熟度 
       part_level_s varchar2(256),  --物料级别
       part_key varchar2(256),  --物料key
       part_number varchar2(256),  --物料编号
       description_part varchar2(256),  --物料名称
       consumption_type varchar2(256),  --是否关键件标示
       date_t DATE,  --时间
       defect_s varchar2(256),  --不良现象
       defect_qty_i varchar2(256),  --不良数
       total_qty_i varchar2(256),  --不良总数
       PRIMARY KEY (R_id)
       
)
CREATE OR REPLACE TRIGGER TRIGGER_t_return_ware
BEFORE INSERT ON t_return_ware FOR EACH ROW WHEN (new.R_id is null)
begin
select bph_sequence.nextval into:new.R_id  from dual;
end;
/**插入组装在线退次（中间表）
**/
insert into t_return_ware (line_s,account_key,supplier_number,description_sup,is_new_s,part_level_s,part_key,part_number,description_part,consumption_type,
date_t,defect_s,defect_qty_i,total_qty_i) 
select a.line_s,b.account_key,b.supplier_number,b.description as description_sup,d.is_new_s,d.part_level_s,c.part_key,c.part_number,
c.description as description_part,c.consumption_type,a.date_t,a.defect_s,defect_qty_i,a.total_qty_i
from at_assemblyproductback a left join (select t_a.account_key,NVL(t_b.supplier_number_n,t_a.account_name) as supplier_number,t_a.description from 
account t_a left join t_supplier_ref t_b on t_a.account_name=t_b.supplier_number ) b on a.supplier_number_s=b.supplier_number left join (select t_1.part_key,
NVL(t_2.new_part_number,t_1.part_number) as part_number,t_1.description,t_1.consumption_type from part t_1 left join new_part_ref t_2 on 
t_1.part_number=t_2.old_part_number) c on a.item_number_s=c.part_number left join uda_part d on c.part_key=d.object_key
where a.date_t between to_date('2010-01-01','yyyy-mm-dd') and to_date('2013-12-31','yyyy-mm-dd')
 /**插入组装在线更换表（中间表）
 */
insert into t_replace_part(modeltype,account_number,account_name,account_Abbreviation,part_number,part_name,ware_house,
part_class,part_level,Maturity,bindingtime,defect_name,replaceTime,consumption_type,account_number_n,new_part_number) 
select ud.mold_type_s,ac.account_name,ac.description,nvl(ac.uda_3,ac.description),p.part_number,p.description,p.uda_2,p.uda_0,ud.part_level_s,
ud.is_new_s ,cp.creation_time,dr.defect_comment,dr.creation_time,p.consumption_type,NVL(ac.supplier_number_n,ac.account_name),
NVL(p.new_part_number,p.part_number) from (select a.account_name,a.description,a.uda_3,b.supplier_number_n from account a 
left join t_supplier_ref b on a.account_name=b.supplier_number) ac, defect_repair_entry dr, (select pp.part_key,pp.part_number,
pp.description,pp.uda_2,pp.uda_0,nn.new_part_number,pp.consumption_type from part pp left join new_part_ref nn on 
pp.part_number=nn.old_part_number) p,uda_part ud, unit u,consumed_part@mes_test_link cp,test_instance t where 
u.unit_key = cp.tobj_key and cp.part_number = p.part_number and p.part_key = ud.object_key and t.object_key = 
u.unit_key and t.test_instance_key = dr.test_instance_key and dr.uda_4 = ac.account_name and dr.defect_user_name = '供应商' 
and t.test_valid='1' and t.location='在线检验'and cp.creation_time between to_date('2010-01-01','yyyy-mm-dd') and to_date('2013-12-31','yyyy-mm-dd')

-----或者----
insert into t_replace_part (serial_number, modeltype, new_part_number, supplier_code,
 material_old, material_new, defect_code, defect_name, replaceTime)select u.serial_number,up.mold_type_s,
 substr(at.material_old_s, 0, length(at.material_old_s) - 8) as part_number,
 substr(at.material_old_s, length(at.material_old_s)-7, 1) as supplier_code,at.material_old_s,at.material_new_s,
 dr.defect_code,dr.defect_comment,dr.udt_1 from at_unitchangematerialrecord at,unit u ,
 test_instance ti,defect_repair_entry dr, part p, uda_part up where 1=1 
 and at.serial_number_s = u.serial_number and u.unit_key = ti.object_key 
 and dr.test_instance_key = ti.test_instance_key and u.part_number=p.part_number 
 and p.part_key = up.object_key 
 and to_char(at.creation_time,'yyyy-mm-dd hh24:mi:ss') <= to_char(dr.udt_1+(2/24/60/60),'yyyy-mm-dd hh24:mi:ss') 
 and to_char(at.creation_time,'yyyy-mm-dd hh24:mi:ss') >= to_char(dr.udt_1-(2/24/60/60),'yyyy-mm-dd hh24:mi:ss') a
 nd to_char(dr.udt_1,'yyyy-mm-dd') between '2016-12-21' and '2016-12-23' 
 
update t_replace_part t set new_part_number = (
select new_part_number from new_part_ref n where t.new_part_number = n.old_part_number) 
where t.new_part_number in (select old_part_number from new_part_ref) 

update t_replace_part t set (account_number_n, account_name, account_abbreviation) = (
select supplier_number_n, supplier_name, supplier_short_name from t_supplier_part s 
where t.new_part_number = s.part_number and t.supplier_code = s.supplier_code) 
where t.new_part_number in (select part_number from t_supplier_part) 
and t.supplier_code in (select supplier_code from t_supplier_part) 

update t_replace_part t set (ware_house, part_class, part_level, maturity, consumption_type, part_name) = (
select p.uda_2, p.uda_0, up.part_level_s, up.is_new_s, p.consumption_type, p.description 
from part p, uda_part up where p.part_key = up.object_key 
and t.new_part_number = p.part_number)
where t.new_part_number in (select part_number from part) 
   
 /**
	视图
 **/
create or replace view vw_findbad as
select S.DATE_T,s.factory_s,s.area_s,s.group_s,S.DUTY_S,S.ITEM_NAME_S,S.PRODUCT_TYPE_S,s.defect_s,s.type_s,s.defect_qty,m.total_qty,total_qty2,line_s from
  (select * from (

    select DATE_T,FACTORY_S,area_s,DUTY_S,ITEM_NAME_S,PRODUCT_TYPE_S,DUTY_GROUP1_S group_s, defect_s ,DEFECT_QTY_I DEFECT_QTY,type_s,line_s
    from AT_ASSEMBLYPRODUCTBACK

    union all

    select DATE_T,FACTORY_S,area_s,DUTY_S,ITEM_NAME_S,PRODUCT_TYPE_S,DUTY_GROUP2_S group_s, defect_s,DEFECT_QTY_I DEFECT_QTY,type_s,line_s
    from AT_ASSEMBLYPRODUCTBACK

    union all

    select DATE_T,FACTORY_S,area_s,DUTY_S,ITEM_NAME_S,PRODUCT_TYPE_S,DUTY_GROUP3_S group_s,defect_s,DEFECT_QTY_I DEFECT_QTY,type_s,line_s
    from AT_ASSEMBLYPRODUCTBACK

  ) where group_s is not null) s left join (select m.factory, m.shift_group_name ,sum(m.col_2) total_qty,sum(m.col_23) total_qty2 from t_mes_data_sum_day m group by  m.factory, m.shift_group_name ) m
  on s.factory_s = m.factory and s.group_s = m.shift_group_name;

    --------插入关键件表-----	
 --   insert into t_keypartsum(creation_time_u,supplier_number_n,part_number,supplier_code) 
 --   select a.creation_time_u,b.supplier_number_n,b.part_number,supplier_code from CONSUMED_PART@MES_TEST_LINK a inner join 
 --   T_SUPPLIER_PART b on a.part_number=b.part_number where substr(a.part_serial, -8,1)=supplier_code and to_char(a.creation_time_u,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-MM-dd')
	insert into t_keypartsum(creation_time_u,supplier_number_n,part_number,supplier_code) 
	select to_char(a.creation_time_u,'yyyy-mm-dd'),nvl(b.supplier_number_n,''),nvl(b.part_number,a.part_number),nvl(b.supplier_code,substr(a.part_serial, -8,1))
	from  CONSUMED_PART@MES_TEST_LINK a,T_SUPPLIER_PART b
	where a.part_number=b.part_number(+)
	and substr(a.part_serial, -8,1)=supplier_code(+)
	and to_char(a.creation_time_u,'yyyy-MM')='2016-10' 