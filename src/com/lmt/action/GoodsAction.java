package com.lmt.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.lmt.dao.IGoodsDao;
import com.lmt.dao.IStyleDao;
import com.lmt.dao.IUsersDao;
import com.lmt.dao.IZoneDao;
import com.lmt.model.Goods;
import com.lmt.util.JsonUtil;
import com.lmt.util.PageBean;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/goods")
public class GoodsAction {
	
	private IGoodsDao goodsDao;
	
	public IGoodsDao getGoodsDao() {
		return goodsDao;
	}
	@Resource(name="GoodsDao")
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	
	private IStyleDao styleDao;
	
	public IStyleDao getStyleDao() {
		return styleDao;
	}
	@Resource(name="StyleDao")
	public void setStyleDao(IStyleDao styleDao) {
		this.styleDao = styleDao;
	}

private IUsersDao usersDao;
	
	public IUsersDao getUsersDao() {
		return usersDao;
	}
	@Resource(name="UsersDao")
	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}
private IZoneDao zoneDao;
	
	public IZoneDao getZoneDao() {
		return zoneDao;
	}
	@Resource(name="ZoneDao")
	public void setZoneDao(IZoneDao zoneDao) {
		this.zoneDao = zoneDao;
	}
	
	/**
	 * 保存缺勤信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="save")
	public String save() throws IOException{
		
		String gName = ServletActionContext.getRequest().getParameter("gName");
		String gSId = ServletActionContext.getRequest().getParameter("gSId");
		BigDecimal gPrice = new BigDecimal(ServletActionContext.getRequest().getParameter("gPrice"));
		String gDescribe = ServletActionContext.getRequest().getParameter("gDescribe");
		String gSTime = ServletActionContext.getRequest().getParameter("gSTime");
		String gOTime = ServletActionContext.getRequest().getParameter("gOTime");
		String gDuration = ServletActionContext.getRequest().getParameter("gDuration");
		String gZId = ServletActionContext.getRequest().getParameter("gZId");
		String gUId = ServletActionContext.getRequest().getParameter("gUId");
		int gSign = Integer.parseInt(ServletActionContext.getRequest().getParameter("gSign"));
		
		Goods goods = new Goods();
		
		goods.setgName(gName);
		goods.setgSId(styleDao.getById(gSId));
		goods.setgPrice(gPrice);
		goods.setgDescribe(gDescribe);
		if(gSTime != null && !"".equals(gSTime)) {
			goods.setgSTime(gSTime);
		}
		if(gOTime != null && !"".equals(gOTime)) {
			goods.setgOTime(gOTime);
		}
		if(gDuration != null && !"".equals(gDuration)) {
			goods.setgDuration(gDuration);
		}
		goods.setgUId(usersDao.getById(gUId));
		goods.setgZId(zoneDao.getById(gZId));
		goods.setgSign(gSign);
		JSONObject jobj = new JSONObject();
		if(goodsDao.save(goods)) {
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
		
		
		String gId = ServletActionContext.getRequest().getParameter("gId");
		Goods goods = goodsDao.getById(gId);
		JSONObject jobj = new JSONObject();
		if(goodsDao.delete(goods)){
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
		
		String gId = ServletActionContext.getRequest().getParameter("gId");
		String gSign = ServletActionContext.getRequest().getParameter("gSign");
		Goods goods = goodsDao.getById(gId);
		goods.setgSign(Integer.parseInt(gSign));
		JSONObject jobj = new JSONObject();
		
		if(goodsDao.update(goods)) {
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
		String gId = ServletActionContext.getRequest().getParameter("gId");
		Goods goods = goodsDao.getById(gId);
		JSONObject jobj = new JSONObject();
		if(goods != null){
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
		List<Object> goodsTypelist = goodsDao.list();//获取所有类型数据，不带分页
		PageBean page=null;
		if(goodsTypelist.size()>0){
			page = new PageBean(goodsTypelist.size(),pageNum,10);
			list = goodsDao.listAll(page);//带分页
		}
		JSONObject jobj = new JSONObject();
		if(list.size() > 0){
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

		List<Object> goodsTypelist = goodsDao.list();//获取所有类型数据，不带分页
		JSONObject jobj = new JSONObject();
		if(goodsTypelist.size() > 0){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(goodsTypelist));
		}else{
			//save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	@Action(value="listByUId")
	public String listByUId() throws IOException{
		//分页
		String pageNumStr = ServletActionContext.getRequest().getParameter("pageNum");
		String uId = ServletActionContext.getRequest().getParameter("gUId");
		int pageNum = 1;
		if(pageNumStr!=null && !"".equals(pageNumStr)){
			pageNum = Integer.parseInt(pageNumStr);
		}
		List<Object> list = new ArrayList<Object>();
		List<Object> goodsTypelist = goodsDao.list();//获取所有类型数据，不带分页
		PageBean page=null;
		if(goodsTypelist.size()>0){
			page = new PageBean(goodsTypelist.size(),pageNum,10);
			list = goodsDao.getByConds("from Goods where gUId='"+uId+"'", page);//带分页
		}
		JSONObject jobj = new JSONObject();
		if(list.size() > 0){
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

}
