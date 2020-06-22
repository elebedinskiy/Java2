package ru.elebedinskiy.java2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendMassageWindow extends JFrame {
    SendMassageWindow(){
        setTitle("Окно отправки сообщений");
        setBounds(250,250,600,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false); // размер окна менять нельзя

        JPanel panelFieldAndButton = new JPanel(); // панель для формы ввода и кнопки;
        JPanel panelTextArea = new JPanel(); // панель для многострочного поля с полосой прокрутки;

        //странно, но работает и без точного указания какой именно компоновщик применить;
        //setLayout(new BorderLayout());
        //panelFieldAndButton.setLayout(new FlowLayout());
        //panelTextArea.setLayout(new FlowLayout());

        JTextField fieldEnterText = new JTextField(47);
        JButton buttonSendMessage = new JButton("Отправить");

        JTextArea textLogMessageArea = new JTextArea("Добро пожаловать в чат. Напишите сообщение, нажмите кнопку 'Отправить' или клавишу 'Enter'\n", 20,57);
        textLogMessageArea.setLineWrap(true); // перенос возможен;
        textLogMessageArea.setWrapStyleWord(true); // перенос по словам;

        panelTextArea.add(new JScrollPane(textLogMessageArea)); // многострочное поле будет с полосой прокрутки;
        panelFieldAndButton.add(fieldEnterText);
        panelFieldAndButton.add(buttonSendMessage);
        add(panelFieldAndButton, BorderLayout.SOUTH); // добавим панель вниз, в положение "юг";
        add(panelTextArea);

        textLogMessageArea.setEditable(false); // отправленные сообщения редактировать нельзя;
        textLogMessageArea.setFocusable(false); // можно и удалить;
        fieldEnterText.setFocusable(true);

        setVisible(true);

        buttonSendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dateNow = new Date();
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
                if (!fieldEnterText.getText().equals("")){
                    textLogMessageArea.append(formatForDateNow.format(dateNow) + " > " + fieldEnterText.getText() + "\n");
                    fieldEnterText.setText("");
                } else System.out.println(formatForDateNow.format(dateNow) + " > Пустое сообщение..."); // можно удалить, это для проверки
            }
        });

        fieldEnterText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dateNow = new Date();
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
                if (!fieldEnterText.getText().equals("")){
                    textLogMessageArea.append(formatForDateNow.format(dateNow) + " > " + fieldEnterText.getText() + "\n");
                    fieldEnterText.setText("");
                } else System.out.println(formatForDateNow.format(dateNow) + " > Пустое сообщение..."); // можно удалить, это для проверки
            }
        });

    }
}
