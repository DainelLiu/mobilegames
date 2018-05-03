package com.lmt.action;

import java.io.IOException;
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

import com.lmt.dao.IAnnouncementDao;
import com.lmt.dao.IUsersDao;
import com.lmt.model.Announcement;
import com.lmt.model.Users;
import com.lmt.util.JsonUtil;
import com.lmt.util.PageBean;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
// 表示继承的父包
@Namespace(value = "/announcement")
public class AnnouncementAction {

	private IAnnouncementDao announcementDao;

	public IAnnouncementDao getAnnouncementDao() {
		return announcementDao;
	}

	@Resource(name = "AnnouncementDao")
	public void setAnnouncementDao(IAnnouncementDao announcementDao) {
		this.announcementDao = announcementDao;
	}

	private IUsersDao usersDao;

	public IUsersDao getUsersDao() {
		return usersDao;
	}

	@Resource(name = "UsersDao")
	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}

	/**
	 * 保存缺勤信息
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "save")
	public String save() throws IOException {
		String aDescribe = ServletActionContext.getRequest().getParameter("aDescribe");
		String aUId = ServletActionContext.getRequest().getParameter("aUId");
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Users users = usersDao.getById(aUId);
		// System.out.println(df.format(day));

		Announcement announcement = new Announcement();
		announcement.setaDescribe(aDescribe);
		announcement.setaUId(users);
		announcement.setaTime(df.format(day));
		announcement.setaSign(0);
		JSONObject jobj = new JSONObject();
		if (announcementDao.save(announcement)) {
			jobj.put("mes", "保存成功!");
			jobj.put("status", "success");
		} else {
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;

	}

	/**
	 * 删除缺勤信息
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "delete")
	public String delete() throws IOException {

		String aId = ServletActionContext.getRequest().getParameter("aId");
		Announcement announcement = announcementDao.getById(aId);
		JSONObject jobj = new JSONObject();
		if (announcementDao.delete(announcement)) {
			// save success
			jobj.put("mes", "删除成功!");
			jobj.put("status", "success");
		} else {
			// save failed
			jobj.put("mes", "删除失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}

	/**
	 * 修改缺勤信息
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "update")
	public String update() throws IOException {

		String aId = ServletActionContext.getRequest().getParameter("aId");

		Announcement announcement = announcementDao.getById(aId);
		JSONObject jobj = new JSONObject();

		if (announcementDao.update(announcement)) {
			jobj.put("mes", "更新成功!");
			jobj.put("status", "success");
		} else {
			// save failed
			jobj.put("mes", "更新失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}

	/**
	 * 根据id信息
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "getById")
	public String getById() throws IOException {
		String aId = ServletActionContext.getRequest().getParameter("aid");
		Announcement announcement = announcementDao.getById(aId);
		JSONObject jobj = new JSONObject();
		if (announcement != null) {
			// save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
		} else {
			// save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}

	/**
	 * 获取品牌(类型)列表
	 * 
	 * @return
	 * @throws IOException
	 */
	@Action(value = "list")
	public String list() throws IOException {
		// 分页
		String pageNumStr = ServletActionContext.getRequest().getParameter("pageNum");
		int pageNum = 1;
		if (pageNumStr != null && !"".equals(pageNumStr)) {
			pageNum = Integer.parseInt(pageNumStr);
		}
		List<Object> list = new ArrayList<Object>();
		List<Object> announcementTypelist = announcementDao.list();// 获取所有类型数据，不带分页
		PageBean page = null;
		if (announcementTypelist.size() > 0) {
			page = new PageBean(announcementTypelist.size(), pageNum, 10);
			list = announcementDao.listAll(page);// 带分页
		}
		JSONObject jobj = new JSONObject();
		if (announcementTypelist.size() > 0) {
			// save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(list));
			jobj.put("pageTotal", page.getPageCount());
			jobj.put("pageNum", page.getPageNum());
		} else {
			// save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}

	@Action(value = "listAll")
	public String listAll() throws IOException {

		List<Object> announcementTypelist = announcementDao.list();// 获取所有类型数据，不带分页
		JSONObject jobj = new JSONObject();
		if (announcementTypelist.size() > 0) {
			// save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(announcementTypelist));
		} else {
			// save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}

}
