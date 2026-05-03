public class AccountService {
    public boolean register(String firstName, String lastName, String email, String dob, String password, String confirmPassword) {
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) return false;
        if (!email.contains("@") || !email.contains(".")) return false;
        if (password.length() < 8) return false;
        if (!password.equals(confirmPassword)) return false;
        // Basit bir tarih format kontrolü (GG/AA/YYYY)
        if (!dob.matches("\\d{2}/\\d{2}/\\d{4}")) return false;
        
        return true; 
    }
}