<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<xsl:text disable-output-escaping='yes'>
			&lt;!DOCTYPE article[&lt;!ENTITY root "http://github.com/softinica"&gt; ]&gt;
		</xsl:text>
		<article xmlns="http://docbook.org/ns/docbook" xmlns:xlink="http://www.w3.org/1999/xlink"
			xmlns:xi="http://www.w3.org/2001/XInclude" xmlns:db="http://docbook.org/ns/docbook"
			version="5.0">

			<articleinfo>
				<title>
					<xsl:value-of select="/report/title" />
				</title>
			</articleinfo>
			<mediaobject>
				<imageobject>
					<imagedata fileref="./src/main/banner.png" />
				</imageobject>
	   		</mediaobject>	
			<xsl:apply-templates select="/report/graph|/report/table" />
		</article>
	</xsl:template>

	<xsl:template match="graph">
		<figure>
			<title>
				<xsl:value-of select="./title" />
			</title>
			<graphic>
				<xsl:attribute name="fileref">
					<xsl:value-of select="./external-file" />
				</xsl:attribute>
			</graphic>
		</figure>
		<para>
			<xsl:value-of select="./description"></xsl:value-of>
		</para>
	</xsl:template>

	<xsl:template match="table">
		<table frame="all">
			<title>
				<xsl:value-of select="./title" />
			</title>
			<tgroup aligh="left" colsep="1" rowsep="1">
				<xsl:attribute name="cols">
					<xsl:value-of select="./column-count" />
				</xsl:attribute>
				<xsl:apply-templates select="./thead|./tbody" />
			</tgroup>
		</table>
		<para>
			<xsl:value-of select="./description" />
		</para>
	</xsl:template>

	<xsl:template match="thead">
		<thead>
			<xsl:apply-templates select="./tr" />
		</thead>
	</xsl:template>

	<xsl:template match="tr">
		<row>
			<xsl:apply-templates select="./td|./th" />
		</row>
	</xsl:template>

	<xsl:template match="td">
		<entry>
			<xsl:value-of select="." />
		</entry>
	</xsl:template>

	<xsl:template match="th">
		<entry>
			<xsl:value-of select="." />
		</entry>
	</xsl:template>

	<xsl:template match="tbody">
		<tbody>
			<xsl:apply-templates select="./tr" />
		</tbody>
	</xsl:template>

</xsl:stylesheet>