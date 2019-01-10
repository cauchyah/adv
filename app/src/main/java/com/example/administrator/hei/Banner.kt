package com.p2pfellow.base.ui

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.p2pfellow.base.R
import kotlinx.android.synthetic.main.layout_banner.view.*

/**
 * Created by Enzo on 2019/1/10.
 */
class Banner(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {

    private lateinit var mAdapter: ViewPagerAdapter<*, *>

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_banner, this)

    }

    fun <T, V> setPages(holderCreator: BannerItemViewHolderCreator<V>, mData: MutableList<T>): Banner {
        mAdapter = ViewPagerAdapter(holderCreator, mData)
        viewPager.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
        return this
    }

    interface BannerItemViewHolderCreator<BannerItemView> {
        fun createHolder(): BannerItemView
    }

    open abstract class BannerItemView<T>(context: Context) : FrameLayout(context) {
        abstract fun createView(context: Context): View
        abstract fun updateUI(context: Context, position: Int, item: T)
    }

    inner class ViewPagerAdapter<T, V>(val holderCreator: BannerItemViewHolderCreator<V>, val list: MutableList<T>) : PagerAdapter() {
        private val mViewRecycler = mutableListOf<BannerItemView<T>>()
        private val mItemViewMap = hashMapOf<T, BannerItemView<T>>()
        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
            var item: T = `object` as T
            return view == mItemViewMap[item]
        }

        override fun getCount(): Int {
            return list.size
        }

        override fun instantiateItem(container: ViewGroup?, position: Int): T {
            var view: BannerItemView<T> = if (mViewRecycler.size > 0) {
                mViewRecycler.removeAt(0)
            } else {
                var itemView = (holderCreator.createHolder() as BannerItemView<T>)
                itemView.createView(context)
                itemView
            }
            container?.addView(view)
            var itemData: T = list[position]
            view.updateUI(context, position, itemData)
            mItemViewMap[itemData] = view
            return itemData
        }

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
            var item: T = `object` as T
            var view: BannerItemView<T>? = mItemViewMap.remove(item)
            view?.let {
                container?.removeView(it)
                mViewRecycler.add(it)
            }

        }

        override fun getItemPosition(`object`: Any?): Int {
            var item: T = `object` as T
            return if (list.contains(item)) {
                list.indexOf(item)
            } else {
                POSITION_NONE
            }
        }
    }
}