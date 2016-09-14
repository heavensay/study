package com.sty.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sty.entity.PermissionDTO;
import com.sty.entity.TResource;
import com.sty.entity.TResourceTreeDTO;
import com.sty.entity.User;
import com.sty.persistent.Page;

public interface IUserDao {
	public User getUserById(Integer id);
	
	public User authenticationUser(@Param("name")String name,@Param("password")String password);
	
	public List<PermissionDTO> queryUserPermission(@Param("name")String name);
	
	public TResourceTreeDTO queryPermissionTreeById(@Param("id")Integer id);
	
	public List<TResource> queryPermissionByPage(Page page);
	
	public void insertTUser(User user);
}
