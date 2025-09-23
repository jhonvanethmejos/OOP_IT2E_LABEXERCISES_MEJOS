public class User {
    protected int userId;
    protected String name;
    protected String email;
    protected String password;
    protected String role;

    public void login() {
        System.out.println(name + " logged in as a " + role + ".");
    }

    public void logout() {
        System.out.println(name + " logged out.");
    }

    public void updateProfile() {
        System.out.println("User profile updated.");
    }
}
