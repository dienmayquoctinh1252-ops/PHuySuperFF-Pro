# PHuySuperFF Pro — System Optimizer (Shizuku, No Root)

## Mo trong Android Studio
1. Open -> chon thu muc PHuySuperFF-Pro
2. Sync Gradle (Kotlin 2.1, AGP 8.6, compileSdk 36, minSdk 30)
3. Run tren Android 16
4. Cai app Shizuku (moe.shizuku.privileged.api), bat Shizuku, cap quyen cho app

## Ghi chu
- Chi dung key that: system/secure/global settings put + settings get verify
- Apply All chi chay SystemTweaks.AllSafe
- pm clear nam rieng trong MemoryScreen, confirm 2 lan
- Khong /sys /proc, khong sysctl, khong fake device, khong hack game
- Export: Download/phuy_superff_profile.json qua MediaStore
- Import: ACTION_OPEN_DOCUMENT chon file json
- Log lich su trong Room, hien thi o tab Stats
