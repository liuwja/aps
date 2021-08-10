/**
创建dblink(测试库,必须使用trqqms用户创建)
**/
create  database link MES_TEST_LINK  
connect to trqmesda1 identified by "trqmesda1" using '192.168.100.91:1521/TRQMESDB';

/**
创建原材料dblink（正式库）
**/
create  database link MES_PART_LINK  
connect to prqmesda1 identified by "prqmesda1" using '192.168.214.178:1521/PRQMESDB';


/**
创建dblink(mes184,必须使用trqqms用户创建)
**/
create  database link MES_TEST_LINK  
connect to prqmesda1 identified by "prqmesda1" using '192.168.214.180:1521/PRQMESDB';

/**
创建dblink(测试库,必须使用trqqms用户创建)
**/
create  database link QMS_TEST_LINK  
connect to trqqms identified by "trqqms" using '192.168.100.160:1521/tmesd1';


/**
查询已有的dblink(必须使用system用户)
**/
select * from dba_db_links

/**
删除dblink(必须使用trqqms用户才可以删除)
**/
drop database link MES_TEST_LINK
/**
创建dblink(正式库,必须使用trqqms用户创建)
**/
create  database link MES_TEST_LINK  
connect to prqmesda1 identified by "prqmesda1" using '192.168.100.94:1521/PRQMESDB'

/**
mes库创建dblink（system用户创建）
**/
create public database link QMS_DB_LINK  
connect to trqqms identified by "trqqms" using '192.168.100.160:1521/tmesd1'
/**
mes历史库创建dblink（trqqms用户创建）
**/
create  database link MES_TEST_LINK  
connect to prqhisda1 identified by "prqhisda1" using '192.168.100.64:1521/MESHIS'
/**
删除
**/
drop public database link QMS_DB_LINK

/**
查询同义词
**/
select * from user_synonyms;
/**
根据已有同义词生成创建同义词脚本
**/
select 'create synonym ' ||SYNONYM_NAME||' for '||TABLE_NAME ||'@MES_TEST_LINK'||';' crtSql from user_synonyms;
/**
创建同义词脚本
**/
create synonym APP_USER for APP_USER@MES_TEST_LINK;                                 
create synonym UNIT for UNIT@MES_TEST_LINK;                                         
create synonym WORK_ORDER_ITEMS for WORK_ORDER_ITEMS@MES_TEST_LINK;                 
create synonym PRODUCTION_LINE for PRODUCTION_LINE@MES_TEST_LINK;                   
create synonym AT_STORAGEORDER for AT_STORAGEORDER@MES_TEST_LINK;                   
create synonym PART for PART@MES_TEST_LINK;                                         
create synonym LOCATION for LOCATION@MES_TEST_LINK;                                 
create synonym UDA_PART for UDA_PART@MES_TEST_LINK;                                 
create synonym AT_ASSEMBLYPRODUCTBACK for AT_ASSEMBLYPRODUCTBACK@MES_TEST_LINK;     
create synonym AT_BATCHDEFECTRECORD for AT_BATCHDEFECTRECORD@MES_TEST_LINK;         
create synonym AT_FORMERPROCESSFQCCHECK for AT_FORMERPROCESSFQCCHECK@MES_TEST_LINK; 
create synonym AT_STAMPINGDAILYREPORT for AT_STAMPINGDAILYREPORT@MES_TEST_LINK;  
create synonym at_ProcessAuditRecord for at_ProcessAuditRecord@MES_TEST_LINK;
create synonym at_QualityImprovementRfp for at_QualityImprovementRfp@MES_TEST_LINK;
create synonym at_BoxDefectRecord for at_BoxDefectRecord@MES_TEST_LINK;
create synonym at_PaintingDailyReport for at_PaintingDailyReport@MES_TEST_LINK;
create synonym SITE for SITE@MES_TEST_LINK;  
create synonym AREA for AREA@MES_TEST_LINK;  
create synonym SITE_AREA for SITE_AREA@MES_TEST_LINK;
create synonym APP_GROUP for APP_GROUP@MES_TEST_LINK;
create synonym QMS_DATA for QMS_DATA@MES_TEST_LINK;
create synonym AT_INSPECTS for at_INSPECTS@MES_TEST_LINK;
create synonym defect_repair_entry  for defect_repair_entry@MES_TEST_LINK;
create synonym test_instance for test_instance@MES_TEST_LINK
create synonym work_order for work_order@MES_TEST_LINK
create synonym shift for shift@MES_TEST_LINK
create synonym at_testcodedefinition for at_testcodedefinition@MES_TEST_LINK
create synonym at_unitchangematerialrecord for at_unitchangematerialrecord@MES_TEST_LINK
create synonym  account for  account@MES_TEST_LINK 
create synonym  at_downlinerecord  for  at_downlinerecord@MES_TEST_LINK 
create synonym  at_stampingworkcheckingrecord for at_stampingworkcheckingrecord@MES_TEST_LINK 
create synonym at_paintingfrontrecord for at_paintingfrontrecord@MES_TEST_LINK 
create synonym unit_detail_pline_shift for unit_detail_pline_shift@MES_TEST_LINK 
create synonym part_class for part_class@MES_TEST_LINK 