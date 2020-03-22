package com.first;

import businesslogic.PrestatorBLL;
import dao.PrestatorDAO;
import model.Prestator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class TestPrestator {

    PrestatorDAO prestatorDAO;
    @Mock
    PrestatorBLL prestatorBLL;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void init(){
        prestatorDAO = new PrestatorDAO();
        prestatorBLL = new PrestatorBLL(prestatorDAO);
    }

    @Test
    public void testFindById(){
        when(prestatorBLL.findById(0)).thenReturn(new Prestator(0,"Default","Default","Default"));
        assertEquals("Default",prestatorBLL.findById(0).getNume());
        verify(prestatorDAO).findById(0);
    }
}
