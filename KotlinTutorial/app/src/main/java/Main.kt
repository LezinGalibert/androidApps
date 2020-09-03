import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
fun main(args: Array<String>) {
//    println("Hello World")
//    println("My first Kotlin program")
//
//    //Kotlin is CASE SENSITIVE
//
//    //Declare variable before assigning value
//    // var is mutable
//    // var for variable
//    var lezinou = "Lezin Galibert"
//    println(lezinou)
//
//    var lezin: String
//    lezin = "Lezin Galibert"
//    println(lezin)
//
//    //REFACTOR to change variable name WHEREVER it appears
//    println()
//    //val is immutable
//    //val for value
//    val apples: Int = 6
//    val oranges: Int = 5
//    var fruits: Int = apples + oranges
//    println(fruits)
//    println(apples / 4)
//    println()
//
//    val weeks: Int = 130
//    //Use at least one DOUBLE type to convert the whole thing to double
//    val years: Double = weeks / 52.0
//
//    //String INTERPOLATION (Kotlin only, not Java)
//    println("$weeks weeks is $years years")
//
//    println("My name is $lezinou")
//
//    //////////////////////////////
//    //Conditions
//
//    val lives = 3
//    val isGameOver = lives < 1
//    println(isGameOver)
//
//    if (isGameOver) {
//        println("Game over!")
//    } else {
//        println("You are still alive!")
//    }
//
//    println("How old are you: ")
//    val age = readLine()!!.toInt()
//    println("age is $age")
//
//    //WHEN instead of IF
//    var message: String
//    message = if (age < 18) {
//        "You're too young to vote."
//    } else if (age == 100) {
//        "Congratulations!"
//    } else {
//        "You can vote."
//    }
//    println(message)
//
//    message = when {
//        age < 18 -> {
//            "You're too young to vote."
//        }
//        age == 100 -> {
//            "Congratulations!"
//        }
//        else -> {
//            "You can vote."
//        }
//    }
//    println(message)

//    //Regular FOR loop
//    for (i in 0..10) { //goes from 0 to 10 included
//        println("$i squared is ${i * i}")
//    }
//
//    println("*********************")
//
//    for (i in 0 until 10) {//goes from 0 to 10 excluded
//        println("$i squared is ${i * i}")
//    }
//
//    println("*********************")
//
//    for (i in 10 downTo 0 step 2) {
//        println("$i squared is ${i * i}")
//    }

    ///////////////////////////////////////
    //CLASSES

//    val player = Player("Lezin")
//    println(player.name)
//    println(player.lives)
//    println(player.level)
//    println(player.score)
//
//    player.show()
//
//    val louise = Player("Louise", 5)
//    louise.show()
//
    val peter = Player("Peter", 10, 4, 17)
    peter.show()

    println(peter.weapon.name.toUpperCase())
    println(peter.weapon.damageInflicted)

    val axe = Weapon("Axe", 12)
    peter.weapon = axe
    axe.damageInflicted = 24
    println(peter.weapon.name.toUpperCase())
    println(peter.weapon.damageInflicted)

    peter.weapon = Weapon("Sword", 17)
    println(peter.weapon.name)

    val redPotion = Loot("Red Potion", LootType.POTION, 7.50)
    peter.getLoot(redPotion)
    val chestArmor = Loot("+3 Chest Armor", LootType.ARMOR, 80.0)
    peter.getLoot(chestArmor)

    peter.showInventory()

    peter.getLoot(Loot("Ring of Protection +2", LootType.RING, 35.0))
    peter.getLoot(Loot("Invisibility Potion", LootType.POTION, 20.0))

    peter.showInventory()

    if (peter.dropLoot(redPotion)) {
        peter.showInventory()
    } else {
        println("Peter doesn't have a ${redPotion.name}")
    }

    peter.dropLoot("Invisibility Potion")
    peter.showInventory()

//    var evilRabbit = Enemy("Evil Rabbit", hitPoints = 20, lives = 3)
//    println(evilRabbit)
//
//    evilRabbit.takeDamage(4)
//    println(evilRabbit)
//
//    evilRabbit.takeDamage(25)
//    println(evilRabbit)
//
//    val uglyTroll = Troll("Ugly troll")
//    println(uglyTroll)
//    uglyTroll.takeDamage(30)
//    println(uglyTroll)
//
//    val vlad = Vampire("Vlad")
//    println(vlad)
//    vlad.takeDamage(8)
//    println(vlad)

//    val dracula = VampireKing("Dracula")
//    println(dracula)
//
//    do {
//        if (dracula.runAway()){
//            println("Dracula ran away")
//            break
//        } else {
//            if (!dracula.dodges()) {
//                dracula.takeDamage(80)
//            }
//        }
//
//        println("-------------------------------")
//    } while (dracula.lives > 0)
//    println(dracula)

    val conan = Player("Conan")
    conan.getLoot(Loot("Invisibility", LootType.ARMOR, 4.0))
    conan.getLoot(Loot("Mithril", LootType.ARMOR, 8.0))
    conan.getLoot(Loot("Ring of Speed", LootType.RING, 1.0))
    conan.getLoot(Loot("Red Potion", LootType.POTION, 10.0))
    conan.getLoot(Loot("Silver Ring", LootType.RING, 18.0))
    conan.getLoot(Loot("Silver Ring", LootType.RING, 18.0))
    conan.getLoot(Loot("Silver Ring", LootType.RING, 18.0))
    conan.getLoot(Loot("Silver Ring", LootType.RING, 18.0))
    conan.showInventory()

    conan.dropLoot("Silver Ring")
    conan.showInventory()




}

