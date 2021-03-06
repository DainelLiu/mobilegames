package com.lmt.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.lmt.dao.IUsersDao;
import com.lmt.model.Users;
import com.lmt.util.JsonUtil;
import com.lmt.util.PageBean;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/users")
public class UsersAction {
	
	private IUsersDao usersDao;
	
	public IUsersDao getUsersDao() {
		return usersDao;
	}
	@Resource(name="UsersDao")
	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}
	

	/**
	 * 保存缺勤信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="save")
	public String save() throws IOException{
		
		String uName = ServletActionContext.getRequest().getParameter("uName");
		String uPassword = ServletActionContext.getRequest().getParameter("uPassword");
		BigDecimal uFraction = new BigDecimal(ServletActionContext.getRequest().getParameter("uFraction"));
		String uPhone = ServletActionContext.getRequest().getParameter("uPhone");
		String uMail = ServletActionContext.getRequest().getParameter("uMail");
		String uPictuer = ServletActionContext.getRequest().getParameter("uPictuer");
		String uPower = ServletActionContext.getRequest().getParameter("uPower");
		BigDecimal uMonery = new BigDecimal(ServletActionContext.getRequest().getParameter("uMonery"));
		
		
		Users users = new Users();
		
		users.setuName(uName);
		users.setuPassword(uPassword);
		users.setuFraction(uFraction);
		users.setuPhone(uPhone);
		users.setuMail(uMail);
		users.setuPicture("images/img01.png");
		users.setuGrade(1);
		users.setuPower(uPower);
		users.setuMonery(uMonery);
		users.setuSign(0);
		JSONObject jobj = new JSONObject();
		if(usersDao.save(users)) {
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
		
		
		String uId = ServletActionContext.getRequest().getParameter("uId");
		Users users = usersDao.getById(uId);
		JSONObject jobj = new JSONObject();
		if(usersDao.delete(users)){
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
		
		String uId = ServletActionContext.getRequest().getParameter("uId");
		String uPassword = ServletActionContext.getRequest().getParameter("uPassword");
		String uPhone = ServletActionContext.getRequest().getParameter("uPhone");
		String uMail = ServletActionContext.getRequest().getParameter("uMail");
		BigDecimal uMonery = new BigDecimal(ServletActionContext.getRequest().getParameter("uMonery"));
		int uSign = Integer.parseInt(ServletActionContext.getRequest().getParameter("uSign"));
		Users users = usersDao.getById(uId);
		if(uMonery != null){
			users.setuMonery(uMonery);
		}
		if(uPassword != null){
			users.setuPassword(uPassword);
		}
		if(uPhone != null){
			users.setuPhone(uPhone);
		}
		if(uMail != null){
			users.setuMail(uMail);
		}
		JSONObject jobj = new JSONObject();
		
		if(usersDao.update(users)) {
			jobj.put("mes", "更新成功!");
			jobj.put("status", "success");
			jobj.put("loginUser", users);
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
		String uId = ServletActionContext.getRequest().getParameter("uId");
		Users users = usersDao.getById(uId);
		JSONObject jobj = new JSONObject();
		if(users != null){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data",users);
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
		List<Object> usersTypelist = usersDao.list();//获取所有类型数据，不带分页
		PageBean page=null;
		if(usersTypelist.size()>0){
			page = new PageBean(usersTypelist.size(),pageNum,5);
			list = usersDao.listAll(page);//带分页
		}
		JSONObject jobj = new JSONObject();
		if(usersTypelist.size() > 0){
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

		List<Object> usersTypelist = usersDao.list();//获取所有类型数据，不带分页
		JSONObject jobj = new JSONObject();
		if(usersTypelist.size() > 0){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(usersTypelist));
		}else{
			//save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	@Action(value="login")
	public String login() throws IOException{
		String uName = URLDecoder.decode(ServletActionContext.getRequest().getParameter("uName"), "utf-8");
		String uPassword = ServletActionContext.getRequest().getParameter("uPwd");
		String hql = "from Users where uName='"+uName+"' and uPassword='"+uPassword+"'";
		List<Object> usersTypelist = usersDao.getAllByConds(hql);//获取所有类型数据，不带分页
		JSONObject jobj = new JSONObject();
		if(usersTypelist.size() > 0){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("loginUser", usersTypelist.get(0));
		}else{
			//save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	@Action(value="updateToSign")
	public String updateToSign() throws IOException{
		
		String uId = ServletActionContext.getRequest().getParameter("uId");
		int uSign = Integer.parseInt(ServletActionContext.getRequest().getParameter("uSign"));
		Users users = usersDao.getById(uId);
		users.setuSign(uSign);
		JSONObject jobj = new JSONObject();
		
		if(usersDao.update(users)) {
			jobj.put("mes", "更新成功!");
			jobj.put("status", "success");
			jobj.put("loginUser", users);
		}else{
			//save failed
			jobj.put("mes", "更新失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	@Action(value="getByUPower")
	public String getByUPower() throws IOException{
		String hql="from Users where 1=1 and uPower='0'"; 
		List usersList = usersDao.getAllByConds(hql);
		Users users= (Users) usersList.get(0);
		JSONObject jobj = new JSONObject();
		if(users != null){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data",users);
		}else{
			//save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	@Action(value="listByBack")
	public String listByBack() throws IOException{
		//分页
		String pageNumStr = ServletActionContext.getRequest().getParameter("pageNum");
		String hql ="from Users where 1=1 and uPower !='0'";
		int pageNum = 1;
		if(pageNumStr!=null && !"".equals(pageNumStr)){
			pageNum = Integer.parseInt(pageNumStr);
		}
		List<Object> list = new ArrayList<Object>();
		List<Object> usersTypelist = usersDao.getAllByConds(hql);//获取所有类型数据，不带分页
		PageBean page=null;
		if(usersTypelist.size()>0){
			page = new PageBean(usersTypelist.size(),pageNum,5);
			list = usersDao.listAll(page);//带分页
		}
		JSONObject jobj = new JSONObject();
		if(usersTypelist.size() > 0){
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
