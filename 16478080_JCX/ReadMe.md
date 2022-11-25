## 期末测试

#### 环境配置

1. Intellij idea 2019.1.3
2. jdk 8U191
3. gradle 5.4.1
4. sdk 9 API 28

#### 考试要求

1.	新建项目以“学号_姓名”为工程名；
2.	Package name为edu.pxxy.姓名缩写,例：edu.pxxy.zs；
3.	项目完成后上传到服务器中；
4.	必须将项目托管在GITEE上，请在MainActivity中加入注释填写GITEE地址:
```
public class MainActivity extends AppCompatActivity {
    // GITEE: 项目GIT地址
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```

### 项目要求

1.	使用SQLiteOpenHelper类创建数据库，名为姓名缩写.db，如张三（zs.db），版本号为1，并在数据库中创建一张food表，字段信息见下表；

字段名|数据类型|备注
:---:|:---:|:---:
_id|Integer|主键、自增长
foodname|Text|菜名
foodprice|Real|菜价
count|Integer|总计销量

2.	使用ADB SHELL或API方法插入初始数据如下；

菜名（foodname）|价格（foodprice）|总销量（count）
:---:|:---:|:---:
小炒肉|20|0
青椒肉丝|24|0
西红柿炒鸡蛋|12|0
红烧鱼头|8|0
红烧猪蹄|25|0
红烧鱼块|18|0
紫菜蛋汤|10|0

3.	创建用于封装数据的Food类；
4.	为food表设计一个数据访问接口dao，并定义操作数据表的CRUD方法；
5.	创建dao接口的实现类，完成抽象方法的实现；
6.	在Activity中创建ListView，要求每一个Item中要显示菜品的“菜名”，“价格”和“销量”；
7.	创建FoodAdapter适配器类，继承自BaseAdapter。用于适配ListView中每一个Item的数据（必须要使用到ListView中的缓存机制）；
8.	项目运行时调用DaoImpl中的findAllFood方法查询数据库中所有的数据，并将数据传递给FoodAdapter，FoodAdapter拿到数据后对Item进行数据适配并显示在页面上。（程序运行就要看见所有菜品的信息展示在页面上）；
9.	点击“menu”菜单会弹出“管理菜品”的跳转，跳转到管理菜品页面后可以对已有菜品进行增删改查操作。
