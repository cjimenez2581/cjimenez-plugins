package com.rivetlogic.velocity.portlet;

import static com.rivetlogic.velocity.portlet.VelocityServiceConstant.CMD;
import static com.rivetlogic.velocity.portlet.VelocityServiceConstant.CMD_GET_ENTRIES;
import static com.rivetlogic.velocity.portlet.VelocityServiceConstant.P_ARTICLE;
import static com.rivetlogic.velocity.portlet.VelocityServiceConstant.P_CLASS_TYPE_IDS;
import static com.rivetlogic.velocity.portlet.VelocityServiceConstant.P_END;
import static com.rivetlogic.velocity.portlet.VelocityServiceConstant.P_GROUP_IDS;
import static com.rivetlogic.velocity.portlet.VelocityServiceConstant.P_START;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.rivetlogic.velocity.util.VelocityServiceUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * Portlet implementation class VelocityServicePortlet
 */
public class VelocityServicePortlet extends MVCPortlet {
 
	private static final Log LOG = LogFactoryUtil.getLog(VelocityServicePortlet.class);
	
	@Override
	public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException,
			PortletException {
		
		String cmd = ParamUtil.getString(request, CMD, StringPool.BLANK);
		LOG.debug( CMD + StringPool.COLON + cmd);
		JSONArray jsonResponse = JSONFactoryUtil.createJSONArray();
		List<AssetEntry> entries = null;
		JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
		if(CMD_GET_ENTRIES.equalsIgnoreCase(cmd)){
			AssetEntryQuery assetEntryQuery = getAssetQueryFromRequest(request);	
			
			try {
				entries = AssetEntryServiceUtil.getEntries(assetEntryQuery);
				for(AssetEntry assetEntry : entries){
					JournalArticle journal = getLatestVersion(assetEntry.getClassPK());
					
					if(journal != null){
						JSONObject jsonEntry = JSONFactoryUtil.createJSONObject(jsonSerializer.serialize(assetEntry));
						JSONObject jsonArticle = JSONFactoryUtil.createJSONObject(jsonSerializer.serialize(journal));
						jsonEntry.put(P_ARTICLE, jsonArticle);
						jsonResponse.put(jsonEntry);
					}
				}
				
			} catch (Exception e) {
				LOG.error(e);
			}
		}
		
		if(jsonResponse != null){
			VelocityServiceUtil.returnJSONObject(response, jsonResponse);
		}
	}
	
	private JournalArticle getLatestVersion(Long resourcePrimKey){
		JournalArticle journalArticle = null;
		try {
			 journalArticle = JournalArticleServiceUtil.getLatestArticle(resourcePrimKey);
		} catch (PrincipalException pe) {
			LOG.warn("Not access allowed to this journal", pe);
		} 
		catch (Exception e) {
			LOG.error(e);
		}
		
		return journalArticle;
	}
	
	private AssetEntryQuery getAssetQueryFromRequest(PortletRequest request){
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
		assetEntryQuery.setStart(ParamUtil.getInteger(request, P_START));
		assetEntryQuery.setEnd(ParamUtil.getInteger(request, P_END));
		assetEntryQuery.setClassTypeIds(ParamUtil.getLongValues(request, P_CLASS_TYPE_IDS));
		assetEntryQuery.setGroupIds(ParamUtil.getLongValues(request, P_GROUP_IDS));
		
		return assetEntryQuery;
	}
}
