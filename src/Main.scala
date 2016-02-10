import javax.swing.UIManager

object Main extends App {
  val ICON_STR: String = "images/trayicon32x32.png"
  val APPLICATION_NAME: String = "Yandex Radio Alarm"
  val CONFIG_FILE : String = "config.xml"
  val CHROME_PATH : String = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe --new-window https://radio.yandex.ru/activity/wake-up"

  UIManager.getInstalledLookAndFeels.find(_.getName == "Windows").headOption match {
    case Some(info) => UIManager.setLookAndFeel(info.getClassName)
    case None => UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName)
  }

  val mainForm: AlarmWindow = new AlarmWindow
}