package Services;
import NotifySystem.notificationStatus;

import java.sql.Date;

import enums.notificationStatus;

public class Notification {
	int id;
	String Mesaj;
	Date Tarih;
	String Gonderen_Kisi;
	notificationStatus Durum;
	int Alan_Kisi_id;
}
