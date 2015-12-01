package org.example.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.eclipse.jetty.util.ajax.JSON;
import org.example.model.SMUser;
import org.example.model.Login;
import org.example.model.Topic;
import org.example.model.bean.Result;
import org.example.service.TopicService;
import org.example.service.UserService;
import org.example.task.UpdateWeather;
import org.example.utils.FunctionUtil;
import org.example.utils.GsonUtil;
import org.example.utils.http.HttpRequester;
import org.example.utils.http.HttpRespons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

//	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
		
	@Autowired
	private TopicService topicService;
	

	@RequestMapping("/login")
	@ResponseBody
	public SMUser Login(HttpServletRequest req,@RequestParam("userAccount")String userAccount,@RequestParam("userPassword")String userPassword){
		if(FunctionUtil.StringSizeFilter(userAccount, 4, 12)&&FunctionUtil.StringSizeFilter(userPassword, 4, 12)){
//			if(FunctionUtil.StringMatchFilter(userAccount) && FunctionUtil.StringMatchFilter(userPassword)){
				SMUser smUser = userService.verifyUserExist(userAccount, userPassword);
				if(smUser != null){
					/***/
					//日志操作
					/***/
//					logger.info("用户登录 == 用户名：" + userAccount + " 登录成功" );
					HttpSession httpSession = req.getSession();
					httpSession.setAttribute("UserId", smUser.getUserId());
					httpSession.setAttribute("UserName", smUser.getUserName());
					httpSession.setAttribute("UserAge", smUser.getUserAge());
					httpSession.setAttribute("UserAccount", smUser.getUserAccount());
					return smUser;
				}
//			}
		}
		return null;
	}
	@RequestMapping("/get_login_user")
	@ResponseBody
	public SMUser getLoginUser(HttpServletRequest req){
//			if(FunctionUtil.StringMatchFilter(userAccount) && FunctionUtil.StringMatchFilter(userPassword)){
		HttpSession session = req.getSession(false);
		if(session!=null)
		{
			if(session.getAttribute("UserId")!= null ){
				String userId = session.getAttribute("UserId").toString();
				return userService.getUserById(Integer.parseInt(userId));
			}
		}
		return null;
	}
	@RequestMapping("/login_out")
	@ResponseBody
	public void LoginOut(HttpServletRequest req){
			HttpSession session = req.getSession(false);
			if(session!=null)
			{
				/***/
				//日志操作
				/***/
				session.invalidate();
			}
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public boolean Register(@Valid @ModelAttribute("user") SMUser user,@Valid @ModelAttribute("login") Login login){
//		if(FunctionUtil.StringSizeFilter(user.getUserAccount()))
		System.out.println(JSON.toString(user));
		System.out.println(JSON.toString(login));
		int userId = userService.registerUser(user, login);
		if(userId != 0)
			return true;
		return false;
	}
	
	//getPersonalInfo
	@RequestMapping("/getPersonalInfo")
	@ResponseBody
	public String PersonalInfo(HttpServletRequest req){
		HttpSession session = req.getSession();
		if(session!=null){			
			if(session.getAttribute("UserId")!=null){			
				String userId = session.getAttribute("UserId").toString();
				SMUser user = userService.getUserById(Integer.parseInt(userId));
				return GsonUtil.objectToJson(user);
			}
		}
		return null;
	}
		
	//ChangePWD
	@RequestMapping("/changePwd")
	@ResponseBody
	public boolean changePwd(HttpServletRequest req, @RequestParam("oldPassword")String oldPassword, @RequestParam("userPassword")String userPassword){
		HttpSession session = req.getSession();
		if(session!=null){
			if(session.getAttribute("UserId")!=null){			
				String userId = session.getAttribute("UserId").toString();
				int id = Integer.parseInt(userId);
				return userService.changePwd(id, oldPassword, userPassword);
			}
		}
		return false;
	}
	
	//ChangeUserInfo
	@RequestMapping("/changeUserInfo")
	@ResponseBody
	public boolean changeUserInfo(HttpServletRequest req, @Valid @ModelAttribute("user") SMUser user){
		HttpSession session = req.getSession();
		if(session.getAttribute("UserId")!=null && session.getAttribute("UserAccount")!=null){			
			user.setUserId(Integer.parseInt(session.getAttribute("UserId").toString()));
			user.setUserAccount(session.getAttribute("UserAccount").toString());
			return userService.changeUserInfo(user);
		}
		return false;
	}
	
	@RequestMapping("/addtopic")
	@ResponseBody
	public Result addUserTopic(HttpServletRequest req,@RequestParam("topicIds")String topicIds){
		HttpSession session = req.getSession();
		int _userId=0;
		Result<Object> res=new Result<Object>();
		if(session!=null&&session.getAttribute("UserId")!=null)
		{
			_userId=Integer.parseInt(session.getAttribute("UserId").toString());
			topicService.addTopicsToUser(_userId, topicIds);
			res.setMessage("保存陈功");
			res.setStatus("success");
			return res;
		}
		else
		{
			
			res.setMessage("请登录");
			res.setStatus("error");
			return res;
		}
		
	}
	
	@RequestMapping("/deletetopic")
	@ResponseBody
	public Result deleteUserTopic(HttpServletRequest req,@RequestParam("topic_id")String topicIds){
		HttpSession session = req.getSession();
		int _userId=0;
		Result<Object> res=new Result<Object>();
		if(session!=null&&session.getAttribute("UserId")!=null)
		{
			_userId=Integer.parseInt(session.getAttribute("UserId").toString());
			topicService.deleteTopicsFromUser(_userId, topicIds);
			//topicService.addTopicsToUser(_userId, topicIds);
			res.setMessage("保存陈功");
			res.setStatus("success");
			return res;
		}
		else
		{
			
			res.setMessage("请登录");
			res.setStatus("error");
			return res;
		}
	}
	
	//使用spring提供的一种视图解析器，将 List数据解析为JSON 格式数据
	@RequestMapping("/searchUserTopics")
	@ResponseBody
	public List searchUserTopic(HttpServletRequest req){
		
		HttpSession session = req.getSession();
		List<Map> list=new ArrayList<Map>();
		int _userId=0;
		if(session!=null&&session.getAttribute("UserId")!=null)
		{
			_userId=Integer.parseInt(session.getAttribute("UserId").toString());
			list = topicService.searchTopicsOfUser(_userId);
		}
		return list;
	}
	
	//使用spring提供的一种视图解析器，将 List数据解析为JSON 格式数据
	@RequestMapping("/searchOtherTopics")
	@ResponseBody
	public List searchOtherUserTopic(HttpServletRequest req){
		HttpSession session = req.getSession();
		List<Topic> topicList = new ArrayList<Topic>();
		int _userId=0;
		if(session!=null&&session.getAttribute("UserId")!=null)
		{
			_userId=Integer.parseInt(session.getAttribute("UserId").toString());
			topicList = topicService.searchOtherTopicsOfUser(_userId);
		}
		return topicList;
		
		
	}
	
	//使用spring提供的一种视图解析器，将 List数据解析为JSON 格式数据
	@RequestMapping("/searchAllTopics")
	@ResponseBody
	public String searchAllTopic(){
		List<Topic> allTopicList = topicService.searchAllTopics();
		return GsonUtil.objectToJson(allTopicList);
	}
	
	private boolean verifyRegister(SMUser user, Login login){
		
		return false;
	}
	
	@RequestMapping("/isLogin")
	@ResponseBody
	public boolean isLogin(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("UserId") != null)
			return true;
		else
			return false;
	}
	@RequestMapping("/deleteTopicByName")
	@ResponseBody
	public boolean deleteTopicByName(@RequestParam("topicName")String topicName,HttpServletRequest request) throws Exception
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("UserId") == null)
			return false;
		if(topicName != null)
		{
			int userId = Integer.parseInt(session.getAttribute("UserId").toString());
			Topic topic = topicService.findTopicsByName(topicName);
			if(topic != null)
			{
				String topicId = topic.getTopicId() + "";
				topicService.deleteTopicsFromUser(userId, topicId);
			}
			return true;
		}
		return false;
	}
	@RequestMapping("/searchWeather")
	@ResponseBody
	public ResponseEntity<String> searchWeather(@RequestParam("dataset")String dataset,@RequestParam("keyref")String keyref,HttpServletRequest request) throws Exception
	{
//		UpdateWeather updateWeather = new UpdateWeather();
//		String response = updateWeather.callWebAPI(dataset,keyref);
//		System.out.println("************************************" + response);
//		return response;
		System.out.println("XX:user");
		HttpRequester req = new HttpRequester();  
		req.setDefaultContentEncoding("utf-8");  
		Map<String,String> params=new HashMap<String,String>();
		
		HttpRespons hr;
		String content ;
		try {
			params.put("dataset", java.net.URLEncoder.encode(dataset,"utf-8"));
			params.put("keyref", java.net.URLEncoder.encode(keyref,"utf-8"));
			hr = req.sendGet("http://www.nea.gov.sg/api/WebAPI/", params);
			content= hr.getContent();
		} catch (Exception e) {
			content=e.getMessage();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<String>(content,headers, HttpStatus.OK);
	}
}
