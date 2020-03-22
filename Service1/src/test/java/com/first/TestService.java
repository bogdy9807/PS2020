package com.first;

import businesslogic.ServiceBLL;
import dao.ServiceDAO;
import model.Service;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class TestService {

    ServiceDAO serviceDAO;
    @Mock
    ServiceBLL serviceBLL;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void init(){
        serviceDAO = new ServiceDAO();
        serviceBLL = new ServiceBLL(serviceDAO);
    }

    @Test
    public void testFindById(){
        when(serviceDAO.findById(0)).thenReturn(new Service(0,0,"Default","Default"));
        assertEquals("Default",serviceBLL.findById(0));
        verify(serviceDAO).findById(0);
    }
}
