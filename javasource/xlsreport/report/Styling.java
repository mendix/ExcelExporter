/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xlsreport.report;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import com.mendix.core.Core;
import com.mendix.core.CoreException;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import xlsreport.proxies.MxCellStyle;
import xlsreport.proxies.MxColor;
import xlsreport.proxies.MxTemplate;
import xlsreport.proxies.TextAlignment;
import xlsreport.proxies.TextVerticalAlignment;
import xlsreport.report.export.Export;

/**
 *
 * @author jvg
 */
public class Styling
{
	private static final String DATEFORMAT = "_Date";
	private static ILogNode log = Core.getLogger("XLSreport");   
	private HashMap<String, CellStyle> styleList;
    private CellStyle defaultStyle;
    private CellStyle defaultStyleDate;
    private CreationHelper createHelper;
    private String datePresentation;
    
    public Styling(MxTemplate template)
    {
    	this.datePresentation = Export.getDatePresentation(template);
    	this.styleList = new HashMap<String, CellStyle>();
    }

    public CellStyle getDefaultStyle()
    {
        return this.defaultStyle;
    }
    
    public CellStyle getDefaultStyle(boolean dateTimeFormat)
    {
    	if(dateTimeFormat)
    	{
    		return this.defaultStyleDate;
    	} else
    	{
    		return this.defaultStyle;
    	}
    }

    public void setDefaultStyle(Long GUID)
    {        
    	this.defaultStyle = this.styleList.get(GUID);
        this.defaultStyleDate = this.styleList.get(GUID+DATEFORMAT);
    }

    public CellStyle getStyle(Long name, boolean useDateTimeFormat)
    {
    	if(useDateTimeFormat)
    	{    		
    		return styleList.get(name.toString()+DATEFORMAT);
    	} else
    	{
    		return styleList.get(name.toString());
    	}
    }

    public void setAllStyles(IContext context, MxTemplate TemplateObject, Workbook book) throws CoreException
    {
        if (styleList != null)
        {
            styleList.clear();
        }
        
        this.createHelper = book.getCreationHelper();
        
        //CreationHelper createHelper = book.getCreationHelper();
        log.debug("-- Initialise all the styles to the hashmap.");

        List<IMendixObject> stylesObjects = Core.retrieveXPathQuery(context,"//" + MxCellStyle.getType()
                + "[XLSReport.MxCellStyle_Template='" + TemplateObject.getMendixObject().getId().toLong() + "']");

        for (IMendixObject styleObject : stylesObjects)
        {
            // Create a style
            MxCellStyle MxStyle = MxCellStyle.initialize(context, styleObject);            
            
            // Create a normal version of the style
            CellStyle style = createCellStyle(MxStyle, book, false);
            this.styleList.put(MxStyle.getMendixObject().getId().toLong()+"", style);
            
            // Create a normal version of the style
            CellStyle datestyle = createCellStyle(MxStyle, book, true);
            this.styleList.put(MxStyle.getMendixObject().getId().toLong()+DATEFORMAT, datestyle);           
        }
    }

    public CellStyle createCellStyle(MxCellStyle MxStyle, Workbook book, boolean dateTimeFormat)
    {
    	CellStyle style = book.createCellStyle();
        // Create the font for the style.
        Font font = book.createFont();
        font.setItalic(MxStyle.getTextItalic());            
        font.setFontHeightInPoints(MxStyle.getTextHeight().shortValue());
        if (MxStyle.getTextColor() != null && MxStyle.getTextColor() != MxColor.Blank)
        {
            font.setColor(getColor(MxStyle.getTextColor()));
        }
        if (MxStyle.getTextUnderline())
        {
            font.setUnderline(Font.U_SINGLE);
        }
        if (MxStyle.getTextBold())
        {
            font.setBold(true);
        }
        style.setFont(font);
        // Alignment
        style.setAlignment(getAlignment(MxStyle.getTextAlignment()));
        style.setVerticalAlignment(getVerticalAlignment(MxStyle.getTextVerticalalignment()));
        // Color fill and other options.
        if (MxStyle.getBackgroundColor() != null && MxStyle.getBackgroundColor() != MxColor.Blank)
        {
            style.setFillForegroundColor(getColor(MxStyle.getBackgroundColor()));
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        style.setRotation(MxStyle.getTextRotation().shortValue());
        if (MxStyle.getTextRotation() == 0)
        {
            style.setWrapText(MxStyle.getWrapText());
        }
        // Create border lines.
        if (MxStyle.getBorderTop() > 0)
        {
            style.setBorderTop(getBorderStyle(MxStyle.getBorderTop()));
        }
        if (MxStyle.getBorderBottom() > 0)
        {
            style.setBorderBottom(getBorderStyle(MxStyle.getBorderBottom()));
        }
        if (MxStyle.getBorderLeft() > 0)
        {
            style.setBorderLeft(getBorderStyle(MxStyle.getBorderLeft()));
        }
        if (MxStyle.getBorderRight() > 0)
        {
            style.setBorderRight(getBorderStyle(MxStyle.getBorderRight()));
        }
        if (MxStyle.getBorderColor() != null && MxStyle.getBorderColor() != MxColor.Blank)
        {
            style.setTopBorderColor(getColor(MxStyle.getBorderColor()));
            style.setBottomBorderColor(getColor(MxStyle.getBorderColor()));
            style.setLeftBorderColor(getColor(MxStyle.getBorderColor()));
            style.setRightBorderColor(getColor(MxStyle.getBorderColor()));
        } 
        
        if(dateTimeFormat)
        {        	
        	style.setDataFormat(this.createHelper.createDataFormat().getFormat(this.datePresentation));
        	log.trace("Created style with DateTimeFormat: " + style.getDataFormatString());
        }
    	    	   
    	return style;
    }
    
    private BorderStyle getBorderStyle(Integer borderBottom) {
    	if( borderBottom <= 0 ) 
    		return BorderStyle.NONE;
    	else if( borderBottom <= 1 )
    		return BorderStyle.THIN;
    	else if( borderBottom <= 2 )
    		return BorderStyle.MEDIUM;
    	else if( borderBottom > 2 )
    		return BorderStyle.THICK;
		
    	return BorderStyle.NONE;
	}

	private static short getColor(MxColor color)
    {
        switch (color)
        {
            case Black:
                return HSSFColorPredefined.BLACK.getIndex();
            case Blue:
                return HSSFColorPredefined.BLUE.getIndex();
            case Brown:
                return HSSFColorPredefined.BROWN.getIndex();
            case Green:
                return HSSFColorPredefined.GREEN.getIndex();
            case Light_Blue:
                return HSSFColorPredefined.LIGHT_BLUE.getIndex();
            case Orange:
                return HSSFColorPredefined.ORANGE.getIndex();
            case Pink:
                return HSSFColorPredefined.PINK.getIndex();
            case Red:
                return HSSFColorPredefined.RED.getIndex();
            case White:
                return HSSFColorPredefined.WHITE.getIndex();
            case Yellow:
                return HSSFColorPredefined.YELLOW.getIndex();
            case Gray_1:
                return HSSFColorPredefined.GREY_25_PERCENT.getIndex();
            case Gray_2:
                return HSSFColorPredefined.GREY_40_PERCENT.getIndex();
            case Gray_3:
                return HSSFColorPredefined.GREY_50_PERCENT.getIndex();
            case Gray_4:
                return HSSFColorPredefined.GREY_80_PERCENT.getIndex();
            default:
                return HSSFColorPredefined.WHITE.getIndex();
        }
    }

    private static HorizontalAlignment getAlignment(TextAlignment align)
    {
        if (align != null)
        {
            switch (align)
            {
                case Left:
                    return HorizontalAlignment.LEFT;
                case Center:
                    return HorizontalAlignment.CENTER;
                case Right:
                    return HorizontalAlignment.RIGHT;
            }
        }       
        return HorizontalAlignment.LEFT;
    }

    private static VerticalAlignment getVerticalAlignment(TextVerticalAlignment align)
    {
        if (align != null)
        {
            switch (align)
            {
                case Top:
                    return VerticalAlignment.TOP;
                case Middle:
                    return VerticalAlignment.CENTER;
                case Bottom:
                    return VerticalAlignment.BOTTOM;
            }
        }
        return VerticalAlignment.TOP;
    }
}
