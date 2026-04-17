package com.apexrise.offline.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ#\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\tH\'\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u000f\u001a\u00020\tH\'J \u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\f2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014H\'J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\f2\u0006\u0010\u0013\u001a\u00020\u0014H\'J\u0016\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0018"}, d2 = {"Lcom/apexrise/offline/data/dao/MilkRecordDao;", "", "delete", "", "record", "Lcom/apexrise/offline/data/entity/MilkRecordEntity;", "(Lcom/apexrise/offline/data/entity/MilkRecordEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteById", "recordId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAllWithCow", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/apexrise/offline/data/model/MilkRecordListRow;", "cowId", "(Ljava/lang/Long;)Lkotlinx/coroutines/flow/Flow;", "observeByCow", "observeByCowAndDate", "date", "", "observeTotalForDate", "", "upsert", "app_debug"})
@androidx.room.Dao()
public abstract interface MilkRecordDao {
    
    @androidx.room.Query(value = "SELECT * FROM milk_records WHERE cow_id = :cowId ORDER BY date DESC, id DESC;")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.apexrise.offline.data.entity.MilkRecordEntity>> observeByCow(long cowId);
    
    @androidx.room.Query(value = "SELECT * FROM milk_records WHERE cow_id = :cowId AND date = :date LIMIT 1;")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.apexrise.offline.data.entity.MilkRecordEntity> observeByCowAndDate(long cowId, @org.jetbrains.annotations.NotNull()
    java.lang.String date);
    
    @androidx.room.Query(value = "\n        SELECT COALESCE(SUM(session_1 + session_2 + session_3 + session_4), 0.0)\n          FROM milk_records\n         WHERE date = :date;\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Double> observeTotalForDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date);
    
    @androidx.room.Query(value = "\n        SELECT r.id,\n               r.date,\n               r.cow_id AS cowId,\n               c.name AS cow_name,\n               c.tag_number AS cow_tag_number,\n               r.session_1, r.session_2, r.session_3, r.session_4,\n               (r.session_1 + r.session_2 + r.session_3 + r.session_4) AS daily_total\n          FROM milk_records r\n          JOIN cows c ON c.id = r.cow_id\n         WHERE (:cowId IS NULL OR r.cow_id = :cowId)\n         ORDER BY r.date DESC, r.id DESC;\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.apexrise.offline.data.model.MilkRecordListRow>> observeAllWithCow(@org.jetbrains.annotations.Nullable()
    java.lang.Long cowId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsert(@org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.entity.MilkRecordEntity record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.entity.MilkRecordEntity record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM milk_records WHERE id = :recordId;")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteById(long recordId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}