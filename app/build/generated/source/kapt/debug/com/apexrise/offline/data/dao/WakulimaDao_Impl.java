package com.apexrise.offline.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.apexrise.offline.data.entity.WakulimaRateEntity;
import com.apexrise.offline.data.entity.WakulimaSaleEntity;
import com.apexrise.offline.data.model.WakulimaSalesTotalsRow;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WakulimaDao_Impl implements WakulimaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WakulimaSaleEntity> __insertionAdapterOfWakulimaSaleEntity;

  private final EntityInsertionAdapter<WakulimaRateEntity> __insertionAdapterOfWakulimaRateEntity;

  private final EntityDeletionOrUpdateAdapter<WakulimaSaleEntity> __deletionAdapterOfWakulimaSaleEntity;

  public WakulimaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWakulimaSaleEntity = new EntityInsertionAdapter<WakulimaSaleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `milk_sales` (`id`,`date`,`session_1`,`session_2`,`session_3`,`litres`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WakulimaSaleEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getDate() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDate());
        }
        statement.bindDouble(3, entity.getSession1());
        statement.bindDouble(4, entity.getSession2());
        statement.bindDouble(5, entity.getSession3());
        statement.bindDouble(6, entity.getLitres());
      }
    };
    this.__insertionAdapterOfWakulimaRateEntity = new EntityInsertionAdapter<WakulimaRateEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `wakulima_rates` (`month`,`price_per_litre`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WakulimaRateEntity entity) {
        if (entity.getMonth() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getMonth());
        }
        statement.bindDouble(2, entity.getPricePerLitre());
      }
    };
    this.__deletionAdapterOfWakulimaSaleEntity = new EntityDeletionOrUpdateAdapter<WakulimaSaleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `milk_sales` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WakulimaSaleEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertSale(final WakulimaSaleEntity sale,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfWakulimaSaleEntity.insertAndReturnId(sale);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertRate(final WakulimaRateEntity rate,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWakulimaRateEntity.insert(rate);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSale(final WakulimaSaleEntity sale,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfWakulimaSaleEntity.handle(sale);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<WakulimaSaleEntity>> observeSalesInRange(final String startDate,
      final String endDate) {
    final String _sql = "\n"
            + "        SELECT * FROM milk_sales\n"
            + "         WHERE date >= ? AND date < ?\n"
            + "         ORDER BY date DESC, id DESC;\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    _argIndex = 2;
    if (endDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, endDate);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"milk_sales"}, new Callable<List<WakulimaSaleEntity>>() {
      @Override
      @NonNull
      public List<WakulimaSaleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfSession1 = CursorUtil.getColumnIndexOrThrow(_cursor, "session_1");
          final int _cursorIndexOfSession2 = CursorUtil.getColumnIndexOrThrow(_cursor, "session_2");
          final int _cursorIndexOfSession3 = CursorUtil.getColumnIndexOrThrow(_cursor, "session_3");
          final int _cursorIndexOfLitres = CursorUtil.getColumnIndexOrThrow(_cursor, "litres");
          final List<WakulimaSaleEntity> _result = new ArrayList<WakulimaSaleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WakulimaSaleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final double _tmpSession1;
            _tmpSession1 = _cursor.getDouble(_cursorIndexOfSession1);
            final double _tmpSession2;
            _tmpSession2 = _cursor.getDouble(_cursorIndexOfSession2);
            final double _tmpSession3;
            _tmpSession3 = _cursor.getDouble(_cursorIndexOfSession3);
            final double _tmpLitres;
            _tmpLitres = _cursor.getDouble(_cursorIndexOfLitres);
            _item = new WakulimaSaleEntity(_tmpId,_tmpDate,_tmpSession1,_tmpSession2,_tmpSession3,_tmpLitres);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<WakulimaSalesTotalsRow> observeTotalsInRange(final String startDate,
      final String endDate) {
    final String _sql = "\n"
            + "        SELECT COALESCE(SUM(session_1), 0.0) AS session_1_total,\n"
            + "               COALESCE(SUM(session_2), 0.0) AS session_2_total,\n"
            + "               COALESCE(SUM(session_3), 0.0) AS session_3_total,\n"
            + "               COALESCE(SUM(litres), 0.0) AS litres_total\n"
            + "          FROM milk_sales\n"
            + "         WHERE date >= ? AND date < ?;\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    _argIndex = 2;
    if (endDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, endDate);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"milk_sales"}, new Callable<WakulimaSalesTotalsRow>() {
      @Override
      @NonNull
      public WakulimaSalesTotalsRow call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSession1Total = 0;
          final int _cursorIndexOfSession2Total = 1;
          final int _cursorIndexOfSession3Total = 2;
          final int _cursorIndexOfLitresTotal = 3;
          final WakulimaSalesTotalsRow _result;
          if (_cursor.moveToFirst()) {
            final double _tmpSession1Total;
            _tmpSession1Total = _cursor.getDouble(_cursorIndexOfSession1Total);
            final double _tmpSession2Total;
            _tmpSession2Total = _cursor.getDouble(_cursorIndexOfSession2Total);
            final double _tmpSession3Total;
            _tmpSession3Total = _cursor.getDouble(_cursorIndexOfSession3Total);
            final double _tmpLitresTotal;
            _tmpLitresTotal = _cursor.getDouble(_cursorIndexOfLitresTotal);
            _result = new WakulimaSalesTotalsRow(_tmpSession1Total,_tmpSession2Total,_tmpSession3Total,_tmpLitresTotal);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Double> observeRate(final String month) {
    final String _sql = "SELECT price_per_litre FROM wakulima_rates WHERE month = ? LIMIT 1;";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (month == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, month);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"wakulima_rates"}, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getDouble(0);
            }
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
