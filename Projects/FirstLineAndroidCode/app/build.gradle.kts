plugins {
    /**
     * 一般有两种值可选：com.android.application表示这是一个应用程序模块，com.android.library表示这是一个库模块。
     * 二者最大的区别在于，应用程序模块是可以直接运行的，库模块只能作为代码库依附于别的应用程序模块来运行。
     * */
    id("com.android.application")
    /**
     * kotlin插件，使用kotlin语言开发必须的
     * */
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.firstlineandroidcode"
    compileSdk = 34

    defaultConfig {
        /**
         * applicationId是每一个应用的唯一标识符，绝对不能重复，
         * 默认会使用我们在创建项目时指定的包名，如果你想在后面对其进行修改，那么就是在这里修改的。
         * */
        applicationId = "com.example.firstlineandroidcode"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            /**
             * minifyEnabled用于指定是否对项目的代码进行混淆，true表示混淆，false表示不混淆。
             * */
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

/**
 * 指定当前项目所有的依赖关系。
 * 通常Android Studio项目一共有3种依赖方式：
 * *** 本地依赖：对本地的jar包或目录添加依赖关系
 * *** 库依赖：对项目中的库模块添加依赖关系
 * *** 远程依赖：对jcenter仓库上的开源项目添加依赖关系
 * */
dependencies {
    /**
     * implementation fileTree就是一个本地依赖声明，
     * 它表示将libs目录下所有.jar后缀的文件都添加到项目的构建路径
     * */
//    implementation fileTree(dir: 'libs', include: ['*.jar'])

    /**
     * implementation则是远程依赖声明
     * androidx.core 域名部分
     * core-ktx 库工程名
     * 1.12.0 版本号
     * */
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.work:work-runtime:2.7.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    /**
     * 至于库依赖声明这里没有用到，它的基本格式是implementation project后面加上要依赖的库的名称，
     * 比如有一个库模块的名字叫helper，
     * 那么添加这个库的依赖关系只需要加入implementationproject(':helper')这句声明即可。
     * */
}