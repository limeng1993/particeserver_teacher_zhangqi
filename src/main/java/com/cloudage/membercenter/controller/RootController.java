package com.cloudage.membercenter.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloudage.membercenter.service.IAdminService;

@Controller
@RequestMapping("/")
public class RootController {
	@Autowired
	IAdminService adminService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model){
		model.addAttribute("message","Member Center Index");
		return "index";
	}
	
//	@RequestMapping("/greeting")
//    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "index";
//    }
	
	@RequestMapping("/test")
	public @ResponseBody String test(HttpServletRequest request){
		Object val = request.getSession().getAttribute("c");
		int c = 0;
		if(val!=null){
			c = (int)val;
		}
		
		c++;
		request.getSession().setAttribute("c", c);
		return String.valueOf(c);
	}
}
