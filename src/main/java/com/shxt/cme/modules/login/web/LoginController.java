package com.shxt.cme.modules.login.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shxt.cme.domain.Menu;
import com.shxt.cme.domain.User;
import com.shxt.cme.modules.login.service.LoginService;
import com.shxt.framework.serivce.BusinessLogger;
import com.shxt.framework.web.base.BaseController;

@Controller
@RequestMapping(value = "login")
public class LoginController extends BaseController {
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	BusinessLogger businessLogger = new BusinessLogger();

	/**
	 * @Description: 获取所有角色
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	
	@RequestMapping("preLogin")
	public String preLogin(Model model) throws Exception {

		return "login/login";
	}
	/**
	 * @Description: 不登陆浏览
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping("noLogin")
	public String noLogin(Model model) throws Exception {
		System.out.println("nologin 进入首页");
		return "/staticPage/shouye";
	}
	/**
	 * @Description: 登录相关校验
	 * @param model
	 * @param user
	 * @return String
	 */
	@RequestMapping("loginCheck")
	public String loginCheck(Model model, User user,String experts, HttpSession session) {
		String str="redirect:/inc/main";
        //添加专家登录功能
		if("experts".equals(experts)){
	  			str="redirect:/approInc/apMain";
			
		}else{
			
		
		User currentUser = loginService.findUserInfo(user);
		//不存在该用户，返回登陆页面
		if(currentUser == null) {
			model.addAttribute("errorInfo", "用户名或密码错误");
			return "login/login";
		} else {
			session.setAttribute("user", currentUser);
			// 获取用户菜单		
			List<Menu> menuList=loginService.findMenuLevel2ByRoleKey(
									currentUser.getUserType(), 001);
						session.setAttribute("menuList", menuList);

			}
		
		
		
		if(currentUser.getUserType()==1){     // 1.审核
			str="redirect:/approInc/apMain";

		}else if(currentUser.getUserType()==2){  // 2.继委办
			str="redirect:/approInc/apJjwbMain";
		}else if(currentUser.getUserType()==3){  //3. 继教委员会
			str="redirect:/approInc/apJjwMain";
		}else if(currentUser.getUserType()==4){  //3. 继教委员会
			str="redirect:/inc/main";
		}
		
		}
			return str;
		
	}

	/**
	 * @Description: 用户退出
	 * @param session
	 * @return String
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login/preLogin";
	}

}
