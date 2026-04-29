# Quick Start: Multi-User ApexRise

## What Was Added

Your ApexRise app now supports **4 concurrent users** with real-time data sync, offline mode, and automatic conflict resolution!

### Key Features:
✅ **Multi-user login** - Each of 4 farm members logs in  
✅ **Automatic sync** - Every 30 minutes (or manually)  
✅ **Offline mode** - Works without internet  
✅ **Conflict resolution** - User prompted to pick version  
✅ **Journey tracking** - Timestamps on all data  

---

## 3-Step Implementation

### Step 1: Set Up Backend (Spring Boot + PostgreSQL)

Follow [BACKEND_SETUP.md](BACKEND_SETUP.md) to create backend server.

**TL;DR:**
```bash
# Create PostgreSQL database
createdb apexrise_db

# Create Spring Boot project from BACKEND_SETUP.md
mvn spring-boot:run  # Runs on localhost:8080
```

### Step 2: Update App Backend URL

In `ApexRiseApplication.kt` (~line 20), change:

```kotlin
NetworkClient.initialize(
    context = this,
    baseUrl = "http://10.0.2.2:8080/api/"  // ← Change if not local
)
```

### Step 3: Add Login Navigation

In your main navigation, check if user is logged in:

```kotlin
// At app startup
val isLoggedIn = NetworkClient.getTokenManager().isLoggedIn()

if (isLoggedIn) {
    // Show main app
    MainApp()
} else {
    // Show login screen
    LoginScreen(
        onLoginSuccess = { /* go to main */ },
        onNavigateToRegister = { /* show register */ },
        viewModel = AuthViewModel()
    )
}
```

---

## Files Added

### Network Layer
- `network/models.kt` - API request/response models
- `network/ApexRiseApi.kt` - Retrofit service
- `network/TokenManager.kt` - JWT storage & management
- `network/NetworkClient.kt` - Network initialization

### Sync & Repository
- `data/repository/SyncRepository.kt` - Sync logic
- `worker/SyncWorker.kt` - Background sync (30 min interval)

### UI Screens
- `ui/screens/AuthScreens.kt` - Login & Register
- `ui/screens/AuthViewModel.kt` - Auth logic
- `ui/screens/ConflictResolutionScreen.kt` - Conflict UI
- `ui/screens/SyncStatusScreen.kt` - Sync status dashboard

### Database
- Updated `CowEntity`, `MilkRecordEntity`, etc. - Added sync tracking fields
- `ApexRiseDatabase.kt` - Migration v1→v2

### Documentation
- `BACKEND_SETUP.md` - Complete backend setup guide
- `MULTIUSER_IMPLEMENTATION.md` - Full technical details

---

## How Multi-User Works

### User 1 (Phone) → Backend → User 2 (Tablet)

1. User 1 adds a cow locally → saved in SQLite
2. Every 30 min, WorkManager wakes up → syncs to server
3. Server merges with other users' changes
4. User 2's app syncs → sees new cow
5. If conflict (both users edited same field):
   - User 2 sees "Resolve Conflicts" screen
   - Picks "Local" or "Server" version
   - Conflict sent to server → resolved

### Offline Mode

- User works offline → changes saved locally
- WiFi turns on → automatic sync within 30 min
- No data loss

### Periodic Sync (30 minutes)

- Scheduled via WorkManager
- Runs even if app closed
- Battery optimized
- Can manually trigger anytime

---

## Testing Multi-User

### Test with 2 Devices:

1. **Device A:** Login as user1@farm.com
2. **Device B:** Login as user2@farm.com
3. **Device A:** Add a new cow → Save
4. **Device A:** Manually sync (Sync Status screen)
5. **Device B:** Wait 30 sec → refresh → See new cow ✓

### Test Offline Mode:

1. Add expense on Device A
2. Turn off WiFi on Device A
3. Add another expense
4. Turn on WiFi → App syncs automatically ✓

### Test Conflict Resolution:

1. Add milk record on Device A (cow_id=1)
2. Simultaneously on Device B (without syncing): Edit same record
3. Device A syncs first → wins
4. Device B syncs → gets conflict resolution screen ✓

---

## API Endpoints You Need to Build

```
POST /auth/login
    Request: { email, password }
    Response: { token, userId, email }

POST /auth/register
    Request: { email, password, farmName }
    Response: { token, userId, email }

POST /sync
    Header: Authorization: Bearer {jwt_token}
    Request: { userId, lastSyncTime, localChanges }
    Response: { success, conflicts[], serverData }

POST /sync/resolve-conflict
    Header: Authorization: Bearer {jwt_token}
    Request: { conflictId, resolution }
    Response: { success }
```

See `BACKEND_SETUP.md` for full database schema.

---

## Dependencies Added

```gradle
// Retrofit & Networking
implementation(libs.retrofit)
implementation(libs.retrofit.moshi)
implementation(libs.okhttp.logging)

// Moshi (JSON serialization)
implementation(libs.moshi)
kapt(libs.moshi.codegen)

// WorkManager (Background sync)
implementation(libs.workmanager)

// DataStore (Token storage)
implementation(libs.datastore.preferences)
```

---

## Gradle Build Info

- **Java 11** (unchanged)
- **Compose** (unchanged)
- **Room** v2.6.1 (unchanged)
- **Coroutines** (unchanged)
- **New:** Retrofit 2.11, OkHttp 4.12, WorkManager 2.9, DataStore 1.0

**Build the app:**
```bash
./gradlew build
./gradlew installDebug
```

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| "Connection refused" | Backend not running on localhost:8080 |
| "401 Unauthorized" | JWT token expired - need to re-login |
| "Sync not happening" | Check WorkManager in Settings > Developer Options |
| "Conflicts every sync" | Server timestamp handling issue - check backend logic |
| "Offline mode not working" | App should work offline by default - check internet toggle |

---

## Next: Backend Implementation

Now you need to build the Spring Boot backend! 

**See:** [BACKEND_SETUP.md](BACKEND_SETUP.md)

Key backend tasks:
1. Create Spring Boot app with PostgreSQL
2. Implement 4 API endpoints
3. Add conflict detection logic
4. Deploy to server

---

## Architecture Diagram

```
┌─ Device 1 ─┐     ┌─ Backend ─┐     ┌─ Device 2 ─┐
│ (User A)   │────→│(PostgreSQL)│←────│ (User B)   │
│ SQLite DB  │←────│ Sync Logic │────→│ SQLite DB  │
└────────────┘     └────────────┘     └────────────┘
     ↓ (local)          ↓                   ↓ (local)
  Changes queued    Merge & Detect    Changes queued
  Offline OK        Conflicts         Offline OK
```

---

**Status: ✅ Android app ready for backend integration**

Need help? Check [MULTIUSER_IMPLEMENTATION.md](MULTIUSER_IMPLEMENTATION.md) for technical details.
