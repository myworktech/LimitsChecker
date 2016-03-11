package fs.processing.model;

/**
 * Created by pechenkin on 15.09.2015.
 */
public enum PaymentStatus {
    NEW_POLICY("New Policy"),
    SUBMIT_REQUIRED("Subm Requi"),
    ALREADY_CHECKED("Already Che");

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
