package guiButtons;

public class SubButton extends AbstractButton {

    public SubButton(String text,Double value1, Double value2) {
        super(text,value1,value2);
    }

    @Override
    public String execute() {
        String serverResponse = 
                this.getReceiver().action(this, value1, value2);
        return serverResponse;
    }

}
