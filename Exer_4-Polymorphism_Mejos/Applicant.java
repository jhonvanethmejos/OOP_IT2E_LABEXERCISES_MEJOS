public class Applicant extends User {
    private String resume;
    private String skills;

    public Applicant(int userId, String name, String email, String password, String resume, String skills) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "Applicant";
        this.resume = resume;
        this.skills = skills;
    }

    public void register() {
        System.out.println(name + " registered.");
    }

    public void applyJob() {
        System.out.println(name + " applied for a job.");
    }

    public void viewApplicationStatus() {
        System.out.println(name + " is viewing application status.");
    }

    @Override
    public void login() {
        System.out.println(name + " logged in as an applicant.");
    }
}
