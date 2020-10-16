/*----------------------------------------------------------------------------------------------------------------------
// ?  Copyright IBM Corp.  2007, 2011.  All Rights Reserved.
//
// US Government Users Restricted Rights - Use, duplication or
// disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
//
// Host Access Transformation Services technology
//
// Module Name: Sports.java
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

import org.eclipse.jface.resource.*;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import com.ibm.hats.rcp.transform.*;
import com.ibm.hats.rcp.ui.misc.*;
import com.ibm.hats.rcp.ui.templates.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class Sports extends RcpTemplate {

    // RGB values for the table header, even row, and odd row colors, in
    // hexadecimal
    final RGB TABLE_HEADER_BACKGROUND = new RGB(0x80, 0x80, 0x40);
    final RGB ODD_ROW_BACKGROUND = new RGB(0xec, 0xea, 0xc6);
    final RGB EVEN_ROW_BACKGROUND = new RGB(0xdd, 0xda, 0x9b);
    final RGB TABLE_HEADER_FOREGROUND = new RGB(0xff, 0xff, 0xff);

    protected Composite header;
    protected TransformationAreaComposite transformationArea;
    
    private Label label;
    private Color focusControlBackgroundColor;
    private Color focusControlForegroundColor;

    /**
     * @param parent
     * @param style
     */
    public Sports(Composite parent, int style) {
        super(parent, style);
        initialize();
    }

    /**
     * 
     */
    private void initialize() {
        GridLayout layout = new GridLayout(1, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.verticalSpacing = 0;
        setLayout(layout);
        setLayoutData(new GridData(GridData.FILL_BOTH));

        if (java.beans.Beans.isDesignTime()) {
            this.setSize(new org.eclipse.swt.graphics.Point(400, 250));
        }

        // Allocate resources
        focusControlBackgroundColor = ColorManager.getInstance(getDisplay()).getColor(255, 236, 159);
        focusControlForegroundColor = Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);

        createHeader();
        createTransformationArea();

        applyStyleToComposite(transformationArea);
    }
    
    /**
     * 
     */
    private void createHeader() {
        header = new Composite(this, SWT.NONE);
        GridLayout layout = new GridLayout(1, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        header.setLayout(layout);
        header.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        header.setBackground(ColorManager.getInstance(getDisplay()).getColor(156, 85, 41));

        label = new Label(header, SWT.RIGHT);
        label.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/sportsmast.gif")));
        label.setLayoutData(new GridData(SWT.END, SWT.CENTER, true, true));
        label.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent e) {
                Image image = label.getImage();
                if (image != null && !image.isDisposed()) {
                    image.dispose();
                }
            }
        });        
    }
    
    /**
     * 
     */
    private void createTransformationArea() {
        // Construct transformation area
        transformationArea = new TransformationAreaComposite(this, SWT.NONE);
        transformationArea.setLayout(new GridLayout(1, false));
        transformationArea.setLayoutData(new GridData(GridData.FILL_BOTH));
        transformationArea.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
        transformationArea.setBackground(ColorManager.getInstance(getDisplay()).getColor(254, 241, 197));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.hats.rcp.ui.templates.RcpTemplate#getDefaultFont()
     */
    public Font getDefaultFont() {
        return JFaceResources.getDefaultFont();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.hats.rcp.ui.templates.RcpTemplate#getTransformationArea()
     */
    public Composite getContentContainer() {
        return transformationArea;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.hats.rcp.ui.misc.IControlStyleProvider#getFocusControlBackgroundColor()
     */
    public Color getFocusControlBackgroundColor() {
        return focusControlBackgroundColor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.hats.rcp.ui.misc.IControlStyleProvider#getFocusControlForegroundColor()
     */
    public Color getFocusControlForegroundColor() {
        return focusControlForegroundColor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.hats.rcp.ui.templates.RcpTemplate#getColorMapper()
     */
    public IColorMapper getColorMapper() {
        return WhiteBackgroundColorMapper.getInstance();
    }
}
