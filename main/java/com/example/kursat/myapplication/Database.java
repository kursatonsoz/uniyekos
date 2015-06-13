package com.example.kursat.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by kursat on 12/20/14.
 */
public class Database extends SQLiteOpenHelper {

    private Context context;
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "ogrenci.dbf";

    public Database(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CRATE_OGR_TABLE = "CREATE TABLE ogr (" +
                "id integer primary key," +
                "adsoyad text," +
                "sifre text," +
                "email text," +
                "bolum text," +
                "device_id text);";

        db.execSQL(CRATE_OGR_TABLE);
        String CRATE_DERS_TABLE = "CREATE TABLE ders (" +
                "id integer primary key," +
                "adi text," +
                "tip text," +
                "alan text" +
                ");";
        db.execSQL(CRATE_DERS_TABLE);
        String CRATE_KONU_TABLE = "CREATE TABLE konu(" +
                "id integer primary key," +
                "adi text," +
                "ders text," +
                "sinav text" +
                ");";
        db.execSQL(CRATE_KONU_TABLE);

        String CRATE_KAYIT_TABLE = "CREATE TABLE kayit(" +
                "id integer primary key," +
                "tarih text," +
                "calsur text," +
                "soru text," +
                "cozme text," +
                "dogru text," +
                "yanlis text," +
                "bos text," +
                "email text," +
                "konu text," +
                "ders text," +
                "tip text" +
                ");";
        db.execSQL(CRATE_KAYIT_TABLE);

        String CREATE_PROG_TABLE = "CREATE TABLE prog(" +
                "id integer primary key," +
                "email text," +
                "ders text," +
                "gun text," +
                "basla text," +
                "bitir text," +
                "notlar text);";
        db.execSQL(CREATE_PROG_TABLE);

        String add_ders = "INSERT INTO ders VALUES(1,'Biyoloji','YGS',NULL);" +
                "INSERT INTO ders VALUES(2,'Coğrafya','YGS',NULL);" +
                "INSERT INTO ders VALUES(3,'Din Kültürü','YGS',NULL);" +
                "INSERT INTO ders VALUES(4,'Felsefe','YGS',NULL);" +
                "INSERT INTO ders VALUES(5,'Fizik','YGS',NULL);" +
                "INSERT INTO ders VALUES(6,'Geometri','YGS',NULL);" +
                "INSERT INTO ders VALUES(7,'Kimya','YGS',NULL);" +
                "INSERT INTO ders VALUES(8,'Matematik','YGS',NULL);" +
                "INSERT INTO ders VALUES(9,'Tarih','YGS',NULL);" +
                "INSERT INTO ders VALUES(10,'Türkçe','YGS',NULL);" +
                "INSERT INTO ders VALUES(11,'Matematik','LYS','Sayısal');" +
                "INSERT INTO ders VALUES(12,'Matematik','LYS','Eşit Ağırlık');" +
                "INSERT INTO ders VALUES(13,'Geometri','LYS','Sayısal');" +
                "INSERT INTO ders VALUES(14,'Geometri','LYS','Eşit Ağırlık');" +
                "INSERT INTO ders VALUES(15,'Kimya','LYS','Sayısal');" +
                "INSERT INTO ders VALUES(16,'Fizik','LYS','Sayısal');" +
                "INSERT INTO ders VALUES(17,'Biyoloji','LYS','Sayısal');" +
                "INSERT INTO ders VALUES(18,'Edebiyat','LYS','Sözel');" +
                "INSERT INTO ders VALUES(19,'Edebiyat','LYS','Eşit Ağırlık');" +
                "INSERT INTO ders VALUES(20,'Coğrafya','LYS','Sözel');" +
                "INSERT INTO ders VALUES(21,'Coğrafya','LYS','Eşit Ağırlık');" +
                "INSERT INTO ders VALUES(22,'Tarih','LYS','Sözel');" +
                "INSERT INTO ders VALUES(23,'Felsefe','LYS','Sözel');" +
                "INSERT INTO ders VALUES(24,'Psikoloji','LYS','Sözel');" +
                "INSERT INTO ders VALUES(25,'Sosyoloji','LYS','Sözel');" +
                "INSERT INTO ders VALUES(26,'Mantık','LYS','Sözel');" +
                "INSERT INTO ders VALUES(27,'İngilizce','LYS','Yabancı Dil');" +
                "INSERT INTO ders VALUES(28,'Fransızca','LYS','Yabancı Dil');" +
                "INSERT INTO ders VALUES(29,'Almanca','LYS','Yabancı Dil');";
        //db.execSQL(add_ders);

        String[] queries = add_ders.split(";");
        for (String query : queries) {
            db.execSQL(query);
        }

        String add_konu = "INSERT INTO konu VALUES(1,'Biyoloji Bilimi-Bilimsel Çalışma','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(7,'Canlıların Ortak Özellikleri','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(8,'Canlıların Temel Bileşenleri','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(9,'Hücrenin Yapısı','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(10,'Hücre Bölünmeleri','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(11,'Nükleik Asitler','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(12,'Metabolizma','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(13,'Bakteri','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(14,'Canlıların Sınıflandırılması','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(15,'Ekoloji','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(16,'Duyu Organları','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(17,'Mitoz-Mayoz Hücre Bölünmeleri','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(18,'Kalıtım-Evrim','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(19,'Bitki Fizyolaojisi','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(20,'Vücudumuzdaki Sistemler','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(21,'Endokrin Sistem','Biyoloji','YGS');" +
                "INSERT INTO konu VALUES(22,'Harita Bilgisi','Coğrafya','YGS');" +
                "INSERT INTO konu VALUES(23,'Rüzgarlar','Coğrafya','YGS');" +
                "INSERT INTO konu VALUES(24,'Türkiye’nin İklimi','Coğrafya','YGS');" +
                "INSERT INTO konu VALUES(25,'İç Kuvvetler','Coğrafya','YGS');" +
                "INSERT INTO konu VALUES(26,'Toprak Tipleri','Coğrafya','YGS');" +
                "INSERT INTO konu VALUES(27,'Dış Kuvvetler','Coğrafya','YGS');" +
                "INSERT INTO konu VALUES(28,'Doğal Afetler','Coğrafya','YGS');" +
                "INSERT INTO konu VALUES(29,'Nüfus','Coğrafya','YGS');" +
                "INSERT INTO konu VALUES(30,'Ortak payda: Bölge','Coğrafya','YGS');" +
                "INSERT INTO konu VALUES(31,'Ulaşım Yolları','Coğrafya','YGS');" +
                "INSERT INTO konu VALUES(32,'Çevre ve İnsan','Coğrafya','YGS');" +
                "INSERT INTO konu VALUES(33,'Coğrafya’ nın Bölümleri','Coğrafya','YGS');" +
                "INSERT INTO konu VALUES(34,'Kur’an-ı Kerim''in Anlaşılması ve Kavranması','Din Kültürü','YGS');" +
                "INSERT INTO konu VALUES(35,'İncil ve Kur’an-ı Kerim ayetlerinin Ortak Noktaları','Din Kültürü','YGS');" +
                "INSERT INTO konu VALUES(36,'Alevilik ve Bektaşilik','Din Kültürü','YGS');" +
                "INSERT INTO konu VALUES(37,'Felsefenin Alanı','Felsefe','YGS');" +
                "INSERT INTO konu VALUES(38,'Bilgi Felsefesi','Felsefe','YGS');" +
                "INSERT INTO konu VALUES(39,'Bilim Felsefesi','Felsefe','YGS');" +
                "INSERT INTO konu VALUES(40,'Varlık Felsefesi','Felsefe','YGS');" +
                "INSERT INTO konu VALUES(41,'Ahlak Felsefesi','Felsefe','YGS');" +
                "INSERT INTO konu VALUES(42,'Siyaset Felsefesi','Felsefe','YGS');" +
                "INSERT INTO konu VALUES(43,'Sanat Felsefesi','Felsefe','YGS');" +
                "INSERT INTO konu VALUES(44,'Din Felsefesi','Felsefe','YGS');" +
                "INSERT INTO konu VALUES(45,'Fiziğin Doğası','Fizik','YGS');" +
                "INSERT INTO konu VALUES(46,'Basınç','Fizik','YGS');" +
                "INSERT INTO konu VALUES(47,'Isı ve Sıcaklık','Fizik','YGS');" +
                "INSERT INTO konu VALUES(48,'Doğrusal Hareket','Fizik','YGS');" +
                "INSERT INTO konu VALUES(49,'Dinamik','Fizik','YGS');" +
                "INSERT INTO konu VALUES(50,'İş ve Enerji','Fizik','YGS');" +
                "INSERT INTO konu VALUES(51,'Elektrik','Fizik','YGS');" +
                "INSERT INTO konu VALUES(52,'Mıknatıslık','Fizik','YGS');" +
                "INSERT INTO konu VALUES(53,'Optik','Fizik','YGS');" +
                "INSERT INTO konu VALUES(54,'Ses','Fizik','YGS');" +
                "INSERT INTO konu VALUES(55,'Sıvıların kaldırma ilkesi','Fizik','YGS');" +
                "INSERT INTO konu VALUES(56,'Temel Kavramlar-Doğruda Açı','Geometri','YGS');" +
                "INSERT INTO konu VALUES(57,'Üçgende Açı','Geometri','YGS');" +
                "INSERT INTO konu VALUES(58,'Üçgenler','Geometri','YGS');" +
                "INSERT INTO konu VALUES(59,'Yamuk','Geometri','YGS');" +
                "INSERT INTO konu VALUES(60,'Kare','Geometri','YGS');" +
                "INSERT INTO konu VALUES(61,'Dikdörtgen','Geometri','YGS');" +
                "INSERT INTO konu VALUES(62,'Üçgende Açı-Kenar Bağıntıları','Geometri','YGS');" +
                "INSERT INTO konu VALUES(63,'Çokgenler','Geometri','YGS');" +
                "INSERT INTO konu VALUES(64,'Çember-Daire','Geometri','YGS');" +
                "INSERT INTO konu VALUES(65,'Katı Cisimler','Geometri','YGS');" +
                "INSERT INTO konu VALUES(66,'Doğrunun Analitiği','Geometri','YGS');" +
                "INSERT INTO konu VALUES(67,'Analitik Geometri','Geometri','YGS');" +
                "INSERT INTO konu VALUES(68,'Simetri-Döndürme','Geometri','YGS');" +
                "INSERT INTO konu VALUES(69,'Bağlar','Kimya','YGS');" +
                "INSERT INTO konu VALUES(70,'Bileşikler','Kimya','YGS');" +
                "INSERT INTO konu VALUES(71,'Kimyasal Değişimler','Kimya','YGS');" +
                "INSERT INTO konu VALUES(72,'Çözünürlük','Kimya','YGS');" +
                "INSERT INTO konu VALUES(73,'Hayatımızdaki Kimya','Kimya','YGS');" +
                "INSERT INTO konu VALUES(74,'Madde ve Özellikleri','Kimya','YGS');" +
                "INSERT INTO konu VALUES(75,'Atomun Yapısı','Kimya','YGS');" +
                "INSERT INTO konu VALUES(76,'Periyodik Cetvel','Kimya','YGS');" +
                "INSERT INTO konu VALUES(77,'Maddelerin Ayrıştırılması','Kimya','YGS');" +
                "INSERT INTO konu VALUES(80,'Sayılar','Matematik','YGS');" +
                "INSERT INTO konu VALUES(81,'Basamak Kavramı','Matematik','YGS');" +
                "INSERT INTO konu VALUES(82,'Taban Aritmetiği','Matematik','YGS');" +
                "INSERT INTO konu VALUES(83,'Bölme-Bölünebilme','Matematik','YGS');" +
                "INSERT INTO konu VALUES(84,'OBEB-OKEK','Matematik','YGS');" +
                "INSERT INTO konu VALUES(85,'Rasyonel Sayılar','Matematik','YGS');" +
                "INSERT INTO konu VALUES(86,'Sıralama-Basit Eşitsizlikler','Matematik','YGS');" +
                "INSERT INTO konu VALUES(87,'Mutlak Değer','Matematik','YGS');" +
                "INSERT INTO konu VALUES(88,'Üslü İfadeler','Matematik','YGS');" +
                "INSERT INTO konu VALUES(89,'Köklü İfadeler','Matematik','YGS');" +
                "INSERT INTO konu VALUES(90,'Oran-Orantı','Matematik','YGS');" +
                "INSERT INTO konu VALUES(91,'Denklem Çözme','Matematik','YGS');" +
                "INSERT INTO konu VALUES(92,'Problemler','Matematik','YGS');" +
                "INSERT INTO konu VALUES(93,'Mantık','Matematik','YGS');" +
                "INSERT INTO konu VALUES(94,'Kümeler','Matematik','YGS');" +
                "INSERT INTO konu VALUES(95,'Bağıntı-Fonksiyon','Matematik','YGS');" +
                "INSERT INTO konu VALUES(96,'İşlem-Modüler Aritmetik','Matematik','YGS');" +
                "INSERT INTO konu VALUES(97,'Permütasyon-Kombinasyon-Olasılık','Matematik','YGS');" +
                "INSERT INTO konu VALUES(98,'İlk Çağ Medeniyetleri','Tarih','YGS');" +
                "INSERT INTO konu VALUES(99,'İslam Öncesi Türk Tarihi','Tarih','YGS');" +
                "INSERT INTO konu VALUES(100,'İslam Tarihi ve Uygarlığı','Tarih','YGS');" +
                "INSERT INTO konu VALUES(101,'Türkiye Selçuklu Devleti','Tarih','YGS');" +
                "INSERT INTO konu VALUES(102,'Beylikten Devlete Osmanlı (1300-1453)','Tarih','YGS');" +
                "INSERT INTO konu VALUES(103,'En Uzun Yüzyıl (1800 - 1922)','Tarih','YGS');" +
                "INSERT INTO konu VALUES(104,'20. yüzyılda Osmanlı Devleti','Tarih','YGS');" +
                "INSERT INTO konu VALUES(105,'Yakınçağ''da Avrupa','Tarih','YGS');" +
                "INSERT INTO konu VALUES(106,'Kurtuluş Savaşına hazırlık dönemi','Tarih','YGS');" +
                "INSERT INTO konu VALUES(107,'Kurtuluş Savaşı Muharebeleri','Tarih','YGS');" +
                "INSERT INTO konu VALUES(108,'İnkilaplar','Tarih','YGS');" +
                "INSERT INTO konu VALUES(109,'Mondros Ateşkes Antlaşması ve Sonrasındaki Gelişmeler','Tarih','YGS');" +
                "INSERT INTO konu VALUES(110,'Mustafa Kemal’in Çalışmaları ve Amasya Genelgesi','Tarih','YGS');" +
                "INSERT INTO konu VALUES(111,'Erzurum ve Sivas Kongreleri','Tarih','YGS');" +
                "INSERT INTO konu VALUES(112,'Amasya Görüşmesi, Son Osmanlı Mebusan Meclisi’nin Toplanması','Tarih','YGS');" +
                "INSERT INTO konu VALUES(113,'Misak – ı Milli ve İstanbul’un İşgali','Tarih','YGS');" +
                "INSERT INTO konu VALUES(114,'I. Dönem TBMM, TBMM’ye Karşı Ayaklanmalar','Tarih','YGS');" +
                "INSERT INTO konu VALUES(115,'Sevr Antlaşması','Tarih','YGS');" +
                "INSERT INTO konu VALUES(116,'Lozan Antlaşması','Tarih','YGS');" +
                "INSERT INTO konu VALUES(117,'Cumhuriyetin İlanı, Halifeliğin Kaldırılması','Tarih','YGS');" +
                "INSERT INTO konu VALUES(118,'Jeopolitik Konum','Tarih','YGS');" +
                "INSERT INTO konu VALUES(119,'Atatürk Devrimleri','Tarih','YGS');" +
                "INSERT INTO konu VALUES(120,'Türk Dış Politikası','Tarih','YGS');" +
                "INSERT INTO konu VALUES(121,'Paragraf (Yardımcı Düşünce)','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(122,'Paragraf (Ana Düşünce)','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(123,'Dil Bilgisi - Karma Dil Bilgisi','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(124,'Paragrafta Yapı','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(125,'Paragraf (Anlatım Teknikleri)','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(126,'Cümle Anlamı (Kavramlar)','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(127,'Sözcük Anlamı','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(128,'Cümle Yorumu','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(129,'Cümle Anlamı (Karma)','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(130,'Anlatım Bozukluğu','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(131,'Noktalama İşaretleri','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(132,'Ses Bilgisi','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(133,'Yazım Kuralları - Noktalama İşaretleri','Türkçe','YGS');" +
                "INSERT INTO konu VALUES(134,'Polinomlar','Matematik','LYS');" +
                "INSERT INTO konu VALUES(135,'II. Dereceden Denklemler','Matematik','LYS');" +
                "INSERT INTO konu VALUES(136,'II. Dereceden Eşitsizlikler','Matematik','LYS');" +
                "INSERT INTO konu VALUES(137,'Parabol','Matematik','LYS');" +
                "INSERT INTO konu VALUES(138,'Permütasyon, Kombinasyon, Binom ve Olasılık','Matematik','LYS');" +
                "INSERT INTO konu VALUES(139,'Trigonometri','Matematik','LYS');" +
                "INSERT INTO konu VALUES(140,'Logaritma','Matematik','LYS');" +
                "INSERT INTO konu VALUES(141,'Karmaşık Sayılar','Matematik','LYS');" +
                "INSERT INTO konu VALUES(142,'Tümevarım (Toplam ve Çarpım Sembolleri)','Matematik','LYS');" +
                "INSERT INTO konu VALUES(143,'Diziler ve Seriler','Matematik','LYS');" +
                "INSERT INTO konu VALUES(144,'Özel Tanımlı Fonksiyonlar','Matematik','LYS');" +
                "INSERT INTO konu VALUES(145,'Limit ve Süreklilik','Matematik','LYS');" +
                "INSERT INTO konu VALUES(146,'Türev','Matematik','LYS');" +
                "INSERT INTO konu VALUES(147,'İntegral','Matematik','LYS');" +
                "INSERT INTO konu VALUES(148,'Lineer Cebir (Matris ve Determinant)','Matematik','LYS');" +
                "INSERT INTO konu VALUES(149,'','Geometri','LYS');" +
                "INSERT INTO konu VALUES(150,'','Geometri','LYS');" +
                "INSERT INTO konu VALUES(151,'Doğruda Açılar','Geometri','LYS');" +
                "INSERT INTO konu VALUES(152,'Üçgende Açılar','Geometri','LYS');" +
                "INSERT INTO konu VALUES(153,'Açı Kenar Bağıntıları','Geometri','LYS');" +
                "INSERT INTO konu VALUES(154,'Dik Üçgen','Geometri','LYS');" +
                "INSERT INTO konu VALUES(155,'Açı ortay','Geometri','LYS');" +
                "INSERT INTO konu VALUES(156,'Kenarortay','Geometri','LYS');" +
                "INSERT INTO konu VALUES(157,'Açı ortay - Kenarortay','Geometri','LYS');" +
                "INSERT INTO konu VALUES(158,'Özel Üçgenler','Geometri','LYS');" +
                "INSERT INTO konu VALUES(159,'Üçgende Alan','Geometri','LYS');" +
                "INSERT INTO konu VALUES(160,'Üçgende Benzerlik','Geometri','LYS');" +
                "INSERT INTO konu VALUES(161,'Benzerlik Alan','Geometri','LYS');" +
                "INSERT INTO konu VALUES(162,'Çokgenler - Dörtgenler','Geometri','LYS');" +
                "INSERT INTO konu VALUES(163,'Paralelkenar - Eşkenar - Dörtgen','Geometri','LYS');" +
                "INSERT INTO konu VALUES(164,'Dikdörtgen - Kare','Geometri','LYS');" +
                "INSERT INTO konu VALUES(165,'Yamuk - Deltoid','Geometri','LYS');" +
                "INSERT INTO konu VALUES(166,'Çemberde Açı','Geometri','LYS');" +
                "INSERT INTO konu VALUES(167,'Çemberde Uzunluk','Geometri','LYS');" +
                "INSERT INTO konu VALUES(168,'Dairede Alan','Geometri','LYS');" +
                "INSERT INTO konu VALUES(169,'Doğrunun Analitik İncelenmesi','Geometri','LYS');" +
                "INSERT INTO konu VALUES(170,'Uzay Geometri','Geometri','LYS');" +
                "INSERT INTO konu VALUES(171,'Prizmalar','Geometri','LYS');" +
                "INSERT INTO konu VALUES(172,'Piramitler','Geometri','LYS');" +
                "INSERT INTO konu VALUES(173,'Çemberin Analitik İncelenmesi','Geometri','LYS');" +
                "INSERT INTO konu VALUES(174,'Vektörler','Geometri','LYS');" +
                "INSERT INTO konu VALUES(175,'Koniklerin Analitik incelenmesi','Geometri','LYS');" +
                "INSERT INTO konu VALUES(176,'Uzayda Doğru ve Düzlem Denklemleri','Geometri','LYS');" +
                "INSERT INTO konu VALUES(180,'Fiziğin Doğası','Fizik','LYS');" +
                "INSERT INTO konu VALUES(181,'Birimler','Fizik','LYS');" +
                "INSERT INTO konu VALUES(182,'Vektör ve Kuvvet','Fizik','LYS');" +
                "INSERT INTO konu VALUES(183,'Moment -Denge','Fizik','LYS');" +
                "INSERT INTO konu VALUES(184,'Ağırlık Merkezi','Fizik','LYS');" +
                "INSERT INTO konu VALUES(185,'Basit Makinalar','Fizik','LYS');" +
                "INSERT INTO konu VALUES(186,'Doğrusal Hareket','Fizik','LYS');" +
                "INSERT INTO konu VALUES(187,'Dinamik','Fizik','LYS');" +
                "INSERT INTO konu VALUES(188,'Enerji','Fizik','LYS');" +
                "INSERT INTO konu VALUES(189,'Yeryüzünde Hareket','Fizik','LYS');" +
                "INSERT INTO konu VALUES(190,'Düzgün Dairesel Hareket','Fizik','LYS');" +
                "INSERT INTO konu VALUES(191,'Basit Harmonik Hareket','Fizik','LYS');" +
                "INSERT INTO konu VALUES(192,'Keppler Yasaları','Fizik','LYS');" +
                "INSERT INTO konu VALUES(193,'İtme -Momentum','Fizik','LYS');" +
                "INSERT INTO konu VALUES(194,'Madde ve Özellikleri','Fizik','LYS');" +
                "INSERT INTO konu VALUES(195,'Basınç','Fizik','LYS');" +
                "INSERT INTO konu VALUES(196,'Sıvıların Kaldırma Kuvveti','Fizik','LYS');" +
                "INSERT INTO konu VALUES(197,'Isı-Sıcaklık-Genleşme','Fizik','LYS');" +
                "INSERT INTO konu VALUES(198,'Gölge','Fizik','LYS');" +
                "INSERT INTO konu VALUES(199,'Düzlem Ayna','Fizik','LYS');" +
                "INSERT INTO konu VALUES(200,'Küresel Aynalar','Fizik','LYS');" +
                "INSERT INTO konu VALUES(201,'Renk','Fizik','LYS');" +
                "INSERT INTO konu VALUES(202,'Kırılma','Fizik','LYS');" +
                "INSERT INTO konu VALUES(203,'Mercekler','Fizik','LYS');" +
                "INSERT INTO konu VALUES(204,'Aydınlanma','Fizik','LYS');" +
                "INSERT INTO konu VALUES(205,'Yay Dalgaları','Fizik','LYS');" +
                "INSERT INTO konu VALUES(206,'Su Dalgaları','Fizik','LYS');" +
                "INSERT INTO konu VALUES(207,'Su Dalgalarında Girişim','Fizik','LYS');" +
                "INSERT INTO konu VALUES(208,'Ses Dalgaları','Fizik','LYS');" +
                "INSERT INTO konu VALUES(209,'Işık Teorileri','Fizik','LYS');" +
                "INSERT INTO konu VALUES(210,'Işığın Tanecik Modeli','Fizik','LYS');" +
                "INSERT INTO konu VALUES(211,'Elektrik Alan-Potansiyel fark','Fizik','LYS');" +
                "INSERT INTO konu VALUES(212,'Paralel Yüklü Levhalar','Fizik','LYS');" +
                "INSERT INTO konu VALUES(213,'Sığa-Kondansatör','Fizik','LYS');" +
                "INSERT INTO konu VALUES(214,'Emk-Zıt Emk','Fizik','LYS');" +
                "INSERT INTO konu VALUES(215,'Elektromagnetizma','Fizik','LYS');" +
                "INSERT INTO konu VALUES(216,'Elektromagnetik İndüksiyon','Fizik','LYS');" +
                "INSERT INTO konu VALUES(217,'Alternatif Akım','Fizik','LYS');" +
                "INSERT INTO konu VALUES(218,'Transformatör','Fizik','LYS');" +
                "INSERT INTO konu VALUES(219,'Modern Fizik','Fizik','LYS');" +
                "INSERT INTO konu VALUES(220,'Astronomi','Fizik','LYS');" +
                "INSERT INTO konu VALUES(221,'Elektromagnetik Dalgalar','Fizik','LYS');" +
                "INSERT INTO konu VALUES(222,'Atom Fiziği','Fizik','LYS');" +
                "INSERT INTO konu VALUES(223,'Nükleer Fizik','Fizik','LYS');" +
                "INSERT INTO konu VALUES(233,'Atomun Yapısı','Kimya','LYS');" +
                "INSERT INTO konu VALUES(234,'Mol Kavramı','Kimya','LYS');" +
                "INSERT INTO konu VALUES(235,'Periyodik Sistem','Kimya','LYS');" +
                "INSERT INTO konu VALUES(236,'Elementler Kimyası','Kimya','LYS');" +
                "INSERT INTO konu VALUES(237,'Oksitler','Kimya','LYS');" +
                "INSERT INTO konu VALUES(238,'Kimyasal Türler Arası Etkileşimler','Kimya','LYS');" +
                "INSERT INTO konu VALUES(239,'Amorf ve Kristal Katılar-Sıvıların Özellikleri','Kimya','LYS');" +
                "INSERT INTO konu VALUES(240,'Gazlar','Kimya','LYS');" +
                "INSERT INTO konu VALUES(241,'Hal Değişimleri ve Isı – Sıcaklık','Kimya','LYS');" +
                "INSERT INTO konu VALUES(242,'Çözeltiler','Kimya','LYS');" +
                "INSERT INTO konu VALUES(243,'Kimyasal Tepkimelerde Enerji','Kimya','LYS');" +
                "INSERT INTO konu VALUES(244,'Reaksiyon Hızı','Kimya','LYS');" +
                "INSERT INTO konu VALUES(245,'Kimyasal Denge','Kimya','LYS');" +
                "INSERT INTO konu VALUES(246,'Sulu Çözeltilerde Denge','Kimya','LYS');" +
                "INSERT INTO konu VALUES(247,'Asitler ve Bazlar','Kimya','LYS');" +
                "INSERT INTO konu VALUES(248,'Redoks Reaksiyonları ve İstemlilik','Kimya','LYS');" +
                "INSERT INTO konu VALUES(249,'Elektro Kimyasal piller','Kimya','LYS');" +
                "INSERT INTO konu VALUES(250,'Elektroliz','Kimya','LYS');" +
                "INSERT INTO konu VALUES(251,'Organik Kimyaya Giriş','Kimya','LYS');" +
                "INSERT INTO konu VALUES(252,'Alkanlar','Kimya','LYS');" +
                "INSERT INTO konu VALUES(253,'Alkenler','Kimya','LYS');" +
                "INSERT INTO konu VALUES(254,'Alkinler','Kimya','LYS');" +
                "INSERT INTO konu VALUES(255,'Alkoller ve Eterler','Kimya','LYS');" +
                "INSERT INTO konu VALUES(256,'Aldehitler ve Ketonlar','Kimya','LYS');" +
                "INSERT INTO konu VALUES(257,'Karboksilli asitler ve Esterler','Kimya','LYS');" +
                "INSERT INTO konu VALUES(258,'Alifatik Amonyak Türevleri','Kimya','LYS');" +
                "INSERT INTO konu VALUES(259,'Aromatik Bileşikler','Kimya','LYS');" +
                "INSERT INTO konu VALUES(260,'Organik Reaksiyonlar','Kimya','LYS');" +
                "INSERT INTO konu VALUES(261,'Çekirdek Kimyası','Kimya','LYS');" +
                "INSERT INTO konu VALUES(266,'Hücresel Solunum','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(267,'Fotosentez','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(268,'Hücre Bölünmesi ve Üreme','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(269,'Bitkilerde Üreme ve Gelişme','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(270,'Hayvanlarda Üreme ve Geliflme','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(271,'Protein Sentezi','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(272,'Kalıtım ve Biyoteknoloji','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(273,'Evrim ve Davranış','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(274,'Bitkisel Dokular ve Bitki Organları','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(275,'Bitkilerde Taşıma','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(276,'Bitkilerde Beslenme','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(277,'Bitkilerde Büyüme','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(278,'Hayvansal Dokular','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(279,'Sinir Sistemi ve Duyu Organları','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(280,'Endokrin Sistem','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(281,'Destek ve Hareket Sistemi','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(282,'Sindirim Sistemi','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(283,'Dolaşım Sistemi','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(284,'Solunum Sistemi','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(285,'Boşaltım Sistemi','Biyoloji','LYS');" +
                "INSERT INTO konu VALUES(299,'İslamiyet Öncesi Türk Edebiyatı','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(300,'Geçiş Dönemi Türk Edebiyatı','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(301,'Halk Edebiyatı','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(302,'Divan Edebiyatı','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(303,'Tanzimat Edebiyatı','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(304,'Servet-i Fünun Edebiyatı','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(305,'Fecr-i Ati Edebiyatı','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(306,'Milli Edebiyat Dönemi','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(307,'Cumhuriyet Edebiyatı','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(308,'Batı Edebiyatı','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(309,'Edebî Akımlar','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(310,'Edebî Sanatlar','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(311,'Şiir Bilgisi','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(312,'Nesir Bilgisi','Edebiyat','LYS');" +
                "INSERT INTO konu VALUES(313,'Doğal Sistemler','Coğrafya','LYS');" +
                "INSERT INTO konu VALUES(314,'Beşeri Sistemler','Coğrafya','LYS');" +
                "INSERT INTO konu VALUES(315,'Ekonomik Faliyetler','Coğrafya','LYS');" +
                "INSERT INTO konu VALUES(316,'Mekansal Bir Sentez: Türkiye','Coğrafya','LYS');" +
                "INSERT INTO konu VALUES(317,'Çevre ve Toplum','Coğrafya','LYS');" +
                "INSERT INTO konu VALUES(318,'Küresel Ortam: Bölgeler Ülkeler','Coğrafya','LYS');" +
                "INSERT INTO konu VALUES(319,'Psikolojinin Alanı','Psikoloji','LYS');" +
                "INSERT INTO konu VALUES(320,'Organizma ve Çevre','Psikoloji','LYS');" +
                "INSERT INTO konu VALUES(321,'Öğrenme','Psikoloji','LYS');" +
                "INSERT INTO konu VALUES(322,'Bellek, Düşünme ve Bilinç','Psikoloji','LYS');" +
                "INSERT INTO konu VALUES(323,'Ruh Sağlığı','Psikoloji','LYS');" +
                "INSERT INTO konu VALUES(324,'Zeka ve Kişilik','Psikoloji','LYS');" +
                "INSERT INTO konu VALUES(325,'Sosyal Davranış','Psikoloji','LYS');" +
                "INSERT INTO konu VALUES(328,'Mantığın Alanı','Mantık','LYS');" +
                "INSERT INTO konu VALUES(329,'Klasik Mantık 1','Mantık','LYS');" +
                "INSERT INTO konu VALUES(330,'Klasik Mantık 2','Mantık','LYS');" +
                "INSERT INTO konu VALUES(331,'Klasik Mantık 3','Mantık','LYS');" +
                "INSERT INTO konu VALUES(332,'Sembolik Mantık Önermeler Mantığı','Mantık','LYS');" +
                "INSERT INTO konu VALUES(333,'Denetlemeler','Mantık','LYS');" +
                "INSERT INTO konu VALUES(334,'Niceleme Yüklemeler Mantığı','Mantık','LYS');" +
                "INSERT INTO konu VALUES(336,'Sosyolojiye Giriş','Sosyoloji','LYS');" +
                "INSERT INTO konu VALUES(337,'Toplumsal Yapı','Sosyoloji','LYS');" +
                "INSERT INTO konu VALUES(338,'Toplumsal Kavramlar 1','Sosyoloji','LYS');" +
                "INSERT INTO konu VALUES(339,'Toplumsal Kavramlar 2','Sosyoloji','LYS');" +
                "INSERT INTO konu VALUES(340,'Kültür','Sosyoloji','LYS');" +
                "INSERT INTO konu VALUES(341,'Toplumsal Değişme','Sosyoloji','LYS');" +
                "INSERT INTO konu VALUES(348,'Cümle Tamamlama','İngilizce','LYS');" +
                "INSERT INTO konu VALUES(349,'Dilbilgisi','İngilizce','LYS');" +
                "INSERT INTO konu VALUES(350,'Sözcük Bilgisi','İngilizce','LYS');" +
                "INSERT INTO konu VALUES(351,'Cloze Test','İngilizce','LYS');" +
                "INSERT INTO konu VALUES(352,'İngilizce Türkçe Çeviri','İngilizce','LYS');" +
                "INSERT INTO konu VALUES(353,'Türkçe İngilizce Çeviri','İngilizce','LYS');" +
                "INSERT INTO konu VALUES(354,'Yanıta Uygun Soru Bulma','İngilizce','LYS');" +
                "INSERT INTO konu VALUES(355,'Anlamca Yakın','İngilizce','LYS');" +
                "INSERT INTO konu VALUES(356,'Okuma Parçası','İngilizce','LYS');" +
                "INSERT INTO konu VALUES(357,'Durum','İngilizce','LYS');" +
                "INSERT INTO konu VALUES(358,'Parça Tamamlama','İngilizce','LYS');" +
                "INSERT INTO konu VALUES(359,'Diyalog','İngilizce','LYS');" +
                "INSERT INTO konu VALUES(360,'Anlam Bozan Cümle','İngilizce','LYS');";

        queries = add_konu.split(";");
        for (String query : queries) {
            db.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST ogr");
        db.execSQL("DROP TABLE IF EXIST ders");
        db.execSQL("DROP TABLE IF EXIST kayit");
        db.execSQL("DROP TABLE IF EXIST konu");
        this.onCreate(db);
    }


    /*
    * CRUD Operations
    * */
    public void ogrEkle(Ogrenci o) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("adsoyad", o.get_ogrAdi());
        cv.put("sifre", o.get_sifre());
        cv.put("email", o.get_email());
        cv.put("bolum", o.get_department());
        cv.put("device_id", o.get_deviceiID());

        db.insert("ogr", null, cv);

        db.close();
    }

    public Cursor ogrAl(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM ogr WHERE email='" + email + "'";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor cidAL(String cihazid) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM ogr WHERE device_id='" + cihazid + "'";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }


    public ArrayList<String> dersAl(String sql) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        ArrayList<String> ar = new ArrayList<>();

        c.moveToFirst();

        do {
            ar.add(c.getString(c.getColumnIndex("adi")));
        }
        while (c.moveToNext());

        return ar;
    }

    public void kayitEkle(Kaydet k) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("tarih", k.getTarih());
        cv.put("calsur", k.getCalsur());
        cv.put("soru", k.getSoru());
        cv.put("cozme", k.getCozme());
        cv.put("dogru", k.getDogru());
        cv.put("yanlis", k.getYanlis());
        cv.put("bos", k.getBos());
        cv.put("email", k.getEmail());
        cv.put("konu", k.getKonu());
        cv.put("ders", k.getDers());
        cv.put("tip", k.getTip());
        db.insert("kayit", null, cv);

    }

    public void programEkle(Prog p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", p.getEmail());
        cv.put("ders", p.getDers());
        cv.put("gun", p.getGun());
        cv.put("basla", p.getBasla());
        cv.put("bitir", p.getBitir());
        cv.put("notlar", p.getNotlar());
        db.insert("prog", null, cv);
    }

    public Cursor programCek(String gun, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM prog WHERE gun='" + gun + "' and email='" + email + "'";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor dersler(String ders, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM kayit WHERE ders='" + ders + "' and email='" + email + "'";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor konular(String konu, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM kayit WHERE konu='" + konu + "' and email='" + email + "'";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor kayitAl(Kaydet k) {
            SQLiteDatabase db = this.getReadableDatabase();
            if (k.getKonu().equals("Hepsi")) {
                    String sql = "SELECT * FROM kayit WHERE email='" + k.getEmail() + "' AND tarih='" + k.getTarih() + "' AND tip='" + k.getTip() + "' AND ders='" + k.getDers() + "'";
                    Log.e("ezguelim->", sql);

                    Cursor c = db.rawQuery(sql, null);
                    return c;
            } else {
                    String sql = "SELECT * FROM kayit WHERE email='" + k.getEmail() + "' AND tarih='" + k.getTarih() + "' AND tip='" + k.getTip() + "' AND ders='" + k.getDers() + "' AND konu='" + k.getKonu() + "'";
                    Cursor c = db.rawQuery(sql, null);
                    return c;
            }

    }

        public Cursor Alayi(){
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM prog", null);
                return c;
        }

}