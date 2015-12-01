package org.hippoecm.hst.demo.servlet;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.solr.client.solrj.response.UpdateResponse;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.example.utils.FunctionUtil;
import org.hippoecm.hst.site.HstServices;
import org.hippoecm.hst.solr.HippoSolrClient;
import org.hippoecm.indexbean.SiteBean;


public class ReadFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
	    FutureTask<String> task = new FutureTask<>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				// 使用另一个线程来执行该方法，会避免占用Tomcat的启动时间
				checkPath();
			    return "Collection Completed";
			}
		});
		new Thread(task).start();
	}
	private static String url = FunctionUtil.loadProperties("siteurl");
	public void checkPath()
	{
		System.out.println("-----------start-------------" + url);
		try {
			InputStream in = this.getClass().getResourceAsStream("/hst/configurations/myhippoproject/sitemap.xml");
			SAXReader reader = new SAXReader();
			Document document = reader.read(in);
			Element root = document.getRootElement();
			//将解析出来的allresource下的resourceitem放在list中
			List list = root.elements("node");
			if(list == null)
				return;
			List<SiteBean> sites = new ArrayList<>();
			SiteBean siteBean = null;
			List nodeList = null;
			for(Iterator i = list.iterator();i.hasNext();)
			{
				Element node = (Element)i.next();
				System.out.println(node.attributeValue("name"));
				boolean filter = false;
				switch (node.attributeValue("name")) {
					case "_any_":
						filter = true;
						break;
					case "root":
						filter = true;
						break;
					case "pagenotfound":
						filter = true;
						break;
					case "search":
						filter = true;
						break;
					case "staff":
						filter = true;
						break;
					case "daliy_message":
						filter = true;
						break;
					case "personal_info":
						filter = true;
						break;
					case "accountsetting":
						filter = true;
						break;
				}
				if(filter)
					continue;
				nodeList = node.elements("node");
//				for(Iterator j = nodeList.iterator();j.hasNext();)
//				{
//					Element node2 = (Element)j.next();
//					System.out.println("------" + node2.getName() + "------" + node2.attributeValue("name"));
//				}
				if(nodeList != null && nodeList.size() > 0)
				{
					continue;
				}
				siteBean = storeQuery(node);
				siteBean.toString();
				if(siteBean != null)
				{
					sites.add(siteBean);
				}
			}
			HippoSolrClient solrClient = HstServices.getComponentManager().
					getComponent(HippoSolrClient.class.getName(),
							"org.hippoecm.hst.solr");
			System.out.println("++++++++++++++sites is" + sites);
			solrClient.getSolrServer().addBeans(sites);
			UpdateResponse commit =  solrClient.getSolrServer().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-----------end-------------");
	}
	public SiteBean storeQuery(Element node)
	{
		if(node == null)
			return null;
		String name = node.attributeValue("name");
		List properties = node.elements("property");
		System.out.println("-----------------------" + properties);
		String imageurl = "";
		for(Iterator i = properties.iterator();i.hasNext();)
		{
			Element property = (Element)i.next();
			System.out.println("---------------------------" + property.attribute("name"));
			if("hst:parameternames".equals(property.attributeValue("name")))
			{
				System.out.println("---------" + property.elementText("value"));
				imageurl = property.elementText("value");
			}
		}
		SiteBean siteBean = new SiteBean(name,name,url + name,imageurl);
		return siteBean;
	}
	
}
