package com.rz;

public class tbtype {
//	用tbtype定义的对象，拥有了类名，父类，父id,类别排名
	private int id;
	private String typename ;
	private String parentname ;
	private int parentid;
	private String fullpath;
	private int levelnum;
	 
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public int getId() {
	 return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typename;
	}
	public void setTypeName(String typename) {
	 this.typename = typename;
	}
	public String getParentName() {
	 return parentname;
	}
	public void setParentName(String parentname) {
	 this.parentname = parentname;
	}
	
	public String getFullPath() {
		 return fullpath;
	}
	public void setFullPath(String fullpath) {
	 this.fullpath = fullpath;
	}
		
	public int getlevelnum() {
	 return levelnum;
	}
	public void setlevelnum(int levelnum) {
	 this.levelnum = levelnum;
	}

}
