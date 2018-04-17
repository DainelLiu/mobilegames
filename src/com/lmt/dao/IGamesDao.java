package com.lmt.dao;

import java.util.List;

import com.lmt.model.Games;
import com.lmt.util.PageBean;

public interface IGamesDao {
	/**
	 * 新增缺勤数据
	 * @param Games
	 * @return
	 */
	public boolean save(Games games);
	
	/**
	 * 删除缺勤数据
	 * @param Games
	 * @return
	 */
	public boolean delete(Games games);
	
	/**
	 * 更新缺勤数据
	 * @param Games
	 * @return
	 */
	public boolean update(Games games);
	
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
	public Games getById(String id);
	
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
