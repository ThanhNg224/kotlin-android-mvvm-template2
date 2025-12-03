# Hướng dẫn gen test cho kiến trúc Clean Architecture
Luu ý: Chỉ áp dụng cho các feature có cấu trúc thư mục đúng theo chuẩn.
Cần có Python 3 

1. python3 scripts/generate_tests.py <folder_feature_name>

2. Tích hợp vào Gradle
    - Thêm đoạn code sau vào file `build.gradle` của module app:
    ```groovy
    task generateTests(type: Exec) {
    def feature = project.findProperty("feature") ?: ""
    if (feature == "") {
        throw new GradleException("Please provide -Pfeature=<name>")
    }
    commandLine "python3", "scripts/generate_tests.py", feature
    }
    ```
    - Chạy lệnh sau để gen test:
      ./gradlew generateTests -Pfeature=<folder_feature_name>
   
3. Tạo alias trong shell (zsh/bash):
   alias gentest='python3 scripts/generate_tests.py'
   
   Chạy lệnh sau để gen test:
      gentest <folder_feature_name>
