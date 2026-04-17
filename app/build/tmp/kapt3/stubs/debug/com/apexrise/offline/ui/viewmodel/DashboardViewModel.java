package com.apexrise.offline.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\r"}, d2 = {"Lcom/apexrise/offline/ui/viewmodel/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "db", "Lcom/apexrise/offline/data/ApexRiseDatabase;", "(Lcom/apexrise/offline/data/ApexRiseDatabase;)V", "_uiState", "Landroidx/compose/runtime/MutableState;", "Lcom/apexrise/offline/ui/viewmodel/DashboardUiState;", "uiState", "Landroidx/compose/runtime/State;", "getUiState", "()Landroidx/compose/runtime/State;", "Factory", "app_debug"})
public final class DashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.apexrise.offline.data.ApexRiseDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState<com.apexrise.offline.ui.viewmodel.DashboardUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.State<com.apexrise.offline.ui.viewmodel.DashboardUiState> uiState = null;
    
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.apexrise.offline.data.ApexRiseDatabase db) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<com.apexrise.offline.ui.viewmodel.DashboardUiState> getUiState() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/apexrise/offline/ui/viewmodel/DashboardViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "db", "Lcom/apexrise/offline/data/ApexRiseDatabase;", "(Lcom/apexrise/offline/data/ApexRiseDatabase;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"})
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