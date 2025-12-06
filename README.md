# Java_shopping_web

1. Nguyên Tắc, Mẫu Thiết Kế và Thực Hành Phát Triển Phần Mềm
   ***Nguyên tắc:
	-Phân Tách Mối Quan Tâm: Web được chia thành các lớp khác nhau như Controller, Service và Repository để mỗi lớp có trách nhiệm riêng biệt.
	-Nguyên Tắc Trách Nhiệm Đơn Lẻ: Mỗi lớp và phương thức đảm nhiệm trách nhiệm duy nhất. Ví dụ, CartServiceImpl xử lý logic liên quan đến giỏ hàng, CartItemServiceImpl xử lý quản lý mục giỏ hàng.
	-Interface Segregation Principle (ISP): Chia nhỏ interface thành nhiều interface nhỏ để các lớp cài đặt không bị ràng buộc bởi các phương thức không cần thiết.
   ***Các Mẫu Thiết Kế Áp Dụng:
	-Sử dụng cơ chế dependency injection của Spring để tiêm các phụ thuộc như repository và service vào các lớp service nhằm tách biệt các thành phần.
	-Factory Pattern: Để tạo ra các đối tượng như Cart hoặc CartItem, mẫu thiết kế factory được áp dụng để các đối tượng này được tạo ra với các phụ thuộc cần thiết, giúp giảm sự phụ thuộc chặt chẽ.
	- Ứng dụng tuân theo mô hình MVC với:
		--Model: Lớp trong thư mục models/.
		--View: Có thể là các trang HTML hoặc Thymeleaf, mặc dù không thấy 	trực tiếp trong cấu trúc này.
		--Controller: Các lớp controller dùng để điều hướng và xử lí login.
		--Services và Repository: Các lớp trong thư mục Services/ và 		Repository/ là các thành phần dịch vụ và kho lưu trữ (Repository), thực hiện 	xử lý nghiệp vụ và tương tác với dữ liệu.
   ***Thực Hành:
	-Test-Driven Development (TDD): Viết kiểm thử trước khi viết mã chính.
	-Thực hiện đánh giá mã để đảm bảo chất lượng và tuân thủ các nguyên tắc.
	-Thiết Kế API RESTful: Ứng dụng sử dụng các nguyên lý REST để xác định các endpoint API, đảm bảo các tài nguyên (như Cart, CartItem, và Product) có thể được thao tác thông qua các phương thức HTTP (GET, POST, PUT, DELETE).
2. 
   ***Cấu trúc mã:
src/
 ├── main/
 │    ├── java/
 │    │    ├── vn/
 │    │    │    ├── tdtu/
 │    │    │    │    ├── edu/
 │    │    │    │    │    ├── springcomerce/
 │    │    │    │    │    │    ├── Services/
 │    │    │    │    │    │    │    ├── Impl/
 │    │    │    │    │    │    │    │    ├── CartServiceImpl.java
 │    │    │    │    │    │    │    │    ├── CartItemServiceImpl.java
 │    │    │    │    │    │    │    ├── CartService.java
 │    │    │    │    │    │    │    ├── CartItemService.java
 │    │    │    │    │    │    ├── Repository/
 │    │    │    │    │    │    │    ├── CartRepo.java
 │    │    │    │    │    │    │    ├── CartItemRepo.java
 │    │    │    │    │    │    │    ├── ProductRepo.java
 │    │    │    │    │    │    ├── models/
 │    │    │    │    │    │    │    ├── Cart.java
 │    │    │    │    │    │    │    ├── CartItem.java
 │    │    │    │    │    │    │    ├── Product.java
 │    │    │    │    │    │    │    ├── User.java
 │    │    │    │    │    │    ├── SecurityConfig/
 │    │    │    │    │    │    │    ├── SecurityConfig.java
 │    │    │    │    │    │    │    ├── MvcConfig.java
 │    │    │    │    │    │    ├── Security/
 │    │    │    │    │    │    │    ├── SecurityCustomUserService.java
 │    ├── resources/
 │    │    ├── application.properties
 │    │    ├── templates/
 │    │    │    ├── index.html
 │    │    │    ├── cart.html
 │    │    │    ├── productList.html
 │    │    │    ├── ...
 └── test/
      ├── java/
           ├── vn/
           │    ├── tdtu/
           │    │    ├── edu/
           │    │    │    ├── springcomerce/
           │    │    │    │    ├── Services/
           │    │    │    │    │    ├── Impl/
           │    │    │    │    │    │    ├── CartServiceImplTest.java
           │    │    │    │    │    │    ├── CartItemServiceImplTest.java
   ***Giải thích:
	- Services/Impl/: Chứa các lớp service thực thi như CartServiceImpl, CartItemServiceImpl dùng để thực hiện các thao tác chính.
	- Repository/: Chứa các interface repository (như CartRepo, CartItemRepo, ProductRepo) để tương tác với cơ sở dữ liệu.
	- models/: Chứa các lớp entity như Cart, CartItem, Product, và User, đại diện cho các bảng cơ sở dữ liệu và để lưu trữ các đối tượng dùng cho các lớp thực thi.
	- test/: Chứa các bài kiểm tra đơn vị cho các lớp service để đảm bảo chức năng hoạt động đúng (ví dụ: CartServiceImplTest.java, CartItemServiceImplTest.java).
	- templates/: chứa các file html dùng để hiển thị trang web.
	- SecurityConfig/: chứa các file dùng để đảm bảo các trang web được bảo mật và Security/ dùng để phân quyền của người đăng nhập.
3. Các bước chạy:
   *** Yêu cầu:
	- Để đảm bảo nhất thì cần chạy java 17 trở lên.
	- Database docker
	- Để mở code cần Intellij hoặc Eclipse
   *** Cách chạy:
	1. Clone github: 
	2. Mở terminal của intellij hoặc Eclipse gõ "cd docker", cũng trong terminal gõ tiếp docker-compose up và đợi đến khi chạy xong.
	3. Mở DBeaver chọn database trên thanh công cụ -> chọn new Database Connection -> chọn MySql -> chọn Next -> trong trường connected by chọn URL -> copy và paste (jdbc:mysql://localhost:3307/springweb?useSSL=false&allowPublicKeyRetrieval=true) vào trường URL -> trong trường password của authentication nhập "123" -> test connect rồi nhấn finish. (Tên database: springweb và password: 123)
	4. Mở docker, tìm "docker-middle-1" để chạy.
	5. Trở lại Intellij bấm run.
	6. Lên trình duyệt gõ localhost:8080 để vào trang web.
4. Chạy các Endpoint API:
	- POSTMAN và ERD theo đường link: 	https://docs.google.com/document/d/1OHfS6KSpHgCRZX6ks9e9ulDGAXB-NV6xdE-zrcLWpfM/edit?tab=t.0
5. Video Demo theo link:
	https://drive.google.com/file/d/1hUEao3L_8xgfqCppZdDpJ2T91bnWUB41/view?usp=drive_link

