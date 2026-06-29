# Akilli-Lojistik-Yonetim-Sistemi
📦 Akıllı Lojistik ve Depo Yönetim Sistemi (Smart ERP)
Kapsamlı tedarik zinciri, depo envanteri, kargo yönetimi ve finansal işlemleri tek bir merkezden yönetmeyi sağlayan, kurumsal mimari standartlarında geliştirilmiş bir sistem simülasyonudur. Sistem, modüler yapısı ve uygulanan Tasarım Desenleri (Design Patterns) sayesinde yüksek genişletilebilirlik sunar.

🚀 Proje Vizyonu ve Mimari Yaklaşım
Bu proje sadece bir CRUD uygulaması değil, gerçek dünya iş kurallarını barındıran dinamik bir yapıdır. Temelinde yatan yazılım mühendisliği prensipleri şunlardır:

Strategy Pattern: Farklı kargo firmalarının (Aras, Yurtiçi vb.) fiyat hesaplama algoritmaları ve çeşitli ödeme yöntemleri (Kripto, Kredi Kartı, Banka Havalesi) çalışma anında (runtime) dinamik olarak Context üzerinden yönetilir.

Factory Pattern: PaymentFactory kullanılarak PaymentType (Kredi Kartı, Banka, Kripto) enum değerlerine göre uygun ödeme stratejisi nesneleri izole bir şekilde üretilir.

Singleton Pattern: Sistemin her yerinden erişilen ve tekil olması gereken LogService, Singleton olarak tasarlanarak kaynak tüketimi optimize edilmiştir.

MVC / Yönlendirme: Arayüzler ve mantık katmanı arasındaki geçişler özel bir Navigator ve ViewController yapısı ile yönetilmektedir.

🛡️ Temel Sistem Modülleri
👥 Gelişmiş Yetkilendirme (RBAC) ve Güvenlik
Dinamik Roller: Admin, Müşteri, Şirket Sahibi, Depo Yöneticisi, Ürün Yöneticisi ve Kargo Yetkilisi.

Aksiyon Bazlı Kısıtlama: Sayfa erişiminden bağımsız olarak eylemler spesifik yetkilere bağlanmıştır (Örn: Sadece Depo Yöneticisi URUN_KABULETME eylemini gerçekleştirebilir).

Seed Data: Sistem ilk çalıştığında eksik roller otomatik olarak veritabanına işlenir.

🏢 Depo, Tedarik ve Ürün Yönetimi
Akıllı Konfigürasyon: Ürünlere eklenebilecek alt parçalar (RAM, İşlemci vb.) kısıtlanarak uyumsuz donanım eşleşmeleri engellenir.

Olay Güdümlü Uyarılar: Depodaki ürün miktarı belirlenen minimum eşik değerinin altına düştüğünde sistem otomatik bildirim üretir.

Tedarikçi Ağları: Depolar ve tedarikçiler arası çoklu ilişkiler (Many-to-Many), anlaşma süreleri ve fiyatlandırma verileriyle birlikte tutulur.

🚚 Lojistik ve Kargo Stratejileri
Dinamik Fiyatlandırma: Kargo firmalarının baz fiyatı, kilogram ücreti ve mesafe çarpanları sisteme entegredir.

Sipariş Yaşam Döngüsü: Hazırlanıyor, Kargoda, Teslim Edildi durumları anlık güncellenir ve kullanıcı tarafından takip edilebilir.

💳 Finans ve Ödeme Sistemi
Çoklu Ödeme Seçenekleri: Strategy ve Factory desenleriyle altyapısı kurulan sistem; Kredi Kartı, Banka Hesabı ve Kripto ödemelerini destekler.

Bakiye Kontrolü: İşlemler kullanıcı hesap bakiyeleri üzerinden güvenle gerçekleştirilir.

📜 Loglama ve İzlenebilirlik
Rol Bazlı Log Görünürlüğü: Sistemdeki hareketler (girişler, ödemeler, siparişler) kaydedilir, ancak her kullanıcı sadece kendi yetki seviyesindeki logları görüntüleyebilir.

Sistem Kayıtları: Merkezi Log ekranı üzerinden ID bazlı filtreleme ve detaylı izleme yapılabilir.

📸 Ekran Görüntüleri
Ana Dashboard ve Modüller
(images/Home.png)

Kullanıcı Profil ve Yetki Yönetimi
(images/UserPanel.png)

Depo Yönetimi, Yeni Tedarikçi ve Stok Kontrolü
(images/StoreManagement.png)

(images/ShipmentManagement.png)

Kullanıcı Sepeti
(images/ProductStore.png)
Modüler Parça Seçimi

(images/ProductPage.png)
Sipariş ve Kargo Takip Paneli

Sistem Logları 
(images/LogRecords.png)

Bildirim Ekranları
(images/Notification.png)

🛠️ Kullanılan Teknolojiler
Programlama Dili: Java

Veritabanı Yönetimi: JPA / Hibernate (ORM), Relational Database

Tasarım Desenleri: Singleton, Strategy, Factory, Observer (Olay Güdümlü Uyarılar için)

Arayüz & Yönlendirme: Navigator & View Controller Mimarisi