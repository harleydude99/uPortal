/**
 * Copyright (c) 2000-2009, Jasig, Inc.
 * See license distributed with this file and available online at
 * https://www.ja-sig.org/svn/jasig-parent/tags/rel-10/license-header.txt
 */
package org.jasig.portal.ldap;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * An ILdapServer impl that wraps a Spring-LDAP ContextSource for getting contections to provide
 * legacy ILdapServer support.
 * 
 * @author Eric Dalquist <a href="mailto:eric.dalquist@doit.wisc.edu">eric.dalquist@doit.wisc.edu</a>
 * @version $Revision: 1.1 $
 * @deprecated see {@link ILdapServer} deprecation comment
 */
public class ContextSourceLdapServerImpl implements ILdapServer {
    protected final Log logger = LogFactory.getLog(this.getClass());
    
    private ContextSource contextSource;
    private String uidAttribute;
    private String baseDN;
    
    /**
     * @return the contextSource
     */
    public ContextSource getContextSource() {
        return this.contextSource;
    }
    /**
     * @param ldapContextSource the ldapContextSource to set
     */
    public void setContextSource(ContextSource ldapContextSource) {
        this.contextSource = ldapContextSource;
    }
    /**
     * @param uidAttribute the uidAttribute to set
     */
    public void setUidAttribute(String uidAttribute) {
        this.uidAttribute = uidAttribute;
    }

    
    /**
     * @see org.jasig.portal.ldap.ILdapServer#getBaseDN()
     */
    public String getBaseDN() {
        return this.baseDN;
    }
    
    /**
     * @see org.jasig.portal.ldap.ILdapServer#setBaseDN()
     */
    public void setBaseDN(String baseDN) {
    	this.baseDN = baseDN;
    }

    /**
     * @see org.jasig.portal.ldap.ILdapServer#getConnection()
     */
    public DirContext getConnection() throws NamingException {
        return this.contextSource.getReadOnlyContext();
    }

    /**
     * @see org.jasig.portal.ldap.ILdapServer#getUidAttribute()
     */
    public String getUidAttribute() {
        return this.uidAttribute;
    }

    /**
     * @see org.jasig.portal.ldap.ILdapServer#releaseConnection(javax.naming.directory.DirContext)
     */
    public void releaseConnection(DirContext conn) {
        try {
            conn.close();
        }
        catch (NamingException ne) {
            this.logger.warn("An exception occured while closing DirContext='" + conn + "'", ne);
        }
    }
}