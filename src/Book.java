/**
 * CLASS BOOK
 * Mewakili data buku yang ada di perpustakaan
 */
public class Book {
    // Data khusus setiap buku (hanya dapat diakses melalui class ini)
    private String bookId;
    private String title;
    private String author;
    private String category;
    private int publicationYear;
    private boolean isAvailable;
    private int totalCopies;
    private int availableCopies;

    // Data bersama untuk menghitung jumlah buku dan ID otomatis
    private static int totalBooks = 0;
    private static int bookCounter = 0;

    // Daftar kategori yang diperbolehkan
    private static final String[] VALID_CATEGORIES = {
            "Fiction", "Non-Fiction", "Science", "Technology", "History"
    };

    // Constructor saat tidak ada data awal (ID dibuat otomatis)
    public Book() {
        this.bookId = "BK" + String.format("%03d", ++bookCounter);
        this.title = "";
        this.author = "";
        this.category = "Fiction";
        this.publicationYear = 2025;
        this.isAvailable = true;
        this.totalCopies = 1;
        this.availableCopies = 1;
        totalBooks++;
    }

    // Constructor dengan data langsung + pengecekan validasi
    public Book(String title, String author, String category,
                int publicationYear, int totalCopies) {

        if (!isValidTitle(title)) {
            throw new IllegalArgumentException("Error: Judul buku tidak boleh kosong");
        }
        if (!isValidAuthor(author)) {
            throw new IllegalArgumentException("Error: Nama penulis tidak boleh kosong");
        }
        if (!isValidCategory(category)) {
            throw new IllegalArgumentException("Error: Kategori harus Fiction/Non-Fiction/Science/Technology/History");
        }
        if (!isValidPublicationYear(publicationYear)) {
            throw new IllegalArgumentException("Error: Tahun terbit tidak valid (1900-2025)");
        }
        if (!isValidCopies(totalCopies)) {
            throw new IllegalArgumentException("Error: Total copies harus >= 1");
        }

        this.bookId = "BK" + String.format("%03d", ++bookCounter);
        this.title = title;
        this.author = author;
        this.category = category;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        this.isAvailable = totalCopies > 0;
        totalBooks++;
    }

    // Pengecekan judul
    private boolean isValidTitle(String title) {
        return title != null && !title.trim().isEmpty();
    }

    // Pengecekan penulis
    private boolean isValidAuthor(String author) {
        return author != null && !author.trim().isEmpty();
    }

    // Pengecekan kategori sesuai daftar kategori
    private boolean isValidCategory(String category) {
        for (String valid : VALID_CATEGORIES) {
            if (valid.equals(category)) {
                return true;
            }
        }
        return false;
    }

    // Pengecekan tahun terbit
    private boolean isValidPublicationYear(int year) {
        return year >= 1900 && year <= 2025;
    }

    // Pengecekan jumlah copy
    private boolean isValidCopies(int copies) {
        return copies >= 1;
    }

    // Pengecekan jumlah copy tersedia
    private boolean isValidAvailableCopies(int copies) {
        return copies >= 0 && copies <= totalCopies;
    }

    // Menampilkan seluruh informasi buku
    public void displayBookInfo() {
        System.out.println("[" + bookId + "] " + title);
        System.out.println("Penulis       : " + author);
        System.out.println("Kategori      : " + category);
        System.out.println("Tahun Terbit  : " + publicationYear);
        System.out.println("Umur Buku     : " + getBookAge() + " tahun");
        System.out.println("Total Copy    : " + totalCopies + " eksemplar");
        System.out.println("Tersedia      : " + availableCopies + " eksemplar | Status: " +
                getAvailabilityStatus() + (isNewRelease() ? " [NEW RELEASE]" : ""));
    }

    // Proses peminjaman (jika masih tersedia)
    public boolean borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
            if (availableCopies == 0) {
                isAvailable = false;
            }
            return true;
        }
        return false;
    }

    // Mengembalikan buku yang dipinjam
    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            if (availableCopies > 0) {
                isAvailable = true;
            }
        }
    }

    // Hitung usia buku
    public int getBookAge() {
        return 2025 - publicationYear;
    }

    // Cek apakah buku tergolong rilisan baru (≤ 2 tahun)
    public boolean isNewRelease() {
        return getBookAge() <= 2;
    }

    // Status stok buku
    public String getAvailabilityStatus() {
        if (availableCopies > 5) {
            return "Banyak Tersedia ✓";
        } else if (availableCopies >= 1 && availableCopies <= 5) {
            return "Terbatas ⚠";
        } else {
            return "Tidak Tersedia ✗";
        }
    }

    // Total buku yang sudah tercatat
    public static int getTotalBooks() {
        return totalBooks;
    }

    // Getter & Setter
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (isValidTitle(title)) {
            this.title = title;
        } else {
            System.out.println("✗ Error: Judul buku tidak boleh kosong");
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (isValidAuthor(author)) {
            this.author = author;
        } else {
            System.out.println("✗ Error: Nama penulis tidak boleh kosong");
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (isValidCategory(category)) {
            this.category = category;
        } else {
            System.out.println("✗ Error: Kategori harus Fiction/Non-Fiction/Science/Technology/History");
        }
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        if (isValidPublicationYear(publicationYear)) {
            this.publicationYear = publicationYear;
        } else {
            System.out.println("✗ Error: Tahun terbit tidak valid (1900-2025)");
        }
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        this.isAvailable = available;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        if (isValidCopies(totalCopies)) {
            this.totalCopies = totalCopies;
        } else {
            System.out.println("✗ Error: Total copies harus >= 1");
        }
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        if (isValidAvailableCopies(availableCopies)) {
            this.availableCopies = availableCopies;
            this.isAvailable = availableCopies > 0;
        } else {
            System.out.println("✗ Error: availableCopies harus 0-" + totalCopies);

        }}
}
