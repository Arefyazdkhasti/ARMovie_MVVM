package com.example.armovie.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.armovie.R
import com.example.armovie.data.entity.Person.Cast
import com.example.armovie.data.network.BASE_IMAGE_MOVIE
import com.example.armovie.databinding.PersonFragmentBinding
import com.example.armovie.ui.base.ScopedFragment
import com.example.armovie.ui.itemRecyclerView.CastItemRecyclerView
import com.example.armovie.ui.itemRecyclerView.PersonCombinedCreditItemRecyclerView
import com.example.armovie.ui.viewModel.PersonViewModel
import com.example.armovie.ui.viewModel.PersonViewModelFactory
import com.example.armovie.utility.GlideApp
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory

class PersonFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactoryInstanceFactory: ((Int) -> PersonViewModelFactory) by factory()

    private lateinit var viewModel: PersonViewModel

    private var _binding: PersonFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PersonFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val safeArgs = arguments?.let { PersonFragmentArgs.fromBundle(it) }
        val personID = safeArgs?.personID
        if (personID != null)
            viewModel = ViewModelProvider(this, viewModelFactoryInstanceFactory(personID)).get(
                PersonViewModel::class.java
            )

        bindUI()
    }

    private fun bindUI() = launch {
        val person = viewModel.personDetail.await()

        person.observe(viewLifecycleOwner, Observer { personDetail ->
            if (personDetail == null) return@Observer

            (activity as? AppCompatActivity)?.supportActionBar?.title = personDetail.name
            //person image
            binding.personImage.clipToOutline = true
            GlideApp.with(this@PersonFragment)
                .load(BASE_IMAGE_MOVIE + personDetail.profilePath)
                .placeholder(R.drawable.load)
                .into(binding.personImage)

            binding.personName.text = personDetail.name

            binding.personKnownForDepartment.text = personDetail.knownForDepartment

            binding.personBiography.text = personDetail.biography

            binding.personBirthday.text = personDetail.birthday

            binding.personPlaceOfBirth.text = personDetail.placeOfBirth

        })

        val personCombinedCredit = viewModel.personCombinedCredit.await()

        personCombinedCredit.observe(viewLifecycleOwner, Observer { combinedCredit ->
            if(combinedCredit == null) return@Observer

            initPersonCombinedCreditRecyclerView(combinedCredit.cast.toCombinedCreditItems(),binding.knownForRecyclerView)
        })
    }

    private fun initPersonCombinedCreditRecyclerView(items: List<PersonCombinedCreditItemRecyclerView>, recyclerView: RecyclerView) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(this.context, 2,GridLayoutManager.HORIZONTAL,false)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener{ item, view ->
            (item as? PersonCombinedCreditItemRecyclerView)?.let {
                sendMovieID(it.cast.id,view)
            }
        }
    }

    private fun sendMovieID(movieID: Int, view: View) {
        val action = PersonFragmentDirections.sendMovieId(movieID)
        Navigation.findNavController(view).navigate(action)
    }

    private fun List<Cast>.toCombinedCreditItems(): List<PersonCombinedCreditItemRecyclerView> = this.map {
        PersonCombinedCreditItemRecyclerView(it)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}