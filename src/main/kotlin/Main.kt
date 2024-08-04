fun main() {
    // Создаем объект "Королевство"
    val kingdom = Kingdom()

    // Выводим информацию о правителе королевства
    println("name: ${kingdom.ruler.name} intellect: ${kingdom.ruler.intellect} power: ${kingdom.ruler.power}")

    // Выводим информацию о наследниках королевства
    kingdom.heirs.forEach {
        println("name: ${it.name} intellect: ${it.intellect} power: ${it.power}")
    }

    // Выводим информацию о лучниках королевства
    println(kingdom.archers)
    // Выводим информацию о воинах королевства
    println(kingdom.warriors)
}

// Класс "Королевство"
class Kingdom {
    // Создаем объект "Правитель" (по умолчанию - "Артур")
    val ruler = Ruler("Arthur")
    // Создаем список наследников
    val heirs = mutableListOf<Heir>()

    // Создаем списки лучников и воинов
    val archers = mutableListOf<Archer>()
    val warriors = mutableListOf<Warrior>()

    // Создаем объект "Колесо фортуны"
    private val wheelOfFortune = WheelOfFortune()

    // Блок инициализации объекта "Королевство"
    init {
        // Добавляем 4 наследника в список наследников, генерируя их характеристики с помощью "Колеса фортуны"
        for (i in 1..4) {
            heirs.add(Heir("Heir $i", wheelOfFortune))
        }

        // Создаем 20 лучников, чередуя "Dagger" и "None"
        for (i in 0..19) {
            if (i % 2 == 0) {
                archers.add(Archer("Dagger"))
            } else {
                archers.add(Archer("None"))
            }
        }

        // Создаем 30 воинов, чередуя "Sword" и "Axe"
        for (i in 0..29) {
            if (i % 2 == 0) {
                warriors.add(Warrior("Sword"))
            } else {
                warriors.add(Warrior("Axe"))
            }
        }
    }
}

// Класс "Правитель"
open class Ruler(val name: String) {
    // Устанавливаем начальную силу и интеллект правителя
    var power = 10f
    var intellect = 10f
}

// Класс "Наследник", наследующий от "Правителя"
class Heir(name: String, wheelOfFortune: WheelOfFortune) : Ruler(name) {
    // В блоке инициализации наследника умножаем его силу и интеллект на случайный коэффициент, полученный из "Колеса фортуны"
    init {
        power *= wheelOfFortune.coefficient()
        intellect *= wheelOfFortune.coefficient()
    }
}

// Класс "Лучник"
data class Archer(var bow: String = "Longbow", val Dagger: String) {
    // Вторичный конструктор для создания лучника с заданным видом кинжала (Dagger)
    constructor(dagger: String) : this("Longbow", dagger)
}

// Класс "Воин"
data class Warrior(val weapon: String)

// Класс "Колесо фортуны"
class WheelOfFortune {
    // Генерируем случайный коэффициент от 0 до 2
    fun coefficient(): Float = (0..200).random() / 100f
}