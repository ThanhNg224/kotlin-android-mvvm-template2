1. [Các Nhánh Chính Trong Gitflow](#1-các-nhánh-chính-trong-gitflow)
2. [Quy Trình Làm Việc Với Gitflow](#2-quy-trình-làm-việc-với-gitflow)
3. [Modifiers](#modifiers)
4. [Trailing commas](#trailing-commas)
5. [Documentation comments](#documentation-comments)
6. [Idiomatic use of language features](#idiomatic-use-of-language-features)

### 1. Các Nhánh Chính Trong Gitflow

# a. Nhánh chính:

main: Lưu trữ lịch sử phát hành chính thức của dự án.
develop: Là nhánh tích hợp các tính năng mới trước khi phát hành.

# b. Nhánh phụ trợ:

fix/*: Nhánh để sửa lỗi phát sinh trong quá trình phát triển, được tạo từ develop.
feature/*: Nhánh để phát triển các tính năng mới, được tạo từ develop.
release/*: Nhánh để chuẩn bị phát hành phiên bản mới, được tạo từ develop.
hotfix/*: Nhánh để sửa lỗi khẩn cấp, được tạo từ main.

# c. Đặt tên nhánh

[Loại-công-việc]/[jira-ticket-ID]-[mô tả-ngắn-gọn-theo-task-lớn]
VD: feature/TG-123-login-social

# d. Đặt tên Commits

[Loại-công-việc][jira-ticket-ID][mô tả-ngắn-gọn-theo-sub-task]
VD:  feature-TG-456-auth-apple
[Loại-công-việc]:
feature: tính năng mới

### 2. Quy Trình Làm Việc Với Gitflow

# a. Sửa lỗi trong quá trình phát triển (Fix Branch)

Tạo nhánh fix từ develop:
git checkout develop # Chuyển sang nhánh develop trước khi tạo nhánh mới
git pull origin develop # Lấy về thay đổi mới nhất từ remote
git checkout -b fix/<fix-name>  # Tạo nhánh fix để sửa lỗi trong quá trình phát triển <chú ý quy tắc Đặt tên nhánh>

Sửa lỗi, commit các thay đổi:
git add . # Thêm tất cả các thay đổi vào staging
git commit -m "Sửa lỗi <fix-name>"  # Ghi lại thay đổi liên quan đến sửa lỗi <chú ý quy tắc Đặt tên commits>
git push origin fix/<fix-name>

Tạo Merge Request trên GitLab: (Nếu dự án đang đảm nhiệm một mình, chưa có reviewer thì bỏ qua bước này, chuyển luôn qua bước 5)
Truy cập Code > Merge Requests trên GitLab.
Nhấn New merge request.
Chọn source branch là fix/<fix-name> và target branch là develop.
Thêm tiêu đề và mô tả.
Chọn reviewer và nhấn Create merge request.
Code Review và Merge: - Trách nhiệm của Lead dự án
Reviewer kiểm tra code và để lại nhận xét.
Nếu đạt yêu cầu, reviewer approve Merge Request.
Merge nhánh fix vào develop:
git checkout develop # Chuyển sang nhánh develop trước khi hợp nhất
git merge --no-ff fix/<fix-name>  # Hợp nhất nhánh fix vào develop, giữ lịch sử rõ ràng
git branch -d fix/<fix-name>  # Xóa nhánh fix ở local
git push origin develop # Đẩy thay đổi từ develop lên remote

# b. Bắt đầu một tính năng mới (Feature Branch)

Tạo nhánh tính năng từ develop:
git checkout develop # Chuyển sang nhánh develop trước khi tạo nhánh mới
git pull origin develop # Lấy về thay đổi mới nhất từ remote
git checkout -b feature/<feature-name> # Tạo nhánh mới để phát triển tính năng <chú ý quy tắc Đặt tên nhánh>

Phát triển tính năng, commit các thay đổi:
git add . # Thêm tất cả các thay đổi vào staging
git commit -m "Mô tả tính năng" # Ghi lại các thay đổi với mô tả phù hợp <chú ý quy tắc Đặt tên Commits>
git push origin feature/<feature-name>

Tạo Merge Request trên GitLab: (Nếu dự án đang đảm nhiệm một mình, chưa có reviewer thì bỏ qua bước này, chuyển luôn qua bước 5)
Truy cập Code > Merge Requests trên GitLab.
Nhấn New merge request.
Chọn source branch là feature/<feature-name> và target branch là develop.
Thêm tiêu đề và mô tả.
Chọn reviewer và nhấn Create merge request.
Code Review và Merge: - Trách nhiệm của Lead dự án
Reviewer kiểm tra code và để lại nhận xét.
Nếu đạt yêu cầu, reviewer approve Merge Request.
Hoàn thành nhánh feature và merge vào develop:
git checkout develop # Chuyển sang nhánh develop trước khi tạo nhánh mới
git merge --no-ff feature/<feature-name> # Hợp nhất nhánh feature vào develop, giữ lịch sử rõ ràng
git branch -d feature/<feature-name> # Xóa nhánh feature ở local
git push origin develop # Đẩy thay đổi từ develop lên remote

# c. Chuẩn bị một bản phát hành (Release Branch) - Trách nhiệm của Lead dự án

Tạo nhánh release từ develop:
git checkout develop # Chuyển sang nhánh develop trước khi tạo nhánh mới
git pull origin develop # Lấy về thay đổi mới nhất từ remote
git checkout -b release/<version-number> # Tạo nhánh mới để review bản release <chú ý quy tắc Đặt tên nhánh>

Kiểm tra và sửa lỗi nếu cần, sau đó commit.
Merge nhánh release vào main trước, sau đó merge vào develop:
git checkout main # Chuyển sang nhánh main để merge fix hoặc release
git merge --no-ff release/<version-number> # Hợp nhất nhánh release vào main, giữ lịch sử commit rõ ràng
git tag -a <version-number> -m "<version-number>" # Tạo tag để đánh dấu phiên bản phát hành
VD: git tag -a 1.2.0 -m "1.2.0"

git push origin <version-number>  # Đẩy tag lên remote
VD: git push origin 1.2.0

Hoặc Push tất cả các tag cùng một lúc:
git push origin --tags

git push origin main # Đẩy thay đổi từ main lên remote

git checkout develop # Chuyển sang nhánh develop trước khi tạo nhánh mới
git merge --no-ff release/<version-number>
git branch -d release/<version-number> # Xóa nhánh release ở local
git push origin develop # Đẩy thay đổi từ develop lên remote

# Khi xảy ra conflict trong quá trình rebase

Khi xảy ra conflict trong quá trình rebase, sẽ có hiển thị như dưới đây (tại thời điểm này sẽ bị tự động chuyển về một branch vô danh)
-----------
$ git rebase master
First, rewinding head to replay your work on top of it...
Applying: refs #1234 Sửa lỗi cache
Using index info to reconstruct a base tree...
Falling back to patching base and 3-way merge...
Auto-merging path/to/conflicting/file
CONFLICT (add/add): Merge conflict in path/to/conflicting/file
Failed to merge in the changes.
Patch failed at 0001 refs #1234 Sửa lỗi cache
The copy of the patch that failed is found in:
/path/to/working/dir/.git/rebase-apply/patch

When you have resolved this problem, run "git rebase --continue".
If you prefer to skip this patch, run "git rebase --skip" instead.
To check out the original branch and stop rebasing, run "git rebase --abort".
---------------
Hãy thực hiện fix lỗi conflict thủ công（những phần được bao bởi <<< và >>> ）. Trong trường hợp muốn dừng việc rebase lại, hãy dùng lệnh git rebase --abort.

Khi đã giải quyết được tất cả các conflict, tiếp tục thực hiện việc rebase bằng:

$ git add .
$ git rebase --continue