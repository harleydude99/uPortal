<?xml version='1.0' encoding='utf-8' ?>
<!--

    Copyright (c) 2000-2009, Jasig, Inc.
    See license distributed with this file and available online at
    https://www.ja-sig.org/svn/jasig-parent/tags/rel-10/license-header.txt

-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" indent="yes"/>
  <xsl:param name="baseActionURL">default</xsl:param>
  <xsl:param name="unauthenticated">false</xsl:param>
  <xsl:param name="locale">en_US</xsl:param>

  <xsl:template match="login-status">
    <xsl:choose>
      <xsl:when test="$unauthenticated='true'">
        <xsl:call-template name="login-form"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:call-template name="logged-in"/>
      </xsl:otherwise>      
    </xsl:choose>
  </xsl:template>
  
  <xsl:template name="logged-in">
    <do type="accept" label="Yes"><go href="Logout"/></do>  
    <do type="options" label="No"><prev/></do>  
    <p>Do you really want to logout?</p>
  </xsl:template>

  <xsl:template name="login-form">
    <do type="accept" label="Submit">
      <go href="Authentication">
        <postfield name="userName" value="$(userName)" />
        <postfield name="password" value="$(password)" />
      </go>
    </do>  
    <do type="options" label="Back">
      <prev/>
    </do>
    
    <xsl:apply-templates/>    
    
    <p> 
      User name: <input name="userName" size="15"/><br/> 
      Password:  <input name="password" size="15"/><br/>
      Press Submit to login...
    </p>
  </xsl:template>
  
  <xsl:template match="failure">
    <p>The user name/password combination entered is not recognized. Please try again!</p>
  </xsl:template>
  
  <xsl:template match="error">
    <p>
      An error occured during authentication.  
      The portal is unable to log you on at this time.
      Try again later.
    </p>
  </xsl:template>
  
  <xsl:template match="full-name">
  </xsl:template>

</xsl:stylesheet>