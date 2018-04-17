package com.lmt.dao;

import java.util.List;

import com.lmt.model.Zone;
import com.lmt.util.PageBean;

public interface IZoneDao {
	/**
	 * 新增缺勤数据
	 * @param Zone
	 * @return
	 */
	public boolean save(Zone zone);
	
	/**
	 * 删除缺勤数据
	 * @param Zone
	 * @return
	 */
	public boolean delete(Zone zone);
	
	/**
	 * 更新缺勤数据
	 * @param Zone
	 * @return
	 */
	public boolean update(Zone zone);
	
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
	public Zone getById(String id);
	
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
