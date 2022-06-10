package com.rpgtasks.dungeon

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.rpgtasks.R

class PlotStory (val de : DungeonEventActivity){

    var nextpath1 = ""
    var nextpath2 = ""
    var nextpath3 = ""
    var nextpath4 = ""

    fun select(postion : String){

        when(postion){
            "test1" -> start()
            "test3" -> test3()
            "test4" -> test4()
        }

    }

    fun start(){

        de.binding.storyText.text = "Hello"

        de.binding.path1.text = "1"
        de.binding.path2.text = "2"
        de.binding.path3.text = "3"
        de.binding.path4.text = "4"

        de.binding.path2.visibility = View.VISIBLE
        de.binding.path3.visibility = View.VISIBLE
        de.binding.path4.visibility = View.VISIBLE

        nextpath1 = "test1"
        nextpath2 = "test2"
        nextpath3 = "test3"
        nextpath4 = "test4"

    }
    fun test3() {

        de.binding.imageView2.setImageResource(R.drawable.ic_action_forever)
        de.binding.storyText.text = "Hello3"

        de.binding.path1.text = "back"
        de.binding.path2.text = ""
        de.binding.path3.text = ""
        de.binding.path4.text = ""

        de.binding.path2.visibility = View.INVISIBLE
        de.binding.path3.visibility = View.INVISIBLE
        de.binding.path4.visibility = View.INVISIBLE

        nextpath1 = "test1"
        nextpath2 = ""
        nextpath3 = ""
        nextpath4 = ""

    }

    fun test4(){

        de.binding.imageView2.setImageResource(R.drawable.ic_action_today)
        de.binding.storyText.text = "Hello4"

        de.binding.path1.text = "back"
        de.binding.path2.text = ""
        de.binding.path3.text = ""
        de.binding.path4.text = ""

        de.binding.path2.visibility = View.INVISIBLE
        de.binding.path3.visibility = View.INVISIBLE
        de.binding.path4.visibility = View.INVISIBLE

        nextpath1 = "test1"
        nextpath2 = ""
        nextpath3 = ""
        nextpath4 = ""

    }

}