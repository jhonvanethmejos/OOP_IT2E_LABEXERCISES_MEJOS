public class Application {
    private int applicationId;
    private String status;
    private String dateApplied;

    public Application(int applicationId, String status, String dateApplied) {
        this.applicationId = applicationId;
        this.status = status;
        this.dateApplied = dateApplied;
    }

    public void submit() {
        System.out.println("Application submitted on " + dateApplied);
    }

    public void withdraw() {
        status = "Withdrawn";
        System.out.println("Application withdrawn.");
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Application status updated to: " + newStatus);
    }
}
