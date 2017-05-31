package com.yunqiu.controller.permissions;




import com.yunqiu.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuJson {

	//计算导航菜单
	public static String getMenuJson(List<Menu> listAll){
		StringBuffer meu=new StringBuffer();
		 meu.append("[");
		 for(int i=0;i<listAll.size();i++){
			 Menu m=listAll.get(i);
			 if(m.getType()==1){
				 meu.append("{\"mid\":\""+m.getMenuId()+"\",");
				 meu.append("\"icon\":\""+m.getIcon()+"\",");
				 meu.append("\"menuname\":\""+m.getName()+"\",");
				 meu.append("\"url\":\""+m.getUrl()+"\",");
				 meu.append("\"menus\":[");
				 for(int it=0;it<listAll.size();it++){
					 Menu mu=listAll.get(it);
					 if(m.getType()==2 || mu.getFather().equals(m.getMenuId())){
						 meu.append("{\"mid\":\""+mu.getMenuId()+"\",");
						 meu.append("\"menuname\":\""+mu.getName()+"\",");
						 meu.append("\"icon\":\""+mu.getIcon()+"\",");
						 meu.append("\"url\":\""+mu.getUrl()+"\"},");
					 }
				 }
				 meu.append("]},");
			 }
		 }
		 meu.append("]");

		return meu.toString();
	}
    //计算授权树形图
	public static List<MenuRoleJson> getTree(List<Menu> list, List<String> permissionsList){
		//存储实体类对象
		List<MenuRoleJson> list2=new ArrayList<MenuRoleJson>();
		//存储字段对象
		List<MenuRoleJson> list3=null;
		//遍历目录集合，取相应值
		for(int i=0;i<list.size();i++){
			//实例授权Json实体类
			MenuRoleJson mJson=new MenuRoleJson();
			//获取到目录实体
			Menu me=list.get(i);
			mJson.setId(me.getMenuId());
			mJson.setpId(me.getFather());
			mJson.setName(me.getName());
			mJson.setOpen(true);

			//查看用户角色是否有权限
			if (permissionsList.contains(me.getMenuId())) {
				mJson.setChecked(true);
			}
			list2.add(mJson);
		}
		return list2;
	}



}
