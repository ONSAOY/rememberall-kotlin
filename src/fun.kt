fun main(){
    val playerHealth = 100
    val playerName = "GigaNiga"

    val updateHealth = attackMonster(playerHealth)
    println("После боя ваше здоровье: $updateHealth")

    println("$playerName начинает путешествие по брейротопии")
    println("Здоровье: $playerHealth")

    val healingPosition = drinkHealingPivo(updateHealth)
    println("Здоровье восстановлено до: $healingPosition")
}

fun attackMonster(health: Int): Int{
    println(" ==== Начинается битва! ====")

    val damage = 25
    val newHealth = health - damage
    println("Получено, $damage урона")

    return  newHealth;
}

fun drinkHealingPivo(health: Int): Int{
    println(" ==== Вы выпили пиво! ====")
    val healing = 30
    val newHealth = health + healing
    println("Здоровье аосстановленно: $healing")
    return newHealth
}