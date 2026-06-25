# Learning Log - TipApp Development

Dokumen ini mencatat pembelajaran dari perbaikan dan pengembangan aplikasi TipApp.

## 1. Gradle & Dependencies
- **Kotlin DSL (.kts)**: Dalam file `build.gradle.kts`, pemanggilan fungsi seperti `implementation` harus menggunakan tanda kurung `()`. Contoh: `implementation("library-name")`.
- **Compose BOM**: Saat menggunakan Bill of Materials (BOM), kita tidak perlu menuliskan versi untuk library Compose secara manual karena sudah dikelola secara terpusat.
- **Version Catalog**: Menggunakan `libs.versions.toml` adalah cara terbaik untuk mengelola dependensi agar rapi dan terpusat.
- **ProGuard/R8**: `proguardFiles` digunakan untuk *code shrinking* (menghapus kode tidak terpakai), *obfuscation* (mengacak nama class), dan *optimization* agar APK lebih kecil dan aman.

## 2. Troubleshooting Emulator
- **Soft Keyboard**: Jika keyboard tidak muncul, cek apakah **Gboard** aktif di pengaturan Android.
- **Physical Keyboard**: Jika keyboard laptop terdeteksi, Android mungkin menyembunyikan keyboard layar. Gunakan **Cmd + K** (Mac) atau **Ctrl + K** (Win) untuk memunculkannya secara paksa.
- **On-Screen Keyboard Settings**: Pengaturan "Show on-screen keyboard" di menu "Physical Keyboard" memastikan keyboard tetap muncul meskipun keyboard laptop tersambung.

## 3. Debugging & Logcat
- **KeyboardActions**: `Log.d` yang diletakkan di dalam `KeyboardActions` hanya akan terpanggil saat tombol "Enter/Next" ditekan, bukan saat mengetik.
- **Filtering**: Gunakan `tag:NAMA_TAG` (contoh: `tag:AMT`) di Logcat untuk mencari log spesifik dengan cepat.

## 4. Jetpack Compose UI
- **Centering Content**: Secara default, `Card` atau `Surface` meletakkan isinya di pojok kiri atas. Untuk membuat icon center, bungkus dengan `Box` yang memiliki `contentAlignment = Alignment.Center` dan `Modifier.fillMaxSize()`.
- **Modifiers**:
    - `.then()`: Digunakan untuk menggabungkan atau menyambung modifier yang sudah didefinisikan sebelumnya.
    - `onBackground`: Gunakan warna `MaterialTheme.colorScheme.onBackground` untuk teks agar otomatis menyesuaikan dengan tema gelap/terang (hitam di atas putih atau putih di atas hitam).
