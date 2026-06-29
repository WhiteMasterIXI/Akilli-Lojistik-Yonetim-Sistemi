package Services.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

import Controller.NotificationController;
import NotifySystem.Notification;
import NotifySystem.notificationStatus;
import UserSystem.User;
import Utils.Session;
import dao.NotificationDao;

public class NotifyService {
	

    private static NotifyService instance;

    private List<Observer> observers = new ArrayList<>();

    private NotifyService() {}

    public static NotifyService getInstance() {
        if (instance == null) {
            instance = new NotifyService();
        }
        return instance;
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }
    
    public void subscribeAllUsers(List<User> users) {

        for (User u : users) {
            observers.add(u);
        }
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllUsers(Notification notification) {

        for (Observer o : observers) {
            o.update(notification);
        }
    }

    public void send(Notification notification) {
        notifyAllUsers(notification);
    }
    
    // gerekli metotlar 
    
	public  boolean save(Notification notify) {
		return NotificationDao.save(notify);
	}
	
	public  boolean Delete(Notification notify) {
		return NotificationDao.delete(notify.getId());
	}
	
	public  boolean Update(Notification notify) {
		return NotificationDao.update(notify);
	}
	
	public  boolean SendNotificationToUsers(Notification notify,List<User> users) {
		try {
			
		for(User user : users) {
			if(user.getId() != Session.user.getId()) {
				
			Notification yeni_bildirim = new Notification();
			yeni_bildirim.setMesaj(notify.getMesaj());
			yeni_bildirim.setNotificationStatus(notificationStatus.OKUNMADI);
			yeni_bildirim.setReceiever(user);
			yeni_bildirim.setSender(notify.getSender());
			yeni_bildirim.setTarih(notify.getTarih());
			notify.setReceiever(user);
			NotificationDao.save(yeni_bildirim);
			}
		}	
		
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<Notification> getNotificationsByUserId(long id){
		return NotificationDao.findByUser(id);
	}
	
	public List<Notification> getUnreadMessagesByUserId(long id){
		return NotificationDao.findUnreadNotifyByUser(id);
	}
	
}