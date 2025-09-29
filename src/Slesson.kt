//import jdk.jfr.DataAmount
//import kotlin.math.max
//import kotlin.random.Random
//
//// улучшенный класс Character
//
//class Character(
//    val name: String,
//    //без вал это просто параметр конструктора это не свойство класса
//    maxHealth: Int,
//    baseAttack: Int,
//){
//    //Инапсуляция - делает внутрение свойства приватными(private)
//    //private - модификатор доступа. Означает что свойство доступно для использования только внутри класса
//    private  var _health = maxHealth.coerceAtLeast(1) //coerceAtLeast(1) - гарантирует минимальное здоровье = 1
//
//    //Публичное свойство health только для чтения кем либо (val)
//    // Другие классы могут узнать кол-во здоровья, но не могут изменить его
//    val health: Int
//        get() = _health //get() - возвращает только значение приватного health
//
//    private val _maxHealth = maxHealth.coerceAtLeast(1)
//    val maxHealth: Int
//        get() = _maxHealth
//
//
//    private val _attack = baseAttack.coerceAtLeast(1)
//    val attack: Int
//        get() = _attack
//
//    //вычисляемое свойство - свойство которое не хранится,а вычисляется при каждом оброщении
//    val isAlive: Boolean
//        get() = _health > 0
//
//    //Блок init выполняется при создании обьекта
//    init {
//        println("Создан новый персонаж: $name (HP: $_health/$_maxHealth, ATK: $_attack)")
//    }
//
//    //Методы с проверками
//    fun takeDamage(damage: Int){
//        if (!isAlive){
//            println("$name уже мертв и не может получить damage")
//            return
//        }
//
//        val actualDamage = damage.coerceAtLeast(0)
//        _health -= actualDamage
//        println("$name получает $actualDamage урона! Осталось здоровья: $_health")
//
//        if (_health <= 0){
//            println("$name пал упал")
//        }
//    }
//
//    fun heal(amount: Int){
//        if (!isAlive){
//            println("$name уже dead и не может похилится!")
//            return
//        }
//
//        val healAmount = amount.coerceAtLeast(0)
//
//        _health = (_health + healAmount).coerceAtLeast(_maxHealth)
//        println("$name востановил $healAmount здоровья , теперь у него: $_health/$_maxHealth HP")
//    }
//
//    fun attack(target: Character){
//        if (!isAlive){
//            println("$name уже dead и не может похилится!")
//            return
//        }
//
//        if (!target.isAlive){
//            println("$target уже dead не издевайся над трупом!")
//            return
//        }
//
//        val damage = calculateDamage(_attack)
//        println("$name атакует ${target.name}")
//        target.takeDamage(damage)
//    }
//
//    fun printStatus(){
//        val status = if (isAlive) "Жив" else "dead"
//        println("$name: $_health/$_maxHealth Hp, ATK: $_attack ($status)")
//    }
//}
//
//
//
//fun main(){
//    println("===== Cоздания персонажей 2.0")
//    val player = Character("GigaNiga", 80,5)
//    val monster = Character("jopa", 120,10)
//
//
//    player.attack(monster)
//    monster.attack(player)
//    println("После боя")
//    player.printStatus()
//    monster.printStatus()
//
//    val healer = Character("42",60,3)
//    val warrior = Character("barakObama",95,8)
//
//    warrior.attack(monster)
//    healer.heal(warrior.health)
//}
