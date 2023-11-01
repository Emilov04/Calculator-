
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Main implements ActionListener {

    JTextField textField;
    JFrame frame;
    double num1 = 0, num2 = 0;
    String sign;
    Font font = new Font("Ink Free", Font.ITALIC, 15);
    JButton[] numbers = new JButton[10];
    JButton[] function = new JButton[5];
    JButton divButton, mulButton, subButton, addButton, equButton, dotButton, delButton, clrButton;

    public Main(){

        frame = new JFrame("Calculator");

        frame.setSize(420, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(103, 50, 200, 30);
        textField.setFont(font);
        textField.setEditable(false);

        frame.add(textField);
        frame.getContentPane().setBackground(new Color(82, 78, 78));

        int cntX = 0, cntY = 0;

        for (int i = 0; i < 10; i++) {
            //placing numbers on frame

            numbers[i] = new JButton(String.valueOf(i));
            numbers[i].addActionListener(this);
            frame.add(numbers[i]);
            if(i != 0){
                if(i == 4 || i == 7){
                    cntX = 0;
                    cntY += 60;
                }
                numbers[i].setBounds(93 + cntX, 90 + cntY, 50, 50);
                cntX += 60;
            }

        }
        numbers[0].setBounds(153, 270, 50, 50);

        delButton = new JButton("Delete");
        delButton.setBounds(203, 330, 100, 50);
        delButton.addActionListener(this);

        frame.add(delButton);

        clrButton = new JButton("Clear");
        clrButton.addActionListener(this);
        clrButton.setBounds(93, 330, 100, 50);
        clrButton.setBackground(Color.BLUE);

        frame.add(clrButton);

        divButton = new JButton("/");
        divButton.addActionListener(this);
        divButton.setBounds(273, 90, 50, 50);
        function[0] = divButton;
        frame.add(divButton);

        mulButton = new JButton("x");
        mulButton.setBounds(273, 150, 50, 50);
        mulButton.addActionListener(this);
        frame.add(mulButton);
        function[1] = mulButton;

        subButton = new JButton("-");
        subButton.setBounds(273, 210, 50, 50);
        subButton.addActionListener(this);
        function[2] = subButton;
        frame.add(subButton);

        addButton = new JButton("+");
        addButton.setBounds(273, 270, 50, 50);
        addButton.addActionListener(this);
        function[3] = addButton;
        frame.add(addButton);



        equButton = new JButton("=");
        equButton.setBounds(93 + 120,  270, 50, 50);
        frame.add(equButton);
        function[4] = equButton;
        equButton.addActionListener(this);

        dotButton = new JButton(".");
        dotButton.setBounds(93, 270, 50, 50);
        frame.add(dotButton);

        dotButton.addActionListener(this);


        frame.setVisible(true);
        frame.setResizable(false);

    }

    public static void main(String[] args) {
        new Main();


    }
///functions

    public double result(double num1, double num2, String sign){
        switch (sign){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "x":
                return num1 * num2;
            case "/":
                return num1/num2;

        }
        return 0;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                textField.setText("");
            }
        };

        for (int i = 0; i < 10; i++) {

            if (e.getSource() == numbers[i]) {
                if(textField.getText().equals("0")){
                    textField.setText("");
                }
                textField.setText(textField.getText() + (i));

                break;

            }

        }

        if(e.getSource() == dotButton){

            if(!textField.getText().contains(".") && textField.getText().length() >= 1) {

                textField.setText(textField.getText() + ".");

            }

        }

        for(int p=0;  p<  5; p++){

            if(e.getSource() == equButton){
                String sn = textField.getText();
                if(sn.length() == 0 || sn.charAt(sn.length()-1) == '.'){
                    continue;
                }
                num1 = result( num2,Double.parseDouble(textField.getText()),  sign);

                ///result

                if(Double.parseDouble(sn) == 0  && sign.equals("/")){
                    timer.schedule(timerTask, 1000);
                    textField.setText("You cannot divide by zero");


                }

                else if((num1*10) % 10 == 0){
                    textField.setText(String.valueOf((int) num1));
                }else {
                    textField.setText(String.valueOf(num1));
                }
                break;

            }
            if(e.getSource() == function[p]){
                if (textField.getText().length() == 0) {
                    continue;
                }
                sign = String.valueOf(function[p].getText());

                num2 =  Double.parseDouble(textField.getText());

                textField.setText("");
                break;
            }

        }


        if (e.getSource() == clrButton) {

            textField.setText("");

        }
        if (e.getSource() == delButton) {



            if(textField.getText().length() != 0){

                textField.setText(textField.getText().substring(0, textField.getText().length() - 1));

            }

        }
    }

}


//task
//