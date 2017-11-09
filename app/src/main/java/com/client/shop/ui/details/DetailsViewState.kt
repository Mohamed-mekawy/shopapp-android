package com.client.shop.ui.details

import android.os.Bundle
import com.client.shop.ui.details.contract.DetailsView
import com.domain.entity.Product
import com.hannesdorfmann.mosby3.mvp.viewstate.RestorableViewState


class DetailsViewState : RestorableViewState<DetailsView> {

    private var data: Product? = null

    companion object {
        private const val KEY_PRODUCT = "DetailsViewState-product"
    }

    override fun restoreInstanceState(`in`: Bundle?): RestorableViewState<DetailsView>? {
        if (`in` == null) {
            return null
        }
        data = `in`.getParcelable(KEY_PRODUCT)
        return this
    }

    override fun saveInstanceState(out: Bundle) {
        out.putParcelable(KEY_PRODUCT, data)
    }

    override fun apply(view: DetailsView?, retained: Boolean) {

        data?.let {
            view?.showContent(it)
        }
    }

    fun setData(data: Product) {
        this.data = data
    }

}