package com.client.shop.ui.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.client.shop.R
import com.domain.entity.Product
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_PRODUCT = "product"
        private const val EXTRA_SELECTED_POSITION = "selected_position"

        fun getStartIntent(context: Context, product: Product?, selectedPosition: Int = 0): Intent {
            val intent = Intent(context, GalleryActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, product)
            intent.putExtra(EXTRA_SELECTED_POSITION, selectedPosition)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val product: Product? = intent.getParcelableExtra(EXTRA_PRODUCT)
        val selectedPosition = intent.getIntExtra(EXTRA_SELECTED_POSITION, 0)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
        }

        val restoredFragment = supportFragmentManager.findFragmentByTag(GalleryFragment::javaClass.name)
        val fragment: GalleryFragment = if (restoredFragment is GalleryFragment) {
            restoredFragment
        } else {
            val newFragmentInstance = GalleryFragment.newInstance(product = product, selectedPosition = selectedPosition)
            newFragmentInstance.imageClickListener = View.OnClickListener {
                toolbar.visibility =
                        if (toolbar.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
            newFragmentInstance
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.galleryContainer, fragment, fragment::javaClass.name)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            if (item.itemId == android.R.id.home) {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}