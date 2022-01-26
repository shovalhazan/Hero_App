package com.main.hero_app.View.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.hero_app.Model.HeroDetails.HeroDetailsModel
import com.main.hero_app.Model.HeroDetails.HeroesList
import com.main.hero_app.R
import com.main.hero_app.View.Adapters.HeroRowCustomAdapter
import com.main.hero_app.ViewModel.RecyclerFragmentViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerListFragment.newInstance] factory method to create an instance of this fragment.
 */
class RecyclerListFragment : Fragment() {

    //views
    private lateinit var LST_searchListResult: RecyclerView
    private lateinit var heroAdapterHeroRow : HeroRowCustomAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var LBL_msg: TextView
    private lateinit var layout: ConstraintLayout

    //viewModel
    private lateinit var viewModel : RecyclerFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("pttt", "onCreate: FRG")
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("pttt", "onCreateView: ")
        val view:View? = inflater.inflate(R.layout.fragment_recycler_list, container, false)
        findViews(view)
        layout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLite))
        initRecyclerView()
        return view
    }

    private fun findViews(view: View?) {
        if (view != null) {
            LST_searchListResult =view.findViewById(R.id.fragment_recycler_LST_searchList)
            progressBar = view.findViewById(R.id.fragment_recycler_list_ProgressBar)
            LBL_msg=view.findViewById(R.id.fragment_recycler_list_LBL_msg)
            layout=view.findViewById(R.id.layout)
        }
    }


    private fun initRecyclerView() {
        LST_searchListResult.visibility= View.INVISIBLE
        LST_searchListResult.apply {
            layoutManager = LinearLayoutManager(this.context)
            val decoration  = DividerItemDecoration(this.context.applicationContext, LinearLayout.VERTICAL)
            addItemDecoration(decoration)
            heroAdapterHeroRow = HeroRowCustomAdapter()
            adapter = heroAdapterHeroRow
        }
    }

    /**
     * load hero details from api by name
     *
     * @param name- the name of the hero
     **/
    fun loadAPIData(name: String) {
        Log.d("pttt", "loadAPIData: "+name)
        progressBar.visibility= View.VISIBLE

        viewModel = ViewModelProvider(this).get(RecyclerFragmentViewModel::class.java)
        viewModel.getHeroListObserver().observe(this, Observer<HeroesList>{
            if(it !=null){
                Log.d("pttt", "loadAPIData: it!=null")
                checkIfNameExist(it)
            }else{
                Log.d("pttt", "loadAPIData: it==null")

            }
        })
        viewModel.getHeroesByName(name)
    }
//    private fun refreshHeroes(name:String) {
//        viewModel.getHeroesByName(name)
//        viewModel.heroStatus.observe(viewLifecycleOwner, Observer { apiStatus ->
//            when (apiStatus) {
//                RecyclerFragmentViewModel.HeroApiStatus.DONE -> {
//                    heroAdapterHeroRow.dataSet = viewModel._heroList.value?.results!!
//                    heroAdapterHeroRow.notifyDataSetChanged()
//                }
//                RecyclerFragmentViewModel.HeroApiStatus.NO_INTERNET_CONNECTION -> {
//
//                }
//                RecyclerFragmentViewModel.HeroApiStatus.LOADING -> {
//                    progressBar.visibility= View.VISIBLE
//                    LBL_msg.visibility = View.INVISIBLE
//                    LST_searchListResult.visibility = View.INVISIBLE
//                }
//                else->{
//                    mBinding.swipeRefreshHeroes.isRefreshing = false
//                }
//            }
//        })
//    }
    private fun checkIfNameExist(it: HeroesList) {
        Log.d("HeroFRG", "checkIfNameExist: ")
      if(it.error=="bad name search request") {
          Log.d("pttt", "checkIfNameExist: bad name search request")
        LBL_msg.visibility = View.INVISIBLE
        progressBar.visibility = View.INVISIBLE
        LST_searchListResult.visibility = View.INVISIBLE
      }else if(it.error=="character with given name not found") {
          Log.d("pttt", "checkIfNameExist: character with given name not found")
                LBL_msg.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
                LST_searchListResult.visibility = View.INVISIBLE
      }else if(it.results!=null){
            LBL_msg.visibility=View.INVISIBLE
            progressBar.visibility = View.INVISIBLE
            LST_searchListResult.visibility = View.VISIBLE
             setAdapter(it.results)
        }

    }

    private fun setAdapter(results: ArrayList<HeroDetailsModel>) {
        heroAdapterHeroRow.dataSet = results
        heroAdapterHeroRow.notifyDataSetChanged()
    }

}