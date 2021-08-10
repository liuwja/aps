package com.peg.model.system;

import java.util.List;
import java.util.Map;

public class DataSource {
	
	private Long id;
	
	private String accordion;

	private String folder;
	
	private String menu;
	
	private String description;
	
	private List<String> folderList;
	
	private List<String> menuList;
	
	private List<Map<String, List<String>>> folderMap;
	
	private List<String> descriptionList;
	
	private String chartType;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccordion() {
		return accordion;
	}

	public void setAccordion(String accordion) {
		this.accordion = accordion;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<String> getFolderList() {
		return folderList;
	}

	public void setFolderList(List<String> folderList) {
		this.folderList = folderList;
	}

	public List<String> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<String> menuList) {
		this.menuList = menuList;
	}

	public List<Map<String, List<String>>> getFolderMap() {
		return folderMap;
	}

	public void setFolderMap(List<Map<String, List<String>>> folderMap) {
		this.folderMap = folderMap;
	}
	
	public List<String> getDescriptionList() {
		return descriptionList;
	}

	public void setDescriptionList(List<String> descriptionList) {
		this.descriptionList = descriptionList;
	}
	
	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
}
