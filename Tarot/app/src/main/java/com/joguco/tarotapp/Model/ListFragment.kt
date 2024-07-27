package com.joguco.tarotapp.Model

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joguco.tarotapp.Interface.OnItemClick
import com.joguco.tarotapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_COLUMN_COUNT = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    //Atributes
    private var columnCount = 2
    var listener: OnItemClick? = null //Click Listener

    //Constructor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    //Function that attaches the activity and implements OnItemClick
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemClick) {
            listener = context
        }
    }

    //Function taht detaches the listener
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    //Function that creates View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflating the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)


        //Setting LayoutManager and RecyclerView
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                adapter = CartaAdapter(Carta.loadSeries(context),listener)
            }
        }
        return view
    }

    //Static object
    companion object {
        @JvmStatic
        //Charging List of Series
        fun newInstance(columnCount: Int) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}