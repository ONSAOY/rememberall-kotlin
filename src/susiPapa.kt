//import jdk.jfr.DataAmount
//import kotlin.random.Random
//
//class Character1{
//    //свойства класса задаются либо в () либо внутри через переменные
//
//    var name: String = "Безымянный"
//    var health: Int = 100
//    var maxHealth: Int = 100
//    var attack: Int = 15
//    var isAlive: Boolean = true
//
//    // Методы класса - описывают поведение обьекта класса
//    fun takeDamage(damage: Int){
//        health -= damage
//        println(" $name получает $damage урона!")
//
//        //ссылка на текущий обьект
//        if (this.health <= 0){
//            isAlive = false
//            println("$name умэр...")
//        }
//    }
//
//    fun heal(amount: Int){
//        if (isAlive){
//            health = minOf(health + amount, maxHealth)
//            println("$name востанавливает $amount здоровья. Теперь здоровья: $health")
//        } else {
//            println("$name Он умэер, невозможно похилить")
//        }
//    }
//
//    // Метод атаки другого персонажа
//    fun attack(target: Character){
//        if (!isAlive){
//            println("$name не могет атаковать , он умэр")
//            return //немедленый выход из метода
//        }
//
//        val damage = calculateDamage(attack)
//        println(" $name атакует ${target.name}")
//        target.takeDamage(damage)
//    }
//}
//
//fun main(){
//    println(" ===== Создание пресонажа через классы ===== ")
//
//    val player = Character1()
//    val monster = Character1()
//
//    player.name = "980"
//    player.health = 100
//    player.attack = 18
//
//    monster.name = "SchoolShoter"
//    monster.health = 60
//    monster.attack = 22
//
//    println(" =====  Начало боя ===== ")
//    player.attack(monster)
//    monster.attack(player)
//
//    println(" ===== Состояние после 1 раунда =====")
//    println("Игрок: ${player.name} имеет ${player.health} Hp, жиы? ${player.isAlive}")
//    println("Игрок: ${monster.name} имеет ${monster.health} Hp, жиы? ${monster.isAlive}")
//
//    println("===== Игрок находит зелье =====")
//    player.heal(25)
//}
