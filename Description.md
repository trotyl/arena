# 说明文档

主要用于课程 Code Review 环节的讲解。

---

## 为什么说 Java 烂！

1\. Field-like Property (C# 1, C# 3)

在 C# 中，Property 直接采用赋值语句进行存取：

```C#
var tmp = obj1.Prop;
obj1.Prop = obj2.Prop;
obj2.Prop = tmp;
```

而在 Java 中，需要使用 Getter/Setter 函数存取：

```Java
SomeType tmp = obj1.getProp();
obj1.setProp(obj2.getProp());
obj2.setProp(tmp);
```

在 C# 中，简单 Property 可以直接写成 Inline 形式（自动属性）：

```C#
class SomeClass
{
    public SomeType Prop { get; protected set; }
}
```

而在 Java 中，所有 Property 都必须与字段分离：

```Java
class SomeClass {
    private SomeType prop;
    public SomeType getProp() {
        return prop;
    }
    protected void setProp(SomeType val) {
        this.prop = val;
    }
}
```

在 C# 中，（复杂）Property 的 Getter 和 Setter 永远在一起：

```C#
class SomeClass
{
    private SomeType prop;
    public SomeType Prop 
    { 
        get
        {
            return prop;
        } 
        protected set
        {
            this.prop = value;
        }
    }
}
```

而在 Java 中，Property 的 Getter 和 Setter 是两个独立函数：（没变）

2\. 类型推理 (C# 3)

在 C# 中，除了类/结构的成员变量和函数接口外，所有变量都可使用隐式类型：

```C#
var result = someLongTypeObject.SomeMethod();
```

而在 Java 中，所有类型信息都必须写出：

```Java
SomeLongType result = someLongTypeObject.someMethod();
```

3\. Lambda 表达式作为成员方法 (C# 6)

在 C# 中，简单成员方法可以直接采用 Lambda 表达式声明：

```C#
class Player
{
    private int aggressivity;
    private Weapon weapon;
    public int Aggressivity() => aggressivity + weapon.Aggressivity;
}
```

而在 Java 中，所有方法必须声明完整签名：

```Java
class Player {
    private int aggressivity;
    private Weapon weapon;
    public int aggressivity() { 
        return aggressivity + weapon.getAggressivity();
    }
}
```

4\. String Format (C# 1, C# 6)

在 C# 中，String 格式化采用 Indexed-based 模板，可以多次插入同一内容：

```C#
var result = Format("{2}{0}用晕锤攻击了{3}{1}, {1}受到了{4}点伤害, {1}晕倒了, {1}剩余生命：{5}",
    procedure.Attacker.Name, procedure.Attackable.Name,
    procedure.Attacker.Role, procedure.Attackable.Role,
    procedure.Damage.Extent, procedure.Attackable.Health);
```

而在 Java 中，对于相同的内容需要重复多次输入：

```Java
String result = format("%s%s用晕锤攻击了%s%s, %s受到了%d点伤害, %s晕倒了, %s剩余生命：%d",
    procedure.getAttacker().getRole(), procedure.getAttacker().getName(), 
    procedure.getAttacker().getRole(), procedure.getAttackable().getName(),
    procedure.getAttackable().getName(), procedure.getDamage().getExtent(), 
    procedure.getAttackable().getName(), procedure.getAttackable().getHealth());
```

在 C# 中，简单格式化可以使用 Interpolation 来直接生成：

```C#
var result = $"{ procedure.Attackable.Name }晕倒了, 无法攻击, 眩晕还剩: { procedure.Effect.Remain }轮";
```

而在 Java 中，所有格式化都必须调用响应函数并传递参数：

```Java
String result = format("%s晕倒了, 无法攻击, 眩晕还剩: %d轮", procedure.host.getName(),
                                                            procedure.host.getRemain());
```

5\. 引用类型 Null 检查 (C# 2, C# 6)

在 C# 中，对于深层对象调用，可以使用空条件运算符无视 Null 情况：

```C#
var type = game?.Player1?.Weapon?.Attribute?.Effect?.Type;
```

而在 Java 中，需要手动检查或创建大量的特殊对象：

```C#
MyType type = null;
if (game != null && game.player1? != null && game.player1.weapon != null && game.player.weapon.attribute != null && game.player1.weapon.attribute.effect != null) {
    type = game.player1.weapon.attribute.effect.type;
} 
```

在 C# 中，可以使用空接合运算符来设置默认值：

```C#
var prop = expression ?? new Prop();
```

而在 Java 中，需要使用条件语句或条件表达式：

```Java
SomeType prop = expression;
prop = (prop != null? prop : new Prop());
```

6\. 关键字 （C# 1）

在 C# 中，可以使用语言关键字作为变量名：

```C#
var @short = new Weapon("...", 0, Length.@short); //枚举本应该使用大写，此处仅为用法说明
```

而在 Java 中，不能使用语言关键字：

```Java
Weapon thisIsShort = new Weapon("...", 0, Length.iMeanShort);
```

7\. 模式匹配 （C# 7）

在 C# 中，可以直接使用模式匹配来判别：

```C#
public String PrintEffect(Effect effect) => effect match (
    case Toxin toxin:
        return $"...{toxin.SomeThing}...毒性伤害...";
    case Flame flame:
        return $"...{flame.SomeThing}...火焰伤害...";
    case Freeze freeze when freeze.Works:
        reutrn $"...{freeze.SomeThing}...冻僵了, 无法攻击...";
    case Swoon swoon:
        reutrn $"...{swoon.SomeThing}...晕倒了, 无法攻击, 眩晕还剩...";
    case *:
        return "";
);
```

而在 Java 中，自定义类型必须手动判断或者使用 Map 查找：

```Java
public String printEffect(Effect effect) {
    if (effect instanceof Toxin) {
        Toxin toxin = (Toxin) effect;
        return format("...毒性伤害...", toxin.someThing);
    } else if (effect instanceof Flame) {
        Flame flame = (Flame) effect;
        return format("...火焰伤害...", flame.someThing);
    } else if (effect instanceof Freeze) {
        Freeze freeze = (Freeze) effect;
        if (freeze.works()) {
            return format("...冻僵了, 无法攻击...", freeze.someThing);
        }
        return "";
    } else if (effect instanceof Swoon) {
        Swoon swoon = (Swoon) effect;
        return format("...晕倒了, 无法攻击, 眩晕还剩...", swoon.someThing);
    } else {
        return "";
    }
}
```

8\. 默认参数 （C# 4）

在 C# 中，可以为方法的参数提供默认值，调用时可以忽略：

```C#
public create(string name, int aggressivity=0, int health=1, Role role=Role.Normal, Weapon weapon=null, Armor armor=null)
{
    // Todo
}
```

而在 Java 中，必须为每种调用方式提供重载：

```Java
public create(String name) {
    // Todo
}

public create(String name, int aggressitity, int health) {
    // Todo
}

public create(String name, int aggressitity, int health, Role role) {
    // Todo
}

public create(String name, int aggressitity, int health, Role role, Weapon weapon, Armor armor) {
    // Todo
}
```

9\. 字符串字面值 （C# 1）

在 C# 中，可以直接使用字面值来创建多行文本：

```C#
var json = @"
{
    ""name"": ""张三"",
    ""role"": ""fighter"",
    ""health"": 10,
    ""aggressivity"": 5,
    ""weapon"": 
    {
        ""name"": ""优质木棒"",
        ""aggressivity"": 5,
        ""length"": ""medium""
    },
    ""armor"":
    {
        ""defence"": 8
    }
}
";
```

而在 Java 中，需要进行人肉拼接：

```Java
String json = "" +
"{" +
"  \"name\": \"张三\"," +
"  \"role\": \"fighter\"," +
"  \"health\": 10," +
"  \"aggressivity\": 5," +
"  \"weapon\": " +
"  {" +
"    \"name\": \"优质木棒\"," +
"    \"aggressivity\": 5," +
"    \"length\": \"medium\"" +
"  }," +
"  \"armor\": " +
"  {" +
"    \"defence\": 8" +
"  }" +
"}";
```

10\. Tuple （C# 7）

在 C# 中，可以直接使用 Tuple 作为函数返回值并隐式解封装：

```C#
public (EffectProcedure effect, AttackProcedure attack) Run() 
{
    // Todo
    return (effect, attack);
}
```

```C#
(var effect, var attack) = game.Run();

var effectString = formatter.FormatEffect(effect);
var attackString = formatter.FormatAttack(attack);
```

而在 Java 中，必须要手动封装和解封装 Tuple：

```Java
public Tuple<EffectProcedure, AttackProcedure> run() {
    // Todo
    return new Tuple<>(effect, attack);
}
```

```Java
Tuple<EffectProcedure, AttackProcedure> tuple = game.run();
EffectProcedure effect = tuple.getValue0();
AttackProcedure attack = tuple.getValue1();

String effectString = formatter.formatEffect(effect);
String attackString = formatter.formatAttack(attack);
```

// 11\. 值类型与可空值类型 (C# 1, C# 2)

// 12\. 扩展方法 (C# 3)

// 13\. 动态类型 (C# 4)

// 14\. 异步语法 (C# 5)

---

## 题目中的逻辑陷阱

1\. Walking Dead

对于部分 Debuff 来说（目前是火焰和毒性），在进攻前会对进攻者产生伤害，可导致进攻者死亡。

如过不先检查就直接攻击，可能产生死者继续英勇奋战的效果。

因此：

+ 对于每次攻击为一次循环的设计来说，每次循环至少需要 **2** 次检查；
+ 对于每一对相互攻击为一次循环的设计来说，每次循环至少需要 **4** 次检查；


2\. 伤害吸收

攻击者的攻击力可能低于被攻击者的防御力，如果不做检查，可能会产生加血效果。


3\. 时光倒流

部分事件（中毒、着火、冻僵、晕倒）出现在伤害事件之后，如：

```
战士张三用优质毒剑攻击了普通人李四, 李四受到了8点伤害, 李四中毒了, 李四剩余生命：12
```

部分事件（发动全力一击）出现在伤害事件之前，如：

```
战士张三用利剑攻击了普通人李四, 张三发动了全力一击, 李四受到了24点伤害, 李四剩余生命：-4
```

如果不针对事件进行区分，可能会产生被攻击者先受伤，攻击者在发动攻击技能的情况。

---

## 项目中的实际问题

1\. 用 Git 又不加 .gitignore

可以自己 `git clone` 下感受下效果。

2\. 所有项目放在同一个 Repository

可以自己 `git clone` 下感受下效果。

3\. Git config 的邮箱与 Github 的邮箱不同

不会被记录到个人的 Contributions 中。

4\. 直接使用 fork 的 Repository

不会被记录到个人的 Contributions 中。

5\. 把 main 方法当测试用

main 方法是程序入口，应该提供真实输入输出，Hardcoding 看结果的事已经在测试中做过了，没有必要再做一遍。

如果就是做类库，那就不要留 main 方法。

6\. 待定
