package com.mashup.friendlycoding.viewmodel

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.lifecycle.ViewModel

class PrincessViewModel : ViewModel() {
    val mapList = arrayOf(
        arrayOf(0, 0, 0, 0, 1, 0, 1, 0, 0, 0),
        arrayOf(1, 1, 0, 0, 1, 0, 1, 1, 1, 1),
        arrayOf(1, 0, 0, 0, 1, 0, 0, 0, 0, 0),
        arrayOf(1, 0, 1, 1, 1, 0, 1, 1, 1, 1),
        arrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
        arrayOf(1, 0, 0, 0, 0, 1, 1, 1, 1, 0),
        arrayOf(1, 1, 1, 1, 0, 1, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 0, 0, 1, 0, 1, 0, 0),
        arrayOf(0, 1, 1, 1, 1, 1, 0, 1, 1, 1),
        arrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0)
    )

    var nowX = 0
    var nowY = 9
    private var princessImg: ImageView? = null
    var direction = 1
    var oneBlock = 0f
    var princessX : Float = 0.0f
    var princessY : Float = 0.0f
    var unit = 1
    var width = 0
    val n = 10

    var handler = Handler()

    fun move(i: Int) {
        when (i) {
            -1 -> clear()
            0 -> go()
            1 -> rotationLeft()
            2 -> rotationRight()
        }
    }

    fun setPrincessImage(view: ImageView) {
        this.princessImg = view
        this.princessX = princessImg!!.x
        this.princessY = princessImg!!.y
    }

    fun setViewSize(width: Int) {
        this.width = width
        oneBlock = (width / n + width % n).toFloat()
        this.princessImg?.height ?: oneBlock.toInt()
        setOnePoint()


    }

    private fun setOnePoint() {
        nowX = 0
        nowY = 9
        direction = 1
        princessImg!!.x = oneBlock * nowX - oneBlock * 0.1f
        princessImg!!.y = oneBlock * nowY - oneBlock * 0.23f
    }

    fun go() {

        changeXY()
        check()
    }

    @SuppressLint("LongLogTag")
    fun check() {
        if (nowX < 10 && nowX > -1 && nowY < 10 && nowY > -1 && mapList[nowY][nowX] == 1) {
            setOnePoint()
        } else {
            setOnePoint()
        }

    }
    fun rotationLeft() {
        direction -= 1
        if (direction < 0)
            direction += 4
    }

    fun rotationRight() {
        if (direction == 3)
            direction = 0
        else
            direction++
    }

    fun changeXY() {
        val one = oneBlock
        direction %= 4
        when (direction) {
            //goint up
            0 -> {
                princessImg!!.y = (princessImg!!.y - one)
                nowY--
            }
            //going right
            1 -> {
                princessImg!!.x = (princessImg!!.x + one)
                nowX++
            }
            //going down
            2 -> {
                princessImg!!.y = (princessImg!!.y + one)
                nowY++
            }
            //going left
            3 -> {
                princessImg!!.x = (princessImg!!.x - one)
                nowX--
            }
        }
    }

    fun clear() {
        unit = 1
        princessImg!!.x = princessX
        princessImg!!.y = princessY
    }
}