package sample;

public class HelloWorld {
    private String printMessage;

    public String getPrintMessage() {
        return printMessage;
    }

    void setPrintMessage(String printMessage) {
        this.printMessage = printMessage;
    }

    public void showMessage() {
        System.out.print(printMessage);
    }
}
