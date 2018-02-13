<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : style_profile.xsl
    Created on : 18 octobre 2017, 12:27
    Author     : zianinou
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns1='http://myGame/tux'    >
    
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Profile</title>
            </head>
            <body>
                <h1>
                    <xsl:value-of select="//name" />
                </h1>
                <table>  
                    <tr>
                       <xsl:apply-templates select="//ns1:avatar" />
                       <xsl:apply-templates select="//ns1:birthday" />
                    </tr>
                </table>
                <h2>Table des jeux</h2>
                <table>
                    <xsl:apply-templates select="//ns1:game" />
                </table>
            </body>
        </html>
    </xsl:template>
    
     <xsl:template match="ns1:avatar">
        <xsl:variable name="a"><xsl:value-of select="text()" /></xsl:variable>
        <td><img src="{$a}" height="100" width="100" /></td>
    </xsl:template>
    <xsl:template match="ns1:birthday">
        <td>Date de naissance <xsl:value-of select="text()" /></td>
    </xsl:template>
    
    <xsl:template match="ns1:game">
            <tr><th>Partie numero</th>  <td><xsl:value-of select="position()"/></td></tr>
            <tr><th>Date</th>           <td> <xsl:value-of select="@date" /></td></tr>
            <tr><th>Temps</th>          <td> <xsl:value-of select=".//ns1:time/text()" /></td></tr>
            <tr><th>Mot</th>            <td> <xsl:value-of select=".//ns1:word/text()" /></td></tr>
            <tr><th>Niveau</th>         <td> <xsl:value-of select=".//ns1:word/@niveau" /></td></tr>
    </xsl:template>
   

</xsl:stylesheet>
