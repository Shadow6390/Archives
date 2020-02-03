package com.view;

import javax.swing.JOptionPane;

public class InputChoiceDialog extends JOptionPane{
	private Object[] answers;
	private Object parent;
	
	public InputChoiceDialog(Object parent, String message,String[] options) {
		this.parent = parent;
                this.answers=options;
		this.setInitialValue((String) answers[0]);
		this.setMessage(message);

	}
	
	public String getAnswer() {
		return (String) this.showInputDialog(parent, answers);
	}
}
