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
6. 每一次「攻击」的「伤害」为「攻击者」的「攻击力」. (即「玩家」没有「防御力」.)
7. 每一次「攻击」后, 「被攻击者」的「生命值」减少量为「攻击」的「伤害」. 
8. 任何一个「玩家」的「生命值」不大于 0 时, 该「玩家」立即「死亡」. 
9. 任何一个「玩家」「死亡」后, 「游戏」立即结束, 该「玩家」称为「失败者」, 另一「玩家」称为「胜利者」, 游戏结束后不再进行任何结算. 
10. 每一局「游戏」开始后, 两名「玩家」轮流相互「攻击」, 直到「游戏」结束. (即若「游戏」永不结束, 则「游戏」永远不终止运行.)
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

1. 每一名「玩家」都有「职业<sup>in</sup>」, 为下述选项之一:
    * 「普通人」. 
    * 「战士」.
2. 每一名「玩家」都有「防御力」且初始值为 0.
3. 每一名「职业」为「战士」的「玩家」都可以独立装备「武器<sup>in</sup>」, 最多只能装备一件.
4. 每一件「武器」都有独立的「名字<sup>in</sup>」. 
5. 每一件「武器」都有独立的「攻击力<sup>in</sup>」. 
5. 每一名「职业」为「战士」的「玩家」装备「武器」后, 该「战士」的「攻击力」增加量为「武器」的「攻击力」.
6. 每一名「职业」为「战士」的「玩家」都可以独立装备「防具<sup>in</sup>」, 最多只能装备一件.
7. 每一件「防具」都有独立的「防御力<sup>in</sup>」. 
5. 每一名「职业」为「战士」的「玩家」装备「防具」后, 该「战士」的「防御力」增加量为「防具」的「防御力」.
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
3. 每一个「延时效果」可以直接造成「伤害」, 也可以影响当前「攻击」, 前者的结算时间在「宿主」的攻击「回合」开始时的其他任何结算之前, 后者的结算时间在每一次「攻击」结算之前. (「延时效果」直接造成的「伤害」不是「攻击」, 故不可以被防御.)
3. 每一个「延时效果」都有独立的「持续回合数<sup>in</sup>」(除非有另外的「需求」指定), 「持续回合数」之后该「延时效果」消失.
3. 每一个「特性」具有不同的「类别<sup>in</sup>」, 若触发「延时效果」, 则后者的「类别」与之相同, 「特性」的「类别」为下述选项之一:
    * 「毒性特性」, 产生「延时效果」, 后者具有独立的「伤害值<sup>in</sup>」, 造成「宿主」在其每「回合」中受到「伤害」.
    * 「火焰特性」, 产生「延时效果」, 后者具有独立的「伤害值<sup>in</sup>」, 造成「宿主」在其每「回合」中受到「伤害」.
    * 「冰冻特性」, 产生「延时效果」, 后者造成「宿主」在其每两「回合」(从「延时伤害」出现开始)的前一「回合」不结算「攻击」.
    * 「击晕特性」, 产生「延时效果」, 后者造成「宿主」在其「持续回合数」次「回合」内(从「延时伤害」出现开始)不结算「攻击」, 初始「持续回合数」恒为 2 「回合」.
    * 「全力一击特性」, 不产生「延时效果」, 但「攻击」造成的「伤害」增加至原先的 3 倍.
2. 每一「回合」开始时, 若「攻击者」处于「延时效果」中, 则先结算「延时效果」, 之后如果需要再结算「攻击」.
3. 若「特性」触发并产生「延时效果」时, 「被攻击者」已处于该「类别」的「延时效果」当中, 则「被攻击者」所处于的「延时效果」的剩余「持续回合数」的增加量为新触发的「延时效果」的「持续回合数」, 其他现有状态不变. (如已处于「冰冻延时效果」时又遭受「冰冻延时效果」时, 「持续回合数」延长但间续不变, 如下一「回合」本应为可攻击「回合」则仍为可攻击「回合」.)
2. 对于每一次成功结算的「延时效果」, 需要输出「延时效果日志」.
3. 不同「类别」的「延时效果」具有不同的「延时效果日志」, 如下所述:
    * 「毒性延时效果」的「延时效果日志」具有「宿主」的「名字<sup>out</sup>」, 「延时效果」的「伤害值<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」, 「宿主」结算「延时效果」后的「生命值<sup>out</sup>」.
    * 「火焰延时效果」的「延时效果日志」具有「宿主」的「名字<sup>out</sup>」, 「延时效果」的「伤害值<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」, 「宿主」结算「延时效果」后的「生命值<sup>out</sup>」.
    * 「冰冻延时效果」的「延时效果日志」具有「宿主」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」, 另一「玩家」的「名字<sup>out</sup>」.
    * 「眩晕延时效果」的「延时效果日志」具有「宿主」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」, 「延时效果」的剩余「持续回合数<sup>out</sup>」.
4. 对于每一行「攻击日志」, 当「攻击者」成功触发「特性」时, 「伤害部分」具有「特性子部分」, 如下所述:
    * 「毒性特性」的「特性子部分」位于原有部分之后, 具有「被攻击者」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」.
    * 「火焰特性」的「特性子部分」位于原有部分之后, 具有「被攻击者」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」.
    * 「冰冻特性」的「特性子部分」位于原有部分之后, 具有「被攻击者」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」.
    * 「眩晕特性」的「特性子部分」位于原有部分之后, 具有「被攻击者」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」.
    * 「全力一击特性」的「特性子部分」位于原有部分之前, 具有「攻击者」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」.


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

1. 每一名「玩家」都有「职业<sup>in</sup>」, 为下述选项之一:
    * 「普通人」. 
    * 「刺客」.
    * 「战士」.
    * 「骑士」.
2. 每个「武器」都具有「长度<sup>in</sup>」, 为下述选项之一:
    * 「长」. 
    * 「中」. 
    * 「短」. 
3. 不同「职业」可装备「武器」的范围不同, 当装备不可装备的「武器」时, 会抛出异常:
    * 「刺客」仅可以装备长度为「中」或「短」的武器.
    * 「战士」仅可以装备长度为「中」的武器.
    * 「刺客」仅可以装备长度为「中」或「长」的武器.
4. 不同「职业」可发动「武器」「特性」的范围不同:
    * 「刺客」仅可以发动长度为「短」的武器的「特性」.
    * 「战士」仅可以发动长度为「中」的武器的「特性」.
    * 「骑士」仅可以发动长度为「长」的武器的「特性」.
5. 每一局「游戏」都具有「玩家」间的「距离」, 初始值为 1.
6. 每一名「玩家」都有「攻击范围」, 初始值为 1.
6. 每一件「武器」都有「攻击范围」, 由「武器」的「长度」确定:
    * 长度为「中」或「短」的武器的「攻击范围」为 1.
    * 长度为「长」的武器的「攻击范围」为 2.
7. 每一名「玩家」装备「武器」后, 其「攻击范围」为原有「攻击范围」与「武器」的「攻击范围」中的较大者.
8. 每一件「武器」都具有「属性」, 由「武器」的「长度」确定:
    * 长度为「长」的武器具有「击退属性」, 为「攻击性属性」, 具有独立的「击退距离<sup>in</sup>」(最多为 2), 造成「游戏」的「距离」增加量为该「武器」的「击退距离」.
    * 长度为「短」的武器具有「连击属性」, 为「攻击性属性」, 造成「攻击者」对「被攻击者」发动一次独立的额外攻击(独立触发「特性」), 但只产生一行「共计日志」.
    * 长度为「短」的武器具有「格挡反击属性」, 为「防御性属性」, 具有独立的「防御力<sup>in</sup>」, 造成当次「攻击」的「伤害」减少量为「防御力」, 若「攻击者」在「被攻击者」的「攻击范围」内则发动一次反击性「攻击」.
9. 每一个「属性」的触发概率恒为 1/4.
10. 每一个「属性」的触发与「特性」的触发保持独立, 一次攻击中可以同时触发「属性」和「特性」.
11. 每一个「回合」中最多只触发一个「攻击时属性」. (如「连击属性」增加的「攻击」不可再触发「连击属性」).
11. 每一个「回合」中最多只触发一个「防御时属性」. (如「格挡反击属性」增加的反击性「攻击」不可再触发「格挡反击属性」).
12. 每一个「攻击时属性」的触发与「防御时属性」的触发独立, 一次攻击中可以同时触发「攻击时属性」和「防御时属性」, 但在每一次「攻击」内优先结算「攻击时属性」. (如「击退属性」可触发「格挡反击属性」, 但反击性「攻击」时的距离为击退后的距离; 「连击属性」中的每一次「攻击」都可触发一次「格挡反击属性」)
13. 若「玩家」在自己的攻击「回合」受到「伤害」时, 若遭受「延时效果」且与现有「延时效果」不同, 则新的「延时效果」在该回合立即进入生效条件但不计入回合数. (如因「格挡反击属性」而遭受「眩晕延时效果」, 则该「回合」内视为生效, 但「持续回合数」仍为从下一次自己的攻击「回合」开始的 2「回合」, 他人的攻击「回合」内的生效情况与下一个自己的攻击「回合」相同.)
13. 每一个「属性」中「攻击」的触发条件与正常「攻击」时相同, 如:
    * 「攻击者」处于「冰冻延时效果」或「眩晕延时效果」下并满足生效条件时, 无法触发「连击属性」. 若在「连击属性」的第一次「攻击」后满足上述条件时, 第二次「攻击」将不复存在, 输出与普通「攻击」相同.
    * 「被攻击者」处于「冰冻延时效果」或「眩晕延时效果」下并满足生效条件时, 无法触发「格挡反击属性」中的反击性「攻击」, 但「被攻击者」的「防御力」仍为「格挡反击属性」下的「防御力」.
14. 不同「职业」具有不同的「移动速度」:
    * 「普通人」的「移动速度」为 1
    * 「刺客」的「移动速度」为 1.
    * 「战士」的「移动速度」为 1.
    * 「骑士」的「移动速度」为 2.
15. 每一个「玩家」的攻击「回合」中, 第一次结算「攻击」前, 若「游戏」的「距离」大于「玩家」的「攻击范围」, 则不结算「攻击」(除非有另外的「需求」指定), 「玩家」进行「移动」. (第二次「攻击」不进行「移动」, 如「连击属性」中; 非该「玩家」的攻击「回合」不进行「移动」, 如「格挡反击属性」中.)
16. 每一次「玩家」进行「移动」时, 「游戏」的「距离」减少量为「玩家」的「移动速度」.
17. 每一个「玩家」的攻击「回合」中, 「职业」为「骑士」的「玩家」在进行「移动」后继续进行「攻击」结算.
18. 部分「类别」的「延时效果」具有的「延时效果日志」如下所述:
    * 「冰冻延时效果」的「延时效果日志」具有「宿主」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」, 原有「行为」相关「标识<sup>out</sup>」, 若为「攻击」则包含另一「玩家」的「名字<sup>out</sup>」.
    * 「眩晕延时效果」的「延时效果日志」具有「宿主」的「名字<sup>out</sup>」, 「类别」相关「标识<sup>out</sup>」, 原有「行为」相关「标识<sup>out</sup>」, 「延时效果」的剩余「持续回合数<sup>out</sup>」.


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
骑士张三用长枪攻击了刺客李四,李四受到了8点伤害,李四被击退了, 李四晕倒了, 李四剩余生命: 12
李四靠近了张三
//张三进攻
//李四进攻
```

输出4: 

```
骑士张三用长枪攻击了刺客李四,李四受到了8点伤害, 李四剩余生命: 12
李四晕倒了, 无法攻击, 眩晕还剩: 0轮
//张三进攻
//李四进攻
骑士张三用长枪攻击了刺客李四,李四受到了8点伤害, 李四被击退了, 李四晕倒了, 李四剩余生命: 12
李四晕倒了, 无法移动, 眩晕还剩: 1轮
//张三进攻
```

输出5: 

```
骑士张三用长枪攻击了刺客李四,李四受到了8点伤害, 李四剩余生命: 12
李四冻得直哆嗦, 没有击中张三
//张三进攻
//李四进攻
骑士张三用长枪攻击了刺客李四,李四受到了8点伤害, 李四被击退了, 李四剩余生命: 12
李四冻得直哆嗦, 没有移动
//张三进攻
```

输出7: 

```
骑士张三用长枪攻击了战士李四,李四受到了6点伤害,李四发动了隔挡反击,张三受到了9点伤害.李四剩余生命:14,张三剩余生命:11
//李四攻击

骑士张三用长枪攻击了战士李四,李四受到了6点伤害,李四发动了隔挡反击,张三不在攻击范围内.李四剩余生命:14,张三剩余生命:11
//李四攻击

骑士张三用长枪攻击了战士李四,李四受到了6点伤害,李四发动了隔挡反击,李四因冻得直哆嗦而无法攻击.李四剩余生命:14,张三剩余生命:11
//李四攻击
```

输出8: 

```
刺客张三用峨眉刺攻击了战士李四, 李四受到了8点伤害, 李四中毒了, 李四发动了隔挡反击, 张三受到了9点伤害, 张三着火了, 张三发动了连击, 张三发动了全力一击, 李四受到了24点伤害, 李四发动了隔挡反击, 张三受到了9点伤害, 张三晕倒了, 李四剩余生命: 3, 张三剩余生命: 11
```

### 第六问（选作）

* 角斗场所
* 场所会对各个属性都有影响
* 屋顶和弄堂对刺客有加成
* 战场和关隘对骑士有加成
* 庭院和都市大道对战士有加成
* 加成的方式是当触发技能效果的时候，各自收到更多的影响

* 屋顶和弄堂里,刺客会因为敏捷的动作而获得额外的一轮进攻
* 战场和关隘,骑士击退距离为武器标注的两倍
* 庭院和都市大道,战士可以隔挡后,连续进攻两次，如果对方不在攻击范围内，则会移动
