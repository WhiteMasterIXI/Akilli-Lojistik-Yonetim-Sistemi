package Services.SingletonPattern;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import NotifySystem.LogRecord;
import Utils.Session;
import dao.LogRecordDao;

public class LogService {

    private static LogService instance;

    private LogService() {

    }

    public static LogService getInstance() {

        if(instance == null) {

            instance = new LogService();
        }

        return instance;
    }

    public boolean log(
            String message
    ) {

        LogRecord log =
                new LogRecord();

        log.setMessage(message);
        log.setActor(Session.user);
        log.setTarih(LocalDateTime.now());

        if(LogRecordDao.save(log))
        return true;
        return false;
    }
    
    public List<LogRecord> getlogs() {
    	return LogRecordDao.findAll();
    }
    
    public List<LogRecord> getLogsByUserId(long user_id){
    	return LogRecordDao.findByUser(user_id);
    }
}