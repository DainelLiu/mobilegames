package com.lmt.dao;

import java.util.List;

import com.lmt.model.Announcement;
import com.lmt.util.PageBean;

public interface IAnnouncementDao {
	/**
	 * 新增缺勤数据
	 * @param Announcement
	 * @return
	 */
	public boolean save(Announcement announcement);
	
	/**
	 * 删除缺勤数据
	 * @param Announcement
	 * @return
	 */
	public boolean delete(Announcement announcement);
	
	/**
	 * 更新缺勤数据
	 * @param Announcement
	 * @return
	 */
	public boolean update(Announcement announcement);
	
	/**
	 * 查询所有缺勤数据
	 * @return
	 */
	public List<Object> list();
	
	/**
	 * 查询所有缺勤数据带分页
	 * @return
	 */
	public List<Object> listAll(PageBean page);
	
	/**
	 * 根据主键id查询缺勤数据
	 * @param id
	 * @return
	 */
	public Announcement getById(String id);
	
	/**
	 * 根据其他条件查询缺勤数据带分页
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getByConds(String hql,PageBean page);
	
	/**
	 * 根据其他条件查询缺勤数据
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getAllByConds(String hql);

}
