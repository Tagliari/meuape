package com.meuap.listener;

import java.util.Date;

import org.hibernate.event.internal.DefaultSaveOrUpdateEventListener;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import com.meuap.objects.GenericAbstractObject;

public class SaveOrUpdateDateListener extends DefaultSaveOrUpdateEventListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event) {
		if (event.getObject() instanceof GenericAbstractObject) {
			GenericAbstractObject ga = (GenericAbstractObject) event.getObject();
			ga.setLastUpdatedDate(new Date());
		}
		super.onSaveOrUpdate(event);
	}
	
	
}
