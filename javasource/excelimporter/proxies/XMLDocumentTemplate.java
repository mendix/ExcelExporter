// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package excelimporter.proxies;

public class XMLDocumentTemplate extends system.proxies.FileDocument
{
	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "ExcelImporter.XMLDocumentTemplate";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		FileID("FileID"),
		Name("Name"),
		DeleteAfterDownload("DeleteAfterDownload"),
		Contents("Contents"),
		HasContents("HasContents"),
		Size("Size"),
		XMLDocumentTemplate_Template("ExcelImporter.XMLDocumentTemplate_Template");

		private java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@java.lang.Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public XMLDocumentTemplate(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "ExcelImporter.XMLDocumentTemplate"));
	}

	protected XMLDocumentTemplate(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject xMLDocumentTemplateMendixObject)
	{
		super(context, xMLDocumentTemplateMendixObject);
		if (!com.mendix.core.Core.isSubClassOf("ExcelImporter.XMLDocumentTemplate", xMLDocumentTemplateMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a ExcelImporter.XMLDocumentTemplate");
	}

	/**
	 * @deprecated Use 'XMLDocumentTemplate.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static excelimporter.proxies.XMLDocumentTemplate initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return excelimporter.proxies.XMLDocumentTemplate.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static excelimporter.proxies.XMLDocumentTemplate initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new excelimporter.proxies.XMLDocumentTemplate(context, mendixObject);
	}

	public static excelimporter.proxies.XMLDocumentTemplate load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return excelimporter.proxies.XMLDocumentTemplate.initialize(context, mendixObject);
	}

	public static java.util.List<excelimporter.proxies.XMLDocumentTemplate> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		java.util.List<excelimporter.proxies.XMLDocumentTemplate> result = new java.util.ArrayList<excelimporter.proxies.XMLDocumentTemplate>();
		for (com.mendix.systemwideinterfaces.core.IMendixObject obj : com.mendix.core.Core.retrieveXPathQuery(context, "//ExcelImporter.XMLDocumentTemplate" + xpathConstraint))
			result.add(excelimporter.proxies.XMLDocumentTemplate.initialize(context, obj));
		return result;
	}

	/**
	 * @return value of XMLDocumentTemplate_Template
	 */
	public final excelimporter.proxies.Template getXMLDocumentTemplate_Template() throws com.mendix.core.CoreException
	{
		return getXMLDocumentTemplate_Template(getContext());
	}

	/**
	 * @param context
	 * @return value of XMLDocumentTemplate_Template
	 */
	public final excelimporter.proxies.Template getXMLDocumentTemplate_Template(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		excelimporter.proxies.Template result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.XMLDocumentTemplate_Template.toString());
		if (identifier != null)
			result = excelimporter.proxies.Template.load(context, identifier);
		return result;
	}

	/**
	 * Set value of XMLDocumentTemplate_Template
	 * @param xmldocumenttemplate_template
	 */
	public final void setXMLDocumentTemplate_Template(excelimporter.proxies.Template xmldocumenttemplate_template)
	{
		setXMLDocumentTemplate_Template(getContext(), xmldocumenttemplate_template);
	}

	/**
	 * Set value of XMLDocumentTemplate_Template
	 * @param context
	 * @param xmldocumenttemplate_template
	 */
	public final void setXMLDocumentTemplate_Template(com.mendix.systemwideinterfaces.core.IContext context, excelimporter.proxies.Template xmldocumenttemplate_template)
	{
		if (xmldocumenttemplate_template == null)
			getMendixObject().setValue(context, MemberNames.XMLDocumentTemplate_Template.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.XMLDocumentTemplate_Template.toString(), xmldocumenttemplate_template.getMendixObject().getId());
	}

	@java.lang.Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final excelimporter.proxies.XMLDocumentTemplate that = (excelimporter.proxies.XMLDocumentTemplate) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@java.lang.Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return "ExcelImporter.XMLDocumentTemplate";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@java.lang.Override
	@java.lang.Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
