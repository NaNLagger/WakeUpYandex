import java.awt._
import java.awt.event.{ActionEvent, ActionListener}
import java.net.URL

/**
  * Created by Stepan on 12.01.2016.
  */
object TrayController {
  val currentIcon = initTray()

  def hideToTray(): Unit = {
    val tray: SystemTray = SystemTray.getSystemTray
    try {
      tray.add(currentIcon)
    }
    catch {
      case e: AWTException => e.printStackTrace()
    }
    currentIcon.displayMessage(Main.APPLICATION_NAME, "Application started!", TrayIcon.MessageType.INFO)
  }

  private def initTray(): TrayIcon = {
    val trayMenu: PopupMenu = new PopupMenu
    val item: MenuItem = new MenuItem("Exit")
    item.addActionListener(new ActionListener() {
      def actionPerformed(e: ActionEvent) {
        System.exit(0)
      }
    })
    trayMenu.add(item)

    val imageURL: URL = classOf[AlarmWindow].getClassLoader.getResource(Main.ICON_STR)

    val icon: Image = Toolkit.getDefaultToolkit.getImage(imageURL)
    val trayIcon: TrayIcon = new TrayIcon(icon, Main.APPLICATION_NAME, trayMenu)
    trayIcon.setImageAutoSize(true)
    trayIcon.addActionListener(new ActionListener() {
      def actionPerformed(e: ActionEvent) {
        new AlarmWindow
        removeFromTray()
      }
    })
    trayIcon
  }

  private def removeFromTray(): Unit = {
    val tray: SystemTray = SystemTray.getSystemTray
    tray.remove(currentIcon)
  }
}
