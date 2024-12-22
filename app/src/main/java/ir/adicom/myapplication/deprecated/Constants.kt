package ir.adicom.myapplication.deprecated

object Constants {
    const val BASE_URL = "https://vinaybyte.github.io/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w342"

    //Screens
    const val MAIN_SCREEN = "main_screen"
    const val DETAIL_SCREEN = "detail_screen"

}

//├─ 📁 **ui** (to keep all your UI related things)
//|	 ├─ 📁 **screens** (where you define your screens composables and their corresponding view models)
//|	 |  └─ 📁 **home**
//|	 |     ├─ 📝 **HomeScreen.kt** (the UI for the Home screen)
//|	 |     └─ 📝 **HomeViewModel.kt** (the view model for the Home screen)
//|	 ├─ 📁 **components** (where you define components that are shared across multiple screens)
//|	 |  └─ 📝 **UserList.kt**
//|  └─ 📁 **theme** (where you keep your theme definition and design tokens)
//|     ├─ 📝 **Colors.kt**
//|     ├─ 📝 **Shapes.kt**
//|     ├─ 📝 **Theme.kt**
//|     └─ 📝 **Typography.kt**
//├─ 📁 **utils** (where you keep your various utility functions, like data converters etc...)
//|  └─ 📝 **DateUtils.kt**
//└─ 📝 **MainActivity.kt** (this is your default MainActivity)
