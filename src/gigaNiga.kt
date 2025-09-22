import javax.naming.Name
import kotlin.random.Random
import kotlin.random.nextInt

fun calculateDamage(baseAttack: Int): Int{
    val variation = Random.nextInt(90, 131)
    return (baseAttack * variation) / 100
}

fun perfotmAttack(attackerName: String, attackerAttack: Int, defenderName: String, defenderHealth: Int): Int{
    println("Ход существа $attackerName: ")
    val damage = calculateDamage(attackerAttack)
    var newHealth = defenderHealth - damage

    println("$attackerName наносит $defenderName $damage урона!")
    println("У $defenderName осталось $newHealth Hp")

    return newHealth
}

fun startBattle(){
    val playerName = "Дристерио"
    var playerHealth = 100
    val playerAttack = 10

    val monsterName = "Eminet"
    var monsterHealth = 60
    val monsterAttack = 12

    var round = 1

    println("===== Бой между $playerName и $monsterName начинается =====")

    while (playerHealth > 0 && monsterHealth > 0){
        println(" - Раунд $round! - ")
        monsterHealth = perfotmAttack(playerName, playerAttack, monsterName, monsterHealth)

        if (monsterHealth <= 0) break

        playerHealth = perfotmAttack(monsterName, monsterAttack, playerName, playerHealth)
        round++
    }

    if (playerHealth > 0){
        println("\n Победа! $playerName одолел $monsterName!")
    } else{
        println("\n Победа! $monsterName одолел $playerName!")
    }
}

fun main(){
    println("===== Запуск движка игры =====")
    startBattle()
    println("===== Игра завершилась =====")
}