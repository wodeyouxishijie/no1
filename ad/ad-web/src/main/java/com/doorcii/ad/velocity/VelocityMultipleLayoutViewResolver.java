package com.doorcii.ad.velocity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

/**
 * 视图解析
 * @author Jacky
 */
public class VelocityMultipleLayoutViewResolver extends VelocityViewResolver {
	private static final Logger logger = Logger.getLogger(VelocityMultipleLayoutViewResolver.class ); 

	private Map<String, String> mappings = new HashMap<String, String>();

	private String layoutKey;

	private String screenContentKey;

	protected Class<?> requiredViewClass() {
		return VelocityLayoutView.class;
	}

	public void setLayoutKey( final String layoutKey) {
		this.layoutKey = layoutKey;
	}

	public void setScreenContentKey( final String screenContentKey) {
		this.screenContentKey = screenContentKey;
	}

	protected AbstractUrlBasedView buildView( final String viewName ) throws Exception {
		if (logger.isDebugEnabled() )
			logger.debug( "Building view using multiple layout resolver. View name is " + viewName );

		VelocityLayoutView view = (VelocityLayoutView) super.buildView( viewName );

		if (this.layoutKey != null)
			view.setLayoutKey(this.layoutKey);

		if (this.screenContentKey != null)
			view.setScreenContentKey(this.screenContentKey);

		if ( ! this.mappings.isEmpty() ) {
			for ( Map.Entry<String, String> entry : this.mappings.entrySet() ) {
				String mappingRegexp = StringUtils.replace(entry.getKey(), "*", ".*");
				if ( viewName.matches( mappingRegexp ) ) {
					if ( logger.isDebugEnabled() )
						logger.debug("Found matching view. Setting layout url to  "+ entry.getValue() );
					view.setLayoutUrl( entry.getValue() );
					return view;
				}
			}
		}

		return view;
	}

	public Map<String, String> getMappings() {
		return mappings;
	}

	public void setMappings(Map<String, String> mappings) {
		this.mappings = mappings;
	}
}