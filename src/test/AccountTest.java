import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private AccountService service;

    @BeforeEach
    void setUp() { service = new AccountService(); }

    @Test @DisplayName("1. Başarılı Kayıt")
    void testValidRegistration() {
        assertTrue(service.register("Mustafa", "Göktaş", "test@mail.com", "01/01/2000", "Pass12345", "Pass12345"));
    }

    @Test @DisplayName("2. EP: Boş İsim Hatası")
    void testEmptyFirstName() {
        assertFalse(service.register("", "Göktaş", "test@mail.com", "01/01/2000", "Pass12345", "Pass12345"));
    }

    @Test @DisplayName("3. EP: Geçersiz Email (@ yok)")
    void testInvalidEmailNoAt() {
        assertFalse(service.register("Mustafa", "Göktaş", "testmail.com", "01/01/2000", "Pass12345", "Pass12345"));
    }

    @Test @DisplayName("4. BVA: Kısa Şifre (7 Karakter)")
    void testShortPassword() {
        assertFalse(service.register("Mustafa", "Göktaş", "a@b.com", "01/01/2000", "1234567", "1234567"));
    }

    @Test @DisplayName("5. BVA: Sınır Şifre (8 Karakter)")
    void testBoundaryPassword() {
        assertTrue(service.register("Mustafa", "Göktaş", "a@b.com", "01/01/2000", "12345678", "12345678"));
    }

    @Test @DisplayName("6. EP: Şifre Uyuşmazlığı")
    void testPasswordMismatch() {
        assertFalse(service.register("Mustafa", "Göktaş", "a@b.com", "01/01/2000", "Pass12345", "Wrong123"));
    }

    @Test @DisplayName("7. EP: Yanlış Tarih Formatı")
    void testInvalidDateFormat() {
        assertFalse(service.register("Mustafa", "Göktaş", "a@b.com", "2000-01-01", "Pass12345", "Pass12345"));
    }

    @Test @DisplayName("8. EP: Boş Soyadı")
    void testEmptyLastName() {
        assertFalse(service.register("Mustafa", "", "a@b.com", "01/01/2000", "Pass12345", "Pass12345"));
    }

    @Test @DisplayName("9. EP: Email Nokta Eksik")
    void testInvalidEmailNoDot() {
        assertFalse(service.register("Mustafa", "Göktaş", "test@mailcom", "01/01/2000", "Pass12345", "Pass12345"));
    }

    @Test @DisplayName("10. EP: Tüm Alanlar Boş")
    void testAllEmpty() {
        assertFalse(service.register("", "", "", "", "", ""));
    }

    @Test @DisplayName("11. BVA: Çok Uzun Şifre")
    void testVeryLongPassword() {
        assertTrue(service.register("Mustafa", "Göktaş", "a@b.com", "01/01/2000", "a".repeat(100), "a".repeat(100)));
    }

    @Test @DisplayName("12. EP: Null Değer Kontrolü")
    void testNullValues() {
        assertFalse(service.register(null, null, null, null, null, null));
    }

    @Test @DisplayName("13. EP: Sayısal İsim")
    void testNumericName() {
        // Not: AccountService'de henüz sayı kontrolü yoksa bu test 'fail' verebilir, bu da bir hata bulduğunuzu gösterir!
        assertTrue(service.register("123", "456", "a@b.com", "01/01/2000", "Pass12345", "Pass12345"));
    }

    @Test @DisplayName("14. BVA: Tarih - Gelecek Zaman")
    void testFutureDate() {
        // Uygulamada tarih kontrolü ekleyerek bunu fail ettirebilirsin
        assertTrue(service.register("Mustafa", "Göktaş", "a@b.com", "01/01/2099", "Pass12345", "Pass12345"));
    }

    @Test @DisplayName("15. BVA: Şifre 9 Karakter")
    void testNineCharPassword() {
        assertTrue(service.register("Mustafa", "Göktaş", "a@b.com", "01/01/2000", "123456789", "123456789"));
    }

    @AfterEach
    void tearDown() { service = null; }
}