package org.example.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.model.Topic;
import org.example.model.UserTopics;
import org.example.service.TopicService;
import org.example.utils.GsonUtil;
import org.hippoecm.hst.site.HstServices;
import org.hippoecm.hst.solr.HippoSolrClient;
import org.hippoecm.hst.solr.content.beans.ContentBeanBinder;
import org.hippoecm.hst.solr.content.beans.query.HippoQuery;
import org.hippoecm.hst.solr.content.beans.query.HippoQueryResult;
import org.hippoecm.hst.solr.content.beans.query.Hit;
import org.hippoecm.hst.solr.content.beans.query.HitIterator;
import org.hippoecm.indexbean.SiteBean;
import org.hippoecm.indexbean.SiteBeanBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/searchtopic")
public class SearchController {

	public static final Logger log = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping("/fortest")
	@ResponseBody
	public String fortest()
	{
		return "hello";
	}
	
	@RequestMapping("/findAllTopics")
	@ResponseBody
	public String findAllTopics(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String result = "";
		List<Topic> list = topicService.searchAllTopics();
		if(list == null)
			list = new ArrayList<>();
		result = GsonUtil.objectToJson(list);
		return result;
	}
	
	@RequestMapping("/findTopicsByStr")
	@ResponseBody
	public String findTopicsByStr(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String queryStr = request.getParameter("search_input").trim();
		String result = "";
		List<Topic> list = topicService.findTopicsByStr(queryStr);
		if(list == null)
			list = new ArrayList<>();
		result = GsonUtil.objectToJson(list);
		return result;
	}
	@RequestMapping("/checkTopicsByUser")
	@ResponseBody
	public boolean checkTopicsByUser(@RequestParam("topicIds")String topicIds,HttpServletRequest request) throws Exception
	{
		HttpSession session = request.getSession();
		String[] ids = topicIds.split(",");
		List<String> addids = new ArrayList<>();
		if(session.getAttribute("UserId") != null)
		{
			int userId = Integer.parseInt(session.getAttribute("UserId").toString());
			System.out.println("----------------userId" + userId);
			List<UserTopics> userTopics = topicService.searchCheckedTopics(topicIds);
//			topicService.addTopicsToUser(Integer.parseInt(session.getAttribute("UserId").toString()), topicIds);
			if(userTopics.size() > 0)
			{
				for(int j = 0;j < ids.length;j++)
				{
					int count = 0;
					for(int i = 0;i < userTopics.size();i++)
					{
						if(!ids[j].equals("" + userTopics.get(i).getTopicId()))
						{
							count++;
						}
						
					}
					if(count == userTopics.size())
					{
						addids.add(ids[j]);
					}
				}
				if(addids.size() > 0)
					topicService.addTopicsToUser(userId, listToString(addids));
				return true;
			}
			else
			{
				topicService.addTopicsToUser(userId, topicIds);
				return true;
			}
		}
		else
		{
			return false;
		}
	}
	public String listToString(List<String> list) {  
	    StringBuilder sb = new StringBuilder();  
	    if (list != null && list.size() > 0) {  
	        for (int i = 0; i < list.size(); i++) {  
	            if (i < list.size() - 1) {  
	                sb.append(list.get(i) + ",");  
	            } else {  
	                sb.append(list.get(i));  
	            }  
	        }  
	    }  
	    return sb.toString();  
	}
	
	@RequestMapping("/searchDocsByName")
	@ResponseBody
	public List<SiteBean> searchDocsByName(@RequestParam("query")String query,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
//		System.out.println("==========searchDocsByName===========");
		if(query == null)
			return null;
		HippoSolrClient solrClient =
			      HstServices.getComponentManager().getComponent(
			                    HippoSolrClient.class.getName(), "org.hippoecm.hst.solr");
		query = "sitename:*" + query + "*";
		HippoQuery hippoQuery = solrClient.createQuery(query);
//		System.out.println(hippoQuery.getSolrQuery().getQuery());
		hippoQuery.setLimit(1000);
		hippoQuery.setOffset(0);
		HippoQueryResult result = hippoQuery.execute();
		result.setContentBeanBinders(new ArrayList<ContentBeanBinder>());
//		request.setAttribute("result", result);
		HitIterator hitIterator = result.getHits();
		return parseHitBean(hitIterator);
	}
	@RequestMapping("/searchForSiteBean")
	@ResponseBody
	public List<SiteBean> searchForSiteBean(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
//		System.out.println("==========searchForSiteBean===========");
		HippoSolrClient solrClient =
			      HstServices.getComponentManager().getComponent(
			                    HippoSolrClient.class.getName(), "org.hippoecm.hst.solr");
		HippoQuery hippoQuery = solrClient.createQuery("*:*");
		hippoQuery.setIncludedClasses(false, SiteBean.class);
		hippoQuery.setLimit(1000);
		hippoQuery.setOffset(0);
		HippoQueryResult result = hippoQuery.execute();
//		System.out.println("=======************" + hippoQuery.getSolrQuery().getQuery());
		result.setContentBeanBinders(new ArrayList<ContentBeanBinder>());
//		request.setAttribute("result", result);
		HitIterator hitIterator = result.getHits();
		return parseHitBean(hitIterator);
	}
	private List<SiteBean> parseHitBean(HitIterator hitIterator) throws Exception
	{
		List<SiteBean> siteBeans = new ArrayList<>();
		SiteBean siteBean = null;
		if(hitIterator.getSize() == 0)
			return siteBeans;
		while(hitIterator.hasNext())
		{
			siteBean = (SiteBean) hitIterator.next().getBean();
			System.out.println("getbean===============" + siteBean);
			if (siteBean != null) 
			{
				siteBeans.add(siteBean);
			}
		}
		return siteBeans;
	}
}
