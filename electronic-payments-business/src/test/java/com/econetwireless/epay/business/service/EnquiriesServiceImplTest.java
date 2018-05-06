package com.econetwireless.epay.business.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.econetwireless.epay.business.integrations.api.ChargingPlatform;
import com.econetwireless.epay.business.integrations.impl.ChargingPlatformImpl;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.in.webservice.IntelligentNetworkService;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.messages.AirtimeBalanceResponse;
import com.econetwireless.utils.pojo.INBalanceResponse;

@RunWith(MockitoJUnitRunner.class) 
public class EnquiriesServiceImplTest {
	
		@Mock
		private ChargingPlatformImpl chargingPlatformImpl;
		private IntelligentNetworkService intelligentNetworkService;
	    private final String PARTNER_CODE="hot-recharge";
	    private final String MSISDN="0775848435";

		@Before
		public void setup() {
				
		}
		@Test
		public void shouldReturnBalanceResponse() {
			final AirtimeBalanceResponse airtimeBalanceResponse = new AirtimeBalanceResponse();
			chargingPlatformImpl =new ChargingPlatformImpl(intelligentNetworkService);
			airtimeBalanceResponse.setAmount(10);
			airtimeBalanceResponse.setMsisdn(MSISDN);
			airtimeBalanceResponse.setResponseCode(ResponseCode.SUCCESS.getCode());
			airtimeBalanceResponse.setNarrative("");
			final INBalanceResponse inBalanceResponse = chargingPlatformImpl.enquireBalance(PARTNER_CODE, MSISDN);
	        System.out.println(airtimeBalanceResponse.getAmount());
			assertTrue("True" ,airtimeBalanceResponse.getAmount()==inBalanceResponse.getAmount());
			
		}
}
