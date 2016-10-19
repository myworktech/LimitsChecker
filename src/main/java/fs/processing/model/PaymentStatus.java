package fs.processing.model;

public enum PaymentStatus {
    NEW_PAYMENT("New Payment"),
    SUBMIT_REQUIRED("Submit Required"),
    READY_TO_PROCESS("Process Ready  ");

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
