package fs.processing.model;

/**
 * Created by pechenkin on 15.09.2015.
 */
public enum PaymentStatus {
    NEW_PAYMENT("New Payment"),
    SUBMIT_REQUIRED("Submit Required"),
    READY_TO_PROCESS("Process Ready");

    @Override
    public String toString() {
        return name;
    }

    private String name;

    PaymentStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
