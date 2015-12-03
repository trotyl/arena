## 需求

### 第一问

* 对打游戏
* 有两个人，下称玩家
* 每个人都有血值
* 有攻击力
* 没有防御力
* 有名字
* 可以互相对打
* 游戏开始，双方互殴，你一下我一下，直到一人死亡，打印出xx被打败了
* 只需要写核心逻辑，不需要考虑界面

输出

```
李四被打败了.
```

### 第二问

* 每次攻击输出谁攻击了谁，被攻击的人掉了多少血，剩多少血。

输出:

```
张三攻击了李四, 李四受到了8点伤害, 李四剩余生命: 12
李四攻击了张三, 张三受到了9点伤害, 张三剩余生命: 1
张三攻击了李四, 李四受到了8点伤害, 李四剩余生命: 4
李四攻击了张三, 张三受到了9点伤害, 张三剩余生命: -8
张三被打败了.
```

### 第三问

* 要有职业分为：普通人和战士。
* 攻击要输出职业+名字
* 被攻击时也输出
* 战士可以装备武器，武器有名字。所以要输出用武器攻击了对方
* 武器有额外的攻击力
* 战士和普通人可以互相攻击
* 战士可以装备防具
* 普通人不可以装备防具或武器
* 战士受到的伤害是对方的攻击力减去防御力

(下列输出没有包含防具,但是记得要实现防具的防御功能，下列输出只是例子，不见得做题时要写的完全一样)

输出1:


```
战士张三用优质木棒攻击了普通人李四, 李四受到了8点伤害, 李四剩余生命: 12
```

输出2：

```
普通人李四攻击了战士张三, 张三受到了9点伤害, 张三剩余生命: 1
```

输出3：

```
普通人张三攻击了普通人李四,李四受到了8点伤害,李四剩余生命：4
```

### 第四问

* 在前三部分的基础上增加，武器可能具有特性，产生各种效果：
  * 毒性伤害，每轮损血
  * 火焰伤害，每轮损血
  * 冰冻伤害，每两轮无法攻击一轮
  * 击晕伤害，两轮无法攻击
  * 全力一击，三倍伤害，是伤害乘以3，不是攻击力
* 有的武器有特性，有的武器没有，一个武器只有一个特性
* 效果触发是随机的。不同的武器有不同的触发几率
* 特性伤害会延时到每局受到伤害的人攻击前才结算，结算时打印伤害受了xx伤害
* 特性伤害都有效果发动的次数，不同的武器造成的伤害发动次数均不同
* 剩余发动伤害次数归零时就不再造成伤害，状态也会消失
* 除了眩晕伤害之外，其他的不显示还剩几轮
* 伤害都可以跟自己累加，一个人中了两次同种伤害，其效果会累加发动次数

示例输出：

输出1（毒性）:

```
战士张三用优质毒剑攻击了普通人李四,李四受到了8点伤害,李四中毒了,李四剩余生命：12
李四受到2点毒性伤害, 李四剩余生命：10
普通人李四攻击了战士张三,张三受到了8点伤害,张三剩余生命：12
```

输出2（眩晕）：

```
战士张三用晕锤攻击了普通人李四,李四受到了8点伤害,李四晕倒了,李四剩余生命：12
李四晕倒了，无法攻击, 眩晕还剩：1轮
```

输出3（全力）：

```
战士张三用利剑攻击了普通人李四,张三发动了全力一击,李四受到了24点伤害,李四剩余生命：-4
```

输出4（累加）：

```
战士张三用晕锤攻击了普通人李四,李四受到了8点伤害,李四晕倒了,李四剩余生命：12
李四晕倒了，无法攻击, 眩晕还剩：1轮
战士张三用晕锤攻击了普通人李四,李四受到了8点伤害,李四晕倒了,李四剩余生命：4
李四晕倒了，无法攻击, 眩晕还剩：2轮
```

### 第五问

* 武器再分化为：长，中，短
* 职业再分化为：刺客，战士，骑士
* 刺客只可以装备中短武器
* 战士只可以装备中武器
* 骑士只可以装备中长武器
* 如果装备了不可以装备的武器，那么会抛出异常
* 这三个职业都可以装备防具
* 长中短武器都可能带有在第四部分提及的特技效果，限制：
  * 只有刺客可以发动短武器技能效果
  * 只有战士可以发动中武器技能效果
  * 只有骑士可以发动长武器技能效果

示例输出：

输出1:

```
刺客张三用峨眉刺攻击了骑士李四,李四受到了8点伤害,李四剩余生命: 4
```

输出2：

```
刺客张三用冰雪峨眉刺攻击了骑士李四,李四受到了8点伤害,李四冻僵了,李四剩余生命: 4
```
