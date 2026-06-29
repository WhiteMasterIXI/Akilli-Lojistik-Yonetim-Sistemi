package Controller;

import java.util.List;
import NotifySystem.LogRecord;

import Services.SingletonPattern.LogService;

public class LogController {

	 public static boolean LogSave(String message){
		return LogService.getInstance().log(message);
	 }
	 
	 public static List<LogRecord> getAllLogs(){
		 return LogService.getInstance().getlogs();
	 }
	 
	 public static List<LogRecord> getUserLogs(long user_id){
		 return LogService.getInstance().getLogsByUserId(user_id);
	 }
}
