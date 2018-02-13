<?xml version="1.0" encoding="UTF-8"?>
<!-- greeting.xsl -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:ns1='http://myGame/schema_dic'
                
                version="1.0">
    <xsl:output method="html"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <title>Liste Dictionnaire</title>
            </head>
            <body>
              <h1>
                  Liste dictionnaire
              </h1>
              <ul>
                  <xsl:apply-templates select="//ns1:mot" >
                      <xsl:sort select="@niveau" order="ascending" />
                      <xsl:sort select="text()"  order="ascending" />
                  </xsl:apply-templates>
              </ul>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="ns1:mot">
        <li><xsl:value-of select="text()" /></li>
    </xsl:template>
    
</xsl:stylesheet>