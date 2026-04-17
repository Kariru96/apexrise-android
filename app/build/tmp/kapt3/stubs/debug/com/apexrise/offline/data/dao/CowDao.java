package com.apexrise.offline.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\nH\'J\u0018\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\n2\u0006\u0010\r\u001a\u00020\bH\'J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nH\'J\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/apexrise/offline/data/dao/CowDao;", "", "delete", "", "cow", "Lcom/apexrise/offline/data/entity/CowEntity;", "(Lcom/apexrise/offline/data/entity/CowEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "", "observeById", "cowId", "observeCount", "", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface CowDao {
    
    @androidx.room.Query(value = "SELECT * FROM cows ORDER BY tag_number;")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.apexrise.offline.data.entity.CowEntity>> observeAll();
    
    @androidx.room.Query(value = "SELECT * FROM cows WHERE id = :cowId LIMIT 1;")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.apexrise.offline.data.entity.CowEntity> observeById(long cowId);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM cows;")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Integer> observeCount();
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.entity.CowEntity cow, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.entity.CowEntity cow, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.entity.CowEntity cow, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}