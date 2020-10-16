/*----------------------------------------------------------------------------------------------------------------------
// (C) Copyright IBM Corp.  2007.  All Rights Reserved.
//
// US Government Users Restricted Rights - Use, duplication or
// disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
//
// Host Access Transformation Services technology
//
// Module Name: BasicTransformation.java
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
package gitExample.transformations;

import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import com.ibm.hats.rcp.transform.*;
import com.ibm.hats.rcp.transform.widgets.*;
import com.ibm.hats.rcp.ui.jve.*;
import com.ibm.hats.rcp.ui.transformations.*;
import com.ibm.hats.transform.components.*;
import com.ibm.hats.transform.regions.*;

/**
 * A basic transformation renders a host screen using basic field widget rendering.
 * Text replacement and global rules are not applied.
 * 
 * @see com.ibm.hats.rcp.ui.transformations.RcpTransformation 
 * 
 */
public class BasicTransformation extends RcpTransformation {

    public static final String screenCapture = JveEditingUtil.getDefaultScreenCapture();
    private ComponentRendering componentRendering = null;
    
    /**
     * Constructs a new BasicTransformation object.
     * 
     */
    public BasicTransformation(Composite parent, int style) {
        super(parent, style);
        initialize();
    }

    private void initialize() {
        setLayout(new GridLayout(1, false));

        componentRendering = new ComponentRendering(this, 0);
        componentRendering.setScreenCapture(screenCapture);
        componentRendering.setComponent(FieldComponent.class.getName());
        componentRendering.setWidget(SwtFieldWidget.class.getName());
        componentRendering.setRegion(new BlockScreenRegion(1, 1, -1,-1));
        componentRendering.setApplyTextReplacement(false);
        componentRendering.setApplyGlobalRules(false);
    }

} //  @jve:decl-index=0:visual-constraint="10,10"
