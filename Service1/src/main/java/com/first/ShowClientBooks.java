package com.first;

import businesslogic.ProgramariServiceBLL;
import model.ProgramariService;

import java.util.ArrayList;

public class ShowClientBooks implements ShowBooksStrategy{
    @Override
    public ArrayList<ProgramariService> showBooks(ProgramariServiceBLL programariServiceBLL,int id) {
        if(programariServiceBLL == null){
            return null;
        }
        return programariServiceBLL.selectByIdClient(id);
    }
}
