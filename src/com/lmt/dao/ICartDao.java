package com.lmt.dao;

import java.util.List;

import com.lmt.model.Cart;
import com.lmt.util.PageBean;

public interface ICartDao {
	/**
	 * 新增缺勤数据
	 * @param Cart
	 * @return
	 */
	public boolean save(Cart cart);
	
	/**
	 * 删除缺勤数据
	 * @param Cart
	 * @return
	 */
	public boolean delete(Cart cart);
	
	/**
	 * 更新缺勤数据
	 * @param Cart
	 * @return
	 */
	public boolean update(Cart cart);
	
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
	public Cart getById(String id);
	
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
