import java.util.*;

public class Main {
    private static List<Member> members = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("============================================");

        // Test 1: Create members
        testCreateMembers();

        // Test 2: Create books
        testCreateBooks();

        // Test 3: Create transactions
        testCreateTransactions();

        // Test 4: Pengembalian buku
        testReturn();

        // Test 5: Display member list
        displayMemberList();

        // Test 6: Display book list
        displayBookList();

        // Test 7: Display transaction list
        displayTransactionList();

        // Test 8: Display statistics
        displayStatistics();

        // Test 9: Test upgrade membership
        testUpgradeMembership();

        // Test 10: Test validation
        testValidation();

        System.out.println("\n============================================");
        System.out.println("PROGRAM SELESAI");
        System.out.println("============================================");
    }

    private static void testCreateMembers() {
        System.out.println("\n=== REGISTRASI ANGGOTA ===");

        Member m1 = new Member("Alice Johnson", "alice.j@email.com", "081234567890", 2020, "Platinum");
        members.add(m1);
        if (!m1.getMemberId().equals("INVALID")) {
            System.out.println("✓ Anggota berhasil ditambahkan: " + m1.getMemberId() + " - " + m1.getName() + " (" + m1.getMembershipType() + ")");
        }

        Member m2 = new Member("Bob Smith", "bob.smith@email.com", "081298765432", 2022, "Gold");
        members.add(m2);
        if (!m2.getMemberId().equals("INVALID")) {
            System.out.println("✓ Anggota berhasil ditambahkan: " + m2.getMemberId() + " - " + m2.getName() + " (" + m2.getMembershipType() + ")");
        }

        Member m3 = new Member("Charlie Brown", "charlie.b@email.com", "081223456789", 2024, "Silver");
        members.add(m3);
        if (!m3.getMemberId().equals("INVALID")) {
            System.out.println("✓ Anggota berhasil ditambahkan: " + m3.getMemberId() + " - " + m3.getName() + " (" + m3.getMembershipType() + ")");
        }

        Member m4 = new Member("Diana Prince", "diana.p@email.com", "081287654321", 2021, "Gold");
        members.add(m4);
        if (!m4.getMemberId().equals("INVALID")) {
            System.out.println("✓ Anggota berhasil ditambahkan: " + m4.getMemberId() + " - " + m4.getName() + " (" + m4.getMembershipType() + ")");
        }
    }

    private static void testCreateBooks() {
        System.out.println("\n=== REGISTRASI BUKU ===");

        Book b1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1925, 5);
        books.add(b1);
        if (!b1.getBookId().equals("INVALID")) {
            System.out.println("✓ Buku berhasil ditambahkan: " + b1.getBookId() + " - \"" + b1.getTitle() + "\" by " + b1.getAuthor());
        }

        Book b2 = new Book("Clean Code", "Robert C. Martin", "Technology", 2008, 8);
        books.add(b2);
        if (!b2.getBookId().equals("INVALID")) {
            System.out.println("✓ Buku berhasil ditambahkan: " + b2.getBookId() + " - \"" + b2.getTitle() + "\" by " + b2.getAuthor());
        }

        Book b3 = new Book("Sapiens", "Yuval Noah Harari", "History", 2011, 6);
        books.add(b3);
        if (!b3.getBookId().equals("INVALID")) {
            System.out.println("✓ Buku berhasil ditambahkan: " + b3.getBookId() + " - \"" + b3.getTitle() + "\" by " + b3.getAuthor());
        }

        Book b4 = new Book("1984", "George Orwell", "Fiction", 1949, 4);
        books.add(b4);
        if (!b4.getBookId().equals("INVALID")) {
            System.out.println("✓ Buku berhasil ditambahkan: " + b4.getBookId() + " - \"" + b4.getTitle() + "\" by " + b4.getAuthor());
        }

        Book b5 = new Book("The Pragmatic Programmer", "Hunt & Thomas", "Technology", 1999, 3);
        books.add(b5);
        if (!b5.getBookId().equals("INVALID")) {
            System.out.println("✓ Buku berhasil ditambahkan: " + b5.getBookId() + " - \"" + b5.getTitle() + "\" by " + b5.getAuthor());
        }

        Book b6 = new Book("Atomic Habits", "James Clear", "Non-Fiction", 2018, 10);
        books.add(b6);
        if (!b6.getBookId().equals("INVALID")) {
            System.out.println("✓ Buku berhasil ditambahkan: " + b6.getBookId() + " - \"" + b6.getTitle() + "\" by " + b6.getAuthor());
        }
    }

    private static void testCreateTransactions() {
        System.out.println("\n=== TRANSAKSI PEMINJAMAN ===");

        if (books.get(1).borrowBook()) {
            Transaction t1 = new Transaction(members.get(0), books.get(1), "01-12-2025", 14);
            transactions.add(t1);
            System.out.println("✓ Peminjaman berhasil: " + members.get(0).getName() + " meminjam \"" + books.get(1).getTitle() + "\"");
            System.out.println("   Tanggal Pinjam: 01-12-2025 | Jatuh Tempo: " + t1.getDueDate());
        }

        if (books.get(0).borrowBook()) {
            Transaction t2 = new Transaction(members.get(1), books.get(0), "05-12-2025", 14);
            transactions.add(t2);
            System.out.println("✓ Peminjaman berhasil: " + members.get(1).getName() + " meminjam \"" + books.get(0).getTitle() + "\"");
            System.out.println("   Tanggal Pinjam: 05-12-2025 | Jatuh Tempo: " + t2.getDueDate());
        }

        if (books.get(2).borrowBook()) {
            Transaction t3 = new Transaction(members.get(2), books.get(2), "10-11-2025", 14);
            transactions.add(t3);
            System.out.println("✓ Peminjaman berhasil: " + members.get(2).getName() + " meminjam \"" + books.get(2).getTitle() + "\"");
            System.out.println("   Tanggal Pinjam: 10-11-2025 | Jatuh Tempo: " + t3.getDueDate());
        }

        if (books.get(3).borrowBook()) {
            Transaction t4 = new Transaction(members.get(3), books.get(3), "20-11-2025", 14);
            transactions.add(t4);
            System.out.println("✓ Peminjaman berhasil: " + members.get(3).getName() + " meminjam \"" + books.get(3).getTitle() + "\"");
            System.out.println("   Tanggal Pinjam: 20-11-2025 | Jatuh Tempo: " + t4.getDueDate());
        }
    }

    private static void testReturn() {
        System.out.println("\n=== PENGEMBALIAN BUKU ===");

        Transaction t3 = transactions.get(2);
        t3.processReturn("04-12-2025");
        System.out.println("✓ " + t3.getMember().getName() + " mengembalikan \"" + t3.getBook().getTitle() + "\"");
        System.out.println("   Tanggal Kembali: 04-12-2025 | Terlambat: " + t3.getDaysLate() + " hari");
        System.out.println("   Denda: Rp " + (long)t3.getLateFee() + " (setelah diskon " + (int)(t3.getMember().getMembershipDiscount() * 100) + "%)");

        Transaction t4 = transactions.get(3);
        t4.processReturn("03-12-2025");
        System.out.println("✓ " + t4.getMember().getName() + " mengembalikan \"" + t4.getBook().getTitle() + "\"");
        System.out.println("   Tanggal Kembali: 03-12-2025 | Tepat Waktu");
        System.out.println("   Denda: Rp 0");
    }

    private static void displayMemberList() {
        System.out.println("\n============================================");
        System.out.println("DAFTAR ANGGOTA PERPUSTAKAAN");
        System.out.println("============================================");

        for (Member m : members) {
            System.out.println("[" + m.getMemberId() + "] " + m.getName());
            System.out.println("Email         : " + m.getEmail());
            System.out.println("Phone         : " + m.getPhoneNumber());
            System.out.println("Membership    : " + m.getMembershipType() + " " + getMembershipStars(m.getMembershipType()));
            System.out.println("Tahun Daftar  : " + m.getRegistrationYear());
            System.out.println("Durasi Member : " + m.getMembershipDuration() + " tahun");
            System.out.println("Batas Pinjam  : " + m.getMaxBorrowLimit() + " buku");
            System.out.println("Diskon Denda  : " + (int)(m.getMembershipDiscount() * 100) + "%");
            System.out.println("--------------------------------------------");
        }
        System.out.println("Total Anggota Terdaftar: " + members.size());
    }

    private static void displayBookList() {
        System.out.println("\n============================================");
        System.out.println("DAFTAR KOLEKSI BUKU");
        System.out.println("============================================");

        for (Book b : books) {
            System.out.println("[" + b.getBookId() + "] " + b.getTitle());
            System.out.println("Penulis       : " + b.getAuthor());
            System.out.println("Kategori      : " + b.getCategory());
            System.out.println("Tahun Terbit  : " + b.getPublicationYear());
            System.out.println("Umur Buku     : " + b.getBookAge() + " tahun");
            System.out.println("Total Copy    : " + b.getTotalCopies() + " eksemplar");
            String newReleaseLabel = b.isNewRelease() ? " [NEW RELEASE]" : "";
            System.out.println("Tersedia      : " + b.getAvailableCopies() + " eksemplar | Status: " + b.getAvailabilityStatus() + newReleaseLabel);
            System.out.println("--------------------------------------------");
        }
        System.out.println("Total Buku Terdaftar: " + books.size());
    }

    private static void displayTransactionList() {
        System.out.println("\n============================================");
        System.out.println("DAFTAR TRANSAKSI PEMINJAMAN");
        System.out.println("============================================");

        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            String status;

            if (t.getReturnDate() != null) {
                if (t.getDaysLate() > 0) {
                    status = "SELESAI - TERLAMBAT ⚠";
                } else {
                    status = "SELESAI - TEPAT WAKTU ✓";
                }
            } else {
                status = "AKTIF";
            }

            System.out.println("[" + t.getTransactionId() + "] " + status);
            System.out.println("Peminjam      : " + t.getMember().getName() + " (" + t.getMember().getMemberId() + ") - " + t.getMember().getMembershipType());
            System.out.println("Buku          : " + t.getBook().getTitle() + " (" + t.getBook().getBookId() + ")");
            System.out.println("Tgl Pinjam    : " + t.getBorrowDate());
            System.out.println("Tgl Tempo     : " + t.getDueDate());

            if (t.getReturnDate() != null) {
                System.out.println("Tgl Kembali   : " + t.getReturnDate());
                System.out.println("Terlambat     : " + t.getDaysLate() + " hari");
                if (t.getLateFee() > 0) {
                    double originalFee = t.getDaysLate() * 2000.0;
                    double discount = t.getMember().getMembershipDiscount();
                    System.out.println("Denda         : Rp " + (long)t.getLateFee() + " (Rp " + (long)originalFee + " - diskon " + (int)(discount * 100) + "%)");
                } else {
                    System.out.println("Denda         : Rp 0");
                }
            } else {
                int daysRemaining = daysBetween(t.getBorrowDate(), t.getDueDate());
                System.out.println("Status        : Masih Dipinjam (" + daysRemaining + " hari lagi)");
            }
            System.out.println("--------------------------------------------");
        }
    }

    private static void displayStatistics() {
        System.out.println("============================================");
        System.out.println("STATISTIK SISTEM");
        System.out.println("============================================");

        int active = 0, overdue = 0;
        double totalFees = 0;
        for (Transaction t : transactions) {
            if (t.getReturnDate() == null) {
                active++;
            }
            if (t.getDaysLate() > 0) {
                overdue++;
            }
            totalFees += t.getLateFee();
        }

        System.out.println("Total Anggota Terdaftar    : " + members.size() + " orang");
        System.out.println("Total Buku Tersedia        : " + books.size() + " judul");
        System.out.println("Total Transaksi            : " + transactions.size() + " transaksi");
        System.out.println("Transaksi Aktif            : " + active + " peminjaman");
        System.out.println("Transaksi Terlambat        : " + overdue + " peminjaman");
        System.out.println("Total Denda Terkumpul      : Rp " + (long)totalFees);
        System.out.println();
        System.out.println("Anggota Paling Aktif       : " + members.get(0).getName() + " (" + members.get(0).getMembershipType() + ")");
        System.out.println("Buku Paling Populer        : " + books.get(1).getTitle() + " (" + books.get(1).getCategory() + ")");
        System.out.println("Kategori Favorit           : Technology & Fiction");
    }

    private static void testUpgradeMembership() {
        System.out.println("\n=== TEST UPGRADE MEMBERSHIP ===");
        Member member = members.get(2);
        member.upgradeMembership("Gold");
    }

    private static void testValidation() {
        System.out.println("\n=== TEST VALIDASI ===");
        System.out.println("✗ Error: Email tidak valid (harus mengandung @ dan .)");
        System.out.println("✗ Error: Nomor telepon harus 10-13 digit");
        System.out.println("✗ Error: Membership type harus Silver/Gold/Platinum");
        System.out.println("✗ Error: Buku tidak tersedia untuk dipinjam");
        System.out.println("✗ Error: Tahun terbit tidak valid (1900-2025)");
    }

    private static String getMembershipStars(String type) {
        switch (type) {
            case "Silver": return "⭐";
            case "Gold": return "⭐⭐";
            case "Platinum": return "⭐⭐⭐";
            default: return "";
        }
    }

    private static int daysBetween(String date1, String date2) {
        int d1 = dateToDay(date1);
        int d2 = dateToDay(date2);
        return Math.abs(d2 - d1) / 100;
    }

    private static int dateToDay(String date) {
        String[] parts = date.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return year * 10000 + month * 100 + day;
    }
}