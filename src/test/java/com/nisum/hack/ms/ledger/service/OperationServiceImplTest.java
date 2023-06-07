package com.nisum.hack.ms.ledger.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class OperationServiceImplTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void sumValuesSuccessful() {
        OperationService service = new OperationServiceImpl();
        int result = service.sumValues(1, 1);
        assertEquals(2, result);
    }

    @Test
    public void sumValuesFailure() {
        OperationService service = new OperationServiceImpl();
        int result = service.sumValues(1, 0);
        assertNotEquals(2, result);
    }
}
