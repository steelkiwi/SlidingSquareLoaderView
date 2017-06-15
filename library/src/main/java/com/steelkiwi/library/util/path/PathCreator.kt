package com.steelkiwi.library.util.path

import android.graphics.Path

/**
 * Created by yaroslav on 6/15/17.
 */
class PathCreator(val startX: Int, val startY: Int, val viewMargin: Int, val viewWidth: Int) {

    fun createPath(type: PathType) : Path {
        when(type) {
            PathType.PATH_1 -> { return createPath1() }
            PathType.LAST_PATH_1 -> { return createLastPath1() }
            PathType.PATH_2 -> { return createPath2() }
            PathType.LAST_PATH_2 -> { return createLastPath2() }
            PathType.PATH_3 -> { return createPath3() }
            PathType.LAST_PATH_3 -> { return createLastPath3() }
            PathType.PATH_4 -> { return createPath4() }
            PathType.LAST_PATH_4 -> { return createLastPath4() }
            PathType.PATH_5 -> { return createPath5() }
            PathType.LAST_PATH_5 -> { return createLastPath5() }
            PathType.PATH_6 -> { return createPath6() }
            PathType.LAST_PATH_6 -> { return createLastPath6() }
        }
    }

    private fun createPath1() : Path {
        val path1: Path = Path()
        path1.moveTo(startX.toFloat() + viewMargin, startY.toFloat())
        path1.lineTo(startX.toFloat() + viewMargin, startY.toFloat() + viewWidth + viewMargin)
        path1.lineTo(startX.toFloat() + viewWidth + viewMargin * 2, startY.toFloat() + viewWidth + viewMargin)
        return path1
    }

    private fun createLastPath1() : Path {
        val lastPath1: Path = Path()
        lastPath1.moveTo(startX.toFloat() + viewWidth + viewMargin * 2, startY.toFloat() + viewWidth + viewMargin)
        lastPath1.lineTo(startX.toFloat() + viewWidth + viewMargin * 2, startY.toFloat())
        return lastPath1
    }

    private fun createPath2() : Path {
        val path2: Path = Path()
        path2.moveTo(startX.toFloat() + viewWidth + viewMargin * 2, startY.toFloat())
        path2.lineTo(startX.toFloat() + viewWidth + viewMargin * 2, startY.toFloat() - viewWidth - viewMargin)
        path2.lineTo(startX.toFloat() + (viewWidth * 2 + viewMargin * 3), startY.toFloat() - viewWidth - viewMargin)
        return path2
    }

    private fun createLastPath2() : Path {
        val lastPath: Path = Path()
        lastPath.moveTo(startX.toFloat() + (viewWidth * 2 + viewMargin * 3), startY.toFloat() - viewWidth - viewMargin)
        lastPath.lineTo(startX.toFloat() + (viewWidth * 2 + viewMargin * 3), startY.toFloat())
        return lastPath
    }

    private fun createPath3() : Path {
        val path: Path = Path()
        path.moveTo(startX.toFloat() + (viewWidth * 2 + viewMargin * 3), startY.toFloat())
        path.lineTo(startX.toFloat() + (viewWidth * 2 + viewMargin * 3), startY.toFloat() + viewWidth + viewMargin)
        path.lineTo(startX.toFloat() + (viewWidth * 3 + viewMargin * 4), startY.toFloat() + viewWidth + viewMargin)
        return path
    }

    private fun createLastPath3() : Path {
        val lastPath: Path = Path()
        lastPath.moveTo(startX.toFloat() + (viewWidth * 3 + viewMargin * 4), startY.toFloat() + viewWidth + viewMargin)
        lastPath.lineTo(startX.toFloat() + (viewWidth * 3 + viewMargin * 4), startY.toFloat())
        return lastPath
    }

    private fun createPath4() : Path {
        val path: Path = Path()
        path.moveTo(startX.toFloat() + (viewWidth * 3 + viewMargin * 4), startY.toFloat())
        path.lineTo(startX.toFloat() + (viewWidth * 3 + viewMargin * 4), startY.toFloat() - viewWidth - viewMargin)
        path.lineTo(startX.toFloat() + (viewWidth * 2 + viewMargin * 3), startY.toFloat() - viewWidth - viewMargin)
        return path
    }

    private fun createLastPath4() : Path {
        val lastPath: Path = Path()
        lastPath.moveTo(startX.toFloat() + (viewWidth * 2 + viewMargin * 3), startY.toFloat() - viewWidth - viewMargin)
        lastPath.lineTo(startX.toFloat() + (viewWidth * 2 + viewMargin * 3), startY.toFloat())
        return lastPath
    }

    private fun createPath5() : Path {
        val path: Path = Path()
        path.moveTo(startX.toFloat() + (viewWidth * 2 + viewMargin * 3), startY.toFloat())
        path.lineTo(startX.toFloat() + (viewWidth * 2 + viewMargin * 3), startY.toFloat() + viewWidth + viewMargin)
        path.lineTo(startX.toFloat() + (viewWidth + viewMargin * 2), startY.toFloat() + viewWidth + viewMargin)
        return path
    }

    private fun createLastPath5() : Path {
        val lastPath: Path = Path()
        lastPath.moveTo(startX.toFloat() + (viewWidth + viewMargin * 2), startY.toFloat() + viewWidth + viewMargin)
        lastPath.lineTo(startX.toFloat() + (viewWidth + viewMargin * 2), startY.toFloat())
        return lastPath
    }

    private fun createPath6() : Path {
        val path: Path = Path()
        path.moveTo(startX.toFloat() + (viewWidth + viewMargin * 2), startY.toFloat())
        path.lineTo(startX.toFloat() + (viewWidth + viewMargin * 2), startY.toFloat() - viewWidth - viewMargin)
        path.lineTo(startX.toFloat() + viewMargin, startY.toFloat() - viewWidth - viewMargin)
        return path
    }

    private fun createLastPath6() : Path {
        val lastPath: Path = Path()
        lastPath.moveTo(startX.toFloat() + viewMargin, startY.toFloat() - viewWidth - viewMargin)
        lastPath.lineTo(startX.toFloat() + viewMargin, startY.toFloat())
        return lastPath
    }
}