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

package org.rivetlogic.wrapper.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WrapperService}.
 *
 * @author Christopher Jimenez
 * @see WrapperService
 * @generated
 */
public class WrapperServiceWrapper implements WrapperService,
	ServiceWrapper<WrapperService> {
	public WrapperServiceWrapper(WrapperService wrapperService) {
		_wrapperService = wrapperService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _wrapperService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_wrapperService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _wrapperService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public java.util.List<com.liferay.portlet.asset.model.AssetEntry> getAssetEntries(
		com.liferay.portlet.asset.service.persistence.AssetEntryQuery assetEntryQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wrapperService.getAssetEntries(assetEntryQuery);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalArticle getLatestArticle(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wrapperService.getLatestArticle(resourcePrimKey);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public WrapperService getWrappedWrapperService() {
		return _wrapperService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedWrapperService(WrapperService wrapperService) {
		_wrapperService = wrapperService;
	}

	@Override
	public WrapperService getWrappedService() {
		return _wrapperService;
	}

	@Override
	public void setWrappedService(WrapperService wrapperService) {
		_wrapperService = wrapperService;
	}

	private WrapperService _wrapperService;
}