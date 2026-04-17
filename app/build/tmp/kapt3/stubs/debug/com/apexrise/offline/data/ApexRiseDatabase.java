package com.apexrise.offline.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000b"}, d2 = {"Lcom/apexrise/offline/data/ApexRiseDatabase;", "Landroidx/room/RoomDatabase;", "()V", "cowDao", "Lcom/apexrise/offline/data/dao/CowDao;", "expenseDao", "Lcom/apexrise/offline/data/dao/ExpenseDao;", "milkRecordDao", "Lcom/apexrise/offline/data/dao/MilkRecordDao;", "wakulimaDao", "Lcom/apexrise/offline/data/dao/WakulimaDao;", "app_debug"})
@androidx.room.Database(entities = {com.apexrise.offline.data.entity.CowEntity.class, com.apexrise.offline.data.entity.MilkRecordEntity.class, com.apexrise.offline.data.entity.WakulimaSaleEntity.class, com.apexrise.offline.data.entity.WakulimaRateEntity.class, com.apexrise.offline.data.entity.ExpenseEntity.class}, version = 1, exportSchema = false)
public abstract class ApexRiseDatabase extends androidx.room.RoomDatabase {
    
    public ApexRiseDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.apexrise.offline.data.dao.CowDao cowDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.apexrise.offline.data.dao.MilkRecordDao milkRecordDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.apexrise.offline.data.dao.WakulimaDao wakulimaDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.apexrise.offline.data.dao.ExpenseDao expenseDao();
}