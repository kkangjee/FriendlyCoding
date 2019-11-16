package com.mashup.friendlycoding.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mashup.friendlycoding.Map
import com.mashup.friendlycoding.Monster
import com.mashup.friendlycoding.Princess
import com.mashup.friendlycoding.ignoreBlanks
import java.util.*

open class RunBaseModel {
    // PrincessViewModel
    var moveView = MutableLiveData<Int>()    // MainActivity에게 보내는 시그널 - 진행 중 상황. 코드 실행의 시작, 종료, 공주의 움직임 등.

    // CodeBlockViewModel
    var nowProcessing = MutableLiveData<Int>()   // MainActivity에게 보내는 시그널 - 현재 진행 중인 코드 번호
    var nowTerminated = MutableLiveData<Int>()   // MainActiivty에게 보내는 시그널 - 현재 진행 종료된 코드 번호
    var mCodeBlock = MutableLiveData<ArrayList<CodeBlock>>()    // 코드 블록, MainActivity가 보고 뷰의 수정과 스크롤이 일어남
    var insertBlockAt = MutableLiveData<Int>()  // MainActivity에게 보내는 시그널 - 코드 블록이 어디에 삽입될 지를 알려준다.

    // BattleViewModel
    var metBoss = MutableLiveData<Boolean>()  // MainActivity에게 보내는 시그널 - 플레이어가 보스를 만났는지 여부. 만났으면 뷰와 인풋코드블록을 바꾼다.
    var monsterAttack = MutableLiveData<Int>()  // MainActivity에게 보내는 시그널 - 보스의 공격 유형
    var princessAction = MutableLiveData<Int>() // MainActivity에게 보내는 시그널 - 보스전에서의 공주의 행동
    var monsterAttacked = MutableLiveData<Boolean>()    // MainActivity에게 보내는 시그널 - 보스가 공격당했는지 여부

    // 공주의 좌표
    var x = 0 // x좌표
    var y = 9 // y좌표
    var driction = 1 // 방향 : 0-> 위, 1-> 오른쪽, 2-> 아래, 3-> 왼쪽

    var mPrincess = Princess()
    var mMap = Map()
    var mMonster: Monster? = null

    var insertBlockPosition = 0
    var insertedBlock: String? = null   // 삽입된 코드 블록의 이름
    var changingView: String? = null    // 아이템을 주웠거나 아이템이 파괴됐을시 해당 아이템의 ID를 MainActivity에게 알려준다.

    var jumpTo = 0 // jump 할 주소
    var IR = 0  // 명령어 실행할 주소
    var iterator = 0 // 반복자
    var blockLevel = 0 // 들여쓰기 정도.
    var bracketStack = Stack<Int>()  // 괄호 체크, 그와 동시에 jump 할 명령어 주소 얻기 위함
    var coc = arrayOf(-1, -1, -1, -1, -1) // 행동 수칙이 있는가?
    var isAttacking = false  // 몬스터가 공격 중에 있는지
    var isBossAlive = false
    var speed = 500L
    var iteratorStack = Stack<Int>()

    var openingBracket : Int = 0
    var closingBracket : Int = 0

    /***
     * inti()
     * ***/
    fun init() {
        mCodeBlock.value = ArrayList()
        insertBlockPosition = -1
        insertBlockAt.postValue(-1)
        metBoss.value = false
        monsterAttack.value = -1
        princessAction.value = 0
    }

    /***
     * 공주 이동 관련 코드
     * ***/
    fun movePrincess() {
        driction %= 4
        when (driction) {
            //goint up
            0 -> y--
            //going right
            1 -> x++
            //going down
            2 -> y++
            //going left
            3 -> x--
        }
    }

    fun rotate(LeftOrRight: Boolean) {
        driction = (driction + 4) % 4
        if (!LeftOrRight) {   // 왼쪽으로
            driction -= 1
        } else {  // 오른쪽으로
            driction++
        }
    }

    /***
     * 코드블락 관련 코드
     * ***/
    fun clearBlock() {
        x = 0
        y = 9
        driction = 1
        iterator = 0
        jumpTo = 0
        blockLevel = 0
        IR = 0
        moveView.postValue(-1)
        insertBlockAt.postValue(-1)
        val block = mCodeBlock.value
        block!!.clear()
        bracketStack.clear()
        mCodeBlock.postValue(block)
        nowTerminated.postValue(IR)
        isBossAlive = false
    }

    fun changeBlockLevel(OpenOrClose: Boolean) {
        if (!OpenOrClose) {  // 여는 괄호를 삭제함
            Log.e("여는 괄호", "삭제")
            blockLevel--
            if (bracketStack.isNotEmpty()) {
                bracketStack.pop()
            }
            openingBracket--
        }
        else {
            Log.e("닫는 괄호", "삭제")
            blockLevel++  // 닫는 괄호를 삭제함
            closingBracket--
            if (openingBracket != closingBracket)
                bracketStack.push(1)
        }
    }

    private fun insertBlock(at: String, block: String): String {
        var idx = 0
        val inserted: String
        while (at[idx++] != '(') { }
        inserted = at.substring(0, idx) + block + ')'
        return inserted
    }

    fun addNewBlock(codeBlock: CodeBlock) {
        if (insertBlockPosition != -1) {
            if (codeBlock.type == 3) {
                Log.e("블록을 추가합니다", "${codeBlock.funcName}  ${codeBlock.type}  ${codeBlock.argument}")
                mCodeBlock.value!![insertBlockPosition].argument = codeBlock.argument
                insertedBlock = codeBlock.funcName
                mCodeBlock.value!![insertBlockPosition].funcName = insertBlock(mCodeBlock.value!![insertBlockPosition].funcName, insertedBlock!!)
                insertBlockAt.postValue(insertBlockPosition)
                insertBlockPosition = -1
                return
            }
            else
                return
        }
        else if (codeBlock.type == 3) {
            return
        }

        val adding = CodeBlock(codeBlock.funcName, address = IR, type = codeBlock.type)
        if (adding.funcName == "}") {
            if (bracketStack.empty()) {
                return
            }
            bracketStack.pop()
            Log.e("block level", "decreases")
            blockLevel--
            closingBracket++
        }

        var tap = ""
        for (i in 0 until blockLevel) {
            tap += "    "
        }

        adding.funcName = tap + adding.funcName

        if (adding.type != 0) {
            bracketStack.push(1)
            blockLevel++
            openingBracket++
        }

        val block = mCodeBlock.value
        block!!.add(adding)
        mCodeBlock.postValue(block)
        insertBlockAt.postValue(-1)
        Log.e("${adding.funcName} ", "${insertBlockAt.value}에 추가됨")
    }

    fun deleteBlock(position : Int) {
        if (mCodeBlock.value!![position].type != 0) {
            this.changeBlockLevel(false)

            for (i in position until mCodeBlock.value!!.size) {
                Log.e("코드 들이기", mCodeBlock.value!![i].funcName)
                if (mCodeBlock.value!![i].funcName == "}") {
                    break
                }
                else if (mCodeBlock.value!![i].funcName.substring(0, 4) == "    ") {
                    Log.e("코드 들이기", mCodeBlock.value!![i].funcName)
                    mCodeBlock.value!![i].funcName = mCodeBlock.value!![i].funcName.substring(4)
                }
            }
        }
        else if (ignoreBlanks(mCodeBlock.value!![position].funcName) == "}") {
            this.changeBlockLevel(true)
        }
        mCodeBlock.value!!.removeAt(position)
    }

    fun setAddress (open : Int) {
        var ir = open
        val myself = ir

        Log.e("여는 괄호?", mCodeBlock.value!![ir].funcName + "$ir")
        ir++
        while (ir < mCodeBlock.value!!.size && ignoreBlanks(mCodeBlock.value!![ir].funcName) != "}") {
            if (mCodeBlock.value!![ir].type != 0) {
                setAddress(ir)
                ir++
            }
            ir++
        }
        Log.e("닫는 괄호?", mCodeBlock.value!![ir].funcName + " $myself" + " $ir 까지")
        mCodeBlock.value!![ir].address = myself

        if (mCodeBlock.value!![myself].type == 2) {
            coc[mCodeBlock.value!![myself].argument] = myself
            mCodeBlock.value!![myself].address = ir
        }
    }
}