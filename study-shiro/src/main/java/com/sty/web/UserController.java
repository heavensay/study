package com.sty.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sty.entity.PermissionDTO;
import com.sty.entity.TResource;
import com.sty.entity.TResourceTreeDTO;
import com.sty.entity.User;
import com.sty.persistent.Page;
import com.sty.service.UserService;


/**
 * 类UserController.java的实现描述：内部用户WEB层
 * 
 * @author admin 2013-3-5 上午09:50:56
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	Logger log  = Logger.getLogger(UserController.class);
	
	/**
	 * 转向创建内部用户页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login",method=RequestMethod.POST)
//	@ResponseBody
	public String login(User user,String username,String name) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,user.getPassword());
        token.setRememberMe(true);
        try {
            subject.login(token);
            return "redirect:/success.jsp";
        }catch (AuthenticationException e) {
//            log.error("登录失败错误信息:"+e);
//            token.clear();
        	log.error("登录失败",e);
            return "redirect:/login.jsp";
        }
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/queryUserPermission",method=RequestMethod.GET)
	@ResponseBody
	public List<PermissionDTO> queryUserPermission(String name) {
			return userService.queryUserPermission(name);
    }
	
	/**
	 * 
	 */
	@RequestMapping(value = "/queryResourceTree",method=RequestMethod.GET)
	@ResponseBody
	public TResourceTreeDTO queryResourceTreeById(String name) {
			return userService.queryResourceTreeById(1);
    }
	
	@RequestMapping(value = "/queryPermissionByPage",method=RequestMethod.GET)
	@ResponseBody
	public List<TResource> queryPermissionByPage(String name) {
		Page page = new Page();
		return userService.queryPermissionByPage(page);
    }
	
}
