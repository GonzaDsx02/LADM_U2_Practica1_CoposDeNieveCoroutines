package mx.edu.ittepic.ladm_u2_practica1_coposdenieve

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.random.Random

class Copos(lienzo: Lienzo) {
    val l = lienzo
    val color = Color.WHITE
    var cx = 0F
    var cy = 0F
    var radio = 0F
    var speed = 0
    var cayendo: Boolean

    init {
        cx = Random.nextInt(0,700).toFloat()
        radio = Random.nextInt(5,15).toFloat()
        speed = Random.nextInt(2,4)
        cy = -radio
        cayendo = false
    }

    fun isActive(): Boolean{
        return cayendo
    }

    fun activar(){
        cayendo = true
        cy = -100f
    }

    fun desactivar(){
        cayendo = false
    }

    fun incSpeed(){
        speed++
    }

    fun decSpeed(){
        speed--
    }

    fun mover(){
        if(cy <= l.height-radio){
            cy+=speed
        }else{
            if(cayendo){
                cy = -100f
            }
        }
    }

    fun pintar(canvas: Canvas){
        val p = Paint()
        p.color = color
        canvas.drawCircle(cx,cy,radio,p)
    }
}