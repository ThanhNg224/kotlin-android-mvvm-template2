# Cấu trúc của một Dự Án

#### Base Application
* core
* di
* feature 
  * data 
    * datasource
      * local
      * remote
    * models
    * repositories
  * domain 
    * entities
    * repositories
    * usecases
  * presentation
    * ui
    * viewmodel
    * adapter

## 1. Cách đặt tên files

### 1.1 Class files
Tên class phải được viết theo kiểu [UpperCamelCase](https://en.wikipedia.org/wiki/CamelCase)

Đối với những lớp mở rộng trong Android thì tên của class nên kết thúc bằng tên của lớp mở rộng đó
Ví dụ: MainActivity, HomeFragment, UserViewModel

Đối với những lớp nằm trong feature thì tên của lớp nên bắt đầu bằng tên của feature đó 
Ví dụ: HomeUserBannerAdapter, IapBannerView

View custom mục đích hiển thị thì kết thúc là view. Mục đích click thì kết thúc là button.
Ví dụ: HomeUserAvatarView, IapSubmitButton


### 1.2 Tên file tài nguyên <Resources>
Được đặt theo định dạnh : lowercase_underscore


#### 1.2.1 Tên file Drawables
Quy ước cho tên cho Drawables:

| Kiểu         | Tiền tố                |		Ví dụ                 |
| Icon         | `ic_<feature_name>_`	|  `ic_home_learning.png`     |
| Image        | `img_<feature_name>_`  |  `img_home_spring.png`      |
| Shape        | `shape_<feature_name>_`|  `shape_home_voucher.xml`   |


#### 1.2.2 Tên Layout
Áp dụng cấu trúc Type _object _ purpose. Tên file phải chứa tên thành phần Android, và nó đặt đầu tiên của tên Files. 

| Thành phần        | Tên lớp                | Tên layout                    |
| Activity          | `MainActivity`         | `activity_main.xml`           |
| Fragment          | `SignUpFragment`       | `fragment_sign_up.xml`        |
| Dialog            | `ChangePasswordDialog` | `dialog_change_password.xml`  |
| AdapterView item  | ---                    | `item_person.xml`             |


#### 1.2.3 Đặt tên id trong layout
Áp dụng cấu trúc Type _object _ purpose. Tên id phải chứa tên thành phần Android, và nó đặt đầu tiên của tên id.
| Thành phần              | Tên lớp                | Tên id                          |
| TextView                | `TextView`             | `text_user_name`                |
| ImageView               | `ImageView`            | `image_user_avatar`             |
| Button                  | `Button`               | `button_submit`                 |
| EditText                | `EditText`             | `edit_email`                    |
| RecyclerView            | `RecyclerView`         | `recycler_user_list`            |
| ConstraintLayout        | `ConstraintLayout`     | `layout_user_profile`           |
| LinearLayout            | `LinearLayout`         | `layout_user_info`              |
| RelativeLayout          | `RelativeLayout`       | `layout_user_details`       |
| FrameLayout             | `FrameLayout`          | `layout_container`          | 
| CardView                | `CardView`             | `card_user_item`            |
| View                    | `View`                 | `view_divider`              |
| ScrollView              | `ScrollView`           | `scroll_content`            |
| NestedScrollView        | `NestedScrollView`     | `scroll_content`     |
| Switch                  | `Switch`               | `switch_enable_notifications` |
| CheckBox                | `CheckBox`             | `checkbox_accept_terms`      |
| RadioButton             | `RadioButton`          | `radio_option_one`           |
| ProgressBar             | `ProgressBar`          | `progress_loading`           |
| SeekBar                 | `SeekBar`              | `seek_volume`                |
| ImageButton             | `ImageButton`          | `button_back`           |
| ToggleButton            | `ToggleButton`         | `toggle_dark_mode`           |
| ViewPager               | `ViewPager`            | `pager_images`           |
| TabLayout               | `TabLayout`            | `tabs_main`                  |
| Toolbar                 | `Toolbar`              | `toolbar_main`               |
| FloatingActionButton    | `FloatingActionButton` | `float_add_item`            |
| RecyclerView Adapter item | ---                  | `item_user.xml`              |

* Nếu sử dụng Button custom (như trong phần 3), nếu mục đích là click thì bắt đầu bằng `button_`,
  nếu mục đích là hiển thị thì bắt đầu theo view base của nó như `text_`, `image_`, `layout_`...


#### 1.2.4 Đặt tên id String
* Sử dụng cấu trúc feature_description
* feature: tên của feature mà string đó thuộc về
* description: mô tả ngắn gọn về nội dung của string đó

| Tiền tố           |		Ví dụ          |
| `<feature_name>_`	|  `home_learning`     |


#### 1.2.4 Values files
Tên tập tin nên có thêm __số nhiều__. Ví dụ `strings.xml`, `styles.xml`, `colors.xml`, `dimens.xml`, `attrs.xml`


## 2. Quy ước về comment
* Sử dụng tiếng Việt có dấu để comment
* Sử dụng comment để giải thích các đoạn code phức tạp, không rõ ràng
* Sử dụng comment để phân chia các phần trong code
* Sử dụng comment để mô tả các hàm, lớp, interface
* Sử dụng `// TODO: ` để đánh dấu các công việc cần làm sau này
* Sử dụng `// FIXME: ` để đánh dấu các đoạn code cần sửa chữa
* Sử dụng `// NOTE: ` để ghi chú các thông tin quan trọng
* Sử dụng `// HACK: ` để đánh dấu các đoạn code tạm thời, không nên sử dụng lâu dài
* Sử dụng `// REVIEW: ` để đánh dấu các đoạn code cần được xem xét
* Sử dụng `// OPTIMIZE: ` để đánh dấu các đoạn code cần được tối ưu hóa
* Sử dụng `// DEPRECATED: ` để đánh dấu các đoạn code đã lỗi thời, không nên sử dụng nữa
* Sử dụng `// WARNING: ` để cảnh báo các đoạn code nguy hiểm

## 3. Giới thiệu về view custom trong project
### 3.1. Button
* Nơi đặt file: `core/ui/buttons`
* Mục đích: Custom từ các view mặc định để sử dụng click
* Danh sách các custom buttons:
  * CardButton
  * ConstraintButton
  * FrameButton
  * LinearButton
  * RelativeButton
  * ImageButton
  * TextButton
  * ViewButton
* Các thuộc tính chung:
  * `button_color`: Màu nền của button
  * `button_radius`: Bán kính bo góc của button
  * `button_radiusTopLeft`: Bán kính bo góc trên bên trái của button
  * `button_radiusTopRight`: Bán kính bo góc trên bên phải của button
  * `button_radiusBottomLeft`: Bán kính bo góc dưới bên trái của button
  * `button_radiusBottomRight`: Bán kính bo góc dưới bên phải của button
  * `button_borderColor`: Màu viền của button
  * `button_borderWidth`: Độ rộng viền của button
  * `button_clipToOutline`: Cắt nội dung bên trong button theo viền bo
  * `button_shape`: Hình dạng của button (0: rectangle, 1: circle)
  * `button_background_skip`: Bỏ qua việc thiết lập nền cho button (sử dụng khi muốn tự thiết lập nền)
  * `button_underline`: Gạch chân chữ trong button (chỉ áp dụng cho TextButton)
  * `button_format`: Format chữ trong button (chỉ áp dụng cho TextButton)
  * `button_autoSizeText`: Tự động điều chỉnh kích thước chữ trong button (chỉ áp dụng cho TextButton)
  * `button_backgroundColors`: Gradient background (list màu) — nhận @array hoặc inline string "#FF0000,#00FF00"
  * `button_borderColors`: Gradient border (list màu) — nhận @array hoặc inline string
  * `button_orientation`: Hướng gradient: 0:left_right, 1:top_bottom, 2:tl_br, 3:tr_bl, 4:bl_tr, 5:br_tl

### 3.2. HighlightTextView
* Nơi đặt file: `core/ui/texts`
* Mục đích: Hiển thị đoạn text với các từ được highlight
* Thuộc tính:
  * `highlightKeywords`: Các từ cần được highlight, cách nhau bởi dấu phẩy
  * `highlightColor`: Màu sắc của từ được highlight
  * `highlightFontFamily`: Font chữ của từ được highlight
  * `highlightTextSize`: Kích thước chữ của từ được highlight
  * `highlightUnderline`: Có gạch chân từ được highlight hay không

### 3.3. CustomToast
* Nơi đặt file: `core/ui/toast`
* Mục đích: Hiển thị thông báo dạng toast với giao diện tùy chỉnh
* Cách sử dụng: Context.showToast

### 3.4. FlowLayout
* Nơi đặt file: `core/ui/view_group`
* Mục đích: Hiển thị các phần tử con theo dạng dòng, tự động xuống dòng khi hết chỗ

### 3.5. BaseAnimatedDialog
* Nơi đặt file: `core/ui/dialog`
* Mô tả:
     BaseAnimatedDialog là lớp cơ sở để tạo Dialog có animation tuỳ chỉnh trong Android, không phụ thuộc vào res/anim hay styles.xml.
     Dialog được thiết kế với:
     Animation show/dismiss từ ngoài màn hình vào/ra ngoài màn hình.
     Nền mờ toàn màn hình (dim background).
     Click nền để tắt (có option bật/tắt).
     Ngăn click nền trong thời gian animation.
     Dễ mở rộng, sử dụng ViewBinding.
* Cách sử dụng:
     Tạo một dialog con kế thừa
      Ví dụ: PaymentIapSuccessDialog.kt
  
      fun Context.showPaymentIapSuccessDialog() {
          val dialog = PaymentIapSuccessDialog(this)
          dialog.show()
      }

      private class PaymentIapSuccessDialog(
           context: Context,
      ) : BaseAnimatedDialog<DialogPaymentIapSuccessBinding>(context) {

          override fun createBinding(inflater: LayoutInflater) =
               DialogPaymentIapSuccessBinding.inflate(inflater)

         init {
            binding.buttonClose.setOnClickListener { dismiss() }
         }
      }

     Gọi dialog trong code
          showPaymentIapSuccessDialog()


### 3.6. Đối với dialog dạng bottom sheet
Xử lý tương tự showPaymentBankDialog

### 3.7. ShapeUtils

#### fun rectangle(...) : Drawable?
    Tạo shape hình chữ nhật.
    
    context: Context? – bắt buộc để resolve color resource / attr.
    backgroundColor: Any? – màu nền solid (Int, @ColorRes, String hex).
    backgroundColors: List<Any?>? – danh sách màu gradient (list hex string hoặc list attr/res).
    borderColor: Any? – màu viền solid.
    borderColors: List<Any?>? – danh sách màu gradient viền.
    borderWidth: Float? – độ rộng viền (px).
    radius: Float? – bo góc chung.
    radiusTopLeft / TopRight / BottomRight / BottomLeft: Float? – bo từng góc riêng.
    orientation: GradientOrientation – hướng gradient (default: LEFT_RIGHT).

#### fun oval(...) : Drawable?
    Tạo shape hình oval/circle.
    
    backgroundColor / backgroundColors
    borderColor / borderColors
    borderWidth
    orientation

#### Cách dùng

    Solid background + solid border
      view.background = ShapeUtils.rectangle(
          context,
          backgroundColor = "#FF5722",
          borderColor = "#4CAF50",
          borderWidth = 4f,
          radius = 16f
      )

    Gradient background + gradient border 
      view.background = ShapeUtils.rectangle(
          context,
          backgroundColors = listOf("#FF5722", "#FFC107"),
          borderColors = listOf("#4CAF50", "#8BC34A"),
          borderWidth = 8f,
          radius = 24f,
          orientation = ShapeUtils.GradientOrientation.TOP_BOTTOM
      )

    Oval với gradient
      view.background = ShapeUtils.oval(
          context,
          backgroundColors = listOf("?attr/colorPrimary", "?attr/colorSecondary"),
          borderWidth = 2f,
          borderColor = "?attr/colorOnPrimary"
      )




## 4. Navigate screen trong project

### 4.1. Không có tham số
    findNavController().go(<id_of_action>)
    sử dụng bất kỳ nơi nào có thể lấy được NavController

### 4.2. Có tham số

    Thêm tham số vào action mục tiêu trong navigation graph
        <action
            android:id="@+id/action_homeFragment_to_detailFragment"
            app:destination="@id/detailFragment">
            <argument
                android:name="itemId"
                app:argType="string" />
        </action>

    Sử dụng SafeArgs để truyền tham số
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(itemId)
        findNavController().go(action)
    sử dụng bất kỳ nơi nào có thể lấy được NavController

    Lấy argument trong Fragment đích
        private val args: DetailFragmentArgs by navArgs()
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val itemId = args.itemId
        }


    Nếu không truyền kiểu nguyên thủy (primitive types) qua SafeArgs, mà là object tự định nghĩa
    thì cần implement Parcelable cho object đó
        @Parcelize
        data class Item(val id: String, val name: String) : Parcelable
    Sau đó trong nav_graph.xml:
        <argument
          android:name="item"
          app:argType="com.yourpackage.Item" />
    Và truyền như sau:
        val item = Item("1", "Item 1")
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item)
        findNavController().go(action)
    Lấy argument trong Fragment đích
        private val args: DetailFragmentArgs by navArgs()
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val item = args.item
        }

    Nếu gọi từ Fragment, Trong Fragment phải check:
      if (isAdded && !requireActivity().isFinishing) {
        SettingThemeDialog(requireContext()).show()
      }

    Khi show từ coroutine/LiveData callback, Check lifecycle owner trước:
      lifecycleScope.launch {
          viewModel.event.collect { 
              if (isResumed) {
                  MyDialog(requireContext()).show()
              }
          }
      }

