package com.peg.model.bph;

public class Group {
    private Long groupKey;

    private Long categoryKey;
    
    private Long oldKey;

    private String factory;

    private String area;

    private String groupCategory;

    private String groupName;

    private String groupCode;

    private String groupDescription;

    private String productionLine;
    
    private GroupCategory uigroupCategory;
    
    private double groupScore;
    
    private String flag;     //标志位

    public Long getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(Long groupKey) {
        this.groupKey = groupKey;
    }

    public Long getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(Long categoryKey) {
        this.categoryKey = categoryKey;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public String getArea() {
        return area == null ? "" : area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getGroupCategory() {
        return groupCategory==null ? "" : groupCategory;
    }

    public void setGroupCategory(String groupCategory) {
        this.groupCategory = groupCategory == null ? null : groupCategory.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription == null ? null : groupDescription.trim();
    }

    public String getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(String productionLine) {
        this.productionLine = productionLine == null ? null : productionLine.trim();
    }

	public Long getOldKey() {
		return oldKey ==null ? 0L : oldKey;
	}

	public void setOldKey(Long oldKey) {
		this.oldKey = oldKey ;
	}

	public GroupCategory getUigroupCategory() {
		return uigroupCategory;
	}

	public void setUigroupCategory(GroupCategory uigroupCategory) {
		this.uigroupCategory = uigroupCategory;
	}

	public double getGroupScore() {
		return groupScore;
	}

	public void setGroupScore(double groupScore) {
		this.groupScore = groupScore;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
    
}