package com.lmt.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.lmt.dao.ICommentDao;
import com.lmt.model.Comment;
import com.lmt.util.PageBean;

@Component(value="CommentDao")
public class CommentDaoImpl implements ICommentDao {
	
	private SessionFactory sessionFactory;
	
	@Resource(name="sessionFactory")//sessionFactory注入
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean save(Comment comment) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String returnId = (String) session.save(comment);
		session.getTransaction().commit();
		session.close();
		if(!"".equals(returnId) && null != returnId){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean delete(Comment comment) {
		boolean result = false;
		try{
			if(comment != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.delete(comment);
				session.getTransaction().commit();
				session.close();
				result = true;
			}
		}catch(HibernateException e){
			result = false;
		}
		return result;
	}

	@Override
	public boolean update(Comment comment) {
		boolean result = false;
		try{
			if(comment != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.update(comment);
				session.getTransaction().commit();
				session.close();
				result = true;
			}
		}catch(HibernateException e){
			result = false;
		}
		return result;
	}
	
	@Override
	public List<Object> list() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from comment");
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public List<Object> listAll(PageBean page) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from comment");
		query.setFirstResult(page.getRowStart());
		query.setMaxResults(page.getPageSize());
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public Comment getById(String id) {
		Session session = sessionFactory.openSession();
		Comment dto = (Comment)session.get(Comment.class, id);
		session.close();
		return dto;
	}

	@Override
	public List<Object> getByConds(String hql,PageBean page) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(page.getRowStart());
		query.setMaxResults(page.getPageSize());
		List<Object> list = query.list();
		session.close();
		return list;
	}
	
	@Override
	public List<Object> getAllByConds(String hql) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		List<Object> list = query.list();
		session.close();
		return list;
	}

}
