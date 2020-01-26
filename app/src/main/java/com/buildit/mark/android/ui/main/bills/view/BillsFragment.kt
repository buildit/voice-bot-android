package com.buildit.mark.android.ui.main.bills.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.buildit.mark.android.R
import com.buildit.mark.android.data.network.Bill
import com.buildit.mark.android.ui.base.view.BaseFragment
import com.buildit.mark.android.ui.main.bills.interactor.BillsMVPInteractor
import com.buildit.mark.android.ui.main.bills.presenter.BillsMVPPresenter
import kotlinx.android.synthetic.main.fragment_bills.*
import javax.inject.Inject


/**
 * Created by harshit.laddha on 25/01/2020
 */
class BillsFragment : BaseFragment(), BillsMVPView {

    companion object {

        fun newInstance(): BillsFragment {
            return BillsFragment()
        }
    }

    @Inject
    internal lateinit var billAdapter: BillsAdapter
    @Inject
    internal lateinit var layoutManager: LinearLayoutManager
    @Inject
    internal lateinit var presenter: BillsMVPPresenter<BillsMVPView, BillsMVPInteractor>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_bills, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        bill_recycler_view.layoutManager = layoutManager
        bill_recycler_view.itemAnimator = DefaultItemAnimator()
        bill_recycler_view.adapter = billAdapter
        presenter.onViewPrepared()
    }

    override fun displayBillList(bills: List<Bill>?) = bills?.let {
        billAdapter.addBlogsToList(it)
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}