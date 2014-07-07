/**
 * 
 */
package com.rivetlogic.velocity.util;

import static com.rivetlogic.velocity.portlet.VelocityServiceConstant.UNDEFINED_ID;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Christopher Jimenez
 *
 */
public class VelocityServiceUtil {
	
	private static final Log LOG = LogFactoryUtil.getLog(VelocityServiceUtil.class);
	
	public static void returnJSONObject(PortletResponse response, JSONArray jsonArray) {
        HttpServletResponse servletResponse = PortalUtil.getHttpServletResponse(response);
        servletResponse.setHeader(HttpHeaders.CACHE_CONTROL, HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);
		servletResponse.setHeader(HttpHeaders.PRAGMA, HttpHeaders.PRAGMA_NO_CACHE_VALUE);
		servletResponse.setHeader(HttpHeaders.EXPIRES, String.valueOf(UNDEFINED_ID));
        PrintWriter pw;
        try {
            pw = servletResponse.getWriter();
            pw.write(jsonArray.toString());
            pw.close();
        } catch (IOException e) {
            LOG.error("Error while returning json", e);
        }
    }
}
