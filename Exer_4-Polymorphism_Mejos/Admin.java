public class Admin extends User {

    public Admin(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "Admin";
    }

    public void manageJobs() {
        System.out.println("Admin managing jobs.");
    }

    public void viewApplications() {
        System.out.println("Admin viewing applications.");
    }

    public void approveApplications() {
        System.out.println("Admin approved applications.");
    }

    public void rejectApplications() {
        System.out.println("Admin rejected applications.");
    }

    @Override
    public void login() {
        System.out.println(name + " logged in as an admin.");
    }
}
