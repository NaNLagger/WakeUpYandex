import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.time.LocalTime;

/**
 * Created by Stepan on 12.01.2016.
 */
public class AlarmWindow extends JFrame {
    private JSpinner HourSpinner;
    private JSpinner MinuteSpinner;
    private JButton SetTimeButton;
    private JPanel mainPanel;
    private JLabel timeLabel;
    private JButton resetButton;
    private JPanel alarmInfoPanel;


    public AlarmWindow() {
        super();
        setTitle(Main.APPLICATION_NAME());
        setContentPane(mainPanel);
        setSize(250, 220);
        setVisible(true);
        InitButtons();
        InitSpinners();
        InitMenuBar();
        ShowAlarmTime();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                TrayController.hideToTray();
            }
        });
    }

    private void ShowAlarmTime() {
        alarmInfoPanel.setVisible(Alarm.state());
        timeLabel.setText("Будильник установлен на " + Alarm.time().getHour() + " : " + Alarm.time().getMinute());
    }

    private void InitButtons() {
        SetTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alarm.SetTime(LocalTime.of((int) HourSpinner.getValue(), (int) MinuteSpinner.getValue()));
                ShowAlarmTime();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alarm.Reset();
                ShowAlarmTime();
            }
        });
    }

    private void InitSpinners() {
        HourSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int) HourSpinner.getValue() > 24) {
                    HourSpinner.setValue(1);
                }
                if((int) HourSpinner.getValue() < 1) {
                    HourSpinner.setValue(24);
                }
            }
        });

        MinuteSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int) MinuteSpinner.getValue() > 59) {
                    MinuteSpinner.setValue(0);
                }
                if((int) MinuteSpinner.getValue() < 0) {
                    MinuteSpinner.setValue(59);
                }
            }
        });
    }

    private void InitMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem itemMenu = new JMenuItem("Exit");
        itemMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(itemMenu);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }

}
