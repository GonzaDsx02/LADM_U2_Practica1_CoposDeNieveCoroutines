package mx.edu.ittepic.ladm_u2_practica1_coposdenieve

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class Lienzo(este: MainActivity) : View(este) {

    var este = este
    var copos = Array<Copos>(500,{Copos(this)})
    //var copitos = ArrayList<Copos>()
    var contador = 0
    var tope = false
    //var t = true
    //var contT = 0
    //var ts = false


    init{
        copos[0].activar()

    }

    val cantCopos = GlobalScope.launch{
        while(true){
            este.runOnUiThread {
                if(contador < copos.size-1 && !tope){
                    contador++
                    copos[contador].activar()
                    este.setTitle("Aumentando "+((copos.size-1)-contador))
                }else{
                    tope = true
                }

                if(contador > 0 && tope){
                    contador--
                    copos[contador].desactivar()
                    este.setTitle("Disminuyendo ${contador}")
                }else{
                    tope = false
                }
            }
            delay(100L)
        }
    }

    val timeSpeed = GlobalScope.launch{
        while(true){
            este.runOnUiThread {
                if(tope){
                    for(copo in copos){
                        copo.decSpeed()
                        copo.radio-=0.1f
                    }
                }else{
                    for(copo in copos){
                        copo.incSpeed()
                        copo.radio+=0.1f
                    }
                }
            }
            delay(3000L)
        }
    }

    val x = GlobalScope.launch(EmptyCoroutineContext,CoroutineStart.DEFAULT){
        while(true){
            este.runOnUiThread {
                invalidate()
            }
            delay(10L)
        }
    }


    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val p = Paint()
        c.drawColor(Color.BLACK)

        //cielo
        c.drawColor(Color.rgb(4,8,37))

        //pasto
        p.setColor(Color.rgb(52,136,34))
        c.drawRect(0f,900f,720f,1120f,p)

        //Edificio
        p.setColor(Color.rgb(111,111,111))
        c.drawRect(400f,200f,700f,1120f,p)

        //ventanas
        p.setColor(Color.rgb(127,217,225))
        c.drawRect(450f,300f,500f,350f,p)

        p.setColor(Color.rgb(127,217,225))
        c.drawRect(600f,300f,650f,350f,p)

        p.setColor(Color.rgb(127,217,225))
        c.drawRect(450f,400f,500f,450f,p)

        p.setColor(Color.rgb(127,217,225))
        c.drawRect(600f,400f,650f,450f,p)

        p.setColor(Color.rgb(127,217,225))
        c.drawRect(450f,500f,500f,550f,p)

        p.setColor(Color.rgb(127,217,225))
        c.drawRect(600f,500f,650f,550f,p)

        p.setColor(Color.rgb(127,217,225))
        c.drawRect(450f,700f,500f,750f,p)

        p.setColor(Color.rgb(127,217,225))
        c.drawRect(600f,700f,650f,750f,p)

        p.setColor(Color.rgb(127,217,225))
        c.drawRect(450f,800f,500f,850f,p)

        p.setColor(Color.rgb(127,217,225))
        c.drawRect(600f,800f,650f,850f,p)

        p.setColor(Color.rgb(127,217,225))
        c.drawRect(450f,900f,500f,950f,p)

        p.setColor(Color.rgb(127,217,225))
        c.drawRect(600f,900f,650f,950f,p)

        //puerta
        p.setColor(Color.rgb(127,217,225))
        c.drawRect(515f,1020f,585f,1120f,p)

        //luna
        p.setColor(Color.rgb(151,151,151))
        c.drawCircle(150f,150f,100f,p)

        p.setColor(Color.rgb(255,245,184))
        c.drawCircle(170f,170f,30f,p)

        p.setColor(Color.rgb(255,245,184))
        c.drawCircle(180f,120f,20f,p)

        //pino
        var t1 = Triangulo(c)
        t1.setColor(Color.rgb(0,85,0))
        t1.dibujar(125f,600f,200f,1120f,50f,1120f)

        //mono de nieve
        p.setColor(Color.WHITE)
        c.drawCircle(300f,1080f,40f,p)
        c.drawCircle(300f,1020f,30f,p)
        c.drawCircle(300f,970f,25f,p)
        //nariz del mono
        var t2 = Triangulo(c)
        t2.setColor(Color.rgb(255,164,7))
        t2.dibujar(295f,980f,305f,980f,302f,1000f)

        p.setColor(Color.BLACK)
        c.drawCircle(290f,970f,5f,p)
        c.drawCircle(310f,970f,5f,p)
        c.drawLine(200f,1000f,290f,1020f,p)
        c.drawLine(310f,1020f,400f,1000f,p)


        for(copo in copos){
            if(copo.isActive() || (copo.cy >= 1 && copo.cy <= height)){
                copo.pintar(c)
                copo.mover()
            }
        }
    }
}

class Triangulo(canvas: Canvas){
    val c = canvas
    private var color = Color.BLACK

    fun setColor(newColor:Int){
        color = newColor
    }

    fun dibujar(c1x: Float,c1y: Float,c2x: Float,c2y: Float,c3x: Float,c3y: Float,){
        val p = Paint()
        val path: Path = Path()
        path.moveTo(c1x,c1y)//vertice de arriba
        path.lineTo(c2x,c2y)//vertice derecho
        path.lineTo(c3x,c3y)//vertice izquierdo
        path.close()
        p.color=color
        p.style = Paint.Style.FILL
        c.drawPath(path,p)
    }
}