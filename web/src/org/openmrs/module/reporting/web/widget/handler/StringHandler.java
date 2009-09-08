/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.reporting.web.widget.handler;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang.StringUtils;
import org.openmrs.annotation.Handler;
import org.openmrs.module.reporting.web.widget.WidgetConfig;
import org.openmrs.module.reporting.web.widget.html.TextAreaWidget;
import org.openmrs.module.reporting.web.widget.html.TextWidget;
import org.openmrs.module.reporting.web.widget.html.Widget;
import org.openmrs.module.reporting.web.widget.html.WidgetFactory;

/**
 * FieldGenHandler for String Types
 */
@Handler(supports={String.class, Character.class}, order=50)
public class StringHandler extends WidgetHandler {
	
	/** 
	 * @see WidgetHandler#render(WidgetConfig)
	 */
	@Override
	public void render(WidgetConfig config, Writer w) throws IOException {
		
		Widget widget = null;
		
		if (config.getType() == Character.class) {
			widget = WidgetFactory.getInstance(TextWidget.class, config);
			config.setConfiguredAttribute("size", "2");
			config.setConfiguredAttribute("maxLength", "1");
		}
		else {
			String rows = config.getAttributeValue("rows");
			String cols = config.getAttributeValue("cols");
			if (StringUtils.isNotEmpty(rows) || StringUtils.isNotEmpty(cols) || "textarea".equals(config.getFormat())) {
				widget = WidgetFactory.getInstance(TextAreaWidget.class, config);
			}
			else {
				widget = WidgetFactory.getInstance(TextWidget.class, config);
				config.setConfiguredAttribute("size", "40");
			}
		}
		widget.render(config, w);
	}

	/** 
	 * @see WidgetHandler#parse(String, Class<?>)
	 */
	@Override
	public Object parse(String input, Class<?> type) {
		return input;
	}
}
