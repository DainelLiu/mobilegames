package com.lmt.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.lmt.dao.IGamesDao;
import com.lmt.dao.IZoneDao;
import com.lmt.model.Zone;
import com.lmt.util.JsonUtil;
import com.lmt.util.PageBean;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/zone")
public class ZoneAction {
	
	private IZoneDao zoneDao;
	
	public IZoneDao getZoneDao() {
		return zoneDao;
	}
	@Resource(name="ZoneDao")
	public void setZoneDao(IZoneDao zoneDao) {
		this.zoneDao = zoneDao;
	}
	
private IGamesDao gamesDao;
	
	public IGamesDao getGamesDao() {
		return gamesDao;
	}
	@Resource(name="GamesDao")
	public void setGamesDao(IGamesDao gamesDao) {
		this.gamesDao = gamesDao;
	}
	

	/**
	 * 保存缺勤信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="save")
	public String save() throws IOException{
		String zDescribe = URLDecoder.decode(ServletActionContext.getRequest().getParameter("zDescribe"),"utf-8");
		String zGaId = ServletActionContext.getRequest().getParameter("zGaId");
		
		Zone zone = new Zone();
		zone.setzDescribe(zDescribe);
		zone.setzGaId(gamesDao.getById(zGaId));
		JSONObject jobj = new JSONObject();
		if(zoneDao.save(zone)) {
			jobj.put("mes", "保存成功!");
			jobj.put("status", "success");
		}else {
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
		
	}
	/**
	 * 删除缺勤信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="delete")
	public String delete() throws IOException{
		
		
		String zId = ServletActionContext.getRequest().getParameter("zId");
		Zone zone = zoneDao.getById(zId);
		JSONObject jobj = new JSONObject();
		if(zoneDao.delete(zone)){
			//save success
			jobj.put("mes", "删除成功!");
			jobj.put("status", "success");
		}else{
			//save failed
			jobj.put("mes", "删除失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	/**
	 * 修改缺勤信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="update")
	public String update() throws IOException{
		
		String zId = ServletActionContext.getRequest().getParameter("zId");
		
		Zone zone = zoneDao.getById(zId);
		JSONObject jobj = new JSONObject();
		
		if(zoneDao.update(zone)) {
			jobj.put("mes", "更新成功!");
			jobj.put("status", "success");
		}else{
			//save failed
			jobj.put("mes", "更新失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	/**
	 * 根据id信息
	 * @return
	 * @throws IOException
	 */
	@Action(value="getById")
	public String getById() throws IOException{
		String zId = ServletActionContext.getRequest().getParameter("zId");
		Zone zone = zoneDao.getById(zId);
		JSONObject jobj = new JSONObject();
		if(zone != null){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
		}else{
			//save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	/**
	 * 获取品牌(类型)列表
	 * @return
	 * @throws IOException
	 */
	@Action(value="list")
	public String list() throws IOException{
		//分页
		String pageNumStr = ServletActionContext.getRequest().getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr!=null && !"".equals(pageNumStr)){
			pageNum = Integer.parseInt(pageNumStr);
		}
		List<Object> list = new ArrayList<Object>();
		List<Object> zoneTypelist = zoneDao.list();//获取所有类型数据，不带分页
		PageBean page=null;
		if(zoneTypelist.size()>0){
			page = new PageBean(zoneTypelist.size(),pageNum,5);
			list = zoneDao.listAll(page);//带分页
		}
		JSONObject jobj = new JSONObject();
		if(zoneTypelist.size() > 0){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(list));
			jobj.put("pageTotal", page.getPageCount());
			jobj.put("pageNum", page.getPageNum());
		}else{
			//save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	@Action(value="listAll")
	public String listAll() throws IOException{

		List<Object> zoneTypelist = zoneDao.list();//获取所有类型数据，不带分页
		JSONObject jobj = new JSONObject();
		if(zoneTypelist.size() > 0){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(zoneTypelist));
		}else{
			//save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	@Action(value="listAllByGaId")
	public String listAllByGaId() throws IOException{

		String gaId = ServletActionContext.getRequest().getParameter("zGaId");
		List<Object> zoneTypelist = zoneDao.getAllByConds("from Zone where zGaId='" + gaId + "'");//获取所有类型数据，不带分页
		JSONObject jobj = new JSONObject();
		if(zoneTypelist.size() > 0){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");

			jobj.put("data", JsonUtil.toJsonByListObj(zoneTypelist));
		}else{
			//save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}

}
