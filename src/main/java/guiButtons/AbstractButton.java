package guiButtons;

import javax.swing.JButton;

public abstract class AbstractButton extends JButton {
    ButtonReceiver receiver;
    /*Will be the value of textBox value 1*/
    Double value1;
    /*Will be the value of textBox value 1*/
    Double value2;
    //Double result;
    
    public AbstractButton(String text,Double value1, Double value2) {
        super(text);
        this.value1 = value1;
        this.value2 = value2;
    }
    
    public abstract String execute();
    
    public ButtonReceiver getReceiver() {
        return this.receiver;
    }
    
    public void setReceiver(ButtonReceiver receiver) {
        this.receiver = receiver;
    }

    public Double getValue1() {
        return value1;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public Double getValue2() {
        return value2;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }
    
}
