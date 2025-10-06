import kotlin.math.atan
import kotlin.math.min
import kotlin.random.Random

open class Character(val name: String, var health: Int, val attack: Int){
    val isAlive: Boolean get() = health > 0

    open fun takeDamage(damage: Int){
        health -= damage
        println("$name получает $damage")
        if (health <= 0) println("$name пал в бою!")
    }

    fun attack(target: Character){
        if (!isAlive || !target.isAlive) return
        val damage = Random.nextInt(attack - 3, attack + 4)
        println("$name атакует ${target.name}!")
        target.takeDamage(damage)
    }
}

class Player(name: String, health: Int, attack: Int): Character(name, health, attack){
    var potions = 3 //кол-во зелий здоровья
    var shield = false

    fun usePotions(){
        if (potions > 0){
            val healAmount = 30
            health += healAmount
            potions --
            println("$name использует зелье, +${healAmount} теперь у него $health Hp")
        }else{
            println("У $name нет зелий, беги...")
        }
    }

    fun useShield(){
        shield = true
        println("$name поднимает щит")
    }

    override fun takeDamage(damage: Int) {
        if (shield == true){
            val reducedDamage = (damage / 2).coerceAtLeast(0)
            super.takeDamage(reducedDamage)
        }

    }
    fun printStatus(){
        println("=== $name ===")
        println("Hp: $health")
        println("Atk: $attack")
        println("Зелья: $potions")
        println("===========================")
    }
}
class GameInput(){
    // Функция получения числа от пользователя
    fun getNumberInput(prompt: String, min: Int = 1, max: Int = 10): Int{
        while (true){
            println(prompt)

            try {
                //readln() читает ввод пользователя и ждем enter
                val input = readln()
                // .toInt() - пытается перевести ввод в число
                val number = input.toInt()

                if (number in min..max){
                    return number //возвращает коректное число
                }else{
                    println("числа тока от $min до $max")
                }
            }catch (e: NumberFormatException){
                println("Варнинг! пошел нахуй окурок (без негатива)")
            }
        }
    }

    fun getYesNoInput(prompt: String): Boolean{
        while (true){
            println("$prompt (д/н): ")
            val input = readln().lowercase()

            when(input){
                "д","да","y","yes" -> return true
                "н","нет","n","no" -> return false
                else -> print("Плиз введи да или нет")
            }
        }
    }
}

fun main(){
    println("=== СИСТЕМА ВВОДА ДАННЫХ ===")

    val gameInput = GameInput()
    val UwU42 = Player("UwU42", 100, 15)

    println("Создайте вашего персонажа: ")
    println("Введите имя игрока: ")
    val playerName = readln()
    val customPlayer = Player(if (playerName.isBlank()) "Безымянный" else playerName, 100, 15)
    val playerLevel = gameInput.getNumberInput("Введите уровень сложности (1-изи казуал, 2-крутой хардкор): ", 1,5)
    println("Выбран уровень сложности: $playerLevel, нет пути назад!")

    var gameRunning = true

    while (gameRunning){
        println("=== ГЛАВНОЕ МЕНЮ ===")
        println("1.Статус")
        println("2.Использовать зелье")
        println("3.Использовать щит")
        println("4.Выйти из игры")

        val choice = gameInput.getNumberInput("Введите действие 1,2,3 или 4")
        when(choice){
            1 -> customPlayer.printStatus()
            2 -> customPlayer.usePotions()
            3 -> customPlayer.useShield()
            4 -> {
                gameRunning = false
                println("Выход из игры...")
            }
        }
    }

    println("Сасиюо за игру")
}
