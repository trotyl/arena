# 格斗游戏

需求完备版. 

## 需求

### 总则

1. 所有带有上标 `in` 的专有名词为输入项, 带有上标 `out` 的专有名词为输出项. 
1. 只需完成核心逻辑, 具体的「输入」方式不限, 但需可覆盖所有给定的输入项. 
1. 对于任何给定的输入, 输出结果满足「需求」的逻辑叠加即可, 具体的「实现」过程可与「需求」的单项描述不同. 
1. 当新「需求」与旧「需求」中的一项或多项冲突时, 所有冲突的旧「需求」项被覆盖. 


### 第一问

1. 每一局「游戏」中都有两个独立的「玩家<sup>in</sup>」.
2. 每一名「玩家」都有独立的「名字<sup>in</sup>」.
3. 每一名「玩家」都有独立的「生命值<sup>in</sup>」.
4. 每一名「玩家」都有独立的「攻击力<sup>in</sup>」.
5. 同一「游戏」的两个「玩家」之间都可以相互「攻击」, 发动「攻击」的「玩家」称为「攻击者」, 另一「玩家」称为「被攻击者」, 该「回合」称为「攻击者」的「回合」. 
5. 每一回合需要结算一次「攻击」(除非另有需求指定).
6. 每一次「攻击」的「伤害」为「攻击者」的「攻击力」. 
7. 每一次「攻击」后, 「被攻击者」的「生命值」减少量为「攻击」的「伤害」. 
8. 任何一个「玩家」的「生命值」不大于 0 时, 该「玩家」立即「死亡」. 
9. 任何一个「玩家」「死亡」后, 「游戏」立即结束, 该「玩家」称为「失败者」, 另一「玩家」称为「胜利者」, 游戏结束后不再进行任何结算. 
10. 每一局「游戏」开始后, 两名「玩家」轮流相互「攻击」, 直到「游戏」结束.
10. 对于每一次「游戏」结束, 需要输出「结束日志」.
10. 「结束日志」具有「失败者」的「名字<sup>out</sup>」. 


输出

```
李四被打败了.
```


### 第二问

1. 对于每一次成功结算的「攻击」, 需要输出「攻击日志」.
2. 「攻击日志」具有玩家部分, 伤害部分, 状态部分, 按此顺序排列.
1. 「攻击日志」的「玩家部分」具有「攻击者」的「名字<sup>out</sup>」与「被攻击者」的「名字<sup>out</sup>」.
1. 「攻击日志」的「伤害部分」具有「被攻击者」的「名字<sup>out</sup>」与「攻击」造成的「伤害<sup>out</sup>」.
2. 「攻击日志」的「状态部分」具有「被攻击者」的「名字<sup>out</sup>」与「被攻击者」在「攻击」后的「生命值<sup>out</sup>」.


输出:

```
张三攻击了李四, 李四受到了8点伤害, 李四剩余生命: 12
李四攻击了张三, 张三受到了9点伤害, 张三剩余生命: 1
张三攻击了李四, 李四受到了8点伤害, 李四剩余生命: 4
李四攻击了张三, 张三受到了9点伤害, 张三剩余生命: -8
张三被打败了.
```


### 第三问

1. `1.4` 中所述的「攻击力」改称为「固有攻击力」. 
2. 每一名「玩家」都有「职业<sup>in</sup>」, 为下述选项之一:
    * 「普通人」. 
    * 「战士」. 
3. 每一名「玩家」都有「攻击力」且初始值为「固有攻击力」. 
3. 每一名「玩家」都有「防御力」且初始值为 0. 
3. 每一名「战士」都可以独立装备「武器<sup>in</sup>」, 最多只能装备一件. 
4. 每一件「武器」都有独立的「名字<sup>in</sup>」. 
5. 每一件「武器」都有独立的「攻击力<sup>in</sup>」. 
5. 每一名「战士」装备「武器」后, 该「战士」的「攻击力」增加量为「武器」的「攻击力」. 
6. 每一名「战士」都可以独立装备「防具<sup>in</sup>」, 最多只能装备一件. 
7. 每一件「防具」都有独立的「防御力<sup>in</sup>」. 
5. 每一名「战士」装备「防具」后, 该「战士」的「防御力」增加量为「防具」的「防御力」. 
7. 每一次「攻击」的「伤害」为「攻击者」的「攻击力」减去「被攻击者」的「防御力」, 最低为 0. 
2. 「攻击日志」的「玩家部分」具有「攻击者」的「职业<sup>out</sup>」和「被攻击者」的「职业<sup>out</sup>」, 分别在对应「名字」之前并与之毗连. 
8. 对于每一行「攻击日志」, 当「攻击者」装备有「武器」时, 「玩家部分」具有「武器」的「名字<sup>out</sup>」, 在「攻击者」与「被攻击者」的信息之间. 


输出1:

```
战士张三用优质木棒攻击了普通人李四, 李四受到了8点伤害, 李四剩余生命: 12
```

输出2: 

```
普通人李四攻击了战士张三, 张三受到了9点伤害, 张三剩余生命: 1
```

输出3: 

```
普通人张三攻击了普通人李四,李四受到了8点伤害,李四剩余生命: 4
```


### 第四问 第一部分

1. 每一件「武器」可以具有「特性<sup>in</sup>」, 最多只能有一个特性. 
2. 每一个「特性」可以产生「延时效果」, 也可以影响「攻击」的「伤害」.
3. 每一个「特性」都有独立的「触发概率<sup>in</sup>」, 若成功触发, 则「被攻击者」处于该「延时效果」, 「被攻击者」称为「延时效果」的「宿主」.
3. 所有产生的「延时效果」都有独立的「持续回合数<sup>in</sup>」(除非有另外的「需求」指定), 「持续回合数」之后该「延时效果」消失. 
3. 每一个「特性」具有不同的「类别<sup>in</sup>」, 若触发「延时效果」, 则后者的「类别」与之相同, 「特性」的「类别」为下述选项之一:
    * 「毒性」, 可产生「延时效果」, 后者具有「伤害值<sup>in</sup>」, 造成「宿主」在其每「回合」中受到「伤害」.
    * 「火焰」, 可产生「延时效果」, 后者具有「伤害值<sup>in</sup>」, 造成「宿主」在其每「回合」中受到「伤害」.
    * 「冰冻」, 可产生「延时效果」, 后者造成「宿主」在其每两「回合」(从「延时伤害」出现开始)的前一「回合」不结算「攻击」.
    * 「击晕」, 可产生「延时效果」, 后者造成「宿主」在其「持续回合数」次「回合」内(从「延时伤害」出现开始)不结算「攻击」, 初始「持续回合数」恒为 2 「回合」.
    * 「全力一击, 不产生「延时效果, 但「攻击造成的「伤害增加为原先的 3 倍.
2. 每一「回合」开始时, 若「攻击者」处于「延时效果」中, 则先结算「延时效果」, 之后如果需要再结算「攻击」.
3. 若「特性」触发并产生「延时效果」时, 「被攻击者」已处于该「类别」的「延时效果」当中, 则「被攻击者」所处于的「延时效果」的剩余「持续回合数」的增加量为新触发的「延时效果」的「持续回合数」, 其他现有状态不变.
2. 对于每一次成功结算的「延时效果」, 需要输出「延时效果日志」.
3. 不同「类别」的「延时效果」具有不同的「延时效果日志」, 如下所述:
    * 「毒性」的「延时效果日志」具有「宿主」的「名字<sup>out</sup>」, 「延时效果」的「伤害值<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」, 「宿主」结算「延时效果」后的「生命值<sup>out</sup>」.
    * 「火焰」的「延时效果日志」具有「宿主」的「名字<sup>out</sup>」, 「延时效果」的「伤害值<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」, 「宿主」结算「延时效果」后的「生命值<sup>out</sup>」.
    * 「冰冻」的「延时效果日志」具有「宿主」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」, 另一「玩家」的「名字<sup>out</sup>」.
    * 「眩晕」的「延时效果日志」具有「宿主」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」, 「延时效果」的剩余「持续回合数<sup>out</sup>」.
4. 对于每一行「攻击日志」, 当「攻击者」成功触发「特性」时, 「伤害部分」具有「特性子部分」, 如下所述:
    * 「毒性」的「特性子部分」位于原有部分之后, 具有「被攻击者」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」.
    * 「火焰」的「特性子部分」位于原有部分之后, 具有「被攻击者」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」.
    * 「冰冻」的「特性子部分」位于原有部分之后, 具有「被攻击者」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」.
    * 「眩晕」的「特性子部分」位于原有部分之后, 具有「被攻击者」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」.
    * 「全力一击」的「特性子部分」位于原有部分之前, 具有「攻击者」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」.


例子: （注: 下面的//只是示意, 实际要求跟正常的攻击输出一样. ）

输出1（毒性）:

```
战士张三用优质毒剑攻击了普通人李四,李四受到了8点伤害,李四中毒了,李四剩余生命: 12
李四受到2点毒性伤害, 李四剩余生命: 10
//李四进攻
//张三进攻
李四受到2点毒性伤害, 李四剩余生命: x
//李四进攻
```

输出2（火焰）: 

```
战士张三用火焰剑攻击了普通人李四,李四受到了8点伤害,李四着火了,李四剩余生命: 12
李四受到2点火焰伤害, 李四剩余生命: 10
//李四进攻
//张三进攻
李四受到2点火焰伤害, 李四剩余生命: x
//李四进攻
```

输出3（冰冻）: 

```
战士张三用寒冰剑攻击了普通人李四,李四受到了8点伤害,李四冻僵了,李四剩余生命: 12
//李四进攻
//张三进攻
//李四进攻
//张三进攻
李四冻得直哆嗦, 没有击中张三
//张三进攻
```

输出4（眩晕）: 

```
战士张三用晕锤攻击了普通人李四,李四受到了8点伤害,李四晕倒了,李四剩余生命: 12
李四晕倒了, 无法攻击, 眩晕还剩: 1轮
//张三进攻
李四晕倒了, 无法攻击, 眩晕还剩: 0轮
//张三进攻
//李四进攻
```

输出5（全力）: 

```
战士张三用利剑攻击了普通人李四,张三发动了全力一击,李四受到了24点伤害,李四剩余生命: -4
```

输出6（累加）: 

```
战士张三用晕锤攻击了普通人李四,李四受到了8点伤害,李四晕倒了,李四剩余生命: 12
李四晕倒了, 无法攻击, 眩晕还剩: 1轮
战士张三用晕锤攻击了普通人李四,李四受到了8点伤害,李四晕倒了,李四剩余生命: 4
李四晕倒了, 无法攻击, 眩晕还剩: 2轮
//张三进攻
李四晕倒了, 无法攻击, 眩晕还剩: 1轮
//张三进攻
李四晕倒了, 无法攻击, 眩晕还剩: 0轮
//张三进攻
//李四进攻
```


### 第四问 第二部分

1. 每一件「武器」可以具有多个「特性<sup>in</sup>」. 
2. 每一次结算「攻击」时, 每一件「武器」最多只触发一个「特性」, 当前一「特性」触发失败时结算后一「特性」的触发, 直至有「特性」触发成功或全部触发失败.
3. 每一名玩家只能同时处于一种「延时效果」中, 若「特性」触发并产生「延时效果」时, 「被攻击者」已处于不同「类别」的「延时效果」当中, 则「被攻击者」原先所处于的「延时效果」消失, 变为新触发的「延时效果」.


输出1（多重）: 

```
战士张三用晕锤攻击了普通人李四, 李四受到了8点伤害, 李四晕倒了, 李四剩余生命: 36
李四晕倒了, 无法攻击, 眩晕还剩: 1轮
战士张三用晕锤攻击了普通人李四, 张三发动了全力一击, 李四受到了24点伤害, 李四剩余生命: 12
李四晕倒了, 无法攻击, 眩晕还剩: 0轮
//张三进攻
```


输出2（取代）: 

```
战士张三用晕锤攻击了普通人李四, 李四受到了8点伤害, 李四晕倒了, 李四剩余生命: 12
李四晕倒了, 无法攻击, 眩晕还剩: 1轮
战士张三用晕锤攻击了普通人李四, 李四受到了8点伤害, 李四着火了, 李四剩余生命: 4
李四受到2点火焰伤害, 李四剩余生命: 2
//李四进攻
```


### 第五问

* 武器再分化为长中短
* 职业再分化为
* 刺客, 战士, 骑士
* 这三个职业都可以装备防具

* 刺客只可以装备中短武器
* 战士只可以装备中武器
* 骑士只可以装备中长武器
* 如果装备了不可以装备的武器, 那么会抛出异常

* 长中短武器有各自的职业技能效果
* 只有刺客可以发动短武器技能效果
* 只有战士可以发动中武器技能效果
* 只有骑士可以发动长武器技能效果

* 引入攻击距离与靠近概念,平时双方默认的距离为1
* 普通武器的攻击距离是1,长兵器的攻击距离为2
* 长武器有击退效果,不同的长武器击退距离不一样, 但最多为2, 击退距离+现在的距离=击退后的距离, 比如现在的距离为1, 击退距离为2, 则击退后的距离为3
* 被击退的人下一局要先移动至可以进行攻击的范围, 称之为靠近.靠近与攻击,一局内只能发生其中之一.骑士例外,骑士在一轮中既能靠近也能攻击,战士的一局只能移动1, 而刺客和骑士一局可以移动2
* 短武器有连击效果, 发动连击的人会发动两次攻击, 打印两次攻击在同一行里
* 中武器有防御力,且可以隔挡反击,兵器的防御力平时不记入防御力,当发生隔挡反击的时候计算入防御力.同时完成一次额外的攻击, 如果在攻击距离之外, 只隔当不反击
* 击退, 连击和格挡反击的触发几率都是1／4


输出1:

```
刺客张三用峨眉刺攻击了骑士李四,李四受到了8点伤害,张三发动了连击,李四收到了8点伤害,李四剩余生命: 4
```

输出2: 

```
刺客张三用冰雪峨眉刺攻击了骑士李四,李四受到了8点伤害,李四冻僵了,张三发动了连击,李四收到了8点伤害,李四剩余生命: 4
```

输出3: 

```
骑士张三用长枪攻击了刺客李四,李四受到了8点伤害,李四被击退了,李四剩余生命: 12
李四靠近了张三
//张三进攻
//李四进攻
```

输出4: 

```
骑士张三用长枪攻击了战士李四,李四受到了6点伤害,李四发动了隔挡反击,张三受到了9点伤害.李四剩余生命:14,张三剩余生命:11
//李四攻击

骑士张三用长枪攻击了战士李四,李四受到了6点伤害,李四发动了隔挡反击,张三不在攻击范围内.李四剩余生命:14,张三剩余生命:11
//李四攻击
```
