package com.apexrise.offline.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bc\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0019J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\tH\u00c6\u0003J\t\u0010$\u001a\u00020\u0005H\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003Jl\u0010\'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010(J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010,\u001a\u00020-H\u00d6\u0001J\t\u0010.\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u000e\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e\u00a8\u0006/"}, d2 = {"Lcom/apexrise/offline/ui/viewmodel/ProfitsUiState;", "", "month", "", "rate", "", "salesTotals", "Lcom/apexrise/offline/data/model/WakulimaSalesTotalsRow;", "sales", "", "Lcom/apexrise/offline/data/entity/WakulimaSaleEntity;", "expensesList", "Lcom/apexrise/offline/data/entity/ExpenseEntity;", "revenue", "expenses", "netProfit", "(Ljava/lang/String;Ljava/lang/Double;Lcom/apexrise/offline/data/model/WakulimaSalesTotalsRow;Ljava/util/List;Ljava/util/List;DDD)V", "getExpenses", "()D", "getExpensesList", "()Ljava/util/List;", "getMonth", "()Ljava/lang/String;", "getNetProfit", "getRate", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getRevenue", "getSales", "getSalesTotals", "()Lcom/apexrise/offline/data/model/WakulimaSalesTotalsRow;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/Double;Lcom/apexrise/offline/data/model/WakulimaSalesTotalsRow;Ljava/util/List;Ljava/util/List;DDD)Lcom/apexrise/offline/ui/viewmodel/ProfitsUiState;", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class ProfitsUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String month = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double rate = null;
    @org.jetbrains.annotations.NotNull()
    private final com.apexrise.offline.data.model.WakulimaSalesTotalsRow salesTotals = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.apexrise.offline.data.entity.WakulimaSaleEntity> sales = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.apexrise.offline.data.entity.ExpenseEntity> expensesList = null;
    private final double revenue = 0.0;
    private final double expenses = 0.0;
    private final double netProfit = 0.0;
    
    public ProfitsUiState(@org.jetbrains.annotations.NotNull()
    java.lang.String month, @org.jetbrains.annotations.Nullable()
    java.lang.Double rate, @org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.model.WakulimaSalesTotalsRow salesTotals, @org.jetbrains.annotations.NotNull()
    java.util.List<com.apexrise.offline.data.entity.WakulimaSaleEntity> sales, @org.jetbrains.annotations.NotNull()
    java.util.List<com.apexrise.offline.data.entity.ExpenseEntity> expensesList, double revenue, double expenses, double netProfit) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMonth() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getRate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.apexrise.offline.data.model.WakulimaSalesTotalsRow getSalesTotals() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.apexrise.offline.data.entity.WakulimaSaleEntity> getSales() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.apexrise.offline.data.entity.ExpenseEntity> getExpensesList() {
        return null;
    }
    
    public final double getRevenue() {
        return 0.0;
    }
    
    public final double getExpenses() {
        return 0.0;
    }
    
    public final double getNetProfit() {
        return 0.0;
    }
    
    public ProfitsUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.apexrise.offline.data.model.WakulimaSalesTotalsRow component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.apexrise.offline.data.entity.WakulimaSaleEntity> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.apexrise.offline.data.entity.ExpenseEntity> component5() {
        return null;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.apexrise.offline.ui.viewmodel.ProfitsUiState copy(@org.jetbrains.annotations.NotNull()
    java.lang.String month, @org.jetbrains.annotations.Nullable()
    java.lang.Double rate, @org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.model.WakulimaSalesTotalsRow salesTotals, @org.jetbrains.annotations.NotNull()
    java.util.List<com.apexrise.offline.data.entity.WakulimaSaleEntity> sales, @org.jetbrains.annotations.NotNull()
    java.util.List<com.apexrise.offline.data.entity.ExpenseEntity> expensesList, double revenue, double expenses, double netProfit) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}