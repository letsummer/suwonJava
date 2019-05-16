import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout; //awt는 윈도우상의 디자인을 따라감
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calc extends JFrame implements ActionListener{ 
	//ActionListener: 데스크탑이 이미 만들어놓은 것, 인터페이스 중 하나
	//인터페이스: 이름만 있는 메소드

	private JPanel panel; //판, 가장 큰 경계선
	private JTextField display;
	private JButton[] buttons;
	private String[] labels = { "Backspace", "", "", "CE", "C", "7", "8", "9",
			"/", "sqrt", "4", "5", "6", "x", "%", "1", "2", "3", "-", "1/x",
			"0", "-/+", ".", "+", "=", };	
	private double result=0; //결과값 초기화
	private String operator="=";
	private boolean startOfNumber = true;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calc c = new Calc();
	}
	
	public Calc()
	{
		display = new JTextField(35); //JTextField cannot be resolved to a type: 클래스인지 확인이 안 돼ㅠ
		panel = new JPanel(); //JVM이 알아서 처리해줘서 소멸자 안 만들어도 됨 new 막 생성해~~
		display.setText("0.0"); //display.setEnabled(true);
		panel.setLayout(new GridLayout(0, 5, 3, 3));
		buttons = new JButton[25];
		
		int index = 0;
		for (int rows = 0; rows < 5; rows++) { //5*5 모양의 계산기
			for (int cols = 0; cols < 5; cols++) {
				buttons[index] = new JButton(labels[index]);
				if (cols >= 3)
					buttons[index].setForeground(Color.white);
				else
					buttons[index].setForeground(Color.black);
				
				buttons[index].setBackground(Color.pink);
				panel.add(buttons[index]);
				buttons[index].addActionListener(this); //addActionListner: 액션을 듣고있는 함수, 여기서는 버튼 누를 때의 것
				index++;
			}
		}
		add(display, BorderLayout.NORTH); //add는 JFrame이 가진 메소드
		add(panel, BorderLayout.CENTER); 
		setVisible(true);
		pack();
	}
	
	public void actionPerformed(ActionEvent e) { //actionPerformed: 액션에 따른 모든 효과 모음
		String command = e.getActionCommand();
		
		if (command.charAt(0) == 'C')
		{
			startOfNumber = true;
			result = 0;
			operator = "=";
			display.setText("0.0");
		}
		
		else if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals("."))
		{
			if (startOfNumber == true)
				display.setText(command);
			
			else
				display.setText(display.getText() + command);
			
			startOfNumber = false;
		}
		
		else
		{
			if (startOfNumber)
			{
				if (command.equals("-"))
				{
					display.setText(command);
					startOfNumber = false;
				}
				
				else
					operator = command;
			}
			
			else
			{
				double x = Double.parseDouble(display.getText());
				calculate(x);
				operator = command;
				startOfNumber = true;
			}
		}
	}
	
	private void calculate(double n) { //실제 계산 과정
		if (operator.equals("+"))
			result += n;
		else if (operator.equals("-"))
			result -= n;
		else if (operator.equals("*"))
			result *= n;
		else if (operator.equals("/"))
			result /= n;
		else if (operator.equals("="))
			result = n;
		display.setText("" + result);
	}
	

}
