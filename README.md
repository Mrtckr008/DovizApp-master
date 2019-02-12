1.1	Intro Ekranları
Kullanıcılar uygulamayı mobil cihazlarına indirdiği andan sonra sadece bir kez çalışan ekranlardır. Ekranların amacı kullanıcılara, uygulamayı tanıtmak, uygulama üstünden yapılabilecek işlemlere yönelik ön bilgi vermektir. Bu ekranlar tasarlanırken tasarımın kullanıcı dostu olması açısından ek kütüphaneler araştırılıp kullanılmıştır.

![image](https://user-images.githubusercontent.com/32914909/52638364-e02c5880-2ee2-11e9-8bc8-927a37f7e925.png)                ![image](https://user-images.githubusercontent.com/32914909/52638386-ede1de00-2ee2-11e9-8193-a80f0209ac8e.png) ![image](https://user-images.githubusercontent.com/32914909/52638442-0baf4300-2ee3-11e9-9032-82f76178751a.png)

                         
       Şekil 1.1.1	         Şekil 1.1.2	       Şekil 1.1.3




 


1.2	Giriş Ekranları
Kullanıcıların uygulamaya girişini, üye olmasını ve şifre sıfırlaması gibi işlemleri gerçekleştirebilmesi için oluşturulan ekranlardır. Bu ekranlar hazırlanırken Firebase authentication özellikleri kullanılıp, bu sayede uygulamaya üye olan her bir kullanıcı için Firebase veritabanına üye kayıtları oluşturulmaktadır. Giriş yapılırken üye veritabanı kullanılıp, eşleşmelere göre uygulamaya giriş gerçekleşmektedir.

     
![image](https://user-images.githubusercontent.com/32914909/52638505-271a4e00-2ee3-11e9-8aae-b75f24d606d3.png)  ![image](https://user-images.githubusercontent.com/32914909/52638518-2c779880-2ee3-11e9-9095-d09656a4d709.png)  ![image](https://user-images.githubusercontent.com/32914909/52638534-37cac400-2ee3-11e9-81f4-2ae1451dd896.png)


                        
        Şekil 1.2.1           Şekil 1.2.2	  Şekil 1.2.3



1.3	Güncel Döviz Kurları Ekranı
Uygulama üstünde bütün döviz değerlerinin güncel alım,satım kurlarını ve değişim oranlarını kullanıcıya sunan ekrandır. Bu ekranda API aracılığı ile çekilen verilerin arka planda işlenip, kullanıcılara sunulması hedeflenmiştir. Döviz kur bilgileri doviz.com sitesinden json dosyaları kullanılarak çekilmiştir. Bu değerler, kullanıcı uygulamada zaman geçirdiği süre boyunca her 3 dakikada bir çekilip kullanıcılara güncel kur değerleri sunulmuştur. Veri çekim işlemleri arka plan görevleri ile gerçekleştirilmiştir.

                                              
![image](https://user-images.githubusercontent.com/32914909/52638578-5204a200-2ee3-11e9-8a62-83de28a530f0.png)
        Şekil 1.3.1

1.4	Güncel Kur Grafikleri Ekranı
Döviz değerlerinin geçmiş zamanlara dayalı grafiklerini kullanıcıya sunarak, kullanıcıların grafik analiz yeteneklerini geliştirmelerini sağlayan ekrandır. Günlük, haftalık, aylık ve yıllık şeklinde zaman dilimlerine bağlı olarak bütün döviz değerlerinin json dosyaları aracılığı ile çekimi ve grafik kütüphanelerinin kullanımı ile bu değerleri grafik üstünde göstermeye dayanır. Veriler doviz.com sitesinden çekilmektedir.
       
![image](https://user-images.githubusercontent.com/32914909/52638610-60eb5480-2ee3-11e9-8b2e-9021e2cb4af3.png) ![image](https://user-images.githubusercontent.com/32914909/52638617-63e64500-2ee3-11e9-9237-350fd90d12c7.png)

                                
        Şekil 1.4.1	  Şekil 1.4.2
1.5	Döviz Çevirici Ekranı
Döviz çevirici, uygulama Türk Lirası üstünden alım, satım işlemleri gerçekleştirdiği için kullanıcıların çapraz döviz kurlarının hesaplamalarını gerçekleştirmesi için oluşturulan araçtır. Kullanıcı istediği döviz türünü seçip, miktar girerek, hangi kur türünden (alım ya da satım) çevrim yapacağını seçtiği anda, diğer bütün döviz değerlerine güncel kur değerleri üstünden çevirip, kullanıcıya sunulmasını sağlar. Bunu yaparken arka planda çalışan parite hesaplama fonksiyonları kullanılmıştır.
            
![image](https://user-images.githubusercontent.com/32914909/52638660-7a8c9c00-2ee3-11e9-855c-f09f79692b43.png)
                                  
       Şekil 1.5.1
 


1.6	Uygulama Hakkında Ekranı
Bu ekranda kullanıcılara uygulama üstünde bulunan araçları nasıl ve hangi amaçla kullanılması gerektiği detaylı olarak tanımlanmış ve anlatılmıştır. Bu sayede döviz ticaretine veya uygulamaya yabancı kullanıcılara giriş düzeyinde bilgiler sunulmuştur.
   
![image](https://user-images.githubusercontent.com/32914909/52638687-8b3d1200-2ee3-11e9-9946-96c2f42508d7.png)
                                               
      Şekil 1.6.1

1.7	Profil Ekranı
Bu ekranda Firebase Servisleri kullanılarak kullanıcının email güncelleme, şifre değiştirme, maile şifre sıfırlama linki yollama gibi işlemlerini gerçekleştirmek amaçlanmıştır. Ayrıca tasarım açısından kolaylık sağlaması ve şık durması açısından animasyon kütüphanelerinden yararlanılmıştır.
             
![image](https://user-images.githubusercontent.com/32914909/52638710-9bed8800-2ee3-11e9-8803-7218f8d3cf9a.png)
                                     
	Şekil 1.7.1

1.8	Hesap Geçmişim Ekranları
Kullanıcıların geçmiş alım ve satım işlemlerini ve işlem detaylarını kullanıcılara sunularak oluşturulan ekrandır. Bu ekran ile kullanıcı geçmişte yaptığı hesap işlemlerini, hesap dökümünü detaylı bir şekilde görebilir ve yorumlayabilir. İşlemler gösterilirken bir çok detay kullanıcıların bilgisine sunulmuştur. Bu işlemi gerçekleştirirken kullanıcıların işlem gerçekleştirdiği sırada veritabanında gerekli verilerin ağaç yapısı altında kayıt altına alınıp, çekilmesi ile oluşturulmuştur. Aynı zamanda bu ekranda tab activity kullanılmış ve bu tab activity içinde 3 farklı fragment tasarlanmıştır. Bu sayede kullanıcı tüm işlemleri, sadece alım veya sadece satım işlemlerini ayrı ayrı analiz edebilmektedir.
       
![image](https://user-images.githubusercontent.com/32914909/52638729-a6a81d00-2ee3-11e9-82f1-aa98db624f8a.png)   ![image](https://user-images.githubusercontent.com/32914909/52638733-aa3ba400-2ee3-11e9-9259-bad1e9884db6.png)  ![image](https://user-images.githubusercontent.com/32914909/52638750-ae67c180-2ee3-11e9-8cea-24f8ba89aac1.png)

       Şekil 1.8.1          Şekil 1.8.2    Şekil 1.8.3

1.9	Varlıklarım Ekranları
Uygulama üstünde kullanıcıların gerçekleştirdiği işlemler sonucu oluşan varlıkları kullanıcılara sunan ekranlardır. Bu ekranlar tasarlanırken bir tab activity ve bu activity’nin içini doldurmak için 2 farklı fragment kullanılmıştır. Fragmentların birinde kullanıcıların varlıklarının yüzdelik ve miktara bağlı olarak
dairesel grafiği oluşturulup, gösterilirken diğer fragmentta detaylı varlık dökümü kullanıcılara sunulmuştur. Dairesel grafik oluşturulurken ek grafik kütüphanelerinden faydalanılmış, detaylı varlık dökümü fragmentinda ise veritabanında kayıtlı olan kullanıcı varlıklarının detayları ile birlikte çekilip kullanıcıya sunularak oluşturulmuştur. Aynı zamanda bu varlıklardan elde ettikleri kar, zarar bilgileri de güncel kurlar üstünden güncellenmektedir.
        
![image](https://user-images.githubusercontent.com/32914909/52638803-cf301700-2ee3-11e9-9b73-eeeea57f1f3d.png)  ![image](https://user-images.githubusercontent.com/32914909/52638812-d1927100-2ee3-11e9-8fd0-d6cab37af7e4.png)

                                    
     Şekil 1.9.1     Şekil 1.9.2
1.10	GrupChat Ekranı
Uygulamamıza renk katacağını düşündüğümüz bir diğer araç ise grup chattir. Grup chat ile uygulama içinde bulunan kullanıcılar birbirleri ile iletişim sağlayabilir ve görüşlerini paylaşabilmektedirler. Bu ekranı tasarlarken bir çok dış kaynaktan yararlandık. Öncelik olarak cloud sistemleri, bulut haberleşme gibi konular üstünde araştırma yapıp, daha sonra bu amaçlar için oluşturulan adaptörlerden yardım alarak firebase üstünde chat işlemini de gerçekleştirmiş olduk.


![image](https://user-images.githubusercontent.com/32914909/52638834-e40caa80-2ee3-11e9-8ee4-d1539db15e72.png)

                                                 
      Şekil 1.10.1
1.11	Alım-Satım işlemleri Ekranları
Kullanıcıların döviz alım satımı yapabilmesi için kullanımı basit ve kullanıcı dostu bir tasarım gerçekleştirdik. Kullanıcılar bu ekran üstünden gerekli alım satım işlemlerini gerçekleştirebilecekler, bunlar ile ilgili yeterli paranın bulunmaması veya işlemin başarılı şekilde gerçekleştirilmesi durumu gibi durumlarda gerekli kontroller ile kullanıcıya bu konuda bilgilendirme mesajları da sunduk.
   
![image](https://user-images.githubusercontent.com/32914909/52638844-ecfd7c00-2ee3-11e9-8fb8-eef21ee7ac8c.png)  ![image](https://user-images.githubusercontent.com/32914909/52638851-f0910300-2ee3-11e9-9e68-c64608cc6682.png)

                                           
    Şekil 1.11.1    Şekil 1.11.2

