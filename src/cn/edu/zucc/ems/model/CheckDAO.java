package cn.edu.zucc.ems.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.ems.bean.CheckBean;
import cn.edu.zucc.ems.bean.CheckDetailBean;
import cn.edu.zucc.ems.bean.StudentBean;
import cn.edu.zucc.ems.bean.ViewCheckDetail;
import cn.edu.zucc.ems.bean.ViewCountBean;

@Repository
@Transactional
public class CheckDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<CheckBean> loadAllCheckList(int courseid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<CheckBean> list = session.createQuery("from CheckBean where course_id = " + courseid + " and removetime is null ").list();
		return list;
		
	}
	
	public void addCheck(int courseid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		CheckBean checkBean = new CheckBean();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		checkBean.setCourse_id(courseid);
		checkBean.setCheck_time(time);
		checkBean.setCreatetime(time);
		session.save(checkBean);
	}
	
	public List<StudentBean> readStudent(int classid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<StudentBean> list = session.createQuery("from StudentBean where class_id = " + classid).list();
		return list;
	}
	
	public void deleteCheck(int checkid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		org.hibernate.query.Query query = session.createQuery("update CheckBean set removetime = '" + time + "' where check_id = " + checkid);
		query.executeUpdate();
		
	}
	
	public List<ViewCheckDetail> readCheckDetail(int courseid, int checkid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<ViewCheckDetail> list = session.createQuery("from ViewCheckDetail where course_id = " + courseid + " and check_id = " + checkid).list();
		return list;
		
	}
	
	public List<ViewCheckDetail> readCheckDetailByCheckID(int checkid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		List<ViewCheckDetail> list = session.createQuery("from ViewCheckDetail where check_id = " + checkid).list();
		return list;
		
	}
	
	public void addCheckDetail(String state, int checkid, int studentid){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		CheckDetailBean checkdetailBean = new CheckDetailBean();
		
		checkdetailBean.setCheck_id(checkid);
		checkdetailBean.setCreatetime(time);
		checkdetailBean.setState(state);
		checkdetailBean.setStudent_id(studentid);
		session.save(checkdetailBean);
		
	}
	
	public void modifyCheckDetail(String state, int checkdetail_id){
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		org.hibernate.query.Query query = session.createQuery("update CheckDetailBean set state = '" + state + "' where check_detail_id = " + checkdetail_id);
		query.executeUpdate();
		
	}

	public Object listFinal(Integer courseid) {
		this.setSessionFactory(sessionFactory);
		Session session = sessionFactory.getCurrentSession();
		String hql="from ViewCountBean where course_id="+courseid;
		List<ViewCountBean> list=session.createQuery(hql).list();
		return list;
	}

	
}
