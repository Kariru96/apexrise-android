package com.apexrise.offline.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.apexrise.offline.data.dao.CowDao;
import com.apexrise.offline.data.dao.CowDao_Impl;
import com.apexrise.offline.data.dao.ExpenseDao;
import com.apexrise.offline.data.dao.ExpenseDao_Impl;
import com.apexrise.offline.data.dao.MilkRecordDao;
import com.apexrise.offline.data.dao.MilkRecordDao_Impl;
import com.apexrise.offline.data.dao.WakulimaDao;
import com.apexrise.offline.data.dao.WakulimaDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ApexRiseDatabase_Impl extends ApexRiseDatabase {
  private volatile CowDao _cowDao;

  private volatile MilkRecordDao _milkRecordDao;

  private volatile WakulimaDao _wakulimaDao;

  private volatile ExpenseDao _expenseDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `cows` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `tag_number` TEXT NOT NULL, `breed` TEXT, `purchase_date` TEXT, `purchase_cost` REAL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_cows_tag_number` ON `cows` (`tag_number`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `milk_records` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `cow_id` INTEGER NOT NULL, `date` TEXT NOT NULL, `session_1` REAL NOT NULL, `session_2` REAL NOT NULL, `session_3` REAL NOT NULL, `session_4` REAL NOT NULL, `notes` TEXT, FOREIGN KEY(`cow_id`) REFERENCES `cows`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_milk_records_date` ON `milk_records` (`date`)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_milk_records_cow_id_date` ON `milk_records` (`cow_id`, `date`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `milk_sales` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` TEXT NOT NULL, `session_1` REAL NOT NULL, `session_2` REAL NOT NULL, `session_3` REAL NOT NULL, `litres` REAL NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_milk_sales_date` ON `milk_sales` (`date`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `wakulima_rates` (`month` TEXT NOT NULL, `price_per_litre` REAL NOT NULL, PRIMARY KEY(`month`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `expenses` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` TEXT NOT NULL, `category` TEXT NOT NULL, `amount` REAL NOT NULL, `description` TEXT)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_expenses_date` ON `expenses` (`date`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_expenses_category` ON `expenses` (`category`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6ef23f6b251926cb67d5fdac314e9464')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `cows`");
        db.execSQL("DROP TABLE IF EXISTS `milk_records`");
        db.execSQL("DROP TABLE IF EXISTS `milk_sales`");
        db.execSQL("DROP TABLE IF EXISTS `wakulima_rates`");
        db.execSQL("DROP TABLE IF EXISTS `expenses`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsCows = new HashMap<String, TableInfo.Column>(6);
        _columnsCows.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCows.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCows.put("tag_number", new TableInfo.Column("tag_number", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCows.put("breed", new TableInfo.Column("breed", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCows.put("purchase_date", new TableInfo.Column("purchase_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCows.put("purchase_cost", new TableInfo.Column("purchase_cost", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCows = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCows = new HashSet<TableInfo.Index>(1);
        _indicesCows.add(new TableInfo.Index("index_cows_tag_number", true, Arrays.asList("tag_number"), Arrays.asList("ASC")));
        final TableInfo _infoCows = new TableInfo("cows", _columnsCows, _foreignKeysCows, _indicesCows);
        final TableInfo _existingCows = TableInfo.read(db, "cows");
        if (!_infoCows.equals(_existingCows)) {
          return new RoomOpenHelper.ValidationResult(false, "cows(com.apexrise.offline.data.entity.CowEntity).\n"
                  + " Expected:\n" + _infoCows + "\n"
                  + " Found:\n" + _existingCows);
        }
        final HashMap<String, TableInfo.Column> _columnsMilkRecords = new HashMap<String, TableInfo.Column>(8);
        _columnsMilkRecords.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilkRecords.put("cow_id", new TableInfo.Column("cow_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilkRecords.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilkRecords.put("session_1", new TableInfo.Column("session_1", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilkRecords.put("session_2", new TableInfo.Column("session_2", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilkRecords.put("session_3", new TableInfo.Column("session_3", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilkRecords.put("session_4", new TableInfo.Column("session_4", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilkRecords.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMilkRecords = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysMilkRecords.add(new TableInfo.ForeignKey("cows", "CASCADE", "NO ACTION", Arrays.asList("cow_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesMilkRecords = new HashSet<TableInfo.Index>(2);
        _indicesMilkRecords.add(new TableInfo.Index("index_milk_records_date", false, Arrays.asList("date"), Arrays.asList("ASC")));
        _indicesMilkRecords.add(new TableInfo.Index("index_milk_records_cow_id_date", true, Arrays.asList("cow_id", "date"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoMilkRecords = new TableInfo("milk_records", _columnsMilkRecords, _foreignKeysMilkRecords, _indicesMilkRecords);
        final TableInfo _existingMilkRecords = TableInfo.read(db, "milk_records");
        if (!_infoMilkRecords.equals(_existingMilkRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "milk_records(com.apexrise.offline.data.entity.MilkRecordEntity).\n"
                  + " Expected:\n" + _infoMilkRecords + "\n"
                  + " Found:\n" + _existingMilkRecords);
        }
        final HashMap<String, TableInfo.Column> _columnsMilkSales = new HashMap<String, TableInfo.Column>(6);
        _columnsMilkSales.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilkSales.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilkSales.put("session_1", new TableInfo.Column("session_1", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilkSales.put("session_2", new TableInfo.Column("session_2", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilkSales.put("session_3", new TableInfo.Column("session_3", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilkSales.put("litres", new TableInfo.Column("litres", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMilkSales = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMilkSales = new HashSet<TableInfo.Index>(1);
        _indicesMilkSales.add(new TableInfo.Index("index_milk_sales_date", false, Arrays.asList("date"), Arrays.asList("ASC")));
        final TableInfo _infoMilkSales = new TableInfo("milk_sales", _columnsMilkSales, _foreignKeysMilkSales, _indicesMilkSales);
        final TableInfo _existingMilkSales = TableInfo.read(db, "milk_sales");
        if (!_infoMilkSales.equals(_existingMilkSales)) {
          return new RoomOpenHelper.ValidationResult(false, "milk_sales(com.apexrise.offline.data.entity.WakulimaSaleEntity).\n"
                  + " Expected:\n" + _infoMilkSales + "\n"
                  + " Found:\n" + _existingMilkSales);
        }
        final HashMap<String, TableInfo.Column> _columnsWakulimaRates = new HashMap<String, TableInfo.Column>(2);
        _columnsWakulimaRates.put("month", new TableInfo.Column("month", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWakulimaRates.put("price_per_litre", new TableInfo.Column("price_per_litre", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWakulimaRates = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWakulimaRates = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWakulimaRates = new TableInfo("wakulima_rates", _columnsWakulimaRates, _foreignKeysWakulimaRates, _indicesWakulimaRates);
        final TableInfo _existingWakulimaRates = TableInfo.read(db, "wakulima_rates");
        if (!_infoWakulimaRates.equals(_existingWakulimaRates)) {
          return new RoomOpenHelper.ValidationResult(false, "wakulima_rates(com.apexrise.offline.data.entity.WakulimaRateEntity).\n"
                  + " Expected:\n" + _infoWakulimaRates + "\n"
                  + " Found:\n" + _existingWakulimaRates);
        }
        final HashMap<String, TableInfo.Column> _columnsExpenses = new HashMap<String, TableInfo.Column>(5);
        _columnsExpenses.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExpenses = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExpenses = new HashSet<TableInfo.Index>(2);
        _indicesExpenses.add(new TableInfo.Index("index_expenses_date", false, Arrays.asList("date"), Arrays.asList("ASC")));
        _indicesExpenses.add(new TableInfo.Index("index_expenses_category", false, Arrays.asList("category"), Arrays.asList("ASC")));
        final TableInfo _infoExpenses = new TableInfo("expenses", _columnsExpenses, _foreignKeysExpenses, _indicesExpenses);
        final TableInfo _existingExpenses = TableInfo.read(db, "expenses");
        if (!_infoExpenses.equals(_existingExpenses)) {
          return new RoomOpenHelper.ValidationResult(false, "expenses(com.apexrise.offline.data.entity.ExpenseEntity).\n"
                  + " Expected:\n" + _infoExpenses + "\n"
                  + " Found:\n" + _existingExpenses);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "6ef23f6b251926cb67d5fdac314e9464", "3374a6f382aef58254d43f4ce0ec54a0");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "cows","milk_records","milk_sales","wakulima_rates","expenses");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `cows`");
      _db.execSQL("DELETE FROM `milk_records`");
      _db.execSQL("DELETE FROM `milk_sales`");
      _db.execSQL("DELETE FROM `wakulima_rates`");
      _db.execSQL("DELETE FROM `expenses`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CowDao.class, CowDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MilkRecordDao.class, MilkRecordDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WakulimaDao.class, WakulimaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExpenseDao.class, ExpenseDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CowDao cowDao() {
    if (_cowDao != null) {
      return _cowDao;
    } else {
      synchronized(this) {
        if(_cowDao == null) {
          _cowDao = new CowDao_Impl(this);
        }
        return _cowDao;
      }
    }
  }

  @Override
  public MilkRecordDao milkRecordDao() {
    if (_milkRecordDao != null) {
      return _milkRecordDao;
    } else {
      synchronized(this) {
        if(_milkRecordDao == null) {
          _milkRecordDao = new MilkRecordDao_Impl(this);
        }
        return _milkRecordDao;
      }
    }
  }

  @Override
  public WakulimaDao wakulimaDao() {
    if (_wakulimaDao != null) {
      return _wakulimaDao;
    } else {
      synchronized(this) {
        if(_wakulimaDao == null) {
          _wakulimaDao = new WakulimaDao_Impl(this);
        }
        return _wakulimaDao;
      }
    }
  }

  @Override
  public ExpenseDao expenseDao() {
    if (_expenseDao != null) {
      return _expenseDao;
    } else {
      synchronized(this) {
        if(_expenseDao == null) {
          _expenseDao = new ExpenseDao_Impl(this);
        }
        return _expenseDao;
      }
    }
  }
}
