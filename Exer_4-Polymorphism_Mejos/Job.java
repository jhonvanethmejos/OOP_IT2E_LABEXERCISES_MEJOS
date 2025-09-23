public class Job {
    private int jobId;
    private String title;
    private String description;
    private String requirements;
    private String status;

    public Job(int jobId, String title, String description, String requirements, String status) {
        this.jobId = jobId;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.status = status;
    }

    public void postJob() {
        System.out.println("Job posted: " + title);
    }

    public void updateJob() {
        System.out.println("Job updated: " + title);
    }

    public void closeJob() {
        status = "Closed";
        System.out.println("Job closed: " + title);
    }
}
