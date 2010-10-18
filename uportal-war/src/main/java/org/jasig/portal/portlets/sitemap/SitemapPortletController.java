package org.jasig.portal.portlets.sitemap;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stax.StAXSource;

import org.apache.commons.lang.Validate;
import org.jasig.portal.UserPreferencesManager;
import org.jasig.portal.layout.IUserLayoutManager;
import org.jasig.portal.url.IPortalRequestUtils;
import org.jasig.portal.user.IUserInstance;
import org.jasig.portal.user.IUserInstanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

/**
 * SitemapPortletController produces a sitemap view of the current user's
 * portal layout.
 * 
 * @author Jen Bourey, jbourey@unicon.net
 * @version $Revision$
 */
@Controller
@RequestMapping("VIEW")
public class SitemapPortletController {
    
    private IUserInstanceManager userInstanceManager;
    
    @Autowired(required = true)
    public void setUserInstanceManager(IUserInstanceManager userInstanceManager) {
        this.userInstanceManager = userInstanceManager;
    }

    private IPortalRequestUtils portalRequestUtils;
    
    /**
     * @param portalRequestUtils the portalRequestUtils to set
     */
    @Autowired(required = true)
    public void setPortalRequestUtils(IPortalRequestUtils portalRequestUtils) {
        Validate.notNull(portalRequestUtils);
        this.portalRequestUtils = portalRequestUtils;
    }

    /**
     * Display the user sitemap.
     * 
     * @param request
     * @param response
     * @return
     * @throws XMLStreamException 
     */
    @RequestMapping
    public ModelAndView displaySitemap(PortletRequest request) throws XMLStreamException {
        
        Map<String, Object> model = new HashMap<String, Object>();
        
        // retrieve the user layout manager for the current user
        final HttpServletRequest httpServletRequest = this.portalRequestUtils.getOriginalPortalRequest(request);
        IUserInstance ui = userInstanceManager.getUserInstance(httpServletRequest);
        UserPreferencesManager preferencesManager = (UserPreferencesManager) ui.getPreferencesManager();
        IUserLayoutManager layoutManager = preferencesManager.getUserLayoutManager();

        // create a Source from the user's layout document
        XMLEventReader reader = layoutManager.getUserLayoutReader();
        StAXSource source = new StAXSource(reader);
        model.put("source", source);
        
        return new ModelAndView("sitemapView", model);
    }
    
}
