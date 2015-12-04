## 说明文档

主要用于课程 Code Review 环节的讲解。

### 为什么说 Java 烂！

1\. Property

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

在 C# 中，简单 Property 可以直接写成 Inline 形式：

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

2\. 类型推理

在 C# 中，除了类/结构的成员变量和函数接口外，所有变量都可使用隐式类型：

```C#
var result = someLongTypeObject.SomeMethod();
```

而在 Java 中，所有类型信息都必须写出：

```Java
SomeLongType result = someLongTypeObject.someMethod();
```

3\. 成员方法

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
        return aggressivity + weapon.aggressivity();
    }
}
```

4\. String 格式化

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
    procedure.getAttacker().get()Role, procedure.getAttackable().getName(),
    procedure.getAttackable().getName(), procedure.getDamage().getExtent(), 
    procedure.getAttackable().getName(), procedure.getAttackable().getHealth());
```

在 C# 中，简单格式化可以使用 Interpolation 来直接生成：

```C#
var result = $"{ procedure.Attackable.Name }晕倒了, 无法攻击, 眩晕还剩: { procedure.Effect.Remain }轮";
```

而在 Java 中，所有格式化都必须调用响应函数并传递参数：

```Java
String result = format("%s晕倒了, 无法攻击, 眩晕还剩: %d轮", procedure.attackable.getName(), 
                                                             procedure.attackable.getRemain());
```


5\. 对象 Null 检查


