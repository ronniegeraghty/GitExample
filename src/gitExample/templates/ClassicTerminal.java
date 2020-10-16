/*----------------------------------------------------------------------------------------------------------------------
// (C) Copyright IBM Corp.  2007.  All Rights Reserved.
//
// US Government Users Restricted Rights - Use, duplication or
// disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
//
// Host Access Transformation Services technology
//
// Module Name: ClassicTerminal.java
//
// Function: See class JavaDoc for details.
//
// This sample is provided AS IS.
//
// Permission to use, copy and modify this software for any purpose and without fee is hereby granted, provided that
// the name of IBM not be used in advertising or publicity pertaining to distribution of the software without
// specific written permission.
//
// IBM DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SAMPLE, INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND
// FITNESS. IN NO EVENT SHALL IBM BE LIABLE FOR ANY SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
// WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
// TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SAMPLE.
*///--------------------------------------------------------------------------------------------------------------------
package gitExample.templates;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.forms.*;
import org.eclipse.ui.forms.widgets.*;

import com.ibm.hats.rcp.transform.*;
import com.ibm.hats.rcp.ui.misc.*;
import com.ibm.hats.rcp.ui.templates.*;

public class ClassicTerminal extends RcpTemplate {

    // Platform check for button color problem on Linux
    private static final boolean isWin32 = java.io.File.separatorChar == '\\';

    // Table color constants
    private final RGB TABLE_HEADER_BACKGROUND       = new RGB(0x80, 0x80, 0x80); // gray 
    private final RGB TABLE_HEADER_FOREGROUND       = new RGB(0xff, 0xff, 0xff); // white
    private final RGB TABLE_EVEN_ROW_BACKGROUND     = new RGB(0x66, 0x66, 0x66); // dark gray
    private final RGB TABLE_ODD_ROW_BACKGROUND      = new RGB(0x33, 0x33, 0x33); // darker gray    
    private final RGB TABLE_HIGHLIGHT_BACKGROUND    = Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND).getRGB();
    private final RGB TABLE_HIGHLIGHT_FOREGROUND    = Display.getCurrent().getSystemColor(SWT.COLOR_INFO_FOREGROUND).getRGB();

    // Focus control constants
    private final RGB FOCUS_CONTROL_BACKGROUND =    Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND).getRGB();
    private final RGB FOCUS_CONTROL_FOREGROUND =    Display.getCurrent().getSystemColor(SWT.COLOR_INFO_FOREGROUND).getRGB();

    // Parent composite for a transformation displayed on this template 
    protected TransformationAreaComposite transformationArea;

    /**
     * Default constructor.
     * 
     * @param parent
     * @param style
     */
    public ClassicTerminal(Composite parent, int style) {
        super(parent, style);
        initialize();
    }

    /**
     * Initializes the layout and colors of this template.  
     */
    protected void initialize() {
        GridLayout layout = new GridLayout(1, false);
        setLayout(layout);
        setLayoutData(new GridData(GridData.FILL_BOTH));

        // Set the size of the composite when in the JVE design page
        if (java.beans.Beans.isDesignTime()) {
            this.setSize(new Point(400, 250));
        }

        setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
        setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GREEN));

        createTransformationArea();

        applyStyleToComposite(transformationArea);
    }

    /**
     * Creates the transformation and sets its layout and color.
     */
    protected void createTransformationArea() {
        transformationArea = new TransformationAreaComposite(this, 0);
        GridLayout layout = new GridLayout(1, false);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        transformationArea.setLayout(layout);
        transformationArea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        transformationArea.setBackground(getBackground());
        transformationArea.setForeground(getForeground());
    }

    /**
     * @see com.ibm.hats.rcp.ui.templates.IRcpTemplate#getContentContainer()
     */
    public Composite getContentContainer() {
        return transformationArea;
    }

    /**
     * @see com.ibm.hats.rcp.ui.templates.RcpTemplate#getFocusControlBackgroundColor()
     */
    public Color getFocusControlBackgroundColor() {
        return ColorManager.getInstance(getDisplay()).getColor(FOCUS_CONTROL_BACKGROUND);
    }

    /**
     * @see com.ibm.hats.rcp.ui.templates.RcpTemplate#getFocusControlForegroundColor()
     */
    public Color getFocusControlForegroundColor() {
        return ColorManager.getInstance(getDisplay()).getColor(FOCUS_CONTROL_FOREGROUND);
    }

    /**
     * @see com.ibm.hats.rcp.ui.templates.RcpTemplate#getControlBackgroundColor(org.eclipse.swt.widgets.Control)
     */
    public Color getControlBackgroundColor(Control control) {
        // If the control is null or disposed, return null
        if (control == null || control.isDisposed()) return null;
        
        // If the control's background color has been explicitly set, return its background color
        if (control.getData(SwtDataConstants.DATA_BACKGROUND_COLOR_SET) != null && ((Boolean) control.getData(SwtDataConstants.DATA_BACKGROUND_COLOR_SET)).booleanValue()) {
            return control.getBackground();
        }        
        
        // If the control is a radio button or checkbox, return its current background color
        if (control instanceof Button && (!isWin32 || (control.getStyle() & SWT.PUSH) != SWT.PUSH)) {
            return control.getBackground();
        } else if (control instanceof Text || control instanceof Combo || control instanceof Table || control instanceof List || (control instanceof Button && ((control.getStyle() & SWT.PUSH) == SWT.PUSH))) {            
            return getDefaultBackgroundColor(); 
        }
        
        return super.getControlBackgroundColor(control);
    }

    /**
     * @see com.ibm.hats.rcp.ui.templates.RcpTemplate#getControlForegroundColor(org.eclipse.swt.widgets.Control)
     */
    public Color getControlForegroundColor(Control control) {
        // If the control is null or disposed, return null
        if (control == null || control.isDisposed()) return null;
        
        // If the control's foreground color has been explicitly set, return its foreground color
        if (control.getData(SwtDataConstants.DATA_FOREGROUND_COLOR_SET) != null && ((Boolean) control.getData(SwtDataConstants.DATA_FOREGROUND_COLOR_SET)).booleanValue()) {
            return control.getForeground();
        }
        
        // If the control is a radio button or checkbox, return its current foreground color (see Eclipse bug #87088)
        if (control instanceof Button && (!isWin32 || (control.getStyle() & SWT.PUSH) != SWT.PUSH)) {
            return control.getForeground();
        } else if (control instanceof Text || control instanceof Combo || control instanceof Table || control instanceof List || (control instanceof Button && ((control.getStyle() & SWT.PUSH) == SWT.PUSH))) {
            return getDefaultForegroundColor(); 
        }
        
        return super.getControlForegroundColor(control);
    }
    
    /**
     * Returns the integer style to use when constructing a control of the specified class type. 
     * 
     * This implementation returns 0 if the specified class is org.eclipse.swt.widgets.Text, else 
     * it returns the value of super.getControlClassStyle(Class) is returned.
     * 
     * @see com.ibm.hats.rcp.ui.misc.IControlStyleProvider#getControlClassStyle(java.lang.Class)
     */
    public int getControlClassStyle(Class controlClass) {
        if (controlClass.equals(Text.class)) {
            return 0;// super.getControlClassStyle(controlClass);
        } else {
            return super.getControlClassStyle(controlClass);
        }
    }

    /**
     * @see com.ibm.hats.rcp.ui.misc.IFormToolkitProvider#getFormToolkit()
     */
    public FormToolkit getFormToolkit() {
        if (formToolkit == null) {
            FormColors colors = new FormColors(getDisplay());
            colors.setBackground(getDefaultBackgroundColor());
            colors.setForeground(getDefaultForegroundColor());
            formToolkit = new FormToolkit(colors);

            formToolkit.getHyperlinkGroup().setActiveForeground(getDefaultForegroundColor());
            formToolkit.getHyperlinkGroup().setForeground(ColorManager.getInstance(getDisplay()).getColor(100, 255, 100));
        }

        return formToolkit;
    }


    /**
     * @see com.ibm.hats.rcp.ui.templates.RcpTemplate#getColorMapper()
     */
    public IColorMapper getColorMapper() {
        return DefaultColorMapper.getInstance();
    }

    /**
     * @see com.ibm.hats.rcp.ui.templates.RcpTemplate#getTableColorProvider()
     */
    public ITableColorProvider getTableColorProvider() {
        return new TableColorProvider(
                //header color
                new CompositeColor(rgb2Color(TABLE_HEADER_BACKGROUND), rgb2Color(TABLE_HEADER_FOREGROUND)),
                //even row color
                new CompositeColor(rgb2Color(TABLE_EVEN_ROW_BACKGROUND), getDefaultForegroundColor()),
                //odd row color
                new CompositeColor(rgb2Color(TABLE_ODD_ROW_BACKGROUND), getDefaultForegroundColor()),
                // highlight color
                new CompositeColor(rgb2Color(TABLE_HIGHLIGHT_BACKGROUND), rgb2Color(TABLE_HIGHLIGHT_FOREGROUND)));
    }
}
