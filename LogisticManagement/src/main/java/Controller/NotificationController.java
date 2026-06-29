package Controller;

import java.util.List;

import NotifySystem.Notification;
import NotifySystem.notificationStatus;
import Services.BuilderPattern.NotificationBuilder;
import Services.ObserverPattern.NotifyService;
import ShoppingSystem.Cart;
import UserSystem.User;
import Utils.Session;
import dao.CartDao;
import dao.NotificationDao;

public class NotificationController {
	public static boolean save(Notification notify) {
		return NotifyService.getInstance().save(notify);
	}
	
	public static boolean Delete(Notification notify) {
		return NotifyService.getInstance().Delete(notify);
	}
	
	public static boolean Update(Notification notify) {
		return NotifyService.getInstance().Update(notify);
	}
	
	public static boolean SendNotificationToUsers(Notification notify,List<User> users) {
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
	
	public static List<Notification> getNotificationsByUserId(long id){
		return NotifyService.getInstance().getNotificationsByUserId(id);
	}
	
	public static List<Notification> getUnreadMessagesByUserId(long id){
		return NotifyService.getInstance().getUnreadMessagesByUserId(id);
	}
	
    
    // Kullanıcıya bildirim gönder
    public static void sendNotification(String mesaj, String sender, User receiver) {

        Notification notification =
                NotificationBuilder.create()
                        .message(mesaj)
                        .sender(sender)
                        .receiver(receiver)
                        .now()
                        .build();

        NotifyService.getInstance().send(notification);
    }

    // Direkt hazır notification gönder (advanced kullanım)
    public static void sendNotification(Notification notification) {
    	NotifyService.getInstance().send(notification);
    }

    // Sisteme user ekleme (observer subscribe)
    public static void registerUser(User user) {
    	NotifyService.getInstance().subscribe(user);
    }
    
    public static void registerAllUsers(List<User> users) {
    	NotifyService.getInstance().subscribeAllUsers(users);
    }

    // sistemden çıkarma
    public static void unregisterUser(User user) {
    	NotifyService.getInstance().unsubscribe(user);
    }
    
    
}
