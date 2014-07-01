/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.rivetlogic.wrapper.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import java.util.List;

import org.rivetlogic.wrapper.service.base.WrapperServiceBaseImpl;

/**
 * The implementation of the wrapper remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.rivetlogic.wrapper.service.WrapperService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Christopher Jimenez
 * @see org.rivetlogic.wrapper.service.base.WrapperServiceBaseImpl
 * @see org.rivetlogic.wrapper.service.WrapperServiceUtil
 */
public class WrapperServiceImpl extends WrapperServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link org.rivetlogic.wrapper.service.WrapperServiceUtil} to access the wrapper remote service.
	 */
	@AccessControlled(guestAccessEnabled=true)
	public List<AssetEntry> getAssetEntries(AssetEntryQuery assetEntryQuery) throws SystemException{
		return AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
	}
	
	@AccessControlled(guestAccessEnabled=true)
	public JournalArticle getLatestArticle(long resourcePrimKey) throws PortalException, SystemException{
		return JournalArticleLocalServiceUtil.getLatestArticle(resourcePrimKey);
	}
}