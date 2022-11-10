package com.example.myapplicationkotlin

val toDolist = mutableMapOf<String, String>().toSortedMap(compareBy<String> { it.length }.thenBy { it })
// mutableMap에 toStoredMap을 사용하여 키값(기한)을 기준으로 정렬한 입력받은 해야 할 일 목록
val finList = mutableMapOf<String, String>().toSortedMap(compareBy<String> { it.length }.thenBy { it })
// mutableMap에 toStoredMap을 사용하여 키값(기한)을 기준으로 정렬한 입력받은 완료한 일 목록

class inputMode {
    fun inputToDoList() {

        println("\n--해야 할 일 입력모드--")
        println("해야 할 일 입력을 시작 하시겠습니까?")
        println("Any Key / N (N 입력 시 메인으로 이동합니다)")
        val startInputChoice = readLine().toString() //

        // 초기화면에서 메소드를 잘못 입력을 감지하기 위하여
        if(startInputChoice == "N" || startInputChoice == "n") {
            main()
        }
        // 문자 N 입력 시 초기화면으로 이동하며 이외는 정상적으로 메소드를 실행한다

        println("\n입력을 시작 하겠습니다")
        println("메인으로 돌아가려면 기한에 0을 입력해주세요")
        while(true){
            // 기한과 해야 할 일을 따로 입력받아서
            println("\n기한을 입력해주세요")
            val inputDate = readLine().toString() // 기한 입력
            if(inputDate == "0") {
                return main()
            }
            // 만약 기한에 0을 입력하게되면 모든 입력을 마친 상태이기 때문에 초기화면으로 이동한다
            // 0을 입력하기 전까지 무한히 반복한다

            println("해야 할 일을 입력해주세요")
            val inputList = readLine().toString()

            toDolist.put("$inputDate", "$inputList") //기한은 key값으로, 해야 할 일은 value 값으로 toDolist인 mutableMap에 선언한다
        }
    }

    fun inputFinList() {
        println("\n--끝마친 일 입력모드--")
        println("끝마친 일 입력을 시작 하시겠습니까?")
        println("Any Key / N (N 입력 시 메인으로 이동합니다)")
        val startInputChoice = readLine().toString() //

        // 초기화면에서 메소드를 잘못 입력을 감지하기 위하여
        if(startInputChoice == "N" || startInputChoice == "n") {
            main()
        } // 문자 N 입력 시 초기화면으로 이동하며 이외는 정상적으로 메소드를 실행한다

        println("\n해야 할 일 목록")
        if(toDolist.isEmpty()) {
            println("\n입력된 해야 할 일이 없습니다")
            println("해야 할 일을 먼저 입력해주세요\n")
            return inputToDoList() // 해야 할 일 이 없다면 완료한 일을 수행할 수 없기 때문에 해야 할 일에 메소드를 실행한다
        } else {
            for((key,value) in toDolist) {
                println("기한은 $key 까지 이며 $value 을 해야 합니다")
            }

            println("\n위에 있는 목록 중 끝마친 기간을 입력해주세요")
            println("메인으로 돌아가려면 0을 입력해주세요")
            while(true) {
                val inputFinListTitle = readLine().toString()
                if(toDolist.contains(inputFinListTitle)) /* 완료한 기간을 입력하게되면 */ {
                    finList.put(inputFinListTitle, toDolist[inputFinListTitle]) //finList에 입력받은 키값(기한)과 해당 기한에 맞는 해야 할 일에 벨류값(해야 할 일)추가하고
                    toDolist.remove(inputFinListTitle) //toDoList에 있는 키값(기한)과 벨류값(해야 할 일) 삭제한다  (옮기는 기능)
                    println("$inputFinListTitle 기간에 해야 할 일이 완료되었습니다")
                    println("끝마친 일이 있다면 다시 한번 기간을 입력해주세요")
                } else if(inputFinListTitle == "0") {
                    print("메인 화면으로 돌아갑니다\n")
                    return main()
                    // 기한에 0을 입력하게 되면 초기화면으로 넘어가게된다
                } else {
                    println("정확하게 입력해주세요 ex) 1/1")
                } // 정확한 기한 또는 0이 아니라면 정확하게 입력하도록 유도한다
            }

        } // 해야 할 일을 가지고 있을때 실행한다

    }

}

class  printMode {
    fun printToDoList() {
        println("\n--해야 할 일 출력모드--")
        println("\n해야 할 일 목록")
        if(toDolist.isEmpty()) {
            println("입력된 해야 할 일이 없습니다")
        } else {
            for((key,value) in toDolist) {
                println("기한은 $key 까지 이며 $value 을 해야 합니다")
            }
        } // 해야 할 일이 비어있는지 확인한다, 비어있지 않다면 기한과 해야 할 일을 출력한다

        println("\n메인으로 돌아가려면 0을 입력해주세요")
        while(true) {
            val goMain = readLine().toString()
            if (goMain == "0") {
                return main()
            } else {
                println("메인으로 돌아가려면 0을 입력해주세요")
            }
        } // 오직 메인으로 돌아가기 위하여 0만 입력받게 한다
    }

    fun printFinList() {
        println("\n--완료한 일 출력모드--")
        println("\n완료한 일 목록")
        if(finList.isEmpty()) {
            println("입력된 완료한 일이 없습니다")
        } else {
            for((key,value) in finList) {
                println("기한은 $key 까지였고 $value 을 완료했습니다")
            }
        } // 완료한 일이 비어있는지 확인한다, 비어있지 않다면 기한과 완료한 일을 출력한다

        println("\n메인으로 돌아가려면 0을 입력해주세요")
        while(true) {
            val goMain = readLine().toString()
            if (goMain == "0") {
                return main()
            } else {
                println("메인으로 돌아가려면 0을 입력해주세요")
            }
        } // 오직 메인으로 돌아가기 위하여 0만 입력받게 한다
    }

    fun printAllList() {
        println("\n--전체 일정 출력모드--")
        println("\n해야 할 일 목록")
        if(toDolist.isEmpty()) {
            println("입력된 해야 할 일이 없습니다")
        } else {
            for((key,value) in toDolist) {
                println("기한은 $key 까지 이며 $value 을 해야 합니다")
            }
        } // 해야 할 일이 비어있는지 확인한다, 비어있지 않다면 기한과 해야 할 일을 출력한다

        println("\n완료한 일 목록")
        if(finList.isEmpty()) {
            println("입력된 완료한 일이 없습니다")
        } else {
            for((key,value) in finList) {
                println("기한은 $key 까지였고 $value 을 완료했습니다")
            }
        } // 완료한 일이 비어있는지 확인한다, 비어있지 않다면 기한과 완료한 일을 출력한다

        println("\n메인으로 돌아가려면 0을 입력해주세요")
        while(true) {

            val goMain = readLine().toString()
            if (goMain == "0") {
                return main()
            } else {
                println("메인으로 돌아가려면 0을 입력해주세요")
            }
        } // 오직 메인으로 돌아가기 위하여 0만 입력받게 한다

    } // 해야 할 일 목록과 완료한 일 목록을 전체적으로 보여주는 메서드

}

class endMode {
    fun endSys() {
        println("오늘도 수고하셨습니다")
        println("시스템을 종료합니다")
        return
    } // 시스템을 종료하는 메서드
}

fun main() {
    println("--환영합니다 toDoList 입니다--\n")
    println("입력모드")
    println("1) 해야 할 일 입력하기")
    println("2) 끝마친 일 입력하기\n")
    println("출력모드")
    println("3) 해야 할 일 출력하기")
    println("4) 끝마친 일 출력하기")
    println("5) 전체 일정 출력하기\n")
    println("종료모드")
    println("0) 시스템 종료하기\n")
    println(">필요한 기능의 번호를 입력해주세요")
    val num1 = readLine()!!.toInt() // 입력값을 숫자로 입력받기


    when(num1) {
        1 -> inputMode().inputToDoList()
        2 -> inputMode().inputFinList()
        3 -> printMode().printToDoList()
        4 -> printMode().printFinList()
        5 -> printMode().printAllList()
        0 -> endMode().endSys()
        else -> {
            println("0~5 사이 숫자를 입력해주세요")
            main()
        }
    }
    // 0~5사이에 숫자를 입력받았다면 해당 메소드를 실행하고
    // 이외의 모든 숫자와 문자를 입력받았다면 초기화면을 다시 실행한다
}