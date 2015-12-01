package org.hippoecm.indexbean;

import org.hippoecm.hst.content.beans.index.IndexField;
import org.hippoecm.hst.content.beans.standard.IdentifiableContentBean;

public class SiteBean implements IdentifiableContentBean {

	private String identifier;
	private String sitename;
	private String url;
	private String imageurl;
	public SiteBean() {}
	
	public SiteBean(String identifier,String sitename,String url,String imageurl)
	{
		this.identifier = identifier;
		this.sitename = sitename;
		this.url = url;
		this.imageurl = imageurl;
	}
	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	@IndexField
	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	@IndexField
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "id:" + identifier + "name:" + sitename + "url:" + url;
	}
	@IndexField
	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
}
