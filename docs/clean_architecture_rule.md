# Nguyên tắc khi xây dựng ứng dụng theo kiến trúc sạch (Clean Architecture)

## Tổng quan
Nguyên tắc chung: Mỗi tầng chỉ quan tâm đến nhiệm vụ của chính nó, không được biết chi tiết của tầng khác.
Hướng phụ thuộc (Dependency Rule): Mũi tên phụ thuộc luôn đi từ ngoài vào trong (UI → UseCase → Repository → DataSource → Service).
Lợi ích: Dễ mở rộng, dễ test, giảm coupling, tăng tính tái sử dụng.

### 1. UI (Presentation Layer)

  #### Vai trò
    Hiển thị dữ liệu, xử lý tương tác người dùng.
    Không chứa logic nghiệp vụ.

  #### Nguyên tắc
    Chỉ được gọi đến UseCase để thực hiện hành động.
    Không được gọi trực tiếp Repository hay DataSource.
    Tránh xử lý logic phức tạp ở UI.

  #### Ví dụ
    Đúng: onClick -> useCase.execute()
    Sai: onClick -> repository.saveToDb()

### 2. UseCase (Domain Layer)

  #### Vai trò
    Chứa logic nghiệp vụ cốt lõi của ứng dụng.
    Định nghĩa hành vi độc lập với UI và Data.

  #### Nguyên tắc
    Chỉ được gọi đến Repository.
    Không gọi trực tiếp DataSource, API, Database.
    Có thể kết hợp nhiều repository để hoàn thành một nghiệp vụ.
    Luôn trả về dữ liệu thuần (Plain Model / Entity) cho UI.

  #### Ví dụ
    Đúng: LoginUseCase gọi AuthRepository.login() rồi trả về User
    Sai: LoginUseCase gọi trực tiếp RetrofitService.loginApi()

### 3. Repository (Data Layer - Abstract)

  #### Vai trò
    Trung gian giữa Domain (UseCase) và DataSource.
    Ẩn chi tiết cài đặt nguồn dữ liệu (API, DB, Cache).
    
  #### Nguyên tắc
    Chỉ được gọi đến DataSource.
    Không chứa logic nghiệp vụ.
    Có thể áp dụng Strategy/Mapper để kết hợp nhiều nguồn dữ liệu.

  #### Ví dụ
    UserRepository.getUser() có thể lấy từ Cache trước, nếu không có thì lấy từ API.

### 4. DataSource (Data Layer - Implementation)

  #### Vai trò
    Là tầng giao tiếp trực tiếp với nguồn dữ liệu (API, Database, Cache, Local file...).

  #### Nguyên tắc
    Chỉ được gọi đến Service (nếu có) hoặc thư viện cụ thể (Retrofit, Room, SharedPref…).
    Không được gọi đến Repository hoặc các tầng khác.
    Chỉ xử lý thao tác dữ liệu (CRUD), không xử lý nghiệp vụ.

  #### Ví dụ
    Đúng: ApiDataSource.fetchUser() dùng Retrofit call API.
    Sai: ApiDataSource tự kiểm tra logic đăng nhập thành công/thất bại.

### 5. Service (Optional)

  #### Vai trò
    Wrapper cho các SDK hoặc thư viện bên ngoài.

  #### Ví dụ: 
    Retrofit Service, Firebase SDK, LocalStorageService.