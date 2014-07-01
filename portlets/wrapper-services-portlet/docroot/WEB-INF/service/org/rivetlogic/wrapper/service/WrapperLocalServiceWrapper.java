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
 * Provides a wrapper for {@link WrapperLocalService}.
 *
 * @author Christopher Jimenez
 * @see WrapperLocalService
 * @generated
 */
public class WrapperLocalServiceWrapper implements WrapperLocalService,
	ServiceWrapper<WrapperLocalService> {
	public WrapperLocalServiceWrapper(WrapperLocalService wrapperLocalService) {
		_wrapperLocalService = wrapperLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _wrapperLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_wrapperLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _wrapperLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public WrapperLocalService getWrappedWrapperLocalService() {
		return _wrapperLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedWrapperLocalService(
		WrapperLocalService wrapperLocalService) {
		_wrapperLocalService = wrapperLocalService;
	}

	@Override
	public WrapperLocalService getWrappedService() {
		return _wrapperLocalService;
	}

	@Override
	public void setWrappedService(WrapperLocalService wrapperLocalService) {
		_wrapperLocalService = wrapperLocalService;
	}

	private WrapperLocalService _wrapperLocalService;
}