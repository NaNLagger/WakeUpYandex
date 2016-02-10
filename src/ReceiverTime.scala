import java.time.LocalTime

/**
  * Created by Stepan on 12.01.2016.
  */
class ReceiverTime extends Runnable{
  override def run(): Unit = {
    var time: LocalTime = LocalTime.now()
    while (true) {
      time = LocalTime.now()
      Thread.sleep(1000)
      if(Alarm.state && time.getHour == Alarm.time.getHour && time.getMinute == Alarm.time.getMinute)
        Alarm.StartAlarm()
      println(time.getHour + " : " + time.getMinute)
    }
  }
}
