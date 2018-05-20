package ua.kiev.prog;

public class Notifier {
    private LoggerAPI loggerAPI;

    public Notifier(LoggerAPI loggerAPI) {
        this.loggerAPI = loggerAPI;
    }

    public void sendSms() {
        try {
            loggerAPI.log("Sending sms...");
            // emulate some job
            Thread.sleep(3000);
            // done
            loggerAPI.log("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
