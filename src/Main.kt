import kotlin.random.Random

fun main(){
    val playerAttack = 15
    val monsterHealth = 50

    println("На вас напал монстр")


    //Копирование переменной со здоровьем монстра для изменения
    var currentMonsterHealth = monsterHealth

    while (currentMonsterHealth > 0){//Random.nextInt() функция возвращающая случайное инт число
        val damageVariation = Random.nextInt(80,121)
        val actualDamage = (playerAttack * damageVariation) / 100 //Вычисляем фактический урон

        currentMonsterHealth -= actualDamage

        println("Вы наносите монстру $actualDamage урона!")

        if (currentMonsterHealth <= 0){
            println("Вы победили Бреинротопия не останется в долгу")
        } else {
            println("Монстр still standing с $currentMonsterHealth здоровья.")
        }
    }
}