//import kotlin.math.cos
//import kotlin.random.Random
//
//// Базовый класс его важно сделать open чтобы его наследовать
//open class Character(
//    val name: String,
//    maxHealth: Int,
//    baseAttack: Int,
//    baseLevel: Int
//){
//    // protected - доступен для использования в классе и в наследниках этого класса
//    protected var _health = maxHealth.coerceAtLeast(1)
//    protected val _maxHealth = maxHealth.coerceAtLeast(1)
//    protected val _attack = baseAttack.coerceAtLeast(1)
//    protected var _level = baseLevel.coerceAtLeast(1)
//
//    // open - разрешаем переопредиления метода в наследниках класса
//    open val health: Int
//        get() = _health
//
//    open val maxHealth: Int
//        get() = _maxHealth
//
//    open val attack: Int
//        get() = _attack
//
//    open val isAlive: Boolean
//        get() = _health > 0
//
//    open val lelUp: Int
//        get() = _level
//
//    init {
//        println(" Создан персонаж: $name")
//    }
//
//    open fun levelUp(level: Int){
//        if (!isAlive) return
//
//
//        val damage =  calculateDamage(_attack + 3)
//        val heal = (_health + 3)
//        val level = level + 1
//    }
//
//    open fun takeDamage(damage: Int){
//        if (!isAlive) return
//
//        val actualDamage = damage.coerceAtLeast(0)
//        _health -= actualDamage
//        println("$name получает $actualDamage урона! Осталось: ${_health}")
//
//        if (_health <= 0){
//            println("$name умэр")
//        }
//    }
//
//    open fun heal(amount: Int){
//        if (!isAlive) return
//
//        val healAmount = amount.coerceAtLeast(0)
//        _health = (_health + healAmount).coerceAtLeast(_maxHealth)
//        println("$name похилился на $healAmount")
//    }
//
//    open fun attack(target: Character){
//        if (!isAlive || !target.isAlive) return
//
//        val damage = calculateDamage(_attack)
//        println("$name атакует ${target.name} !")
//        target.takeDamage(damage)
//    }
//
//    open fun printStatus(){
//       val status = if (isAlive) "Жив" else "dead"
//       println("$name: $_health/$_maxHealth Hp, ATK: $_attack ($status)")
//    }
//}
//
//// WARRIOR - класс наследник
//class Warrior(name: String, maxHealth: Int, baseAttack: Int) : Character(name, maxHealth, baseAttack){
//    var armor: Int = 5
//
//    //переопределение метода получения урона с учетом брони
//    override fun takeDamage(damage: Int) {
//        if (!isAlive) return
//
//        val reducedDamage = (damage - armor).coerceAtLeast(0)
//        println("Броня $name поглащает $armor урона")
//        // super - вызов метода родительского класса
//        super.takeDamage(reducedDamage)
//    }
//
//    fun powerfullStrike(target: Character){
//        if (!isAlive) return
//
//        val cost = 10 //Стоимость способности в очках
//        if (_health > cost){
//            _health -= cost // Тратим здоровье для усиленной атаки
//            val damage = calculateDamage(_attack * 2)
//            println("$name жертвует HP и использует [Решимость] атака увеличена в 2 раза! ")
//            target.takeDamage(damage)
//        }else{
//            println(" у $name не хватает HP для мощьной атаки")
//            attack(target)
//        }
//    }
//}
//
//class Mage(name: String, maxHealth: Int, baseAttack: Int) : Character(name, maxHealth, baseAttack){
//
//    var mana: Int = 100
//    val maxMana: Int = 100
//
//    override fun attack(target: Character) {
//        if (!isAlive || !target.isAlive) return
//
//        if (mana >= 10){
//            mana -= 10
//            val damage = calculateDamage(_attack + 5)
//            println("$name атакует магическим посохом и тратит  10 маны")
//            target.takeDamage(damage)
//            println(" Осталось маны: $mana/$maxMana")
//        }else{
//            // Обычная атака если маны нет
//            println(" У $name недостаточно маны")
//            super.attack(target) // Вызов базовой реализации атаки
//        }
//    }
//
//    fun castFireball(target: Character){
//        if (!isAlive) return
//
//        val cost = 30
//
//        if (mana >= 30){
//            mana -= cost
//            val damage = calculateDamage(_attack * 3)
//            println("$name кастует fireball и тратит $cost маны")
//            target.takeDamage(damage)
//        }else{
//            attack(target)
//        }
//    }
//
//    override fun printStatus(){
//        val status = if (isAlive) "жив" else "умэр"
//        println("$name: $_health/$_maxHealth HP, MANA: $mana/$maxMana, ATK: $_attack ($status)")
//    }
//}
//class Archer(name: String, maxHealth: Int, baseAttack: Int) : Character(name, maxHealth, baseAttack){
//    val agility = Random.nextInt(0,100)
//
//    //переопределение метода получения урона с учетом брони
//    override fun attack(target: Character) {
//        if (!isAlive || !target.isAlive) return
//        if (agility <= 30){
//            val damage = calculateDamage(_attack * 2)
//            println("30% выпало")
//            target.takeDamage(damage)
//        }else{
//            println("30% не выпало")
//        }
//    }
//}
//
//fun main(){
//    println(" === Бой 42 === ")
//
//    val warrior = Warrior("mr.Full", 120,16)
//    val mage = Mage("Toldik", 80, 10)
//    val enemy = Warrior("BigNiga", 100, 14)
//    val archer = Archer("лучок", 80, 6)
//
//    println(" === НАЧАЛО БОЯ ===")
//    warrior.powerfullStrike(enemy)
//    mage.castFireball(enemy)
//    enemy.attack(warrior)
//    archer.attack(enemy)
//
//    println(" === Cтатусы персонажей ===")
//    warrior.printStatus()
//    mage.printStatus()
//    enemy.printStatus()
//    archer.printStatus()
//
//    warrior.takeDamage(20)
//    mage.attack(enemy)
//
//    warrior.levelUp()
//    mage.levelUp()
//    archer.levelUp()
//
//}