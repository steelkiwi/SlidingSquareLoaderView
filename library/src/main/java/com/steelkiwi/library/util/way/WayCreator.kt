package com.steelkiwi.library.util.way

import com.steelkiwi.library.util.path.PathCreator
import com.steelkiwi.library.util.path.PathType

/**
 * Created by yaroslav on 6/15/17.
 */
class WayCreator(startX: Int, startY: Int, viewMargin: Int, viewWidth: Int) {

    private val pathCreator: PathCreator = PathCreator(startX, startY, viewMargin, viewWidth)

    fun createWay(type: WayType) : WayHolder {
        when(type) {
            WayType.WAY_1 -> { return createWay1() }
            WayType.WAY_2 -> { return createWay2() }
            WayType.WAY_3 -> { return createWay3() }
            WayType.WAY_4 -> { return createWay4() }
            WayType.WAY_5 -> { return createWay5() }
            WayType.WAY_6 -> { return createWay6() }
        }
    }

    private fun createWay1() : WayHolder {
        val wayHolder: WayHolder = WayHolder()
        wayHolder.currentWay = pathCreator.createPath(PathType.PATH_1)
        wayHolder.lastWay = pathCreator.createPath(PathType.LAST_PATH_1)
        return wayHolder
    }

    private fun createWay2() : WayHolder {
        val wayHolder: WayHolder = WayHolder()
        wayHolder.currentWay = pathCreator.createPath(PathType.PATH_2)
        wayHolder.lastWay = pathCreator.createPath(PathType.LAST_PATH_2)
        return wayHolder
    }

    private fun createWay3() : WayHolder {
        val wayHolder: WayHolder = WayHolder()
        wayHolder.currentWay = pathCreator.createPath(PathType.PATH_3)
        wayHolder.lastWay = pathCreator.createPath(PathType.LAST_PATH_3)
        return wayHolder
    }

    private fun createWay4() : WayHolder {
        val wayHolder: WayHolder = WayHolder()
        wayHolder.currentWay = pathCreator.createPath(PathType.PATH_4)
        wayHolder.lastWay = pathCreator.createPath(PathType.LAST_PATH_4)
        return wayHolder
    }

    private fun createWay5() : WayHolder {
        val wayHolder: WayHolder = WayHolder()
        wayHolder.currentWay = pathCreator.createPath(PathType.PATH_5)
        wayHolder.lastWay = pathCreator.createPath(PathType.LAST_PATH_5)
        return wayHolder
    }

    private fun createWay6() : WayHolder {
        val wayHolder: WayHolder = WayHolder()
        wayHolder.currentWay = pathCreator.createPath(PathType.PATH_6)
        wayHolder.lastWay = pathCreator.createPath(PathType.LAST_PATH_6)
        return wayHolder
    }
}