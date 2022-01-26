package com.main.hero_app.View.Fragments
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.hero_app.Model.HeroDetails.HeroDetailsModel
import com.main.hero_app.R
import com.main.hero_app.View.Adapters.HeroRowCustomAdapter
import com.main.hero_app.ViewModel.RecyclerFragmentViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerListFragment.newInstance] factory method to create an instance of this fragment.
 */
class MyHeroFragment : Fragment (){
    private lateinit var LST_searchListResult : RecyclerView
    private lateinit var heroAdapterHeroRow : HeroRowCustomAdapter
    private lateinit var progressBar: ProgressBar
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
        initRecyclerView()
        loadAPIData()
        return view
    }

    private fun findViews(view: View?) {
        if (view != null) {
            LST_searchListResult =view.findViewById(R.id.fragment_recycler_LST_searchList)
            progressBar = view.findViewById(R.id.fragment_recycler_list_ProgressBar)
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
     * load myHero details from api by name
     *
     * @param name- the name of the hero
     **/
    fun loadAPIData() {
        progressBar.visibility= View.VISIBLE
        viewModel = ViewModelProvider(this).get(RecyclerFragmentViewModel::class.java)
        viewModel._myHeroList.observe(viewLifecycleOwner, Observer<ArrayList<HeroDetailsModel>>{
            if(it !=null){
                Log.d("pttt", "loadAPIData: it!=null")
                setAdapter(it)
                progressBar.visibility = View.INVISIBLE
                LST_searchListResult.visibility = View.VISIBLE
            }else{
                Log.d("pttt", "loadAPIData: it==null")
            }
        })
        viewModel.getMyHeroesByName()
    }

    private fun setAdapter(results: ArrayList<HeroDetailsModel>) {
        heroAdapterHeroRow.dataSet = results
        heroAdapterHeroRow.notifyDataSetChanged()

    }

}