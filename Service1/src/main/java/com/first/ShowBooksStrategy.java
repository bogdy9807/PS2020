package com.first;

import businesslogic.ProgramariServiceBLL;
import model.ProgramariService;

import java.util.ArrayList;

public interface ShowBooksStrategy {
    public ArrayList<ProgramariService> showBooks(ProgramariServiceBLL programariServiceBLL, int id);
}
