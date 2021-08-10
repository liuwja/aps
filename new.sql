/**
 *班组设定表
 *字段：工厂，车间，班组编号，班组名称，班组类别 创建人 创建时间 更新人 更新时间
 */
/**
create table T_shiftGroup(
id NUMBER(12) NOT NULL,
factory  VARCHAR(128) NULL,
area VARCHAR(128) NULL,
code  VARCHAR(128) NULL,
name  VARCHAR(256) NULL,
category VARCHAR(128) NULL,
create_user  VARCHAR(128) NULL,
create_time DATE DEFAULT SYSDATE  NULL,
last_update_user  VARCHAR(128) NULL,
last_update_time DATE NULL,
PRIMARY KEY (id) 
 );
*/ 
 /**
 *考核项目设定表
 *字段：工厂，班组类别，项目代码，考核项目，比例  创建人 创建时间 更新人 更新时间
 */
 /**
create table T_CHECK_ITEM(
id NUMBER(12) NOT NULL,
factory  VARCHAR(128) NULL,
shiftGroup_category  VARCHAR(256) NULL,
item_code VARCHAR(256) NULL,
item VARCHAR(256) NULL,
scale NUMBER(12,4)  NULL,
create_user  VARCHAR(128) NULL,
create_time DATE DEFAULT SYSDATE  NULL,
last_update_user  VARCHAR(128) NULL,
last_update_time DATE NULL,
PRIMARY KEY (id) 
 )
*/ 
 
 
 /**
 *考核指标设定表
 *字段：工厂，车间，班组类别，考核项目，考核指标，比例，是否关键指标，基准类，目标值 创建人 创建时间 更新人 更新时间
 */
 /**
create table T_CHECK_INDEX(
id NUMBER(12) NOT NULL,
factory  VARCHAR(128) NULL,
area  VARCHAR(128) NULL,
shiftGroup_category  VARCHAR(256) NULL,
check_item VARCHAR(256) NULL,
check_index VARCHAR(256) NULL,
index_code VARCHAR(128) NULL,
index_description VARCHAR(256) NULL,
scale Number(12,4)  NULL,
mykey varchar(128),
base_value NUMBER(16,8),
target_value NUMBER(16,8),
create_user  VARCHAR(128) NULL,
create_time DATE DEFAULT SYSDATE  NULL,
last_update_user  VARCHAR(128) NULL,
last_update_time DATE NULL,
item_scale NUMBER(12) NULL,
col_1 VARCHAR(256) NULL,  --备用字段1
col_2 VARCHAR(256) NULL,  --备用字段2
col_3 VARCHAR(256) NULL,  --备用字段3
PRIMARY KEY (id) 
 )
 
create sequence SEQ_T_SHIFTGROUP increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_CHECK_ITEM increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_CHECK_INDEX increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
*/
/**
 *月度考核基准目标设定表
 *字段：工厂，车间，月份，班组类别，考核项目，考核指标，比例，是否关键指标，基准类，目标值 创建人 创建时间 更新人 更新时间
 */
/**
create table T_SETTING_MONTHLY_ASSESSMENT(
id NUMBER(12) NOT NULL,
factory  VARCHAR(128) NULL,
area  VARCHAR(128) NULL,
month VARCHAR(128) NULL,
shiftGroup_category  VARCHAR(256) NULL,
check_item VARCHAR(256) NULL,
check_index VARCHAR(256) NULL,
scale NUMBER(12,4) NULL,
mykey varchar(128) NULL,
base_value NUMBER(16,8) NULL,
target_value NUMBER(16,8) NULL,
create_user  VARCHAR(128) NULL,
create_time DATE DEFAULT SYSDATE  NULL,
last_update_user  VARCHAR(128) NULL,
last_update_time DATE NULL,
item_scale number(12,4) null,
index_code VARCHAR(128) null,
index_id   number(12) null,
col_1 VARCHAR(256) NULL,  --备用字段1
col_2 VARCHAR(256) NULL,  --备用字段2
col_3 VARCHAR(256) NULL,  --备用字段3
PRIMARY KEY (id) 
)
*/

/**
 *生产退次指定型号设定表
 *字段：工厂，车间，班组类别，物料标号，物料名称 创建人 创建时间 更新人 更新时间
 */
/*
create table T_SETTING_PRODUCTION_RETURN(
id NUMBER(12) NOT NULL,
factory  VARCHAR(128) NULL,
area  VARCHAR(128) NULL,
shiftGroup_category  VARCHAR(256) NULL,
material_tag NUMBER(12)　NULL,
material_name VARCHAR(256) NULL,
create_user  VARCHAR(128) NULL,
create_time DATE DEFAULT SYSDATE  NULL,
last_update_user  VARCHAR(128) NULL,
last_update_time DATE NULL,
PRIMARY KEY (id) 
)

create sequence SEQ_T_SEEEING_MONTHLY increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_SETTING_PRODUCTION increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
*/
/**
QPRN设定表
字段：qprn，得分类型，权重，得分
**/
create table T_QPRN_SETTING(
id NUMBER(12) NOT NULL,
qprn  VARCHAR(128) NULL,
score_type  NUMBER(1) NULL,
weight NUMBER(5,2) NULL,
score NUMBER(5,2)  NULL,
create_user  VARCHAR(128) NULL,
create_time DATE DEFAULT SYSDATE  NULL,
last_update_user  VARCHAR(128) NULL,
last_update_time DATE NULL,
PRIMARY KEY (id) 
 )
 
/**
过程分数设定表
字段：工厂，指标内容，编号，得分类型，分数
**/
create table T_PROCESS_SCORE_SETTING(
id NUMBER(12) NOT NULL,
factory  VARCHAR(128) NULL,
index_content  VARCHAR(128) NULL,
content varchar(128) NULL,
index_code  VARCHAR(128) NULL,
score_type NUMBER(5,2) NULL,
score NUMBER(5,2)  NULL,
create_user  VARCHAR(128) NULL,
create_time DATE DEFAULT SYSDATE  NULL,
last_update_user  VARCHAR(128) NULL,
last_update_time DATE NULL,
PRIMARY KEY (id) 
 )
create sequence SEQ_T_QPRN_SETTING increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
create sequence SEQ_T_PRC_SCORE_SETTING increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;

/**
    组装生产退次表(月份)
   字段：工厂 车间 班组 月份 月份责任班组退次数（不良数） 月度组装投产数
**/
/**
create table AT_ASSEMBLYPRODUCTBACK_SUM(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
return_num NUMBER(12) NULL,
commit_num NUMBER(12) NULL
)
create sequence SEQ_AT_ASSEMBLYPRODUCTBACK_SUM increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
*/
/**
前工序FQC查检表(月份)
字段：工厂 车间 班组 月份 月份责任班组不良数量  总数  抽样数
**/
/**
create table AT_FORMERPROCESSFQCCHECK_SUM(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
defect_qty NUMBER(12) NULL,
total_qty NUMBER(12) NULL,
simple_qty NUMBER(12) NULL
)

create sequence SEQ_AT_FORMERPROCESSFQC_SUM increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
*/
/**
过程批量不良记录总和表（月份）
字段： 工厂 车间 班组 月份 质量风险分数之和 ,发生流程节点
**/
/**
create table AT_BATCHDEFECTRECORD_SUM(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
risk_score NUMBER(12,2) NULL,
process_node VARCHAR(256) NULL

)

create sequence SEQ_AT_BATCHDEFECTR_SUM increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
*/

/**
过程审核单总和表（月份）
字段： 工厂 车间 班组 月份 审核内容   月度责任班组审核次数
**/
/**
create table AT_PROCESSAUDITRECORD_SUM(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
check_content VARCHAR(128),
audit_num NUMBER(12) NULL					
)

create sequence SEQ_AT_PROCESSAUDIT_SUM increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
*/
/**
质量改善课题申报总和（月份）
字段： 工厂 车间 班组 月份  月度责任班组最总评分
**/
/**
create table AT_QUALITYIMPROVEMENTRFP_SUM(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
total_score NUMBER(20) NULL
)
create sequence SEQ_QUALITYIMPROVE_SUM increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
*/
/**
冲压质量日报表总和（月份）
字段： 工厂 车间 班组 月份  冲压不良数量  冲压入库数总和（当月）
**/
/**
create table AT_STAMPINGDAILYREPORT_SUM(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
defect_qty NUMBER(20) NULL,
total_qty NUMBER(20) NULL
)

create sequence SEQ_STAMPINGDAILY_SUM increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
*/

/**
喷涂质量日报表总和（月份）
字段： 工厂 车间 班组 月份  合格数  挂件数
**/
/**
create table AT_PAINTINGDAILYREPORT_SUM(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
quality_qty NUMBER(20) NULL,
defect_qty NUMBER(20) NULL
)
create sequence SEQ_PAINTINGDAILY_SUM increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
*/
/**
市场开箱不良记录日报总和表（月份）
字段： 工厂  班组 月份  不良来源  不良次数
**/
/**
create table AT_BOXDEFECTRECORD_SUM(
factory varchar(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
defect_source VARCHAR(256) NULL,
defect_num NUMBER(15) NULL
)

create sequence SEQ_BOXDEFECTRE_SUM increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;
*/
/**
mes数据汇总(月)
**/
create table T_MES_DATA_SUM_MONTH(
factory varchar(128) NULL,
area varchar(128) NULL,
shift_group_code VARCHAR(256) NULL, -- 班组编号
shift_group_name VARCHAR(256) NULL, --班组名称
sum_date date NULL,                 --统计时间
group_type VARCHAR(128) NULL,       --班组类别
col_1 NUMBER(15) NULL, -- 月度责任班组的退次数（不良数）(组装退次表)
col_2 NUMBER(15) NULL, -- 月度组装投产数                              (组装退次表)
col_3 NUMBER(15) NULL, --   月度责任班组的不良频次           (FQC抽检表)           
col_4 NUMBER(15) NULL, --   月度责任班组的抽检频次            (FQC抽检表) 
col_5 NUMBER(15) NULL, --   月度责任班组巡检不良批次数  （IPQC巡检表）
col_6 NUMBER(15) NULL, --   月度责任班组巡检总批次数    （IPQC巡检表）
col_7 NUMBER(15,2) NULL, --   发生流程节点为A/B/C的质量风险分数  （IPQC巡检表）              /****************修改****/                    
col_8 NUMBER(15,2) NULL, --   发生流程节点为D的次数      （IPQC巡检表）                                /****************修改****/  
col_9 NUMBER(15,2) NULL, -- 月度责任班组附加分数（质量改善重要提案表）
col_10 NUMBER(15) NULL, --  冲压不良数量之和              （冲压质量日报表）
col_11 NUMBER(15) NULL, --  冲压入库数之和                  （冲压质量日报表）
col_12 NUMBER(15) NULL, --  月度指定物料清单的下件合格数    （喷涂质量日报表）
col_13 NUMBER(15) NULL, --  月度（指定物料清单的）挂件数    （喷涂质量日报表）
col_14 VARCHAR(256) NULL, --备用字段                                    
col_15 NUMBER(15) NULL, --  喷涂退次数                                    （喷涂退次表）                                      
col_16 NUMBER(15) NULL, --  不良台数                                       （OQC抽检表）
col_17 NUMBER(15) NULL, --  组装停线数
col_18 NUMBER(15) NULL, --  组装维修数                                   （组装维修日报表）
col_19 NUMBER(15) NULL, --  开箱不良次数        （市场开箱不良记录表）
col_20 NUMBER(15) NULL, --  流行性不良次数   （市场开箱不良记录表）
col_21 NUMBER(15) NULL, --  精加工不良数        （精加工直通率）
col_22 NUMBER(15) NULL, --  精加工抽检总数     （精加工直通率）
col_23 NUMBER(15) NULL,  -- 喷涂总数                  （喷涂退次表）
col_24 VARCHAR(256) NULL, -- 审核类型
col_25 number(12,4) null, --发生流程节点为A/B/C的质量风险系数之和   ( 5M1E不良记录表)
col_26 number(12,4) null, --发生流程节点为D的质量风险系数之和 ( 5M1E不良记录表)
col_27 NUMBER(15) NULL, -- 公司外审不符合次数（过程审核记录表）
col_28 NUMBER(15) NULL, -- 公司内审不符合 次数（过程审核记录表）
col_29 NUMBER(15) NULL,  --系统内审不符合次数（过程审核记录表）
col_30 NUMBER(15) NULL, -- 巡检5M1E次数（过程审核记录表）
col_31 NUMBER(15) null, --工艺纪律检查次数   （过程审核记录表）
col_32 NUMBER(15) null, --盲点测试 次数（过程审核记录表）
insert_time        DATE,
last_update_time   DATE,
need_sum NUMBER(1) NULL, -- 是否需要重新汇总
PRIMARY KEY (factory, area, shift_group_code, sum_date) 
)

/**
mes数据汇总(天)
**/
create table T_MES_DATA_SUM_DAY(
factory varchar(128) NULL,
area varchar(128) NULL,
shift_group_code VARCHAR(256) NULL, -- 班组编号
shift_group_name VARCHAR(256) NULL,
sum_date date NULL,
group_type VARCHAR(128) NULL,
col_1 NUMBER(15) NULL, -- 月度责任班组的退次数（不良数）(组装退次表)
col_2 NUMBER(15) NULL, -- 月度组装投产数                              (组装退次表)
col_3 NUMBER(15) NULL, --   月度责任班组的不良频次           (FQC抽检表)           
col_4 NUMBER(15) NULL, --   月度责任班组的抽检频次            (FQC抽检表) 
col_5 NUMBER(15) NULL, --   月度责任班组巡检不良批次数  （IPQC巡检表）
col_6 NUMBER(15) NULL, --   月度责任班组巡检总批次数    （IPQC巡检表）
col_7 NUMBER(15,2) NULL, --   发生流程节点为A/B/C的质量风险分数  （IPQC巡检表）              /****************修改****/                    
col_8 NUMBER(15,2) NULL, --   发生流程节点为D的次数      （IPQC巡检表）                                /****************修改****/  
col_9 NUMBER(15,2) NULL, -- 月度责任班组附加分数（质量改善重要提案表）
col_10 NUMBER(15) NULL, --  冲压不良数量之和              （冲压质量日报表）
col_11 NUMBER(15) NULL, --  冲压入库数之和                  （冲压质量日报表）
col_12 NUMBER(15) NULL, --  月度指定物料清单的下件合格数    （喷涂质量日报表）
col_13 NUMBER(15) NULL, --  月度（指定物料清单的）挂件数    （喷涂质量日报表）
col_14 VARCHAR(256) NULL, --备用字段                                    
col_15 NUMBER(15) NULL, --  喷涂退次数                                    （喷涂退次表）                                      
col_16 NUMBER(15) NULL, --  不良台数                                       （OQC抽检表）
col_17 NUMBER(15) NULL, --  组装停线数
col_18 NUMBER(15) NULL, --  组装维修数                                   （组装维修日报表）
col_19 NUMBER(15) NULL, --  开箱不良次数        （市场开箱不良记录表）
col_20 NUMBER(15) NULL, --  流行性不良次数   （市场开箱不良记录表）
col_21 NUMBER(15) NULL, --  精加工不良数        （精加工直通率）
col_22 NUMBER(15) NULL, --  精加工抽检总数     （精加工直通率）
col_23 NUMBER(15) NULL,  -- 喷涂总数                  （喷涂退次表）
col_24 VARCHAR(256) NULL, -- 审核类型
col_25 number(12,4) null, --发生流程节点为A/B/C的质量风险系数之和   ( 5M1E不良记录表)
col_26 number(12,4) null, --发生流程节点为D的质量风险系数之和 ( 5M1E不良记录表)
col_27 NUMBER(15) NULL, -- 公司外审不符合次数（过程审核记录表）
col_28 NUMBER(15) NULL, -- 公司内审不符合 次数（过程审核记录表）
col_29 NUMBER(15) NULL,  --系统内审不符合次数（过程审核记录表）
col_30 NUMBER(15) NULL, -- 巡检5M1E次数（过程审核记录表）
col_31 NUMBER(15) null, --工艺纪律检查次数   （过程审核记录表）
col_32 NUMBER(15) null, --盲点测试 次数（过程审核记录表）
insert_time        DATE,
last_update_time   DATE,
need_sum NUMBER(1) NULL, -- 是否需要重新汇总
PRIMARY KEY (factory, area, shift_group_code, sum_date) 
)
/**
项目得分
**/
create table t_item_scroe(
factory varchar(128) NULL,
area varchar(128) NULL,
shift_group_code VARCHAR(256) NULL, -- 班组编号
shift_group_name VARCHAR(256) NULL,
sum_date date NULL,
item_id NUMBER(15) NULL,
item_score NUMBER(15,3) NULL,
insert_time        DATE,
last_update_time   DATE,
PRIMARY KEY (factory, area, shift_group_code, sum_date, item_id) 
)

/**
项目指标得分(月)
**/
create table t_item_index_scroe_month(
factory varchar(128) NULL,
area varchar(128) NULL,
shift_group_code VARCHAR(256) NULL, -- 班组编号
shift_group_name VARCHAR(256) NULL,
sum_date date NULL,
item_id NUMBER(15) NULL,
index_id  NUMBER(15) NULL,   --指标id
index_act_value NUMBER(15,3) NULL, -- 指标实绩值
index_score  NUMBER(15,3) NULL, --指标得分
insert_time        DATE,
last_update_time   DATE,
PRIMARY KEY (factory, area, shift_group_code, sum_date, item_id, index_id) 
)

/**
关键项目指标得分(日)
**/
create table t_item_index_scroe_day(
factory varchar(128) NULL,
area varchar(128) NULL,
shift_group_code VARCHAR(256) NULL, -- 班组编号
shift_group_name VARCHAR(256) NULL,
sum_date date NULL,
item_id NUMBER(15) NULL,
index_id  NUMBER(15) NULL,   --指标id
index_act_value NUMBER(15,3) NULL, -- 指标实绩值
index_score  NUMBER(15,3) NULL, --指标得分
insert_time        DATE,
last_update_time   DATE,
PRIMARY KEY (factory, area, shift_group_code, sum_date, item_id, index_id) 
)
/**
qms_data 数据汇总月份
**/
create table t_qms_data_month(
factory_name varchar(128) null,   --工厂名称
working_date varchar(128) null,            --期间
area_name varchar(128) null,         --车间名称
group_name varchar(256) null,        --班组名称
assemble_defect_qty number(15) null,     --不良台数
downline_qty number(15) null,           ---组装停线数
stamping_finish_qty number(15) null,     --冲压入库数（最后道工序）
painting_qty number(15) null,            --挂件数
production_line_name varchar(128) null,   ---产线名称
assemble_finish_qty number(15) null,         --完工数
assemble_produce_qty number(15) null,        ---投产数
ipqc_total_qty number(15) null,               --巡检总批次数
ipqc_defect_qty number(15) null               --巡检不良批次数        
)

/**
    组装生产退次表天
   字段：工厂 车间 班组 时间  责任班组退次数（不良数）  组装投产数
**/
/**
create table AT_ASSEMBLYPRODUCTBACK_SUMDAY(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
return_num NUMBER(12) NULL,
commit_num NUMBER(12) NULL
)
*/
/**
前工序FQC查检表天
字段：工厂 车间 班组 时间  责任班组不良数量  总数  抽样数
**/
/**
create table AT_FORMERPROCESS_SUMDAY(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
defect_qty NUMBER(12) NULL,
total_qty NUMBER(12) NULL,
simple_qty NUMBER(12) NULL
)
*/

/**
过程批量不良记录总和表天
字段： 工厂 车间 班组 时间 质量风险分数之和 ,发生流程节点
**/
/**
create table AT_BATCHDEFECTRECORD_SUMDAY(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
risk_score NUMBER(15,2) NULL,
process_node VARCHAR(256) NULL

)
*/

/**
过程审核单总和表天
字段： 工厂 车间 班组 时间  审核内容   责任班组审核次数
**/
/**
create table AT_PROCESSAUDITRECORD_SUMDAY(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
check_content VARCHAR(128),
audit_num NUMBER(12) NULL					
)
*/
/**
质量改善课题申报总和天
字段： 工厂 车间 班组  时间  责任班组最总评分
**/
/*
create table  AT_QUALITYIMPROVEMENT_SUMDAY(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
total_score NUMBER(20) NULL
)
*/
/**
冲压质量日报表总和天）
字段： 工厂 车间 班组  时间   不良数量  冲压入库数总和当天
**/
/**
create table AT_STAMPINGDAILYREPORT_SUMDAY(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
defect_qty NUMBER(20) NULL,
total_qty NUMBER(20) NULL
)
*/

/**
喷涂质量日报表总和天
字段： 工厂 车间 班组 时间  合格数  挂件数
**/
/**
create table AT_PAINTINGDAILYREPORT_SUMDAY(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
quality_qty NUMBER(20) NULL,
defect_qty NUMBER(20) NULL
)
*/
/**
市场开箱不良记录日报总和表天
字段： 工厂  班组  时间  不良来源  不良次数
**/
/**
create table AT_BOXDEFECTRECORD_SUMDAY(
factory varchar(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
defect_source VARCHAR(256) NULL,
defect_num NUMBER(15) NULL
)
*/
/**
组装维修日报月份
字段：   班组  时间  维修数  备用字段1，备用字段2
**/
/**
create table AT_ASEEBLEREPAIRED_SUM(
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
repaired_num NUMBER(15) NULL,
col_1 varchar(256) null,
col_2 number(15) null
)
*/
/**
组装维修日报天表
字段：   班组  时间  维修数  备用字段1，备用字段2
**/
/**
create table AT_ASEEBLEREPAIRED_SUMDAY(
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
repaired_num NUMBER(15) NULL,
col_1 varchar(256) null,
col_2 number(15) null
)
*/
/**
OQC巡检表--不良台数
字段：   班组  时间  不良台数  备用字段1，备用字段2,备用字段3，备用字段4
**/
/**
create table AT_IPQCCHECK_SUM(
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
defect_num NUMBER(15) NULL,
col_1 varchar(256) null,
col_2 varchar(256) null,
col_3 number(15) null,
col_4 number(15,3) null
)
*/
/**
OQC巡检表--不良台数
字段：   班组  时间  不良台数  备用字段1，备用字段2,备用字段3，备用字段4
**/
/**
create table AT_IPQCCHECK_SUMDAY(
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
defect_num NUMBER(15) NULL,
col_1 varchar(256) null,
col_2 varchar(256) null,
col_3 number(15) null,
col_4 number(15,3) null
)
*/
/**
年度考核指标设定表
**/
create table t_performance_checkyear(
id number(12),
check_year DATE null,                --年
factory varchar(128) null,           --工厂
check_index_name varchar(256) null,  --考核指标
base_value_year number(14,4) null,   --年度基准
target_value_year number(14,4) null, --年度目标
target_value_halfyear number(14,4) null,  --上半年目标
depress_rate_year  number(14,4) null,     --年度下降率
create_user  VARCHAR(128) NULL,           --创建人
create_time DATE DEFAULT SYSDATE  NULL,   --创建时间
last_update_user  VARCHAR(128) NULL,      --更新人
last_update_time DATE NULL,               --更新时间
primary key(id)
)

create sequence seq_t_performance_checkyear increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;

/**
月度考核指标统计表
**/
create table t_count_performancemonth(
id number(12),
check_month date null,              --月份
factory varchar(128) null,          --工厂
check_index_name varchar(256) null,  --考核指标
target_value number(14,4) null,      --单月目标
actual_value number(14,4) null,      --单月实绩
total_value number(14,4) null,       --累计实绩
boxdefect_qty number(12) null,       --开箱不良数
oqcdefect_qty number(12) null,       --OQC不良数
quality_qty   number(12) null,       --合格数
product_qty   number(12) null,       --投产数
create_time DATE DEFAULT SYSDATE  NULL,  --创建时间
primary key(id)
)

create sequence seq_t_count_performancemonth increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;

/**
异常信息录入表
*/
create table t_exception_enter(
id number(12),
factory varchar(128) null,           --工厂
area varchar(128) null,              --车间
group_name varchar(128) null,        --班组
exception_type varchar(128) null,    --异常类型
exception_name varchar(256) null,    --异常描述
occur_time Date null,                --异常发生时间
create_time Date default sysdate,    --录入时间
create_user varchar(128) null,       --录入人
last_update_time Date null,          --更新时间
last_update_user varchar(128) null,  --更新人
primary key(id)
)
create sequence seq_t_exception_enter increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;

/**
  触发器 插入月度考核指标统计表
*/
create trigger TRI_count_performancemonth 
before insert on t_count_performancemonth
for each row
begin
select seq_t_count_performancemonth.nextval into :new.id from dual;
end;


create table sys_login_history
(
  id number(18) not null,
  sessionId     varchar2(256),
  user_key    number(18),
  user_name     varchar2(256),
  user_description             varchar2(256),
  login_ip             varchar2(256),
  logout_type     number(2),
  login_time      date default sysdate,
  logout_time     date
)
create sequence seq_sys_login_history increment by 1 start with 1 minvalue 1 nomaxvalue nocycle cache 20;

CREATE OR REPLACE TRIGGER TRIGGER_sys_login_history
BEFORE INSERT ON sys_login_history FOR EACH ROW WHEN (new.id is null)
begin
select seq_sys_login_history.nextval into:new.id  from dual;
end;


/**
IPQC巡检记录总和表（月份）
字段： 工厂 车间 班组 月份 质量风险分数之和 ,发生流程节点
**/
/**
create table AT_IPQCINSPECTS_SUM(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
risk_score NUMBER(12,2) NULL,
process_node VARCHAR(256) NULL
)
*/
/**
IPQC巡检记录总和表（天）
字段： 工厂 车间 班组 月份 质量风险分数之和 ,发生流程节点
**/
/**
create table AT_IPQCINSPECTS_SUMDAY(
factory varchar(128) NULL,
areaS  VARCHAR(128) NULL,
shift_group VARCHAR(256) NULL,
monthly VARCHAR(128) NULL,
risk_score NUMBER(12,2) NULL,
process_node VARCHAR(256) NULL
)
*/
/**存储过程*/

/**组装维修数天*/
CREATE OR REPLACE PROCEDURE repaired_num_day_proce(startTime in varchar2,endTime in varchar2) AUTHID CURRENT_USER  
as
v_count INT; 
str varchar2(1200); 
BEGIN 
v_count := 0; 
str:='CREATE  GLOBAL TEMPORARY TABLE repaired_num_day on commit PRESERVE rows  AS 
    select 
    w.addr_city factory,       
    ag.description group_name, 
    to_char(d.udt_1,''yyyy-mm-dd'') stat_month,
    count(u.serial_number) repaired_num          
    from  defect_repair_entry d,
    test_instance t,
    unit u,
    work_order w,
    work_order_items woi,
    app_group ag
     where 1=1 
            and d.test_instance_key=t.test_instance_key  
            and t.object_key=u.unit_key 
            and u.order_item_key=woi.order_item_key
            and w.order_key = woi.order_key
            and woi.uda_1=ag.description
            and t.location=''在线检验'' 
            and t.test_valid=''1''
            and d.defect_user_name=''组装车间''
            and to_char(d.udt_1,''yyyy-mm-dd'') between '''||startTime||''' and '''||endTime||'''
     group by w.addr_city, ag.description,to_char(d.udt_1,''yyyy-mm-dd'')'; 
execute immediate str;
str:=' update  T_MES_DATA_SUM_DAY sm set (col_18) = ( 
     select repaired_num from repaired_num_day a
      where a.factory = sm.factory and a.group_name = sm.shift_group_name and a.stat_month= to_char(sm.sum_date,''YYYY-MM-DD'')
     )
     where  to_char(sm.sum_date,''YYYY-MM-DD'') between  '''||startTime||''' and '''||endTime||''''; 
execute immediate str; 
str:='truncate table repaired_num_day ';
execute immediate str; 
str:='drop table repaired_num_day';
execute immediate str; 
commit;
END repaired_num_day_proce;

/**oqc抽检不良天*/
CREATE OR REPLACE PROCEDURE oqcdefect_num_day_proce(startTime in varchar2,endTime in varchar2) AUTHID CURRENT_USER  
as
v_count INT; 
str varchar2(3000); 
BEGIN 
v_count := 0; 
str:='CREATE  GLOBAL TEMPORARY TABLE oqcdefect_num_day on commit PRESERVE rows  AS 
   select a.factory,a.stat_month,a.group_name ,sum(a.defect_num) defect_num from (
   select p.uda_1 factory, to_char(t.creation_time,''YYYY-MM-DD'') stat_month, substr(t.uda_5,0,instr(t.uda_5||'','','','')-1) group_name,
   count(t.test_instance_key) defect_num  
   from  test_instance t,app_group p
   where 1=1
             and substr(t.uda_5,0,instr(t.uda_5||'','','','')-1) = p.description
             and to_char(t.creation_time,''YYYY-MM-DD'') between '''||startTime||''' and '''||endTime||'''
             and t.uda_7=''OQC单独抽检''
             and t.test_passed=0
             and t.uda_5 is not null
   group by  p.uda_1, to_char(t.creation_time,''YYYY-MM-DD''), substr(t.uda_5,0,instr(t.uda_5||'','','','')-1)
   
   union all
   
   select  p.uda_1 factory, to_char(t.creation_time,''YYYY-MM-DD'') stat_month,substr(t.uda_5,instr(t.uda_5,'','')+1,instr(t.uda_5||'','','','',1,2)-instr(t.uda_5||'','','','')-1) group_name,
   count(t.test_instance_key) defect_num 
   from  test_instance t,app_group p
   where 1=1 
             and substr(t.uda_5,instr(t.uda_5,'','')+1,instr(t.uda_5||'','','','',1,2)-instr(t.uda_5||'','','','')-1) = p.description
             and to_char(t.creation_time,''YYYY-MM-DD'')between '''||startTime||''' and '''||endTime||'''

             and t.uda_7=''OQC单独抽检''
             and t.test_passed=0
             and t.uda_5 is not null
   group by p.uda_1, to_char(t.creation_time,''YYYY-MM-DD''),substr(t.uda_5,instr(t.uda_5,'','')+1,instr(t.uda_5||'','','','',1,2)-instr(t.uda_5||'','','','')-1)
   
   union all
   
      select p.uda_1 factory, to_char(t.creation_time,''YYYY-MM-DD'') stat_month,substr(t.uda_5,instr(t.uda_5,'','',1,2)+1,instr(t.uda_5||'','','','',1,3)-instr(t.uda_5||'','','','',1,2)-1) group_name,
   count(t.test_instance_key) defect_num
   from  test_instance t,app_group p
   where 1=1 
             and substr(t.uda_5,instr(t.uda_5,'','',1,2)+1,instr(t.uda_5||'','','','',1,3)-instr(t.uda_5||'','','','',1,2)-1)= p.description
             and to_char(t.creation_time,''YYYY-MM-DD'') between '''||startTime||''' and '''||endTime||'''

             and  t.uda_7=''OQC单独抽检''
             and t.test_passed=0
             and t.uda_5 is not null
   group by  p.uda_1, to_char(t.creation_time,''YYYY-MM-DD''),
   substr(t.uda_5,instr(t.uda_5,'','',1,2)+1,instr(t.uda_5||'','','','',1,3)-instr(t.uda_5||'','','','',1,2)-1)
   ) a group by a.factory,a.stat_month,a.group_name'; 
execute immediate str;
str:=' update  T_MES_DATA_SUM_DAY sm set (col_16) = ( 
     select defect_num from oqcdefect_num_day a
      where a.factory = sm.factory and a.group_name = sm.shift_group_name and a.stat_month= to_char(sm.sum_date,''YYYY-MM-DD'')
     )
     where  to_char(sm.sum_date,''YYYY-MM-DD'') between  '''||startTime||''' and '''||endTime||''''; 
execute immediate str; 
str:='truncate table oqcdefect_num_day ';
execute immediate str; 
str:='drop table oqcdefect_num_day';
execute immediate str; 
commit;
END oqcdefect_num_day_proce;

/** 组装维修数月*/
CREATE OR REPLACE PROCEDURE repaired_num_month_proce(startTime in varchar2,endTime in varchar2) AUTHID CURRENT_USER  
as
v_count INT; 
str varchar2(1500); 
BEGIN 
v_count := 0; 
str:='CREATE  GLOBAL TEMPORARY TABLE repaired_num_month on commit PRESERVE rows  AS 
	  select 
	  w.addr_city factory,       
	  ag.description group_name, 
	  substr('''||endTime||''',0,7) stat_month,
	  count(u.serial_number) repaired_num          
	  from  defect_repair_entry d,
	  test_instance t,
	  unit u,
	  work_order w,
	  work_order_items woi,
	  app_group ag
	   where 1=1 
            and d.test_instance_key=t.test_instance_key  
            and t.object_key=u.unit_key 
            and u.order_item_key=woi.order_item_key
            and w.order_key = woi.order_key
            and woi.uda_1=ag.description
            and t.location=''在线检验'' 
            and t.test_valid=''1''
            and d.defect_user_name=''组装车间''
            and to_char(d.udt_1,''yyyy-mm-dd'') between '''||startTime||''' and '''||endTime||''' 
   group by w.addr_city, ag.description,substr('''||endTime||''' ,0,7)'; 
execute immediate str;
str:=' update  T_MES_DATA_SUM_MONTH sm set (col_18) = ( 
     select repaired_num from repaired_num_month a
      where a.factory = sm.factory and a.group_name = sm.shift_group_name and a.stat_month= to_char(sm.sum_date,''YYYY-MM'')
     )
     where  exists( select repaired_num from repaired_num_month a
      where a.factory = sm.factory and a.group_name = sm.shift_group_name and a.stat_month= to_char(sm.sum_date,''YYYY-MM'')
      )'; 
execute immediate str; 
str:='truncate table repaired_num_month ';
execute immediate str; 
str:='drop table repaired_num_month';
execute immediate str; 
commit;
END repaired_num_month_proce;

/** OQC不良数月*/
CREATE OR REPLACE PROCEDURE oqcdefect_num_month_proce(startTime in varchar2,endTime in varchar2) AUTHID CURRENT_USER
as
v_count INT;
str varchar2(3000);
BEGIN
v_count := 0;
str:='CREATE  GLOBAL TEMPORARY TABLE oqcdefect_num_month on commit PRESERVE rows  AS
  select a.factory,a.stat_month,a.group_name ,sum(a.defect_num) defect_num from (
  select p.uda_1 factory, substr('''||endTime||''',0,7) stat_month, substr(t.uda_5,0,instr(t.uda_5||'','','','')-1) group_name,
   count(t.test_instance_key) defect_num
   from  test_instance t,app_group p
   where 1=1
             and substr(t.uda_5,0,instr(t.uda_5||'','','','')-1) = p.description
             and to_char(t.creation_time,''YYYY-MM-DD'') between '''||startTime||''' and '''||endTime||'''
             and t.uda_7=''OQC单独抽检''
             and t.test_passed=0
             and t.uda_5 is not null
   group by  p.uda_1, substr('''||endTime||''',0,7), substr(t.uda_5,0,instr(t.uda_5||'','','','')-1)

   union all

   select  p.uda_1 factory, substr('''||endTime||''',0,7) stat_month,substr(t.uda_5,instr(t.uda_5,'','')+1,instr(t.uda_5||'','','','',1,2)-instr(t.uda_5||'','','','')-1) group_name,
   count(t.test_instance_key) defect_num
   from  test_instance t,app_group p
   where 1=1
             and substr(t.uda_5,instr(t.uda_5,'','')+1,instr(t.uda_5||'','','','',1,2)-instr(t.uda_5||'','','','')-1) = p.description
             and to_char(t.creation_time,''YYYY-MM-DD'') between '''||startTime||''' and '''||endTime||'''

             and t.uda_7=''OQC单独抽检''
             and t.test_passed=0
             and t.uda_5 is not null
   group by p.uda_1, substr('''||endTime||''',0,7),substr(t.uda_5,instr(t.uda_5,'','')+1,instr(t.uda_5||'','','','',1,2)-instr(t.uda_5||'','','','')-1)

   union all

      select p.uda_1 factory, substr('''||endTime||''',0,7) stat_month,substr(t.uda_5,instr(t.uda_5,'','',1,2)+1,instr(t.uda_5||'','','','',1,3)-instr(t.uda_5||'','','','',1,2)-1) group_name,
   count(t.test_instance_key) defect_num
   from  test_instance t,app_group p
   where 1=1
             and substr(t.uda_5,instr(t.uda_5,'','',1,2)+1,instr(t.uda_5||'','','','',1,3)-instr(t.uda_5||'','','','',1,2)-1)= p.description
             and to_char(t.creation_time,''YYYY-MM-DD'') between '''||startTime||''' and '''||endTime||'''

             and  t.uda_7=''OQC单独抽检''
             and t.test_passed=0
             and t.uda_5 is not null
   group by  p.uda_1, substr('''||endTime||''',0,7),substr(t.uda_5,instr(t.uda_5,'','',1,2)+1,instr(t.uda_5||'','','','',1,3)-instr(t.uda_5||'','','','',1,2)-1)
   ) a group by a.factory,a.stat_month,a.group_name';
execute immediate str;
str:=' update  T_MES_DATA_SUM_MONTH sm set (col_16) = (
     select defect_num from oqcdefect_num_month a
      where a.factory = sm.factory and a.group_name = sm.shift_group_name and a.stat_month= to_char(sm.sum_date,''YYYY-MM'')
     )
     where  exists(
      select defect_num from oqcdefect_num_month a
      where a.factory = sm.factory and a.group_name = sm.shift_group_name and a.stat_month= to_char(sm.sum_date,''YYYY-MM'')
     )';
execute immediate str;
str:='truncate table oqcdefect_num_month ';
execute immediate str;
str:='drop table oqcdefect_num_month';
execute immediate str;
commit;
END oqcdefect_num_month_proce;






/**
全质量系统
*/

/**IQC登记
*/
create table t_test_instance_record (
lot_name VARCHAR(256) NULL,     --到货批次
ware_house VARCHAR(256) NULL,   --仓库
part_version VARCHAR(256) NULL, --物料版本
part_type VARCHAR(256) NULL,    --物料类别
part_class  VARCHAR(64) NULL,  -- 物料级别
part_number VARCHAR(256) NULL,  --物料编号
new_part_number VARCHAR(256) NULL,  --新物料编号
part_name VARCHAR(256) NULL,    --物料名称
product_type VARCHAR(256) NULL, --机型类别
supplier_number varchar(256)  NULL, --供应商编号
new_supplier_number varchar(256)  NULL, --供应商编号
supplier_name VARCHAR(256) NULL, --供应商名称
supplier_brief VARCHAR(256) NULL, --供应商简称
date_t VARCHAR(256) NULL,        --日期
is_new VARCHAR(256) NULL,        --是否新品
result_s VARCHAR(256) NULL,      --本批判断结果
total_qty NUMBER(12)  NULL,      --总数
aspect_type VARCHAR(256) NULL,   --外观判断
aspect_tnum NUMBER(12)  NULL,    --抽检总数
aspect_dnum NUMBER(12)  NULL ,   --抽检不良数
size_type VARCHAR(256) NULL,     --尺寸判断
size_tnum NUMBER(12)  NULL,      --抽检总数
size_dnum NUMBER(12)  NULL ,     --抽检不良数
property_type VARCHAR(256) NULL, --性能判断
property_tnum NUMBER(12)  NULL,  --抽检总数
property_dnum NUMBER(12)  NULL , --抽检不良数
other_type VARCHAR(256) NULL,    --其他判断
other_tnum NUMBER(12)  NULL,     --抽检总数
other_dnum NUMBER(12)  NULL      --抽检不良数
)tablespace FT_TBS
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
  
  create index tiri_lot_name on t_test_instance_record (lot_name)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse;  
  
  create index tiri_ware_house on t_test_instance_record (ware_house)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse;
  
   create index tiri_part_type on t_test_instance_record (part_type)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
  create index tiri_part_number on t_test_instance_record (part_number)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
  create index tiri_product_type on t_test_instance_record (product_type)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
    create index tiri_supplier_name on t_test_instance_record (supplier_name)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
    create index tiri_date_t on t_test_instance_record (date_t)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
   create index tiri_is_new on t_test_instance_record (is_new)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
  
    create index tiri_result_s on t_test_instance_record (result_s)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 

--------------------
MES建立OQC抽检视图
--------------------
create or replace view test_instance_uda_7 as
select  T.TEST_INSTANCE_KEY, T.SITE_NUM, T.TEST_DEFINITION_KEY,
 T.OBJECT_KEY, T.OBJECT_NAME, T.OBJECT_TYPE, T.DSCOMMENT, T.USER_NAME, T.ROUTE_NAME,
  T.ROUTE_KEY, T.ROUTE_STEP_NAME, T.OP_NAME, T.TOBJ_HISTORY_KEY, T.COMPLETE_COUNT,
   T.P_LINE_NAME, T.LOCATION, T.TEST_EQUIP_KEY, T.TEST_PASSED, T.TEST_VALID, T.TEST_START_TIME, 
   T.TEST_START_TIME_U, T.TEST_START_TIME_Z, T.TEST_END_TIME, T.TEST_END_TIME_U, 
   T.TEST_END_TIME_Z, T.CREATION_TIME, T.CREATION_TIME_U, T.CREATION_TIME_Z, T.LAST_MODIFIED_TIME, 
   T.LAST_MODIFIED_TIME_U, T.LAST_MODIFIED_TIME_Z, T.XFR_INSERT_PID, T.TRX_ID, T.UDA_0, T.UDA_1, 
   T.UDA_2, T.UDA_3, T.UDA_4, T.UDA_5, T.UDA_6, T.UDA_7, T.UDA_8, T.UDA_9, T.UDT_0, T.UDT_0_U, 
   T.UDT_0_Z, T.UDT_1, T.UDT_1_U, T.UDT_1_Z, T.UDT_2, T.UDT_2_U, T.UDT_2_Z, 
   T.CREATOR_KEY, T.LAST_MODIFIER_KEY, T.CATEGORY, T.DESCRIPTION, T.PASS_COUNT, 
   T.TEST_INSTANCE_NAME
  from TEST_INSTANCE T where  t.uda_7='OQC单独抽检';

--------------------
MES建立组装维修视图
--------------------  
create or replace view test_instance_location as
select  T.TEST_INSTANCE_KEY, T.SITE_NUM, T.TEST_DEFINITION_KEY,
 T.OBJECT_KEY, T.OBJECT_NAME, T.OBJECT_TYPE, T.DSCOMMENT, T.USER_NAME, T.ROUTE_NAME,
  T.ROUTE_KEY, T.ROUTE_STEP_NAME, T.OP_NAME, T.TOBJ_HISTORY_KEY, T.COMPLETE_COUNT,
   T.P_LINE_NAME, T.LOCATION, T.TEST_EQUIP_KEY, T.TEST_PASSED, T.TEST_VALID, T.TEST_START_TIME, 
   T.TEST_START_TIME_U, T.TEST_START_TIME_Z, T.TEST_END_TIME, T.TEST_END_TIME_U, 
   T.TEST_END_TIME_Z, T.CREATION_TIME, T.CREATION_TIME_U, T.CREATION_TIME_Z, T.LAST_MODIFIED_TIME, 
   T.LAST_MODIFIED_TIME_U, T.LAST_MODIFIED_TIME_Z, T.XFR_INSERT_PID, T.TRX_ID, T.UDA_0, T.UDA_1, 
   T.UDA_2, T.UDA_3, T.UDA_4, T.UDA_5, T.UDA_6, T.UDA_7, T.UDA_8, T.UDA_9, T.UDT_0, T.UDT_0_U, 
   T.UDT_0_Z, T.UDT_1, T.UDT_1_U, T.UDT_1_Z, T.UDT_2, T.UDT_2_U, T.UDT_2_Z, 
   T.CREATOR_KEY, T.LAST_MODIFIER_KEY, T.CATEGORY, T.DESCRIPTION, T.PASS_COUNT, 
   T.TEST_INSTANCE_NAME
  from TEST_INSTANCE T where  t.location='在线检验' and t.test_valid='1' 


--------------------
插入IQC抽检记录表存储过程
-------------------- 
create or replace procedure insert_lot_check_info(startTime in varchar2,endTime in varchar2)
as
begin
 delete from t_test_instance_record where substr(date_t,0,10) between startTime and endTime;
 
 insert into t_test_instance_record (lot_name,ware_house,part_version,part_type,part_number,part_name,
supplier_number,supplier_name,supplier_brief,date_t,is_new,result_s,total_qty,part_class,aspect_type,
aspect_tnum,aspect_dnum,size_type,size_tnum,size_dnum,property_type,property_tnum,property_dnum,
other_type,other_tnum,other_dnum,product_type,new_supplier_number,new_part_number)  with tmp as
(
select l.lot_name,ul.ware_house_s,p.part_revision,up.part_type_s,p.part_number,p.description part_name,
ul.supplier_number_s,ul.supplier_s,nvl(ac.uda_3,ul.supplier_s) supplier_brief,
to_char(ti.creation_time,'yyyy-mm-dd hh24:mi:ss') check_time,up.is_new_s,ul.little_mark_s,ti.description,ti.pass_count,
listagg(dre.defect_location||decode(dre.repaired,0,'不合格','合格')||','||nvl(dre.defect_code,'0')||','||nvl(dre.defect_comment,'0'),',')
within group
(order by l.lot_name,ul.ware_house_s,p.part_revision,up.part_type_s,p.part_number,p.description,
ul.supplier_number_s,ul.supplier_s,nvl(ac.uda_3,ul.supplier_s),
to_char(ti.creation_time,'yyyy-mm-dd hh24:mi:ss'),up.is_new_s,ti.description,ti.pass_count) as defect_info,up.part_level_s,
up.mold_type_s product_name,decode(qs.supplier_number_n,'',ul.supplier_number_s,qs.supplier_number_n ) supplier_number_n,
decode(qp.new_part_number,'',p.part_number,qp.new_part_number) part_number_n
from lot@mes_part_link l,uda_lot@mes_part_link ul,part@mes_test_link p,uda_part@mes_test_link up,
test_instance@mes_part_link ti,test_definition@mes_part_link td,defect_repair_entry@mes_part_link dre,account@mes_test_link ac,
t_supplier_ref qs,new_part_ref qp
where l.lot_key=ul.object_key
and p.part_key=up.object_key
and l.part_number=p.part_number
and ti.object_key=l.lot_key
and ti.test_definition_key=td.test_definition_key
and td.test_definition_name='IQC'
and ti.test_instance_key=dre.test_instance_key
and ul.supplier_number_s = ac.account_name(+)
and ul.supplier_number_s = qs.supplier_number(+)
and p.part_number = qp.old_part_number(+)
and to_char(ti.creation_time,'yyyy-mm-dd') between startTime and  endTime
group by l.lot_name,ul.ware_house_s,p.part_revision,up.part_type_s,p.part_number,p.description,
ul.supplier_number_s,ul.supplier_s,nvl(ac.uda_3,ul.supplier_s),
to_char(ti.creation_time,'yyyy-mm-dd hh24:mi:ss'),up.is_new_s,ul.little_mark_s,ti.description,ti.pass_count,up.part_level_s,
up.mold_type_s,decode(qs.supplier_number_n,'',ul.supplier_number_s,qs.supplier_number_n ),
decode(qp.new_part_number,'',p.part_number,qp.new_part_number)
)
select tmp.lot_name,tmp.ware_house_s,tmp.part_revision,tmp.part_type_s,tmp.part_number,tmp.part_name,
tmp.supplier_number_s,tmp.supplier_s,tmp.supplier_brief,
tmp.check_time,tmp.is_new_s||decode(tmp.little_mark_s,'是','little','large'),tmp.description,tmp.pass_count,tmp.part_level_s,
splitStr(splitStr(tmp.defect_info,'外观',1),',',0),to_number(splitStr(splitStr(tmp.defect_info,'外观',1),',',1)),to_number(splitStr(splitStr(tmp.defect_info,'外观',1),',',2)),
splitStr(splitStr(tmp.defect_info,'尺寸',1),',',0),to_number(splitStr(splitStr(tmp.defect_info,'外观',1),',',1)),to_number(splitStr(splitStr(tmp.defect_info,'外观',1),',',2)),
splitStr(splitStr(tmp.defect_info,'性能',1),',',0),to_number(splitStr(splitStr(tmp.defect_info,'外观',1),',',1)),to_number(splitStr(splitStr(tmp.defect_info,'外观',1),',',2)),
splitStr(splitStr(tmp.defect_info,'其他',1),',',0),to_number(splitStr(splitStr(tmp.defect_info,'外观',1),',',1)),to_number(splitStr(splitStr(tmp.defect_info,'外观',1),',',2)),
tmp.product_name,tmp.supplier_number_n,tmp.part_number_n
from tmp;

update t_test_instance_record t set t.result_s = '该批不合格' where t.lot_name in (
select l.lot_name
from lot@mes_part_link l ,test_instance@mes_part_link ti ,at_freezelotrecord@mes_part_link at
where l.lot_key=ti.object_key
and at.number_s=l.lot_name
and at.type_s='冻结'
and at.reason_s='IQC检验不合格'
and l.uda_2='该批合格'
and  to_char(ti.creation_time,'yyyy-mm-dd') between startTime and  endTime
union all
select l.lot_name
from lot@mes_part_link l,test_instance@mes_part_link ti 
where  l.lot_key=ti.object_key
and l.uda_2='该批不合格'
and  to_char(ti.creation_time,'yyyy-mm-dd') between startTime and  endTime
)
commit;
end;



----或者----
create or replace procedure insert_lot_check_info(startTime in varchar2,endTime in varchar2) AUTHID CURRENT_USER  
as
str varchar2(6000); 
begin
str := 'delete from t_test_instance_record where substr(date_t,0,10) between '''||startTime||''' and '''||endTime||''' ';
execute immediate str;
str := 'insert into t_test_instance_record (lot_name,ware_house,part_version,part_type,part_number,part_name,
supplier_number,supplier_name,supplier_brief,date_t,is_new,result_s,total_qty,part_class,aspect_type,
aspect_tnum,aspect_dnum,size_type,size_tnum,size_dnum,property_type,property_tnum,property_dnum,
other_type,other_tnum,other_dnum,product_type,new_supplier_number,new_part_number)  with tmp as
(
select l.lot_name,ul.ware_house_s,p.part_revision,up.part_type_s,p.part_number,p.description part_name,
ul.supplier_number_s,ul.supplier_s,nvl(ac.uda_3,ul.supplier_s) supplier_brief,
to_char(ti.creation_time,''yyyy-mm-dd hh24:mi:ss'') check_time,up.is_new_s,ul.little_mark_s,ti.description,ti.pass_count,
listagg(dre.defect_location||decode(dre.repaired,0,''不合格'',''合格'')||'',''||nvl(dre.defect_code,''0'')||'',''||nvl(dre.defect_comment,''0''),'','')
within group
(order by l.lot_name,ul.ware_house_s,p.part_revision,up.part_type_s,p.part_number,p.description,
ul.supplier_number_s,ul.supplier_s,nvl(ac.uda_3,ul.supplier_s),
to_char(ti.creation_time,''yyyy-mm-dd hh24:mi:ss''),up.is_new_s,ti.description,ti.pass_count) as defect_info,up.part_level_s,
up.mold_type_s product_name,decode(qs.supplier_number_n,'''',ul.supplier_number_s,qs.supplier_number_n ) supplier_number_n,
decode(qp.new_part_number,'''',p.part_number,qp.new_part_number) part_number_n
from lot@mes_part_link l,uda_lot@mes_part_link ul,part@mes_test_link p,uda_part@mes_test_link up,
test_instance@mes_part_link ti,test_definition@mes_part_link td,defect_repair_entry@mes_part_link dre,account@mes_test_link ac,
t_supplier_ref qs,new_part_ref qp
where l.lot_key=ul.object_key
and p.part_key=up.object_key
and l.part_number=p.part_number
and ti.object_key=l.lot_key
and ti.test_definition_key=td.test_definition_key
and td.test_definition_name=''IQC''
and ti.test_instance_key=dre.test_instance_key
and ul.supplier_number_s = ac.account_name(+)
and ul.supplier_number_s = qs.supplier_number(+)
and p.part_number = qp.old_part_number(+)
and to_char(ti.creation_time,''yyyy-mm-dd'') between '''||startTime||''' and '''||endTime||'''
group by l.lot_name,ul.ware_house_s,p.part_revision,up.part_type_s,p.part_number,p.description,
ul.supplier_number_s,ul.supplier_s,nvl(ac.uda_3,ul.supplier_s),
to_char(ti.creation_time,''yyyy-mm-dd hh24:mi:ss''),up.is_new_s,ul.little_mark_s,ti.description,ti.pass_count,up.part_level_s,
up.mold_type_s,decode(qs.supplier_number_n,'''',ul.supplier_number_s,qs.supplier_number_n ),
decode(qp.new_part_number,'''',p.part_number,qp.new_part_number)
)
select tmp.lot_name,tmp.ware_house_s,tmp.part_revision,tmp.part_type_s,tmp.part_number,tmp.part_name,
tmp.supplier_number_s,tmp.supplier_s,tmp.supplier_brief,
tmp.check_time,tmp.is_new_s||decode(tmp.little_mark_s,'是','little','large'),tmp.description,tmp.pass_count,tmp.part_level_s,
splitStr(splitStr(tmp.defect_info,''外观'',1),'','',0),to_number(splitStr(splitStr(tmp.defect_info,''外观'',1),'','',1)),to_number(splitStr(splitStr(tmp.defect_info,''外观'',1),'','',2)),
splitStr(splitStr(tmp.defect_info,''尺寸'',1),'','',0),to_number(splitStr(splitStr(tmp.defect_info,''外观'',1),'','',1)),to_number(splitStr(splitStr(tmp.defect_info,''外观'',1),'','',2)),
splitStr(splitStr(tmp.defect_info,''性能'',1),'','',0),to_number(splitStr(splitStr(tmp.defect_info,''外观'',1),'','',1)),to_number(splitStr(splitStr(tmp.defect_info,''外观'',1),'','',2)),
splitStr(splitStr(tmp.defect_info,''其他'',1),'','',0),to_number(splitStr(splitStr(tmp.defect_info,''外观'',1),'','',1)),to_number(splitStr(splitStr(tmp.defect_info,''外观'',1),'','',2)),
tmp.product_name,tmp.supplier_number_n,tmp.part_number_n
from tmp';
execute immediate str;
str :='update t_test_instance_record t set t.result_s = ''该批不合格'' where t.lot_name in (
select l.lot_name
from lot@mes_part_link l ,test_instance@mes_part_link ti ,at_freezelotrecord@mes_part_link at
where l.lot_key=ti.object_key
and at.number_s=l.lot_name
and at.type_s=''冻结''
and at.reason_s=''IQC检验不合格''
and l.uda_2=''该批合格''
and  to_char(ti.creation_time,''yyyy-mm-dd'') between '''||startTime||''' and '''||endTime||'''
union all
select l.lot_name
from lot@mes_part_link l,test_instance@mes_part_link ti 
where  l.lot_key=ti.object_key
and l.uda_2=''该批不合格''
and  to_char(ti.creation_time,''yyyy-mm-dd'') between '''||startTime||''' and '''||endTime||'''
)';
execute immediate str;
str :='update t_test_instance_record tr set tr.supplier_brief = (
 select nvl(ac.uda_3,ac.description) from account ac 
 where ac.account_name = tr.new_supplier_number
 and ac.uda_3 <> tr.supplier_brief
 )where exists(
 select 1 from account ac 
 where ac.account_name = tr.new_supplier_number
 and ac.uda_3 <> tr.supplier_brief
 )';
execute immediate str;
commit;
end;
----------------------------
创建函数splitStr
----------------------------
create or replace function splitStr
(
  av_str varchar2,  --要分割的字符串
  av_split varchar2,  --分隔符号
  av_index number --取第几个元素
)
return varchar2
is
  lv_str varchar2(1024);
  lv_strOfIndex varchar2(1024);
  lv_length number;
begin
  lv_str:=ltrim(rtrim(av_str));
  lv_str:=concat(lv_str,av_split);
  lv_length:=av_index;
  if lv_length=0 then
      lv_strOfIndex:=substr(lv_str,1,instr(lv_str,av_split)-length(av_split));
  else
      lv_length:=av_index+1;
     lv_strOfIndex:=substr(lv_str,instr(lv_str,av_split,1,av_index)+length(av_split),instr(lv_str,av_split,1,lv_length)-instr(lv_str,av_split,1,av_index)-length(av_split));
  end if;
  return  lv_strOfIndex;
end splitStr;

---------------------
 ERP组装生产退次数
---------------------
create table ERP_ASSEMBLE_PRODUCT_RETURN (
return_key number(18) not null,     --主键
lot_number   VARCHAR(256) NULL,     --批次号
part_type    VARCHAR(256) NULL,     --物料类型
part_class  VARCHAR(256) NULL,      --物料级别
part_number VARCHAR(256) NULL,      --物料编号
part_name VARCHAR(256) NULL,         --物料名称
supplier_number VARCHAR(256) NULL,    -- 供应商编号
supplier_name VARCHAR(256) NULL,     --供应商名称
return_date   date   NULL,     --回退日期
total_qty     number(18)  null,      --总数
return_number number(18) NULL,       --回退数量
ware_house VARCHAR(256) NULL,         --仓库
product_maturity VARCHAR(256) NULL,   --产品成熟度
production_type VARCHAR(256) NULL,    -- 机型类别
creation_time  date null,              --创建日期
product_name VARCHAR(256) NULL,		--零部件名称
product_number VARCHAR(256) NULL,	--零部件编号
part_key VARCHAR2(256) null,			--物料key
consumption_type VARCHAR2(256) null,	--是否关键件
account_key VARCHAR2(256) null,		 --供应商key
supplier_number_n VARCHAR(256) NULL ,--新供应商编号
part_number_n  VARCHAR(256) NULL     --新物料编号
)tablespace FT_TBS
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
 
--KEY TRIGGER
CREATE OR REPLACE TRIGGER TRIGGER_ERP_ASSEMBLE_RETURN
BEFORE INSERT ON ERP_ASSEMBLE_PRODUCT_RETURN FOR EACH ROW WHEN (new.return_key is null)
begin
select bph_sequence.nextval into:new.return_key from dual;
end;  

alter table ERP_ASSEMBLE_PRODUCT_RETURN
  add constraint DPK_ERP_ASSEMBLE_RETURN PRIMARY KEY (return_key)
  using index 
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  ) 
  
  create index DIXI_ERP_ASSEMBLE_pt on ERP_ASSEMBLE_PRODUCT_RETURN (part_type)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse;  
  
  create index DIXI_ERP_ASSEMBLE_wh on ERP_ASSEMBLE_PRODUCT_RETURN (ware_house)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse;
  
   create index DIXI_ERP_ASSEMBLE_pnumber on ERP_ASSEMBLE_PRODUCT_RETURN (part_number)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
  create index DIXI_ERP_ASSEMBLE_pname on ERP_ASSEMBLE_PRODUCT_RETURN (part_name)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
    create index DIXI_ERP_ASSEMBLE_snumber on ERP_ASSEMBLE_PRODUCT_RETURN (supplier_number)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
   create index DIXI_ERP_ASSEMBLE_sname on ERP_ASSEMBLE_PRODUCT_RETURN (supplier_name)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
   create index DIXI_ERP_ASSEMBLE_rdate on ERP_ASSEMBLE_PRODUCT_RETURN (return_date)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
---------------------
 ERP原料退料数
---------------------
create table ERP_PART_RETURN (
return_key number(18) not null,     --主键
lot_number   VARCHAR(256) NULL,     --批次号
part_type    VARCHAR(256) NULL,     --物料类型
part_class  VARCHAR(256) NULL,      --物料级别
part_number VARCHAR(256) NULL,      --物料编号
part_name VARCHAR(256) NULL,         --物料名称
supplier_number VARCHAR(256) NULL,    -- 供应商编号
supplier_name VARCHAR(256) NULL,     --供应商名称
return_date   VARCHAR(256) NULL,     --退料日期
total_qty     number(18) NULL,       --总数
return_number number(18) NULL,       --回退数量
ware_house VARCHAR(256) NULL,         --仓库
product_maturity VARCHAR(256) NULL,   --产品成熟度
production_type VARCHAR(256) NULL,    -- 机型类别
creation_time  date null,              --创建日期
supplier_number_n VARCHAR(256) NULL ,--新供应商编号
part_number_n  VARCHAR(256) NULL     --新物料编号
)tablespace FT_TBS
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
 
--KEY TRIGGER
CREATE OR REPLACE TRIGGER TRIGGER_ERP_PART_RETURN
BEFORE INSERT ON ERP_PART_RETURN FOR EACH ROW WHEN (new.return_key is null)
begin
select bph_sequence.nextval into:new.return_key from dual;
end;  

alter table ERP_PART_RETURN
  add constraint DPK_ERP_PART_RETURN PRIMARY KEY (return_key)
  using index 
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  ) 
  
  create index DIXI_ERP_PART_pt on ERP_PART_RETURN (part_type)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse;  
  
  create index DIXI_EERP_PART_wh on ERP_PART_RETURN (ware_house)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse;
  
   create index DIXI_ERP_PART_pnumber on ERP_PART_RETURN (part_number)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
  create index DIXI_ERP_PART_pname on ERP_PART_RETURN (part_name)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
    create index DIXI_ERP_PART_snumber on ERP_PART_RETURN (supplier_number)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
   create index DIXI_ERP_PART_sname on ERP_PART_RETURN (supplier_name)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
   create index DIXI_ERP_PART_rdate on ERP_PART_RETURN (return_date)
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  reverse; 
  
  
---------------------
 仓库与机型对应关系
---------------------
create table ware_house_product_ref (
ware_ref_key number(18) not null,     --主键
ware_number    VARCHAR(256) NULL,     --仓库编号
ware_name  VARCHAR(256) NULL,         --仓库名称
prouct_number VARCHAR(256) NULL,      --机型编号
product_name VARCHAR(256) NULL,       --机型名称
creation_time  date null ,            --创建日期
last_modify_time date null,           --更新日期
create_user   VARCHAR(256) NULL       --创建人
)tablespace FT_TBS
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
 
--KEY TRIGGER
CREATE OR REPLACE TRIGGER TRIGGER_ware_house_pro_ref
BEFORE INSERT ON ware_house_product_ref FOR EACH ROW WHEN (new.ware_ref_key is null)
begin
select bph_sequence.nextval into:new.ware_ref_key from dual;
end;  

alter table ware_house_product_ref
  add constraint DPK_ware_house_pro_ref PRIMARY KEY (ware_ref_key)
  using index 
  tablespace FT_TBS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  )   
 
 
 ---------------------
新旧物料对应关系
---------------------
create table new_part_ref (
part_ref_key number(18) not null,     --主键
old_part_number    VARCHAR(256) NULL, --旧物料编号
new_part_number  VARCHAR(256) NULL,   --新物料编号
part_name VARCHAR(256) NULL,          --物料名称
creation_time  date null ,            --创建日期
last_modify_time date null,           --更新日期
create_user   VARCHAR(256) NULL       --创建人
)tablespace FT_TBS
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
 
--KEY TRIGGER
CREATE OR REPLACE TRIGGER TRIGGER_new_part_ref
BEFORE INSERT ON new_part_ref FOR EACH ROW WHEN (new.part_ref_key is null)
begin
select bph_sequence.nextval into:new.part_ref_key from dual;
end;  

-----------T_SYS_OPERATE_LOG-----
create table T_SYS_OPERATE_LOG (
id number(18) not null,         --主键
op_type    number(2) NULL,      --操作方式增删改查0,1,2,3
CONTENT   VARCHAR(256) NULL,    --操作内容
OPERATOR  VARCHAR(256) NULL,    --操作人
OPERATE_TIME  date null         --操作日期
)tablespace FT_TBS
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
 
--KEY TRIGGER
CREATE OR REPLACE TRIGGER TRIGGER_SYS_OPERATE_LOG
BEFORE INSERT ON T_SYS_OPERATE_LOG FOR EACH ROW WHEN (new.id is null)
begin
select bph_sequence.nextval into:new.id from dual;
end;  


