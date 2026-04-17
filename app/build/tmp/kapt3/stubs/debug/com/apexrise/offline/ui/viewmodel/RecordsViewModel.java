package com.apexrise.offline.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\rJ\u0015\u0010\u0017\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u0019R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011\u00a8\u0006\u001b"}, d2 = {"Lcom/apexrise/offline/ui/viewmodel/RecordsViewModel;", "Landroidx/lifecycle/ViewModel;", "db", "Lcom/apexrise/offline/data/ApexRiseDatabase;", "(Lcom/apexrise/offline/data/ApexRiseDatabase;)V", "_cows", "Landroidx/compose/runtime/MutableState;", "", "Lcom/apexrise/offline/data/entity/CowEntity;", "_records", "Lcom/apexrise/offline/data/model/MilkRecordListRow;", "cowFilter", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "cows", "Landroidx/compose/runtime/State;", "getCows", "()Landroidx/compose/runtime/State;", "records", "getRecords", "deleteRecord", "", "recordId", "setCowFilter", "cowId", "(Ljava/lang/Long;)V", "Factory", "app_debug"})
public final class RecordsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.apexrise.offline.data.ApexRiseDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState<java.util.List<com.apexrise.offline.data.entity.CowEntity>> _cows = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.State<java.util.List<com.apexrise.offline.data.entity.CowEntity>> cows = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> cowFilter = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState<java.util.List<com.apexrise.offline.data.model.MilkRecordListRow>> _records = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.State<java.util.List<com.apexrise.offline.data.model.MilkRecordListRow>> records = null;
    
    public RecordsViewModel(@org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.ApexRiseDatabase db) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.util.List<com.apexrise.offline.data.entity.CowEntity>> getCows() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.util.List<com.apexrise.offline.data.model.MilkRecordListRow>> getRecords() {
        return null;
    }
    
    public final void setCowFilter(@org.jetbrains.annotations.Nullable()
    java.lang.Long cowId) {
    }
    
    public final void deleteRecord(long recordId) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/apexrise/offline/ui/viewmodel/RecordsViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "db", "Lcom/apexrise/offline/data/ApexRiseDatabase;", "(Lcom/apexrise/offline/data/ApexRiseDatabase;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"})
    public static final class Factory implements androidx.lifecycle.ViewModelProvider.Factory {
        @org.jetbrains.annotations.NotNull()
        private final com.apexrise.offline.data.ApexRiseDatabase db = null;
        
        public Factory(@org.jetbrains.annotations.NotNull()
        com.apexrise.offline.data.ApexRiseDatabase db) {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
        java.lang.Class<T> modelClass) {
            return null;
        }
    }
}