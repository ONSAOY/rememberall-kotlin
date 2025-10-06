//import jdk.jfr.DataAmount
//
//class Player{
//    var name: String = "980"
//    var health: Int = 100
//    var damage: Int = 15
//    var isAlive: Boolean = true
//
//    fun takeDamage(damage: Int){
//        health -= damage
//        println("$name получает $damage урона! Осталось: $health")
//
//        if (this.health <= 0){
//            isAlive = false
//            println("$name позорно умер ударив в грязь лицом")
//        }
//    }
//
//    fun heal(amount: Int){
//        if (isAlive){
//            health += amount
//            println("$name восстанавливает $amount здоровья. Теперь оно $health")
//        } else{
//            println("$name умэр")
//        }
//    }
//}
//
//fun main(){
//    val warrior = Player()
//
//    warrior.name = "Sosek"
//    println("Игрок: ${warrior.name} появился в мире, его здоровье: ${warrior.health}")
//
//    warrior.takeDamage(30)
//    warrior.heal(10)
//}