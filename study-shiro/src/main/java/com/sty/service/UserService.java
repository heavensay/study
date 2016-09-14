package com.sty.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sty.dao.user.IUserDao;
import com.sty.entity.PermissionDTO;
import com.sty.entity.TResource;
import com.sty.entity.TResourceTreeDTO;
import com.sty.entity.User;
import com.sty.persistent.Page;

@Service
@Transactional("transactionManager")
//@Transactional("jtaTxManager")
public class UserService {
	
	@Autowired
	IUserDao iuserDao;
	
	public User getUserById(Integer id){
		User user = iuserDao.getUserById(id);
		return user;
	}
	
	/**
	 * get user's permissions
	 * @param name
	 * @return
	 */
	public List<PermissionDTO> queryUserPermission(String name){
		return iuserDao.queryUserPermission(name);
	}
	
	/**
	 * get user's permissions
	 * @param name
	 * @return
	 */
	public TResourceTreeDTO queryResourceTreeById(Integer id){
		return iuserDao.queryPermissionTreeById(id);
	}
	
	
	public List<TResource> queryPermissionByPage(Page page){
		return iuserDao.queryPermissionByPage(page);
	}
	
	public void insertTuser(User user){
		iuserDao.insertTUser(user);
	}
}
