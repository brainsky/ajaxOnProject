package com.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.CustomerService;
import com.web.util.ActionUtil;

@Controller
public class CustomerAction {
	//注入service
	@Autowired
	private CustomerService service;
	
	@RequestMapping("/firstPage")
	public String goToFirstPage(){
		return "firstPage";
	}
	@RequestMapping("/index")
	public void goIndex(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		 req.getRequestDispatcher("/pages/index.html").forward(req, resp);
		
	}
	@RequestMapping(value="/addCustomer",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addCustomer(@RequestParam("name") String name, @RequestParam("level") String level) throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		if(ActionUtil.validateNotNull(name,level)){
			service.insertCustomer(name,level);
			map.put("status", "success");
			map.put("url", "/mycrud/findAll");	
		}else{
			map.put("message", "name或者level为空");
		}	
		return mapper.writeValueAsString(map);
	}
	@RequestMapping(value="/findCustomer")
	public ModelAndView findCustomer(@RequestParam("id") int id) throws Exception{
		Customer customer = service.findCustomerByID(id);
		ModelAndView model = new ModelAndView();
		model.addObject("customer", customer);
		model.setViewName("firstPage");
		return model;
	}
	@RequestMapping(value="/findAll")
	public String findAll(ModelMap map) throws Exception{
		List<Customer> list = service.findAllCustomer();
		map.put("listCustomer", list);
		return "secondePage";	
	}	
	@RequestMapping(value="/updateCustomer",method=RequestMethod.POST,
			produces = "application/json; charset=utf-8")
	@ResponseBody
	public String update(@RequestParam ("id") String id, 
			@RequestParam ("name") String name, @RequestParam("level") String level)
			throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		if(ActionUtil.validateNotNull(id,name,level)){
			service.updateCustomer(id, name, level);
			map.put("status", "success");
			map.put("url", "/mycrud/findAll");
			
		}else{
			map.put("message", "输入值错误！");
		}
		return mapper.writeValueAsString(map);
	}	
	@RequestMapping(value="/deleteCustomer",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delete(@RequestParam("id") int id) throws Exception{
		service.deleteCustomer(id);
		Map<String,String> map = new HashMap<String, String>();
		map.put("url", "/mycrud/findAll");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(map);
	}
	
	@RequestMapping(value="/findByID",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findByID(@RequestParam("id") String id) throws Exception{
		
		Map<String,Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		Customer result = service.findCustomerByID(Integer.parseInt(id));
		if(result == null || !ActionUtil.validateNotNull(id)){
			map.put("code", "404");
			return mapper.writeValueAsString(map);	
		}else{
			//map.put("name", result.getName());
			JSONObject json = JSONObject.fromObject(result);
			
			return  json.toString();
		}	
	}
	@RequestMapping(value="/getJsonOfCustomer",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getJsonOfCustomer() throws Exception{
		List<Customer> list = service.findAllCustomer();
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}
	@RequestMapping("/toAll")
	public void toAll(HttpServletRequest req, HttpServletResponse resp) throws Exception{
			req.getRequestDispatcher("/pages/ajaxUseJson.html").forward(req, resp);
	}
}
