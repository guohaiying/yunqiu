package com.yunqiu.controller.permissions;

public class MenuRoleJson {

	private String id;
	private String pId;
	private String name;
	private boolean checked;
	private boolean open;

    public void setId(String id) {
		this.id = id;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
    public void setOpen(boolean open) {
        this.open = open;
    }


}
