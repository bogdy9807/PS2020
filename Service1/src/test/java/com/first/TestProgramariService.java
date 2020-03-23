package com.first;

import businesslogic.ProgramariServiceBLL;
import dao.ProgramariServiceDAO;
import model.ProgramariService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class TestProgramariService {
    ProgramariServiceDAO programariServiceDAO;
    @Mock
    ProgramariServiceBLL programariServiceBLL;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void init(){
        programariServiceDAO = new ProgramariServiceDAO();
    }

    @Test
    public void testFindById(){
        when(programariServiceBLL.findById(0)).thenReturn(new ProgramariService(0,0,0,"Default","Default",0,"Default"));
        assertEquals(programariServiceDAO.findById(0).getData_creare(),programariServiceBLL.findById(0).getData_creare());
        verify(programariServiceBLL).findById(0);
    }
}
