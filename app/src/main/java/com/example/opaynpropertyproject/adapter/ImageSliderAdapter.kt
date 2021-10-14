package com.opaynkart.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.opaynpropertyproject.R
import com.example.opaynpropertyproject.api.Keys
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.image_slider_item.view.*


class ImageSliderAdapter(private val context: Context  ) : PagerAdapter() {

        val img = Keys.customerList
    private var inflater: LayoutInflater? = null
    private val images = arrayOf(R.drawable.properties1, R.drawable.properties1)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view === `object`
    }

    override fun getCount(): Int {

        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater!!.inflate(R.layout.image_slider_item, null)
//        view.imageView_slide.setImageResource(images[position])
        Picasso.get().load(img?.get(position)?.image?.image).placeholder(R.drawable.properties1).into(view.imageView_slide)

        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}