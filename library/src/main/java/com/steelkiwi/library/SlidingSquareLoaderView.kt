package com.steelkiwi.library

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Path
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.widget.RelativeLayout
import com.steelkiwi.library.extensions.getLeftMargin
import com.steelkiwi.library.extensions.onEnd
import com.steelkiwi.library.extensions.setLeftMargin
import com.steelkiwi.library.util.Constant
import com.steelkiwi.library.util.way.WayCreator
import com.steelkiwi.library.util.way.WayHolder
import com.steelkiwi.library.util.way.WayType
import com.steelkiwi.library.view.SquareView
import java.util.ArrayList

/**
 * Created by yaroslav on 6/15/17.
 */
class SlidingSquareLoaderView : RelativeLayout, StateController {

    private val repeatHandler: Handler = Handler()
    // instance for create ways for view to animate
    private lateinit var wayCreator: WayCreator
    // views list
    private val squares: ArrayList<SquareView> = ArrayList()
    // created ways list
    private val ways: ArrayList<WayHolder> = ArrayList()
    // views list position
    private lateinit var viewsPosition: IntArray
    // ways list position
    private lateinit var waysPosition: IntArray
    // gradient list position
    private lateinit var gradientsPosition: IntArray
    // view left margin
    private var viewMargin: Int = 0
    // view corner radius
    private var viewCornerRadius: Int = 0
    // start gradient for view
    private var viewStartGradientColor: Int = 0
    // end gradient for view
    private var viewEndGradientColor: Int = 0
    // background for parent ViewGroup
    private var parentBackground: Int = 0
    // current way
    private var currentWayPosition: Int = 0
    // current position for view to animate it
    private var currentViewPosition: Int = 0
    // current gradient position for offset
    private var gradientPosition: Int = 0
    // square item size
    private var squareViewSize: Int = 0
    // stop views animation
    private var stopAnimation : Boolean = true
    // flag for wait for animation stopping
    private var waitToFinish : Boolean = false

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        prepareDefaultPositions()
        prepareAttributesSet(attrs)
        prepareParentSettings()
        prepareViewsParameters()
    }

    private fun prepareDefaultPositions() {
        viewsPosition = resources.getIntArray(R.array.views_positions)
        waysPosition = resources.getIntArray(R.array.ways_position)
        gradientsPosition = resources.getIntArray(R.array.gradients_position)
    }

    private fun prepareAttributesSet(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlidingSquareLoaderView)
        viewMargin = typedArray.getDimensionPixelSize(R.styleable.SlidingSquareLoaderView_sslv_margin,
                resources.getDimensionPixelSize(R.dimen.view_margin))
        viewCornerRadius = typedArray.getDimensionPixelSize(R.styleable.SlidingSquareLoaderView_sslv_margin,
                resources.getDimensionPixelSize(R.dimen.view_margin))
        viewStartGradientColor = typedArray.getColor(R.styleable.SlidingSquareLoaderView_sslv_start_gradient_color,
                ContextCompat.getColor(context, R.color.start_gradient_color))
        viewEndGradientColor = typedArray.getColor(R.styleable.SlidingSquareLoaderView_sslv_end_gradient_color,
                ContextCompat.getColor(context, R.color.end_gradient_color))
        parentBackground = typedArray.getColor(R.styleable.SlidingSquareLoaderView_sslv_background_color,
                ContextCompat.getColor(context, R.color.background))
        squareViewSize = typedArray.getDimensionPixelSize(R.styleable.SlidingSquareLoaderView_sslv_square_size,
                resources.getDimensionPixelSize(R.dimen.square_size))
        typedArray.recycle()
    }

    private fun prepareWayCreator(startX: Int, startY: Int, viewMargin: Int, viewWidth: Int) {
        wayCreator = WayCreator(startX, startY, viewMargin, viewWidth)
    }

    private fun prepareParentSettings() {
        setBackgroundColor(parentBackground)
    }

    private fun prepareViewsParameters() {
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                val view = squares[0]
                val viewWidth = view.width
                val viewMargin = view.getLeftMargin()
                val startX = width / 2 - ((viewWidth + viewMargin) * Constant.VIEW_COUNT) / 2
                val startY = height / 2 - viewWidth / 2
                // prepare each view position of the last view
                prepareViews(startX, startY, viewMargin)
                // prepare way creator instance
                prepareWayCreator(startX, startY, viewMargin, viewWidth)
                // create ways for view animations
                prepareWays()
            }
        })
    }

    private fun prepareViews(startX: Int, startY: Int, viewMargin: Int) {
        squares.forEachIndexed { index, view ->
            view.x = startX.toFloat() + viewMargin + (index * (view.width + viewMargin))
            view.y = startY.toFloat()
            if (squares.lastIndex == index) {
                view.x = startX.toFloat() + viewMargin
                view.y = startY.toFloat()
            }
        }
    }

    private fun prepareWays() {
        ways.add(wayCreator.createWay(WayType.WAY_1))
        ways.add(wayCreator.createWay(WayType.WAY_2))
        ways.add(wayCreator.createWay(WayType.WAY_3))
        ways.add(wayCreator.createWay(WayType.WAY_4))
        ways.add(wayCreator.createWay(WayType.WAY_5))
        ways.add(wayCreator.createWay(WayType.WAY_6))
    }

    override fun show() {
        if(stopAnimation) {
            visibility = View.VISIBLE
            stopAnimation = false
            // animate each view
            animateViews()
        }
    }

    override fun hide() {
        waitToFinish = true
        visibility = View.GONE
    }

    private fun animateViews() {
        val currentView: View = squares[viewsPosition[currentViewPosition]]
        val currentWay: Path = ways[waysPosition[currentWayPosition]].currentWay
        animateView(currentView, currentWay, Constant.CURRENT_DURATION)
    }

    private fun incrementCurrentViewPosition() {
        if (currentViewPosition < viewsPosition.size - 1) {
            currentViewPosition++
        } else {
            currentViewPosition = 0
        }
    }

    private fun increaseCurrentGradientPosition() {
        if (gradientPosition < gradientsPosition.size - 1) {
            gradientPosition++
        } else {
            gradientPosition = 0
        }
    }

    private fun incrementCurrentWayPosition() {
        if (currentWayPosition < waysPosition.size - 1) {
            currentWayPosition++
        } else {
            currentWayPosition = 0
        }
    }

    private fun repeatAnimations() {
        if(!stopAnimation) {
            repeatHandler.post({
                incrementCurrentViewPosition()
                incrementCurrentWayPosition()
                increaseCurrentGradientPosition()
                animatePreviousView()
                animateCurrentView()
            })
        }
    }

    private fun animateView(view: View, way: Path, duration: Long) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "x", "y", way)
        objectAnimator.duration = duration
        objectAnimator.onEnd {
            if(currentViewPosition == 0 && waitToFinish) {
//                visibility = View.GONE
                stopAnimation = true
                waitToFinish = false
                reset()
            }
            repeatAnimations()
        }
        objectAnimator.start()
    }

    private fun animateCurrentView() {
        val currentView = squares[viewsPosition[currentViewPosition]]
        val currentWay: Path = ways[waysPosition[currentWayPosition]].currentWay
        animateView(currentView, currentWay, Constant.CURRENT_DURATION)
    }

    private fun animatePreviousView() {
        if (currentViewPosition > 0 && currentWayPosition > 0) {
            // get previous view
            val previousView = squares[viewsPosition[currentViewPosition - 1]]
            // update offset for drawing gradient
            previousView.updateGradientPosition(-gradientsPosition[gradientPosition])
            // get last way(Path) to finish animation
            val lastWay: Path = ways[waysPosition[currentWayPosition - 1]].lastWay
            // animate view
            animateViewByLastWay(previousView, lastWay, Constant.PREVIOUS_DURATION)
        } else {
            // get previous view
            val previousView = squares[viewsPosition[viewsPosition.size - 1]]
            // update offset for drawing gradient
            previousView.updateGradientPosition(-gradientsPosition[gradientPosition])
            // get last way(Path) to finish animation
            val lastWay: Path = ways[waysPosition[waysPosition.size - 1]].lastWay
            // animate view
            animateViewByLastWay(previousView, lastWay, Constant.PREVIOUS_DURATION)
        }
    }

    private fun animateViewByLastWay(view: View, way: Path, duration: Long) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "x", "y", way)
        objectAnimator.duration = duration
        objectAnimator.start()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        initializeSquaresCollection()
    }

    private fun initializeSquaresCollection() {
        for (i: Int in 1..5) {
            val view : SquareView = prepareSquareViews()
            squares.add(view)
            addView(view)
        }
        updateViews()
    }

    private fun updateViews() {
        squares.forEachIndexed { index, view ->
            if (index == squares.size - 1) {
                view.updateGradientPosition(0)
                return
            }
            view.updateGradientPosition(-index)
        }
    }

    private fun prepareSquareViews(): SquareView {
        val view = SquareView(context)
        view.prepareGradientDrawable(viewStartGradientColor, viewEndGradientColor, viewCornerRadius, squareViewSize)
        view.setLeftMargin(viewMargin)
        return view
    }

    private fun reset() {
        currentViewPosition = 0
        currentWayPosition = 0
        gradientPosition = 0
    }
}