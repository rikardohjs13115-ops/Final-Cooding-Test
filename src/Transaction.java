public class Transaction {
    // Data khusus tiap transaksi peminjaman
    private String transactionId;
    private Member member;
    private Book book;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private int daysLate;
    private double lateFee;

    // Variabel yang berlaku untuk seluruh transaksi
    private static int totalTransactions = 0;
    private static int transactionCounter = 0;
    private static final double LATE_FEE_PER_DAY = 2000.0;

    // Membuat transaksi baru jika tanggal valid
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

    // Membuat ID transaksi secara otomatis (TRX001, TRX002, ...)
    private String generateTransactionId() {
        transactionCounter++;
        return String.format("TRX%03d", transactionCounter);
    }

    // Mengecek apakah format tanggal benar (DD-MM-YYYY)
    private static boolean isValidDate(String date) {
        if (date == null || !date.matches("\\d{2}-\\d{2}-\\d{4}")) return false;

        String[] parts = date.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        return day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 1900 && year <= 2025;
    }

    // Hitung tanggal jatuh tempo peminjaman
    private String calculateDueDate(String borrowDate, int days) {
        String[] parts = borrowDate.split("-");
        int day = Integer.parseInt(parts[0]) + days;
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Penyesuaian jika lewat bulan atau tahun
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

    // Mengubah tanggal jadi angka untuk perhitungan
    private static long dateToDay(String date) {
        String[] parts = date.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        return (long) year * 365 + month * 30 + day;
    }

    // Hitung selisih hari antara dua tanggal
    private static int daysBetween(String date1, String date2) {
        long d1 = dateToDay(date1);
        long d2 = dateToDay(date2);
        return (int) Math.abs(d2 - d1);
    }

    // Hitung berapa hari telat
    private void calculateDaysLate() {
        if (dateToDay(returnDate) > dateToDay(dueDate)) {
            this.daysLate = daysBetween(dueDate, returnDate);
        } else {
            this.daysLate = 0;
        }
    }

    // Hitung denda berdasarkan telat dan diskon member
    private void calculateLateFee() {
        if (daysLate > 0) {
            double discount = 1 - member.getMembershipDiscount();
            this.lateFee = daysLate * LATE_FEE_PER_DAY * discount;
        } else {
            this.lateFee = 0;
        }
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
        calculateDaysLate();
        calculateLateFee();
        book.returnBook();
    }

    // Mengecek apakah buku masih belum dikembalikan dan sudah lewat jatuh tempo
    public boolean isOverdue(String currentDate) {
        if (returnDate != null) {
            return false;
        }
        return dateToDay(currentDate) > dateToDay(dueDate);
    }

    // Status transaksi saat ini
    public String getTransactionStatus() {
        if (returnDate != null) {
            if (daysLate > 0) return "Selesai (Terlambat)";
            return "Selesai";
        }

        String currentDate = "05-12-2025"; // contoh tanggal pengecekan
        if (isOverdue(currentDate)) return "Terlambat";

        return "Aktif";
    }

    // Mendapatkan total seluruh transaksi
    public static int getTotalTransactions() {
        return totalTransactions;
    }

    // Getter untuk atribut penting transaksi
    public String getTransactionId() { return transactionId; }
    public Member getMember() { return member; }
    public Book getBook() { return book; }
    public String getBorrowDate() { return borrowDate; }
    public String getDueDate() { return dueDate; }
    public String getReturnDate() { return returnDate; }
    public int getDaysLate() { return daysLate; }
    public double getLateFee() { return lateFee; }
}
