package com.centdb.LogManagement;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import com.centdb.LogManagement.LogWriter.LogWriter;
public class SqlLogger {
    public void generalLog(String DbName, String TableName, String RowCount, String Category){
       ArrayList<String> data = new ArrayList<>();
       data.add(DbName);
       data.add(TableName);
       data.add(RowCount);
       data.add(Category);
       String WriteStr = prepareString(data);
       String GeneralLogPath = "LogResources/LogGeneral.txt";
       new LogWriter().write(GeneralLogPath, WriteStr);


    }

    public void eventLog(String DbName, String eventReport, String timeStamp){
        ArrayList<String> data = new ArrayList<>();
        data.add(DbName);
        data.add(eventReport);
        data.add(timeStamp);
        String writeStr = prepareString(data);
        String eventLogPath = "LogResources/LogEvent.txt";
        new LogWriter().write(eventLogPath, writeStr);
    }

    public void queryLog(String DbName, String TableName, String Query, String timestamp){
        ArrayList<String> data = new ArrayList<>();
        data.add(DbName);
        data.add(TableName);
        data.add(Query);
        data.add(timestamp);
        String writeStr = prepareString(data);
        String QueryLogPath = "LogResources/LogQuery.txt";
        new LogWriter().write(QueryLogPath, writeStr);

    }


    public String prepareString(ArrayList<String> data){
        AtomicReference<String> resp= new AtomicReference<>("");
        data.forEach(str -> {
            resp.set(resp + str + "|");
        });
        resp.set(resp+"\n");
        return resp.toString();
    }
}
