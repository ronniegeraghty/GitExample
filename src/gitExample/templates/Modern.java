/*----------------------------------------------------------------------------------------------------------------------
// ?  Copyright IBM Corp. 2007, 2011.  All Rights Reserved.
//
// US Government Users Restricted Rights - Use, duplication or
// disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
// 5724-U67
// Host Access Transformation Services technology
//
// Module Name: Modern.java
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
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import com.ibm.hats.rcp.transform.*;
import com.ibm.hats.rcp.ui.misc.*;
import com.ibm.hats.rcp.ui.templates.*;

public class Modern extends RcpTemplate {

    // RGB values for the table header, even row, and odd row colors, in
    // hexadecimal
    private final RGB TABLE_HEADER_BACKGROUND = new RGB(0x80, 0x80, 0x80); // gray
    private final RGB TABLE_HEADER_FOREGROUND = new RGB(0xff, 0xff, 0xff); // white
    private final RGB TABLE_ODD_ROW_BACKGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_GRAY).getRGB();
    private final RGB TABLE_EVEN_ROW_BACKGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_LIST_BACKGROUND).getRGB();
    private final RGB TABLE_HIGHLIGHT_BACKGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND).getRGB();
    private final RGB TABLE_HIGHLIGHT_FOREGROUND = Display.getCurrent().getSystemColor(SWT.COLOR_INFO_FOREGROUND).getRGB();

    private final RGB FOCUS_CONTROL_BACKGROUND = new RGB(232, 242, 254);
    private final RGB FOCUS_CONTROL_FOREGROUND = new RGB(0, 0, 0);

    protected Composite sideBarArea;
    protected TransformationAreaComposite transformationArea;

    private Label label;

    /**
     * @param parent
     * @param style
     */
    public Modern(Composite parent, int style) {
        super(parent, style);
        initialize();
    }

    /**
     * 
     */
    protected void initialize() {
        GridLayout gridLayout = new GridLayout(2, false);
        gridLayout.horizontalSpacing = 0;
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        setLayout(gridLayout);
        setLayoutData(new GridData(GridData.FILL_BOTH));

        if (java.beans.Beans.isDesignTime()) {
            this.setSize(new Point(400, 250));
        }

        setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));

        createSidebarArea();
        createTransformationArea();

        applyStyleToComposite(transformationArea);
    }

    /**
     * 
     */
    protected void createSidebarArea() {
        sideBarArea = new Canvas(this, SWT.NONE);
        sideBarArea.setBackground(ColorManager.getInstance(getDisplay()).getColor(221, 228, 255));
        sideBarArea.setLayout(new GridLayout(1, false));
        sideBarArea.setLayoutData(new GridData(SWT.NONE, SWT.FILL, false, true));

        label = new Label(sideBarArea, SWT.NONE);
        label.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/modern_image.gif")));
        label.setLayoutData(new GridData(SWT.BEGINNING, SWT.TOP, true, true));
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
    protected void createTransformationArea() {
        transformationArea = new TransformationAreaComposite(this, SWT.NONE);
        transformationArea.setLayout(new GridLayout(1, false));
        transformationArea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        transformationArea.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        transformationArea.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
    }

    /* (non-Javadoc)
     * @see com.ibm.hats.rcp.ui.templates.IRcpTemplate#getContentContainer()
     */
    public Composite getContentContainer() {
        return transformationArea;
    }

    /* (non-Javadoc)
     * @see com.ibm.hats.rcp.ui.templates.RcpTemplate#getFocusControlBackgroundColor()
     */
    public Color getFocusControlBackgroundColor() {
        return ColorManager.getInstance(getDisplay()).getColor(FOCUS_CONTROL_BACKGROUND);
    }

    /* (non-Javadoc)
     * @see com.ibm.hats.rcp.ui.templates.RcpTemplate#getFocusControlForegroundColor()
     */
    public Color getFocusControlForegroundColor() {
        return ColorManager.getInstance(getDisplay()).getColor(FOCUS_CONTROL_FOREGROUND);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.hats.rcp.ui.templates.IRcpTemplate#getColorMapper()
     */
    public IColorMapper getColorMapper() {
        return WhiteBackgroundColorMapper.getInstance();
    }

    public ITableColorProvider getTableColorProvider() {
        return new TableColorProvider(
                // header color
                new CompositeColor(rgb2Color(TABLE_HEADER_BACKGROUND), rgb2Color(TABLE_HEADER_FOREGROUND)),
                // even row color
                new CompositeColor(rgb2Color(TABLE_EVEN_ROW_BACKGROUND), getDefaultForegroundColor()),
                // odd row color
                new CompositeColor(rgb2Color(TABLE_ODD_ROW_BACKGROUND), getDefaultForegroundColor()),
                // highlight color
                new CompositeColor(rgb2Color(TABLE_HIGHLIGHT_BACKGROUND), rgb2Color(TABLE_HIGHLIGHT_FOREGROUND)));
    }
} // @jve:decl-index=0:visual-constraint="1,1"
