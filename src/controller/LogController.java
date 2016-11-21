package controller;

import model.Log;
import model.User;
import service.LogService;
import service.LogServiceImpl;

/**
 * Created by Alican on 2016-11-21.
 */
public class LogController {
    private LogService logService;

    public LogController(){
        logService = new LogServiceImpl();
    }

    public void addLog(Log log){
        logService.addLog(log);
        //return result
    }

    public void getLogs(User user){
        logService.getLogs(user);
        //return result
    }
}//class
