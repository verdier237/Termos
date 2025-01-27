package com.example.viewsandsensor.Views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import kotlin.math.roundToInt
import kotlin.random.Random


class Something(context: Context):View(context){
    var change: Float = this.height/2f
    get() = field
    set(value) {
        field  = value
//        //indice = (0..2).random()
//        if (value >= 50f){
//            indice = 1;
//        }else if (value <= 0 ){
//            indice = 2;
//        }else if (value <=50f && value >=0){
//            indice = 0;
//        }
        invalidate()
    }
    private lateinit var drawPaint:Paint;
    private lateinit var drawText:Paint;
    @SuppressLint("SuspiciousIndentation", "DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var maxPos = this.height/6f; //100
        var midPos = this.height/2f; //0
        var minPos = this.height/1.09f; //-273.1
        drawPaint = Paint();
        drawPaint.color = Color.BLACK;
        drawPaint.isAntiAlias = true;
        drawPaint.strokeWidth = 10f;
        drawPaint.style = Paint.Style.STROKE;
        drawPaint.strokeJoin = Paint.Join.ROUND;
        drawPaint.strokeCap = Paint.Cap.ROUND;

        drawText = Paint();
        drawText.textSize = 49f
        drawText.style = Paint.Style.FILL;
        var i = 0;
        var j = 0;
//        if (isRenderAll){
            // ligne de gauche
            canvas.drawLine(this.width/4f,this.height*1f-this.height/20,this.width/4f,this.height/8f,drawPaint)
            canvas.drawText("C",this.width/4f,this.height/8f,drawText)
            //lignes de graduation
        // e est la variable qui me permet de graduer
        var e = -110f;
            //graduation de gauche
            for (i in (20.. 1320) step 60){
                if (j == 0) j=20 else j=0
                canvas.drawLine(this.width/4f,this.height*1f-this.height/20-i,this.width/4f+this.width/20+j,this.height*1f-this.height/20-i,drawPaint)
                if (i>20) if (j == 0) canvas.drawText(e.roundToInt().toString(),this.width/4f+this.width/15+j,this.height*1f-this.height/25-i,drawText)
                e+=10;

            }
            e = -110f;
            j=0
        // graduation de droite
            for (i in (20.. 1320) step 60){
                if (j == 0) j=20 else j=0
                canvas.drawLine(this.width*1f-this.width/3f+j,this.height*1f-this.height/20-i,this.width*1f-this.width/4f,this.height*1f-this.height/20-i,drawPaint)
                if (i>20) if (j == 0) canvas.drawText(((e*9/5)+32).roundToInt().toString(),this.width*1f-this.width/3f+j-90,this.height*1f-this.height/25-i,drawText)
                e+=10;
            }

            //ligne de droite
            canvas.drawLine(this.width*1f-this.width/4f,this.height*1f-this.height/20,this.width*1f-this.width/4f,this.height/8f,drawPaint)
            canvas.drawText("F",this.width*1f-this.width/4f,this.height/8f,drawText)
//            isRenderAll = false;
//        }
        // on raffrachi seulement notre thermo
//        drawPaint.color = Color.BLACK
        drawPaint.strokeWidth = 56f;
        canvas.drawCircle(this.width/2f,this.height*1f, 150f,Paint(Color.RED))
        // linge de thermo
        var check = this.height/6f;
        // si mon thermo est dans cette plage
        if (this.change >= 0 && this.change <=100){
            // je reverse sa position (explication en schema)
                this.change  = 100 - this.change;
            // calcul de l'ecart dans mon intervalle des axes des y
            var ecart = midPos-maxPos;

            //explication de la formule(EN CLASSE)
            this.change = this.change *(ecart/100) + maxPos;
            // Changer la couleur en fonction de la température
            if (this.change < 0) {
                drawPaint.color = Color.BLUE // Température basse
            } else if (this.change in 0f..50f) {
                drawPaint.color = Color.YELLOW // Température moyenne
            } else {
                drawPaint.color = Color.RED // Température élevée
            }
        }else if (this.change <0 && this.change > -274 ){
            this.change = -1*this.change;
            var ecart = minPos - midPos;
            this.change = this.change *(ecart/273) + midPos;
        }
        canvas.drawLine(this.width/2f,this.height*1f-this.height/20,this.width/2f,(this.change),drawPaint)
    }
}