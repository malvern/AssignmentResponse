package com.econetwireless.epay.business.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


import com.econetwireless.epay.business.services.impl.ReportingServiceImpl;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;

@RunWith(MockitoJUnitRunner.class) 
public class ReportingServiceImplTest {
	@Mock
	 private SubscriberRequestDao subscriberRequestDao;
	 ReportingServiceImpl reportingServiceImpl;
	 private final String PARTNER_CODE="hot-recharge";
	 private final List<SubscriberRequest> subscriberRequest=new ArrayList<>();

	@Before
	public void setup() {
		reportingServiceImpl =new ReportingServiceImpl(subscriberRequestDao);
		
	}
	@Test
	public void shouldReturnAListOfAllPartnerCodes() {
		    when(subscriberRequestDao.findByPartnerCode(PARTNER_CODE)).thenReturn(subscriberRequest);
			assertEquals(0,reportingServiceImpl.findSubscriberRequestsByPartnerCode(PARTNER_CODE).size());
		    verify(subscriberRequestDao,times(1)).findByPartnerCode(PARTNER_CODE);
				
	}
}
