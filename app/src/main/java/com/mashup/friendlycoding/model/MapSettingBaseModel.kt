package com.mashup.friendlycoding.model

import java.util.*

open class MapSettingBaseModel {

    // Act 마다 반복되는 거 있으면 알아서 빼주세용
    val defaultCodeBlock_tutorial: ArrayList<CodeBlock> = arrayListOf(
        CodeBlock("move();"),
        CodeBlock("turnLeft();"),
        CodeBlock("turnRight();")
    )
    val defaultCodeBlock: ArrayList<CodeBlock> = arrayListOf(
        CodeBlock("move();"),
        CodeBlock("turnLeft();"),
        CodeBlock("turnRight();"),
        CodeBlock("for(", type = 1),
        CodeBlock("if()", type = 2),
        CodeBlock("while()", type = 2),
        CodeBlock("}", type = 4)
    )
    var stageCodeBlock0 = arrayListOf(
        CodeBlock("pickAxe();"),
        CodeBlock("isPickAxe()", type = 3, argument = 3))

    var stageCodeBlock2_1 = arrayListOf(
        CodeBlock("isBook()", type = 3, argument = 5),
        CodeBlock("pickBook();")
    )
    val stageCodeBlock2_2 = arrayListOf(
        CodeBlock("isMushroom()", type = 3, argument = 4),
        CodeBlock("eatMushRoom();")
    )
    var stageCodeBlock2_3 = arrayListOf(
        CodeBlock("isBranch()", type = 3, argument = 6),
        CodeBlock("pickBranch();")
    )


    /**
     * 보스 배틀 코드블락
     * **/


    val defaultBattleCodeBlock = arrayListOf(
        CodeBlock("if()", type = 2),
        CodeBlock("while()", type = 2),
        CodeBlock("for(", type = 1),
        CodeBlock("}", type = 4),
        CodeBlock("attack();"),
        CodeBlock("isAlive()", type = 3, argument = 7)
    )

    var battleCodeBlock0 = arrayListOf(
        CodeBlock("detectedFire()", type = 3, argument = 0),
        CodeBlock("detectedWater()", type = 3, argument = 1))


    val mapListActNull = arrayOf(
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)


        )
    val mapListAct0 = arrayOf(
        arrayOf(0, 0, 0, 0, 1, 0, 1, 0, 0, 0),
        arrayOf(1, 1, 0, 0, 1, 0, 1, 1, 1, 1),
        arrayOf(1, 0, 0, 0, 1, 0, 0, 0, 0, 0),
        arrayOf(1, 0, 1, 1, 1, 0, 1, 1, 1, 1),
        arrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(1, 0, 0, 0, 0, 1, 1, 1, 1, 0),
        arrayOf(1, 1, 1, 1, 0, 1, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 1, 0, 1, 0, 0),
        arrayOf(0, 1, 1, 1, 1, 1, 0, 1, 1, 1),
        arrayOf(0, 0, 3, 0, 0, 1, 0, 0, 0, 2)    // 클리어되는 곳을 일단 2로 설정함, 3은 곡괭이
    )
    val mapListAct1 = arrayOf(
        arrayOf(0, 0, 0, 0, 1, 0, 1, 0, 0, 0),
        arrayOf(1, 1, 0, 0, 1, 0, 1, 1, 1, 1),
        arrayOf(1, 0, 0, 0, 1, 0, 0, 0, 0, 0),
        arrayOf(1, 0, 1, 1, 1, 0, 1, 1, 1, 1),
        arrayOf(1, 0, 0, 4, 0, 0, 0, 0, 0, 0),
        arrayOf(1, 0, 0, 0, 0, 1, 1, 1, 1, 0),
        arrayOf(1, 1, 1, 1, 0, 1, 0, 0, 4, 0),
        arrayOf(0, 0, 0, 0, 0, 1, 0, 1, 0, 0),
        arrayOf(0, 1, 1, 1, 1, 1, 0, 1, 1, 1),
        arrayOf(0, 0, 4, 0, 0, 1, 0, 0, 0, 2)  // 4는 버섯
    )
    val mapListAct2_1 = arrayOf(
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 5, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 2)  // 5는 책
    )
    val mapListAct2_2 = arrayOf(
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 4, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 4, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 2)  // 4는 버섯
    )
    val mapListAct2_3 = arrayOf(
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 6, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 6, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 2)  // 6은 나무
    )

}