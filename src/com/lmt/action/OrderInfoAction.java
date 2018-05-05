package com.lmt.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.lmt.dao.IGoodsDao;
import com.lmt.dao.IOrderInfoDao;
import com.lmt.dao.IUsersDao;
import com.lmt.model.Goods;
import com.lmt.model.OrderInfo;
import com.lmt.util.JsonUtil;
import com.lmt.util.PageBean;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/orderInfo")
public class OrderInfoAction {
	
	private IOrderInfoDao orderInfoDao;
	
	public IOrderInfoDao getOrderInfoDao() {
		return orderInfoDao;
	}
	@Resource(name="OrderInfoDao")
	public void setOrderInfoDao(IOrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}
	
private IUsersDao usersDao;
	
	public IUsersDao getUsersDao() {
		return usersDao;
	}
	@Resource(name="UsersDao")
	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
private IGoodsDao goodsDao;
	
	public IGoodsDao getGoodsDao() {
		return goodsDao;
	}
	@Resource(name="GoodsDao")
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	

	/**
	 * 保存缺勤信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="save")
	public String save() throws IOException{
		
		String oUId = ServletActionContext.getRequest().getParameter("oUId");
		String oGId = ServletActionContext.getRequest().getParameter("oGId");
		BigDecimal oTotal = new BigDecimal(ServletActionContext.getRequest().getParameter("oTotal"));
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String oComplete = ServletActionContext.getRequest().getParameter("oComplete");
				
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setoUId(usersDao.getById(oUId));
		orderInfo.setoGId(goodsDao.getById(oGId));
		orderInfo.setoTotal(oTotal);
		orderInfo.setoDetermine(df.format(day));
		if(oComplete != null && !"".equals(oComplete)) {
			orderInfo.setoComplete(oComplete);
		}
		
		orderInfo.setoSign(1);
		
		JSONObject jobj = new JSONObject();
		if(orderInfoDao.save(orderInfo)) {
			Goods tempG = goodsDao.getById(oGId);
			tempG.setgSign(2);
			goodsDao.update(tempG);
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
		
		
		String oId = ServletActionContext.getRequest().getParameter("oId");
		OrderInfo orderInfo = orderInfoDao.getById(oId);
		JSONObject jobj = new JSONObject();
		if(orderInfoDao.delete(orderInfo)){
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
		
		String oId = ServletActionContext.getRequest().getParameter("oId");
		
		OrderInfo orderInfo = orderInfoDao.getById(oId);
		JSONObject jobj = new JSONObject();
		
		if(orderInfoDao.update(orderInfo)) {
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
		String oId = ServletActionContext.getRequest().getParameter("oId");
		OrderInfo orderInfo = orderInfoDao.getById(oId);
		JSONObject jobj = new JSONObject();
		if(orderInfo != null){
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
		List<Object> orderInfoTypelist = orderInfoDao.list();//获取所有类型数据，不带分页
		PageBean page=null;
		if(orderInfoTypelist.size()>0){
			page = new PageBean(orderInfoTypelist.size(),pageNum,10);
			list = orderInfoDao.listAll(page);//带分页
		}
		JSONObject jobj = new JSONObject();
		if(orderInfoTypelist.size() > 0){
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

		List<Object> orderInfoTypelist = orderInfoDao.list();//获取所有类型数据，不带分页
		JSONObject jobj = new JSONObject();
		if(orderInfoTypelist.size() > 0){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(orderInfoTypelist));
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
	public String String() throws IOException{
		//分页
		String pageNumStr = ServletActionContext.getRequest().getParameter("pageNum");
		String uId = ServletActionContext.getRequest().getParameter("oUId");
		int pageNum = 1;
		if(pageNumStr!=null && !"".equals(pageNumStr)){
			pageNum = Integer.parseInt(pageNumStr);
		}
		List<Object> list = new ArrayList<Object>();
		List<Object> orderInfoTypelist = orderInfoDao.list();//获取所有类型数据，不带分页
		PageBean page=null;
		if(orderInfoTypelist.size()>0){
			page = new PageBean(orderInfoTypelist.size(),pageNum,10);
			list = orderInfoDao.getByConds("from OrderInfo where oUId='"+uId+"'", page);//带分页
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
