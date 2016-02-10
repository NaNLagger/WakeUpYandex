import java.awt.Desktop
import java.net.URI
import java.time.{LocalTime, LocalDateTime}
import sys.process._

/**
  * Created by Stepan on 12.01.2016.
  */
object Alarm {

  var time: LocalTime = LocalTime.now()
  var state: Boolean = false
  val receiver = new Thread(new ReceiverTime)

  def SetTime(nTime: LocalTime): Unit = {
    time = nTime
    state = true
    if(!receiver.isAlive)
      receiver.start()
  }

  def StartAlarm(): Unit = {
    //Main.CHROME_PATH.!
    if(Desktop.isDesktopSupported)
    {
      Desktop.getDesktop.browse(new URI("https://radio.yandex.ru/activity/wake-up"))
    }
  }

  def Reset(): Unit = {
    state = false;
  }

}
