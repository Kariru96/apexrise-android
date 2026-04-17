package com.apexrise.offline.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\'J$\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\n2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\'J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\n2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\'J\u0016\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0017\u00a8\u0006\u0018"}, d2 = {"Lcom/apexrise/offline/data/dao/WakulimaDao;", "", "deleteSale", "", "sale", "Lcom/apexrise/offline/data/entity/WakulimaSaleEntity;", "(Lcom/apexrise/offline/data/entity/WakulimaSaleEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertSale", "", "observeRate", "Lkotlinx/coroutines/flow/Flow;", "", "month", "", "observeSalesInRange", "", "startDate", "endDate", "observeTotalsInRange", "Lcom/apexrise/offline/data/model/WakulimaSalesTotalsRow;", "upsertRate", "rate", "Lcom/apexrise/offline/data/entity/WakulimaRateEntity;", "(Lcom/apexrise/offline/data/entity/WakulimaRateEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface WakulimaDao {
    
    @androidx.room.Query(value = "\n        SELECT * FROM milk_sales\n         WHERE date >= :startDate AND date < :endDate\n         ORDER BY date DESC, id DESC;\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.apexrise.offline.data.entity.WakulimaSaleEntity>> observeSalesInRange(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate);
    
    @androidx.room.Query(value = "\n        SELECT COALESCE(SUM(session_1), 0.0) AS session_1_total,\n               COALESCE(SUM(session_2), 0.0) AS session_2_total,\n               COALESCE(SUM(session_3), 0.0) AS session_3_total,\n               COALESCE(SUM(litres), 0.0) AS litres_total\n          FROM milk_sales\n         WHERE date >= :startDate AND date < :endDate;\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.apexrise.offline.data.model.WakulimaSalesTotalsRow> observeTotalsInRange(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate);
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertSale(@org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.entity.WakulimaSaleEntity sale, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteSale(@org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.entity.WakulimaSaleEntity sale, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT price_per_litre FROM wakulima_rates WHERE month = :month LIMIT 1;")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Double> observeRate(@org.jetbrains.annotations.NotNull()
    java.lang.String month);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertRate(@org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.entity.WakulimaRateEntity rate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}