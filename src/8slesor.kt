import kotlin.random.Random

class Item(val name: String, val description: String, val useEffect: (Player) -> Unit){
    fun use(player: Player){
        println("Используется: $name")
        useEffect(player)
    }
}

class Location(val name: String,val description: String){
    val enemies = mutableListOf<Character>()
    val items = mutableListOf<Item>()

    fun addEnemy(enemy: Character){
        enemies.add(enemy)
    }

    fun addItem(item: Item){
        items.add(item)
    }

    fun describe(){
        println("=== $name ===")
        println(description)

        if (enemies.isNotEmpty()){
            println("Враги в локации: ")
            //forEachIndexed - перебирает список предоставляя индексы и элементы
            enemies.forEachIndexed { index, enemy ->
                println("${index + 1}. ${enemy.name} (Hp: ${enemy.health})")
            }
        }

        if (items.isNotEmpty()){
            println("Предметы в локации: ")
            //forEachIndexed - перебирает список предоставляя индексы и элементы
            items.forEachIndexed { index, item ->
                println("${index + 1}. ${item.name} - ${item.description}")
            }
        }
    }
}

class  GameWorld{
    private val locations = mutableListOf<Location>()
    private var currentLocationIndex = 0
    val gameInput = GameInput()

    //вычисляемое свойство для текущей локации
    val currentLocation: Location
        get() = locations[currentLocationIndex]

    fun createWorld(){
        val forest = Location("Темный лес", "Из леса точится сташная аура; Вы чуствуете решимость")
        val cave = Location("Пещера", "Рядом с ней вы чуствуете трупный запах; Вы чуствуете страх")
        val village = Location("Деревня", "От деревни несет запахом свежего хлеба; Вы чуствуете спокойствие")

        val mrBeast = Character("Мистер зверь", 40, 8)
        val bear = Character("Мэдведь", 25, 10)
        val toad = Character("Зубастый жаб", 200, 15)

        val healPotion = Item("Зелье здоровья", "Востанавливает от -1 до 1 Hp", { player ->
            println("${player.name} Востанавливает ... Hp")
        })
        val attackPotion = Item("Зелье силы", "Увеличивает атаку на 5", { player ->
            println("${player.name} Усиливает себя на 5")
        })

        forest.addEnemy(bear)
        forest.addEnemy(mrBeast)
        forest.addItem(healPotion)

        cave.addEnemy(toad)
        cave.addEnemy(mrBeast)
        cave.addItem(attackPotion)

        village.addItem(healPotion)

        locations.add(forest)
        locations.add(cave)
        locations.add(village)
    }


    fun startGame(player: Player){
        println("Бобро в игру, бобро пожаловать типо ${player.name}")

        var gameRunning = true

        while (gameRunning && player.isAlive){
            currentLocation.describe()

            println("\n === Доступные действия ===")
            println("1.Осмотрется")
            println("2.Атаковать")
            println("3.Взять предмет")
            println("4.Перейти на след. локу")
            println("5.Использовать зелье")
            println("6.Выйти из игры")
            println("7.Открыть инвентарь")

            val choice = gameInput.getNumberInput("Выберите действие: ", 1,6)

            when(choice){
                1 -> currentLocation.describe()
                2 -> combatMenu(player)
                3 -> takeItemMenu(player)
                4 -> moveToNextLocation()
                5 -> player.usePotions()
                6 -> gameRunning = false
                7 -> player.useInventory()

            }

            if (!player.isAlive){
                println("Игра окончена ${player.name} пал упал")
            }
        }
    }

    private fun combatMenu(player: Player){
        if (currentLocation.enemies.isEmpty()){
            println("У тебя нет врагов")
            return
        }
        println("\n Выберете цель для атаки: ")
        currentLocation.enemies.forEachIndexed { index, enemy ->
            println("${index + 1}. ${enemy.name} (Hp: ${enemy.health})")
        }
        println("${currentLocation.enemies.size + 1}. Отмена")

        val choice = gameInput.getNumberInput("Ваш выбор: ", 1, currentLocation.enemies.size + 1)

        if (choice <= currentLocation.enemies.size){
            val target = currentLocation.enemies[choice - 1]
            player.attack(target)

            if (!target.isAlive){
                currentLocation.enemies.remove(target)
                println("${target.name} повержен и удален из локации!")
            }

            currentLocation.enemies.forEach { enemy ->
                if (enemy.isAlive){
                    enemy.attack(player)
                }
            }
        }
    }

    val currentItem = mutableListOf<Item>()

    private fun useInventory(player: Player){
        if (currentItem.isEmpty()){
            println("нету лута :(")
            return
        }
    }
    private fun takeItemMenu(player: Player){
        if (currentLocation.items.isEmpty()){
            println("нету лута :(")
            return
        }

        println("\n выберите прдемет для лута: ")

        currentLocation.items.forEachIndexed { index, item ->
            println("${index + 1}. ${item.name}")
        }

        val choice = gameInput.getNumberInput("Ваш выюор: ", 1, currentLocation.items.size)
        val item = currentLocation.items[choice - 1]

        println("Вы взяли: ${item.name}")

        item.use(player)
        currentLocation.items.remove(item)
    }

    private fun moveToNextLocation(){
        if (currentLocationIndex < locations.size - 1){
            currentLocationIndex++
            println("Вы переместились в: ${currentLocation.name}")

            if (currentLocation.enemies.isNotEmpty()){
                println(" ... ")
            }
        }else{
            println("Это последняя локация")
        }
    }

}

fun main(){
    println("=== СОЗДАНИЯ ИГРОВОГО МИРА ===")

    val gameWorld = GameWorld()
    gameWorld.createWorld()

    val player = Player("42enjoier",100,15)
    gameWorld.startGame(player)
}


















