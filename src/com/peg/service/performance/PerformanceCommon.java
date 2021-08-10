package com.peg.service.performance;

/**
 * 常用字段常量<br>
 * 主要用于日志记录时传入字段标识信息
 * @author xuanm
 *
 */
public interface PerformanceCommon {
	
	/**
	 * 增加
	 */
	String CREATE = "create";
	
	/**
	 * 删除
	 */
	String DELETE = "delete";
	
	/**
	 * 修改
	 */
	String UPDATE = "update";
	
	/**
	 * 查询
	 */
	String RETRIEVE = "retrieve";
	
	/**
	 * 增加
	 */
	String CREATESTRING = "增加";
	
	/**
	 * 删除
	 */
	String DELETESTRING = "删除";
	
	/**
	 * 修改
	 */
	String UPDATESTRING = "修改";
	
	/**
	 * 查询
	 */
	String RETRIEVESTRING = "查询";
	
	/**
	 * 绩效指标设定
	 */
	String INDEX_SET = "绩效指标设定";
	
	/**
	 * 年度指标设定
	 */
	String YEAR_INDEX_SET = "年度指标设定";
	
	/**
	 * 月度指标设定
	 */
	String MONTH_INDEX_SET = "月度指标设定";
	
	/**
	 * 月度指标结果设定
	 */
	String MONTH_INDEX_RESULT_SET = "月度指标结果设定";
	
	/**
	 * 修改内容字段
	 */
	String UPDATECONTENT = "【无】";
	
	/**
	 * 修改原因
	 */
	String UPDATEREASON = "修改原因";
	
	/**
	 * 保存成功
	 */
	String INSERT_SUCCESS_INFO = "保存信息成功";
	
	/**
	 * 删除成功
	 */
	String DELETE_SUCCESS_INFO = "删除信息成功";
	
	/**
	 * 查询成功
	 */
	String QUERY_SUCCESS_INFO = "查询信息成功";
	
	/**
	 * 修改成功
	 */
	String UPDATE_SUCCESS_INFO = "修改信息成功";
	
	/**
	 * 保存失败，请联系相关人员
	 */
	String INSERT_ERROR_INFO = "保存信息失败，请联系相关人员";
	
	/**
	 * 删除失败，请联系相关人员
	 */
	String DELETE_ERROR_INFO = "删除信息失败，请联系相关人员";
	
	/**
	 * 查询失败，请联系相关人员
	 */
	String QUERY_ERROR_INFO = "查询信息失败，请联系相关人员";
	
	/**
	 * 修改失败，请联系相关人员
	 */
	String UPDATE_ERROR_INFO = "修改信息失败，请联系相关人员";
	
	/**
	 * 该记录下存在年度指标或者月度指标，无法进行删除操作
	 */
	String HAS_OTHER_RELEVANT_ORDER = "该记录下存在年度指标或者月度指标，无法进行删除操作";
	
	/**
	 * 插入或更新失败时提示信息
	 */
	String INPUT_UPDATE_ERROR_INFO = "不允许添加相同的年度，工厂、部门、绩效大类、指标内容、指标类型";
	
	/**
	 * 该年度绩效值已经存在，不允许重复添加
	 */
	String INSERT_YEAR_ERROR = "该年度绩效值已经存在，不允许重复添加";
	
	/**
	 * 未设置年度指标值之前无法进行修改
	 */
	String YEAR_INDEX_NOT_EXIST = "未设置年度指标值之前无法进行修改";
	
	/**
	 * 年度指标值不存在，无法进行删除操作
	 */
	String DELETE_YEAR_ERROR_INFO = "年度指标值不存在，无法进行删除操作";
	
	/**
	 * 该年度指标下存在月度指标，不允许删除操作
	 */
	String YEAT_INDEX_HAS_MONTH_INDEX = "该年度指标下存在月度指标，不允许删除操作";
	
	/**
	 * 未选择
	 */
	String NOTSELECT = "未选择";
	
	/**
	 * 未正确填写
	 */
	String INPUTERROR = "未正确填写";
	
	/**
	 * 信息
	 */
	String INFORMATION = "信息";
	
	/**
	 * 要求输入数值型数据
	 */
	String REQUIRED_NUMBER_TYPE = "要求输入数值型数据";
	
	/**
	 * 创建人
	 */
	String CREATE_USER = "创建人";
	
	/**
	 * 创建时间
	 */
	String CREATE_TIME = "创建时间";
	
	/**
	 * 年度
	 */
	String CHECKYEAR = "年度";
	
	/**
	 * 工厂名称
	 */
	String FACTORYNAME = "工厂名称";
	
	/**
	 * 部门名称
	 */
	String DEPARTMENTNAME = "部门名称";
	
	/**
	 * 绩效目标大类
	 */
	String PERFORMANCETARGETCLASS = "绩效目标大类";
	
	/**
	 * 绩效类型 
	 */
	String PERFORMANCETYPE = "绩效类型";
	
	/**
	 * 指标内容
	 */
	String INDEXCONTENT = "指标内容";
	
	/**
	 * 指标类型
	 */
	String INDEXTYPE = "指标类型";
	
	/**
	 * 权重
	 */
	String WEIGHT = "权重";
	
	/**
	 * 单位
	 */
	String COMPANY = "单位";
	
	/**
	 * 计算公式
	 */
	String FORMULA = "计算公式";
	
	/**
	 * 备注<br>
	 * 这里为了和年度指标中的基准值、中间值、目标值区分，所以在字段前面加上“INDEX_”前缀
	 */
	
	/**
	 * 小于基准
	 */
	String INDEX_REFERENCEVALUE = "小于基准";
	
	/**
	 * 基准与目标之间
	 */
	String INDEX_MIDDLEVALUE = "基准与目标之间";
	
	/**
	 * 大于目标
	 */
	String INDEX_TARGETVALUE = "大于目标";
	
	/**
	 * 上年度实际值
	 */
	String LASTYEARREALITYVALUE = "上年度实际值";
	
	/**
	 * 上半年基准值
	 */
	String FIRSTYEARREFERENCEVALUE = "上半年基准值";
	
	/**
	 * 本年度基准值
	 */
	String REFERENCEVALUE = "本年度基准值";
	
	/**
	 * 本年度目标值
	 */
	String TARGETVALUE = "本年度目标值";
	
	/**
	 * 上半年目标值
	 */
	String FIRSTYEARTARGETVALUE = "上半年目标值";
	
	/**
	 * 下半年目标值
	 */
	String SECONDYEARTARGETVALUE = "下半年目标值";
	
	/**
	 * 没有新增任何月度绩效指标
	 */
	String NO_MONTH_INDEX = "没有新增任何月度绩效指标";
	
	/**
	 * 当月目标值
	 */
	String MONTARGETVALUE = "当月目标值";
	
	/**
	 * 当月累计目标值
	 */
	String MONTOTALTARGETVALUE = "当月累计目标值";
	
	/**
	 * 当月挑战目标值
	 */
	String MONCHALLENGETARGETVALUE = "当月挑战目标值";
	
	/**
	 * 当月实际目标值
	 */
	String MONREALITYTARGETVALUE = "当月实际值";
	
	/**
	 * 当月实际值
	 */
	String MONREALITYVALUE = "当月实际值";
	
	/**
	 * 当月实际累计目标值
	 */
	String MONREALITYTOTALTARGETVALUE = "累计目标值";
	
	/**
	 * 累计实际值
	 */
	String MONREALITYTOTALVALUE = "累计实际值";
	
	/**
	 * 当月实际挑战目标值
	 */
	String MONREALITYCHALLENGETARGETVALUE = "当月实际挑战目标值";
	
	/**
	 * 该月度绩效目标已经存在，不允许重复添加
	 */
	String MONTH_INDEX_EXIST = "该月度绩效目标已经存在，不允许重复添加";
	
	/**
	 * 没有要修改的记录
	 */
	String NO_INDEX_TO_UPDATE = "没有要修改的记录";
	
	/**
	 * 没有要删除的记录
	 */
	String NO_INDEX_TO_DELETE = "没有要删除的记录";
	
	/**
	 * 月份
	 */
	String MYMONTH = "月份";
	
	/**
	 * 年度指标值为空
	 */
	String YEAR_INDEX_VALUE_NULL = "年度指标值为空";
	
	/**
	 * 指标型
	 */
	String INDEX_TYEP = "指标型";
	
	/**
	 * 项目型
	 */
	String PROGRAME_TYPE = "项目型";
	
	/**
	 * 望大型
	 */
	String BIG_THAN_TARGET_TYPE = "望大型";
	
	/**
	 * 望小型
	 */
	String LESS_THAN_TARGET_TYPE = "望小型";
	
	/**
	 * 望目型
	 */
	String EQUAL_TARGET_TYPE = "望目型";
	
	/**
	 * 已达标
	 */
	String IS_TRAGET = "已达标";
	
	/**
	 * 未达标
	 */
	String NOT_TARGET = "未达标";
	
	/**
	 * 正常进行
	 */
	String INDEX_NORMAL = "正常进行";
	
	/**
	 * 延期
	 */
	String INDEX_DELAY = "延期";
	
	/**
	 * 终止
	 */
	String INDEX_END = "终止";
	
	/**
	 * 从数据库中获取指标信息错误，请联系相关人员检查数据库中记录的完整性
	 */
	String MONTH_INDEX_ERROR_FROM_DATABASE = "从数据库中获取指标信息错误，请联系相关人员检查数据库中记录的完整性";
	
	/**
	 * 该月度绩效指标值已经存在，不允许重复添加
	 */
	String MONTH_INDEX_RESULT_EXIST = "月度绩效指标结果值已经存在，不允许重复添加";
	
	/**
	 * 月度绩效指标结果值不存在，不允许修改
	 */
	String MONTH_INDEX_RESULT_NOT_EXIST = "月度绩效指标结果值不存在，不允许修改";
	
	String MONTH_INDEX_RESULT_NOT_EXIST_DELETE = "月度绩效指标结果值不存在，不允许删除";
	
	/**
	 * 考核结果
	 */
	String MONCHECKRESULT = "考核结果";
	
	/**
	 * 状态
	 */
	String COLUMN1 = "状态";
	
	
}
