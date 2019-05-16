import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout; //awt�� ��������� �������� ����
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calc extends JFrame implements ActionListener{ 
	//ActionListener: ����ũž�� �̹� �������� ��, �������̽� �� �ϳ�
	//�������̽�: �̸��� �ִ� �޼ҵ�

	private JPanel panel; //��, ���� ū ��輱
	private JTextField display;
	private JButton[] buttons;
	private String[] labels = { "Backspace", "", "", "CE", "C", "7", "8", "9",
			"/", "sqrt", "4", "5", "6", "x", "%", "1", "2", "3", "-", "1/x",
			"0", "-/+", ".", "+", "=", };	
	private double result=0; //����� �ʱ�ȭ
	private String operator="=";
	private boolean startOfNumber = true;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calc c = new Calc();
	}
	
	public Calc()
	{
		display = new JTextField(35); //JTextField cannot be resolved to a type: Ŭ�������� Ȯ���� �� �Ť�
		panel = new JPanel(); //JVM�� �˾Ƽ� ó�����༭ �Ҹ��� �� ���� �� new �� ������~~
		display.setText("0.0"); //display.setEnabled(true);
		panel.setLayout(new GridLayout(0, 5, 3, 3));
		buttons = new JButton[25];
		
		int index = 0;
		for (int rows = 0; rows < 5; rows++) { //5*5 ����� ����
			for (int cols = 0; cols < 5; cols++) {
				buttons[index] = new JButton(labels[index]);
				if (cols >= 3)
					buttons[index].setForeground(Color.white);
				else
					buttons[index].setForeground(Color.black);
				
				buttons[index].setBackground(Color.pink);
				panel.add(buttons[index]);
				buttons[index].addActionListener(this); //addActionListner: �׼��� ����ִ� �Լ�, ���⼭�� ��ư ���� ���� ��
				index++;
			}
		}
		add(display, BorderLayout.NORTH); //add�� JFrame�� ���� �޼ҵ�
		add(panel, BorderLayout.CENTER); 
		setVisible(true);
		pack();
	}
	
	public void actionPerformed(ActionEvent e) { //actionPerformed: �׼ǿ� ���� ��� ȿ�� ����
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
	
	private void calculate(double n) { //���� ��� ����
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
