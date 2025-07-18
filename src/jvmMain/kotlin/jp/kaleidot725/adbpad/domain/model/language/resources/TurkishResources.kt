package jp.kaleidot725.adbpad.domain.model.language.resources

object TurkishResources : StringResources {
    override val windowTitle = "AdbPad($APP_VERSION)"

    override val notFoundDevice = "Cihaz bulunamadı"
    override val notFoundCommand = "Komut bulunamadı"
    override val notFoundInputText = "Giriş metni bulunamadı"
    override val notFoundScreenshot = "Ekran görüntüsü bulunamadı"

    override val execute = "Çalıştır"
    override val save = "Kaydet"
    override val delete = "Sil"
    override val tab = "Sekme"
    override val send = "Gönder"
    override val cancel = "İptal"
    override val targetDevice = "Cihazlar"
    override val tool = "Araçlar"
    override val setting = "Ayarlar"
    override val dark = "Koyu"
    override val light = "Açık"
    override val system = "Sistem"
    override val search: String = "Ara"

    override val textCommandUnTitle: String = "başlıksız metin komutu"

    override val screenshotTakeByCurrentTheme = "Mevcut tema ile al"
    override val screenshotTakeByDarkTheme = "Koyu tema ile al"
    override val screenshotTakeByLightTheme = "Açık tema ile al"
    override val screenshotTakeByBothTheme = "Her iki tema ile al"

    override val textCommandOptionNewLine: String = "Yeni satır tuşu ile gönder"
    override val textCommandOptionTab: String = "Sekme tuşu ile gönder"

    override val commandStartEventFormat = "「%s」 komutu gönderiliyor"
    override val commandEndEventFormat = "「%s」 komutu gönderildi"
    override val commandErrorEventFormat = "「%s」 komutu gönderilirken hata oluştu"

    override val commandPointerLocationOnTitle = "İmleç konumu: Açık"
    override val commandPointerLocationOnDetails = "Mevcut dokunma noktası koordinatlarını gösteren ekran katmanını etkinleştir"
    override val commandPointerLocationOffTitle = "İmleç konumu: Kapalı"
    override val commandPointerLocationOffDetails = "Mevcut dokunma noktası koordinatlarını gösteren ekran katmanını devre dışı bırak"
    override val commandLayoutBorderOnTitle = "Düzen sınırlarını göster: AÇIK"
    override val commandLayoutBorderOnDetails = "Kırpma sınırlarını, kenar boşluklarını vb. göstermeyi etkinleştir"
    override val commandLayoutBorderOffTitle = "Düzen sınırlarını göster: KAPALI"
    override val commandLayoutBorderOffDetails = "Kırpma sınırlarını, kenar boşluklarını vb. göstermeyi devre dışı bırak"
    override val commandTapEffectOnTitle = "Dokunmaları göster: AÇIK"
    override val commandTapEffectOnDetails = "Dokunmalar için görsel geri bildirimi etkinleştir"
    override val commandTapEffectOffTitle = "Dokunmaları göster: KAPALI"
    override val commandTapEffectOffDetails = "Dokunmalar için görsel geri bildirimi devre dışı bırak"
    override val commandSleepModeOnTitle = "Uyku modu: AÇIK"
    override val commandSleepModeOnDetails = "Uyku modunu etkinleştir ve cihazın uykuya geçmesine izin ver"
    override val commandSleepModeOffTitle = "Uyku modu: KAPALI"
    override val commandSleepModeOffDetails = "Uyku modunu devre dışı bırak ve cihazın uykuya geçmesini engelle"
    override val commandDarkThemeOnTitle = "Koyu tema: AÇIK"
    override val commandDarkThemeOnDetails = "Koyu temayı etkinleştir"
    override val commandDarkThemeOffTitle = "Koyu tema: KAPALI"
    override val commandDarkThemeOffDetails = "Koyu temayı devre dışı bırak"
    override val commandWifiOnTitle = "Wi-Fi: AÇIK"
    override val commandWifiOnDetails = "Wi-Fi iletişimini etkinleştir"
    override val commandWifiOffTitle = "Wi-Fi: KAPALI"
    override val commandWifiOffDetails = "Wi-Fi iletişimini devre dışı bırak"
    override val commandDataOnTitle = "Hücresel veri: AÇIK"
    override val commandDataOnDetails = "Hücresel veri iletişimini etkinleştir"
    override val commandDataOffTitle = "Hücresel veri: KAPALI"
    override val commandDataOffDetails = "Hücresel veri iletişimini devre dışı bırak"
    override val commandWifiAndDataOnTitle = "Wi-Fi ve hücresel veri: AÇIK"
    override val commandWifiAndDataOnDetails = "Wi-Fi ve hücresel veri iletişimini etkinleştir"
    override val commandWifiAndDataOffTitle = "Wi-Fi ve hücresel veri: KAPALI"
    override val commandWifiAndDataOffDetails = "Wi-Fi ve hücresel veri iletişimini devre dışı bırak"
    override val commandScreenPinningOffTitle = "Ekran sabitleme: KAPALI"
    override val commandScreenPinningOffDetails = "Ekran sabitlemeyi devre dışı bırak"
    override val commandEnableThreeButtonNavigationTitle: String = "3 düğmeli gezinme: AÇIK"
    override val commandEnableThreeButtonNavigationDetails: String = "3 düğmeli gezinmeyi etkinleştir"
    override val commandEnableTwoButtonNavigationTitle: String = "2 düğmeli gezinme: AÇIK"
    override val commandEnableTwoButtonNavigationDetails: String = "2 düğmeli gezinmeyi etkinleştir"
    override val commandEnableGestureNavigationTitle: String = "Hareket tabanlı gezinme: AÇIK"
    override val commandEnableGestureNavigationDetails: String = "Hareket tabanlı gezinmeyi etkinleştir"

    override val textCommandStartEventFormat = "「%s」 metni gönderiliyor"
    override val textCommandEndEventFormat = "「%s」 metni gönderildi"
    override val textCommandErrorEventFormat = "「%s」 metni gönderilirken hata oluştu"

    override val keyCommandStartEventFormat = "「%s」 tuşu gönderiliyor"
    override val keyCommandEndEventFormat = "「%s」 tuşu gönderildi"
    override val keyCommandErrorEventFormat = "「%s」 tuşu gönderilirken hata oluştu"

    override val screenshotCommandStartEventFormat = "Ekran görüntüsü alınıyor"
    override val screenshotCommandEndEventFormat = "Ekran görüntüsü alındı"
    override val screenshotCommandErrorEventFormat = "Ekran görüntüsü alınırken hata oluştu"
    override val screenshotCopyToClipbaordEventFormat: String = "Ekran görüntüsü panoya kopyalandı"
    override val cantScreenshotCopyToClipbaordEventFormat: String = "Ekran görüntüsü panoya kopyalanamadı"
    override val screenshotClearCache: String = "Ekran görüntüsünü sil"

    override val menuCommandTitle = "Komut"
    override val menuInputTextTitle = "Metin Gönder"
    override val menuScreenshot = "Ekran Görüntüsü"

    override val settingLanguageHeader = "Dil"
    override val settingLanguageEnglish = "İngilizce"
    override val settingLanguageJapanese = "Japonca"
    override val settingLanguageChinese = "Çince"
    override val settingLanguageTurkish = "Türkçe"

    override val settingAppearanceHeader = "Görünüm"
    override val settingAdbHeader = "ADB"
    override val settingAdbDirectoryPathTitle = "Yürütülebilir Dosya Yolu"
    override val settingAdbPortNumberTitle = "Sunucu Portu"
    override val settingAndroidSdkHeader = "Android SDK"
    override val settingAndroidSdkDirectoryPathTitle = "Dizin Yolu"
    override val settingAdbRestartTitle: String = "ADB'yi Yeniden Başlat"

    override val adbErrorTitle = "Adb Hatası"
    override val adbErrorMessage = "Adb sunucusu başlatılamıyor, lütfen adb ayarlarını değiştirin."
    override val adbErrorOpenSetting = "Ayarları Aç"

    override val sortByNameAsc: String = "İsim (artan sıra)"
    override val sortByNameDesc: String = " İsim (azalan sıra)"

    // TopSection tooltips
    override val tooltipRefresh: String = "Yenile"
    override val tooltipPower: String = "Güç"
    override val tooltipVolumeUp: String = "Sesi Artır"
    override val tooltipVolumeDown: String = "Sesi Azalt"
    override val tooltipVolumeMute: String = "Sessiz"
    override val tooltipBack: String = "Geri"
    override val tooltipHome: String = "Ana Ekran"
    override val tooltipRecents: String = "Son Uygulamalar"

    // NavigationRail tooltips
    override val tooltipCommand: String = "Komut"
    override val tooltipText: String = "Metin"
    override val tooltipScreenshot: String = "Ekran Görüntüsü"
    override val tooltipFile: String = "Dosya"
    override val tooltipSetting: String = "Ayarlar"

    // MenuBar Window menu
    override val menuWindow: String = "Pencere"
    override val menuWindowMaximize: String = "Büyüt"
    override val menuWindowMinimize: String = "Küçült"
    override val menuWindowFullscreen: String = "Tam Ekran"
    override val menuWindowAlwaysOnTop: String = "Her Zaman Üstte"
}
