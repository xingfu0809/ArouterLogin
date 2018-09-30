
# ARouter 爬坑之旅


# 效果图
<img src="icon.gif" width="320px"/>

## 1、ARouter功能介绍
1. **支持直接解析标准URL进行跳转，并自动注入参数到目标页面中**
2. **支持多模块工程使用**
3. **支持添加多个拦截器，自定义拦截顺序**
4. **支持依赖注入，可单独作为依赖注入框架使用**
5. **支持InstantRun**
6. **支持MultiDex(Google方案)**
7. **映射关系按组分类、多级管理，按需初始化**
8. **支持用户指定全局降级与局部降级策略**
9. **页面、拦截器、服务等组件均自动注册到框架**
10. **支持多种方式配置转场动画**
11. **支持获取Fragment**
12. **完全支持Kotlin以及混编()**
13. **支持第三方 App 加固**(使用 arouter-register 实现自动注册)
14. **支持生成路由文档**

## 2、用法
### 2.1 添加依赖和配置
```gradle
android {
    defaultConfig {
        ...
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [AROUTER_MODULE_NAME: project.getName()]
            }
        }
    }
}

dependencies {
      implementation 'com.alibaba:arouter-api:1.4.0'
      annotationProcessor 'com.alibaba:arouter-compiler:1.2.1'
    ...
}
```

### 2.2 初始化sdk
```java
初始化阿里巴巴路由框架
        if (ConfigConstants.IS_DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)// 尽可能早，推荐在Application中初始化
```


### 2.3 添加注解
```java
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         // 添加自动属性注入 这句话可以放在baseActivity中
        ARouter.getInstance().inject(this);
    }
```
```java
    // 对应的Activity 添加@Route注解 path路径 这里的路径需要注意的是至少需要有两级，/xx/xx
    @Route(path = ConfigConstants.FIRST_PATH)
    public class FirstActivity extends BaseActivity {
    
        private TextView tvMsg;
    
        @Autowired
        public String msg;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_first);
            tvMsg = findViewById(R.id.tv_msg);
            tvMsg.setText(msg);
    
        }
    }
```
### 2.4 拦截器的使用面向切面编程
```java


    // 比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
    // 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行    
    @Interceptor(name = "login", priority = 6)
    public class LoginInterceptorImpl implements IInterceptor {
        @Override
        public void process(Postcard postcard, InterceptorCallback callback) {
            String path = postcard.getPath();
            LogUtils.d(path);
            boolean isLogin = SPUtils.getInstance().getBoolean(ConfigConstants.SP_IS_LOGIN, false);
    
            if (isLogin) { // 如果已经登录不拦截
                callback.onContinue(postcard);
            } else {  // 如果没有登录
                switch (path) {
                    // 不需要登录的直接进入这个页面
                    case ConfigConstants.LOGIN_PATH:
                    case ConfigConstants.FIRST_PATH:
                        callback.onContinue(postcard);
                        break;
                    default:
                        callback.onInterrupt(null);
                        // 需要登录的直接拦截下来
                        break;
                }
            }
    
        }
    
        @Override
        public void init(Context context) {
            LogUtils.v("路由登录拦截器初始化成功"); //只会走一次
        }
    
    }
    // activity启动的时候
    ARouter.getInstance().build(ConfigConstants.SECOND_PATH)
                             .withString("msg", "ARouter传递过来的需要登录的参数msg")
                              .navigation(this,new LoginNavigationCallbackImpl()); // 第二个参数是路由跳转的回调
         
     
    // 拦截的回调
    public class LoginNavigationCallbackImpl  implements NavigationCallback{
        @Override //找到了
        public void onFound(Postcard postcard) {
    
        }
    
        @Override //找不到了
        public void onLost(Postcard postcard) {
    
        }
    
        @Override    //跳转成功了
        public void onArrival(Postcard postcard) {
    
        }
    
        @Override
        public void onInterrupt(Postcard postcard) {
            String path = postcard.getPath();
            LogUtils.v(path);
            Bundle bundle = postcard.getExtras();
            // 被登录拦截了下来了 
            // 需要调转到登录页面，把参数跟被登录拦截下来的路径传递给登录页面，登录成功后再进行跳转被拦截的页面
            ARouter.getInstance().build(ConfigConstants.LOGIN_PATH)
                    .with(bundle)
                    .withString(ConfigConstants.PATH, path)
                    .navigation();
        }
    }


```

1. 页面之间跳转使用阿里巴巴ARouter跳转，其中注意带返回值启动的时候请求吗不能为0否则没有回调onActivityResult

2. 使用ARouter传递参数的时候如果有传递Bundle跟其他的属性Bundle必须写在前面否则其他的属性注入不了




