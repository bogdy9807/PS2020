package com.first;

import businesslogic.ClientBLL;
import dao.ClientDAO;
import model.Client;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestClient {

    ClientDAO clientDAO;
    @Mock
    ClientBLL clientBLL;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void init(){
        clientDAO = new ClientDAO();
        clientBLL = new ClientBLL(clientDAO);
    }

    @Test
    public void testFindById() {
        when(clientDAO.findById(0)).thenReturn(new Client(0, "Default", "Default", "Default"));
        assertEquals("Default", clientBLL.findById(0).getNume());
        verify(clientDAO).findById(0);
    }
}
