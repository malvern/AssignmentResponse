package com.econetwireless.epay.business.service;

import com.econetwireless.epay.business.services.impl.PartnerCodeValidatorImpl;
import com.econetwireless.epay.dao.requestpartner.api.RequestPartnerDao;
import com.econetwireless.epay.domain.RequestPartner;
import com.econetwireless.utils.execeptions.EpayException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)       

public class PartnerCodeValidatorImplTest {
	@Mock
    public RequestPartnerDao requestPartnerDao;
    private PartnerCodeValidatorImpl partnerCodeValidator;
    private final String PARTNER_CODE="hot-recharge";


    @Before
    public void setup() {
        partnerCodeValidator = new PartnerCodeValidatorImpl(requestPartnerDao);
    }
    @Test
    public void shouldReturnTrueIfPartnerCodeIsNotNull() {
        final RequestPartner VALID_PARTNER= new RequestPartner();
        when(requestPartnerDao.findByCode(PARTNER_CODE)).thenReturn(VALID_PARTNER);
        assertTrue(partnerCodeValidator.validatePartnerCode(PARTNER_CODE));
        verify(requestPartnerDao,times(1)).findByCode(PARTNER_CODE);
    }
    @Test(expected = EpayException.class)
    public void shouldReturnThrowEpayExceptionIfPartnerCodeIsInvalid() {
        final RequestPartner INVALID_PARTNER=null;
        when(requestPartnerDao.findByCode(PARTNER_CODE)).thenReturn(INVALID_PARTNER);
        assertTrue(partnerCodeValidator.validatePartnerCode(PARTNER_CODE));
        verify(requestPartnerDao,times(1)).findByCode(PARTNER_CODE);
    }
}
