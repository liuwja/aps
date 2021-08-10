package com.peg.model.jxmb;

public class PerMember {
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
    
    
    private long itemKey;

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

    public Long getOldKey() {
        return oldKey;
    }

    public void setOldKey(Long oldKey) {
        this.oldKey = oldKey;
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

    public String getGroupCategory() {
        return groupCategory;
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
        this.groupCode = groupCode == null ? null : groupCode.trim();
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

	public long getItemKey() {
		return itemKey;
	}

	public void setItemKey(long itemKey) {
		this.itemKey = itemKey;
	}
}