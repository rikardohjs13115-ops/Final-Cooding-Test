public class Member {

    // Data pribadi dan informasi member
    private String memberId;
    private String name;
    private String email;
    private String phoneNumber;
    private int registrationYear;
    private String membershipType; // Silver, Gold, Platinum

    // Menghitung total member dan sebagai nomor urut ID
    private static int totalMembers = 0;
    private static int memberCounter = 0;

    // Daftar jenis member yang diizinkan
    private static final String[] VALID_TYPES = {"Silver", "Gold", "Platinum"};

    // Constructor tanpa parameter → memakai nilai default
    public Member() {
        this.memberId = "MBR" + String.format("%03d", ++memberCounter);
        this.name = "";
        this.email = "";
        this.phoneNumber = "";
        this.registrationYear = 2025;
        this.membershipType = "Silver";
        totalMembers++;
    }

    // Constructor dengan parameter langsung isi data member baru
    public Member(String name, String email, String phoneNumber,
                  int registrationYear, String membershipType) {

        // Cek kelengkapan dan format data
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email tidak valid (harus mengandung @ dan .)");
        }
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Nomor telepon harus 10-13 digit");
        }
        if (!isValidYear(registrationYear)) {
            throw new IllegalArgumentException("Tahun registrasi harus antara 2015-2025");
        }
        if (!isValidMembershipType(membershipType)) {
            throw new IllegalArgumentException("Membership type harus Silver/Gold/Platinum");
        }

        // Simpan data jika valid
        this.memberId = "MBR" + String.format("%03d", ++memberCounter);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationYear = registrationYear;
        this.membershipType = membershipType;
        totalMembers++;
    }

    // Pengecekan validitas input data
    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    private boolean isValidPhoneNumber(String phone) {
        if (phone == null) return false;
        String digits = phone.replaceAll("[^0-9]", "");
        return digits.length() >= 10 && digits.length() <= 13;
    }

    private boolean isValidYear(int year) {
        return year >= 2015 && year <= 2025;
    }

    private boolean isValidMembershipType(String type) {
        for (String validType : VALID_TYPES) {
            if (validType.equals(type)) {
                return true;
            }
        }
        return false;
    }

    // Menampilkan informasi lengkap member
    public void displayInfo() {
        System.out.println("[" + memberId + "] " + name);
        System.out.println("Email         : " + email);
        System.out.println("Phone         : " + phoneNumber);
        System.out.println("Membership    : " + membershipType + " " + getMembershipIcon());
        System.out.println("Tahun Daftar  : " + registrationYear);
        System.out.println("Durasi Member : " + getMembershipDuration() + " tahun");
        System.out.println("Batas Pinjam  : " + getMaxBorrowLimit() + " buku");
        System.out.println("Diskon Denda  : " + (int)(getMembershipDiscount() * 100) + "%");
    }

    // Proses kenaikan level membership
    public void upgradeMembership(String newType) {
        if (!isValidMembershipType(newType)) {
            System.out.println("Membership type harus Silver/Gold/Platinum");
            return;
        }

        // Urutan upgrade: Silver → Gold → Platinum
        if (membershipType.equals("Silver")) {
            if (newType.equals("Gold") || newType.equals("Platinum")) {
                this.membershipType = newType;
                System.out.println("✓ " + name + " berhasil di-upgrade dari Silver ke " + newType + "!");
                System.out.println("  Batas Pinjam Baru: " + getMaxBorrowLimit() + " buku | " +
                        "Diskon Denda Baru: " + (int)(getMembershipDiscount() * 100) + "%");
            } else {
                System.out.println("Tidak bisa downgrade membership");
            }
        } else if (membershipType.equals("Gold")) {
            if (newType.equals("Platinum")) {
                this.membershipType = newType;
                System.out.println("✓ " + name + " berhasil di-upgrade dari Gold ke Platinum!");
                System.out.println("  Batas Pinjam Baru: " + getMaxBorrowLimit() + " buku | " +
                        "Diskon Denda Baru: " + (int)(getMembershipDiscount() * 100) + "%");
            } else if (newType.equals("Silver")) {
                System.out.println("Tidak bisa downgrade membership");
            } else {
                System.out.println("Sudah Platinum, tidak bisa upgrade lagi");
            }
        } else if (membershipType.equals("Platinum")) {
            System.out.println("Sudah Platinum, membership tertinggi");
        }
    }

    // Batas maksimal buku yang boleh dipinjam
    public int getMaxBorrowLimit() {
        switch (membershipType) {
            case "Platinum":
                return 10;
            case "Gold":
                return 7;
            case "Silver":
                return 5;
            default:
                return 5;
        }
    }

    // Menghitung sudah berapa tahun terdaftar
    public int getMembershipDuration() {
        return 2025 - registrationYear;
    }

    // Besar diskon denda sesuai level membership
    public double getMembershipDiscount() {
        switch (membershipType) {
            case "Platinum":
                return 0.50;
            case "Gold":
                return 0.30;
            case "Silver":
                return 0.10;
            default:
                return 0.10;
        }
    }

    // Simbol bintang sebagai penanda level member
    private String getMembershipIcon() {
        switch (membershipType) {
            case "Platinum":
                return "⭐⭐⭐";
            case "Gold":
                return "⭐⭐";
            case "Silver":
                return "⭐";
            default:
                return "";
        }
    }

    // Informasi total member yang sudah terdaftar
    public static int getTotalMembers() {
        return totalMembers;
    }

    // Getter & setter untuk akses dan pengubahan data member
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (isValidName(name)) {
            this.name = name;
        } else {
            System.out.println("Nama tidak boleh kosong");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            System.out.println("Email tidak valid");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Nomor telepon harus 10-13 digit");
        }
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(int registrationYear) {
        if (isValidYear(registrationYear)) {
            this.registrationYear = registrationYear;
        } else {
            System.out.println("Tahun registrasi harus antara 2015-2025");
        }
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        if (isValidMembershipType(membershipType)) {
            this.membershipType = membershipType;
        } else {
            System.out.println("Membership type harus Silver/Gold/Platinum");
        }
    }
}
