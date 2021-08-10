package com.peg.model.bph;

import java.util.Date;
import java.util.List;

import com.peg.model.baseCommonVo;

public class MonthlyAssessment  extends baseCommonVo{
    private Long id;

    private String factory;

    private String area;

    private String month;   //月份

    private String shiftgroupCategory;   //班组类别

    private String checkItem;     //考核项目

    private String checkIndex;    //考核指标
    
    private double itemScale;     //项目比例
   
	private double scale;         //指标比例

    private String mykey;         //是否关键值

    private double baseValue;     //基准值
 
    private double targetValue;    //目标值

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;
    
    private String indexCode;        //指标代码
    
    private Long indexId;            //指标Id
    
    private String col1;  //备用字段1
    
    private String col2;  //备用字段1
    
    private String col3;  //备用字段1
    
    private String itemCode;  //项目代码
    
    private List<CheckIndex> checkIndexs;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }


	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getShiftgroupCategory() {
        return shiftgroupCategory;
    }

    public void setShiftgroupCategory(String shiftgroupCategory) {
        this.shiftgroupCategory = shiftgroupCategory == null ? null : shiftgroupCategory.trim();
    }

    public String getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem == null ? null : checkItem.trim();
    }

    public String getCheckIndex() {
        return checkIndex;
    }

    public void setCheckIndex(String checkIndex) {
        this.checkIndex = checkIndex == null ? null : checkIndex.trim();
    }

    public String getMykey() {
        return mykey;
    }

    public void setMykey(String mykey) {
        this.mykey = mykey == null ? null : mykey.trim();
    }


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

	public double getItemScale() {
		return itemScale;
	}

	public void setItemScale(double itemScale) {
		this.itemScale = itemScale;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public double getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(double baseValue) {
		this.baseValue = baseValue;
	}

	public double getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(double targetValue) {
		this.targetValue = targetValue;
	}

	public String getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	public Long getIndexId() {
		return indexId;
	}

	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public List<CheckIndex> getCheckIndexs() {
		return checkIndexs;
	}

	public void setCheckIndexs(List<CheckIndex> checkIndexs) {
		this.checkIndexs = checkIndexs;
	}
    	

}