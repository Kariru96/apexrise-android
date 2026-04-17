package com.apexrise.offline.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.apexrise.offline.data.entity.MilkRecordEntity;
import com.apexrise.offline.data.model.MilkRecordListRow;
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
public final class MilkRecordDao_Impl implements MilkRecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MilkRecordEntity> __insertionAdapterOfMilkRecordEntity;

  private final EntityDeletionOrUpdateAdapter<MilkRecordEntity> __deletionAdapterOfMilkRecordEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public MilkRecordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMilkRecordEntity = new EntityInsertionAdapter<MilkRecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `milk_records` (`id`,`cow_id`,`date`,`session_1`,`session_2`,`session_3`,`session_4`,`notes`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MilkRecordEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getCowId());
        if (entity.getDate() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDate());
        }
        statement.bindDouble(4, entity.getSession1());
        statement.bindDouble(5, entity.getSession2());
        statement.bindDouble(6, entity.getSession3());
        statement.bindDouble(7, entity.getSession4());
        if (entity.getNotes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getNotes());
        }
      }
    };
    this.__deletionAdapterOfMilkRecordEntity = new EntityDeletionOrUpdateAdapter<MilkRecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `milk_records` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MilkRecordEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM milk_records WHERE id = ?;";
        return _query;
      }
    };
  }

  @Override
  public Object upsert(final MilkRecordEntity record,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfMilkRecordEntity.insertAndReturnId(record);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final MilkRecordEntity record,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMilkRecordEntity.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final long recordId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, recordId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MilkRecordEntity>> observeByCow(final long cowId) {
    final String _sql = "SELECT * FROM milk_records WHERE cow_id = ? ORDER BY date DESC, id DESC;";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cowId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"milk_records"}, new Callable<List<MilkRecordEntity>>() {
      @Override
      @NonNull
      public List<MilkRecordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCowId = CursorUtil.getColumnIndexOrThrow(_cursor, "cow_id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfSession1 = CursorUtil.getColumnIndexOrThrow(_cursor, "session_1");
          final int _cursorIndexOfSession2 = CursorUtil.getColumnIndexOrThrow(_cursor, "session_2");
          final int _cursorIndexOfSession3 = CursorUtil.getColumnIndexOrThrow(_cursor, "session_3");
          final int _cursorIndexOfSession4 = CursorUtil.getColumnIndexOrThrow(_cursor, "session_4");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<MilkRecordEntity> _result = new ArrayList<MilkRecordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MilkRecordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpCowId;
            _tmpCowId = _cursor.getLong(_cursorIndexOfCowId);
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
            final double _tmpSession4;
            _tmpSession4 = _cursor.getDouble(_cursorIndexOfSession4);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new MilkRecordEntity(_tmpId,_tmpCowId,_tmpDate,_tmpSession1,_tmpSession2,_tmpSession3,_tmpSession4,_tmpNotes);
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
  public Flow<MilkRecordEntity> observeByCowAndDate(final long cowId, final String date) {
    final String _sql = "SELECT * FROM milk_records WHERE cow_id = ? AND date = ? LIMIT 1;";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cowId);
    _argIndex = 2;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"milk_records"}, new Callable<MilkRecordEntity>() {
      @Override
      @Nullable
      public MilkRecordEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCowId = CursorUtil.getColumnIndexOrThrow(_cursor, "cow_id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfSession1 = CursorUtil.getColumnIndexOrThrow(_cursor, "session_1");
          final int _cursorIndexOfSession2 = CursorUtil.getColumnIndexOrThrow(_cursor, "session_2");
          final int _cursorIndexOfSession3 = CursorUtil.getColumnIndexOrThrow(_cursor, "session_3");
          final int _cursorIndexOfSession4 = CursorUtil.getColumnIndexOrThrow(_cursor, "session_4");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final MilkRecordEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpCowId;
            _tmpCowId = _cursor.getLong(_cursorIndexOfCowId);
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
            final double _tmpSession4;
            _tmpSession4 = _cursor.getDouble(_cursorIndexOfSession4);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result = new MilkRecordEntity(_tmpId,_tmpCowId,_tmpDate,_tmpSession1,_tmpSession2,_tmpSession3,_tmpSession4,_tmpNotes);
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
  public Flow<Double> observeTotalForDate(final String date) {
    final String _sql = "\n"
            + "        SELECT COALESCE(SUM(session_1 + session_2 + session_3 + session_4), 0.0)\n"
            + "          FROM milk_records\n"
            + "         WHERE date = ?;\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"milk_records"}, new Callable<Double>() {
      @Override
      @NonNull
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
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
  public Flow<List<MilkRecordListRow>> observeAllWithCow(final Long cowId) {
    final String _sql = "\n"
            + "        SELECT r.id,\n"
            + "               r.date,\n"
            + "               r.cow_id AS cowId,\n"
            + "               c.name AS cow_name,\n"
            + "               c.tag_number AS cow_tag_number,\n"
            + "               r.session_1, r.session_2, r.session_3, r.session_4,\n"
            + "               (r.session_1 + r.session_2 + r.session_3 + r.session_4) AS daily_total\n"
            + "          FROM milk_records r\n"
            + "          JOIN cows c ON c.id = r.cow_id\n"
            + "         WHERE (? IS NULL OR r.cow_id = ?)\n"
            + "         ORDER BY r.date DESC, r.id DESC;\n"
            + "        ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (cowId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, cowId);
    }
    _argIndex = 2;
    if (cowId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, cowId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"milk_records",
        "cows"}, new Callable<List<MilkRecordListRow>>() {
      @Override
      @NonNull
      public List<MilkRecordListRow> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfDate = 1;
          final int _cursorIndexOfCowId = 2;
          final int _cursorIndexOfCowName = 3;
          final int _cursorIndexOfCowTagNumber = 4;
          final int _cursorIndexOfSession1 = 5;
          final int _cursorIndexOfSession2 = 6;
          final int _cursorIndexOfSession3 = 7;
          final int _cursorIndexOfSession4 = 8;
          final int _cursorIndexOfDailyTotal = 9;
          final List<MilkRecordListRow> _result = new ArrayList<MilkRecordListRow>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MilkRecordListRow _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final long _tmpCowId;
            _tmpCowId = _cursor.getLong(_cursorIndexOfCowId);
            final String _tmpCowName;
            if (_cursor.isNull(_cursorIndexOfCowName)) {
              _tmpCowName = null;
            } else {
              _tmpCowName = _cursor.getString(_cursorIndexOfCowName);
            }
            final String _tmpCowTagNumber;
            if (_cursor.isNull(_cursorIndexOfCowTagNumber)) {
              _tmpCowTagNumber = null;
            } else {
              _tmpCowTagNumber = _cursor.getString(_cursorIndexOfCowTagNumber);
            }
            final double _tmpSession1;
            _tmpSession1 = _cursor.getDouble(_cursorIndexOfSession1);
            final double _tmpSession2;
            _tmpSession2 = _cursor.getDouble(_cursorIndexOfSession2);
            final double _tmpSession3;
            _tmpSession3 = _cursor.getDouble(_cursorIndexOfSession3);
            final double _tmpSession4;
            _tmpSession4 = _cursor.getDouble(_cursorIndexOfSession4);
            final double _tmpDailyTotal;
            _tmpDailyTotal = _cursor.getDouble(_cursorIndexOfDailyTotal);
            _item = new MilkRecordListRow(_tmpId,_tmpDate,_tmpCowId,_tmpCowName,_tmpCowTagNumber,_tmpSession1,_tmpSession2,_tmpSession3,_tmpSession4,_tmpDailyTotal);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
