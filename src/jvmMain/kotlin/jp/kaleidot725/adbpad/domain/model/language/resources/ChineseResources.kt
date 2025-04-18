package jp.kaleidot725.adbpad.domain.model.language.resources

object ChineseResources : StringResources {
    override val windowTitle = "AdbPad($APP_VERSION)"

    override val notFoundDevice = "未找到设备"
    override val notFoundCommand = "未找到命令"
    override val notFoundInputText = "未找到输入文本"
    override val notFoundScreenshot = "未找到截图"

    override val execute = "运行"
    override val save = "保存"
    override val delete = "删除"
    override val tab = "标签"
    override val send = "发送"
    override val cancel = "取消"
    override val targetDevice = "设备"
    override val tool = "工具"
    override val setting = "设置"
    override val dark = "深色"
    override val light = "浅色"
    override val system = "系统"
    override val search: String = "搜索"

    override val textCommandUnTitle: String = "取消文本标题命令"

    override val screenshotTakeByCurrentTheme = "按当前主题截图"
    override val screenshotTakeByDarkTheme = "按深色主题截图"
    override val screenshotTakeByLightTheme = "按浅色主题截图"
    override val screenshotTakeByBothTheme = "按两种主题截图"

    override val textCommandOptionNewLine: String = "用换行键发送"
    override val textCommandOptionTab: String = "用制表符键发送"

    override val commandStartEventFormat = "开始发送命令 「%s」"
    override val commandEndEventFormat = "结束发送命令 「%s」"
    override val commandErrorEventFormat = "发送命令失败 「%s」"

    override val commandPointerLocationOnTitle = "指针位置：开启"
    override val commandPointerLocationOnDetails = "启用屏幕叠加层显示当前触摸点坐标"
    override val commandPointerLocationOffTitle = "指针位置：关闭"
    override val commandPointerLocationOffDetails = "禁用屏幕叠加层显示当前触摸点坐标"
    override val commandLayoutBorderOnTitle = "显示布局边界: 开启"
    override val commandLayoutBorderOnDetails = "启用显示裁剪边界、边距等。"
    override val commandLayoutBorderOffTitle = "显示布局边界: 关闭"
    override val commandLayoutBorderOffDetails = "禁用显示裁剪边界、边距等。"
    override val commandTapEffectOnTitle = "显示触摸点: 开启"
    override val commandTapEffectOnDetails = "启用显示触摸点的视觉反馈。"
    override val commandTapEffectOffTitle = "显示触摸点: 关闭"
    override val commandTapEffectOffDetails = "禁用显示触摸点的视觉反馈。"
    override val commandSleepModeOnTitle = "休眠模式: 开启"
    override val commandSleepModeOnDetails = "启用休眠模式，设备进入休眠状态。"
    override val commandSleepModeOffTitle = "休眠模式: 关闭"
    override val commandSleepModeOffDetails = "禁用休眠模式，设备不会进入休眠。"
    override val commandDarkThemeOnTitle = "深色主题: 开启"
    override val commandDarkThemeOnDetails = "启用深色主题。"
    override val commandDarkThemeOffTitle = "深色主题: 关闭"
    override val commandDarkThemeOffDetails = "禁用深色主题。"
    override val commandWifiOnTitle = "Wi-Fi: 开启"
    override val commandWifiOnDetails = "启用 Wi-Fi 通信。"
    override val commandWifiOffTitle = "Wi-Fi: 关闭"
    override val commandWifiOffDetails = "禁用 Wi-Fi 通信。"
    override val commandDataOnTitle = "蜂窝网ad络: 开启"
    override val commandDataOnDetails = "启用蜂窝网络通信。"
    override val commandDataOffTitle = "蜂窝网络: 关闭"
    override val commandDataOffDetails = "禁用蜂窝网络通信。"
    override val commandWifiAndDataOnTitle = "Wi-Fi 和蜂窝网络: 开启"
    override val commandWifiAndDataOnDetails = "启用 Wi-Fi 和蜂窝网络通信。"
    override val commandWifiAndDataOffTitle = "Wi-Fi 和蜂窝网络: 关闭"
    override val commandWifiAndDataOffDetails = "禁用 Wi-Fi 和蜂窝网络通信。"
    override val commandScreenPinningOffTitle = "屏幕固定: 关闭"
    override val commandScreenPinningOffDetails = "禁用屏幕固定功能。"
    override val commandEnableThreeButtonNavigationTitle: String = "3 按钮导航 : 开启"
    override val commandEnableThreeButtonNavigationDetails: String = "启动三键导航"
    override val commandEnableTwoButtonNavigationTitle: String = "2 按钮导航 : 开启"
    override val commandEnableTwoButtonNavigationDetails: String = "启动双按钮导航"
    override val commandEnableGestureNavigationTitle: String = "手势导航 : 开启"
    override val commandEnableGestureNavigationDetails: String = "启动手势导航"

    override val textCommandStartEventFormat = "开始发送文本 「%s」"
    override val textCommandEndEventFormat = "结束发送文本 「%s」"
    override val textCommandErrorEventFormat = "发送文本失败 「%s」"

    override val keyCommandStartEventFormat = "开始发送按键 「%s」"
    override val keyCommandEndEventFormat = "结束发送按键 「%s」"
    override val keyCommandErrorEventFormat = "发送按键失败 「%s」"

    override val screenshotCommandStartEventFormat = "开始截屏"
    override val screenshotCommandEndEventFormat = "结束截屏"
    override val screenshotCommandErrorEventFormat = "截屏失败"
    override val screenshotCopyToClipbaordEventFormat = "已复制截图到剪贴板"
    override val cantScreenshotCopyToClipbaordEventFormat = "无法复制截图到剪贴板"
    override val screenshotClearCache = "删除截图"

    override val menuCommandTitle = "命令"
    override val menuInputTextTitle = "发送文本"
    override val menuScreenshot = "截图"

    override val settingLanguageHeader = "语言"
    override val settingLanguageEnglish = "英语"
    override val settingLanguageJapanese = "日语"
    override val settingLanguageChinese = "简体中文"
    override val settingLanguageTurkish = "土耳其语"

    override val settingAppearanceHeader = "外观"
    override val settingAdbHeader = "ADB"
    override val settingAdbDirectoryPathTitle = "二进制路径"
    override val settingAdbPortNumberTitle = "服务端口"
    override val settingAndroidSdkHeader = "Android SDK"
    override val settingAndroidSdkDirectoryPathTitle = "目录路径"
    override val settingAdbRestartTitle = "重启 ADB"

    override val adbErrorTitle = "ADB 错误"
    override val adbErrorMessage = "无法启动 ADB 服务，请更改 ADB 设置。"
    override val adbErrorOpenSetting = "打开设置"

    override val sortByNameAsc: String = "名稱(升序)"
    override val sortByNameDesc: String = "名稱(降序)"

    // TopSection tooltips
    override val tooltipRefresh: String = "刷新"
    override val tooltipPower: String = "电源"
    override val tooltipVolumeUp: String = "音量增大"
    override val tooltipVolumeDown: String = "音量减小"
    override val tooltipVolumeMute: String = "静音"
    override val tooltipBack: String = "返回"
    override val tooltipHome: String = "主页"
    override val tooltipRecents: String = "最近任务"

    // NavigationRail tooltips
    override val tooltipCommand: String = "命令"
    override val tooltipText: String = "文本"
    override val tooltipScreenshot: String = "截图"
    override val tooltipFile: String = "文件"
    override val tooltipSetting: String = "设置"
}
