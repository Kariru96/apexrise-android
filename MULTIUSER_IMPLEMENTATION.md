# Multi-User ApexRise Implementation Guide

## Overview
Your ApexRise Android app has been updated to support multi-user sync with 4 concurrent users, periodic sync every 30 minutes, offline mode, and automatic conflict resolution with user prompts.

## ✅ Android Implementation Complete

### 1. **Network Layer**
- ✅ Added Retrofit + OkHttp for API communication
- ✅ JWT token-based authentication
- ✅ API models for sync requests/responses
- ✅ Token storage with DataStore (Preferences)
- ✅ Authentication interceptor for API requests

**Files created:**
- `network/models.kt` - All API DTOs
- `network/ApexRiseApi.kt` - Retrofit service
- `network/TokenManager.kt` - JWT token management
- `network/NetworkClient.kt` - Network initialization

### 2. **Database Migration**
- ✅ Added `timestamp` field to track last modification
- ✅ Added `lastSyncedAt` field to mark synced records
- ✅ Database migration from v1 → v2
- ✅ Migration applied automatically on app startup

**Updated entities:**
- `CowEntity` - Tracks when each cow was added/modified
- `MilkRecordEntity` - Sync tracking for milk records
- `WakulimaSaleEntity` - Sync tracking for sales
- `ExpenseEntity` - Sync tracking for expenses

### 3. **Sync Repository**
- ✅ `SyncRepository.kt` - Core sync logic
- ✅ Collects unsynced changes from local database
- ✅ Sends changes to backend
- ✅ Receives server updates and merges locally
- ✅ Handles conflicts for user resolution
- ✅ Marks records as synced

**Key methods:**
- `syncWithServer()` - Main sync orchestration
- `getLocalChanges()` - Gather unsync changes
- `updateLocalDatabase()` - Merge server data
- `markSynced()` - Update sync timestamps

### 4. **Periodic Sync**
- ✅ `SyncWorker.kt` - Background sync task (WorkManager)
- ✅ Automatic sync every 30 minutes (configurable)
- ✅ Retry on failure
- ✅ Works even in background

**Usage:**
```kotlin
// Schedule sync (call in MainActivity or app startup)
SyncWorker.scheduleSyncWork(context)

// Cancel sync if needed
SyncWorker.cancelSyncWork(context)
```

### 5. **Authentication Screens**
- ✅ `AuthScreens.kt` - Login & Registration UI
- ✅ `AuthViewModel.kt` - Authentication logic
- Email/password login
- User registration with farm name
- Error handling and loading states

### 6. **Conflict Resolution**
- ✅ `ConflictResolutionScreen.kt` - User-friendly conflict UI
- ✅ Shows local vs server data side-by-side
- ✅ User picks which version to keep
- ✅ Resolves one conflict at a time

### 7. **Sync Status Dashboard**
- ✅ `SyncStatusScreen.kt` - Sync monitoring
- ✅ Shows user account info
- ✅ Last sync time
- ✅ Manual sync button
- ✅ Multi-user info display
- ✅ Logout functionality

---

## 🔧 Backend Setup Required

You need to create a Spring Boot backend. A complete setup guide is in `BACKEND_SETUP.md`.

### Quick Backend Setup Steps:

1. **Clone or create Spring Boot project**
   ```bash
   mkdir apexrise-backend
   cd apexrise-backend
   ```

2. **Copy `pom.xml` from BACKEND_SETUP.md** and run:
   ```bash
   mvn clean package
   ```

3. **Setup PostgreSQL:**
   ```sql
   CREATE DATABASE apexrise_db;
   CREATE USER apexrise_user WITH PASSWORD 'your_password';
   GRANT ALL PRIVILEGES ON DATABASE apexrise_db TO apexrise_user;
   ```

4. **Create backend source structure** (see BACKEND_SETUP.md)

5. **Implement these endpoints:**
   - `POST /auth/login` - Authenticate user
   - `POST /auth/register` - Create new account
   - `POST /sync` - Sync data
   - `POST /sync/resolve-conflict` - Resolve conflicts

6. **Run backend:**
   ```bash
   mvn spring-boot:run
   ```

---

## 🚀 Android Integration Steps

### 1. **Update Network URL**
In `ApexRiseApplication.kt`, update the backend URL:

```kotlin
NetworkClient.initialize(
    context = this,
    baseUrl = "http://YOUR_BACKEND_URL/api/"  // Change this
)
```

- **Local development:** `http://10.0.2.2:8080/api/`
- **Remote server:** `https://your-domain.com/api/`

### 2. **Add Permissions** (AndroidManifest.xml)
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

### 3. **Initialize Sync on App Startup**
In your MainActivity or App startup:

```kotlin
SyncWorker.scheduleSyncWork(context)
```

### 4. **Navigate to Auth Screens**
Update your navigation to show `LoginScreen` first if user is not logged in:

```kotlin
// Check if user is logged in
val tokenManager = NetworkClient.getTokenManager()
val isLoggedIn = tokenManager.isLoggedIn()

if (isLoggedIn) {
    // Show main app
    MainApp()
} else {
    // Show login screen
    LoginScreen(
        onLoginSuccess = { /* navigate to main app */ },
        onNavigateToRegister = { /* show register screen */ },
        viewModel = authViewModel
    )
}
```

### 5. **Handle Conflicts in UI**
When sync returns conflicts, show the resolution screen:

```kotlin
val syncResult = syncRepository.syncWithServer()
if (syncResult is SyncResult.Success && syncResult.conflicts.isNotEmpty()) {
    ConflictResolutionScreen(
        conflicts = syncResult.conflicts,
        onConflictsResolved = { /* refresh UI */ },
        onResolveConflict = { conflict, resolution ->
            // Send resolution to server
            api.resolveConflict(resolution)
        }
    )
}
```

---

## 📋 Multi-User Features Implemented

### Feature: **4 Concurrent Users**
- ✅ Each user logs in with email/password
- ✅ JWT token ensures secure authentication
- ✅ User ID tracked in tokens and on server

### Feature: **Periodic Sync (30 minutes)**
- ✅ Automatic background sync via WorkManager
- ✅ No user action required
- ✅ Interval can be changed (see `SyncWorker.kt`)

### Feature: **Offline Mode**
- ✅ All data stored locally in Room database
- ✅ Changes queued locally when offline
- ✅ Automatic sync when connection returns
- ✅ App remains fully functional without internet

### Feature: **Conflict Resolution**
- ✅ User prompted when conflicts exist
- ✅ Shows local vs server versions side-by-side
- ✅ User selects "Keep Local" or "Keep Server"
- ✅ Automatic conflict detection on sync

### Feature: **Data Sync**
- ✅ All entities synced: Cows, Milk Records, Wakulima Sales, Expenses
- ✅ Bidirectional sync (send + receive)
- ✅ Timestamp tracking prevents duplicate conflicts
- ✅ Efficient sync (only unsync changes sent)

---

## 🔌 API Endpoints (Backend must implement)

### Authentication
```
POST /auth/login
Request: { email, password }
Response: { token, userId, email }

POST /auth/register
Request: { email, password, farmName }
Response: { token, userId, email }
```

### Sync
```
POST /sync
Header: Authorization: Bearer {token}
Request: { userId, lastSyncTime, localChanges }
Response: { success, conflicts[], serverData }

POST /sync/resolve-conflict
Header: Authorization: Bearer {token}
Request: { conflictId, resolution, data }
Response: { success, message }
```

---

## 🗄️ Backend Database Schema (PostgreSQL)

Required tables:
- `users` - User accounts (email, password, farm_name)
- `cows` - Livestock (foreign key to user)
- `milk_records` - Production (foreign key to cow)
- `wakulima_sales` - Sales records
- `expenses` - Farm expenses
- `sync_logs` - Track sync operations (optional)
- `conflicts` - Store unresolved conflicts (optional)

---

## 📱 Testing Checklist

- [ ] Login with email/password works
- [ ] Registration creates new account
- [ ] Add cow/milk record locally - appears in app
- [ ] Turn off WiFi - app still works (offline mode)
- [ ] Turn on WiFi - automatic sync happens (check adb logcat)
- [ ] Make change on device 1, check appears on device 2 after sync
- [ ] Create conflict scenario and test resolution screen
- [ ] Manual sync button works
- [ ] App shows last sync time correctly
- [ ] Logout works and clears auth token

---

## 🐛 Debugging Tips

### Check Sync Logs:
```bash
adb logcat | grep SyncWorker
```

### Check Network Requests:
- All network calls logged with body in Logcat (level BODY)
- Look for "Authorization" header in requests

### Check Database:
```bash
# Pull database from device
adb pull /data/data/com.apexrise.offline/databases/apexrise_offline.db

# Open with SQLite Browser to inspect sync fields
```

### Common Issues:

1. **Network timeout:**
   - Check backend URL in `NetworkClient.initialize()`
   - Ensure backend is running on correct port
   - For emulator: use `10.0.2.2` instead of `localhost`

2. **401 Unauthorized:**
   - JWT token expired (re-login required)
   - Backend not validating token properly

3. **Sync not happening:**
   - Check WorkManager in Settings > Developer Options > Jobs
   - Manually trigger with sync button
   - Check phone battery optimization settings

4. **Conflicts keep appearing:**
   - Different devices modifying same record simultaneously
   - Use conflict resolution screen to pick winner
   - Backend timestamp handling may need adjustment

---

## 📚 Next Steps

1. **Create Spring Boot Backend** using guide in `BACKEND_SETUP.md`
2. **Implement backend endpoints** (auth, sync, conflict resolution)
3. **Update Android backend URL** with actual server address
4. **Test with multiple devices** (2-4 phones/emulators)
5. **Monitor and optimize sync** based on real usage

---

## 💡 Architecture Summary

```
┌─────────────────────────────────┐
│      Mobile App (Android)       │
│  ┌──────────────────────────┐   │
│  │   UI Screens             │   │
│  │ - Auth                   │   │
│  │ - Farms/Cows             │   │
│  │ - Sync Status            │   │
│  │ - Conflicts              │   │
│  └──────────────────────────┘   │
│           ↕ (ViewModel)         │
│  ┌──────────────────────────┐   │
│  │  SyncRepository          │   │
│  │ - Local changes          │   │
│  │ - Merge server data      │   │
│  │ - Track conflicts        │   │
│  └──────────────────────────┘   │
│           ↓↑ (API)              │
│  ┌──────────────────────────┐   │
│  │  Room Database           │   │
│  │ (SQLite)                 │   │
│  │ - Offline storage        │   │
│  │ - Timestamps             │   │
│  └──────────────────────────┘   │
└─────────────────────────────────┘
          ↕↕ (HTTPS)
┌─────────────────────────────────┐
│     Spring Boot Backend         │
│  ┌──────────────────────────┐   │
│  │  Auth Controller         │   │
│  │ - Login                  │   │
│  │ - Register               │   │
│  └──────────────────────────┘   │
│  ┌──────────────────────────┐   │
│  │  Sync Controller         │   │
│  │ - Sync data              │   │
│  │ - Conflict detection     │   │
│  │ - Resolution             │   │
│  └──────────────────────────┘   │
│           ↓↑                     │
│  ┌──────────────────────────┐   │
│  │  PostgreSQL Database     │   │
│  │ - Shared farm data       │   │
│  │ - 4 user accounts        │   │
│  │ - Audit logs             │   │
│  └──────────────────────────┘   │
└─────────────────────────────────┘
```

---

## 📞 Support & Questions

- Check `BACKEND_SETUP.md` for backend implementation
- Review network logs in Logcat for API issues
- Verify database schema with SQLite viewer
- Test with adb commands for background sync

Good luck with your multi-user farm management system! 🚀
