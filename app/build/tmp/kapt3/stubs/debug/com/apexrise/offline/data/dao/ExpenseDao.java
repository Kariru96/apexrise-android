package com.apexrise.offline.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J$\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\'J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\'\u00a8\u0006\u0011"}, d2 = {"Lcom/apexrise/offline/data/dao/ExpenseDao;", "", "delete", "", "expense", "Lcom/apexrise/offline/data/entity/ExpenseEntity;", "(Lcom/apexrise/offline/data/entity/ExpenseEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "observeInRange", "Lkotlinx/coroutines/flow/Flow;", "", "startDate", "", "endDate", "observeSumInRange", "", "app_debug"})
@androidx.room.Dao()
public abstract interface ExpenseDao {
    
    @androidx.room.Query(value = "\n        SELECT * FROM expenses\n         WHERE date >= :startDate AND date < :endDate\n         ORDER BY date DESC, id DESC;\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.apexrise.offline.data.entity.ExpenseEntity>> observeInRange(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate);
    
    @androidx.room.Query(value = "\n        SELECT COALESCE(SUM(amount), 0.0)\n          FROM expenses\n         WHERE date >= :startDate AND date < :endDate;\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Double> observeSumInRange(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate);
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.entity.ExpenseEntity expense, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.entity.ExpenseEntity expense, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}