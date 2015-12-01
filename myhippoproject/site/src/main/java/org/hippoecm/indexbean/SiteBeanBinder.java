package org.hippoecm.indexbean;

import org.hippoecm.hst.content.beans.standard.IdentifiableContentBean;
import org.hippoecm.hst.solr.content.beans.BindingException;
import org.hippoecm.hst.solr.content.beans.ContentBeanBinder;

public class SiteBeanBinder implements ContentBeanBinder{

	@Override
	public boolean canBind(Class<? extends IdentifiableContentBean> clazz) {
		return (SiteBean.class.isAssignableFrom(clazz));
	}

	@Override
	public void bind(IdentifiableContentBean identifiableContentBean)
			throws BindingException {
		if (!(identifiableContentBean instanceof SiteBean)) {
            return;
        }
		SiteBean bean = (SiteBean) identifiableContentBean;
	}

}
