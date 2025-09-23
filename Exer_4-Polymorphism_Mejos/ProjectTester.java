public class ProjectTester {
    public static void main(String[] args) {
        // POLYMORPHISM via User reference
        User user1 = new Applicant(1, "Jhon", "jhon@email.com", "pass123", "MyResume", "Java, Html");
        User user2 = new Admin(2, "Georgie", "admin@email.com", "adminpass");

        user1.login();         // Applicant's overridden method
        user2.login();         // Admin's overridden method
        user1.updateProfile(); // Inherited
        user2.logout();        // Inherited

        // Create and use Job
        Job job = new Job(101, "Software Developer", "Developer job", "Java,", "Open");
        job.postJob();
        job.updateJob();
        job.closeJob();

        // Create and use Application
        Application app = new Application(201, "Pending", "2025-01-22");
        app.submit();
        app.updateStatus("Under Review");
        app.withdraw();
    }
}
