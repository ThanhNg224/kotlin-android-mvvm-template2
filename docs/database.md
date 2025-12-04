# 1. Tổng quan
Project dùng 1 Room Database duy nhất (AppDatabase) khai báo ở core/helpers/storage/database/

Mỗi feature sẽ tự định nghĩa:
- Entity (ánh xạ DB table): <feature>/data/models/
- Dao (query SQL): <feature>/data/models/
- Migration (nếu có thay đổi DB schema): core/helpers/storage/database/migrations/DatabaseMigrations.kt


Tất cả Entity/Dao từ các feature sẽ được gom lại trong AppDatabase.
Với một số tài liệu hướng dẫn thì định nghĩa DTO (Data Transfer Object) (trong tầng data/models) để ánh xạ kết quả query 
JSON, còn trong project này sẽ định nghĩa là Model.


# 2. Cấu trúc thư mục
core/helpers/storage/database/
├─ AppDatabase.kt            # Database chính
├─ dao/BaseDao.kt            # CRUD chung
└─ migrations/               # Migration tập trung

di/DatabaseModule.kt      # Hilt module cung cấp DB & Dao

feature_xxx/
├─ data/
│   ├─ datasources/          # LocalDataSource (Dao), RemoteDataSource 
│   ├─ models/               # Entity (DB), Model
│   └─ repositories/         # RepositoryImpl
├─ domain/
│   ├─ entities/             # Domain entity
│   ├─ repositories/         # Repository interface
│   └─ usecases/             # Business logic
└─ presentation/
├─ viewmodel/            # ViewModel
└─ ui/                   # UI

# 3. Cách thêm bảng mới (Entity + Dao)

3.1. Vào feature_xxx/data/models/ → tạo Entity mới:
    @Entity(tableName = "alphabet_lessons")
    data class AlphabetLessonEntity(
        @PrimaryKey val id: String,
        val title: String,
        val updatedAt: Long
    )

3.2. Vào feature_xxx/data/datasources/ → tạo Dao mới:
    @Dao
    interface AlphabetLessonsDao : BaseDao<AlphabetLessonEntity> {
        @Query("SELECT * FROM alphabet_lessons ORDER BY updatedAt DESC")
        suspend fun getAll(): List<AlphabetLessonEntity>
    }

3.3. Update AppDatabase.kt trong core/helpers/storage/database/:
    @Database(
        entities = [
            ...
            AlphabetLessonEntity::class
        ],
        version = 1,
        exportSchema = false
    )
    abstract class AppDatabase : RoomDatabase() {
        ...
        abstract fun alphabetLessonsDao(): AlphabetLessonsDao
    }

3.4. Migration:
    Khi thay đổi cấu trúc bảng → tăng version trong AppDatabase.
    Viết migration trong core/helpers/storage/database/migrations/DatabaseMigrations.kt

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE alphabet_lessons ADD COLUMN description TEXT")
            }
        }



