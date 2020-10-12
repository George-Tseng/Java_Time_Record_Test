package javaTest;

import java.util.TimerTask;

public class File_Timer extends TimerTask {
    public void run(){
        String timeNow = Get_Date.getDateNow();
        File_Conf.writeTmpFile(timeNow);
    }
}
