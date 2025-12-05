public class Transaction {
    // Data transaksi peminjaman
    private String transactionId;
    private Member member;
    private Book book;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private int daysLate;
    private double lateFee;

    // Data untuk menghitung jumlah transaksi dan ID otomatis
    private static int totalTransactions = 0;
    private static int transactionCounter = 0;
    private static final double LATE_FEE_PER_DAY = 2000.0;

    // Membuat transaksi baru + cek tanggal
    public Transaction(Member member, Book book, String borrowDate, int borrowDurationDays) {
        if (isValidDate(borrowDate)) {
            this.transactionId = generateTransactionId();
            this.member = member;
            this.book = book;
            this.borrowDate = borrowDate;
            this.dueDate = calculateDueDate(borrowDate, borrowDurationDays);
            this.returnDate = null;
            this.daysLate = 0;
            this.lateFee = 0;
            totalTransactions++;
        } else {
            System.out.println("✗ Error: Format tanggal tidak valid (DD-MM-YYYY)");
            this.transactionId = "INVALID";
        }
    }

    // Membuat ID otomatis (TRX001)
    private String generateTransactionId() {
        transactionCounter++;
        return String.format("TRX%03d", transactionCounter);
    }

    // Validasi format tanggal dasar
    private static boolean isValidDate(String date) {
        if (date == null || !date.matches("\\d{2}-\\d{2}-\\d{4}")) return false;

        String[] parts = date.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        return day >= 1 && day <= 31 &&
                month >= 1 && month <= 12 &&
                year >= 1900 && year <= 2025;
    }

    // Hitung tanggal jatuh tempo berdasarkan lama pinjam
    private String calculateDueDate(String borrowDate, int days) {
        String[] parts = borrowDate.split("-");
        int day = Integer.parseInt(parts[0]) + days;
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Perhitungan sederhana
        while (day > 31) {
            day -= 31;
            month++;
        }
        while (month > 12) {
            month -= 12;
            year++;
        }

        return String.format("%02d-%02d-%04d", day, month, year);
    }

    // Mengubah tanggal jadi angka untuk perbandingan sederhana
    private static int dateToDay(String date) {
        String[] parts = date.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return year * 10000 + month * 100 + day;
    }

    // Perkiraan selisih hari
    private static int daysBetween(String date1, String date2) {
        int d1 = dateToDay(date1);
        int d2 = dateToDay(date2);
        return Math.abs(d2 - d1) / 100;
    }

    // Proses pengembalian buku
    public void processReturn(String returnDate) {
        if (!isValidDate(returnDate)) {
            System.out.println("✗ Error: Format tanggal tidak valid (DD-MM-YYYY)");
            return;
        }

        if (dateToDay(returnDate) < dateToDay(borrowDate)) {
            System.out.println("✗ Error: Tanggal kembali tidak boleh sebelum tanggal pinjam");
            return;
        }

        this.returnDate = returnDate;

        if (dateToDay(returnDate) > dateToDay(dueDate)) {
            this.daysLate = daysBetween(dueDate, returnDate);
        } else {
            this.daysLate = 0;
        }

        calculateLateFee();
        book.returnBook();
    }

    // Hitung denda terlambat berdasarkan membership
    public void calculateLateFee() {
        if (daysLate > 0) {
            double discount = 1 - member.getMembershipDiscount();
            this.lateFee = daysLate * LATE_FEE_PER_DAY * discount;
        } else {
            this.lateFee = 0;
        }
    }

    // Mengecek apakah masih dipinjam dan sudah lewat jatuh tempo
    public boolean isOverdue(String currentDate) {
        if (returnDate != null) return false;
        return dateToDay(currentDate) > dateToDay(dueDate);
    }

    // Status transaksi (Aktif, Terlambat, atau Selesai)
    public String getTransactionStatus() {
        if (returnDate != null) {
            return "Selesai";
        }

        String currentDate = "05-12-2025"; // contoh tanggal hari ini
        if (isOverdue(currentDate)) {
            return "Terlambat";
        }

        return "Aktif";
    }

    // Total transaksi tercatat
    public static int getTotalTransactions() {
        return totalTransactions;
    }

    // -------- Getter --------
    public String getTransactionId() { return transactionId; }
    public Member getMember() { return member; }
    public Book getBook() { return book; }
    public String getBorrowDate() { return borrowDate; }
    public String getDueDate() { return dueDate; }
    public String getReturnDate() { return returnDate; }
    public int getDaysLate() { return daysLate; }
    public double getLateFee() { return lateFee; }
}
