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
import com.apexrise.offline.data.entity.CowEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
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
public final class CowDao_Impl implements CowDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CowEntity> __insertionAdapterOfCowEntity;

  private final EntityDeletionOrUpdateAdapter<CowEntity> __deletionAdapterOfCowEntity;

  private final EntityDeletionOrUpdateAdapter<CowEntity> __updateAdapterOfCowEntity;

  public CowDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCowEntity = new EntityInsertionAdapter<CowEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `cows` (`id`,`name`,`tag_number`,`breed`,`purchase_date`,`purchase_cost`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CowEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getTagNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTagNumber());
        }
        if (entity.getBreed() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getBreed());
        }
        if (entity.getPurchaseDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPurchaseDate());
        }
        if (entity.getPurchaseCost() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getPurchaseCost());
        }
      }
    };
    this.__deletionAdapterOfCowEntity = new EntityDeletionOrUpdateAdapter<CowEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `cows` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CowEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfCowEntity = new EntityDeletionOrUpdateAdapter<CowEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `cows` SET `id` = ?,`name` = ?,`tag_number` = ?,`breed` = ?,`purchase_date` = ?,`purchase_cost` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CowEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getTagNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTagNumber());
        }
        if (entity.getBreed() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getBreed());
        }
        if (entity.getPurchaseDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPurchaseDate());
        }
        if (entity.getPurchaseCost() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getPurchaseCost());
        }
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final CowEntity cow, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfCowEntity.insertAndReturnId(cow);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final CowEntity cow, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCowEntity.handle(cow);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final CowEntity cow, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCowEntity.handle(cow);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<CowEntity>> observeAll() {
    final String _sql = "SELECT * FROM cows ORDER BY tag_number;";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cows"}, new Callable<List<CowEntity>>() {
      @Override
      @NonNull
      public List<CowEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTagNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "tag_number");
          final int _cursorIndexOfBreed = CursorUtil.getColumnIndexOrThrow(_cursor, "breed");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchase_date");
          final int _cursorIndexOfPurchaseCost = CursorUtil.getColumnIndexOrThrow(_cursor, "purchase_cost");
          final List<CowEntity> _result = new ArrayList<CowEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CowEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpTagNumber;
            if (_cursor.isNull(_cursorIndexOfTagNumber)) {
              _tmpTagNumber = null;
            } else {
              _tmpTagNumber = _cursor.getString(_cursorIndexOfTagNumber);
            }
            final String _tmpBreed;
            if (_cursor.isNull(_cursorIndexOfBreed)) {
              _tmpBreed = null;
            } else {
              _tmpBreed = _cursor.getString(_cursorIndexOfBreed);
            }
            final String _tmpPurchaseDate;
            if (_cursor.isNull(_cursorIndexOfPurchaseDate)) {
              _tmpPurchaseDate = null;
            } else {
              _tmpPurchaseDate = _cursor.getString(_cursorIndexOfPurchaseDate);
            }
            final Double _tmpPurchaseCost;
            if (_cursor.isNull(_cursorIndexOfPurchaseCost)) {
              _tmpPurchaseCost = null;
            } else {
              _tmpPurchaseCost = _cursor.getDouble(_cursorIndexOfPurchaseCost);
            }
            _item = new CowEntity(_tmpId,_tmpName,_tmpTagNumber,_tmpBreed,_tmpPurchaseDate,_tmpPurchaseCost);
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
  public Flow<CowEntity> observeById(final long cowId) {
    final String _sql = "SELECT * FROM cows WHERE id = ? LIMIT 1;";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cowId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cows"}, new Callable<CowEntity>() {
      @Override
      @Nullable
      public CowEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTagNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "tag_number");
          final int _cursorIndexOfBreed = CursorUtil.getColumnIndexOrThrow(_cursor, "breed");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchase_date");
          final int _cursorIndexOfPurchaseCost = CursorUtil.getColumnIndexOrThrow(_cursor, "purchase_cost");
          final CowEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpTagNumber;
            if (_cursor.isNull(_cursorIndexOfTagNumber)) {
              _tmpTagNumber = null;
            } else {
              _tmpTagNumber = _cursor.getString(_cursorIndexOfTagNumber);
            }
            final String _tmpBreed;
            if (_cursor.isNull(_cursorIndexOfBreed)) {
              _tmpBreed = null;
            } else {
              _tmpBreed = _cursor.getString(_cursorIndexOfBreed);
            }
            final String _tmpPurchaseDate;
            if (_cursor.isNull(_cursorIndexOfPurchaseDate)) {
              _tmpPurchaseDate = null;
            } else {
              _tmpPurchaseDate = _cursor.getString(_cursorIndexOfPurchaseDate);
            }
            final Double _tmpPurchaseCost;
            if (_cursor.isNull(_cursorIndexOfPurchaseCost)) {
              _tmpPurchaseCost = null;
            } else {
              _tmpPurchaseCost = _cursor.getDouble(_cursorIndexOfPurchaseCost);
            }
            _result = new CowEntity(_tmpId,_tmpName,_tmpTagNumber,_tmpBreed,_tmpPurchaseDate,_tmpPurchaseCost);
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
  public Flow<Integer> observeCount() {
    final String _sql = "SELECT COUNT(*) FROM cows;";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cows"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
